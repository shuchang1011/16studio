package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.entity.Role;

@Repository("RoleDao")
public interface RoleDao {

	public void createRole(Role role);
	
	public void updateRole(Role role);
    
	public void deleteRole(@Param("roleId")String roleId);

    public Role findOne(@Param("roleId")String roleId);
    
    public List<Role> findAll();
    
    public List<Role> findAllOverLevel(@Param("level")int level);
	
}
