import Task.*;
import errorcorrection.Command;
import errorcorrection.DukeException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String CMD_TODO     = "todo ";
    private static final String CMD_DEADLINE = "deadline ";
    private static final String CMD_EVENT    = "event ";
    private static final String CMD_MARK     = "mark ";
    private static final String CMD_UNMARK   = "unmark ";
    private static final String CMD_DELETE   = "delete ";
    private static final String CMD_SAVE     = "save";
    private static Storage storage = new Storage("./data/duke.txt");
    private static ArrayList<Task> todoList;
    public static void main(String[] args) {
        final String line = "____________________________________________________________";
        final String bye = "bye";
        todoList = new ArrayList<Task>();
        try{
            todoList.addAll(storage.readFromFile());
        }catch (IOException e){
            System.out.println("Could not load saved tasks.");
        }
        System.out.println(line);
        System.out.println("Hello! I'm Speed\nWhat can I do for you?");
        System.out.println(line);

        Scanner in = new Scanner(System.in);

        while (true) {
            String input = in.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Please enter a command or task description.");
                System.out.println(line);
                continue;
            }

            String commandWord = input.split(" ")[0].toLowerCase();

            if (!Command.isValidCommand(commandWord)) {
                System.out.println(line);
                System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :(");
                System.out.println(line);
                continue;
            }

            try {
                switch (commandWord) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;

                case "list":

                    if (!todoList.isEmpty()) {
                        for (int i = 0; i < todoList.size(); i++) {
                            System.out.println((i+1) + ". " + todoList.get(i));
                        }
                        System.out.println(line);
                        break;
                    } else {
                        System.out.println("There are no tasks in this list.");
                        System.out.println(line);
                        break;
                    }

                case "deadline":
                    getDeadlineDetails(input, todoList, line);
                    break;

                case "todo":
                    getTodoDetails(input, todoList, line);
                    break;

                case "event":
                    getEventDetails(input, todoList, line);
                    break;

                case "mark":
                    handleMarkUnmark(input, todoList, line, true);
                    break;

                case "unmark":
                    handleMarkUnmark(input, todoList, line, false);
                    break;

                case "delete":
                    handleDelete(input, todoList, line);
                    break;

                default:
                    System.out.println(line);
                    System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(line);
                }
            } catch (DukeException e) {
                System.out.println(line);
                System.out.println(" " + e.getMessage());
                System.out.println(line);
            }
        }
    }

    private static void getTodoDetails(String input, ArrayList<Task> todoList, String line) throws DukeException {
        if (input.length() <= CMD_TODO.length()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        String description = input.substring(CMD_TODO.length()).trim();
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        todoList.add(new TODO(description, false));
        saveTasks();
        StringBuilder sb = new StringBuilder();
        for(Task t: todoList){
            sb.append(t.toSaveFormat()).append(System.lineSeparator());
        }try{
            storage.writeToFile(todoList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Now you have " + todoList.size() + " tasks in the list");
        System.out.println(line);


    }

    private static void getDeadlineDetails(String input, ArrayList<Task> todoList, String line) throws DukeException {
        if (input.length() <= CMD_DEADLINE.length()) {
            throw new DukeException("OOPS!!! Please type “deadline <description> /by <date>”.");
        }
        String rest = input.substring(CMD_DEADLINE.length()).trim();
        String[] split = rest.split("/by", 2);
        String description = split[0].trim();
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        String by = split.length > 1 ? split[1].trim() : "";
        if (by.isEmpty()) {
            throw new DukeException("OOPS!!! You must provide a /by for this deadline task.");
        }
        todoList.add(new Deadline(description, by, false));
        saveTasks();
        System.out.println("Now you have " + todoList.size() + " tasks in the list");
        System.out.println(line);
    }

    private static void getEventDetails(String input, ArrayList<Task> todoList, String line) throws DukeException {
        if (input.length() <= CMD_EVENT.length()) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
        String rest = input.substring(CMD_EVENT.length()).trim();
        String[] initialInput = rest.split("/from", 2);
        String description = initialInput[0].trim();
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
        if (initialInput.length < 2 || initialInput[1].trim().isEmpty()) {
            throw new DukeException("OOPS!!! You must specify a /from time for the event.");
        }
        String[] dateParts = initialInput[1].trim().split("/to", 2);
        String from = dateParts[0].trim();
        String to = dateParts.length > 1 ? dateParts[1].trim() : "";
        if (from.isEmpty()) {
            throw new DukeException("OOPS!!! The start time (/from) for the event is missing.");
        }
        if (to.isEmpty()) {
            throw new DukeException("OOPS!!! The end time (/to) for the event is missing.");
        }
        todoList.add(new Event(description, from, to, false));
        saveTasks();
        System.out.println("Now you have " + todoList.size() + " tasks in the list");
        System.out.println(line);
    }

    private static void handleMarkUnmark(String input, ArrayList<Task> todoList, String line, boolean mark) throws DukeException {
        try {
            String arg = mark ? input.substring(CMD_MARK.length()).trim() : input.substring(CMD_UNMARK.length()).trim();
            int index = Integer.parseInt(arg) - 1;
            if (index < 0 || index >= todoList.size()) {
                throw new DukeException(" OOPS!!! Invalid task number. Please enter a valid index in your list.");
            }
            if (mark) {
                todoList.get(index).markasDone();
            } else {
                todoList.get(index).markAsNotDone();
            }
            System.out.println(line);
            saveTasks();
        } catch (NumberFormatException e) {
            throw new DukeException(" OOPS!!! The index provided is not a valid number.");
        }
    }

    private static void handleDelete(String input, ArrayList<Task> todoList, String line) throws DukeException {
        if(todoList.isEmpty()) {
            throw new DukeException("There are no task currently to delete. :)");
        }
        if (input.length() < 7) {
            throw new DukeException("Please input a number :)");
        }
        String number = input.substring(7).trim();
        int  index;
        try{
            index = Integer.parseInt(number) - 1;
        if(index >= 0 && index < todoList.size()) {
            Task removedTask = todoList.remove(index);
            System.out.println("Noted. I've removed this task: "+ removedTask);
            System.out.println("Now you have " + todoList.size() + " tasks in the list");
            System.out.println(line);
        } else {
            throw new DukeException("Please make sure you delete the task within the listed tasks!");
        }
            saveTasks();
        }catch(NumberFormatException e){
            System.out.println("OOPS!!! The index provided is not a valid number.");
            System.out.println(line);
        }
    }

    private static void saveTasks() {
        try {
            storage.writeToFile(todoList);
        } catch (IOException e) {
            System.out.println("Failed to save tasks: " + e.getMessage());
        }
    }

}
