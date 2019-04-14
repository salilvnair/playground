package com.salilvnair.playground.springboot.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.salilvnair.playground.springboot.aop.DateSourceRoutingAspect;
import com.salilvnair.playground.springboot.repository.UserRepository;
import com.salilvnair.playground.springboot.service.UserService;
import com.salilvnair.playground.springboot.service.impl.UserServiceImpl;



@Configuration
@EnableConfigurationProperties
@EnableJpaRepositories("com.salilvnair.playground.springboot.repository")
@EntityScan(basePackages = "com.salilvnair.playground.springboot.entity")
public class DatabaseRoutingConfig {
    @Bean
    @Primary
    public DataSource dataSource(Map<Object, Object> dataSources) {
        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setTargetDataSources(dataSources());
        routingDataSource.setDefaultTargetDataSource(defaultTargetDataSource());
        routingDataSource.afterPropertiesSet();
        return routingDataSource;
    }
    
    @Bean
    public DataSource defaultTargetDataSource() {
        return DataSourceBuilder.create()
        		.driverClassName("org.h2.Driver")
        		.url("jdbc:h2:mem:testdb")
        		.username("sa")
        		.build();
    }

    @Bean
    public Map<Object, Object> dataSources() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put(RoutingDataSource.DEFAULT_DATASOURCE, defaultTargetDataSource());
        return map;
    }

    @Bean
    public DateSourceRoutingAspect datebaseRoutingAspect() {
        return new DateSourceRoutingAspect();
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }
}
