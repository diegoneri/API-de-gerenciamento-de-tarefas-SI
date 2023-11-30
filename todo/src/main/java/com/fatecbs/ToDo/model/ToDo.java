package com.fatecbs.ToDo.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="tb_todo")
public class ToDo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="nm_titulo") 
    private String title;
	@Column(name="ds_descricao") 
    private String description;
	@Column(name="nm_status") 
    private Status status;
	@Column(name="dt_tarefa") 
    private LocalDate dueDate;
	@Column(name="nm_prioridade") 
    private Priority priority;
    
	public enum Status {
	    TODO, IN_PROGRESS, DONE
	}

	public enum Priority {
	    LOW, MEDIUM, HIGH
	}
	
	public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
