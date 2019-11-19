package com.analyzer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrequencyManagerTest {
    private FrequencyManager frequencyManager = new FrequencyManager();

    private List<Float> frequencyList = Arrays.asList(127F, 128F, 129F, 130F, 131F, 164F, 165F, 166F, 167F, 168F);

    @Test
    public void countSimilarFrequenciesTest() {
        List<FrequencyCounter> frequencyCounters = frequencyManager.countSimilarFrequencies(frequencyList, 1.0 / frequencyList.size());
        assertEquals(2, frequencyCounters.size());
    }

    @Test
    public void convertFrequenciesTest() {
        List<NoteTimeData> noteTimeData = frequencyManager.convertFrequencies(frequencyList, 1.0);
        System.out.println();
    }
}
