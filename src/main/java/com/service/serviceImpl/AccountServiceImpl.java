package com.service.serviceImpl;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.AccountDao;
import com.entity.Account;
import com.entity.Role;
import com.entity.Member;
import com.service.AccountService;
import com.service.MemberService;
import com.service.OrganizationService;
import com.service.PasswordService;
import com.service.RoleService;

@Service("AccountServiceImpl")
public class AccountServiceImpl implements AccountService{

	@Autowired@Qualifier("AccountDao")
	private AccountDao accountDao;
	
	@Autowired@Qualifier("PasswordService")
	private PasswordService passwordService;
	
	@Autowired@Qualifier("RoleServiceImpl")
	private RoleService roleService;
	
	@Autowired@Qualifier("MemberServiceImpl")
	private MemberService memberService;
	
	@Autowired@Qualifier("OrganizationServiceImpl")
	private OrganizationService organizationService;
	
	@Transactional(rollbackFor={RuntimeException.class,Exception.class})
	@Override
	public void createAccount(Account account) throws SQLSyntaxErrorException {
		// TODO Auto-generated method stub
		passwordService.encryptPassword(account);
		accountDao.createAccount(account);
	}

	@Transactional(rollbackFor={RuntimeException.class,Exception.class})
	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub
		accountDao.updateAccount(account);
	}

	@Transactional(rollbackFor={RuntimeException.class,Exception.class})
	@Override
	public void deleteAccount(String accountId) {
		// TODO Auto-generated method stub
		accountDao.deleteAccount(accountId);
		memberService.deleteMember(memberService.findOneByAccountId(accountId).getId());
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public Account findAccountById(String Id) {
		// TODO Auto-generated method stub
		return accountDao.findOne(Id);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return accountDao.findAll();
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public List<Account> findAllInOrganization(String organizationId) {
		// TODO Auto-generated method stub
		return accountDao.findAllInOrganization(organizationId);
	}


	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public Account findAccountByName(String accountName) {
		// TODO Auto-generated method stub
		System.out.println("AccountName:"+accountName);
		System.out.println("account:"+accountDao.findByAccountName(accountName));
		return accountDao.findByAccountName(accountName);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public Role findRole(String accountName) {
		// TODO Auto-generated method stub
		Account account = accountDao.findByAccountName(accountName);
		String roleId = accountDao.findRoleIdByAccountId(account.getId());
		Role role = roleService.findOne(roleId);
		return role;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public Member findPersonalDataById(String accountId) {
		// TODO Auto-generated method stub
		return memberService.findOneByAccountId(accountId);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findOrganizationById(String accountId) {
		if(memberService.findOneByAccountId(accountId)==null) {
			return null;
		}
		String organizationId = memberService.findOneByAccountId(accountId).getOrganizationId();
		if(organizationService.findOne(organizationId) == null) {
			return null;
		}
		return organizationService.findOne(organizationId).getName();
	}

}
