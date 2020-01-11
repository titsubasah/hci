package com.ecommerce.module.management.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataAlreadyExistException extends Exception {
  private static final Logger LOGGER = LoggerFactory.getLogger(DataAlreadyExistException.class);
  private static final long serialVersionUID = 6751555369440498905L;

  public DataAlreadyExistException(String data) {
    super(String.format("Data Already Exist %s", data));
    LOGGER.error("Data Already Exist {}", data);
  }
}
