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

public class ViewFinder {
    public Tile[][] view(Tile[][] area, Tile surroundingTile, int radius, int centerRow, int centerCol) {
        int size = radius * 2 + 1;
        Tile[][] view = new Tile[size][size];
        for (int row = 0; row < size; row ++) {
            for (int col = 0; col < size; col ++) {
                int mapRow = centerRow - radius + row;
                int mapCol = centerCol - radius + col;
                view[row][col] = isWithinMapRange(area, mapRow, mapCol) ? area[mapRow][mapCol] : surroundingTile;
            }
        }
        return view;
    }

    private boolean isWithinMapRange(Tile[][] area, int row, int col) {
        return ! (row < 0 || row >= area.length || col < 0 || col >= area[0].length);
    }
}
