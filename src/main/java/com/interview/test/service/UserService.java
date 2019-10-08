package com.interview.test.service;

import com.interview.test.entity.User;

/**
 *  @author  Chandra Bhushan Verma
 *  User service used for save and find users
 */
public interface UserService {

    void save(User user);

    User findByUsername(String username);
}