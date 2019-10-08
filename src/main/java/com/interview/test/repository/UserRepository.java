package com.interview.test.repository;

import com.interview.test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author  Chandra Bhushan Verma
 * JPA repository for users
 */

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}