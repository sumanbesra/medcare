package com.omnicell.med.document;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutStock {

	@Id
	private String id;
	private String itemId;
	private String dispenseOn;
	private String restockOn;
	private String pharmTechId;
	private Integer restockQty;
	private String dispenceCpmId;
	private String jobId;
	
}
