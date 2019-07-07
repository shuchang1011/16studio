package com.service;

import java.util.List;

import com.entity.Village;

public interface VillageService {

	public void createVillage(Village village);
	
	public void updateVillage(Village village);
	
	public void deleteVillage(String villageId);
	
	Village findOne(String villageId);
	
	Village findOneByName(String name);
	
	List<Village> findAll();
	
	List<Village> findAllByOrganizationId(String organizationId);
	
	List<Village> findDeletedVillage(String organizationId);
	
	List<Village> findAllDeletedVillage();
	
}
