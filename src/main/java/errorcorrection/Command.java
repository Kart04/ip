package errorcorrection;

public enum Command {
    BYE, LIST, DEADLINE, TODO, EVENT, MARK, UNMARK;

    public static boolean isValidCommand(String input) {
        for (Command cmd : Command.values()) {
            if (cmd.name().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
}


