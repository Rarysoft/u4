/*
 * MIT License
 *
 * Copyright (c) 2020 Rarysoft Enterprises
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.rarysoft.u4.model;

import com.rarysoft.u4.model.graphics.Tile;
import com.rarysoft.u4.model.npc.Person;
import com.rarysoft.u4.model.party.Location;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DungeonLevel implements Map {
    public static final int MAP_WIDTH = 8;
    public static final int MAP_HEIGHT = 8;

    public static DungeonLevel fromStream(InputStream stream, Location location, int level, int worldRow, int worldCol) throws IOException {
        Tile[][] data = new Tile[MAP_HEIGHT][MAP_WIDTH];
        int startRow = -1;
        int startCol = -1;
        for (int row = 0; row < MAP_HEIGHT; row ++) {
            for (int col = 0; col < MAP_WIDTH; col ++) {
                int tile = stream.read();
                if (tile == 0x10) {
                    // first ladder up we find is the starting point
                    if (startRow < 0) {
                        startRow = row;
                        startCol = col;
                    }
                }
                data[row][col] = Tile.forIndex(dungeonTile(tile));
            }
        }
        return new DungeonLevel(location, level, data, worldRow, worldCol, startRow, startCol);
    }

    private static int dungeonTile(int tile) {
        // In the original game, dungeons are rendered in 3D, so the dungeon maps use a different tile set than the
        // surface and settlement maps. For this, I'm mapping dungeon tiles to surface tiles.
        switch (tile) {
            case 0x00:  // nothing
                return Tile.GRASSLANDS.index();
            case 0x10:  // ladder up
                return Tile.LADDER_UP.index();
            case 0x20:  // ladder down
                return Tile.LADDER_DOWN.index();
            case 0x30:  // ladder up and down
                return Tile.LADDER_DOWN.index();    // TODO: how to address this properly?
            case 0x40:  // treasure chest
                return Tile.CHEST.index();
            case 0x50:  // ceiling hole (unused?)
                return Tile.GRASSLANDS.index();
            case 0x60:  // floor hole (unused)
                return Tile.GRASSLANDS.index();
            case 0x70:  // magic orb
                return Tile.MAGIC_SPHERE.index();
            case 0x80:  // winds/darkness trap
                return Tile.GRASSLANDS.index();
            case 0x81:  // falling rock trap
                return Tile.GRASSLANDS.index();
            case 0x8E:  // pit trap
                return Tile.GRASSLANDS.index();
            case 0x90:  // plain fountain
            case 0x91:  // healing fountain
            case 0x92:  // acid fountain
            case 0x93:  // cure fountain
            case 0x94:  // poison fountain
                return Tile.CHEST.index();    // TODO: how to address this properly?
            case 0xA0:  // poison field
                return Tile.POISON_FIELD.index();
            case 0xA1:  // energy field
                return Tile.ENERGY_FIELD.index();
            case 0xA2:  // fire field
                return Tile.FIRE_FIELD.index();
            case 0xA3:  // sleep field
                return Tile.SLEEP_FIELD.index();
            case 0xB0:  // altar
                return Tile.ALTAR.index();
            case 0xC0:  // door
                return Tile.UNLOCKED_DOOR.index();
            case 0xD0:  // dungeon rooms
            case 0xD1:  // dungeon rooms
            case 0xD2:  // dungeon rooms
            case 0xD3:  // dungeon rooms
            case 0xD4:  // dungeon rooms
            case 0xD5:  // dungeon rooms
            case 0xD6:  // dungeon rooms
            case 0xD7:  // dungeon rooms
            case 0xD8:  // dungeon rooms
            case 0xD9:  // dungeon rooms
            case 0xDA:  // dungeon rooms
            case 0xDB:  // dungeon rooms
            case 0xDC:  // dungeon rooms
            case 0xDD:  // dungeon rooms
            case 0xDE:  // dungeon rooms
            case 0xDF:  // dungeon rooms
                return Tile.UNLOCKED_DOOR.index();
            case 0xE0:  // secret door
                return Tile.UNLOCKED_DOOR.index();   // TODO: how to address this properly?
            case 0xF0:  // wall
                return Tile.STONE_WALL.index();
            default:
                return Tile.STONE_WALL.index();
        }
    }

    private final Location location;
    private final int level;
    private final int worldRow;
    private final int worldCol;
    private final int startRow;
    private final int startCol;

    private final Tile[][] data;

    private DungeonLevel(Location location, int level, Tile[][] data, int worldRow, int worldCol, int startRow, int startCol) {
        this.location = location;
        this.level = level;
        this.data = data;
        this.worldRow = worldRow;
        this.worldCol = worldCol;
        this.startRow = startRow;
        this.startCol = startCol;
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
    public List<Person> people() {
        return new ArrayList<>();
    }

    @Override
    public Optional<Person> personAt(int row, int col) {
        return Optional.empty();
    }

    @Override
    public void movePeople(PeopleMover peopleMover, int playerRow, int playerCol, Person excludedPerson) {
    }

    @Override
    public Tile[][] full() {
        return data;
    }

    @Override
    public Tile[][] view(ViewFinder viewFinder, int centerRow, int centerCol, int radius) {
        return viewFinder.view(data, Tile.STONE_WALL, radius, centerRow, centerCol);
    }

    @Override
    public Tile at(int row, int col) {
        if (col < 0 || col >= MAP_WIDTH || row < 0 || row >= MAP_HEIGHT) {
            return null;
        }
        return data[row][col];
    }
}
