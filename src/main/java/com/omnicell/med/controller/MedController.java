package com.omnicell.med.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnicell.med.dao.MedDao;
import com.omnicell.med.dao.OutStockDto;
import com.omnicell.med.dao.PharmTechDao;
import com.omnicell.med.document.Med;
import com.omnicell.med.document.OutStock;
import com.omnicell.med.service.MedService;

@RestController
@RequestMapping("/api/v1/meds")
@CrossOrigin
public class MedController {
	
	@Autowired
	private MedService medService;
	
	@GetMapping
	public List<Med> getAllMeds() {
		return medService.getAllMeds();
	}
	
	@GetMapping("/restock")
	public List<OutStock> getAllOutStocks(@RequestParam("id") String techId) {
		return medService.getAllOutStocks(techId);
	}
	
	@PostMapping
	public void saveAllMeds(@RequestBody MedDao meds) {
		medService.saveAllMeds(meds.getData());
	}
	
	@PostMapping("/dispense") 
	public boolean dispense(@RequestBody MedDao meds) {
		medService.updateDispenseQty(meds.getData());
		return true;
	}
	
	@PostMapping("/pharmTech")
	public void saveAllPharmTech(@RequestBody PharmTechDao pharmTech) {
		medService.saveAllPharmTech(pharmTech.getData());
	}
	
	@PostMapping("/restock/{id}") 
	public boolean restock(@PathVariable("id") String id) {
		medService.updateOutStocks(id);
		return true;
	}
}
