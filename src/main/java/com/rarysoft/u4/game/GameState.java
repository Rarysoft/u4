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
import com.rarysoft.u4.game.party.Status;
import com.rarysoft.u4.game.physics.ViewFinder;
import com.rarysoft.u4.game.physics.WayFinder;

import java.util.*;

public class GameState {
    private final MapEnhancer mapEnhancer;
    private final Maps maps;
    private final Party party;

    private Map map;
    private PlayMode playMode;
    private Dialog dialog;
    private Person conversingPerson;
    private String input;

    private Set<Door> doors;
    private int targetRow;
    private int targetCol;

    public GameState(MapEnhancer mapEnhancer, Maps maps, Party party) {
        this.mapEnhancer = mapEnhancer;
        this.maps = maps;
        this.map = maps.map(party.getCurrentPartyLocation(), party.getDungeonLevel());
        this.party = party;
        this.party.setKeys(1);
        this.playMode = PlayMode.NORMAL;
        switchToMap(this.map);
    }

    public String playerName() {
        return party.getPlayer0().getName();
    }

    public List<Character> charactersInParty() {
        return allActiveCharacters();
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

    public PlayMode playMode() {
        return playMode;
    }

    public void setPlayMode(PlayMode playMode) {
        this.playMode = playMode;
    }

    public void queryUnlockDoor(int row, int col) {
        playMode = PlayMode.UNLOCK_QUERIED;
        targetRow = row;
        targetCol = col;
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
                RenderedTile renderedTile = renderedTile(map.data(), mapView.get(viewRow, viewCol), mapRow, mapCol);
                view[viewRow][viewCol] = renderedTile;
            }
        }
        return view;
    }

    public RenderedTile tileAt(int row, int col) {
        return renderedTile(map.data(), map.at(row, col), row, col);
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
            // special case: there's a ladder in LB's castle that leads down to Hythloth
            if (location() == Location.CASTLE_BRITANNIA && party.getRow() == 2 && party.getCol() == 7) {
                switchToMap(maps.map(Location.HYTHLOTH, 0));
                party.setRow(61);
                party.setCol(61);
                party.setDungeonLevel(0);
            }
            else {
                returnToSurface();
            }
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
        doors.forEach(door -> door.turnCompleted(door.getRow() == party.getRow() && door.getCol() == party.getCol()));
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

    public boolean hasKey() {
        return party.getKeys() > 0;
    }

    public void openDoor(int row, int col) {
        doors.stream().filter(door -> door.getRow() == row && door.getCol() == col).findAny().ifPresent(door -> door.open(5));
    }

    public void unlockDoor() {
        doors.stream().filter(door -> door.getRow() == targetRow && door.getCol() == targetCol).findAny().ifPresent(Door::unlock);
        party.setKeys(party.getKeys() - 1);
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

    public void initiateResurrection() {
        this.playMode = PlayMode.DEAD;
    }

    public void completeResurrection() {
        allActiveCharacters().forEach(character -> {
            character.setHp(character.getMaxHp());
            character.setStatus(Status.GOOD);
        });
        party.setCurrentPartyLocation(Location.CASTLE_BRITANNIA);
        party.setDungeonLevel(1);
        party.setRow(8);
        party.setCol(19);
        switchToMap(maps.map(Location.CASTLE_BRITANNIA, 1));
        party.setDungeonRow(map.worldRow());
        party.setDungeonCol(map.worldCol());
        // TODO: remove some items from inventory
        playMode = PlayMode.NORMAL;
    }

    public boolean isResurrecting() {
        return this.playMode == PlayMode.DEAD;
    }

    private List<Character> allActiveCharacters() {
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

    private void switchToMap(Map map) {
        this.map = map;
        party.setCurrentPartyLocation(map.location());
        party.setDungeonLevel(map.level());
        prepareDoors(map);
    }

    private RenderedTile renderedTile(Tile[][] data, Tile tile, int row, int col) {
        Tile tileToRender = mapEnhancer.replacementTile(map, row, col).orElse(tile);
        if (tile == Tile.UNLOCKED_DOOR || tile == Tile.LOCKED_DOOR) {
            if (isDoorOpen(row, col)) {
                tileToRender = Tile.BRICK_FLOOR;
            }
            else if (isDoorUnlocked(row, col)) {
                tileToRender = Tile.UNLOCKED_DOOR;
            }
        }
        Tile overlayTile = mapEnhancer.overlayTile(map, data, tileToRender, row, col).orElse(null);
        Tile groundTile = (tileToRender != null && tileToRender.isRenderedAtopNearbyGroundTile()) ? guessGroundTile(data, row, col) : null;
        Tile personTile = (row == party.getRow() && col == party.getCol()) ? Tile.AVATAR : map.personAt(row, col).map(Person::tileIndex).map(Tile::forIndex).orElse(null);
        return new RenderedTile().withBaseTile(groundTile).withBaseTile(tileToRender).withBaseTile(overlayTile).withTransientTile(personTile);
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

    private boolean isDoorUnlocked(int row, int col) {
        return doors.stream().filter(door -> door.getRow() == row && door.getCol() == col).map(Door::isLocked).anyMatch(doorIsLocked -> ! doorIsLocked);
    }

    private Tile guessGroundTile(Tile[][] background, int row, int col) {
        java.util.List<Tile> groundTiles = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        for (int surroundingRow = (row >= 0 ? row - 1 : row); surroundingRow < (row + 1 < background.length ? row + 1 : row) + 1; surroundingRow ++) {
            for (int surroundingCol = (col >= 0 ? col - 1 : col); surroundingCol < (col + 1 < background[surroundingRow].length ? col + 1 : col) + 1; surroundingCol ++) {
                Tile tile = Optional.ofNullable(background[surroundingRow][surroundingCol]).orElse(Tile.GRASSLANDS);
                if (tile.isGroundTile()) {
                    int tileIndex = groundTiles.indexOf(tile);
                    if (tileIndex > -1) {
                        counts.set(tileIndex, counts.get(tileIndex) + 1);
                    }
                    else {
                        groundTiles.add(tile);
                        counts.add(1);
                    }
                }
            }
        }
        int highestCount = counts.stream().mapToInt(count -> count).max().orElse(0);
        if (highestCount == 0) {
            return null;
        }
        return groundTiles.get(counts.indexOf(highestCount));
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
