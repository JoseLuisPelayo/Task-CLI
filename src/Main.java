import java.io.IOException;

import controller.TaskController;


public class Main {
    public static void main(String[] args) throws IOException {
        	TaskController controller = new TaskController();
        	controller.add("Tomar café");
//        	controller.delete(1);
        	controller.update(4, "regalo day");
        	controller.markInProgress(3);
			controller.markInProgress(4);
//			controller.markDone(2);
        	controller.listToDo();
        
//        System.out.println(taskList.loadTaskFromJson().getLast());
    }
    }
