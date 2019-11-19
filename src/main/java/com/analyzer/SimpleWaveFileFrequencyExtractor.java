package com.analyzer;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SimpleWaveFileFrequencyExtractor {
    static String[] notes = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};

    public List<Float> getFrequencies(File fileIn) throws IOException, UnsupportedAudioFileException {
        int size = 1048576;
        AudioInputStream ais = AudioSystem.getAudioInputStream(fileIn);
        byte[] bytesIn = new byte[size];
        int read = ais.read(bytesIn);
        float[] samples = new float[size];
        AudioConverter.decode(bytesIn, samples, read, ais.getFormat());

        double[] dSamples = new double[size];

        for (int i = 0; i < size; i++) {
            dSamples[i] = samples[i];
        }

        FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
        Complex[] resultC = fft.transform(dSamples, TransformType.FORWARD);

        double[] results = new double[resultC.length];
        for (int i = 0; i < resultC.length; i++) {
            double real = resultC[i].getReal();
            double imaginary = resultC[i].getImaginary();
            results[i] = Math.sqrt(real * real + imaginary * imaginary);
        }

        List<Float> found = DFT.process(results, ais.getFormat().getSampleRate(), resultC.length, 7);
        return found;
    }

    public static String closestKey(double freq) {
        int key = closestKeyIndex(freq);
        if (key <= 0) {
            return null;
        }
        int range = 1 + (key - 1) / notes.length;
        return notes[(key - 1) % notes.length] + range;
    }

    public static int closestKeyIndex(double freq) {
        return 1 + (int) ((12 * Math.log(freq / 440) / Math.log(2) + 49) - 0.5);
    }
}
