package com.mentaljava.mentaljavarestapiproject.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class ResponseDTO {

    private int status;         // 상태코드값(200,404,415, 400, 500)
    private String message;     // 응답메세지
    private Object data;        // 응답데이터

    public ResponseDTO() {
    }

    public ResponseDTO(HttpStatus status, String message, Object data) {
        this.status = status.value();
        this.message = message;
        this.data = data;
    }
}
