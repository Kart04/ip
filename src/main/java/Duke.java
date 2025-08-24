import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String todo = " ";
        String line = "____________________________________________________________";
        String bye = "Bye";
        String[] todolist = new String[100];
        int listnumber = 0;
        System.out.println(line);
        System.out.println("Hello! I'm Speed\n" + "What can I do for you?");
        System.out.println(line);
        Scanner in = new Scanner(System.in);
        do{
            if(listnumber != 100) {
                todo = in.nextLine();
                if(todo.equalsIgnoreCase("Bye")){
                    break;
                }
                if(todo.equalsIgnoreCase("list")){
                    for(int i = 0; i < listnumber; i++){
                        System.out.println((i+1) + ". " + todolist[i]);
                    }
                    System.out.println(line);
                }else {
                    todolist[listnumber] = todo;
                    System.out.println("Added: " + todo);
                    System.out.println(line);
                    listnumber++;
                }
            }else{
                System.out.println("Sorry, overflow of TODO list!");
            }
        }while(!todo.equalsIgnoreCase(bye));

        System.out.println("Bye. Hope to see you again soon!");
    }
}
