import Task.*;
import errorcorrection.*;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String bye = "bye";

        ArrayList<Task> todolist = new ArrayList<Task>();

        System.out.println(line);
        System.out.println("Hello! I'm Speed\nWhat can I do for you?");
        System.out.println(line);

        Scanner in = new Scanner(System.in);

        while (true) {

            String input = in.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Please enter a command or task description.");
                continue;
            }

            String commandWord = input.split(" ")[0].toLowerCase();

            // Check if input matches one of the known commands
            if (!Command.isValidCommand(commandWord)) {
                System.out.println(line);
                System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(line);
                continue;
            }

            try {
                switch (commandWord) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;

                case "list":
                    for (int i = 0; i < todolist.size(); i++) {
                        System.out.println((i+1) + ". " + todolist.get(i));
                    }
                    System.out.println(line);
                    break;

                case "deadline":
                    getDeadlineDetails(input, todolist, line);
                    break;

                case "todo":
                    getTodoDetails(input, todolist, line);
                    break;

                case "event":
                    getEventDetails(input, todolist, line);
                    break;

                case "mark":
                    handleMarkUnmark(input, todolist, line, true);
                    break;

                case "unmark":
                    handleMarkUnmark(input, todolist, line, false);
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

    private static void getTodoDetails(String input, ArrayList<Task> todolist, String line) throws DukeException {
        if (input.length() <= 5) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        todolist.add(new TODO(description));
        System.out.println("Now you have " + todolist.size() + " tasks in the list");
        System.out.println(line);

    }
    //adding comment for commit
    private static void getDeadlineDetails(String input, ArrayList<Task> todolist, String line) throws DukeException {
        if (input.length() <= 9) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        String rest = input.substring(9);
        String[] split = rest.split("/by", 2);
        String description = split[0].trim();
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        String by = split.length > 1 ? split[1].trim() : "";
        if (by.isEmpty()) {
            throw new DukeException("OOPS!!! You must provide a /by for this deadline task.");
        }
        todolist.add(new Deadline(description, by));
        System.out.println("Now you have " + todolist.size() + " tasks in the list");
        System.out.println(line);
    }

    private static void getEventDetails(String input, ArrayList<Task> todolist, String line) throws DukeException {
        if (input.length() <= 6) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
        String rest = input.substring(6);
        String[] fromTo = rest.split("/from", 2);
        String description = fromTo[0].trim();
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
        if (fromTo.length < 2 || fromTo[1].trim().isEmpty()) {
            throw new DukeException("OOPS!!! You must specify a /from time for the event.");
        }
        String[] dateParts = fromTo[1].trim().split("/to", 2);
        String from = dateParts[0].trim();
        String to = dateParts.length > 1 ? dateParts[1].trim() : "";
        if (from.isEmpty()) {
            throw new DukeException("OOPS!!! The start time (/from) for the event is missing.");
        }
        if (to.isEmpty()) {
            throw new DukeException("OOPS!!! The end time (/to) for the event is missing.");
        }
        todolist.add(new Event(description, from, to));
        System.out.println("Now you have " + todolist.size() + " tasks in the list");
        System.out.println(line);
    }

    private static void handleMarkUnmark(String input, ArrayList<Task> todolist, String line, boolean mark) {
        try {
            String arg = mark ? input.substring(4).trim() : input.substring(6).trim();
            int index = Integer.parseInt(arg) - 1;
            if (index < 0 || index >= todolist.size()) {
                System.out.println(line);
                System.out.println(" OOPS!!! Invalid task number. Please enter a valid index in your list.");
                System.out.println(line);
                return;
            }
            if (mark) {
                todolist.get(index).markasDone();
            } else {
                todolist.get(index).markAsNotDone();
            }
            System.out.println(line);
        } catch (NumberFormatException e) {
            System.out.println(line);
            System.out.println(" OOPS!!! The index provided is not a valid number.");
            System.out.println(line);
        }
    }
}
