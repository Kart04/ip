package Task;

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
