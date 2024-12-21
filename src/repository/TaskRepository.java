package repository;

import javabean.Task;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskRepository {
	private final static Path FILE_PATH = Path.of("taskList.json");
	private ArrayList<Task> taskList;

	public TaskRepository() throws IOException {
		if (!Files.exists(FILE_PATH)) {
			Files.createFile(FILE_PATH);
			System.out.println("********************************************");
			System.out.println("	Welcome to my task-cli-app");
		}
		else
			System.out.println("********************************************");
			System.out.println("		Task-cli-app");
		this.taskList = loadTaskFromJson();
	}

	public void add(String task) throws IOException {
		this.taskList.add(new Task(
								(!taskList.isEmpty()) ?
										taskList.getLast().getId() + 1 : 1
								, task
								));
		writeTasksInJson();
	}

	public boolean update(Task task) {
		int i = this.taskList.indexOf(task);
		
		if( i != -1 && this.taskList.get(i).getId() == task.getId() ) {		
			this.taskList.remove(i);
			this.taskList.add(i, task);
			this.taskList.get(i).setUpdatedAt(Task.formatter.format(LocalDateTime.now()));
			
			writeTasksInJson();
			return true;
		} 	
		return false;
	}
	
	public boolean delete(Task task) {
		int i = this.taskList.indexOf(task);

		if ( i != -1 ) {	
			this.taskList.remove(i);
			writeTasksInJson();
			return true;
		}
		return false;
	}
	
	public ArrayList<Task> getAll() {
		return this.taskList;
	}
	
 	public Task getById(int id) {
		for (int i = 0; i < this.taskList.size(); i++) {
			if( this.taskList.get(i).getId() == id ) {
				return this.taskList.get(i);
			}
		}
			return new Task();
	}

	private ArrayList<Task> loadTaskFromJson() {
		String stringList = readJson();
		ArrayList<Task> taskList = new ArrayList<>();

		if (stringList.isBlank())
			return taskList;

		stringList = stringList.replace("]", "")
								.replace("[", "")
								.replace("}", "")
								.trim().substring(1);

		String[] strings = stringList.split("[{]");
		for (String string : strings) {
			string = string.replaceAll("\"", "");
			String[] taskString = string.split(",");

			Task task = new Task(
					Integer.parseInt(taskString[0].substring(taskString[0].indexOf(':') + 1).trim()),
					taskString[1].substring(taskString[1].indexOf(':') + 1).trim(),
					TaskStatusRepository.valueOf(taskString[2].substring(taskString[2].indexOf(':') + 1)
																					.trim()
																					.toUpperCase()
																					.replace(" ", "_")
																					),
					taskString[3].substring(taskString[3].indexOf(':') + 1).trim(),
					taskString[4].substring(taskString[4].indexOf(':') + 1).trim()
					);

			taskList.add(task);
		}
		return taskList;
	}

	private String readJson() {
		try (FileReader fileReader = new FileReader(FILE_PATH.toFile())) {
			String jsonTasks = "";

			BufferedReader buffer = new BufferedReader(fileReader);

			String line;

			while ((line = buffer.readLine()) != null) {
				jsonTasks += "\n" + line;
			}
			buffer.close();

			return jsonTasks;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void writeTasksInJson() {
		String stringToJson = "[\n";
        for (int i = 0; i < taskList.size(); i++) {            	
        	if (i != taskList.size() -1)
           		stringToJson += taskList.get(i)
           								.toString()
           								.stripTrailing() + ",\n";
            }
            
        stringToJson += taskList.getLast() +"]";

		try(Writer writer = new FileWriter(FILE_PATH.toFile())) {
			writer.write(stringToJson);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
