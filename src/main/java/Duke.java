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

                String command = todo.split(" ")[0].toLowerCase();

                if (!Command.isValidCommand(command)) {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(line);
                    continue;  // Skip this loop iteration and prompt for next input
                }

                switch (command) {
                case "bye":
                    // Exit the loop
                    break;
                case "list":
                    for (int i = 0; i < listnumber; i++) {
                        System.out.println(todolist[i].toString());
                    }
                    System.out.println(line);
                    break;
                case "deadline":
                    try {
                        listnumber = getDeadlineDetails(todo, todolist, listnumber, line);
                    }catch (DukeException e){
                        System.out.println(e.getMessage());
                        System.out.println(line);
                    }
                    break;
                case "todo":
                    try {
                        listnumber = getTodoDetails(todo, todolist, listnumber, line);
                    }catch (DukeException e){
                        System.out.println(e.getMessage());
                        System.out.println(line);
                    }
                    break;
                case "event":
                    try {
                        listnumber = getEventDetails(todo, todolist, listnumber, line);
                    }catch (DukeException e){
                        System.out.println(e.getMessage());
                        System.out.println(line);
                    }
                    break;
                case "mark":
                    try{
                        int indexMark = Integer.parseInt(todo.substring(5)) - 1;

                        if (indexMark >= 0 && indexMark < listnumber) {
                            todolist[indexMark].markasDone();
                            System.out.println(line);
                        } else {
                            System.out.println("You have entered an invalid index");
                            System.out.println(line);
                        }
                    }catch (NumberFormatException e){
                        System.out.println("OPPS!!!! Please enter a number :)");
                    }
                    break;
                case "unmark":
                    try{
                    int indexUnmark = Integer.parseInt(todo.substring(7)) - 1;

                    if (indexUnmark >= 0 && indexUnmark < listnumber) {
                        todolist[indexUnmark].markAsNotDone();
                        System.out.println(line);
                    } else {
                        System.out.println("You have entered an invalid index");
                        System.out.println(line);
                    }
                    }catch (NumberFormatException e){
                        System.out.println("OPPS!!!! Please enter a number :)");
                    }
                    break;

                }
            } else {
                System.out.println("Sorry, overflow of TODO list!");
            }
        } while (!todo.equalsIgnoreCase(bye));

        System.out.println("Bye. Hope to see you again soon!");
    }

    private static int getDeadlineDetails(String todo, Task[] todolist, int listnumber, String line) throws DukeException {
        if (todo.length() <= 9) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        String rest = todo.substring(9);
        String[] split = rest.split("/by", 2);
        String description = split[0].trim();
        if(description.isEmpty()){
            throw new DukeException("You have not entered in any description");
        }
        String by = split.length > 1 ? split[1].trim() : "";
        if(by.isEmpty() ){
            throw new DukeException("You have not entered when this task has to be completed by!");
        }
        todolist[listnumber] = new Deadline(description, by);
        listnumber++;
        System.out.println("Now you have " + listnumber + " tasks in the list");
        System.out.println(line);
        return listnumber;
    }

    private static int getTodoDetails(String todo, Task[] todolist, int listnumber, String line) throws DukeException{
        String restTodo = todo.substring(5);
        if(todo.length() <= 5){
            throw new DukeException("Oh no there is no discription for the activity TODO is given, please check again");
        }
        todolist[listnumber] = new TODO(restTodo);
        listnumber++;
        System.out.println("Now you have " + listnumber + " tasks in the list");
        System.out.println(line);
        return listnumber;
    }

    private static int getEventDetails(String todo, Task[] todolist, int listnumber, String line) throws DukeException {
        if(todo.length() <= 6){
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        }
        String restEvent = todo.substring(6);
        String[] FromTo = restEvent.split("/from", 2);
        String descriptionEvent = FromTo[0].trim();
        String temp = FromTo.length > 1 ? FromTo[1].trim() : "";
        if(temp.isEmpty()){
            throw new DukeException("You have not entered the date for the event");
        }
        String[] FromTo1 = temp.split("/to", 2);
        String from = FromTo1.length > 0 ? FromTo1[0].trim() : "";
        if(from.isEmpty()){
            throw new DukeException("You have not entered the date for the event from which it happens");
        }
        String to = FromTo1.length > 1 ? FromTo1[1].trim() : "";
        if(to.isEmpty()){
            throw new DukeException("You have not entered the date till when the event happens");
        }
        todolist[listnumber] = new Event(descriptionEvent, from, to);
        listnumber++;
        System.out.println("Now you have " + listnumber + " tasks in the list");
        System.out.println(line);
        return listnumber;
    }
}
