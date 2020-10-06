package com.rarysoft.u4.model;

import com.rarysoft.u4.model.graphics.Tile;
import com.rarysoft.u4.model.npc.Person;
import com.rarysoft.u4.model.party.Location;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
    public Location location() {
        return Location.SURFACE;
    }

    @Override
    public int level() {
        return 1;
    }

    @Override
    public MapType type() {
        return MapType.WORLD;
    }

    @Override
    public int worldRow() {
        return 0;
    }

    @Override
    public int worldCol() {
        return 0;
    }

    @Override
    public int startRow() {
        return 104;
    }

    @Override
    public int startCol() {
        return 78;
    }

    @Override
    public List<Person> people() {
        return new ArrayList<>();
    }

    @Override
    public Tile[][] full() {
        return data;
    }

    @Override
    public Tile[][] view(int centerRow, int centerCol, int radius) {
        int size = radius * 2 + 1;
        Tile[][] view = new Tile[size][size];
        for (int row = 0; row < size; row ++) {
            for (int col = 0; col < size; col ++) {
                int mapRow = centerRow - radius + row;
                int mapCol = centerCol - radius + col;
                view[row][col] = isWithinMapRange(mapCol, mapRow) ? data[mapRow][mapCol] : Tile.DEEP_WATER;
            }
        }
        return view;
    }

    @Override
    public Tile at(int row, int col) {
        return data[row][col];
    }

    private boolean isWithinMapRange(int x, int y) {
        return ! (y < 0 || y >= MAP_HEIGHT || x < 0 || x >= MAP_WIDTH);
    }
}
