package com.interview.test.repository;

import com.interview.test.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author  Chandra Bhushan Verma
 * JPA repository interface for saving photos
 */
@Repository
public interface PhotoRepository extends JpaRepository<Photo,Long> {
}
