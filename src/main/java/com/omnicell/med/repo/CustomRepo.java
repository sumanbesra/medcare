package com.omnicell.med.repo;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.client.result.UpdateResult;
import com.omnicell.med.document.Med;
import com.omnicell.med.document.OutStock;
import com.omnicell.med.service.SchedulerService;
import com.omnicell.med.service.TaskDefinitionBean;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomRepo {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	OutStockRepo outStockRepo;
	
	@Autowired
	private SchedulerService schedulerService;
	
	@Autowired
	private TaskDefinitionBean taskDefinitionBean;

	public void updateDispenseQty(Med med) {
		Query query = new Query(Criteria.where("itemId").is(med.getItemId()));
		Update update = new Update();
		med.setParQty(med.getParQty() - med.getDispenseQty());
		update.set("dispenseQty", med.getDispenseQty());
		update.set("parQty", med.getParQty());

		OutStock outStock = OutStock.builder().itemId(med.getItemId()).pharmTechId("1649668063")
				.restockQty(med.getDispenseQty())
				.dispenceCpmId("CPC01 - 008890990")
				.dispenseOn(new Date().toString())
				.build();
		
		outStockRepo.save(outStock);
		scheduleTask(outStock);
		UpdateResult result = mongoTemplate.updateFirst(query, update, Med.class);

		if (result == null) {
			log.info("Record Not Updated!!!");
		} else {
			log.info("Record Updated!!!");
		}
	}
	
	public void scheduleTask(OutStock outStock) {
		String cronExpression = "0 0/1 * * * ?";
		String jobId = UUID.randomUUID().toString();
		outStock.setJobId(jobId);
		
		taskDefinitionBean.setOutStock(outStock);
		schedulerService.scheduleTask(jobId, taskDefinitionBean, cronExpression);
		
	}

	public void updateOstock(String id) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update();
		String date = new Date().toString();
		update.set("restockOn", date);

		UpdateResult result = mongoTemplate.updateFirst(query, update, OutStock.class);
		
		if (result == null) {
			log.info("Record Not Updated!!!");
		} else {
			log.info("Record Updated!!!");
		}
	}

}
