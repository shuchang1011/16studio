package com.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.OrganizationDao;
import com.entity.Organization;
import com.service.OrganizationService;

@Service("OrganizationServiceImpl")
public class OrganizationServiceImpl implements OrganizationService{

	@Autowired@Qualifier("OrganizationDao")
	private OrganizationDao organizationDao;
	
	@Transactional(rollbackFor={RuntimeException.class,Exception.class})
	@Override
	public void createOrganization(Organization organization) {
		// TODO Auto-generated method stub
		organizationDao.createOrganization(organization);
	}

	@Transactional(rollbackFor={RuntimeException.class,Exception.class})
	@Override
	public void updateOrganization(Organization organization) {
		// TODO Auto-generated method stub
		organizationDao.updateOrganization(organization);
	}

	@Transactional(rollbackFor={RuntimeException.class,Exception.class})
	@Override
	public void deleteOrganization(String organizationId) {
		// TODO Auto-generated method stub
		organizationDao.deleteOrganization(organizationId);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public Organization findOne(String organizationId) {
		// TODO Auto-generated method stub
		return organizationDao.findOne(organizationId);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public List<Organization> findAll() {
		// TODO Auto-generated method stub
		return organizationDao.findAll();
	}

	
	
}
