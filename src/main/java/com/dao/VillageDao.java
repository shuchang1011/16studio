package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.entity.Village;

@Repository("VillageDao")
public interface VillageDao {

	public void createVillage(Village village);
	
	public void updateVillage(Village village);
	
	public void deleteVillage(@Param("villageId")String villageId);
	
	Village findOne(@Param("villageId")String villageId);
	
	Village findOneByName(@Param("name")String name);
	
	List<Village> findAll();
	
	List<Village> findAllByOrganizationId(@Param("organizationId")String organizationId);
	
	List<Village> findDeletedVillage(@Param("organizationId")String organizationId);
	
	List<Village> findAllDeletedVillage();
	
}
