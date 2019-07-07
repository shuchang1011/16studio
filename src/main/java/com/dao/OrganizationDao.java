package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.entity.Organization;

@Repository("OrganizationDao")
public interface OrganizationDao {

	public void createOrganization(Organization organization);
	
	public void updateOrganization(Organization organization);
	
	public void deleteOrganization(@Param("organizationId")String organizationId);
	
	Organization findOne(@Param("organizationId")String organizationId);
	
	List<Organization> findAll();
	
}
