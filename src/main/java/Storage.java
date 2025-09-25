import Task.Task;
import Task.Deadline;
import Task.TODO;
import Task.Event;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdir();
        }
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void writeToFile(List<Task> tasks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task t : tasks) {
                writer.write(t.toSaveFormat());
                writer.newLine();
            }
        }
    }


    public ArrayList<Task> readFromFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File doesn't exist");
            return tasks;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    String[] parts = line.split("\\s*\\|\\s*");
                    if (parts.length < 3) continue;
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String Descriptions = parts[2];
                    switch (type) {
                    case "T":
                        tasks.add(new TODO(Descriptions, isDone));
                        break;
                    case "D":
                        tasks.add(new Deadline(Descriptions, parts[3], isDone));
                        break;
                    case "E":
                        boolean isDone1 = parts[1].equals("1");
                        String Descriptions1 = parts[2];
                        String from = parts[3];
                        String to = parts[4];
                        tasks.add(new Event(Descriptions1, from, to, isDone1));
                    }
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
            }
        }


        return tasks;
    }
}