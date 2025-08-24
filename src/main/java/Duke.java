import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String reply = " ";
        String line = "____________________________________________________________";
        String bye = "Bye";
        System.out.println(line);
        System.out.println("Hello! I'm Speed\n" + "What can I do for you?");
        System.out.println(line);
        Scanner in = new Scanner(System.in);
        while(!reply.equalsIgnoreCase(bye)){
            reply = in.nextLine();
            System.out.println(reply);
            System.out.println(line);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
