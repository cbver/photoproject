package com.interview.test.repository;

import com.interview.test.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author  Chandra Bhushan Verma
 * JPA repository for users roles
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
}
