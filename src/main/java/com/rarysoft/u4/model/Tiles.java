package com.rarysoft.u4.model;

import java.io.IOException;
import java.io.InputStream;

public class Tiles {
    public static final int TILE_WIDTH = 16;
    public static final int TILE_HEIGHT = 16;

    private static final int TILE_COUNT = 256;
    private static final int EXTENDED_TILE_COUNT = 1;

    public static Tiles fromStream(InputStream stream) throws IOException {
        int[][][] data = new int[TILE_COUNT + EXTENDED_TILE_COUNT][TILE_HEIGHT][TILE_WIDTH];
        for (int tile = 0; tile < TILE_COUNT; tile ++) {
            for (int row = 0; row < TILE_HEIGHT; row ++) {
                for (int col = 0; col < TILE_WIDTH; col +=2) {
                    int value = stream.read();
                    int leftCode = value >>> 4;
                    int rightCode = ((value << 4) & 0xFF) >>> 4;
                    data[tile][row][col] = leftCode;
                    data[tile][row][col + 1] = rightCode;
                }
            }
        }
        return new Tiles(data);
    }

    private final int[][][] data;

    private Tiles(int[][][] data) {
        this.data = data;
    }

    public int[][][] data() {
        return data;
    }
}
