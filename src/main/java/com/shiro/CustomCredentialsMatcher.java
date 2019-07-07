package com.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.entity.Account;
import com.service.AccountService;

public class CustomCredentialsMatcher  extends SimpleCredentialsMatcher {
	
	@Autowired@Qualifier("AccountServiceImpl")
	private AccountService accountService;
	
    public boolean doCredentialsMatch(AuthenticationToken token,
            AuthenticationInfo info) {
        UsernamePasswordToken usertoken = (UsernamePasswordToken) token; 
        System.out.println("(String)usertoken.getPrincipal():"+(String)usertoken.getPrincipal());
        System.out.println("String.valueOf(usertoken.getPassword()):"+String.valueOf(usertoken.getPassword()));
        //注意token.getPassword()拿到的是一个char[]，不能直接用toString()，它底层实现不是我们想的直接字符串，只能强转
        Object Password = new SimpleHash(
                "md5",
                String.valueOf(usertoken.getPassword()),
                ByteSource.Util.bytes((String)usertoken.getPrincipal()+String.valueOf(usertoken.getPassword())),
                2).toHex();
        
        Object accountCredentials = getCredentials(info);
        System.out.println("accountCredentials:"+accountCredentials);
        System.out.println("Password:"+Password);

        //将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false  
        return equals(Password, accountCredentials);  
    }
}
