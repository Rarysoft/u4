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

import com.rarysoft.u4.game.party.Character;
import com.rarysoft.u4.game.party.Status;
import com.rarysoft.u4.game.physics.ViewFinder;
import com.rarysoft.u4.game.physics.WayFinder;
import com.rarysoft.u4.i18n.Messages;
import com.rarysoft.u4.game.npc.Conversation;
import com.rarysoft.u4.game.npc.Dialog;
import com.rarysoft.u4.game.npc.Dialogs;
import com.rarysoft.u4.game.npc.Person;
import com.rarysoft.u4.game.party.Location;

import java.util.*;

public class Game {
    private static final int VIEW_RADIUS = 9;

    private final Messages messages;

    private final Dialogs dialogs;

    private final Set<InformationListener> informationListeners;

    private final Set<ViewListener> viewListeners;

    private final Random random;

    private final ViewFinder viewFinder;

    private final WayFinder wayFinder;

    private final Timer timer;

    private GameState gameState;

    private int animationCycle;

    public Game(Messages messages, Dialogs dialogs, Random random, ViewFinder viewFinder, WayFinder wayFinder) {
        this.messages = messages;
        this.dialogs = dialogs;
        this.informationListeners = new HashSet<>();
        this.viewListeners = new HashSet<>();
        this.random = random;
        this.viewFinder = viewFinder;
        this.wayFinder = wayFinder;
        this.timer = new Timer("Main Timer");
    }

    public void addInformationListener(InformationListener informationListener) {
        informationListeners.add(informationListener);
    }

    public void addViewListener(ViewListener viewListener) {
        viewListeners.add(viewListener);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void start(GameState gameState) {
        this.gameState = gameState;
        initializeAnimation();
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
        if (gameState.inConversation()) {
            if (input == '\n' || gameState.inConversationRespondingYesOrNo()) {
                Dialog dialog = gameState.dialog();
                Conversation conversation;
                if (gameState.inConversationRespondingYesOrNo()) {
                    gameState.addToOngoingInput(input);
                    conversation = new Conversation(dialog, gameState.playerName(), 0, 0, 0, 0, 0, 0, 0, 0).forResponse(gameState.input());
                    gameState.queryAnswered();
                }
                else {
                    conversation = new Conversation(dialog, gameState.playerName(), 0, 0, 0, 0, 0, 0, 0, 0).forInput(gameState.input());
                }
                conversation.response().ifPresent(response -> {
                    String question = conversation.question().orElse(null);
                    if (conversation.endConversation()) {
                        say(gameState.input(), response);
                        endConversation();
                    }
                    else {
                        sayAndPrompt(gameState.input(), response, question);
                        if (conversation.question().isPresent()) {
                            gameState.playerQueried();
                        }
                    }
                });
                gameState.adjustKarma(Virtue.HUMILITY, conversation.humilityDelta());
                if (conversation.healPlayer()) {
                    // TODO heal the party
                }
                gameState.resetInput();
                afterPlayerMove();
            }
            else if (input == '\b') {
                gameState.revertLastInput();
            }
            else {
                gameState.addToOngoingInput(input);
            }
            type(gameState.input());
        }
    }

    private void initializeAnimation() {
        animationCycle = 0;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                animationCycle = animationCycle + 1 < 16 ? animationCycle + 1 : 0;
                updateBackground();
            }
        }, 0, 200);
    }

    private void attemptMove(int rowDelta, int colDelta, String actionDirection) {
        if (DevMode.isMapBrowsingEnabled()) {
            gameState.changeRow(rowDelta);
            gameState.changeCol(colDelta);
            actionCompleted("Row: " + gameState.row() + " Col: " + gameState.col());
            return;
        }
        if (gameState.isResurrecting()) {
            return;
        }
        if (gameState.inConversation()) {
            abandonConversation();
        }
        RenderedTile renderedTile = gameState.tileAt(gameState.row() + rowDelta, gameState.col() + colDelta);
        Optional<Person> npc = gameState.personAt(gameState.row() + rowDelta, gameState.col() + colDelta);
        if (renderedTile.baseTiles().isEmpty()) {
            actionCompleted(messages.actionMove(actionDirection));
            gameState.returnToSurface();
            actionCompleted(messages.actionExit());
            afterPlayerMove();
        }
        else {
            if (renderedTile.walkability() == 0) {
                if (renderedTile.baseTiles().contains(Tile.UNLOCKED_DOOR)) {
                    gameState.openDoor(gameState.row() + rowDelta, gameState.col() + colDelta);
                    actionCompleted(messages.actionOpen(actionDirection));
                }
                else if (renderedTile.canTalkThrough()) {
                    Optional<Person> adjacentPerson = gameState.personAt(gameState.row() + rowDelta + rowDelta, gameState.col() + colDelta + colDelta);
                    adjacentPerson.ifPresent(person -> {
                        actionCompleted(messages.actionTalk(actionDirection));
                        attemptConversationWith(person);
                    });
                }
                else {
                    actionCompleted(messages.actionMove(actionDirection));
                    moveBlocked();
                }
                afterPlayerMove();
                return;
            }
            // Special case: don't allow northward exit from LB's castle
            if (rowDelta == -1 && gameState.tileAt(gameState.row(), gameState.col()).baseTiles().contains(Tile.CASTLE_BRITANNIA_ENTRANCE)) {
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
                attemptConversationWith(npc.get());
                afterPlayerMove();
                return;
            }
            if (! allowWalkTo(renderedTile)) {
                actionCompleted(messages.actionMove(actionDirection));
                moveSlowed();
                afterPlayerMove();
                return;
            }
            gameState.changeRow(rowDelta);
            gameState.changeCol(colDelta);
            if (renderedTile.isPortal()) {
                actionCompleted(messages.actionMove(actionDirection));
                enterPortal();
                actionCompleted(messages.actionEnter(gameState.location().displayName()));
                afterPlayerMove();
            }
            else {
                actionCompleted(messages.actionMove(actionDirection));
                afterPlayerMove();
            }
        }
    }

    private void attemptConversationWith(Person person) {
        Optional<Dialog> possibleConversation = dialogs.findCharacterConversationFor(gameState.location().code(), person);
        if (possibleConversation.isPresent()) {
            Dialog dialog = possibleConversation.get();
            Conversation conversation = new Conversation(dialog, gameState.playerName(), 0, 0, 0, 0, 0, 0, 0, 0);
            conversation.response().ifPresent(response -> {
                gameState.startConversation(dialog, person);
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

    private void initializeDisplay() {
        informationListeners.forEach(InformationListener::initialize);
        updateCharacters();
    }

    private void updateCharacters() {
        List<Character> characters = gameState.charactersInParty();
        for (int index = 0; index < characters.size(); index ++) {
            Character character = characters.get(index);
            int characterIndex = index;
            informationListeners.forEach(informationListener -> informationListener.playerUpdated(characterIndex, character.getName(), String.format("%d%s", character.getHp(), character.getStatus().displayName())));
        }
    }

    private void updateBackground() {
        RenderedTile[][] playerView = determinePlayerView(gameState.mapView(viewFinder, VIEW_RADIUS));
        viewListeners.forEach(viewListener -> viewListener.backgroundUpdated(playerView, animationCycle));
        informationListeners.forEach(informationListener -> informationListener.environmentUpdated(gameState.phaseOfTrammel(), gameState.phaseOfFelucca(), gameState.windDirection()));
    }

    private void actionCompleted(String message) {
        informationListeners.forEach(displayListener -> displayListener.actionCompleted(message));
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

    private void abandonConversation() {
        Conversation conversation = new Conversation(gameState.dialog(), gameState.playerName(), 0, 0, 0, 0, 0, 0, 0, 0).forInput("BYE");
        conversation.response().ifPresent(this::spokenTo);
        endConversation();
    }

    private void endConversation() {
        gameState.endConversation();
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
        for (Character character : gameState.charactersInParty()) {
            if (character.getStatus() == Status.GOOD) {
                if (random.nextInt(100) < 20) {
                    character.setStatus(Status.POISONED);
                }
            }
        }
    }

    private void applySleep() {
        // I don't know the actual algorithm used in the original U4, so this will have to suffice.
        // Perhaps character level and/or stats should play into this in some way.....
        for (Character character : gameState.charactersInParty()) {
            if (character.getStatus() == Status.GOOD) {
                if (random.nextInt(100) < Effects.SLEEP_PERCENTAGE) {
                    character.setStatus(Status.SLEEPING);
                }
            }
        }
    }

    private void applyFire() {
        // I don't know the actual algorithm used in the original U4, so this will have to suffice.
        // Perhaps character level and/or stats should play into this in some way.....
        for (Character character : gameState.charactersInParty()) {
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
        RenderedTile renderedTile = gameState.tileAt(gameState.row(), gameState.col());
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
        for (Character character : gameState.charactersInParty()) {
            if (character.getStatus() == Status.POISONED) {
                applyDamage(character, Effects.POISON_DAMAGE_PER_TURN);
            }
        }
    }

    private void enterPortal() {
        int row = gameState.row();
        int col = gameState.col();
        if (gameState.location() == Location.SURFACE) {
            gameState.enter();
        }
        else if (gameState.location() == Location.CASTLE_BRITANNIA) {
            if (gameState.tileAt(row, col).baseTiles().contains(Tile.LADDER_DOWN)) {
                gameState.descend();
            }
            else {
                gameState.ascend();
            }
        }
        else {
            // if not on the surface or in LB's castle, the only other places with portals are the dungeons
            if (gameState.tileAt(row, col).baseTiles().contains(Tile.LADDER_DOWN)) {
                gameState.ascend(); // in dungeons, we go down to higher numbered levels
            }
            else {
                gameState.descend(); // in dungeons, we go up to lower numbered levels;
            }
        }
    }

    private RenderedTile[][] determinePlayerView(RenderedTile[][] view) {
        int size = view.length;
        if (gameState.location() == Location.SURFACE) {
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    if (!isInStandardView(row, col) || gameState.isResurrecting()) {
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
        gameState.postTurnUpdates(random, viewFinder, wayFinder);
        applyTerrainEffects();
        applyCharacterDamage();
        updateCharacters();
        checkForAndHandlePartyDeath();
        updateBackground();
    }

    private void checkForAndHandlePartyDeath() {
        for (Character character : gameState.charactersInParty()) {
            if (character.getStatus() != Status.DEAD) {
                return;
            }
        }
        gameState.initiateResurrection();
        List<String> resurrectionMessages = this.messages.messageResurrection(gameState.playerName());
        for (int index = 0; index < resurrectionMessages.size(); index ++) {
            boolean lastMessage = index == resurrectionMessages.size() - 1;
            String message = resurrectionMessages.get(index);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    informationListeners.forEach(displayListener -> displayListener.actionCompleted(message));
                    if (lastMessage) {
                        gameState.completeResurrection();
                        updateBackground();
                        updateCharacters();
                    }
                }
            }, 2000L * index);
        }
    }
}
