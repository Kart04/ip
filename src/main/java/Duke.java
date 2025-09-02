import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String todo = " ";
        String line = "____________________________________________________________";
        String bye = "Bye";
        Task[] todolist = new Task[100];
        int listnumber = 0;
        System.out.println(line);
        System.out.println("Hello! I'm Speed\n" + "What can I do for you?");
        System.out.println(line);
        Scanner in = new Scanner(System.in);
        do {
            if (listnumber != 100) {
                todo = in.nextLine();
                todolist[listnumber] = new Task(todo);
                if (todo.equalsIgnoreCase("Bye")) {
                    break;
                } else if (todo.equalsIgnoreCase("list")) {
                    for (int i = 0; i < listnumber; i++) {
                        System.out.println(todolist[i].toString());
                    }
                    System.out.println(line);
                } else if (todo.startsWith("deadline")) {
                    String rest = todo.substring(9);
                    String[] split = rest.split("/by",2);
                    String description = split[0].trim();
                    String by = split.length > 1 ? split[1].trim() : "Never State!";
                    todolist[listnumber] = new Deadline(description, by);
                    listnumber++;
                    System.out.println("Now you have " + listnumber + " tasks in the list");
                    System.out.println(line);
                } else if (todo.startsWith("TODO")) {
                    String rest = todo.substring(5);
                    todolist[listnumber] = new TODO(rest);
                    listnumber++;
                    System.out.println("Now you have " + listnumber + " tasks in the list");
                    System.out.println(line);
                } else if (todo.startsWith("Event")){
                    String rest = todo.substring(6);
                    String[] FromTo = rest.split("/from",2);
                    String description = FromTo[0].trim();
                    String temp = FromTo.length > 1 ? FromTo[1].trim() : "";
                    String[] FromTo1 = temp.split("/to",2);
                    String from = FromTo1.length > 0 ? FromTo1[0].trim() : "";
                    String to = FromTo1.length > 1 ? FromTo1[1].trim() : "";
                    todolist[listnumber] = new Event(description, from, to);
                    listnumber++;
                    System.out.println("Now you have " + listnumber + " tasks in the list");
                    System.out.println(line);


                } else if (todo.startsWith("mark")) {
                    int index = Integer.parseInt(todo.substring(5)) - 1;

                    if (index >= 0 && index < listnumber) {
                        todolist[index].markasDone();
                        System.out.println(line);
                    } else {
                        System.out.println("You have entered an invalid index");
                    }
                } else if (todo.startsWith("unmark")) {
                    int index = Integer.parseInt(todo.substring(7)) - 1;

                    if (index >= 0 && index < listnumber) {
                        todolist[index].markAsNotDone();
                        System.out.println(line);
                    } else {
                        System.out.println("You have entered an invalid index");
                    }
                } else {
                    System.out.println("Added: " + todo);
                    System.out.println(line);
                    listnumber++;
                }
            } else {
                System.out.println("Sorry, overflow of TODO list!");
            }
        } while (!todo.equalsIgnoreCase(bye));

        System.out.println("Bye. Hope to see you again soon!");
    }
}