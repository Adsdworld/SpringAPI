package com.example.springapi.repository;

import com.example.springapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Database access
 * Define User as a table with a primary type Long
 * Provide methods (findAll, save...) to interact with the database
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
