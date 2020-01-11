package com.ecommerce.module.management.service.api;

import java.util.List;

import com.ecommerce.module.management.rest.model.request.GroupAssignRequest;
import com.ecommerce.module.management.rest.model.request.UserRequest;
import com.ecommerce.module.management.rest.model.response.UserResponse;

public interface UserService {

  void save(UserRequest userRequest) throws Exception;
  
  void assignGroup(GroupAssignRequest groupAssignRequest) throws Exception;

  List<UserResponse> findAll();

  default void update(String userId, UserRequest userRequest) {
    throw new UnsupportedOperationException("Not implemented");
  }

  default void delete(String userId) {
    throw new UnsupportedOperationException("Not implemented");
  }

}
