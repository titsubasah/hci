package com.ecommerce.module.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.module.management.entity.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, String> {
  
  Module findOneByModuleId(String moduleId);
  
}
