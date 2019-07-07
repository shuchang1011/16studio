package com.shiro.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;

import com.entity.Account;

public class ResFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session= req.getSession();
		if (session==null){
            MDC.put("userId",UUID.randomUUID().toString());  
        }
        else{
            Account customer=(Account)session.getAttribute("user");
            if (customer==null){
                MDC.put("userId",UUID.randomUUID().toString());
                MDC.put("userName",UUID.randomUUID().toString());
            }
            else
            {
                MDC.put("userId",customer.getDisplayName());
                MDC.put("userName",customer.getDisplayName());
            }
        }
		//请求转发给过滤器链上下一个filter对象,即web.xml里面配置的filter过滤器  	
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
