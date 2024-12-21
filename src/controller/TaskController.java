package controller;

import java.io.IOException;
import java.util.ArrayList;

import javabean.Task;
import repository.TaskRepository;
import repository.TaskStatusRepository;

public class TaskController {
	TaskRepository repository;

	public TaskController() {
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
	
	public void markInProgress(int id) {
		Task task = this.repository.getById(id);

		if (task.getStatus().getStatus().equals(TaskStatusRepository.IN_PROGRESS.getStatus())){
			System.out.println("********************************************");
			System.out.println("	Task with id: " + id + " is already in progress");
			System.out.println("********************************************");
			return;
		}

		task.changeStatusToInProgress();

		if (this.repository.update(task)) {
			System.out.println("********************************************");
			System.out.println("	Task with id: " + id + " updated");
			System.out.println("	Status: " + task.getStatus().getStatus());
			System.out.println("********************************************");
		} else {			
			System.out.println("**********************************************");
			System.out.println("	Task with id: " + id + " was not found");
			System.out.println("**********************************************");
		}
	}

	public void markDone(int id) {
		Task task = this.repository.getById(id);

		if (task.getStatus().getStatus().equals(TaskStatusRepository.DONE.getStatus())){
			System.out.println("********************************************");
			System.out.println("	Task with id: " + id + " is already done");
			System.out.println("********************************************");
			return;
		}

		task.changeStatusToDone();

		if (this.repository.update(task)) {
			System.out.println("********************************************");
			System.out.println("	Task with id: " + id + " updated");
			System.out.println("	Status: " + task.getStatus().getStatus());
			System.out.println("********************************************");
		} else {
			System.out.println("**********************************************");
			System.out.println("	Task with id: " + id + " was not found");
			System.out.println("**********************************************");
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

	public void listToDo() {
		ArrayList<Task> tasks = this.repository.getAll();
		System.out.println("********************************************");
        for (Task task : tasks) {
            if (TaskStatusRepository.TO_DO.getStatus().equals(task.getStatus().getStatus())) {
                System.out.println("    id: " + task.getId() + " -- "
                        + task.getDescription()
                        + " -- " + task.getStatus().getStatus());
            }
        }
		System.out.println("********************************************");
	}

	public void listDone() {
		ArrayList<Task> tasks = this.repository.getAll();
		System.out.println("********************************************");
        for (Task task : tasks) {
            if (TaskStatusRepository.DONE.getStatus().equals(task.getStatus().getStatus()))
                System.out.println("    " + task.getId() + " -- " + task.getDescription()
                        + " -- " + task.getStatus().getStatus());
        }
			System.out.println("********************************************");
	}

	public void listInProgress() {
		ArrayList<Task> tasks = this.repository.getAll();
		System.out.println("********************************************");
        for (Task task : tasks) {
            if (TaskStatusRepository.IN_PROGRESS.getStatus().equals(task.getStatus().getStatus()))
                System.out.println("    " + task.getId() + " -- " + task.getDescription()
                        + " -- " + task.getStatus().getStatus());
        }
		System.out.println("********************************************");
	}

}
