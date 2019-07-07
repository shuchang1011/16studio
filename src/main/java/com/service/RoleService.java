package com.service;

import java.util.List;

import com.entity.Role;

public interface RoleService {

	public void createRole(Role role);

	public void updateRole(Role role);
    
	public void deleteRole(String roleId);

    public Role findOne(String roleId);
    
    public List<Role> findAll();
    
    public List<Role> findAllOverLevel(int level);
}
