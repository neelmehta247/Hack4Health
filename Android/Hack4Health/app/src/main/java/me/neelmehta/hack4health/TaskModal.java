package me.neelmehta.hack4health;

/**
 * Created by rahuldominic on 05/11/16.
 */

public class TaskModal {
    // Task variables
    private String name;
    private long timeInMilliseconds;
    private int hours;
    private int minutes;
    private long timestamp;

    // Constructor
    public TaskModal(String name, long timeInMilliseconds, int hours, int minutes) {
        this.name = name;
        this.timeInMilliseconds = timeInMilliseconds;
        this.hours = hours;
        this.minutes = minutes;
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

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
