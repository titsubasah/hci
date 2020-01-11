package com.ecommerce.module.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.ecommerce.module.management.entity")
@EnableJpaRepositories("com.ecommerce.module.management.repository")
public class ModuleManagementApplication {

  public static void main(String[] args) {
    SpringApplication.run(ModuleManagementApplication.class, args);
  }

}
