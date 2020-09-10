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

public class GameState {
    private final Maps maps;

    private Map map;
    private int surfaceX;
    private int surfaceY;
    private int x;
    private int y;

    public GameState(Maps maps, Map map) {
        this.maps = maps;
        this.map = map;
        this.x = maps.world().startX();
        this.y = maps.world().startY();
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public void increaseX() {
        x ++;
    }

    public void decreaseX() {
        x --;
    }

    public void increaseY() {
        y ++;
    }

    public void decreaseY() {
        y --;
    }

    public int[][] playerView(int radius) {
        return map.view(x, y, radius);
    }

    public Tile tileAt(int x, int y) {
        int tile = map.at(x, y);
        if (tile < 0) {
            return null;
        }
        return Tile.forIndex(tile);
    }

    public void enter() {
        map = maps.mapAt(x, y);
        surfaceX = x;
        surfaceY = y;
        x = map.startX();
        y = map.startY();
    }

    public void returnToSurface() {
        map = maps.world();
        x = surfaceX;
        y = surfaceY;
    }

    public boolean isOnSurface() {
        return map.type() == MapType.WORLD;
    }
}
