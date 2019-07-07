package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.entity.Account;

@Repository("AccountDao")
public interface AccountDao {

	public void createAccount(Account account);
	
	public void updateAccount(Account account);
	
	public void deleteAccount(@Param("accountId")String accountId);
	
	Account findOne(@Param("accountId")String accountId);
	
	List<Account> findAll();
	
	List<Account> findAllInOrganization(@Param("organizationId")String organizationId);
	
	Account findByAccountName(@Param("accountName")String accountName);
	
	String findRoleIdByAccountId(@Param("accountId")String accountId);
	
}
