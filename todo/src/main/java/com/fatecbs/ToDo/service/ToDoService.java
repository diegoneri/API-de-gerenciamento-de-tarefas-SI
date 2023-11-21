package com.fatecbs.ToDo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fatecbs.ToDo.model.ToDo;
import com.fatecbs.ToDo.model.ToDo.Status;

@Service
public class ToDoService {
    private final List<ToDo> todos = new ArrayList<>();
    private Long nextId = 1L;

    public List<ToDo> getAllTodos() {
        return todos;
    }

    public Optional<ToDo> getTodoById(Long id) {
        return todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst();
    }

    public ToDo createTodo(ToDo newTodo) {
        newTodo.setId(nextId++);
        todos.add(newTodo);
        return newTodo;
    }

    public boolean deleteTodoById(Long id) {
        return todos.removeIf(todo -> todo.getId().equals(id));
    }

    public Optional<ToDo> updateTodoById(Long id, ToDo updatedTodo) {
        for (int i = 0; i < todos.size(); i++) {
            ToDo todo = todos.get(i);
            if (todo.getId().equals(id)) {
                updatedTodo.setId(id);
                todos.set(i, updatedTodo);
                return Optional.of(updatedTodo);
            }
        }
        return Optional.empty();
    }

    public Optional<ToDo> patchTodoStatus(Long id, Status newStatus) {
        for (ToDo todo : todos) {
            if (todo.getId().equals(id)) {
                todo.setStatus(newStatus);
                return Optional.of(todo);
            }
        }
        return Optional.empty();
    }
}
