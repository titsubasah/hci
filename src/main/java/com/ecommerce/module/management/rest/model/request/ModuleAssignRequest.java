package com.ecommerce.module.management.rest.model.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModuleAssignRequest implements Serializable{
 
  private static final long serialVersionUID = 2771657365827545635L;
  
  private String moduleId;
  private String groupId;
  private int orderNumber;
  
}
