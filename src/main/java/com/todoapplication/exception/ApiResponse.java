package com.todoapplication.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ApiResponse {

    private String message;
    private HttpStatus status;

}
