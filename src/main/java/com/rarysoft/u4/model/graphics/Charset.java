package com.rarysoft.u4.model.graphics;

import java.io.IOException;
import java.io.InputStream;

public class Charset {
    public static final int CHAR_WIDTH = 8;
    public static final int CHAR_HEIGHT = 8;

    private static final int CHAR_COUNT = 256;

    public static Charset fromStream(InputStream stream) throws IOException {
        int[][][] data = new int[CHAR_COUNT][CHAR_HEIGHT][CHAR_WIDTH];
        for (int tile = 0; tile < CHAR_COUNT; tile ++) {
            for (int row = 0; row < CHAR_HEIGHT; row ++) {
                for (int col = 0; col < CHAR_WIDTH; col +=2) {
                    int value = stream.read();
                    if (value < 0) {
                        throw new IOException("Unexpected end of stream");
                    }
                    int leftCode = value >>> 4;
                    int rightCode = ((value << 4) & 0xFF) >>> 4;
                    data[tile][row][col] = leftCode;
                    data[tile][row][col + 1] = rightCode;
                }
            }
        }
        return new Charset(data);
    }

    private final int[][][] data;

    private Charset(int[][][] data) {
        this.data = data;
    }

    public int[][][] data() {
        return data;
    }
}
