package com.ecommerce.module.management.rest.model.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleResponse implements Serializable {
  
  private static final long serialVersionUID = 3660288482931012863L;
  private String moduleName;
  private int moduleOrder;
}
