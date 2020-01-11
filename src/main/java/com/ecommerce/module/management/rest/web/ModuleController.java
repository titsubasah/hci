package com.ecommerce.module.management.rest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.module.management.rest.model.request.ModuleAssignRequest;
import com.ecommerce.module.management.rest.model.response.BaseResponse;
import com.ecommerce.module.management.rest.model.response.ModuleWrapperResponse;
import com.ecommerce.module.management.rest.model.response.SuccessResponse;
import com.ecommerce.module.management.service.api.ModuleService;

@RestController
@RequestMapping(ApiPathConstant.API_MODULE)
public class ModuleController {

  @Autowired
  private ModuleService moduleService;

  @GetMapping(ApiPathConstant.USER)
  public ResponseEntity<ModuleWrapperResponse> findModuleByUserId(
      @RequestParam("UserID") String userId) throws Exception {
    return ResponseEntity.ok(new ModuleWrapperResponse(moduleService.findModuleByUserId(userId)));
  }

  @PostMapping(ApiPathConstant.ASSIGN_GROUP_TO_MODULE)
  public ResponseEntity<BaseResponse> assignGroupModule(
      @RequestBody ModuleAssignRequest moduleAssignRequest) throws Exception {
    moduleService.assignGroupModule(moduleAssignRequest);
    return ResponseEntity.ok(new SuccessResponse("Module assigned successfully"));
  }
}
