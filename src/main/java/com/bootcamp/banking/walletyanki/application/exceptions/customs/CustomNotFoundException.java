package com.bootcamp.banking.walletyanki.application.exceptions.customs;

/**
 * Object that returns a message in case an exception occurs.
 */
public class CustomNotFoundException extends RuntimeException {
  public CustomNotFoundException(String message) {
    super(message);
  }
}
