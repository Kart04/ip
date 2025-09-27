/**
 * Represents the set of valid commands supported by the Speed application.
 * <p>
 * Each enum constant corresponds to a user command, such as {code BYE}, {@code LIST}, etc.
 * Use {@link #isVlaidCOmmand(String)} to check if a string match a valid command.
 *
 */
package errorcorrection;

public enum Command {
    BYE, LIST, DEADLINE, TODO, EVENT, MARK, UNMARK,DELETE,SAVE,READ,FIND;

    public static boolean isValidCommand(String input) {
        for (Command cmd : Command.values()) {
            if (cmd.name().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
}


