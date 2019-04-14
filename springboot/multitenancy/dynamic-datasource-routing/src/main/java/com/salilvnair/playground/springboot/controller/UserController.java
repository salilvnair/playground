package com.salilvnair.playground.springboot.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salilvnair.playground.springboot.entity.User;
import com.salilvnair.playground.springboot.service.UserService;


@RestController
@Transactional
public class UserController {
	
	 @Autowired
	  private UserService userService;
	 
	 @Autowired
	 private Map<Object, Object> dataSources;
	
	//http://localhost:8080/swagger-ui.html
	//use above swagger URL to trigger request
	
	@RequestMapping(path = "/user/save", method= RequestMethod.POST)
    public ResponseEntity<?> saveUserData() {
		 DataSourceBuilder<?> dsBuilder2 = DataSourceBuilder
 				.create()
 				.driverClassName("org.postgresql.Driver")
 				.username("postgres")
 				.password("postgres")
 				.url("jdbc:postgresql://localhost:5432/DB5");
     	dataSources.put("DB5", dsBuilder2.build());
     	User user = userService.createUser("Only From Controller", "DB5");
        return ResponseEntity.ok(user);
		
    }

}
