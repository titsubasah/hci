package com.ecommerce.module.management.service.api;

import java.util.List;

import com.ecommerce.module.management.rest.model.request.ModuleAssignRequest;
import com.ecommerce.module.management.rest.model.response.ModuleResponse;

public interface ModuleService {

  List<ModuleResponse> findModuleByUserId(String userId) throws Exception;

  void assignGroupModule(ModuleAssignRequest moduleAssignRequest) throws Exception;

}
