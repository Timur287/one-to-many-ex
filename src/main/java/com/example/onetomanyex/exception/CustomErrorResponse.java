package com.example.onetomanyex.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorResponse {

    private String message;
    private HttpStatus httpStatus;
}
