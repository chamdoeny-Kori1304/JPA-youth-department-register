package com.kori1304.jpayouthdepartmentregister._common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class ResponseDTO {

  private int status;         // HTTP 응답 상태코드
  private String message;     // HTTP 응답 메시지
  private Object data;        // HTTP 응답 데이터

  public ResponseDTO() {
  }


  public ResponseDTO(HttpStatus status, String message, Object data) {
    this.status = status.value();
    this.message = message;
    this.data = data;
  }

  static public ResponseDTO fromException(HttpStatus httpStatus, Exception e) {
    return new ResponseDTO(httpStatus, e.getMessage(), e.getCause());
  }


}
