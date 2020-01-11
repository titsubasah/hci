package com.ecommerce.module.management.service.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

import com.ecommerce.module.management.entity.User;
import com.ecommerce.module.management.entity.UserGroup;
import com.ecommerce.module.management.exception.DataAlreadyExistException;
import com.ecommerce.module.management.exception.DataNotFoundException;
import com.ecommerce.module.management.repository.UserGroupRepository;
import com.ecommerce.module.management.repository.UserRepository;
import com.ecommerce.module.management.rest.model.request.GroupAssignRequest;
import com.ecommerce.module.management.rest.model.request.UserRequest;
import com.ecommerce.module.management.rest.model.response.UserResponse;

public class UserServiceImplTest {

  @InjectMocks
  private UserServiceImpl userServiceImpl;
  @Mock
  private UserRepository userRepository;
  @Mock
  private UserGroupRepository userGroupRepository;
  @Captor
  private ArgumentCaptor<User> userArgumentCaptor;

  private static final String USER_ID = "userId";
  private static final String USER_FULL_NAME = "fullname";
  private static final String USER_GROUP_ID = "userGroupId";
  private static final String USER_GROUP_NAME = "userGroupId";

  @Test
  public void saveHappyFlow() throws Exception {
    when(userRepository.findByUserId(USER_ID)).thenReturn(null);
    userServiceImpl.save(new UserRequest(USER_ID, USER_FULL_NAME));
    verify(userRepository).findByUserId(USER_ID);
    verify(userRepository).save(userArgumentCaptor.capture());
    final User captured = userArgumentCaptor.getValue();
    Assertions.assertNotNull(captured);
    Assertions.assertEquals(USER_ID, captured.getUserId());
    Assertions.assertEquals(USER_FULL_NAME, captured.getFullName());
  }

  @Test
  public void saveHappyFlowUserIdIsExist() throws Exception {
    when(userRepository.findByUserId(USER_ID)).thenReturn(new User());
    Assertions.assertThrows(DataAlreadyExistException.class, () -> {
      userServiceImpl.save(new UserRequest(USER_ID, USER_FULL_NAME));
    });
    verify(userRepository).findByUserId(USER_ID);
  }

  @Test
  public void assignGroupHappyFlow() throws Exception {
    final UserGroup userGroup = userGroup();
    final User user = new User("id", USER_ID, USER_FULL_NAME, null);
    when(userGroupRepository.findOneByGroupId(USER_GROUP_ID)).thenReturn(userGroup);
    when(userRepository.findByUserId(USER_ID)).thenReturn(user);
    Assertions.assertNull(user.getUserGroup());
    userServiceImpl.assignGroup(new GroupAssignRequest(USER_ID, USER_GROUP_ID));
    verify(userGroupRepository).findOneByGroupId(USER_GROUP_ID);
    verify(userRepository).findByUserId(USER_ID);
    verify(userRepository).save(userArgumentCaptor.capture());
    final User captured = userArgumentCaptor.getValue();
    Assertions.assertNotNull(captured);
    Assertions.assertEquals(USER_ID, captured.getUserId());
    Assertions.assertEquals(USER_FULL_NAME, captured.getFullName());
    Assertions.assertNotNull(captured.getUserGroup());
    Assertions.assertEquals(USER_GROUP_ID, captured.getUserGroup().getGroupId());
  }

  @Test
  public void assignGroupUserIdNotFound() throws Exception {
    final UserGroup userGroup = userGroup();
    when(userGroupRepository.findOneByGroupId(USER_GROUP_ID)).thenReturn(userGroup);
    when(userRepository.findByUserId(USER_ID)).thenReturn(null);
    Assertions.assertThrows(DataNotFoundException.class, () -> {
      userServiceImpl.assignGroup(new GroupAssignRequest(USER_ID, USER_GROUP_ID));
    });
    verify(userGroupRepository).findOneByGroupId(USER_GROUP_ID);
    verify(userRepository).findByUserId(USER_ID);
  }

  @Test
  public void assignGroupUserGroupNotFound() throws Exception {
    when(userGroupRepository.findOneByGroupId(USER_GROUP_ID)).thenReturn(null);
    Assertions.assertThrows(DataNotFoundException.class, () -> {
      userServiceImpl.assignGroup(new GroupAssignRequest(USER_ID, USER_GROUP_ID));
    });
    verify(userGroupRepository).findOneByGroupId(USER_GROUP_ID);
  }

  @Test
  public void updateTest() throws Exception {
    Assertions.assertThrows(UnsupportedOperationException.class, () -> {
      userServiceImpl.update(USER_ID, new UserRequest());
    });
  }

  @Test
  public void deleteTest() throws Exception {
    Assertions.assertThrows(UnsupportedOperationException.class, () -> {
      userServiceImpl.delete(USER_ID);
    });
  }

  @Test
  public void findAllHappyFlow() throws Exception {
    when(userRepository.findAll()).thenReturn(users());
    List<UserResponse> userResponses = userServiceImpl.findAll();
    Assertions.assertNotNull(userResponses);
    Assertions.assertEquals(1, userResponses.size());
    verify(userRepository).findAll();
  }

  private UserGroup userGroup() {
    final UserGroup userGroup = new UserGroup();
    userGroup.setId("id");
    userGroup.setGroupId(USER_GROUP_ID);
    userGroup.setName(USER_GROUP_NAME);
    return userGroup;
  }
  
  private List<User> users() {
    final List<User> users = new ArrayList<User>();
    final User user1 = new User();
    user1.setUserId(USER_ID);
    user1.setFullName(USER_FULL_NAME);
    users.add(user1);
    return users;
  }

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @AfterEach
  public void tearDown() {}

}
