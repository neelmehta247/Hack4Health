package me.neelmehta.hack4health;

/**
 * Created by rahuldominic on 05/11/16.
 */

public class TaskModal {
    // Task variables
    private String name;
    private long timeInMilliseconds;
    private long timestamp;

    // Constructor
    public TaskModal(String name, long timeInMilliseconds) {
        this.name = name;
        this.timeInMilliseconds = timeInMilliseconds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    public void setTimeInMilliseconds(long timeInMilliseconds) {
        this.timeInMilliseconds = timeInMilliseconds;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
