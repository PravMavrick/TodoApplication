package com.todoapplication.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TodoResponse {
    private long todoId;
    private String title;
    private LocalDateTime creationDate;
    private LocalDateTime updatedDate;
    private boolean status;
}
