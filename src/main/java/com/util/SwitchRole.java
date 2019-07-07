package com.util;

public class SwitchRole {
	
	
	public static void switchRoleByUserName(String role) {
		if(role.equals("超级管理员")) {
			CustomerContextHolder.setCustomerType(CustomerContextHolder.DATASOURCE_A); 
		}else if(role.equals("机构管理员")){
			CustomerContextHolder.setCustomerType(CustomerContextHolder.DATASOURCE_B); 
		}else if(role.equals("学者")) {
			CustomerContextHolder.setCustomerType(CustomerContextHolder.DATASOURCE_C);
		}else {
			CustomerContextHolder.setCustomerType(CustomerContextHolder.DATASOURCE_D);
		}
	}
	
}
