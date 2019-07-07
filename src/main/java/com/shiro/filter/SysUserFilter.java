package com.shiro.filter;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.Constants;
import com.service.AccountService;
import com.util.CustomerContextHolder;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

/**
 * sysUserFilter 用于根据当前登录用户身份获取 User 信息放入 request
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-15
 * <p>Version: 1.0
 */
public class SysUserFilter extends PathMatchingFilter {

    @Autowired@Qualifier("AccountServiceImpl")
    private AccountService accountService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
    	System.out.println("onPreHandle");
    	CustomerContextHolder.setCustomerType(CustomerContextHolder.DATASOURCE_A); 
        String username = (String)SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(Constants.CURRENT_USER, accountService.findAccountByName(username));
        System.out.println("123"+request.getAttribute("user"));
        return true;
    }
}
