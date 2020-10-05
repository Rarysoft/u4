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

import com.rarysoft.u4.model.npc.Conversation;
import com.rarysoft.u4.model.npc.PeopleTracker;
import com.rarysoft.u4.model.npc.Person;
import com.rarysoft.u4.model.graphics.Tile;
import com.rarysoft.u4.model.party.Location;
import com.rarysoft.u4.model.party.Party;

import java.util.HashSet;
import java.util.Set;

public class GameState {
    private final Maps maps;
    private final PeopleTracker peopleTracker;
    private final Party party;

    private Map map;
    private int surfaceRow;
    private int surfaceCol;
    private PlayMode playMode;
    private Conversation conversation;
    private Person conversingPerson;
    private String input;

    private Set<Door> doors;

    public GameState(Maps maps, PeopleTracker peopleTracker, Party party) {
        this.maps = maps;
        this.peopleTracker = peopleTracker;
        this.map = maps.map(party.getCurrentPartyLocation().code(), party.getDungeonLevel());
        this.party = party;
        this.playMode = PlayMode.NORMAL;
        switchToMap(this.map);
    }

    public int row() {
        return party.getRow();
    }

    public int col() {
        return party.getCol();
    }

    public void changeRow(int delta) {
        party.setRow(party.getRow() + delta);
    }

    public void changeCol(int delta) {
        party.setCol(party.getCol() + delta);
    }

    public boolean inNormalPlay() {
        return playMode == PlayMode.NORMAL;
    }

    public boolean inConversation() {
        return playMode == PlayMode.CONVERSATION || playMode == PlayMode.CONVERSATION_QUERIED;
    }

    public boolean inConversationRespondingYesOrNo() {
        return playMode == PlayMode.CONVERSATION_QUERIED;
    }

    public Conversation conversation() {
        return conversation;
    }

    public String input() {
        return input;
    }

    public Location location() {
        return Location.forCode(map.locationId());
    }

    public RenderedTile[][] mapView(int radius) {
        int row = party.getRow();
        int col = party.getCol();
        Tile[][] mapView = map.view(row, col, radius);
        int viewSize = radius * 2 + 1;
        RenderedTile[][] view = new RenderedTile[viewSize][viewSize];
        for (int viewRow = 0; viewRow < viewSize; viewRow ++) {
            for (int viewCol = 0; viewCol < viewSize; viewCol ++) {
                int mapRow = row - radius + viewRow;
                int mapCol = col - radius + viewCol;
                RenderedTile renderedTile = renderedTile(mapView[viewRow][viewCol], mapRow, mapCol);
                view[viewRow][viewCol] = renderedTile;
            }
        }
        return view;
    }

    public RenderedTile tileAt(int row, int col) {
        return renderedTile(map.at(row, col), row, col);
    }

    public void enter() {
        int row = party.getRow();
        int col = party.getCol();
        if (map.locationId() == 0) {
            Map map = maps.mapAt(row, col);
            switchToMap(map);
            surfaceCol = col;
            surfaceRow = row;
            party.setCurrentPartyLocation(Location.forCode(map.locationId()));
            party.setDungeonLevel(map.level()); // Using this in all locations, not just dungeons
            party.setRow(map.startRow());
            party.setCol(map.startCol());
        }
        else if (map.locationId() == Location.CASTLE_BRITANNIA.code()) {
            if (row == 3 && (col == 3 || col == 27)) {
                switchToMap(maps.map(Location.CASTLE_BRITANNIA.code(), map.level() == 1 ? 2 : 1));
            }
        }
    }

    public void returnToSurface() {
        map = maps.world();
        party.setCurrentPartyLocation(Location.SURFACE);
        party.setRow(surfaceRow);
        party.setCol(surfaceCol);
    }

    public void postTurnUpdates() {
        peopleTracker.movePeople(map.full(), party.getRow(), party.getCol(), conversingPerson);
        doors.forEach(Door::turnCompleted);
    }

    public void startConversation(Conversation conversation, Person person) {
        playMode = PlayMode.CONVERSATION;
        this.conversation = conversation;
        this.conversingPerson = person;
        input = "";
    }

    public void playerQueried() {
        playMode = PlayMode.CONVERSATION_QUERIED;
    }

    public void queryAnswered() {
        playMode = PlayMode.CONVERSATION;
    }

    public void addToOngoingInput(char input) {
        this.input += input;
    }

    public void revertLastInput() {
        this.input = this.input.substring(0, this.input.length() - 1);
    }

    public void resetInput() {
        input = "";
    }

    public void endConversation() {
        playMode = PlayMode.NORMAL;
        this.conversation = null;
        this.conversingPerson = null;
        input = null;
    }

    public void openDoor(int row, int col) {
        doors.stream().filter(door -> door.getRow() == row && door.getCol() == col).findAny().ifPresent(door -> door.open(5));
    }

    private void switchToMap(Map map) {
        this.map = map;
        peopleTracker.addPeople(map.locationId(), map.people());
        prepareDoors(map);
    }

    private RenderedTile renderedTile(Tile tile, int row, int col) {
        Tile tileToRender = tile;
        if (tile == Tile.UNLOCKED_DOOR || tile == Tile.LOCKED_DOOR) {
            if (isDoorOpen(row, col)) {
                tileToRender = Tile.BRICK_FLOOR;
            }
        }
        Person person = peopleTracker.personAt(map.locationId(), row, col).orElse(null);
        return new RenderedTile(tileToRender, person);
    }

    private void prepareDoors(Map map) {
        doors = new HashSet<>();
        if (map.locationId() == 0) {
            // there are no doors in the world map
            return;
        }
        Tile[][] tiles = map.full();
        for (int row = 0; row < tiles.length; row ++) {
            for (int col = 0; col < tiles[row].length; col ++) {
                Tile tile = tiles[row][col];
                if (tile == Tile.UNLOCKED_DOOR || tile == Tile.LOCKED_DOOR) {
                    Door door = new Door(row, col, true, tile == Tile.LOCKED_DOOR);
                    doors.add(door);
                }
            }
        }
    }

    private boolean isDoorOpen(int row, int col) {
        return doors.stream().filter(door -> door.getRow() == row && door.getCol() == col).map(Door::isClosed).anyMatch(doorIsClosed -> ! doorIsClosed);
    }
}
