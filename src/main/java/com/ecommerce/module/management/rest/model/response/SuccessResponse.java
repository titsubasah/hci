package com.ecommerce.module.management.rest.model.response;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SuccessResponse extends BaseResponse {

  private static final long serialVersionUID = -5150001136448437360L;

  public SuccessResponse(String message) {
    super(HttpStatus.OK.value(), Boolean.TRUE, null, message);
  }
}
