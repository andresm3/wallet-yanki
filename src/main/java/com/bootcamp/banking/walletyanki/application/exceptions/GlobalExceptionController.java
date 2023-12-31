package com.bootcamp.banking.walletyanki.application.exceptions;

import com.bootcamp.banking.walletyanki.application.exceptions.customs.CustomInformationException;
import com.bootcamp.banking.walletyanki.application.exceptions.customs.CustomNotFoundException;
import com.bootcamp.banking.walletyanki.application.exceptions.customs.CustomResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * Global exception controller.
 */
@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionController {
  /**
   * Returns a JSON object with an error message.
   *
   * @return CustomInformationException
   */
  @ExceptionHandler
  public ResponseEntity<CustomResult> handle(CustomInformationException ex) {
    CustomResult customResult = new CustomResult();
    customResult.setMessage(ex.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(customResult);
  }

  /**
   * Returns a JSON object with an error message.
   *
   * @return CustomNotFoundException
   */
  @ExceptionHandler
  public ResponseEntity<CustomResult> handle(CustomNotFoundException ex) {
    CustomResult customResult = new CustomResult();
    customResult.setMessage(ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customResult);
  }
}
