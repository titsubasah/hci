package com.ecommerce.module.management.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.module.management.entity.UserGroup;
import com.ecommerce.module.management.exception.DataAlreadyExistException;
import com.ecommerce.module.management.exception.DataNotFoundException;
import com.ecommerce.module.management.repository.UserGroupRepository;
import com.ecommerce.module.management.rest.model.request.UserGroupRequest;
import com.ecommerce.module.management.rest.model.response.UserGroupResponse;
import com.ecommerce.module.management.service.api.UserGroupService;
import com.google.common.base.Preconditions;

@Service
public class UserGroupServiceImpl implements UserGroupService {

  @Autowired
  private UserGroupRepository userGroupRepository;

  @Override
  public void save(UserGroupRequest userGroupRequest) throws Exception {
    Preconditions.checkArgument(Strings.isNotBlank(userGroupRequest.getGroupId()),
        "Field 'code' can not be blank");
    final UserGroup existing = userGroupRepository.findOneByGroupId(userGroupRequest.getGroupId());
    if (Optional.ofNullable(existing).isPresent()) {
      throw new DataAlreadyExistException("user group code for " + userGroupRequest.getGroupId());
    }
    userGroupRepository.save(this.toUserGroupEntity(userGroupRequest));
  }

  @Override
  public void update(String groupId, UserGroupRequest userGroupRequest) throws Exception {
    Preconditions.checkArgument(Strings.isNotBlank(userGroupRequest.getGroupId()),
        "Field 'code' can not be blank");
    final UserGroup existing = Optional.ofNullable(userGroupRepository.findOneByGroupId(groupId))
        .orElseThrow(() -> new DataNotFoundException("user group code " + groupId));
    BeanUtils.copyProperties(userGroupRequest, existing, "id");
    userGroupRepository.save(existing);
  }

  @Override
  public void delete(String groupId) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public List<UserGroupResponse> findAll() {
    return Optional.ofNullable(userGroupRepository.findAll()).orElseGet(ArrayList::new).stream()
        .map(this::toUserGroupResponse).collect(Collectors.toList());
  }

  private UserGroup toUserGroupEntity(UserGroupRequest userGroupRequest) {
    return UserGroup.builder().groupId(userGroupRequest.getGroupId())
        .name(userGroupRequest.getName()).build();
  }

  private UserGroupResponse toUserGroupResponse(UserGroup userGroup) {
    return new UserGroupResponse(userGroup.getGroupId(), userGroup.getName());
  }
}
