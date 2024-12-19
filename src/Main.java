import java.io.IOException;

import controller.TaskController;


public class Main {
    public static void main(String[] args) throws IOException {
        	TaskController controller = new TaskController();
//        	controller.add("Tomar café");
//        	controller.delete(1);
//        	controller.update(27, "regalo day");
        	controller.list();
        
//        System.out.println(taskList.loadTaskFromJson().getLast());
    }
    }
