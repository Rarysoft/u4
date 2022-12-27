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
package com.rarysoft.u4.game;

import com.rarysoft.u4.game.npc.Person;
import com.rarysoft.u4.game.model.Location;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DungeonLevel implements Map {
    private static final int INPUT_WIDTH = 8;
    private static final int INPUT_HEIGHT = 8;
    private static final int ROOM_WIDTH_SCALE = 11;
    private static final int ROOM_HEIGHT_SCALE = 11;
    private static final int BORDER_WIDTH = 1;
    private static final int MAP_WIDTH = BORDER_WIDTH + INPUT_WIDTH * ROOM_WIDTH_SCALE + BORDER_WIDTH;
    private static final int MAP_HEIGHT = BORDER_WIDTH + INPUT_HEIGHT * ROOM_HEIGHT_SCALE + BORDER_WIDTH;
    private static final int CENTER_ROW = (ROOM_HEIGHT_SCALE - 1) / 2;
    private static final int CENTER_COL = (ROOM_WIDTH_SCALE - 1) / 2;

    public static int heightScale() {
        return ROOM_HEIGHT_SCALE;
    }

    public static int widthScale() {
        return ROOM_WIDTH_SCALE;
    }

    public static int borderWidth() {
        return BORDER_WIDTH;
    }

    public static DungeonLevel fromStream(InputStream stream, Location location, int level, int worldRow, int worldCol) throws IOException {
        Tile[][] data = new Tile[MAP_HEIGHT][MAP_WIDTH];
        // surround the map with brick walls
        for (int row = 0; row < MAP_HEIGHT; row ++) {
            for (int col = 0; col < MAP_WIDTH; col ++) {
                if (row == 0 || col == 0 || row == MAP_HEIGHT - 1 || col == MAP_WIDTH - 1) {
                    data[row][col] = Tile.BRICK_WALL;
                }
            }
        }
        int startRow = -1;
        int startCol = -1;
        for (int row = 0; row < INPUT_HEIGHT; row ++) {
            for (int col = 0; col < INPUT_WIDTH; col ++) {
                int tile = stream.read();
                if (tile == 0x10) {
                    // first ladder up we find is the starting point
                    if (startRow < 0) {
                        startRow = row * ROOM_HEIGHT_SCALE + CENTER_ROW + 1;
                        startCol = col * ROOM_WIDTH_SCALE + CENTER_COL + 1;
                    }
                }
                Tile[][] tiles = dungeonTiles(tile);
                for (int tileRow = 0; tileRow < ROOM_HEIGHT_SCALE; tileRow ++) {
                    for (int tileCol = 0; tileCol < ROOM_WIDTH_SCALE; tileCol ++) {
                        data[row * ROOM_HEIGHT_SCALE + tileRow + 1][col * ROOM_WIDTH_SCALE + tileCol + 1] = tiles[tileRow][tileCol];
                    }
                }
            }
        }
        return new DungeonLevel(location, level, data, worldRow, worldCol, startRow, startCol);
    }

    private static Tile[][] dungeonTiles(int tile) {
        // In the original game, dungeons are rendered in 3D, so the dungeon maps use a different tile set than the
        // surface and settlement maps. For this, I'm mapping dungeon tiles to surface tiles.
        switch (tile) {
            case 0x00:  // nothing
                return fillArea(Tile.TILE_FLOOR);
            case 0x10:  // ladder up
                return fillAreaWithDifferentCenterTile(Tile.TILE_FLOOR, Tile.LADDER_UP);
            case 0x20:  // ladder down
                return fillAreaWithDifferentCenterTile(Tile.TILE_FLOOR, Tile.LADDER_DOWN);
            case 0x30:  // ladder up and down
                return fillAreaWithDifferentCenterTiles(Tile.TILE_FLOOR, Tile.LADDER_UP, Tile.LADDER_DOWN);
            case 0x40:  // treasure chest
                return fillAreaWithDifferentCenterTile(Tile.TILE_FLOOR, Tile.CHEST);
            case 0x50:  // ceiling hole (unused?)
            case 0x60:  // floor hole (unused)
                return fillArea(Tile.TILE_FLOOR);
            case 0x70:  // magic orb
                return fillAreaWithDifferentCenterTile(Tile.TILE_FLOOR, Tile.MAGIC_SPHERE);
            case 0x80:  // winds/darkness trap
            case 0x81:  // falling rock trap
            case 0x8E:  // pit trap
                return fillArea(Tile.TILE_FLOOR);
            case 0x90:  // plain fountain
            case 0x91:  // healing fountain
            case 0x92:  // acid fountain
            case 0x93:  // cure fountain
            case 0x94:  // poison fountain
                return fillAreaWithDifferentCenterTile(Tile.TILE_FLOOR, Tile.CHEST);    // TODO: how to address this properly?
            case 0xA0:  // poison field
                return fillAreaWithBorder(Tile.TILE_FLOOR, Tile.POISON_FIELD);
            case 0xA1:  // energy field
                return fillAreaWithBorder(Tile.TILE_FLOOR, Tile.ENERGY_FIELD);
            case 0xA2:  // fire field
                return fillAreaWithBorder(Tile.TILE_FLOOR, Tile.FIRE_FIELD);
            case 0xA3:  // sleep field
                return fillAreaWithBorder(Tile.TILE_FLOOR, Tile.SLEEP_FIELD);
            case 0xB0:  // altar
                return fillAreaWithDifferentCenterTile(Tile.TILE_FLOOR, Tile.ALTAR);
            case 0xC0:  // door
                // this will be modified later
                return fillArea(Tile.UNLOCKED_DOOR);
            case 0xD0:  // dungeon rooms
                // this is a placeholder for the room and will be modifier later
                return fillAreaWithDifferentCenterTile(Tile.TILE_FLOOR, Tile.A);
            case 0xD1:  // dungeon rooms
                // this is a placeholder for the room and will be modifier later
                return fillAreaWithDifferentCenterTile(Tile.TILE_FLOOR, Tile.B);
            case 0xD2:  // dungeon rooms
                // this is a placeholder for the room and will be modifier later
                return fillAreaWithDifferentCenterTile(Tile.TILE_FLOOR, Tile.C);
            case 0xD3:  // dungeon rooms
                // this is a placeholder for the room and will be modifier later
                return fillAreaWithDifferentCenterTile(Tile.TILE_FLOOR, Tile.D);
            case 0xD4:  // dungeon rooms
                // this is a placeholder for the room and will be modifier later
                return fillAreaWithDifferentCenterTile(Tile.TILE_FLOOR, Tile.E);
            case 0xD5:  // dungeon rooms
                // this is a placeholder for the room and will be modifier later
                return fillAreaWithDifferentCenterTile(Tile.TILE_FLOOR, Tile.F);
            case 0xD6:  // dungeon rooms
                // this is a placeholder for the room and will be modifier later
                return fillAreaWithDifferentCenterTile(Tile.TILE_FLOOR, Tile.G);
            case 0xD7:  // dungeon rooms
                // this is a placeholder for the room and will be modifier later
                return fillAreaWithDifferentCenterTile(Tile.TILE_FLOOR, Tile.H);
            case 0xD8:  // dungeon rooms
                // this is a placeholder for the room and will be modifier later
                return fillAreaWithDifferentCenterTile(Tile.TILE_FLOOR, Tile.I);
            case 0xD9:  // dungeon rooms
                // this is a placeholder for the room and will be modifier later
                return fillAreaWithDifferentCenterTile(Tile.TILE_FLOOR, Tile.J);
            case 0xDA:  // dungeon rooms
                // this is a placeholder for the room and will be modifier later
                return fillAreaWithDifferentCenterTile(Tile.TILE_FLOOR, Tile.K);
            case 0xDB:  // dungeon rooms
                // this is a placeholder for the room and will be modifier later
                return fillAreaWithDifferentCenterTile(Tile.TILE_FLOOR, Tile.L);
            case 0xDC:  // dungeon rooms
                // this is a placeholder for the room and will be modifier later
                return fillAreaWithDifferentCenterTile(Tile.TILE_FLOOR, Tile.M);
            case 0xDD:  // dungeon rooms
                // this is a placeholder for the room and will be modifier later
                return fillAreaWithDifferentCenterTile(Tile.TILE_FLOOR, Tile.N);
            case 0xDE:  // dungeon rooms
                // this is a placeholder for the room and will be modifier later
                return fillAreaWithDifferentCenterTile(Tile.TILE_FLOOR, Tile.O);
            case 0xDF:  // dungeon rooms
                // this is a placeholder for the room and will be modifier later
                return fillAreaWithDifferentCenterTile(Tile.TILE_FLOOR, Tile.P);
            case 0xE0:  // secret door
                // this will be modified later
                return fillArea(Tile.HIDDEN_PASSAGE);
            case 0xF0:  // wall
                return fillArea(Tile.BRICK_WALL);
            default:
                return fillArea(Tile.BRICK_WALL);
        }
    }

    private static Tile[][] fillArea(Tile tile) {
        Tile[][] filledArea = new Tile[ROOM_HEIGHT_SCALE][ROOM_WIDTH_SCALE];
        for (int row = 0; row < ROOM_HEIGHT_SCALE; row ++) {
            for (int col = 0; col < ROOM_WIDTH_SCALE; col ++) {
                filledArea[row][col] = tile;
            }
        }
        return filledArea;
    }

    private static Tile[][] fillAreaWithDifferentCenterTile(Tile tile, Tile centerTile) {
        Tile[][] filledArea = new Tile[ROOM_HEIGHT_SCALE][ROOM_WIDTH_SCALE];
        for (int row = 0; row < ROOM_HEIGHT_SCALE; row ++) {
            for (int col = 0; col < ROOM_WIDTH_SCALE; col ++) {
                filledArea[row][col] = (row == CENTER_ROW && col == CENTER_COL) ? centerTile : tile;
            }
        }
        return filledArea;
    }

    private static Tile[][] fillAreaWithDifferentCenterTiles(Tile tile, Tile centerTile1, Tile centerTile2) {
        Tile[][] filledArea = new Tile[ROOM_HEIGHT_SCALE][ROOM_WIDTH_SCALE];
        for (int row = 0; row < ROOM_HEIGHT_SCALE; row ++) {
            for (int col = 0; col < ROOM_WIDTH_SCALE; col ++) {
                filledArea[row][col] = (row == CENTER_ROW - 1 && col == CENTER_COL - 1) ? centerTile1 : (row == CENTER_ROW + 1 && col == CENTER_COL + 1) ? centerTile2 : tile;
            }
        }
        return filledArea;
    }

    private static Tile[][] fillAreaWithBorder(Tile areaTile, Tile borderTile) {
        Tile[][] filledArea = new Tile[ROOM_HEIGHT_SCALE][ROOM_WIDTH_SCALE];
        for (int row = 0; row < ROOM_HEIGHT_SCALE; row ++) {
            for (int col = 0; col < ROOM_WIDTH_SCALE; col ++) {
                filledArea[row][col] = (row == 0 || col == 0 || row == ROOM_HEIGHT_SCALE - 1 || col == ROOM_WIDTH_SCALE - 1) ? borderTile : areaTile;
            }
        }
        return filledArea;
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
    public MapType type() {
        return MapType.DUNGEON;
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
    }

    @Override
    public Tile at(int row, int col) {
        if (col < 0 || col >= MAP_WIDTH || row < 0 || row >= MAP_HEIGHT) {
            return null;
        }
        return data[row][col];
    }

    public void overlayRoom(DungeonRoom room) {
        // first find the room; they're labelled with A through P in the center tile
        int roomStartRow = -1;
        int roomStartCol = -1;
        for (int row = 0; row < MAP_HEIGHT; row ++) {
            for (int col = 0; col < MAP_WIDTH; col ++) {
                if (data[row][col].index() == Tile.A.index() + room.roomNumber()) {
                    roomStartRow = row - CENTER_ROW;
                    roomStartCol = col - CENTER_COL;
                    break;
                }
            }
            if (roomStartRow > -1 && roomStartCol > -1) {
                break;
            }
        }
        if (roomStartRow < 0 && roomStartCol < 0) {
            // I'm not sure where this happens yet, but it happens; ignore for now
            return;
        }
        // now copy the room data into the area reserved for the room
        for (int row = 0; row < ROOM_HEIGHT_SCALE; row ++) {
            for (int col = 0; col < ROOM_WIDTH_SCALE; col ++) {
                Tile roomTile = room.data()[row][col];
                if (roomTile == Tile.BLANK) {
                    // blank tiles in the original game are always hidden behind walls and simply render as dead space;
                    // blank tiles on the edge of the room could become accessible next to an open space, so we change
                    // them to brick walls, and they'll still render as blank from within the room since their
                    // visibility is blocked by walls
                    roomTile = Tile.BRICK_WALL;
                }
                int mapRow = roomStartRow + row;
                int mapCol = roomStartCol + col;
                data[mapRow][mapCol] = roomTile;
            }
        }
    }

    public void enhanceMap(DungeonMapEnhancer dungeonMapEnhancer) {
        dungeonMapEnhancer.enhance(this.data);
    }
}
