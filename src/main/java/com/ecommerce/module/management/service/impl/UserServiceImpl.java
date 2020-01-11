package com.ecommerce.module.management.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.module.management.entity.User;
import com.ecommerce.module.management.entity.UserGroup;
import com.ecommerce.module.management.exception.DataAlreadyExistException;
import com.ecommerce.module.management.exception.DataNotFoundException;
import com.ecommerce.module.management.repository.UserGroupRepository;
import com.ecommerce.module.management.repository.UserRepository;
import com.ecommerce.module.management.rest.model.request.GroupAssignRequest;
import com.ecommerce.module.management.rest.model.request.UserRequest;
import com.ecommerce.module.management.rest.model.response.UserResponse;
import com.ecommerce.module.management.service.api.UserService;
import com.google.common.base.Preconditions;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserGroupRepository userGroupRepository;

  @Override
  public void save(UserRequest userRequest) throws Exception {
    Preconditions.checkArgument(Strings.isNotBlank(userRequest.getUserId()),
        "Field 'userId' can not be blank");
    final User user = userRepository.findByUserId(userRequest.getUserId());
    if (Optional.ofNullable(user).isPresent()) {
      throw new DataAlreadyExistException("for userId: " + userRequest.getUserId());
    }
    userRepository.save(toUserEntity(userRequest));
  }

  @Override
  public void assignGroup(GroupAssignRequest groupAssignRequest) throws Exception {
    Preconditions.checkArgument(Strings.isNotBlank(groupAssignRequest.getUserId()),
        "Field 'userId' can not be blank");
    Preconditions.checkArgument(Strings.isNotBlank(groupAssignRequest.getGroupId()),
        "Field 'groupId' can not be blank");
    final UserGroup userGroup = Optional
        .ofNullable(userGroupRepository.findOneByGroupId(groupAssignRequest.getGroupId()))
        .orElseThrow(
            () -> new DataNotFoundException("group id for: " + groupAssignRequest.getGroupId()));
    final User user = Optional
        .ofNullable(userRepository.findByUserId(groupAssignRequest.getUserId())).orElseThrow(
            () -> new DataNotFoundException("user id for: " + groupAssignRequest.getUserId()));
    user.setUserGroup(userGroup);
    userRepository.save(user);
  }

  @Override
  public List<UserResponse> findAll() {
    final List<User> users = userRepository.findAll();    
    return Optional.ofNullable(users).orElseGet(ArrayList::new)
        .stream()
        .map(this::toUserResponse)
        .collect(Collectors.toList());
  }

  private User toUserEntity(UserRequest userRequest) {
    return User.builder().userId(userRequest.getUserId()).fullName(userRequest.getFullName())
        .build();
  }
  
  private UserResponse toUserResponse(User user) {
    final UserResponse userResponse = new UserResponse();
    BeanUtils.copyProperties(user, userResponse);
    userResponse.setGroupName(Optional.ofNullable(user.getUserGroup()).map(UserGroup::getName)
        .orElseGet(() -> Strings.EMPTY));
    return userResponse;
  }

}
