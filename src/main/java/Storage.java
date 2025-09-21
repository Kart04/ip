import Task.Task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(ArrayList<Task> todolist) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : todolist) {
                writer.write(task.toString());
                writer.newLine();
            }
            System.out.println("Tasks saved successfully to " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving tasks:(");
        }
    }
}
