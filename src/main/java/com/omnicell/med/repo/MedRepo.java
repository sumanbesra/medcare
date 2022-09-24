package com.omnicell.med.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.omnicell.med.document.Med;

public interface MedRepo extends MongoRepository<Med, String>{

	Med findByItemId(String itemId);
}

