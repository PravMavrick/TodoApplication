package com.todoapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.todoapplication.dto.TodoRequest;
import com.todoapplication.dto.TodoResponse;
import com.todoapplication.entity.Todo;
import com.todoapplication.service.TodoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @MockBean
    private TodoService todoService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;


    TodoRequest todoRequest;

    Todo todo;

    @BeforeEach
    void setUp() {
        todoRequest = new TodoRequest("Do assignment", false);
        todo = new Todo(101, "Start with tasks", LocalDateTime.now(), LocalDateTime.now(), false);

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void addTodoTest() throws Exception {

        TodoResponse todoResponse = modelMapper.map(todo, TodoResponse.class);
        Mockito.when(todoService.addTodoToDb(Mockito.any())).thenReturn(todoResponse);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Objects.requireNonNull(convertObjectToJsonString(todoRequest)))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").exists());

    }


    @Test
    void updateTodoTest() throws Exception {

        long todoId=101;
        TodoResponse todoResponse = modelMapper.map(todo, TodoResponse.class);
        Mockito.when(todoService.updateTodoToDb(Mockito.anyLong(),Mockito.any())).thenReturn(todoResponse);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/todos/"+todoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Objects.requireNonNull(convertObjectToJsonString(todoRequest)))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").exists());

    }

    @Test
    void fetchTodoByIdTest() throws Exception {

        long todoId=101;
        TodoResponse todoResponse = modelMapper.map(todo, TodoResponse.class);
        Mockito.when(todoService.getTodoById(Mockito.anyLong())).thenReturn(todoResponse);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/todos/"+todoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Objects.requireNonNull(convertObjectToJsonString(todoId)))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").exists());
    }

    @Test
    void fetchAllTodosTest() throws Exception {


        List<TodoResponse> todoResponseList = new ArrayList<>();
        todoResponseList.add(modelMapper.map(todo,TodoResponse.class));

        Mockito.when(todoService.getAllTodos()).thenReturn(todoResponseList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }



    @Test
    void deleteTodoByIdTest() throws Exception {

        long todoId=101;
        String deleteResponse="Todo " + todoId + " is deleted successfully.";
        Mockito.when(todoService.deleteTodoToDb(Mockito.anyLong())).thenReturn(deleteResponse);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/todos/"+todoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Objects.requireNonNull(convertObjectToJsonString(todoId)))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> deleteResponse.toString());
    }


    private String convertObjectToJsonString(Object todo) {
        try {
            return objectMapper.writeValueAsString(todo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
 }
}