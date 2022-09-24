package com.omnicell.med.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("pharmTech")
public class PharmTech {

	@Id
	private String id;
	private String email;
	private String managerId;
	private String name;
}
