package com.salilvnair.playground.springboot.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class DynamicDataSourceInterceptor  extends HandlerInterceptorAdapter{
	@Autowired
	 private Map<Object, Object> dataSources;
	
	 protected void addNewDataSourceAtRuntime() {
	    	if(!dataSources.containsKey("DB2")) {
	    		DataSourceBuilder<?> dsBuilder = DataSourceBuilder
	    				.create()
	    				.driverClassName("org.postgresql.Driver")
	    				.username("postgres")
	    				.password("postgres")
	    				.url("jdbc:postgresql://localhost:5432/DB2");
	        	dataSources.put("DB2", dsBuilder.build());
	    	}
	    	if(!dataSources.containsKey("DB4")) {
	    		DataSourceBuilder<?> dsBuilder2 = DataSourceBuilder
	    				.create()
	    				.driverClassName("org.postgresql.Driver")
	    				.username("postgres")
	    				.password("postgres")
	    				.url("jdbc:postgresql://localhost:5432/DB4");
	        	dataSources.put("DB4", dsBuilder2.build());
	    	}
	    		
	    	
		}
	 
	 @Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
			//addNewDataSourceAtRuntime();
		 	//commenting above as we will be creating and adding datasource config
		 	//in the request controller however we can also add the configs
		 	//like above method i.e. intercepting any request and adding the db source.
			return true;
		}
	
}
