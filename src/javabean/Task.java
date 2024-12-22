package javabean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import repository.TaskStatusRepository;

public class Task {
    private int id;
    private String description;
    private TaskStatusRepository status;
    private String createdAt;
    private String updatedAt;
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    public Task() {
    }
    
	public Task(int id, String description) {
		this.id = id;
        this.description = description;
        this.status = TaskStatusRepository.TO_DO;
        this.createdAt = Task.formatter.format(LocalDateTime.now());
        this.updatedAt = null;
    }

    public Task(int id, String description, TaskStatusRepository status, String createdAt, String updatedAt) {
		super();
		this.id = id;
		this.description = description;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatusRepository getStatus() {
        return status;
    }

    public void setStatus(TaskStatusRepository status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = String.valueOf(createdAt);
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
    	return String.format("""
    					 {
    					 "id": 				%d,
    					 "descripcion":		"%s",
    					 "status":			"%s",
    					 "createdAt": 		"%s",
    					 "updatedAt": 		"%s"
    					 }
    					 """, id, description, status, createdAt, updatedAt);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Task task))
        	return false;
        
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, status, createdAt, updatedAt);
    }

    public void changeStatusToInProgress() {
    	this.status = TaskStatusRepository.IN_PROGRESS;
    }
    
    public void changeStatusToDone() {
    	this.status = TaskStatusRepository.DONE;
    }

    public void changeStatusToToDo() {
        this.status = TaskStatusRepository.TO_DO;
    }
}
