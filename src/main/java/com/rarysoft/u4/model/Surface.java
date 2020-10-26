package com.rarysoft.u4.model;

import com.rarysoft.u4.model.npc.Person;
import com.rarysoft.u4.model.party.Location;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Surface implements Map {
    private static final int MAP_WIDTH = 256;
    private static final int MAP_HEIGHT = 256;
    private static final int CHUNK_COLUMNS = 8;
    private static final int CHUNK_ROWS = 8;
    private static final int CHUNK_WIDTH = 32;
    private static final int CHUNK_HEIGHT = 32;

    public static Surface fromStream(InputStream stream) throws IOException {
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
        return new Surface(data);
    }

    private final Tile[][] data;

    private Surface(Tile[][] data) {
        this.data = data;
    }

    @Override
    public MapType type() {
        return MapType.WORLD;
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
    public Tile[][] data() {
        return data;
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
    public Tile surroundingTile() {
        return null;
    }

    @Override
    public List<Person> people() {
        return new ArrayList<>();
    }

    @Override
    public Optional<Person> personAt(int row, int col) {
        return Optional.empty();
    }

    @Override
    public void movePeople(PeopleMover peopleMover, int playerRow, int playerCol, Person excludedPerson) {
        // TODO: no creatures on the surface yet
    }

    @Override
    public Tile at(int row, int col) {
        return data[row][col];
    }
}
