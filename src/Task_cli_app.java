import java.io.IOException;

import controller.TaskController;


public class Task_cli_app {
    private static final TaskController taskController = new TaskController();

    public static void main(String[] args) throws IOException {
                String help = """
                - add [description] : Add a new task
                - update [id] [description] : Update a task
                - delete [id] : Delete a task
                - mark-todo [id] : Mark a task as to do
                - mark-in-progress [id] : Mark a task as In progress
                - mark-done [id] : Mark a task as Done
                - list : List all tasks
                - list todo : List all Todo tasks
                - list in-progress : List all In-Progress tasks
                - list done : List all Done tasks
                """;

            if (args.length == 0) {
                System.out.println(help);
                return;
            }

            switch (args[0]) {
                case "add":
                    taskController.add(args[1]);
                    break;
                case "update":
                    taskController.update(Integer.parseInt(args[1]), args[2]);
                    break;
                case "delete":
                    taskController.delete(Integer.parseInt(args[1]));
                    break;
                case "mark-in-progress":
                    taskController.markInProgress(Integer.parseInt(args[1]));
                    break;
                case "mark-done":
                    taskController.markDone(Integer.parseInt(args[1]));
                    break;
                case "list":
                    if (args[1] == null || args[1].isEmpty())
                        taskController.list();
                    else if (args[1].equals("done"))
                        taskController.listDone();
                    else if (args[1].equals("in-progress"))
                        taskController.listInProgress();
                    break;
                default:
                    System.out.println(help);
                    break;
            }

    }
    }
