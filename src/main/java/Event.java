public class Event extends Task{
    private String From;
    private String To;

    public Event(String description,  String from, String to) {
        super(description);
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
}
