package com.util;

public class CustomerContextHolder {
	public static final String DATASOURCE_A = "dataSource1";
	public static final String DATASOURCE_B = "dataSource2";
	public static final String DATASOURCE_C = "dataSource3";
	public static final String DATASOURCE_D = "dataSource4";
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static void setCustomerType(String customerType) {  
        contextHolder.set(customerType);  
    }  
    public static String getCustomerType() {  
        return contextHolder.get();  
    }  
    public static void clearCustomerType() {  
        contextHolder.remove();  
    }  
}
