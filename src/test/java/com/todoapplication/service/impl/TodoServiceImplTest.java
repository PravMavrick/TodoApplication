package com.todoapplication.service.impl;

import com.todoapplication.dto.TodoRequest;
import com.todoapplication.dto.TodoResponse;
import com.todoapplication.entity.Todo;
import com.todoapplication.exception.TodoIsNotFound;
import com.todoapplication.repository.TodoRepository;
import com.todoapplication.service.TodoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class TodoServiceImplTest {
    @MockBean
    private TodoRepository todoRepository;

    @Autowired
    private TodoService todoService;

    @Autowired
    private ModelMapper modelMapper;

    Todo todo;

    TodoRequest todoRequest;

    @BeforeEach
    void setUp() {
        todoRequest = new TodoRequest("Do Assignment",false);
        todo = new Todo(101,"Start with tasks",LocalDateTime.now(),LocalDateTime.now(),false);
    }

    @AfterEach
    void tearDown() {

    }


    @Test
    void createTodoTest() {

        Mockito.when(todoRepository.save(Mockito.any())).thenReturn(todo);
        TodoResponse todoResponse = todoService.addTodoToDb(modelMapper.map(todo, TodoRequest.class));

        Assertions.assertNotNull(todoResponse);
        Assertions.assertEquals(todo.getTitle(),todoResponse.getTitle());
    }

    @Test
    void updateTodoTest(){

        long todoId = 101;
        TodoRequest todoRequest= TodoRequest.builder().title("Test-> update todo").status(true).build();
        Mockito.when(todoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(todo));
        TodoResponse todoResponse = todoService.updateTodoToDb(todoId, todoRequest);

        Assertions.assertNotNull(todoResponse);
        Assertions.assertEquals(todo.getTitle(),todoResponse.getTitle());

    }


    @Test
    void deleteTodoTest(){

        long todoId = 101;
        Mockito.when(todoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(todo));
        String response = todoService.deleteTodoToDb(todoId);

        Assertions.assertNotNull(response);
        Assertions.assertEquals("Todo " + todoId + " is deleted successfully.",response);

    }


    @Test
    void getAllTodoListTest(){

        Todo todo1 = new Todo(101,"Focus on one task at a time",LocalDateTime.now(),LocalDateTime.now(),false);
        Todo todo2 = new Todo(102,"Divide work in small tasks",LocalDateTime.now(),LocalDateTime.now(),false);
        Todo todo3 = new Todo(103,"Complete task on time",LocalDateTime.now(),LocalDateTime.now(),false);

        List<Todo> todoList = new ArrayList<>();
        todoList.add(todo1);
        todoList.add(todo2);
        todoList.add(todo3);

        Mockito.when(todoRepository.findAll((Sort) Mockito.any())).thenReturn(todoList);
        List<TodoResponse> allTodos = todoService.getAllTodos();

        Assertions.assertEquals(todoList.size(),todoList.size());
    }

}