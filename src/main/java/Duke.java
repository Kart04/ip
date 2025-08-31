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