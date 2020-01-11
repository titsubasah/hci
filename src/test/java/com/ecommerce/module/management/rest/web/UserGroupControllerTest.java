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

import com.ecommerce.module.management.rest.model.request.UserGroupRequest;
import com.ecommerce.module.management.rest.model.response.UserGroupResponse;
import com.ecommerce.module.management.service.api.UserGroupService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserGroupControllerTest {

  @InjectMocks
  private UserGroupController userGroupController;
  @Mock
  private UserGroupService userGroupService;
  @Captor
  private ArgumentCaptor<UserGroupRequest> userGroupArgumentCaptor;

  private MockMvc mockMvc;
  final ObjectMapper objectMapper = new ObjectMapper();

  private static final String GROUP_ID = "groupId";
  private static final String GROUP_NAME = "group name";

  @Test
  public void storeHappyFlow() throws Exception {
    mockMvc
    .perform(post(ApiPathConstant.API_USER_GROUP)
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(userGroupJson()))
    .andExpect(status().isOk())
    .andExpect(jsonPath("success", equalTo(Boolean.TRUE)));
    verify(userGroupService).save(userGroupArgumentCaptor.capture());
    final UserGroupRequest captured = userGroupArgumentCaptor.getValue();
    Assertions.assertNotNull(captured);
    Assertions.assertEquals(GROUP_ID, captured.getGroupId());
    Assertions.assertEquals(GROUP_NAME, captured.getName());
  }
  
  @Test
  public void updateHappyFlow() throws Exception {
    mockMvc
        .perform(put(ApiPathConstant.API_USER_GROUP)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .param("groupId", GROUP_ID)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(userGroupJson()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("success", equalTo(Boolean.TRUE)));
    verify(userGroupService).update(eq(GROUP_ID), userGroupArgumentCaptor.capture());
    final UserGroupRequest captured = userGroupArgumentCaptor.getValue();
    Assertions.assertNotNull(captured);
    Assertions.assertEquals(GROUP_ID, captured.getGroupId());
    Assertions.assertEquals(GROUP_NAME, captured.getName());
  }
  
  @Test
  public void deleteHappyFlow() throws Exception {
    mockMvc
        .perform(delete(ApiPathConstant.API_USER_GROUP)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .param("groupId", GROUP_ID))
        .andExpect(status().isOk())
        .andExpect(jsonPath("success", equalTo(Boolean.TRUE)));
    verify(userGroupService).delete(GROUP_ID);    
  }
  
  @Test
  public void findAllHappyFlow() throws Exception {
    when(userGroupService.findAll()).thenReturn(userGroupResponse());
    mockMvc.perform(get(ApiPathConstant.API_USER_GROUP)
        .accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("success", equalTo(Boolean.TRUE)));
    verify(userGroupService).findAll();
  }
  
  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = standaloneSetup(this.userGroupController).build();
  }

  @AfterEach
  public void tearDown() {}

  private String userGroupJson() throws Exception {
    return objectMapper.writeValueAsString(new UserGroupRequest(GROUP_ID, GROUP_NAME));
  }
  
  private List<UserGroupResponse> userGroupResponse() {
    List<UserGroupResponse> userGroupResponses = new ArrayList<>();
    return userGroupResponses;
  } 
}
