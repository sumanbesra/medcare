package com.omnicell.med.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("med")
public class Med {
	
	@Id
	private String itemId;
	private String cpmId;
	private String description;
	private Integer parQty;
	private String binId;
	private Integer restockQty;
	private Integer dispenseQty;
	private Date dispenseOn;
	private Date restockOn;
	private String pharmTechId;
}
