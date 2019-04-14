package com.salilvnair.playground.springboot.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.salilvnair.playground.springboot.entity.User;
import com.salilvnair.playground.springboot.reflect.DatabaseId;
import com.salilvnair.playground.springboot.reflect.DatabaseRouting;
import com.salilvnair.playground.springboot.repository.UserRepository;
import com.salilvnair.playground.springboot.service.UserService;

@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {this.repository = repository;}

    @Override
    public User createUser(String username) {
        return null;
    }

    @Override
    @DatabaseRouting
    public User createUser(String userName, @DatabaseId String databseId) {
        User user = new User();
        user.setUserName(userName);
        return repository.save(user);
    }
}
