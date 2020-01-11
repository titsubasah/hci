package com.ecommerce.module.management.service.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ecommerce.module.management.entity.Module;
import com.ecommerce.module.management.entity.UserGroup;
import com.ecommerce.module.management.entity.UserGroupModule;
import com.ecommerce.module.management.exception.DataNotFoundException;
import com.ecommerce.module.management.repository.ModuleRepository;
import com.ecommerce.module.management.repository.UserGroupModuleRepository;
import com.ecommerce.module.management.repository.UserGroupRepository;
import com.ecommerce.module.management.rest.model.request.ModuleAssignRequest;
import com.ecommerce.module.management.rest.model.response.ModuleResponse;

@Execution(ExecutionMode.SAME_THREAD)
public class ModuleServiceImplTest {

  @InjectMocks
  private ModuleServiceImpl moduleServiceImpl;

  @Mock
  private UserGroupRepository userGroupRepository;
  @Mock
  private UserGroupModuleRepository userGroupModuleRepository;
  @Mock
  private ModuleRepository moduleRepository;
  
  @Captor
  private ArgumentCaptor<UserGroupModule> userGroupModuleArgumentCaptor;

  private static final String USER_ID = "userId";
  private static final String USER_GROUP_ID = "userGroupId";
  private static final String MODULE_ID = "moduleId";
  private static final String MODULE_NAME = "ModuleName";
  private static final int ORDER_NUMBER = 1;
  
  @Test
  public void findModuleByUserIdHappyFlow() throws Exception {
    final UserGroup userGroup = this.userGroup();
    final Module module = this.module();
    final UserGroupModule userGroupModule = this.userGroupModule(module, userGroup);
    when(userGroupRepository.findByUsers_userId(USER_ID)).thenReturn(userGroup);
    when(userGroupModuleRepository.findByUserGroupIdOrderByOrderNumberAsc(userGroup.getId()))
        .thenReturn(Arrays.asList(userGroupModule));
    List<ModuleResponse> result = this.moduleServiceImpl.findModuleByUserId(USER_ID);
    Assertions.assertNotNull(result);
    Assertions.assertFalse(result.isEmpty());
    verify(userGroupRepository).findByUsers_userId(USER_ID);
    verify(userGroupModuleRepository).findByUserGroupIdOrderByOrderNumberAsc(userGroup.getId());
  }

  @Test
  public void findModuleByUserIdUserGroupNotFound() throws Exception {
    when(userGroupRepository.findByUsers_userId(USER_ID)).thenReturn(null);
    Assertions.assertThrows(DataNotFoundException.class, () -> {
      this.moduleServiceImpl.findModuleByUserId(USER_ID);
    });
    verify(userGroupRepository).findByUsers_userId(USER_ID);
  }

  @Test
  public void assignGroupModuleHappyFlow() throws Exception {
    final UserGroup userGroup = this.userGroup();
    final Module module = this.module();
    final UserGroupModule userGroupModule = this.userGroupModule(module, userGroup);
    when(userGroupRepository.findOneByGroupId(USER_GROUP_ID)).thenReturn(userGroup);
    when(moduleRepository.findOneByModuleId(MODULE_ID)).thenReturn(module);
    when(userGroupModuleRepository.findByUserGroup_idAndModule_id(userGroup.getGroupId(),
        module.getId())).thenReturn(userGroupModule);
    when(userGroupModuleRepository.save(userGroupModule)).thenReturn(userGroupModule);
    moduleServiceImpl.assignGroupModule(new ModuleAssignRequest(MODULE_ID, USER_GROUP_ID, ORDER_NUMBER));
    verify(userGroupRepository).findOneByGroupId(USER_GROUP_ID);
    verify(moduleRepository).findOneByModuleId(MODULE_ID);
    verify(userGroupModuleRepository).findByUserGroup_idAndModule_id(userGroup.getId(),
        module.getId());
    verify(userGroupModuleRepository).save(userGroupModuleArgumentCaptor.capture());
    final UserGroupModule userGroupModuleCaptured = userGroupModuleArgumentCaptor.getValue();
    Assertions.assertNotNull(userGroupModuleCaptured);
    Assertions.assertEquals(ORDER_NUMBER, userGroupModuleCaptured.getOrderNumber());
    Assertions.assertEquals(MODULE_ID, userGroupModuleCaptured.getModule().getModuleId());
    Assertions.assertEquals(USER_GROUP_ID, userGroupModuleCaptured.getUserGroup().getGroupId());
  }
  
  @Test
  public void assignGroupModuleModuleIsNotFound() throws Exception {        
    final UserGroup userGroup = this.userGroup();    
    when(userGroupRepository.findOneByGroupId(USER_GROUP_ID)).thenReturn(userGroup);
    when(moduleRepository.findOneByModuleId(MODULE_ID)).thenReturn(null);
    Assertions.assertThrows(DataNotFoundException.class, () -> {
      moduleServiceImpl.assignGroupModule(new ModuleAssignRequest(MODULE_ID, USER_GROUP_ID, ORDER_NUMBER));
    });    
    verify(userGroupRepository).findOneByGroupId(USER_GROUP_ID);
    verify(moduleRepository).findOneByModuleId(MODULE_ID);    
  }
  
  
  @Test
  public void assignGroupModuleGroupIdIsNotFound() throws Exception {        
    when(userGroupRepository.findOneByGroupId(USER_GROUP_ID)).thenReturn(null);    
    Assertions.assertThrows(DataNotFoundException.class, () -> {
      moduleServiceImpl.assignGroupModule(new ModuleAssignRequest(MODULE_ID, USER_GROUP_ID, ORDER_NUMBER));
    });    
    verify(userGroupRepository).findOneByGroupId(USER_GROUP_ID);    
  }
  
  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @AfterEach
  public void tearDown() {}

  private UserGroup userGroup() {
    final UserGroup userGroup = new UserGroup();
    userGroup.setId("id");
    userGroup.setGroupId(USER_GROUP_ID);
    return userGroup;
  }

  private Module module() {
    final Module module = new Module();
    module.setId("id");
    module.setModuleId(MODULE_ID);
    module.setName(MODULE_NAME);
    return module;
  }

  private UserGroupModule userGroupModule(Module module, UserGroup userGroup) {
    final UserGroupModule userGroupModule = new UserGroupModule();
    userGroupModule.setModule(module);
    userGroupModule.setUserGroup(userGroup);
    userGroupModule.setOrderNumber(ORDER_NUMBER);
    return userGroupModule;
  }
}
