# Task Tracker CLI

TaskTrackerCLI is a command-line tool for managing tasks.

## Features
>- Add new tasks with a unique ID and store it in JSON format.
>- Update the description of an existing task.
>- Delete tasks by their ID.
>- Mark tasks as in-progress or done.
>- List tasks by their status: to-do, in-progress, or done.

## How to install
To use Task Tracker CLI, clone the repository, after that you must compile the app.
```
git clone https://github.com/JoseLuisPelayo/Task-CLI.git
cd Task-CLI/src
javac Task_cli_app.java
```

## Usage

- **Add a new task**
  - `java Task_cli_app add [description] `
- **Delete task by ID**
  - `java Task_cli_app update [id] [description]`
- **Update task by ID**
   - `java Task_cli_app delete [id] `
- **Mark a task as todo** 
   - `java Task_cli_app mark-todo [id]`
- **Mark a task as in progress**
   - `java Task_cli_app mark-in-progress [id]`
- **Mark a task as done** 
   - `java Task_cli_app mark-done [id]`
- **List all tasks**
   - `java Task_cli_app list`
- **List all Todo tasks**
   - `java Task_cli_app list todo`
-  **List all In-Progress tasks**
   - `java Task_cli_app list in-progress`
-  **List all Done tasks**
   - `java Task_cli_app list done`

## Contributions
If you would like to contribute to TaskTrackerCLI, please fork the repository, 
create a branch for your feature or bug fix, and then submit a pull request.

## URL Project
    [Roadmap project](https://roadmap.sh/projects/task-tracker)



