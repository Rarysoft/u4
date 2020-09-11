package com.rarysoft.u4.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Settlement implements Map {
    public static final int MAP_WIDTH = 32;
    public static final int MAP_HEIGHT = 32;

    private static final int NPC_COUNT = 32;

    private final int worldX;
    private final int worldY;
    private final int startX;
    private final int startY;
    private final Tile areaTile;

    public static Settlement fromStream(InputStream stream, int worldX, int worldY, int startX, int startY, Tile areaTile) throws IOException {
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
                people.add(new Person(npcTiles[npc], npcStartXs[npc], npcStartYs[npc], npcMovementBehaviours[npc], npcConversationIndexes[npc]));
            }
        }
        return new Settlement(data, people, worldX, worldY, startX, startY, areaTile);
    }

    private final Tile[][] data;

    private final List<Person> people;

    private Settlement(Tile[][] data, List<Person> people, int worldX, int worldY, int startX, int startY, Tile areaTile) {
        this.data = data;
        this.people = people;
        this.worldX = worldX;
        this.worldY = worldY;
        this.startX = startX;
        this.startY = startY;
        this.areaTile = areaTile;
    }

    @Override
    public MapType type() {
        return MapType.SETTLEMENT;
    }

    @Override
    public int worldX() {
        return worldX;
    }

    @Override
    public int worldY() {
        return worldY;
    }

    @Override
    public int startX() {
        return startX;
    }

    @Override
    public int startY() {
        return startY;
    }

    @Override
    public Tile[][] view(int centerX, int centerY, int radius) {
        int size = radius * 2 + 1;
        Tile[][] view = new Tile[size][size];
        for (int row = 0; row < size; row ++) {
            for (int col = 0; col < size; col ++) {
                int mapRow = centerY - radius + row;
                int mapCol = centerX - radius + col;
                view[row][col] = isWithinMapRange(mapCol, mapRow) ? data[mapRow][mapCol] : areaTile;
            }
        }
        return view;
    }

    @Override
    public Tile at(int x, int y) {
        if (x < 0 || x >= MAP_WIDTH || y < 0 || y >= MAP_HEIGHT) {
            return null;
        }
        return data[y][x];
    }

    private boolean isWithinMapRange(int x, int y) {
        return ! (y < 0 || y >= MAP_HEIGHT || x < 0 || x >= MAP_WIDTH);
    }
}
