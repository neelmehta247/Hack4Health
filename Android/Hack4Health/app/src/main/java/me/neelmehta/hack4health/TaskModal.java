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
    public TaskModal (String _name, long _timeInMilliseconds, int _hours, int _minutes) {
        name = _name;
        timeInMilliseconds = _timeInMilliseconds;
        hours = _hours;
        minutes = _minutes;
    }

    // Getters
    public String getName() {
        return name;
    }

    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setTimeInMilliseconds(long timeInMilliseconds) {
        this.timeInMilliseconds = timeInMilliseconds;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
