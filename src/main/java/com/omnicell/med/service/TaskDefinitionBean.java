package com.omnicell.med.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import org.springframework.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.omnicell.med.document.Med;
import com.omnicell.med.document.OutStock;
import com.omnicell.med.document.PharmTech;
import com.omnicell.med.repo.MedRepo;
import com.omnicell.med.repo.OutStockRepo;
import com.omnicell.med.repo.PharmTechRepo;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

import lombok.Data;

@Service
@Data
public class TaskDefinitionBean implements Runnable {

	private OutStock outStock;
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private OutStockRepo outStockRepo;
	
	@Autowired
	private MedRepo medRepo;

	@Autowired
	private PharmTechRepo pharmTechRepo;
	
	@Autowired
    private JavaMailSender emailSender;
	
	@Autowired
	SchedulerService schedulerService;

	@Override
	public void run() {
		System.out.println("Running scheduler: " + outStock.getItemId());
		List<OutStock> outStocks = outStockRepo.findByItemIdAndRestockOnIsNull(outStock.getPharmTechId());
		Optional<PharmTech> tech = pharmTechRepo.findById(outStock.getPharmTechId());
		PharmTech manager = pharmTechRepo.findByManagerId(tech.get().getManagerId());
		sendMail(outStock, tech.get(), manager);
		System.out.println(outStocks);
	}

	public void sendMail(OutStock outStock, PharmTech tech, PharmTech manager) {
		createSfdcRecord(outStock, tech, manager);
	}
	
	public void createSfdcRecord(OutStock outStock, PharmTech tech, PharmTech manager) {
		Med med = medRepo.findByItemId(outStock.getItemId());
		String url = "https://bitorder66-dev-ed.my.salesforce.com/services/data/v54.0/sobjects/Notification__c/";
		Map<String, String> record = new HashMap<>();
		record.put("name", "1");
		record.put("ItemId__c", outStock.getItemId());
		record.put("PharmTech__c", tech.getName());
		record.put("Email__c", manager.getEmail());
		record.put("Description__c", med.getDescription());
		try {
			String token = "Bearer " + getToken();
			
			HttpHeaders header = new HttpHeaders();
			header.add("Authorization", token);
	        HttpEntity<?> request = new HttpEntity<>(record, header);
	        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST,request,String.class);
	        schedulerService.removeScheduledTask(outStock.getJobId());
	        System.out.println("Email send");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public String getToken() throws ConnectionException {
		ConnectorConfig partnerConfig = new ConnectorConfig();
		partnerConfig.setUsername("suman.b@sfdc.com"); // karteek_ssctest@sp.com
		partnerConfig.setPassword("Ajeet@123LyfoHDrNSuqQWgDNMQ56BNn0"); // spark1234
//		partnerConfig.setSessionId(sessionId);
		partnerConfig.setAuthEndpoint("https://login.salesforce.com/services/Soap/u/41.0");
		PartnerConnection pcon = new PartnerConnection(partnerConfig);
		return partnerConfig.getSessionId();

	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(new TaskDefinitionBean().getToken());
	}

	public static void send(String from, String password, String to, String sub, String msg) {
		// Get properties object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.ssl.enable", "true");
//		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(sub);
			message.setText(msg);
			// send message
			Transport.send(message);
			System.out.println("message sent successfully");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
