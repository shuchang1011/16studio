package com.service;

import java.util.List;

import com.entity.Organization;

public interface OrganizationService {

	public void createOrganization(Organization organization);
	
	public void updateOrganization(Organization organization);
	
	public void deleteOrganization(String organizationId);
	
	Organization findOne(String organizationId);
	
	List<Organization> findAll();
	
}
