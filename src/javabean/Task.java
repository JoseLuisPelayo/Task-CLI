package javabean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Task {
    private int id;
    private String description;
    private String status;
    private String createdAt;
    private String updatedAt;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    public Task() {
    }
    
    public Task(int id, String description, String status, String createdAt, String updatedAt) {
		super();
		this.id = id;
		this.description = description;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Task(int id, String description) {
		this.id = id;
        this.description = description;
        this.status = "not done";
        this.createdAt = formatter.format(LocalDateTime.now());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
    					 "updatedAt": 		%s
    					 }
    					 """, id, description, status, createdAt, updatedAt);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Task task)) return false;
        return id == task.id && Objects.equals(description, task.description) &&
                Objects.equals(status, task.status) &&
                Objects.equals(createdAt, task.createdAt) &&
                Objects.equals(updatedAt, task.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, status, createdAt, updatedAt);
    }
}
