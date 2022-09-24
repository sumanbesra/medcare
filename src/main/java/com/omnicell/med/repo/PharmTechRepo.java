package com.omnicell.med.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.omnicell.med.document.PharmTech;

public interface PharmTechRepo extends MongoRepository<PharmTech, String>{

	PharmTech findByManagerId(String managerId);
	
}
