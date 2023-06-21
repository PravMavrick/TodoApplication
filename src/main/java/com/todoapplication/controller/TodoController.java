package com.todoapplication.controller;

import com.todoapplication.dto.TodoRequest;
import com.todoapplication.dto.TodoResponse;
import com.todoapplication.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponse> addTodo(@RequestBody TodoRequest todoRequest) {
        return new ResponseEntity<>(todoService.addTodoToDb(todoRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoResponse> fetchTodoById(@PathVariable long todoId) {
        return new ResponseEntity<>(todoService.getTodoById(todoId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> fetchAllTodos() {
        return new ResponseEntity<>(todoService.getAllTodos(), HttpStatus.OK);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<TodoResponse> addTodo(@PathVariable long todoId, @RequestBody TodoRequest todoRequest) {
        return new ResponseEntity<>(todoService.updateTodoToDb(todoId, todoRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodoById(@PathVariable long todoId) {
        return new ResponseEntity<>(todoService.deleteTodoToDb(todoId), HttpStatus.OK);
    }

}
