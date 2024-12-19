import model.TaskList;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        TaskList taskList = new TaskList();
        taskList.addTask("ir al gym");
        
//        System.out.println(taskList.loadTaskFromJson().getLast());
    }
    }
