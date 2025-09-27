package Task;

/**
 * Represents an event task with a start and end time in the speed application.
 * <p>
 * An evetns extends the {@link Task} class and includes a description, a start time
 * and the end time and the completion status.
 */
public class Event extends Task{
    private String From;
    private String To;

    public Event(String description,  String from, String to, boolean isDone) {
        super(description, isDone);
        this.From = from;
        this.To = to;
    }

    public String getFrom() {
        return From;
    }
    public String getTo() {
        return To;
    }

    @Override
    public String toString(){
        return "[E]" + ("[" + getStatusIcon() + "] " + getDescription()) + "(from:" + From + "|to:" + To + ")" ;
    }

    @Override
    public String toSaveFormat(){
        return "E | " + getIsDoneFormat() + " | " + getDescription() + " | " + From + " | " + To;
    }
}
