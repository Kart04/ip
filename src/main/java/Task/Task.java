package Task;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String toSaveFormat(){
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
    public void markasDone(){
        isDone = true;
    }

    public void markAsNotDone(){
        isDone = false;
    }

    public String getStatusIcon(){
        return isDone ? "X" : " ";
    }

    public String getIsDoneFormat(){
        return isDone ? "1" : "0";
    }

    public String toString(){
        return "[" + getStatusIcon() + "]" + getDescription();
    }
}