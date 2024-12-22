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
                - mark-in-progress [id] : Mark a task as in progress
                - mark-done [id] : Mark a task as Done
                - list : List all tasks
                - list todo : List all Todo tasks
                - list in-progress : List all In-Progress tasks
                - list done : List all Done tasks
                """;

            if (args.length == 0) {
                System.out.println("**************************************************");
                System.out.println(help);
                System.out.println("**************************************************");
                return;
            }


            switch (args[0]) {
                case "add":
                    if (args.length < 2) {
                        System.out.println("********************************************");
                        System.out.println("- add [description] : Add a new task");
                        System.out.println("********************************************");
                        return;
                    }

                    String task = "";
                    for (int i = 1; i < args.length; i++) {
                        if (i == 1) {
                            task = args[i];
                        } else {
                            task += " " + args[i];
                        }
                    }
                    taskController.add(task);
                    break;

                case "update":
                    if (args.length < 3) {
                        System.out.println("**********************************************");
                        System.out.println("- update [id] [description] : Update a task");
                        System.out.println("**********************************************");
                        return;
                    }

                    String taskToUpdate = "";
                    for (int i = 2; i < args.length; i++) {
                        if (i == 2) {
                            taskToUpdate = args[i];
                        } else {
                            taskToUpdate += " " + args[i];
                        }
                    }

                    taskController.update(Integer.parseInt(args[1]), taskToUpdate);
                    break;

                case "delete":
                    if (args.length < 2) {
                        System.out.println("********************************************");
                        System.out.println("- delete [id] : Delete a task");
                        System.out.println("********************************************");
                        return;
                    }

                    taskController.delete(Integer.parseInt(args[1]));
                    break;

                case "mark-todo":
                    if (args.length < 2) {
                        System.out.println("******************************************************");
                        System.out.println("- mark-todo [id] : Mark a task as to do");
                        System.out.println("******************************************************");
                        return;
                    }

                    taskController.markToDo(Integer.parseInt(args[1]));
                    break;

                case "mark-in-progress":
                    if (args.length < 2) {
                        System.out.println("******************************************************");
                        System.out.println("- mark-in-progress [id] : Mark a task as in progress");
                        System.out.println("******************************************************");
                        return;
                    }

                    taskController.markInProgress(Integer.parseInt(args[1]));
                    break;

                case "mark-done":
                    if (args.length < 2) {
                        System.out.println("********************************************");
                        System.out.println("- mark-done [id] : Mark a task as done");
                        System.out.println("********************************************");
                        return;
                    }

                    taskController.markDone(Integer.parseInt(args[1]));
                    break;
                case "list":
                    if (args.length < 2)
                        taskController.list();
                    else if (args[1].equals("todo"))
                        taskController.listToDo();
                    else if (args[1].equals("done"))
                        taskController.listDone();
                    else if (args[1].equals("in-progress"))
                        taskController.listInProgress();
                    else {
                        System.out.println("********************************************");
                        System.out.println("""
                                - list : List all tasks- list todo : List all Todo tasks
                                - list in-progress : List all In-Progress tasks
                                - list done : List all Done tasks
                                """);
                        System.out.println("********************************************");
                    }
                    break;
                default:
                    System.out.println(help);
                    break;
            }

    }
    }
