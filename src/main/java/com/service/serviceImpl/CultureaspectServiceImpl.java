package com.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.CultureaspectDao;
import com.entity.Cultureaspect;
import com.service.CultureaspectService;

@Service("CultureaspectServiceImpl")
public class CultureaspectServiceImpl implements CultureaspectService{

	@Autowired@Qualifier("CultureaspectDao")
	private CultureaspectDao cultureaspectDao;
	
	@Override
	public void createCultureaspect(Cultureaspect cultureaspect) {
		// TODO Auto-generated method stub
		cultureaspectDao.createCultureaspect(cultureaspect);
	}

	@Override
	public void updateCultureaspect(Cultureaspect cultureaspect) {
		// TODO Auto-generated method stub
		cultureaspectDao.updateCultureaspect(cultureaspect);
	}

	@Override
	public void deleteCultureaspect(String cultureaspectId) {
		// TODO Auto-generated method stub
		cultureaspectDao.deleteCultureaspect(cultureaspectId);
	}

	@Override
	public Cultureaspect findOne(String cultureaspectId) {
		// TODO Auto-generated method stub
		return cultureaspectDao.findOne(cultureaspectId);
	}

	@Override
	public List<Cultureaspect> findAll() {
		// TODO Auto-generated method stub
		return cultureaspectDao.findAll();
	}

	@Override
	public List<String> findAllId() {
		// TODO Auto-generated method stub
		return cultureaspectDao.findAllId();
	}

}
