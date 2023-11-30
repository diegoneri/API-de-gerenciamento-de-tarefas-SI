package com.fatecbs.ToDo.service;

import java.util.List;

public interface ServiceInterface<T> {
	T create(T obj);

	T findById(Long id);

	List<T> findAll();

	boolean update(Long id, T obj);

	boolean delete(Long id);
	
	boolean patch(Long id, T obj);
}