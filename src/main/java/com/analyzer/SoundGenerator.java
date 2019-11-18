package com.analyzer;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class SoundGenerator {
    public static void main(String[] args) throws Exception {
        float sampleRate = 44100;
        double frequency = 261;
        double a = 0.5;
        double twoPiF = 2 * Math.PI * frequency;

        double[] buffer = new double[44100];
        for (int sample = 0; sample < buffer.length; sample++) {
            double time = sample / sampleRate;
            buffer[sample] = a * Math.sin(twoPiF * time);
        }

        final byte[] byteBuffer = new byte[buffer.length];
        int idx = 0;
        for (int i = 0; i < byteBuffer.length; ) {
            int x = (int) (buffer[idx++] * 127);
            byteBuffer[i++] = (byte) x;
        }

        boolean bigEndian = false;
        boolean signed = true;
        int bits = 8;
        int channels = 1;
        AudioFormat format = new AudioFormat(sampleRate, bits, channels, signed, bigEndian);

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
        line.open(format);
        line.start();
        long now = System.currentTimeMillis();
        line.write(byteBuffer, 0, byteBuffer.length);
        line.close();
        long total = System.currentTimeMillis() - now;

        //createWAV(byteBuffer);
    }

    public static byte[] generateSound(double sampleRate, double frequency, double amplitude) {
        double twoPiF = 2 * Math.PI * frequency;

        double[] buffer = new double[44100];
        for (int sample = 0; sample < buffer.length; sample++) {
            double time = sample / sampleRate;
            buffer[sample] = amplitude * Math.sin(twoPiF * time);
        }

        final byte[] byteBuffer = new byte[buffer.length];
        int idx = 0;
        for (int i = 0; i < byteBuffer.length; ) {
            int x = (int) (buffer[idx++] * 127);
            byteBuffer[i++] = (byte) x;
        }

        return byteBuffer;
    }

    public static void createWAV(byte[] byteBuffer) throws IOException {
        File out = new File("sample.wav"); //The path where user want the file data to be written

        //Construct an audio format, using 44100hz sampling rate, 16 bit samples, mono, and big
        // endian byte ordering
        AudioFormat format1 = new AudioFormat((float) 44100, 8, 1, true, false);

        // It uses 'merge' as its buffer array that contains bytes that may be read from the stream.
        ByteArrayInputStream bais = new ByteArrayInputStream(byteBuffer);

        //Constructs an audio input stream that has the requested format and length in sample frames, using audio data
        //from the specified input stream.
        AudioInputStream audioInputStream = new AudioInputStream(bais, format1, (long) (8 * (byteBuffer.length / 2)));

        //Writes a stream of bytes representing an audio file of the specified file type to the external file provided.
        AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, out);

        audioInputStream.close();       //Closes this audio input stream
    }
}
