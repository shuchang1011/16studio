package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.entity.Cultureaspect;

@Repository("CultureaspectDao")
public interface CultureaspectDao {

	public void createCultureaspect(Cultureaspect cultureaspect);
	
	public void updateCultureaspect(Cultureaspect cultureaspect);
	
	public void deleteCultureaspect(@Param("cultureaspectId")String cultureaspectId);
	
	Cultureaspect findOne(@Param("cultureaspectId")String cultureaspectId);
	
	List<Cultureaspect> findAll();
	
	List<String> findAllId();

}
