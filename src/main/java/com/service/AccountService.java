package com.service;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

import com.entity.Account;
import com.entity.Member;
import com.entity.Role;

public interface AccountService {

	public void createAccount(Account account) throws SQLSyntaxErrorException;

	public void updateAccount(Account account);

	public void deleteAccount(String accountId);
	
	Account findAccountById(String Id);
	
	List<Account> findAll();
	
	List<Account> findAllInOrganization(String organizationId);
	
	public Account findAccountByName(String accountName);
	
	public Member findPersonalDataById(String accountId);
	
	public Role findRole(String accountName);
	
	public String findOrganizationById(String accountId);
	
}
