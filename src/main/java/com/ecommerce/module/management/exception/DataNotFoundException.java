package com.ecommerce.module.management.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataNotFoundException extends Exception {

  private static final Logger LOGGER = LoggerFactory.getLogger(DataNotFoundException.class);
  private static final long serialVersionUID = 6751555369440498905L;

  public DataNotFoundException(String data) {
    super(String.format("Data Not Found: %s", data));
    LOGGER.error("Data not found {}", data);
  }

}
