package com.ecommerce.module.management.service.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ecommerce.module.management.entity.UserGroup;
import com.ecommerce.module.management.exception.DataAlreadyExistException;
import com.ecommerce.module.management.exception.DataNotFoundException;
import com.ecommerce.module.management.repository.UserGroupRepository;
import com.ecommerce.module.management.rest.model.request.UserGroupRequest;
import com.ecommerce.module.management.rest.model.response.UserGroupResponse;

public class UserGroupServiceImplTest {

  @InjectMocks
  private UserGroupServiceImpl userGroupServiceImpl;

  @Mock
  private UserGroupRepository userGroupRepository;

  @Captor
  private ArgumentCaptor<UserGroup> userGroupArgumentCaptor;

  private static final String USER_GROUP_ID = "userGroupId";
  private static final String USER_GROUP_NAME = "userGroupId";

  @Test
  public void saveHappyFlow() throws Exception {
    when(userGroupRepository.findOneByGroupId(USER_GROUP_ID)).thenReturn(null);
    userGroupServiceImpl.save(new UserGroupRequest(USER_GROUP_ID, USER_GROUP_NAME));
    verify(userGroupRepository).findOneByGroupId(USER_GROUP_ID);
    verify(userGroupRepository).save(userGroupArgumentCaptor.capture());
    final UserGroup captured = userGroupArgumentCaptor.getValue();
    Assertions.assertNotNull(captured);
    Assertions.assertEquals(USER_GROUP_ID, captured.getGroupId());
    Assertions.assertEquals(USER_GROUP_NAME, captured.getName());
  }

  @Test
  public void saveHappyFlowGroupIdIsExist() throws Exception {
    when(userGroupRepository.findOneByGroupId(USER_GROUP_ID)).thenReturn(new UserGroup());
    Assertions.assertThrows(DataAlreadyExistException.class, () -> {
      userGroupServiceImpl.save(new UserGroupRequest(USER_GROUP_ID, USER_GROUP_NAME));
    });
    verify(userGroupRepository).findOneByGroupId(USER_GROUP_ID);
  }

  @Test
  public void updateHappyFlow() throws Exception {
    when(userGroupRepository.findOneByGroupId(USER_GROUP_ID)).thenReturn(new UserGroup());
    userGroupServiceImpl.update(USER_GROUP_ID,
        new UserGroupRequest(USER_GROUP_ID, USER_GROUP_NAME));
    verify(userGroupRepository).findOneByGroupId(USER_GROUP_ID);
    verify(userGroupRepository).save(userGroupArgumentCaptor.capture());
    final UserGroup captured = userGroupArgumentCaptor.getValue();
    Assertions.assertNotNull(captured);
    Assertions.assertEquals(USER_GROUP_ID, captured.getGroupId());
    Assertions.assertEquals(USER_GROUP_NAME, captured.getName());
  }

  @Test
  public void updateHappyFlowGroupIdNotFound() throws Exception {
    when(userGroupRepository.findOneByGroupId(USER_GROUP_ID)).thenReturn(null);
    Assertions.assertThrows(DataNotFoundException.class, () -> {
      userGroupServiceImpl.update(USER_GROUP_ID,
          new UserGroupRequest(USER_GROUP_ID, USER_GROUP_NAME));
    });
    verify(userGroupRepository).findOneByGroupId(USER_GROUP_ID);
  }
  
  @Test
  public void deleteTest() throws Exception {
    Assertions.assertThrows(UnsupportedOperationException.class, () -> {
      userGroupServiceImpl.delete(USER_GROUP_ID);
    });
  }
  
  @Test
  public void findAllHappyFlow() throws Exception{
    final UserGroup userGroup = userGroup();
    when(userGroupRepository.findAll()).thenReturn(Arrays.asList(userGroup));
    List<UserGroupResponse> userGroupResponses = userGroupServiceImpl.findAll();
    Assertions.assertNotNull(userGroupResponses);
    Assertions.assertFalse(userGroupResponses.isEmpty());
    Assertions.assertEquals(1, userGroupResponses.size());
    Assertions.assertEquals(USER_GROUP_ID, userGroupResponses.get(0).getGroupId());
    Assertions.assertEquals(USER_GROUP_NAME, userGroupResponses.get(0).getGroupName());
    verify(userGroupRepository).findAll();
  }

  private UserGroup userGroup() {
    final UserGroup userGroup = new UserGroup();
    userGroup.setId("id");
    userGroup.setGroupId(USER_GROUP_ID);
    userGroup.setName(USER_GROUP_NAME);
    return userGroup;
  }

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @AfterEach
  public void tearDown() {}
}
