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
public class UserRequest implements Serializable{
  
  private static final long serialVersionUID = 7782804135376475638L;
  private String userId;
  private String fullName;
  
}
