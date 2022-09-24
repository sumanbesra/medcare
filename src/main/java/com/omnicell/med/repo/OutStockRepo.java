package com.omnicell.med.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.omnicell.med.document.OutStock;

public interface OutStockRepo extends MongoRepository<OutStock, String>{
	
	List<OutStock> findByItemIdAndRestockOnIsNull(String pharmTechId);
	
	List<OutStock> findByPharmTechIdAndRestockOnIsNull(String pharmTechId);
	
}
