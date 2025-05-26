package com.kori1304.jpayouthdepartmentregister._common.exception;

import com.kori1304.jpayouthdepartmentregister._common.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessRuleViolationException.class)
  public ResponseEntity<ResponseDTO> handleBusinessRuleViolationException(BusinessRuleViolationException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ResponseDTO.fromException(HttpStatus.BAD_REQUEST, e)
        );
  }


  @ExceptionHandler(RepositoryAccessException.class)
  public ResponseEntity<ResponseDTO> handleRepositoryAccessException(BusinessRuleViolationException e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ResponseDTO.fromException(HttpStatus.INTERNAL_SERVER_ERROR, e)
        );
  }

}
