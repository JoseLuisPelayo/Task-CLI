package model;

import javabean.Task;
import java.io.*;
import java.util.ArrayList;

public class TaskList {
    Task[] taskList;
    int count;
    File file = new File("./taskList.json");

    public TaskList() throws IOException {
        if (file.createNewFile())
            System.out.println("File created");
        else
            System.out.println("File already exists");
    }

    
    //Añade una tarea al json
    public void add(Task task) throws IOException {
        String taskList = catchJson();
        String other;
        if (!taskList.isEmpty())
            other = taskList.replace(']', ' ')
            				.stripTrailing()
            				.concat(",\n" + task.toString() + "]");
        else
            other = "[\n" + task.toString() + "]";

        try (Writer writer = new FileWriter(file)) {
            writer.write(other);
            System.out.println("New task added");
            System.out.println(task);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
/*
 * Creamos un Arraylist de Task con
 * los objetos del json
 */
    public ArrayList<Task> getTaskList() {
        String stringList = catchJson();
        ArrayList<Task> taskList = new ArrayList<>();

        //le quitamos los corchetes y la primera llave y las llaves de cierre
        stringList = stringList.replace(']', ' ')
                    .replace('[', ' ')
                    .replace('}', ' ')
                    .trim().substring(1);
        
        //separa en un array por la llave de los objetos
        String[] strings = stringList.split("[{]");
        for (String string : strings) {
            
        	//separa el string en atributos por la coma
        	String[] taskString = string.split(",");
            
        	//creamos un Task sacando los datos que nos interesan
        	Task task = new Task(
			                    Integer.parseInt(
			                    				taskString[0]
			                    				.substring(taskString[0].indexOf(':') + 1)
			                    				.trim()
			                    				),
			                    taskString[1].substring(taskString[1].indexOf(':') + 1)
			                    				.replaceAll("[\"]","")
			                    				.trim(),
			                    taskString[2].substring(taskString[2].indexOf(':') + 1)
			                    				.replaceAll("[\"]","")
			                    				.trim(),
			                    taskString[3].substring(taskString[3].indexOf(':') + 1)
			                    				.replaceAll("[\"]","")
			                    				.trim(),
			                   	taskString[4].substring(taskString[4].indexOf(':') + 1)
			                    				.replaceAll("[\"]","")
			                    				.trim()
            					);

            taskList.add(task);
        }

        return taskList;
    }

    public String catchJson() {
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
    }

