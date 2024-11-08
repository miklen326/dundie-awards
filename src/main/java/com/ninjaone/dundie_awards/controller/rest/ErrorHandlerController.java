package com.ninjaone.dundie_awards.controller.rest;

import com.ninjaone.dundie_awards.exception.EmployeeNotFoundException;
import com.ninjaone.dundie_awards.exception.OrgNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.ninjaone.dundie_awards.controller.rest")
public class ErrorHandlerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlerController.class);

    @ExceptionHandler({EmployeeNotFoundException.class, OrgNotFoundException.class})
    public ResponseEntity<?> handleItemNotFoundException(Exception ex) {
        LOGGER.info("Item Not Found Error", ex);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleValidationExceptions(BindException ex) {
        LOGGER.info("Validation Error", ex);
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception ex) {
        LOGGER.error("Internal Server Error", ex);
        return ResponseEntity.internalServerError().build();
    }
}
