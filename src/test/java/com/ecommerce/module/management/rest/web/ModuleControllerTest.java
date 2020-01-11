package com.ecommerce.module.management.rest.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.ecommerce.module.management.rest.model.request.ModuleAssignRequest;
import com.ecommerce.module.management.rest.model.response.ModuleResponse;
import com.ecommerce.module.management.service.api.ModuleService;
import com.fasterxml.jackson.databind.ObjectMapper;;

public class ModuleControllerTest {

  @InjectMocks
  private ModuleController moduleController;
  @Mock
  private ModuleService moduleService;
  @Captor
  private ArgumentCaptor<ModuleAssignRequest> moduleAssignRequestCaptor;

  private MockMvc mockMvc;

  private static final String USER_ID = "userId";
  private static final String GROUP_ID = "groupId";
  private static final String MODULE_ID = "moduleId";
  private static final String MODULE_NAME = "ModuleName";
  private static final int MODULE_ORDER = 1;

  @Test
  public void findModuleByUserIdHappyFlow() throws Exception {
    when(moduleService.findModuleByUserId(USER_ID)).thenReturn(moduleResponses());
    mockMvc
        .perform(get(ApiPathConstant.API_MODULE + ApiPathConstant.USER)
            .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
            .param("UserID", USER_ID))
        .andExpect(status().isOk()).andExpect(jsonPath("modules", notNullValue()));
    verify(moduleService).findModuleByUserId(USER_ID);
  }

  @Test
  public void assignGroupModuleHappyFlow() throws Exception {
    mockMvc
        .perform(post(ApiPathConstant.API_MODULE + ApiPathConstant.ASSIGN_GROUP_TO_MODULE)
            .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(jsonBody()))
        .andExpect(status().isOk()).andExpect(jsonPath("success", equalTo(Boolean.TRUE)));
    verify(moduleService).assignGroupModule(moduleAssignRequestCaptor.capture());
    final ModuleAssignRequest captured = moduleAssignRequestCaptor.getValue();
    Assertions.assertNotNull(captured);
    Assertions.assertEquals(MODULE_ID, captured.getModuleId());
    Assertions.assertEquals(GROUP_ID, captured.getGroupId());
    Assertions.assertEquals(MODULE_ORDER, captured.getOrderNumber());
  }

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = standaloneSetup(this.moduleController).build();
  }

  @AfterEach
  public void tearDown() {}

  private List<ModuleResponse> moduleResponses() {
    final List<ModuleResponse> moduleResponses = new ArrayList<>();
    final ModuleResponse moduleResponse = new ModuleResponse(MODULE_NAME, MODULE_ORDER);
    moduleResponses.add(moduleResponse);
    return moduleResponses;
  }

  private String jsonBody() throws Exception {
    final ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper
        .writeValueAsString(new ModuleAssignRequest(MODULE_ID, GROUP_ID, MODULE_ORDER));

  }
}
