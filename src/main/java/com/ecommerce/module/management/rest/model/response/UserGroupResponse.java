package com.ecommerce.module.management.rest.model.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupResponse implements Serializable {

  private static final long serialVersionUID = -7152789085487894718L;  
  private String groupId;
  private String groupName;
  
}
