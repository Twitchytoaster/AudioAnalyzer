package com.analyzer;

public class NoteTimeData {
    private String note;
    private double startTime;
    private double duration;
    private double endTime;

    public NoteTimeData(String note, double startTime, double duration, double endTime) {
        this.note = note;
        this.startTime = startTime;
        this.duration = duration;
        this.endTime = endTime;
    }

    public NoteTimeData() {
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }
}
