package com.analyzer;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class FrequencyManager {

    private static final int FIRST_FREQUENCY_INDEX = 0;

    public List<NoteTimeData> convertFrequencies(List<Float> frequencies, double soundDuration) {
        double singleFrequencyTimeLength = getSingleFrequencyTimeLength(soundDuration, frequencies.size());
        List<FrequencyCounter> frequencyCounters = countSimilarFrequencies(frequencies, singleFrequencyTimeLength);
        return null;
    }

    public List<FrequencyCounter> countSimilarFrequencies(List<Float> frequencies, double sftl) {
        List<FrequencyCounter> counters = new LinkedList<>();
        List<Integer> indices = new LinkedList<>();

        int startIndex = 0;
        int endIndex = 0;
        double startTime = 0;
        double endTime = 0;
        for (int i = 0; i < frequencies.size(); i++) {
            float currentFrequency = frequencies.get(i);
            if (i != frequencies.size() - 1 && Math.abs(currentFrequency - frequencies.get(i + 1)) <= Constants.FREQUENCY_ALLOWED_MISSTEP) {
                indices.add(i);
                endIndex++;
            } else if (i != frequencies.size() - 1 && Math.abs(currentFrequency - frequencies.get(i + 1)) > Constants.FREQUENCY_ALLOWED_MISSTEP) {
                endIndex++;
                List<Float> freqs = frequencies.subList(startIndex, endIndex);
                double duration = freqs.size() * sftl;
                endTime = startTime + duration;

                counters.add(new FrequencyCounter(freqs, startIndex, endIndex, getAverageFrequency(freqs), startTime, endTime));
                startIndex = indices.size() + 1;

                indices = new LinkedList<>();
                startTime = endTime;
            } else if (i == frequencies.size() - 1) {
                if (Math.abs(currentFrequency - frequencies.get(i - 1)) <= Constants.FREQUENCY_ALLOWED_MISSTEP) {
                    indices.add(i);
                    List<Float> freqs = frequencies.subList(startIndex, frequencies.size());
                    double duration = freqs.size() * sftl;
                    endTime = startTime + duration;
                    counters.add(new FrequencyCounter(freqs, startIndex, frequencies.size(), getAverageFrequency(freqs), startTime, endTime));
                } else {
                    indices.add(i);
                    List<Float> freqs = frequencies.subList(startIndex, frequencies.size());
                    double duration = sftl;
                    counters.add(new FrequencyCounter(freqs, frequencies.size() - 1, frequencies.size(), getAverageFrequency(freqs), startTime, startTime + duration));
                }
            }
        }

        return counters;
    }

    /**
     * @param sftl - Single Frequency Time Length = Song Duration / all found frequencies
     */
    public List<NoteTimeData> gatherNoteInfo(List<FrequencyCounter> data, double sftl) {


        return null;
    }

    private float getAverageFrequency(List<Float> frequencies) {
        Optional<Float> reduce = frequencies.stream().reduce((e1, e2) -> e1 + e2);
        return reduce.get() / frequencies.size();
    }

    private double getSingleFrequencyTimeLength(double soundDuration, int frequencySize) {
        return soundDuration / frequencySize;
    }
}
