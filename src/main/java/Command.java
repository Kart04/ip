public enum Command {
    bye, list, deadline, todo, event, mark, unmark;

    public static boolean isValidCommand(String input) {
        for(Command cmd : Command.values()){
            if(cmd.name().equalsIgnoreCase(input)){
                return true;
            }
        }
        return false;
    }
}


