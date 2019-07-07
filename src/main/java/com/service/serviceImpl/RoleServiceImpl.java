package com.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.RoleDao;
import com.entity.Role;
import com.service.RoleService;

@Service("RoleServiceImpl")
public class RoleServiceImpl implements RoleService{

	@Autowired@Qualifier("RoleDao")
	private RoleDao roleDao;
	
	@Transactional(rollbackFor={RuntimeException.class,Exception.class})
	@Override
	public void createRole(Role role) {
		// TODO Auto-generated method stub
		roleDao.createRole(role);
	}
	
	@Transactional(rollbackFor={RuntimeException.class,Exception.class})
	@Override
	public void updateRole(Role role) {
		// TODO Auto-generated method stub
		roleDao.updateRole(role);
	}

	@Transactional(rollbackFor={RuntimeException.class,Exception.class})
	@Override
	public void deleteRole(String roleId) {
		// TODO Auto-generated method stub
		roleDao.deleteRole(roleId);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public Role findOne(String roleId) {
		// TODO Auto-generated method stub
		return roleDao.findOne(roleId);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleDao.findAll();
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public List<Role> findAllOverLevel(int level) {
		// TODO Auto-generated method stub
		return roleDao.findAllOverLevel(level);
	}

}
