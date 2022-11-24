package com.shopbeafoodh.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler({ MerchantNotFoundException.class, AccountNotFoundException.class,UserNotFoundException.class})
    public ResponseEntity<String> handleExceptionA(Exception e) {
        logger.error(String.valueOf(e));
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnwantedException(Exception e) {
        logger.error(String.valueOf(e));
        return ResponseEntity.status(500).body("Unknow error");
    }
}
