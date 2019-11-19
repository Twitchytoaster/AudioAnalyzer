package com.analyzer;

import org.apache.commons.lang3.ArrayUtils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException {
        File wave = new File("sample.wav");

        byte[] e = SoundGenerator.generateSound(44100, 164, 0.1);
        byte[] c = SoundGenerator.generateSound(44100, 130, 0.1);
        byte[] g = SoundGenerator.generateSound(44100, 196, 0.1);
        byte[] d = SoundGenerator.generateSound(44100, 146, 0.1);

        byte[] first = ArrayUtils.addAll(e, c);
        byte[] second = ArrayUtils.addAll(g, d);

        SoundGenerator.createWAV(ArrayUtils.addAll(first, second));

        SimpleWaveFileFrequencyExtractor wavRecognizer = new SimpleWaveFileFrequencyExtractor();
        AudioInputStream stream = AudioSystem.getAudioInputStream(wave);

        WaveFile waveFile = new WaveFile(wave);
        double durationTime = waveFile.getDurationTime();
        List<Float> frequencies = wavRecognizer.getFrequencies(wave);
        System.out.println(frequencies);
    }
}
