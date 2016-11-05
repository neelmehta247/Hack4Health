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

    // Constructor
    public TaskModal(String name, long timeInMilliseconds, int hours, int minutes) {
        this.name = name;
        this.timeInMilliseconds = timeInMilliseconds;
        this.hours = hours;
        this.minutes = minutes;
    }

    // Getters
    public String getName() {
        return name;
    }

    // Setters
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
}
