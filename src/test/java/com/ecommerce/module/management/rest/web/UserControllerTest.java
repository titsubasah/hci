package com.ecommerce.module.management.rest.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ecommerce.module.management.rest.model.request.GroupAssignRequest;
import com.ecommerce.module.management.rest.model.request.UserRequest;
import com.ecommerce.module.management.rest.model.response.UserResponse;
import com.ecommerce.module.management.service.api.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserControllerTest {
  @InjectMocks
  private UserController userController;
  @Mock
  private UserService userService;
  @Captor
  private ArgumentCaptor<GroupAssignRequest> groupAssignCaptor;
  @Captor
  private ArgumentCaptor<UserRequest> userRequestCaptor;

  private MockMvc mockMvc;
  final ObjectMapper objectMapper = new ObjectMapper();

  private static final String USER_ID = "userId";
  private static final String USER_FULLNAME = "fullname";
  private static final String GROUP_ID = "groupId";

  @Test
  public void assignUserToGroupHappyFlow() throws Exception {
    mockMvc
        .perform(post(ApiPathConstant.API_USER + ApiPathConstant.ASSIGN_USER_TO_GROUP)
            .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(assignGroupJson()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("success", equalTo(Boolean.TRUE)));
    verify(userService).assignGroup(groupAssignCaptor.capture());
    final GroupAssignRequest captured = groupAssignCaptor.getValue();
    Assertions.assertNotNull(captured);
    Assertions.assertEquals(USER_ID, captured.getUserId());
    Assertions.assertEquals(GROUP_ID, captured.getGroupId());
  }

  @Test
  public void storeHappyFlow() throws Exception {
    mockMvc
        .perform(post(ApiPathConstant.API_USER)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(userRequestJson()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("success", equalTo(Boolean.TRUE)));
    verify(userService).save(userRequestCaptor.capture());
    final UserRequest captured = userRequestCaptor.getValue();
    Assertions.assertNotNull(captured);
    Assertions.assertEquals(USER_ID, captured.getUserId());
    Assertions.assertEquals(USER_FULLNAME, captured.getFullName());
  }

  @Test
  public void updateHappyFlow() throws Exception {
    mockMvc
        .perform(put(ApiPathConstant.API_USER)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .param("userId", USER_ID)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(userRequestJson()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("success", equalTo(Boolean.TRUE)));
    verify(userService).update(eq(USER_ID), userRequestCaptor.capture());
    final UserRequest captured = userRequestCaptor.getValue();
    Assertions.assertNotNull(captured);
    Assertions.assertEquals(USER_ID, captured.getUserId());
    Assertions.assertEquals(USER_FULLNAME, captured.getFullName());
  }
  
  @Test
  public void deleteHappyFlow() throws Exception {
    mockMvc
        .perform(delete(ApiPathConstant.API_USER)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .param("userId", USER_ID))
        .andExpect(status().isOk())
        .andExpect(jsonPath("success", equalTo(Boolean.TRUE)));
    verify(userService).delete(USER_ID);    
  }
  
  @Test
  public void findAllHappyFlow() throws Exception {
    when(userService.findAll()).thenReturn(userResponses());
    mockMvc.perform(get(ApiPathConstant.API_USER)
        .accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("success", equalTo(Boolean.TRUE)));
    verify(userService).findAll();
  }

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = standaloneSetup(this.userController).build();
  }

  @AfterEach
  public void tearDown() {}

  private String assignGroupJson() throws Exception {
    return objectMapper.writeValueAsString(new GroupAssignRequest(USER_ID, GROUP_ID));

  }

  private String userRequestJson() throws Exception {
    return objectMapper.writeValueAsString(new UserRequest(USER_ID, USER_FULLNAME));
  }
  
  private List<UserResponse> userResponses() {
    final List<UserResponse> userResponses = new ArrayList<>();
    return userResponses;
  }
}
