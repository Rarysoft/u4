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

import java.sql.SQLOutput;
import java.util.List;

public class GameState {
    private final Maps maps;
    private final PeopleTracker peopleTracker;

    private Map map;
    private int surfaceRow;
    private int surfaceCol;
    private int row;
    private int col;

    public GameState(Maps maps, PeopleTracker peopleTracker, Map map) {
        this.maps = maps;
        this.peopleTracker = peopleTracker;
        this.map = map;
        this.col = maps.world().startX();
        this.row = maps.world().startY();
        switchToMap(this.map);
    }

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }

    public void increaseRow() {
        row++;
    }

    public void decreaseRow() {
        row--;
    }

    public void increaseCol() {
        col++;
    }

    public void decreaseCol() {
        col--;
    }

    public RenderedTile[][] mapView(int radius) {
        Tile[][] mapView = map.view(col, row, radius);
        int viewSize = radius * 2 + 1;
        RenderedTile[][] view = new RenderedTile[viewSize][viewSize];
        for (int row = 0; row < viewSize; row ++) {
            for (int col = 0; col < viewSize; col ++) {
                int mapRow = this.row - radius + row;
                int mapCol = this.col - radius + col;
                view[row][col] = renderedTile(mapView[row][col], mapRow, mapCol);
            }
        }
        return view;
    }

    public RenderedTile tileAt(int row, int col) {
        return renderedTile(map.at(row, col), row, col);
    }

    public void enter() {
        switchToMap(maps.mapAt(col, row));
        surfaceCol = col;
        surfaceRow = row;
        col = map.startX();
        row = map.startY();
    }

    public void returnToSurface() {
        map = maps.world();
        col = surfaceCol;
        row = surfaceRow;
    }

    public void movePeople() {
        peopleTracker.movePeople(map.full(), row, col);
    }

    private void switchToMap(Map map) {
        this.map = map;
        peopleTracker.addPeople(map.id(), map.people());
    }

    private RenderedTile renderedTile(Tile tile, int row, int col) {
        Person person = peopleTracker.personAt(map.id(), row, col).orElse(null);
        return new RenderedTile(tile, person);
    }
}
