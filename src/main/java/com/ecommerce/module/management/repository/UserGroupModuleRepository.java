package com.ecommerce.module.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.module.management.entity.UserGroupModule;

public interface UserGroupModuleRepository extends JpaRepository<UserGroupModule, String>{
  
  List<UserGroupModule> findByUserGroupIdOrderByOrderNumberAsc(String userGroupId);
  
  UserGroupModule findByUserGroup_idAndModule_id(String groupId, String moduleId);
}
