package model;

import javabean.Task;
import java.io.*;
import java.util.ArrayList;

public class TaskList {
	private File file = new File("./taskList.json");
	private ArrayList<Task> taskList = loadTaskFromJson();

	public TaskList() throws IOException {
		if (file.createNewFile())
			System.out.println("	Welcome to my task-cli-app");
		else
			System.out.println("		Task-cli-app");
	}

	/*
	 * Add Task to list
	 */
	public void addTask(String task) throws IOException {
		this.taskList.add(new Task(
								(!taskList.isEmpty()) ?
										taskList.getLast().getId() + 1 : 1
								, task
								));
		System.out.println("********************************************");
		System.out.println("	New task added with id: " + taskList.getLast().getId());
		System.out.println("	Description: " + task);
		System.out.println("********************************************");
		writeTasksInJson();
	}

	
	/*
	 * Generate Task ArrayList from json file
	 */
	private ArrayList<Task> loadTaskFromJson() {
		String stringList = readJson();
		ArrayList<Task> taskList = new ArrayList<>();

		if (stringList.isBlank())
			return taskList;

		// le quitamos los corchetes y la primera llave y las llaves de cierre
		stringList = stringList.replace(']', ' ').replace('[', ' ').replace('}', ' ').trim().substring(1);

		// separa en un array por la llave de los objetos
		String[] strings = stringList.split("[{]");
		for (String string : strings) {

			// separa el string en atributos por la coma
			String[] taskString = string.split(",");

			// creamos un Task sacando los datos que nos interesan
			Task task = new Task(Integer.parseInt(taskString[0].substring(taskString[0].indexOf(':') + 1).trim()),
					taskString[1].substring(taskString[1].indexOf(':') + 1).replaceAll("[\"]", "").trim(),
					taskString[2].substring(taskString[2].indexOf(':') + 1).replaceAll("[\"]", "").trim(),
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
