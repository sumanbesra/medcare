package com.omnicell.med.dao;

import java.util.List;

import com.omnicell.med.document.OutStock;

import lombok.Data;

@Data
public class OutStockDto {
	List<OutStock> data;
}
