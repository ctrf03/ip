package tinkerton.task;

import tinkerton.util.Date;

public abstract class Task {
    private String name;
    private boolean isCompleted;

    public Task(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public void complete() {
        this.isCompleted = true;
    }

    public void uncomplete() {
        this.isCompleted = false;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public String name() {
        return this.name;
    }

    @Override
    public String toString() {
        String marker = this.isCompleted ? "[X]" : "[ ]";
        return marker + " " + name;
    }

    public abstract String toFile();

    public abstract boolean onDate(Date date);
}
