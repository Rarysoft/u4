package com.rarysoft.u4.game;

import com.rarysoft.u4.game.npc.MovementBehaviour;
import com.rarysoft.u4.game.npc.NonPlayerCharacter;
import com.rarysoft.u4.game.npc.Person;
import com.rarysoft.u4.game.party.Location;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Settlement implements Map {
    public static final int MAP_WIDTH = 32;
    public static final int MAP_HEIGHT = 32;

    private static final int NPC_COUNT = 32;

    public static Settlement fromStream(InputStream stream, Location location, int level, int worldRow, int worldCol, int startRow, int startCol, Tile areaTile) throws IOException {
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
                NonPlayerCharacter nonPlayerCharacter = NonPlayerCharacter.CITIZEN;
                if (npcTiles[npc] == Tile.LORD_BRITISH_1.index() || npcTiles[npc] == Tile.LORD_BRITISH_2.index()) {
                    nonPlayerCharacter = NonPlayerCharacter.LORD_BRITISH;
                }
                else if (location == Location.CASTLE_BRITANNIA && npcStartYs[npc] == 27 && npcStartXs[npc] == 9) {
                    nonPlayerCharacter = NonPlayerCharacter.HAWKWIND;
                }
                people.add(new Person(nonPlayerCharacter, npcTiles[npc], npcStartYs[npc], npcStartXs[npc], MovementBehaviour.forCode(npcMovementBehaviours[npc]), npcConversationIndexes[npc]));
            }
        }
        return new Settlement(location, level, data, people, worldRow, worldCol, startRow, startCol, areaTile);
    }

    private final Location location;
    private final int level;
    private final int worldRow;
    private final int worldCol;
    private final int startRow;
    private final int startCol;
    private final Tile surroundingTile;

    private final Tile[][] data;

    private final List<Person> people;

    private Settlement(Location location, int level, Tile[][] data, List<Person> people, int worldRow, int worldCol, int startRow, int startCol, Tile surroundingTile) {
        this.location = location;
        this.level = level;
        this.data = data;
        this.people = people;
        this.worldRow = worldRow;
        this.worldCol = worldCol;
        this.startRow = startRow;
        this.startCol = startCol;
        this.surroundingTile = surroundingTile;
    }

    @Override
    public MapType type() {
        return MapType.SETTLEMENT;
    }

    @Override
    public Location location() {
        return location;
    }

    @Override
    public int width() {
        return MAP_WIDTH;
    }

    @Override
    public int height() {
        return MAP_HEIGHT;
    }

    @Override
    public int level() {
        return level;
    }

    @Override
    public Tile[][] data() {
        return data;
    }

    @Override
    public int worldRow() {
        return worldRow;
    }

    @Override
    public int worldCol() {
        return worldCol;
    }

    @Override
    public int startRow() {
        return startRow;
    }

    @Override
    public int startCol() {
        return startCol;
    }

    @Override
    public Tile surroundingTile() {
        return surroundingTile;
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
        peopleMover.movePeople(new Area<>(data), people, playerRow, playerCol, excludedPerson);
    }

    @Override
    public Tile at(int row, int col) {
        if (col < 0 || col >= MAP_WIDTH || row < 0 || row >= MAP_HEIGHT) {
            return null;
        }
        return data[row][col];
    }
}
