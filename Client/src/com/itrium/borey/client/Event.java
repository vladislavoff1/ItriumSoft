package com.itrium.borey.client;

/**
 * @author Vladislav Romanov
 */

public class Event {
    
    public String msg;
    public String time;
    public int priority;

    public Event() {
    	
    }
    
    public Event(String msg) {
        this.msg = msg;
        this.priority = 0;
    }
    
    public Event(String msg, String time) {
        this.msg = msg;
        this.time = time;
        this.priority = 0;
    }

    public Event(String msg, String time, int priority) {
        this.msg = msg;
        this.time = time;
        this.priority = priority;
    }
    
}