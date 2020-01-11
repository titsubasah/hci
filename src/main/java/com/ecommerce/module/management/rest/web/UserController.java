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

import com.ecommerce.module.management.rest.model.request.GroupAssignRequest;
import com.ecommerce.module.management.rest.model.request.UserRequest;
import com.ecommerce.module.management.rest.model.response.BaseResponse;
import com.ecommerce.module.management.rest.model.response.SuccessResponse;
import com.ecommerce.module.management.rest.model.response.SuccessValueResponse;
import com.ecommerce.module.management.rest.model.response.UserResponse;
import com.ecommerce.module.management.service.api.UserService;

@RestController
@RequestMapping(ApiPathConstant.API_USER)
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping(path = ApiPathConstant.ASSIGN_USER_TO_GROUP)
  public ResponseEntity<BaseResponse> assignToGroup(
      @RequestBody GroupAssignRequest groupAssignRequest) throws Exception {
    userService.assignGroup(groupAssignRequest);
    return ResponseEntity.ok(new SuccessResponse("User assigned to group successfully"));
  }

  @PostMapping
  public ResponseEntity<BaseResponse> store(@RequestBody UserRequest userRequest) throws Exception {
    userService.save(userRequest);
    return ResponseEntity.ok(new SuccessResponse("Data stored successfully"));
  }

  @GetMapping
  public ResponseEntity<BaseResponse> findAll() {
    return ResponseEntity.ok(new SuccessValueResponse<List<UserResponse>>(userService.findAll()));
  }

  @PutMapping
  public ResponseEntity<BaseResponse> update(@RequestParam String userId,
      @RequestBody UserRequest userRequest) throws Exception {
    userService.update(userId, userRequest);
    return ResponseEntity.ok(new SuccessResponse("Data updated successfully"));
  }

  @DeleteMapping
  public ResponseEntity<BaseResponse> delete(@RequestParam String userId) throws Exception {
    userService.delete(userId);
    return ResponseEntity.ok(new SuccessResponse("Data deleted successfully"));
  }

}
