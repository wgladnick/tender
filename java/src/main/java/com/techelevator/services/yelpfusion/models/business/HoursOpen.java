package com.techelevator.services.yelpfusion.models.business;

public class HoursOpen {

    private boolean is_overnight;
    private int start;
    private int end;
    private int day;

    public HoursOpen() {
    }

    public boolean isIs_overnight() {
        return is_overnight;
    }

    public void setIs_overnight(boolean is_overnight) {
        this.is_overnight = is_overnight;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
