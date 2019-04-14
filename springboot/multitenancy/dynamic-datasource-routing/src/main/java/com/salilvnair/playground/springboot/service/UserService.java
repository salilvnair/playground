package com.salilvnair.playground.springboot.service;

import com.salilvnair.playground.springboot.entity.User;
import com.salilvnair.playground.springboot.reflect.DatabaseId;

public interface UserService {

    User createUser(String username);

    User createUser(String userName, @DatabaseId String databaseId);
}
