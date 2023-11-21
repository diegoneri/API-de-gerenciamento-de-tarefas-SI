package com.fatecbs.ToDo.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatecbs.ToDo.model.ToDo;
import com.fatecbs.ToDo.model.ToDo.Status;

@RestController
@RequestMapping("/todos")


public class ToDoController {
    private final List<ToDo> todos = new ArrayList<>();
    private Long nextId = 1L;

    @GetMapping
    public List<ToDo> getAllTodos() {
        return todos;
    }

    @GetMapping("/{id}")
    public ToDo getTodoById(@PathVariable Long id) {
        return todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .orElse(null); 
    }

    @PostMapping
    public ToDo createTodo(@RequestBody ToDo newTodo) {
        newTodo.setId(nextId++);
        todos.add(newTodo);
        return newTodo;
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todos.removeIf(todo -> todo.getId().equals(id));
    }

    @PutMapping("/{id}")
    public ToDo updateTodo(@PathVariable Long id, @RequestBody ToDo updatedTodo) {
        for (int i = 0; i < todos.size(); i++) {
            ToDo todo = todos.get(i);
            if (todo.getId().equals(id)) {
                updatedTodo.setId(id);
                todos.set(i, updatedTodo);
                return updatedTodo;
            }
        }
        return null; 
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ToDo> patchTodoStatus(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        for (ToDo todo : todos) {
            if (todo.getId().equals(id)) {
                String newStatusString = requestBody.get("status");
                if (newStatusString != null) {
                    try {
                        Status newStatus = Status.valueOf(newStatusString.toUpperCase()); 
                        todo.setStatus(newStatus);
                        return ResponseEntity.ok(todo);
                    } catch (IllegalArgumentException e) {
                        return ResponseEntity.badRequest().build();
                    }
                } else {      
                    return ResponseEntity.badRequest().build();
                }
            }
        }
        return ResponseEntity.notFound().build();
    }
}
