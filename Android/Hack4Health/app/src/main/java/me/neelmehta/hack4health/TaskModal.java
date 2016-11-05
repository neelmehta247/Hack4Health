package me.neelmehta.hack4health;

/**
 * Created by rahuldominic on 05/11/16.
 */

public class TaskModal {
    // Task variables
    private long id;
    private String name;
    private long timeInMilliseconds;
    private long timestamp;

    // Constructors

    public TaskModal () {}

    public TaskModal(long id, String name, long timeInMilliseconds, long timestap) {
        this.id = id;
        this.name = name;
        this.timeInMilliseconds = timeInMilliseconds;
        this.timestamp = timestap;
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    public long getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimeInMilliseconds(long timeInMilliseconds) {
        this.timeInMilliseconds = timeInMilliseconds;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
