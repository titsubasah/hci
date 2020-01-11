package com.ecommerce.module.management.rest.model.response;

public class SuccessValueResponse<T> extends SuccessResponse {

  private static final long serialVersionUID = -2078880039857538645L;

  private T value;

  public SuccessValueResponse(String message) {
    super(message);
    value = null;
  }

  public SuccessValueResponse(T value) {
    super(null);
    this.value = value;
  }

  public SuccessValueResponse(T value, String message) {
    super(message);
    this.value = value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public T getValue() {
    return this.value;
  }
}
