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

import com.rarysoft.u4.i18n.Messages;
import com.rarysoft.u4.model.npc.Conversation;
import com.rarysoft.u4.model.npc.Dialog;
import com.rarysoft.u4.model.npc.Dialogs;
import com.rarysoft.u4.model.npc.Person;
import com.rarysoft.u4.model.party.Location;

import javax.swing.Timer;
import java.util.*;

public class Game {
    // Note: view radius is one larger than what will actually be shown on screen, which allows the display to do
    // some necessary analysis on the tiles around each visible tile
    private static final int VIEW_RADIUS = 10;

    private final Messages messages;

    private final Dialogs dialogs;

    private final Set<InformationListener> informationListeners;

    private final Set<ViewListener> viewListeners;

    private final Random random;

    private final ViewFinder viewFinder;

    private final WayFinder wayFinder;

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
            if (input == '\n') {
                Dialog dialog = gameState.dialog();
                Conversation conversation;
                if (gameState.inConversationRespondingYesOrNo()) {
                    conversation = new Conversation(dialog, gameState.playerName(), 0, 0, 0, 0, 0, 0, 0, 0).forResponse(gameState.input());
                    gameState.queryAnswered();
                }
                else {
                    conversation = new Conversation(dialog, gameState.playerName(), 0, 0, 0, 0, 0, 0, 0, 0).forInput(gameState.input());
                }
                conversation.response().ifPresent(response -> {
                    String question = conversation.question().orElse(null);
                    sayAndPrompt(gameState.input(), response, question);
                });
                if (conversation.question().isPresent()) {
                    gameState.playerQueried();
                }
                gameState.adjustKarma(Virtue.HUMILITY, conversation.humilityDelta());
                if (conversation.healPlayer()) {
                    // TODO heal the party
                }
                if (! conversation.response().isPresent()) {
                    endConversation();
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
        new Timer(200, event -> {
            animationCycle = animationCycle + 1 < 16 ? animationCycle + 1 : 0;
            updateBackground();
        }).start();
    }

    private void attemptMove(int rowDelta, int colDelta, String actionDirection) {
        if (gameState.inConversation()) {
            abandonConversation();
        }
        RenderedTile renderedTile = gameState.tileAt(gameState.row() + rowDelta, gameState.col() + colDelta);
        Optional<Person> npc = gameState.personAt(gameState.row() + rowDelta, gameState.col() + colDelta);
        if (renderedTile.tile() == null) {
            actionCompleted(messages.actionMove(actionDirection));
            gameState.returnToSurface();
            actionCompleted(messages.actionExit());
            afterPlayerMove();
        }
        else {
            if (renderedTile.tile().walkability() == 0) {
                if (renderedTile.tile() == Tile.UNLOCKED_DOOR) {
                    gameState.openDoor(gameState.row() + rowDelta, gameState.col() + colDelta);
                    actionCompleted(messages.actionOpen(actionDirection));
                }
                else if (renderedTile.tile().canTalkThrough()) {
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
            if (rowDelta == -1 && gameState.tileAt(gameState.row(), gameState.col()).tile() == Tile.CASTLE_BRITANNIA_ENTRANCE) {
                actionCompleted(messages.actionMove(actionDirection));
                moveBlocked();
                afterPlayerMove();
                return;
            }
            // Special case: don't allow entry to LB's castle from the north
            if (rowDelta == 1 && renderedTile.tile() == Tile.CASTLE_BRITANNIA_ENTRANCE) {
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
            if (renderedTile.tile().isPortal()) {
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
        informationListeners.forEach(informationListener -> informationListener.playerUpdated(0, gameState.playerName(), String.format("%d%s", gameState.playerHitPoints(), gameState.playerStatus().displayName())));
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

    private boolean allowWalkTo(RenderedTile renderedTile) {
        if (renderedTile.tile().walkability() == 100) {
            return true;
        }
        return random.nextInt(100) < renderedTile.tile().walkability();
    }

    private void enterPortal() {
        int row = gameState.row();
        int col = gameState.col();
        if (gameState.location() == Location.SURFACE) {
            gameState.enter();
        }
        else if (gameState.location() == Location.CASTLE_BRITANNIA) {
            if (gameState.tileAt(row, col).tile() == Tile.LADDER_DOWN) {
                // TODO: need to check if this is the ladder down to Hythloth
                gameState.descend();
            }
            else {
                gameState.ascend();
            }
        }
        else {
            // if not on the surface or in LB's castle, the only other places with portals are the dungeons
            if (gameState.tileAt(row, col).tile() == Tile.LADDER_DOWN) {
                // TODO: need to confirm with the user that they want to use the ladder
                gameState.ascend(); // in dungeons, we go down to higher numbered levels
            }
            else {
                gameState.descend(); // in dungeons, we go up to lower numbered levels;
            }
            // TODO: what about ladders going up and down at the same time?
        }
    }

    private RenderedTile[][] determinePlayerView(RenderedTile[][] view) {
        int size = view.length;
        for (int row = 0; row < size; row ++) {
            for (int col = 0; col < size; col ++) {
                if (! isInStandardView(row, col)) {
                    view[row][col] = view[row][col].hidden();
                }
            }
        }
        Visibility visibility = new Visibility();
        for (int row = 0; row < size; row ++) {
            for (int col = 0; col < size; col ++) {
                if (view[row][col].render() && visibility.isVisibleFromCenter(tileView(view), Coordinate.forRowCol(row, col))) {
                    continue;
                }
                view[row][col] = view[row][col].hidden();
            }
        }
        return view;
    }

    private Tile[][] tileView(RenderedTile[][] view) {
        Tile[][] tileView = new Tile[view.length][view[0].length];
        for (int row = 0; row < view.length; row ++) {
            for (int col = 0; col < view[row].length; col ++) {
                tileView[row][col] = view[row][col].tile();
            }
        }
        return tileView;
    }

    private boolean isInStandardView(int row, int col) {
        // Eliminate corners to make a circular view; this could be done mathematically, but brute force is simple
        switch (row) {
            case 1:
            case 19:
                return col > 8 && col < 12;
            case 2:
            case 18:
                return col > 6 && col < 14;
            case 3:
            case 17:
                return col > 4 && col < 16;
            case 4:
            case 16:
                return col > 3 && col < 17;
            case 5:
            case 6:
            case 15:
            case 14:
                return col > 2 && col < 18;
            case 7:
            case 8:
            case 13:
            case 12:
                return col > 1 && col < 19;
            case 9:
            case 10:
            case 11:
                return true;
            default:
                return false;
        }
    }

    private void afterPlayerMove() {
        gameState.postTurnUpdates(random, viewFinder, wayFinder);
        updateBackground();
    }
}
