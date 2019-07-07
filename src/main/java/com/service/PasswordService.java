package com.service;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import com.entity.Account;

@Service("PasswordService")
public class PasswordService {

    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public void encryptPassword(Account account) {

        String newPassword = new SimpleHash(
                algorithmName,
                account.getPassword(),
                ByteSource.Util.bytes(account.getAccountName()+account.getPassword()),
                hashIterations).toHex();

        account.setPassword(newPassword);
    }
    
    public String encryptPassword(String account,String password) {

        String newPassword = new SimpleHash(
                algorithmName,
                password,
                ByteSource.Util.bytes(account+password),
                hashIterations).toHex();
        return newPassword;
        
    }
    
    public static void main(String args[]) {
    	PasswordService p = new PasswordService();
    	String password = p.encryptPassword("communityAdmin", "123456");
    	System.out.print(password);
    }
	
}
