package com.analyzer;

import java.util.List;

public class FrequencyCounter {
    private List<Float> frequencies;
    //index from all frequencies when this particular frequency starts
    private int startIndex;
    //index from all frequencies when this particular frequency ends
    private int endIndex;

    //average frequency from similar frequencies
    private float frequency;

    //start time of current frequency in millis
    private double startTime;

    //end time of current frequency in millis
    private double endTime;

    public List<Float> getFrequencies() {
        return frequencies;
    }

    public FrequencyCounter(List<Float> frequencies, int startIndex, int endIndex, float frequency) {
        this.frequencies = frequencies;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.frequency = frequency;
    }

    public FrequencyCounter(List<Float> frequencies, int startIndex, int endIndex, float frequency, double startTime, double endTime) {
        this.frequencies = frequencies;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.frequency = frequency;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setFrequencies(List<Float> frequencies) {
        this.frequencies = frequencies;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }
}
