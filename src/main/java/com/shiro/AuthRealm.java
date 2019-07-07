package com.shiro;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.entity.Account;
import com.service.AccountService;
import com.util.CustomerContextHolder;
import com.util.SwitchRole;

public class AuthRealm extends AuthorizingRealm{

	@Autowired@Qualifier("AccountServiceImpl")
	private AccountService accountService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATASOURCE_A); 
		String username = (String)principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
	    authorizationInfo.addRole(accountService.findRole(username).getName());
	    SwitchRole.switchRoleByUserName(accountService.findRole(username).getName());
	    return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String)token.getPrincipal();
        Account account = accountService.findAccountByName(username);
		
        if(account == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                account.getAccountName(), //用户名
                account.getPassword(), //密码
                ByteSource.Util.bytes(account.getAccountName()+account.getPassword()),//salt=username+salt
                getName()  //realm name
        );
        System.out.println("authenticationInfo:"+authenticationInfo);
        return authenticationInfo;
	}
	
	 @Override
	    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
	        super.clearCachedAuthorizationInfo(principals);
	    }

	    @Override
	    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
	        super.clearCachedAuthenticationInfo(principals);
	    }

	    @Override
	    public void clearCache(PrincipalCollection principals) {
	        super.clearCache(principals);
	    }

	    public void clearAllCachedAuthorizationInfo() {
	        getAuthorizationCache().clear();
	    }

	    public void clearAllCachedAuthenticationInfo() {
	        getAuthenticationCache().clear();
	    }

	    public void clearAllCache() {
	        clearAllCachedAuthenticationInfo();
	        clearAllCachedAuthorizationInfo();
	    }

}
