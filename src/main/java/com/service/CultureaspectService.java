package com.service;

import java.util.List;

import com.entity.Cultureaspect;

public interface CultureaspectService {

	public void createCultureaspect(Cultureaspect cultureaspect);
	
	public void updateCultureaspect(Cultureaspect cultureaspect);
	
	public void deleteCultureaspect(String cultureaspectId);
	
	Cultureaspect findOne(String cultureaspectId);
	
	List<Cultureaspect> findAll();
	
	List<String> findAllId();
	
}
