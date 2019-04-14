package com.salilvnair.playground.springboot.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.salilvnair.playground.springboot.config.helper.DataSourceIdHolder;

public class RoutingDataSource extends AbstractRoutingDataSource {
	public static final String DEFAULT_DATASOURCE = "DEFAULT";
	 
    @Override
    protected Object determineCurrentLookupKey() {
    	if(DataSourceIdHolder.get()!=null) {
    		this.afterPropertiesSet();
    	}
        return DataSourceIdHolder.get()!=null?DataSourceIdHolder.get():DEFAULT_DATASOURCE;
    }
    
}
