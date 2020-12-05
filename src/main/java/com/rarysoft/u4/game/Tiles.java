package com.rarysoft.u4.game;

import java.io.IOException;
import java.io.InputStream;

public class Tiles {
    public static final int TILE_WIDTH = 16;
    public static final int TILE_HEIGHT = 16;
    public static final int FRAME_COUNT = 16;

    public static Tiles fromStream(InputStream stream, TileMapper tileMapper) throws IOException {
        return Tiles.fromStream(stream, tileMapper.tileCount(), tileMapper);
    }

    private static Tiles fromStream(InputStream stream, int tileCount, TileMapper tileMapper) throws IOException {
        int[][][][] data = new int[tileCount][FRAME_COUNT][TILE_HEIGHT][TILE_WIDTH];
        for (int tile = 0; tile < tileCount; tile ++) {
            for (int row = 0; row < TILE_HEIGHT; row ++) {
                for (int col = 0; col < TILE_WIDTH; col +=2) {
                    int value = stream.read();
                    int leftCode = value >>> 4;
                    int rightCode = ((value << 4) & 0xFF) >>> 4;
                    data[tile][0][row][col] = leftCode;
                    data[tile][0][row][col + 1] = rightCode;
                }
            }
        }
        if (tileMapper != null) {
            return new Tiles(tileMapper.map(data));
        }
        return new Tiles(data);
    }

    private final int[][][][] data;

    protected Tiles(int[][][][] data) {
        this.data = data;
    }

    public int[][][][] data() {
        return data;
    }
}
