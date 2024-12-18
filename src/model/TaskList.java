package model;

import javabean.Task;

import java.io.*;
import java.nio.file.Files;

public class TaskList {
    Task[] taskList;
    int count;
    File file = new File("taskList.json");
//    private static final FileReader fileReader = new FileReader("taskList.json");

    public TaskList() throws IOException {
        if (file.createNewFile())
            System.out.println("File created");
        else
            System.out.println("File already exists");
    }

    public void add(Task task) throws IOException {
        String taskList = catchJson();
        String other;
        if (!taskList.isEmpty())
            other = taskList.replace(']', ' ').concat(", " + task.toString() + "]");
        else
            other = "[ " + task.toString() + "]";


        try (Writer writer = new FileWriter(file)) {
            writer.write(other);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(other);
    }


        public String catchJson() {
            try (FileReader fileReader = new FileReader(this.file.getPath())) {
                String jsonTasks = "";

                BufferedReader buffer = new BufferedReader(fileReader);

                String line;


                while ((line = buffer.readLine()) != null) {
                    jsonTasks = jsonTasks + line;
                }
                buffer.close();

                return jsonTasks;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
    }

