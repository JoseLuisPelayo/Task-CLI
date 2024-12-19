package controller;

import java.io.IOException;
import java.util.ArrayList;

import javabean.Task;
import repository.TaskRepository;

public class TaskController {
	TaskRepository repository;

	public TaskController() {
		super();
		try {
			this.repository = new TaskRepository();
		} catch (IOException e) {
			e.addSuppressed(e);
		}
	}
	
	public void add(String task) throws IOException {
		this.repository.add(task);
		System.out.println("********************************************");
		System.out.println("	New task added with id: " + this.repository
																.getAll()
																.getLast()
																.getId());
		System.out.println("	Description: " + task);
		System.out.println("********************************************");
	}
	
	public void update(int id, String description) {
		Task task = this.repository.getById(id);
		task.setDescription(description);
		
		if (this.repository.update(task)) {
			System.out.println("********************************************");
			System.out.println("	Task with id: " + id + " updated");
			System.out.println("	Description: " + description);
			System.out.println("********************************************");
		} else {			
			System.out.println("**********************************************");
			System.out.println("	Task with id: " + id + " was not found");
			System.out.println("**********************************************");
		}
	}
	
	public void delete(int id) {
		Task task = this.repository.getById(id);
		if (this.repository.delete(task)) {
			System.out.println("********************************************");
			System.out.println("	Task with id: " + id + " was deleted");
			System.out.println("********************************************");
		} else {
			System.out.println("********************************************");
			System.out.println("	Task with id: " + id + " was not found");
			System.out.println("********************************************");	
		}
	}
	
	public void list() {
		ArrayList<Task> tasks = this.repository.getAll();
		System.out.println("********************************************");
		for (int i = 0; i < tasks.size(); i++) {
			System.out.println("    " + (i + 1) + " -- " + tasks.get(i).getDescription()
								+ " -- " + tasks.get(i).getStatus().getStatus());
		}
		System.out.println("********************************************");
		
	}
	
	
	
	
	
	
}
