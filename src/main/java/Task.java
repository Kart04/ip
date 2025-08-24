public class Task {
    private String description;
    private boolean isDone;

    public Task(String description){
        this.description = description;
        isDone = false;
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
        return isDone? "X" : " ";
    }

    public String toString(){
        return "[" + getStatusIcon() + "]" + getDescription();
    }
}