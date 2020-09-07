package com.rarysoft.u4.model;

import java.io.IOException;
import java.io.InputStream;

public class World implements Map {
    private static final int MAP_WIDTH = 256;
    private static final int MAP_HEIGHT = 256;
    private static final int CHUNK_COLUMNS = 8;
    private static final int CHUNK_ROWS = 8;
    private static final int CHUNK_WIDTH = 32;
    private static final int CHUNK_HEIGHT = 32;

    public static World fromStream(InputStream stream) throws IOException {
        int[][] data = new int[MAP_HEIGHT][MAP_WIDTH];
        for (int chunkRow = 0; chunkRow < CHUNK_ROWS; chunkRow ++) {
            for (int chunk = 0; chunk < CHUNK_COLUMNS; chunk ++) {
                for (int row = 0; row < CHUNK_HEIGHT; row ++) {
                    for (int col = 0; col < CHUNK_WIDTH; col ++) {
                        int tile = stream.read();
                        data[chunkRow * CHUNK_WIDTH + row][chunk * CHUNK_HEIGHT + col] = tile;
                    }
                }
            }
        }
        return new World(data);
    }

    private final int[][] data;

    private World(int[][] data) {
        this.data = data;
    }

    @Override
    public int[][] view(int centerX, int centerY, int radius) {
        int size = radius * 2 + 1;
        int[][] chunk = new int[size][size];
        for (int row = 0; row < size; row ++) {
            for (int col = 0; col < size; col ++) {
                int mapRow = centerY - radius + row;
                int mapCol = centerX - radius + col;
                chunk[row][col] = isWithinMapRange(mapCol, mapRow) ? data[mapRow][mapCol] : -1;
            }
        }
        return chunk;
    }
    
    private boolean isWithinMapRange(int x, int y) {
        return ! (y < 0 || y >= MAP_HEIGHT || x < 0 || x >= MAP_WIDTH);
    }
}
