package com.fatecbs.ToDo.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatecbs.ToDo.model.ToDo;
import com.fatecbs.ToDo.repository.ToDoRepository;

@Service
public class ToDoService implements ServiceInterface<ToDo> {
	@Autowired
	private ToDoRepository repository;
//    private final List<ToDo> todos = new ArrayList<>();
	 public ToDoService() {}
	  @Override
	  public ToDo create(ToDo obj) {
	     repository.save(obj);
	     return obj;
	  }

    @Override
    public List<ToDo> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
         }
         return false;
      }


	@Override
    public boolean update(Long id, ToDo updatedTodo) {
        if (repository.existsById(id)) {
            updatedTodo.setId(id);
            repository.save(updatedTodo);
            return true;
         }
         return false;
    }
	
	@Override
    public boolean patch(Long id, ToDo updates) {
        Optional<ToDo> optionalToDo = repository.findById(id);

        if (optionalToDo.isPresent()) {
            ToDo toDoToUpdate = optionalToDo.get();

            if (updates.getTitle() != null) {
                toDoToUpdate.setTitle(updates.getTitle());
            }

            if (updates.getDescription() != null) {
                toDoToUpdate.setDescription(updates.getDescription());
            }

            if (updates.getStatus() != null) {
                toDoToUpdate.setStatus(updates.getStatus());
            }

            if (updates.getDueDate() != null) {
                toDoToUpdate.setDueDate(updates.getDueDate());
            }

            if (updates.getPriority() != null) {
                toDoToUpdate.setPriority(updates.getPriority());
            }

            repository.save(toDoToUpdate);
            return true;
        }

        return false;
    }

    @Override
    public ToDo findById(Long id) {
      Optional<ToDo> obj = repository.findById(id);
      return obj.orElse(null);
    }
}
