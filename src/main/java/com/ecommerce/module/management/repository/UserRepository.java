package com.ecommerce.module.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.module.management.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  
  User findByUserId(String userId);
  
}
