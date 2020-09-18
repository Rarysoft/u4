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

import java.math.BigDecimal;

public class Coordinate {
    private static final int TILE_SIZE = 11;
    private static final int CENTER_INDEX = 10;
    private static final int MIN_INDEX = 0;
    private static final int MAX_INDEX = CENTER_INDEX * 2;
    private static final int MIN_COORD = -CENTER_INDEX * TILE_SIZE - 5;
    private static final int MAX_COORD = CENTER_INDEX * TILE_SIZE + 5;

    private final int row;
    private final int col;

    private int x;
    private int y;

    public static Coordinate forRowCol(int row, int col) {
        return new Coordinate(row, col, calculateX(col), calculateY(row));
    }

    public static Coordinate forXY(int x, int y) {
        return new Coordinate(calculateRow(y), calculateCol(x), x, y);
    }

    public static Coordinate center() {
        return new Coordinate(CENTER_INDEX, CENTER_INDEX, 0, 0);
    }

    private Coordinate(int row, int col, int x, int y) {
        if (row < MIN_INDEX || row > MAX_INDEX || col < MIN_INDEX || col > MAX_INDEX || x < MIN_COORD || x > MAX_COORD || y < MIN_COORD || y > MAX_COORD) {
            throw new IllegalArgumentException();
        }
        this.row = row;
        this.col = col;
        this.x = x;
        this.y = y;
    }

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public boolean isCenter() {
        return row == CENTER_INDEX && col == CENTER_INDEX;
    }

    public boolean isCenterRow() {
        return row == CENTER_INDEX;
    }

    public boolean isBelowCenterRow() {
        return row < CENTER_INDEX;
    }

    public boolean isCenterCol() {
        return col == CENTER_INDEX;
    }

    public boolean isBelowCenterCol() {
        return col < CENTER_INDEX;
    }

    public boolean isAdjacentTo(Coordinate coordinate) {
        return coordinate.row() == row && delta(coordinate.col(), col) == 1 ||
                coordinate.col() == col && delta(coordinate.row, row) == 1 ||
                (delta(coordinate.row(), row) == 1 && delta(coordinate.col(), col) == 1);
    }

    public boolean isEastOf(Coordinate coordinate) {
        return col > coordinate.col();
    }

    public BigDecimal slopeTo(Coordinate coordinate) {
        return BigDecimal.valueOf(coordinate.y - y).divide(BigDecimal.valueOf(coordinate.x - x), 2, BigDecimal.ROUND_HALF_UP);
    }

    public boolean isSameRowCol(Coordinate coordinate) {
        return coordinate.row() == row && coordinate.col() == col;
    }

    private static int calculateX(int col) {
        // Convert a col index to a proper x value where each tile is itself an 11x11 grid with the center of the
        // player tile at 0,0 and the x value representing the value at the center of the tile
        if (col == CENTER_INDEX) {
            return 0;
        }
        return (col - CENTER_INDEX) * TILE_SIZE;
    }

    private static int calculateY(int row) {
        // Convert a row index, which starts at the top and increases going down, to a proper y value, where each tile
        // is itself an 11x11 grid with the center of the player tile at 0,0 and the y value representing the value at
        // the center of the tile
        if (row == CENTER_INDEX) {
            return 0;
        }
        return (CENTER_INDEX - row) * TILE_SIZE;
    }

    private static int calculateRow(int y) {
        if (y == 0) {
            return CENTER_INDEX;
        }
        return (MAX_INDEX - ((y + (CENTER_INDEX * TILE_SIZE) + 5)) / TILE_SIZE);
    }

    private static int calculateCol(int x) {
        if (x == 0) {
            return CENTER_INDEX;
        }
        return (x + (CENTER_INDEX * TILE_SIZE) + 5) / TILE_SIZE;
    }

    private int delta(int a, int b) {
        return Math.abs(a - b);
    }
}
