package com.shiro.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class XSSFilter implements Filter {  
	  
    @Override  
    public void destroy() {  
        // TODO Auto-generated method stub  
  
    }  
  
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)  
            throws IOException, ServletException {  
        // TODO Auto-generated method stub  
    	//请求转发给过滤器链上下一个filter对象,即web.xml里面配置的filter过滤器  	
        chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response);  
    }  
  
    @Override  
    public void init(FilterConfig arg0) throws ServletException {  
        // TODO Auto-generated method stub  
    	
    }  
  
}  