package com.ecommerce.module.management.rest.model.response;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleWrapperResponse implements Serializable {

  private static final long serialVersionUID = 3203631497534142883L;
  private List<ModuleResponse> modules;
  
}
