package com.ecommerce.module.management.rest.model.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupAssignRequest implements Serializable{
 
  private static final long serialVersionUID = -5145510259952711319L;
  
  private String userId;
  private String groupId;
  
}
