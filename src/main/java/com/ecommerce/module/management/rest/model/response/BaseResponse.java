package com.ecommerce.module.management.rest.model.response;

import java.io.Serializable;

import com.ecommerce.module.management.exception.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse implements Serializable {
  private static final long serialVersionUID = -5143261941300786008L;
  private int httpStatus;
  private boolean success;
  private ErrorCode code;
  private String message;
}
