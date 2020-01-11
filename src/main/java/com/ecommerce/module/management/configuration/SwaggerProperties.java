package com.ecommerce.module.management.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "swagger.info")
@Data
public class SwaggerProperties {

  private String title;
  private String description;
  private String version;
  
}
