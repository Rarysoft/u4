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

import java.io.IOException;
import java.io.InputStream;

public class DungeonRoom {
    private static final int MAP_HEIGHT = 11;
    private static final int MAP_WIDTH = 11;

    private final int roomNumber;
    private final Tile[][] data;

    public static DungeonRoom fromStream(InputStream stream, int roomNumber) throws IOException {
        stream.skip(0x80);    // TODO: for now just skipping these details; need to implement later
        Tile[][] data = new Tile[MAP_HEIGHT][MAP_WIDTH];
        for (int row = 0; row < MAP_HEIGHT; row ++) {
            for (int col = 0; col < MAP_WIDTH; col ++) {
                data[row][col] = Tile.forIndex(stream.read());
            }
        }
        stream.skip(0x07);  // unused bytes
        return new DungeonRoom(roomNumber, data);
    }

    private DungeonRoom(int roomNumber, Tile[][] data) {
        this.roomNumber = roomNumber;
        this.data = data;
    }

    public int roomNumber() {
        return roomNumber;
    }

    public Tile[][] data() {
        return data;
    }
}
