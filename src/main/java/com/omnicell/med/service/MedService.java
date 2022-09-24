package com.omnicell.med.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omnicell.med.document.Med;
import com.omnicell.med.document.OutStock;
import com.omnicell.med.document.PharmTech;
import com.omnicell.med.repo.CustomRepo;
import com.omnicell.med.repo.MedRepo;
import com.omnicell.med.repo.OutStockRepo;
import com.omnicell.med.repo.PharmTechRepo;

@Service
public class MedService {
	
	@Autowired
	private MedRepo medRepo;
	
	@Autowired
	private OutStockRepo outStockRepo;
	
	@Autowired
	private PharmTechRepo pharmTechRepo;
	
	@Autowired
	private CustomRepo cusRepo;

	public List<Med> getAllMeds() {
		return medRepo.findAll();
	}
	
	public List<OutStock> getAllOutStocks(String techId) {
		return outStockRepo.findByPharmTechIdAndRestockOnIsNull(techId);
	}
	
	public void saveAllMeds(List<Med> meds) {
		medRepo.saveAll(meds);
	}
	
	public void saveAllPharmTech(List<PharmTech> pharmTech) {
		pharmTechRepo.saveAll(pharmTech);
	}
	
	public void updateDispenseQty(List<Med> meds) {
		meds.forEach(med -> {
			cusRepo.updateDispenseQty(med);
		});
	}

	public void updateOutStocks(String id) {
			cusRepo.updateOstock(id);
	}
}
