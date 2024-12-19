package repository;

import javabean.Task;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskRepository {
	private File file = new File("./taskList.json");
	private ArrayList<Task> taskList = loadTaskFromJson();

	public TaskRepository() throws IOException {
		if (file.createNewFile())
			System.out.println("	Welcome to my task-cli-app");
		else
			System.out.println("		Task-cli-app");
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

		// le quitamos los corchetes y la primera llave y las llaves de cierre
		stringList = stringList.replace("]", "")
								.replace("[", "")
								.replace("}", "")
								.trim().substring(1);

		// separa en un array por la llave de los objetos
		String[] strings = stringList.split("[{]");
		for (String string : strings) {
			// separa el string en atributos por la coma
			String[] taskString = string.split(",");

			// creamos un Task sacando los datos que nos interesan
			Task task = new Task(
					Integer.parseInt(taskString[0].substring(taskString[0].indexOf(':') + 1).trim()),
					taskString[1].substring(taskString[1].indexOf(':') + 1).replaceAll("\"", "").trim(),
					TaskStatusRepository.valueOf(taskString[2].substring(taskString[2].indexOf(':') + 1)
																					.replaceAll("[\"]", "")
																					.trim()
																					.toUpperCase()
																					.replace(" ", "_")
																					),
					taskString[3].substring(taskString[3].indexOf(':') + 1).replaceAll("[\"]", "").trim(),
					taskString[4].substring(taskString[4].indexOf(':') + 1).replaceAll("[\"]", "").trim());

			taskList.add(task);
		}
		return taskList;
	}

	private String readJson() {
		try (FileReader fileReader = new FileReader(this.file.getPath())) {
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

		try(Writer writer = new FileWriter(file)) {
			writer.write(stringToJson);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
