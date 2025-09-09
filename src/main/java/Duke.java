import Task.*;
import errorcorrection.*;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String bye = "bye";
        Task[] todolist = new Task[100];
        int listnumber = 0;

        System.out.println(line);
        System.out.println("Hello! I'm Speed\nWhat can I do for you?");
        System.out.println(line);

        Scanner in = new Scanner(System.in);

        while (true) {
            if (listnumber == 100) {
                System.out.println("Sorry, overflow of TODO list!");
                break;
            }

            String input = in.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Please enter a command or task description.");
                continue;
            }

            String commandWord = input.split(" ")[0].toLowerCase();

            // Check if input matches one of the known commands (using an enum for robustness)
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
                    for (int i = 0; i < listnumber; i++) {
                        System.out.println((i+1) + ". " + todolist[i]);
                    }
                    System.out.println(line);
                    break;

                case "deadline":
                    listnumber = getDeadlineDetails(input, todolist, listnumber, line);
                    break;

                case "todo":
                    listnumber = getTodoDetails(input, todolist, listnumber, line);
                    break;

                case "event":
                    listnumber = getEventDetails(input, todolist, listnumber, line);
                    break;

                case "mark":
                    handleMarkUnmark(input, todolist, listnumber, line, true);
                    break;

                case "unmark":
                    handleMarkUnmark(input, todolist, listnumber, line, false);
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

    private static int getTodoDetails(String input, Task[] todolist, int listnumber, String line) throws DukeException {
        if (input.length() <= 5) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        todolist[listnumber] = new TODO(description);
        listnumber++;
        System.out.println("Now you have " + listnumber + " tasks in the list");
        System.out.println(line);
        return listnumber;
    }

    private static int getDeadlineDetails(String input, Task[] todolist, int listnumber, String line) throws DukeException {
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
        todolist[listnumber] = new Deadline(description, by);
        listnumber++;
        System.out.println("Now you have " + listnumber + " tasks in the list");
        System.out.println(line);
        return listnumber;
    }

    private static int getEventDetails(String input, Task[] todolist, int listnumber, String line) throws DukeException {
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
        todolist[listnumber] = new Event(description, from, to);
        listnumber++;
        System.out.println("Now you have " + listnumber + " tasks in the list");
        System.out.println(line);
        return listnumber;
    }

    private static void handleMarkUnmark(String input, Task[] todolist, int listnumber, String line, boolean mark) {
        try {
            String arg = mark ? input.substring(4).trim() : input.substring(6).trim();
            int index = Integer.parseInt(arg) - 1;
            if (index < 0 || index >= listnumber) {
                System.out.println(line);
                System.out.println(" OOPS!!! Invalid task number. Please enter a valid index in your list.");
                System.out.println(line);
                return;
            }
            if (mark) {
                todolist[index].markasDone();
            } else {
                todolist[index].markAsNotDone();
            }
            System.out.println(line);
        } catch (NumberFormatException e) {
            System.out.println(line);
            System.out.println(" OOPS!!! The index provided is not a valid number.");
            System.out.println(line);
        }
    }
}
