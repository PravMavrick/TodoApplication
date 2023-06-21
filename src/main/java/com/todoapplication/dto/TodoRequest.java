package com.todoapplication.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TodoRequest {

    private String title;
    private boolean status;

}
