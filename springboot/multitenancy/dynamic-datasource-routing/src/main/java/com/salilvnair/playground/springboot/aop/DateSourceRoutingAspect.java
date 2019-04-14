package com.salilvnair.playground.springboot.aop;

import java.lang.annotation.Annotation;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.salilvnair.playground.springboot.config.helper.DataSourceIdHolder;
import com.salilvnair.playground.springboot.reflect.DatabaseId;

@Component
@Aspect
@Order(200)
public class DateSourceRoutingAspect {

    @Autowired
    private Map<Object, Object> dataSources;

    @Pointcut("@annotation(com.salilvnair.playground.springboot.reflect.DatabaseRouting))")
    public void databaseRouting() {
    }

    @Pointcut("execution(* *(..))")
    public void atExecution() {
    	System.out.println();
    }

    @Around("atExecution() && databaseRouting()")
    public Object switchDatabase(ProceedingJoinPoint pjp) throws Throwable {
        String datbaseId = retrieveDatbaseId(pjp);
        try {
            if (null != datbaseId) {
            	addNewDataSourceAtRuntime();
                if (!dataSources.containsKey(datbaseId)) {
                    throw new IllegalArgumentException("Unknown database key: " + datbaseId);
                }
                DataSourceIdHolder.set(datbaseId);
            }
            return pjp.proceed();
        } finally {
        	DataSourceIdHolder.clear();
        }
    }
    
    private void addNewDataSourceAtRuntime() {
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
    
   

    private String retrieveDatbaseId(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Annotation[][] parameterAnnotations = signature.getMethod().getParameterAnnotations();
        Object[] args = pjp.getArgs();
        int idx = 0;
        Object tenantId = null;
        for (Annotation[] annotation : parameterAnnotations) {
            tenantId = args[idx++];
            for (Annotation annot : annotation) {
                if (DatabaseId.class.equals(annot.annotationType())) {
                    break;
                }
            }
        }
        return (String) tenantId;
    }

}
