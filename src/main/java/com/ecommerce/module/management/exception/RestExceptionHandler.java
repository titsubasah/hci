package com.ecommerce.module.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ecommerce.module.management.rest.model.response.BaseResponse;
import com.ecommerce.module.management.rest.model.response.ErrorResponse;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {IllegalArgumentException.class})
  protected ResponseEntity<BaseResponse> handleIllegalArgument(IllegalArgumentException ex,
      WebRequest request) {
    return toResponseEntitiy(HttpStatus.BAD_REQUEST, new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(), ErrorCode.INVALID_PARAMETER, ex.getMessage()));
  }

  @ExceptionHandler(value = {DataNotFoundException.class})
  protected ResponseEntity<BaseResponse> handleDataNotFound(DataNotFoundException ex,
      WebRequest request) {
    return toResponseEntitiy(HttpStatus.NOT_FOUND,
        new ErrorResponse(HttpStatus.NOT_FOUND.value(), ErrorCode.DATA_NOT_FOUND, ex.getMessage()));
  }

  @ExceptionHandler(value = {DataAlreadyExistException.class})
  protected ResponseEntity<BaseResponse> handleDataAlreadyExist(DataAlreadyExistException ex,
      WebRequest request) {
    return toResponseEntitiy(HttpStatus.FOUND,
        new ErrorResponse(HttpStatus.FOUND.value(), ErrorCode.DATA_ALREADY_EXIST, ex.getMessage()));
  }

  @ExceptionHandler(value = {UnsupportedOperationException.class})
  protected ResponseEntity<BaseResponse> handleUnsupportedOperation(
      UnsupportedOperationException ex, WebRequest request) {
    return toResponseEntitiy(HttpStatus.NOT_IMPLEMENTED, new ErrorResponse(
        HttpStatus.NOT_IMPLEMENTED.value(), ErrorCode.INVALID_STATE, ex.getMessage()));
  }

  @ExceptionHandler(value = {Exception.class})
  protected ResponseEntity<BaseResponse> handleOthers(Exception ex, WebRequest request) {
    return toResponseEntitiy(HttpStatus.BAD_REQUEST, new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(), ErrorCode.INVALID_STATE, ex.getMessage()));
  }

  private ResponseEntity<BaseResponse> toResponseEntitiy(HttpStatus httpStatus,
      BaseResponse errorResponse) {
    return new ResponseEntity<>(errorResponse, httpStatus);
  }

}
