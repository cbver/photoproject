package com.interview.test.service;

/**
 * @author  Chandra Bhushan Verma
 * Security service interface
 */
public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}