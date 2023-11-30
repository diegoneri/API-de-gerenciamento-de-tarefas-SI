package com.fatecbs.ToDo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatecbs.ToDo.model.ToDo;
import com.fatecbs.ToDo.service.ToDoService;

@RestController
@RequestMapping("/todos")

public class ToDoController implements ControllerInterface<ToDo> {
    @Autowired
    private ToDoService service;

    @GetMapping
    public ResponseEntity<List<ToDo>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ToDo _ToDo = service.findById(id);
        if (_ToDo != null)
            return ResponseEntity.ok(_ToDo);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ToDo> post(@RequestBody ToDo toDo) {
        service.create(toDo);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(toDo.getId())
                .toUri();
        return ResponseEntity.created(location).body(toDo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody ToDo updatedTodo) {
        if (service.update(id, updatedTodo)) {
            return ResponseEntity.ok(updatedTodo);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ToDo> patch(@PathVariable Long id, @RequestBody ToDo updates) {
        if (service.patch(id, updates)) {
            return ResponseEntity.ok(service.findById(id));
        }
        return ResponseEntity.notFound().build();
    }

}
