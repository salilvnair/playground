package com.salilvnair.playground.springboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.salilvnair.playground.springboot.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {}
