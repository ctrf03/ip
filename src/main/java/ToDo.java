public class ToDo extends Task {
    public ToDo(String name, boolean isCompleted) {
        super(name, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFile() {
        String completed = this.isCompleted() ? "1" : "0";
        return "T | " + completed + " | " + this.name();
    }

    @Override
    public boolean onDate(Date date) {
        return false;
    }
}
