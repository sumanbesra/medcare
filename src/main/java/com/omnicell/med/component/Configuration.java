package com.omnicell.med.component;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(25); //587
	    
	    mailSender.setUsername("omnicellone@gmail.com");
	    mailSender.setPassword("omnicell@123");
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}
	
	public static void main(String[] args) {
		 JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		    mailSender.setHost("smtp.gmail.com");
		    mailSender.setPort(25); //587
		    
		    mailSender.setUsername("omnicellone@gmail.com");
		    mailSender.setPassword("omnicell@123");
		    
		    Properties props = mailSender.getJavaMailProperties();
		    props.put("mail.transport.protocol", "smtp");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.debug", "true");
		    
		    
		    String to = "ajeetmbr@gmail.com";

			String sender = "ajeetmbr@gmail.com";
			
			String subject = "Restock during completed";

			SimpleMailMessage message = new SimpleMailMessage();
			
			message.setFrom("omnicellone@gmail.com");
	        message.setTo(to); 
	        message.setSubject(subject); 
	        message.setText("Med didn't got restocked");
	        mailSender.send(message);
	}

}
