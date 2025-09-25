package Task;

public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;

    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString(){
        return "[D]" + ("[" + getStatusIcon() + "] " + getDescription()) + "(by:" + by + ")";
    }

    @Override
    public String toSaveFormat(){return "D | " + getIsDoneFormat() + " | "+ getDescription() + " | " + by;}
}
