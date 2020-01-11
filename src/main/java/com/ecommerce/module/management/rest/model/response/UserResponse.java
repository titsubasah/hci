package com.ecommerce.module.management.rest.model.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {

  private static final long serialVersionUID = -7066603342876309631L;
  
  private String userId;
  private String fullName;
  private String groupName;
}
