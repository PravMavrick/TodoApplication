package com.todoapplication.service;


import com.todoapplication.dto.TodoRequest;
import com.todoapplication.dto.TodoResponse;

import java.util.List;

public interface TodoService {

    TodoResponse addTodoToDb(TodoRequest todoRequest);

    TodoResponse getTodoById(long todoId);

    List<TodoResponse> getAllTodos();

    TodoResponse updateTodoToDb(long todoId, TodoRequest todoRequest);

    String deleteTodoToDb(long todoId);

}
