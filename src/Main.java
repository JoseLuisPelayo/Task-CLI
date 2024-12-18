import javabean.Task;
import model.TaskList;

import java.io.File;
import java.io.IOException;

public class Main {
    private static final File file = new File("taskList.json");

    public static void main(String[] args) throws IOException {
        TaskList taskList = new TaskList();
        taskList.add(new Task("ir al gym"));
        }
    }
