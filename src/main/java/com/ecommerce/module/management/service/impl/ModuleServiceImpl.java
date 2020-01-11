package com.ecommerce.module.management.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.module.management.entity.Module;
import com.ecommerce.module.management.entity.UserGroup;
import com.ecommerce.module.management.entity.UserGroupModule;
import com.ecommerce.module.management.exception.DataNotFoundException;
import com.ecommerce.module.management.repository.ModuleRepository;
import com.ecommerce.module.management.repository.UserGroupModuleRepository;
import com.ecommerce.module.management.repository.UserGroupRepository;
import com.ecommerce.module.management.rest.model.request.ModuleAssignRequest;
import com.ecommerce.module.management.rest.model.response.ModuleResponse;
import com.ecommerce.module.management.service.api.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {

  final static Logger LOGGER = LoggerFactory.getLogger(ModuleServiceImpl.class);

  @Autowired
  private UserGroupRepository userGroupRepository;
  @Autowired
  private UserGroupModuleRepository userGroupModuleRepository;
  @Autowired
  private ModuleRepository moduleRepository;

  @Override
  public List<ModuleResponse> findModuleByUserId(String userId) throws Exception {
    LOGGER.info("Find module by user id: {}", userId);
    final UserGroup userGroup = Optional.ofNullable(userGroupRepository.findByUsers_userId(userId))
        .orElseThrow(() -> new DataNotFoundException("UserGroup for user " + userId));
    final List<UserGroupModule> userGroupModules =
        userGroupModuleRepository.findByUserGroupIdOrderByOrderNumberAsc(userGroup.getId());
    return Optional.ofNullable(userGroupModules).orElseGet(ArrayList::new).stream()
        .map(this::convertToResponse).collect(Collectors.toList());
  }

  @Override
  public void assignGroupModule(ModuleAssignRequest moduleAssignRequest) throws Exception {
    LOGGER.info("Assign group module: {}", moduleAssignRequest);
    final UserGroup userGroup =
        Optional.ofNullable(userGroupRepository.findOneByGroupId(moduleAssignRequest.getGroupId()))
            .orElseThrow(
                () -> new DataNotFoundException("group id: " + moduleAssignRequest.getGroupId()));
    final Module module =
        Optional.ofNullable(moduleRepository.findOneByModuleId(moduleAssignRequest.getModuleId()))
            .orElseThrow(
                () -> new DataNotFoundException("module id: " + moduleAssignRequest.getModuleId()));
    final UserGroupModule userGroupModule =
        Optional
            .ofNullable(userGroupModuleRepository
                .findByUserGroup_idAndModule_id(userGroup.getId(), module.getId()))
            .orElseGet(UserGroupModule::new);
    userGroupModule.setModule(module);
    userGroupModule.setUserGroup(userGroup);
    userGroupModule.setOrderNumber(moduleAssignRequest.getOrderNumber());
    userGroupModuleRepository.save(userGroupModule);
  }

  private ModuleResponse convertToResponse(UserGroupModule userModule) {
    return new ModuleResponse(userModule.getModule().getName(), userModule.getOrderNumber());
  }

}
