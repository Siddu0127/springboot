package com.example.utile;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class CustomResponse {
    private HttpStatus status;
    private String message;
    private Object data;

    public CustomResponse(HttpStatus status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }


}
