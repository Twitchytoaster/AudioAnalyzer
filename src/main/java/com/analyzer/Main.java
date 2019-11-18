package com.analyzer;

import org.apache.commons.lang3.ArrayUtils;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException {
        byte[] e = SoundGenerator.generateSound(44100, 164, 0.5);
        byte[] c = SoundGenerator.generateSound(44100, 130, 0.5);
        byte[] g = SoundGenerator.generateSound(44100, 196, 0.5);
        byte[] d = SoundGenerator.generateSound(44100, 146, 0.5);

        byte[] first = ArrayUtils.addAll(e, c);
        byte[] second = ArrayUtils.addAll(g, d);

        SoundGenerator.createWAV(ArrayUtils.addAll(first,  second));

        WavRecognizer wavRecognizer = new WavRecognizer();

        System.out.println(wavRecognizer.getFrequencies(new File("sample.wav")));
        System.out.println(wavRecognizer.getFrequencies(new File("sample.wav")));
    }
}
