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
public class UserGroupRequest implements Serializable {

  private static final long serialVersionUID = 1412121937644623675L;
  
  private String groupId;
  private String name;

}
