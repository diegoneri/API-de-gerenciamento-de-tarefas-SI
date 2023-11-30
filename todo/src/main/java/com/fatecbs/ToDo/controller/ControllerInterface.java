package com.fatecbs.ToDo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ControllerInterface<T> {
   ResponseEntity<List<T>> getAll();
   ResponseEntity<?> findById(Long id);
   ResponseEntity<T> post(T obj);
   ResponseEntity<?> put(Long id, T obj);
   ResponseEntity<?> patch(Long id, T obj);
   ResponseEntity<?> delete(Long id);
}