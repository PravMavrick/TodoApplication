package com.todoapplication.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "todo_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoId;

    @NonNull
    @Column(name="title")
    private String title;

    @Column(name="creation_date")
    private LocalDateTime creationDate;

    @Column(name="update_date")
    private LocalDateTime updatedDate;

    @NotNull
    private boolean status;

}
