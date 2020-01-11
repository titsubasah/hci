package com.ecommerce.module.management.rest.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.module.management.rest.model.request.UserGroupRequest;
import com.ecommerce.module.management.rest.model.response.BaseResponse;
import com.ecommerce.module.management.rest.model.response.SuccessResponse;
import com.ecommerce.module.management.rest.model.response.SuccessValueResponse;
import com.ecommerce.module.management.rest.model.response.UserGroupResponse;
import com.ecommerce.module.management.service.api.UserGroupService;

@RestController
@RequestMapping(ApiPathConstant.API_USER_GROUP)
public class UserGroupController {

  @Autowired
  private UserGroupService userGroupService;

  @PostMapping
  public ResponseEntity<BaseResponse> store(@RequestBody UserGroupRequest userGroupRequest)
      throws Exception {
    userGroupService.save(userGroupRequest);
    return ResponseEntity.ok(new SuccessResponse("Data stored successfully"));
  }

  @GetMapping
  public ResponseEntity<BaseResponse> findAll() {
    return ResponseEntity
        .ok(new SuccessValueResponse<List<UserGroupResponse>>(userGroupService.findAll()));
  }

  @PutMapping
  public ResponseEntity<BaseResponse> update(@RequestParam String groupId,
      @RequestBody UserGroupRequest userGroupRequest) throws Exception {
    userGroupService.update(groupId, userGroupRequest);
    return ResponseEntity.ok(new SuccessResponse("Data updated successfully"));
  }

  @DeleteMapping
  public ResponseEntity<BaseResponse> delete(@RequestParam String groupId) throws Exception {
    userGroupService.delete(groupId);
    return ResponseEntity.ok(new SuccessResponse("Data deleted successfully"));
  }
}
