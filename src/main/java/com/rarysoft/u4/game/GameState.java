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

import com.rarysoft.u4.game.npc.Dialog;
import com.rarysoft.u4.game.npc.Person;
import com.rarysoft.u4.game.party.Character;
import com.rarysoft.u4.game.party.Location;
import com.rarysoft.u4.game.party.Party;
import com.rarysoft.u4.game.physics.ViewFinder;
import com.rarysoft.u4.game.physics.WayFinder;

import java.util.*;

public class GameState {
    private final Maps maps;
    private final Party party;

    private Map map;
    private PlayMode playMode;
    private Dialog dialog;
    private Person conversingPerson;
    private String input;

    private Set<Door> doors;

    public GameState(Maps maps, Party party) {
        this.maps = maps;
        this.map = maps.map(party.getCurrentPartyLocation(), party.getDungeonLevel());
        this.party = party;
        this.playMode = PlayMode.NORMAL;
        switchToMap(this.map);
    }

    public String playerName() {
        return party.getPlayer0().getName();
    }

    public List<Character> charactersInParty() {
        List<Character> charactersInParty = new ArrayList<>();
        for (int index = 0; index < party.getNumberOfCharacters(); index ++) {
            charactersInParty.add(
                    index == 0 ? party.getPlayer0() :
                            index == 1 ? party.getPlayer1() :
                                    index == 2 ? party.getPlayer2() :
                                            index == 3 ? party.getPlayer3() :
                                                    index == 4 ? party.getPlayer4() :
                                                            index == 5 ? party.getPlayer5() :
                                                                    index == 6 ? party.getPlayer6() :
                                                                            party.getPlayer7()
            );
        }
        return charactersInParty;
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

    public boolean inConversation() {
        return playMode == PlayMode.CONVERSATION || playMode == PlayMode.CONVERSATION_QUERIED;
    }

    public boolean inConversationRespondingYesOrNo() {
        return playMode == PlayMode.CONVERSATION_QUERIED;
    }

    public Dialog dialog() {
        return dialog;
    }

    public String input() {
        return input;
    }

    public Location location() {
        return party.getCurrentPartyLocation();
    }

    public int phaseOfTrammel() {
        return party.getPhaseOfTrammel();
    }

    public int phaseOfFelucca() {
        return party.getPhaseOfFelucca();
    }

    public int windDirection() {
        return party.getWinds();
    }

    public RenderedTile[][] mapView(ViewFinder viewFinder, int radius) {
        int row = party.getRow();
        int col = party.getCol();
        Area<Tile> mapView = viewFinder.view(map.data(), map.surroundingTile(), radius, row, col);
        RenderedTile[][] view = new RenderedTile[mapView.rows()][mapView.cols()];
        for (int viewRow = 0; viewRow < mapView.rows(); viewRow ++) {
            for (int viewCol = 0; viewCol < mapView.cols(); viewCol ++) {
                int mapRow = row - radius + viewRow;
                int mapCol = col - radius + viewCol;
                RenderedTile renderedTile = renderedTile(mapView.get(viewRow, viewCol), mapRow, mapCol);
                view[viewRow][viewCol] = renderedTile;
            }
        }
        return view;
    }

    public RenderedTile tileAt(int row, int col) {
        return renderedTile(map.at(row, col), row, col);
    }

    public Optional<Person> personAt(int row, int col) {
        return map.personAt(row, col);
    }

    public void enter() {
        int row = party.getRow();
        int col = party.getCol();
        Map map = maps.mapAt(row, col);
        switchToMap(map);
        party.setDungeonRow(row);
        party.setDungeonCol(col);
        party.setRow(map.startRow());
        party.setCol(map.startCol());
    }

    public void ascend() {
        int nextLevel = map.level() + 1;
        Map nextLevelMap = maps.map(map.location(), nextLevel);
        switchToMap(nextLevelMap);
        party.setDungeonLevel(nextLevel);
    }

    public void descend() {
        int nextLevel = map.level() - 1;
        if (nextLevel < 0) {
            returnToSurface();
        }
        else {
            Map nextLevelMap = maps.map(map.location(), nextLevel);
            switchToMap(nextLevelMap);
            party.setDungeonLevel(nextLevel);
        }
    }

    public void returnToSurface() {
        map = maps.surface();
        party.setCurrentPartyLocation(Location.SURFACE);
        party.setRow(party.getDungeonRow());
        party.setCol(party.getDungeonCol());
    }

    public void postTurnUpdates(Random random, ViewFinder viewFinder, WayFinder wayFinder) {
        party.setCounter(party.getCounter() + 1);
        party.setMoves(party.getMoves() + 1);
        updateMoonPhases();
        map.movePeople(new NpcMover(random, viewFinder, wayFinder), party.getRow(), party.getCol(), conversingPerson);
        doors.forEach(Door::turnCompleted);
    }

    public void startConversation(Dialog dialog, Person person) {
        playMode = PlayMode.CONVERSATION;
        this.dialog = dialog;
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
        if (! this.input.isEmpty()) {
            this.input = this.input.substring(0, this.input.length() - 1);
        }
    }

    public void resetInput() {
        input = "";
    }

    public void endConversation() {
        playMode = PlayMode.NORMAL;
        this.dialog = null;
        this.conversingPerson = null;
        input = null;
    }

    public void openDoor(int row, int col) {
        doors.stream().filter(door -> door.getRow() == row && door.getCol() == col).findAny().ifPresent(door -> door.open(5));
    }

    public void adjustKarma(Virtue virtue, int delta) {
        switch (virtue) {
            case HONESTY:
                party.setHonesty(party.getHonesty() + delta);
                break;
            case COMPASSION:
                party.setCompassion(party.getCompassion() + delta);
                break;
            case VALOUR:
                party.setValour(party.getValour() + delta);
                break;
            case JUSTICE:
                party.setJustice(party.getJustice() + delta);
                break;
            case SACRIFICE:
                party.setSacrifice(party.getSacrifice() + delta);
                break;
            case HONOUR:
                party.setHonour(party.getHonour() + delta);
                break;
            case SPIRITUALITY:
                party.setSpirituality(party.getSpirituality() + delta);
                break;
            case HUMILITY:
                party.setHumility(party.getHumility() + delta);
                break;
            default:
                break;
        }
    }

    private void switchToMap(Map map) {
        this.map = map;
        party.setCurrentPartyLocation(map.location());
        party.setDungeonLevel(map.level());
        prepareDoors(map);
    }

    private RenderedTile renderedTile(Tile tile, int row, int col) {
        Tile tileToRender = tile;
        if (tile == Tile.UNLOCKED_DOOR || tile == Tile.LOCKED_DOOR) {
            if (isDoorOpen(row, col)) {
                tileToRender = Tile.BRICK_FLOOR;
            }
        }
        Tile personTile = map.personAt(row, col).map(Person::tileIndex).map(Tile::forIndex).orElse(null);
        return new RenderedTile(tileToRender, personTile);
    }

    private void prepareDoors(Map map) {
        doors = new HashSet<>();
        if (map.location() == Location.SURFACE) {
            // there are no doors in the surface map
            return;
        }
        Tile[][] tiles = map.data();
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

    private void updateMoonPhases() {
        if (party.getMoves() % 24 == 0) {
            party.setPhaseOfTrammel((party.getPhaseOfTrammel() + 1) % 8);
        }
        if (party.getMoves() % 8 == 0) {
            party.setPhaseOfFelucca((party.getPhaseOfFelucca() + 1) % 8);
        }
    }
}
