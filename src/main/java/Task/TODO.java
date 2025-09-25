package Task;

public class TODO extends Task {

    public TODO(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString(){
        return "[T]" + ("[" + getStatusIcon() + "] " + getDescription());
    }

    @Override
    public String toSaveFormat(){return "T | " + getIsDoneFormat() + " | "+ getDescription();}
}

