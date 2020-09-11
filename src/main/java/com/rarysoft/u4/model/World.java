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
        Tile[][] data = new Tile[MAP_HEIGHT][MAP_WIDTH];
        for (int chunkRow = 0; chunkRow < CHUNK_ROWS; chunkRow ++) {
            for (int chunk = 0; chunk < CHUNK_COLUMNS; chunk ++) {
                for (int row = 0; row < CHUNK_HEIGHT; row ++) {
                    for (int col = 0; col < CHUNK_WIDTH; col ++) {
                        int tile = stream.read();
                        data[chunkRow * CHUNK_WIDTH + row][chunk * CHUNK_HEIGHT + col] = Tile.forIndex(tile);
                    }
                }
            }
        }
        return new World(data);
    }

    private final Tile[][] data;

    private World(Tile[][] data) {
        this.data = data;
    }

    @Override
    public MapType type() {
        return MapType.WORLD;
    }

    @Override
    public int worldX() {
        return 0;
    }

    @Override
    public int worldY() {
        return 0;
    }

    @Override
    public int startX() {
        return 78;
    }

    @Override
    public int startY() {
        return 104;
    }

    @Override
    public Tile[][] view(int centerX, int centerY, int radius) {
        int size = radius * 2 + 1;
        Tile[][] view = new Tile[size][size];
        for (int row = 0; row < size; row ++) {
            for (int col = 0; col < size; col ++) {
                int mapRow = centerY - radius + row;
                int mapCol = centerX - radius + col;
                view[row][col] = isWithinMapRange(mapCol, mapRow) ? data[mapRow][mapCol] : Tile.DEEP_WATER;
            }
        }
        return view;
    }

    @Override
    public Tile at(int x, int y) {
        return data[y][x];
    }

    private boolean isWithinMapRange(int x, int y) {
        return ! (y < 0 || y >= MAP_HEIGHT || x < 0 || x >= MAP_WIDTH);
    }
}
