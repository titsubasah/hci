package com.ecommerce.module.management.rest.model.response;

import com.ecommerce.module.management.exception.ErrorCode;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ErrorResponse extends BaseResponse {
  private static final long serialVersionUID = -3384563844713039328L;

  public ErrorResponse(int httpStatus, ErrorCode errorCode, String message) {
    super(httpStatus, Boolean.FALSE, errorCode, message);
  }

}
