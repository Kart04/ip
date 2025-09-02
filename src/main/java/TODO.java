public class TODO extends Task {

    public TODO(String description) {
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + ("[" + getStatusIcon() + "] " + getDescription());
    }

}

