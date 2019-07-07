package com.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.VillageDao;
import com.entity.Village;
import com.service.VillageService;

@Service("VillageServiceImpl")
public class VillageServiceImpl implements VillageService{

	@Autowired@Qualifier("VillageDao")
	private VillageDao villageDao;
	
	@Transactional(rollbackFor = {RuntimeException.class,Exception.class})
	@Override
	public void createVillage(Village village) {
		// TODO Auto-generated method stub
		villageDao.createVillage(village);
	}

	@Transactional(rollbackFor = {RuntimeException.class,Exception.class})
	@Override
	public void updateVillage(Village village) {
		// TODO Auto-generated method stub
		villageDao.updateVillage(village);
	}

	@Transactional(rollbackFor = {RuntimeException.class,Exception.class})
	@Override
	public void deleteVillage(String villageId) {
		// TODO Auto-generated method stub
		villageDao.deleteVillage(villageId);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public Village findOne(String villageId) {
		// TODO Auto-generated method stub
		return villageDao.findOne(villageId);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public List<Village> findAll() {
		// TODO Auto-generated method stub
		return villageDao.findAll();
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public List<Village> findAllByOrganizationId(String organizationId) {
		// TODO Auto-generated method stub
		return villageDao.findAllByOrganizationId(organizationId);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public List<Village> findDeletedVillage(String organizationId) {
		// TODO Auto-generated method stub
		return villageDao.findDeletedVillage(organizationId);
	}

	@Override
	public Village findOneByName(String name) {
		// TODO Auto-generated method stub
		return villageDao.findOneByName(name);
	}

	@Override
	public List<Village> findAllDeletedVillage() {
		// TODO Auto-generated method stub
		return villageDao.findAllDeletedVillage();
	}

}
