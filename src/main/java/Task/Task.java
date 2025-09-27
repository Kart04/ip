/**
 * Represents a generic task in the Speed applications.
 * <p>
 * This class serves as the base for more specific task types such as {@link Deadline}, {@link TODO} and {@link Event}.
 * Each task has a description and a completion status.
 * @author Karthik
 * @version 1.0
 * @since 25-08-10
 */
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

    /**
     * Marks the task as done. Equivalent to {@link #setDone(boolean) setdone(true)}.
     */
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