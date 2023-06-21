package com.todoapplication.service.impl;

import com.todoapplication.dto.TodoRequest;
import com.todoapplication.dto.TodoResponse;
import com.todoapplication.entity.Todo;
import com.todoapplication.exception.TodoIsNotFound;
import com.todoapplication.repository.TodoRepository;
import com.todoapplication.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private ModelMapper modelMapper;

    static final String TODO_NOT_FOUND="Todo is not found.";

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public TodoResponse addTodoToDb(TodoRequest todoRequest) {

        Todo todoObject = Todo.builder().title(todoRequest.getTitle())
                .creationDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .status(false).build();

        Todo saveTodo = todoRepository.save(todoObject);
        return modelMapper.map(saveTodo, TodoResponse.class);
    }

    @Override
    public TodoResponse getTodoById(long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new TodoIsNotFound(TODO_NOT_FOUND));
        return modelMapper.map(todo, TodoResponse.class);
    }

    @Override
    public List<TodoResponse> getAllTodos() {
        List<Todo> todoRepositoryAll = todoRepository.findAll();
        return todoRepositoryAll.stream().map(e -> modelMapper.map(e, TodoResponse.class)).collect(Collectors.toList());
    }

    @Override
    public TodoResponse updateTodoToDb(long todoId, TodoRequest todoRequest) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new TodoIsNotFound(TODO_NOT_FOUND));
        todo.setStatus(todoRequest.isStatus());
        todo.setTitle(todoRequest.getTitle());
        todo.setUpdatedDate(LocalDateTime.now());
        todoRepository.save(todo);
        return modelMapper.map(todo, TodoResponse.class);
    }

    @Override
    public String deleteTodoToDb(long todoId) {

        todoRepository.findById(todoId).orElseThrow(() -> new TodoIsNotFound(TODO_NOT_FOUND));
        todoRepository.deleteById(todoId);

        return "Todo " + todoId + " is deleted successfully.";

    }
}
