package com.rarysoft.u4.model;

import com.rarysoft.u4.model.graphics.Tile;
import com.rarysoft.u4.model.npc.MovementBehaviour;
import com.rarysoft.u4.model.npc.Person;
import com.rarysoft.u4.model.party.Location;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Settlement implements Map {
    public static final int MAP_WIDTH = 32;
    public static final int MAP_HEIGHT = 32;

    private static final int NPC_COUNT = 32;

    private final Location location;
    private final int level;
    private final int worldX;
    private final int worldY;
    private final int startX;
    private final int startY;
    private final Tile areaTile;

    public static Settlement fromStream(InputStream stream, Location location, int level, int worldX, int worldY, int startX, int startY, Tile areaTile) throws IOException {
        Tile[][] data = new Tile[MAP_HEIGHT][MAP_WIDTH];
        for (int row = 0; row < MAP_HEIGHT; row ++) {
            for (int col = 0; col < MAP_WIDTH; col ++) {
                int tile = stream.read();
                data[row][col] = Tile.forIndex(tile);
            }
        }
        int[] npcTiles = new int[NPC_COUNT];
        int[] npcStartXs = new int[NPC_COUNT];
        int[] npcStartYs = new int[NPC_COUNT];
        int[] npcMovementBehaviours = new int[NPC_COUNT];
        int[] npcConversationIndexes = new int[NPC_COUNT];
        for (int npc = 0; npc < NPC_COUNT; npc ++) {
            npcTiles[npc] = stream.read();
        }
        for (int npc = 0; npc < NPC_COUNT; npc ++) {
            npcStartXs[npc] = stream.read();
        }
        for (int npc = 0; npc < NPC_COUNT; npc ++) {
            npcStartYs[npc] = stream.read();
        }
        for (int npc = 0; npc < NPC_COUNT; npc ++) {
            // these bytes are merely repetition of the tile bytes
            stream.read();
        }
        for (int npc = 0; npc < NPC_COUNT; npc ++) {
            // these bytes are merely repetition of the start X bytes
            stream.read();
        }
        for (int npc = 0; npc < NPC_COUNT; npc ++) {
            // these bytes are merely repetition of the start Y bytes
            stream.read();
        }
        for (int npc = 0; npc < NPC_COUNT; npc ++) {
            npcMovementBehaviours[npc] = stream.read();
        }
        for (int npc = 0; npc < NPC_COUNT; npc ++) {
            npcConversationIndexes[npc] = stream.read();
        }
        List<Person> people = new ArrayList<>();
        for (int npc = 0; npc < NPC_COUNT; npc ++) {
            if (npcTiles[npc] > 0) {
                people.add(new Person(Tile.forIndex(npcTiles[npc]), npcStartYs[npc], npcStartXs[npc], MovementBehaviour.forCode(npcMovementBehaviours[npc]), npcConversationIndexes[npc]));
            }
        }
        return new Settlement(location, level, data, people, worldX, worldY, startX, startY, areaTile);
    }

    private final Tile[][] data;

    private final List<Person> people;

    private Settlement(Location location, int level, Tile[][] data, List<Person> people, int worldX, int worldY, int startX, int startY, Tile areaTile) {
        this.location = location;
        this.level = level;
        this.data = data;
        this.people = people;
        this.worldX = worldX;
        this.worldY = worldY;
        this.startX = startX;
        this.startY = startY;
        this.areaTile = areaTile;
    }

    @Override
    public Location location() {
        return location;
    }

    @Override
    public int level() {
        return level;
    }

    @Override
    public MapType type() {
        return MapType.SETTLEMENT;
    }

    @Override
    public int worldRow() {
        return worldY;
    }

    @Override
    public int worldCol() {
        return worldX;
    }

    @Override
    public int startRow() {
        return startY;
    }

    @Override
    public int startCol() {
        return startX;
    }

    @Override
    public List<Person> people() {
        return people;
    }

    @Override
    public Optional<Person> personAt(int row, int col) {
        return people
                .stream()
                .filter(person -> person.row() == row && person.col() == col)
                .findAny();
    }

    @Override
    public void movePeople(PeopleMover peopleMover, int playerRow, int playerCol, Person excludedPerson) {
        peopleMover.movePeople(data, people, playerRow, playerCol, excludedPerson);
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
                view[row][col] = isWithinMapRange(mapRow, mapCol) ? data[mapRow][mapCol] : areaTile;
            }
        }
        return view;
    }

    @Override
    public Tile at(int row, int col) {
        if (col < 0 || col >= MAP_WIDTH || row < 0 || row >= MAP_HEIGHT) {
            return null;
        }
        return data[row][col];
    }

    private boolean isWithinMapRange(int row, int col) {
        return ! (row < 0 || row >= MAP_HEIGHT || col < 0 || col >= MAP_WIDTH);
    }
}
