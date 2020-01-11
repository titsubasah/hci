package com.ecommerce.module.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.module.management.entity.UserGroup;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, String>{
  
  UserGroup findByUsers_id(String userId);
  
  UserGroup findByUsers_userId(String userId);
  
  UserGroup findOneByGroupId(String groupId);
}
