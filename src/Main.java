import javabean.Task;
import model.TaskList;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        TaskList taskList = new TaskList();
        taskList.add(new Task(1, "ir al gym"));
        ArrayList<Task> taskList1 = taskList.getTaskList();
    }
    }
