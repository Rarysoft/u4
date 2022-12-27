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

import com.rarysoft.u4.game.event.Event;
import com.rarysoft.u4.game.model.Character;
import com.rarysoft.u4.game.model.Status;
import com.rarysoft.u4.game.physics.ViewFinder;
import com.rarysoft.u4.game.physics.WayFinder;
import com.rarysoft.u4.game.state.GameState;
import com.rarysoft.u4.game.state.StateManager;
import com.rarysoft.u4.i18n.Messages;
import com.rarysoft.u4.game.npc.Conversation;
import com.rarysoft.u4.game.npc.Dialog;
import com.rarysoft.u4.game.npc.Dialogs;
import com.rarysoft.u4.game.npc.Person;
import com.rarysoft.u4.game.model.Location;

import java.util.*;

public class Game {
    private static final int VIEW_RADIUS = 9;

    private final StateManager stateManager;

    private final Messages messages;

    private final Maps maps;

    private final MapEnhancer mapEnhancer;

    private final Dialogs dialogs;

    private final Set<InformationListener> informationListeners;

    private final Random random;

    private final ViewFinder viewFinder;

    private final WayFinder wayFinder;

    private final Timer timer;

    private Map map;

    public Game(StateManager stateManager, Messages messages, Maps maps, MapEnhancer mapEnhancer, Dialogs dialogs, Random random, ViewFinder viewFinder, WayFinder wayFinder) {
        this.stateManager = stateManager;
        this.messages = messages;
        this.maps = maps;
        this.mapEnhancer = mapEnhancer;
        this.dialogs = dialogs;
        this.informationListeners = new HashSet<>();
        this.random = random;
        this.viewFinder = viewFinder;
        this.wayFinder = wayFinder;
        this.timer = new Timer("Main Timer");
    }

    public void addInformationListener(InformationListener informationListener) {
        informationListeners.add(informationListener);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void start() {
        this.map = maps.map(stateManager.gameState().getCurrentPartyLocation(), stateManager.gameState().getDungeonLevel());
        switchToMap(this.map);
        initializeCounter();
        initializeDisplay();
        updateBackground();
    }

    public void onFunctionKeyPressed(int function) {
        switch (function) {
            case 1:
                DevMode.toggleMapBrowsing();
                break;
            case 2:
                DevMode.toggleFullVisibility();
                break;
            default:
                break;
        }
    }

    public void onNorthPressed() {
        attemptMove(-1, 0, "north");
    }

    public void onNortheastPressed() {
        attemptMove(-1, 1, "northeast");
    }

    public void onEastPressed() {
        attemptMove(0, 1, "east");
    }

    public void onSoutheastPressed() {
        attemptMove(1, 1, "southeast");
    }

    public void onSouthPressed() {
        attemptMove(1, 0, "south");
    }

    public void onSouthwestPressed() {
        attemptMove(1, -1, "southwest");
    }

    public void onWestPressed() {
        attemptMove(0, -1, "west");
    }

    public void onNorthwestPressed() {
        attemptMove(-1, -1, "northwest");
    }

    public void onUserInput(char input) {
        GameState gameState = stateManager.gameState();
        if (gameState.getPersonConversingWith() != null) {
            if (input == '\n' || gameState.isRespondingToPerson()) {
                Dialog dialog = gameState.getDialog();
                Conversation conversation;
                if (gameState.isRespondingToPerson()) {
                    stateManager.changeState(state -> state.withAdditionalInput(input).withNotRespondingToPerson(), Event.COMMUNICATION_UPDATED);
                    conversation = new Conversation(dialog, gameState.getPlayer0().getName(), 0, 0, 0, 0, 0, 0, 0, 0).forResponse(gameState.getInput());
                }
                else {
                    conversation = new Conversation(dialog, gameState.getPlayer0().getName(), 0, 0, 0, 0, 0, 0, 0, 0).forInput(gameState.getInput());
                }
                conversation.response().ifPresent(response -> {
                    String question = conversation.question().orElse(null);
                    if (conversation.endConversation()) {
                        say(gameState.getInput(), response);
                        endConversation();
                    }
                    else {
                        sayAndPrompt(gameState.getInput(), response, question);
                        if (conversation.question().isPresent()) {
                            stateManager.changeState(GameState::withRespondingToPerson, Event.NONE);
                        }
                    }
                });
                stateManager.changeState(state -> state.withHumilityChanged(conversation.humilityDelta()), Event.NONE);
                if (conversation.healPlayer()) {
                    // TODO heal the party
                }
                stateManager.changeState(GameState::withNoInput, Event.NONE);
                afterPlayerMove();
            }
            else if (input == '\b') {
                stateManager.changeState(GameState::withoutPreviousAdditionalInput, Event.COMMUNICATION_UPDATED);
            }
            else {
                stateManager.changeState(state -> state.withAdditionalInput(input), Event.COMMUNICATION_UPDATED);
            }
        }
        else if (stateManager.gameState().getDoorInteractingWith() != null) {
            if (input == 'y') {
                stateManager.changeState(state -> state.withDoorInteractingWithUnlocked().withNoDoorInteractingWith().withKeysDecremented(), Event.DISPLAY_UPDATED);
                stateManager.changeState(state -> state.withMessage(messages.actionResponseUnlocked()), Event.COMMUNICATION_UPDATED);
            }
            else {
                stateManager.changeState(state -> state.withMessage(messages.actionResponseNotUnlocked()).withNoDoorInteractingWith(), Event.COMMUNICATION_UPDATED);
            }
        }
        else {
            if (input == ' ') {
                pass();
            }
        }
    }

    private void initializeCounter() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                stateManager.changeState(GameState::withCounterIncremented, Event.DISPLAY_UPDATED);
            }
        }, 0, 200);
    }

    private void switchToMap(Map map) {
        this.map = map;
        stateManager.changeState(state -> state.withCurrentPartyLocation(map.location()).withDungeonLevel(map.level()), Event.DISPLAY_UPDATED);
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
        Tile personTile = (row == stateManager.gameState().getRow() && col == stateManager.gameState().getCol()) ? Tile.AVATAR : map.personAt(row, col).map(Person::tileIndex).map(Tile::forIndex).orElse(null);
        return new RenderedTile().withBaseTile(groundTile).withBaseTile(tileToRender).withBaseTile(overlayTile).withTransientTile(personTile);
    }

    private void prepareDoors(Map map) {
        Set<Door> doors = new HashSet<>();
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
        stateManager.changeState(GameState::withNoDoors, Event.NONE);
    }

    private boolean isDoorOpen(int row, int col) {
        return stateManager.gameState().getDoors().stream().filter(door -> door.getRow() == row && door.getCol() == col).map(Door::isClosed).anyMatch(doorIsClosed -> ! doorIsClosed);
    }

    private boolean isDoorUnlocked(int row, int col) {
        return stateManager.gameState().getDoors().stream().filter(door -> door.getRow() == row && door.getCol() == col).map(Door::isLocked).anyMatch(doorIsLocked -> ! doorIsLocked);
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
        if (stateManager.gameState().getMoves() % Effects.TRAMMEL_CYCLE_LENGTH == 0) {
            stateManager.changeState(state -> state.withPhaseOfTrammelCycled(8), Event.DISPLAY_UPDATED);
        }
        if (stateManager.gameState().getMoves() % Effects.FELUCCA_CYCLE_LENGTH == 0) {
            stateManager.changeState(state -> state.withPhaseOfFeluccaCycled(8), Event.DISPLAY_UPDATED);
        }
    }

    private void updateWinds() {
        if (random.nextInt(100) < Effects.WIND_CHANGE_PERCENTAGE) {
            stateManager.changeState(state -> state.withWinds(random.nextInt(8)), Event.DISPLAY_UPDATED);
        }
    }

    private void pass() {
        stateManager.changeState(state -> state.withMessage(messages.actionPass()).withNoDoorInteractingWith(), Event.COMMUNICATION_UPDATED);
        afterPlayerMove();
    }

    private void attemptMove(int rowDelta, int colDelta, String actionDirection) {
        if (isEntirePartyIncapacitated()) {
            return;
        }
        if (DevMode.isMapBrowsingEnabled()) {
            stateManager.changeState(state -> state.withRowAndColChanged(rowDelta, colDelta), Event.DISPLAY_UPDATED);
            actionCompleted("Row: " + stateManager.gameState().getRow() + " Col: " + stateManager.gameState().getCol());
            return;
        }
        if (stateManager.gameState().getPersonConversingWith() != null) {
            abandonConversation();
        }
        GameState gameState = stateManager.gameState();
        int targetRow = gameState.getRow() + rowDelta;
        int targetCol = gameState.getCol() + colDelta;
        RenderedTile renderedTile = stateManager.gameState().getMapView()[targetRow][targetCol];
        Optional<Person> npc = map.personAt(targetRow, targetCol);
        if (renderedTile.baseTiles().isEmpty()) {
            actionCompleted(messages.actionMove(actionDirection));
            returnToSurface();
            actionCompleted(messages.actionExit());
            afterPlayerMove();
        }
        else {
            if (renderedTile.walkability() == 0) {
                Optional<Door> door = stateManager.gameState().getDoors().stream().filter(potentialDoor -> potentialDoor.getRow() == targetRow && potentialDoor.getCol() == targetCol).findAny();
                if (renderedTile.baseTiles().contains(Tile.UNLOCKED_DOOR)) {
                    door.ifPresent(doorToOpen -> doorToOpen.open(5));   // TODO: make this an normal state change
                    stateManager.changeState(state -> state.withMessage(messages.actionOpen(actionDirection)), Event.COMMUNICATION_UPDATED);
                }
                else if (renderedTile.baseTiles().contains(Tile.LOCKED_DOOR)) {
                    if (stateManager.gameState().getKeys() > 0) {
                        stateManager.changeState(state -> state.withDoorInteractingWith(door.orElse(null)).withRespondingToPerson().withMessage(messages.actionResponseLockedHaveKeys()), Event.COMMUNICATION_UPDATED);
                    }
                    else {
                        stateManager.changeState(state -> state.withMessage(messages.actionResponseLockedHaveNoKeys()), Event.COMMUNICATION_UPDATED);
                    }
                }
                else if (renderedTile.canTalkThrough()) {
                    Optional<Person> adjacentPerson = map.personAt(targetRow + rowDelta, targetCol + colDelta);
                    adjacentPerson.ifPresent(person -> attemptConversationWith(person, actionDirection));
                }
                else {
                    actionCompleted(messages.actionMove(actionDirection));
                    moveBlocked();
                }
                afterPlayerMove();
                return;
            }
            // Special case: don't allow northward exit from LB's castle
            if (rowDelta == -1 && map.at(stateManager.gameState().getRow(), stateManager.gameState().getCol()) == Tile.CASTLE_BRITANNIA_ENTRANCE) {
                actionCompleted(messages.actionMove(actionDirection));
                moveBlocked();
                afterPlayerMove();
                return;
            }
            // Special case: don't allow entry to LB's castle from the north
            if (rowDelta == 1 && renderedTile.baseTiles().contains(Tile.CASTLE_BRITANNIA_ENTRANCE)) {
                actionCompleted(messages.actionMove(actionDirection));
                moveBlocked();
                afterPlayerMove();
                return;
            }
            if (npc.isPresent()) {
                actionCompleted(messages.actionTalk(actionDirection));
                attemptConversationWith(npc.get(), actionDirection);
                afterPlayerMove();
                return;
            }
            if (! allowWalkTo(renderedTile)) {
                actionCompleted(messages.actionMove(actionDirection));
                moveSlowed();
                afterPlayerMove();
                return;
            }
            stateManager.changeState(state -> state.withRowAndCol(targetRow, targetCol), Event.DISPLAY_UPDATED);
            if (renderedTile.isPortal()) {
                actionCompleted(messages.actionMove(actionDirection));
                enterPortal();
                actionCompleted(messages.actionEnter(stateManager.gameState().getCurrentPartyLocation().displayName()));
                afterPlayerMove();
            }
            else {
                actionCompleted(messages.actionMove(actionDirection));
                afterPlayerMove();
            }
        }
    }

    private void attemptConversationWith(Person person, String actionDirection) {
        stateManager.changeState(state -> state.withMessage(messages.actionTalk(actionDirection)), Event.COMMUNICATION_UPDATED);
        Optional<Dialog> possibleConversation = dialogs.findCharacterConversationFor(stateManager.gameState().getCurrentPartyLocation().code(), person);
        if (possibleConversation.isPresent()) {
            Dialog dialog = possibleConversation.get();
            Conversation conversation = new Conversation(dialog, stateManager.gameState().getPlayer0().getName(), 0, 0, 0, 0, 0, 0, 0, 0);
            conversation.response().ifPresent(response -> {
                stateManager.changeState(state -> state.withDialog(dialog).withPersonConversingWith(person).withNoInput(), Event.COMMUNICATION_UPDATED);
                spokenTo("\n" + response + "\n\n" + messages.speechCitizenPrompt());
            });
            if (! conversation.response().isPresent()) {
                conversationIgnored();
            }
            afterPlayerMove();
            return;
        }
        moveBlocked();
        afterPlayerMove();
    }

    private void returnToSurface() {
        map = maps.surface();
        stateManager.changeState(state -> state.withCurrentPartyLocation(Location.SURFACE).withRowAndColFromDungeonRowAndCol(), Event.DISPLAY_UPDATED);
    }

    private void initializeDisplay() {
        informationListeners.forEach(InformationListener::initialize);
        updateCharacters();
    }

    private void updateCharacters() {
        List<Character> characters = charactersInParty();
        for (int index = 0; index < characters.size(); index ++) {
            Character character = characters.get(index);
            int characterIndex = index;
            informationListeners.forEach(informationListener -> informationListener.playerUpdated(characterIndex, character.getName(), String.format("%d%s", character.getHp(), character.getStatus().displayName())));
        }
    }

    private List<Character> charactersInParty() {
        List<Character> charactersInParty = new ArrayList<>();
        for (int index = 0; index < stateManager.gameState().getNumberOfCharacters(); index ++) {
            charactersInParty.add(
                    index == 0 ? stateManager.gameState().getPlayer0() :
                            index == 1 ? stateManager.gameState().getPlayer1() :
                                    index == 2 ? stateManager.gameState().getPlayer2() :
                                            index == 3 ? stateManager.gameState().getPlayer3() :
                                                    index == 4 ? stateManager.gameState().getPlayer4() :
                                                            index == 5 ? stateManager.gameState().getPlayer5() :
                                                                    index == 6 ? stateManager.gameState().getPlayer6() :
                                                                            stateManager.gameState().getPlayer7()
            );
        }
        return charactersInParty;
    }

    private void updateBackground() {
        stateManager.changeState(state -> state.withMapView(determinePlayerView(mapView())), Event.DISPLAY_UPDATED);
    }

    private RenderedTile[][] mapView() {
        int row = stateManager.gameState().getRow();
        int col = stateManager.gameState().getCol();
        Area<Tile> mapView = viewFinder.view(map.data(), map.surroundingTile(), VIEW_RADIUS, row, col);
        RenderedTile[][] view = new RenderedTile[mapView.rows()][mapView.cols()];
        for (int viewRow = 0; viewRow < mapView.rows(); viewRow ++) {
            for (int viewCol = 0; viewCol < mapView.cols(); viewCol ++) {
                int mapRow = row - VIEW_RADIUS + viewRow;
                int mapCol = col - VIEW_RADIUS + viewCol;
                RenderedTile renderedTile = renderedTile(map.data(), mapView.get(viewRow, viewCol), mapRow, mapCol);
                view[viewRow][viewCol] = renderedTile;
            }
        }
        return view;
    }

    private void actionCompleted(String message) {
        stateManager.changeState(state -> state.withMessage(message), Event.COMMUNICATION_UPDATED);
    }

    private void moveBlocked() {
        informationListeners.forEach(displayListener -> displayListener.actionCompleted(messages.actionResponseBlocked()));
    }

    private void moveSlowed() {
        informationListeners.forEach(displayListener -> displayListener.actionCompleted(messages.actionResponseSlowProgress()));
    }

    private void conversationIgnored() {
        informationListeners.forEach(displayListener -> displayListener.actionCompleted(messages.actionResponseIgnored()));
    }

    private void spokenTo(String text) {
        informationListeners.forEach(displayListener -> displayListener.responseRequested(text));
    }

    private void type(String input) {
        informationListeners.forEach(displayListener -> displayListener.inputReceived(input));
    }

    private void say(String input, String response) {
        spokenTo(input + "\n\n" + response + "\n\n");
    }

    private void sayAndPrompt(String input, String response, String question) {
        spokenTo(input + "\n\n" + response + "\n\n" + (question == null ? messages.speechCitizenPrompt() : question));
    }

    private void prompt(String question) {
        spokenTo(question);
    }

    private void abandonConversation() {
        Conversation conversation = new Conversation(stateManager.gameState().getDialog(), stateManager.gameState().getPlayer0().getName(), 0, 0, 0, 0, 0, 0, 0, 0).forInput("BYE");
        conversation.response().ifPresent(this::spokenTo);
        endConversation();
    }

    private void endConversation() {
        stateManager.changeState(state -> state.withNoDialog().withNoPersonConversingWith().withNoInput(), Event.COMMUNICATION_UPDATED);
    }

    private boolean allowWalkTo(RenderedTile tile) {
        if (tile.walkability() == 100) {
            return true;
        }
        return random.nextInt(100) < tile.walkability();
    }

    private void applyPoison() {
        // I don't know the actual algorithm used in the original U4, so this will have to suffice.
        // Each character will have a 20% chance of being poisoned.
        // Perhaps character level and/or stats should play into this in some way.....
        for (Character character : charactersInParty()) {
            if (character.getStatus() == Status.GOOD) {
                if (random.nextInt(100) < Effects.POISON_PERCENTAGE) {
                    character.setStatus(Status.POISONED);
                }
            }
        }
    }

    private void applySleep() {
        // I don't know the actual algorithm used in the original U4, so this will have to suffice.
        // Perhaps character level and/or stats should play into this in some way.....
        for (Character character : charactersInParty()) {
            if (character.getStatus() == Status.GOOD) {
                if (random.nextInt(100) < Effects.SLEEP_PERCENTAGE) {
                    character.setStatus(Status.SLEEPING);
                }
            }
        }
    }

    private void applyFire() {
        // I don't know the actual algorithm used in the original U4, so this will have to suffice.
        // Perhaps character level and/or stats and armour should play into this in some way.....
        for (Character character : charactersInParty()) {
            int damage = (random.nextInt(Effects.FIRE_DAMAGE_MAXIMUM - Effects.FIRE_DAMAGE_MINIMUM) + Effects.FIRE_DAMAGE_MINIMUM) + 1;
            applyDamage(character, damage);
        }
    }

    private void applyDamage(Character character, int damage) {
        int damageToApply = damage;
        int currentHp = character.getHp();
        if (currentHp < damage) {
            damageToApply = currentHp;
        }
        character.setHp(currentHp - damageToApply);
        if (character.getHp() == 0) {
            character.setStatus(Status.DEAD);
        }
    }

    private void applyTerrainEffects() {
        RenderedTile renderedTile = stateManager.gameState().getMapView()[stateManager.gameState().getRow()][stateManager.gameState().getCol()];
        if (renderedTile.baseTiles().contains(Tile.SWAMP) || renderedTile.baseTiles().contains(Tile.POISON_FIELD)) {
            applyPoison();
        }
        if (renderedTile.baseTiles().contains(Tile.SLEEP_FIELD)) {
            applySleep();
        }
        if (renderedTile.baseTiles().contains(Tile.FIRE_FIELD)) {
            applyFire();
        }
    }

    private void applyCharacterDamage() {
        for (Character character : charactersInParty()) {
            if (character.getStatus() == Status.POISONED) {
                applyDamage(character, Effects.POISON_DAMAGE_PER_TURN);
            }
        }
    }

    private void enterPortal() {
        int row = stateManager.gameState().getRow();
        int col = stateManager.gameState().getCol();
        if (stateManager.gameState().getCurrentPartyLocation() == Location.SURFACE) {
            Map map = maps.mapAt(row, col);
            switchToMap(map);
            stateManager.changeState(state -> state.withDungeonRowAndColFromRowAndCol().withRowAndCol(map.startRow(), map.startCol()), Event.DISPLAY_UPDATED);
        }
        else if (stateManager.gameState().getCurrentPartyLocation() == Location.CASTLE_BRITANNIA) {
            if (stateManager.gameState().getMapView()[row][col].baseTiles().contains(Tile.LADDER_DOWN)) {
                descend();
            }
            else {
                ascend();
            }
        }
        else {
            // if not on the surface or in LB's castle, the only other places with portals are the dungeons
            if (stateManager.gameState().getMapView()[row][col].baseTiles().contains(Tile.LADDER_DOWN)) {
                ascend(); // in dungeons, we go down to higher numbered levels
            }
            else {
                descend(); // in dungeons, we go up to lower numbered levels;
            }
        }
    }

    private void ascend() {
        int nextLevel = map.level() + 1;
        Map nextLevelMap = maps.map(map.location(), nextLevel);
        switchToMap(nextLevelMap);
        stateManager.changeState(state -> state.withDungeonLevel(nextLevel), Event.DISPLAY_UPDATED);
    }

    private void descend() {
        int nextLevel = map.level() - 1;
        if (nextLevel < 0) {
            // special case: there's a ladder in LB's castle that leads down to Hythloth
            if (stateManager.gameState().getCurrentPartyLocation() == Location.CASTLE_BRITANNIA && stateManager.gameState().getRow() == 2 && stateManager.gameState().getCol() == 7) {
                switchToMap(maps.map(Location.HYTHLOTH, 0));
                stateManager.changeState(state -> state.withRowAndCol(61, 61).withDungeonLevel(0), Event.DISPLAY_UPDATED);
            }
            else {
                returnToSurface();
            }
        }
        else {
            Map nextLevelMap = maps.map(map.location(), nextLevel);
            switchToMap(nextLevelMap);
            stateManager.changeState(state -> state.withDungeonLevel(nextLevel), Event.DISPLAY_UPDATED);
        }
    }

    private RenderedTile[][] determinePlayerView(RenderedTile[][] view) {
        int size = view.length;
        if (stateManager.gameState().getCurrentPartyLocation() == Location.SURFACE) {
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    if (!isInStandardView(row, col)) {    // || stateManager.gameState().isResurrecting()) {  TODO: reinstate this logic
                        if (!DevMode.isFullVisibilityEnabled()) {
                            view[row][col] = view[row][col].hidden();
                        }
                    }
                }
            }
        }
        Visibility visibility = new Visibility();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (view[row][col].render() && visibility.isVisibleFromCenter(view, Coordinate.forRowCol(row, col))) {
                    continue;
                }
                if (! DevMode.isFullVisibilityEnabled()) {
                    view[row][col] = view[row][col].hidden();
                }
            }
        }
        return view;
    }

    private boolean isInStandardView(int row, int col) {
        // Eliminate corners to make a circular view; this could be done mathematically, but brute force is simple
        switch (row) {
            case 0:
            case 18:
                return col > 7 && col < 11;
            case 1:
            case 17:
                return col > 5 && col < 13;
            case 2:
            case 16:
                return col > 3 && col < 15;
            case 3:
            case 15:
                return col > 2 && col < 16;
            case 4:
            case 5:
            case 14:
            case 13:
                return col > 1 && col < 17;
            case 6:
            case 7:
            case 12:
            case 11:
                return col > 0 && col < 18;
            case 8:
            case 9:
            case 10:
                return true;
            default:
                return false;
        }
    }

    private void afterPlayerMove() {
        stateManager.changeState(state -> state.withCounterIncremented().withMovesIncremented(), Event.DISPLAY_UPDATED);
        map.movePeople(new NpcMover(random, viewFinder, wayFinder), stateManager.gameState().getRow(), stateManager.gameState().getCol(), stateManager.gameState().getPersonConversingWith());
        stateManager.gameState().getDoors().forEach(door -> door.turnCompleted(stateManager.gameState().getRow() == door.getRow() && stateManager.gameState().getCol() == door.getCol()));  // TODO: ?
        updateMoonPhases();
        updateWinds();
        applyTerrainEffects();
        applyCharacterDamage();
        attemptToAwakenSleepingCharacters();
        updateCharacters();
        checkForAndHandlePartyDeath();
        checkForAndHandlePartySleeping();
        updateBackground();
    }

    private void attemptToAwakenSleepingCharacters() {
        for (Character character : charactersInParty()) {
            if (character.getStatus() == Status.SLEEPING) {
                if (random.nextInt(100) < Effects.SLEEP_PERCENTAGE) {
                    character.setStatus(Status.GOOD);
                }
            }
        }
    }

    private void checkForAndHandlePartyDeath() {
        if (isEntirePartyStatus(Status.DEAD)) {
            //initiateResurrection();   TODO: ?
            List<String> resurrectionMessages = this.messages.messageResurrection(stateManager.gameState().getPlayer0().getName());
            for (int index = 0; index < resurrectionMessages.size(); index ++) {
                boolean lastMessage = index == resurrectionMessages.size() - 1;
                String message = resurrectionMessages.get(index);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        informationListeners.forEach(displayListener -> displayListener.actionCompleted(message));
                        if (lastMessage) {
                            completeResurrection();
                            updateBackground();
                            updateCharacters();
                        }
                    }
                }, 2000L * index);
            }
        }
    }

    private void completeResurrection() {
        charactersInParty().forEach(character -> {
            character.setHp(character.getMaxHp());
            character.setStatus(Status.GOOD);
        });
        stateManager.changeState(state -> state.withCurrentPartyLocation(Location.CASTLE_BRITANNIA).withDungeonLevel(1).withRowAndCol(8, 19).withDungeonRowAndCol(map.worldRow(), map.worldCol()), Event.DISPLAY_UPDATED);
        switchToMap(maps.map(Location.CASTLE_BRITANNIA, 1));
        // TODO: remove some items from inventory
    }

    private void checkForAndHandlePartySleeping() {
        if (isEntirePartyStatus(Status.SLEEPING)) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    pass();
                    checkForAndHandlePartySleeping();
                }
            }, 500);
        }
    }

    private boolean isEntirePartyStatus(Status status) {
        for (Character character : charactersInParty()) {
            if (character.getStatus() != status) {
                return false;
            }
        }
        return true;
    }

    private boolean isEntirePartyIncapacitated() {
        for (Character character : charactersInParty()) {
            if (character.getStatus() != Status.SLEEPING && character.getStatus() != Status.DEAD) {
                return false;
            }
        }
        return true;
    }
}
