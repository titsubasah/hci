package com.ecommerce.module.management.service.api;

import java.util.List;

import com.ecommerce.module.management.rest.model.request.UserGroupRequest;
import com.ecommerce.module.management.rest.model.response.UserGroupResponse;

public interface UserGroupService {
  
  void save(UserGroupRequest userGroupRequest) throws Exception;

  void update(String groupId, UserGroupRequest userGroupRequest) throws Exception;

  void delete(String groupId) throws Exception;

  List<UserGroupResponse> findAll();

}
