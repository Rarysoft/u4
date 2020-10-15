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
import com.rarysoft.u4.model.npc.*;
import com.rarysoft.u4.model.graphics.*;

import javax.swing.Timer;
import java.util.*;

public class Game {
    // Note: view radius is one larger than what will actually be shown on screen, which allows the display to do
    // some necessary analysis on the tiles around each visible tile
    private static final int VIEW_RADIUS = 10;

    private final Messages messages;

    private final Dialogs dialogs;

    private final Set<DisplayListener> displayListeners;

    private final Random random;

    private GameState gameState;

    private int animationCycle;

    public Game(Messages messages, Dialogs dialogs) {
        this.messages = messages;
        this.dialogs = dialogs;
        this.displayListeners = new HashSet<>();
        this.random = new Random();
    }

    public void addDisplayListener(DisplayListener displayListener) {
        displayListeners.add(displayListener);
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
        if (gameState.inConversation()) {
            endConversation();
        }
        attemptMove(-1, 0, "north");
    }

    public void onNortheastPressed() {
        if (gameState.inConversation()) {
            endConversation();
        }
        attemptMove(-1, 1, "northeast");
    }

    public void onEastPressed() {
        if (gameState.inConversation()) {
            endConversation();
        }
        attemptMove(0, 1, "east");
    }

    public void onSoutheastPressed() {
        if (gameState.inConversation()) {
            endConversation();
        }
        attemptMove(1, 1, "southeast");
    }

    public void onSouthPressed() {
        if (gameState.inConversation()) {
            endConversation();
        }
        attemptMove(1, 0, "south");
    }

    public void onSouthwestPressed() {
        if (gameState.inConversation()) {
            endConversation();
        }
        attemptMove(1, -1, "southwest");
    }

    public void onWestPressed() {
        if (gameState.inConversation()) {
            endConversation();
        }
        attemptMove(0, -1, "west");
    }

    public void onNorthwestPressed() {
        if (gameState.inConversation()) {
            endConversation();
        }
        attemptMove(-1, -1, "northwest");
    }

    public void onUserInput(char input) {
        if (gameState.inConversation()) {
            if (input == '\n') {
                Dialog dialog = gameState.conversation();
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
                conversation.affectedVirtue().ifPresent(virtue -> {
                    // TODO check conversation.virtueDelta() and apply accordingly
                });
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
        RenderedTile renderedTile = gameState.tileAt(gameState.row() + rowDelta, gameState.col() + colDelta);
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
                    RenderedTile adjacentTile = gameState.tileAt(gameState.row() + rowDelta + rowDelta, gameState.col() + colDelta + colDelta);
                    if (adjacentTile.person().isPresent()) {
                        actionCompleted(messages.actionTalk(actionDirection));
                        attemptConversationWith(adjacentTile.person().get());
                    }
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
            if (renderedTile.person().isPresent()) {
                actionCompleted(messages.actionTalk(actionDirection));
                attemptConversationWith(renderedTile.person().get());
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
                gameState.enter();
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
        Optional<Dialog> possibleConversation = dialogs.findCharacterConversationFor(gameState.location(), person);
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
        displayListeners.forEach(DisplayListener::initialize);
    }

    private void updateBackground() {
        RenderedTile[][] playerView = determinePlayerView(gameState.mapView(VIEW_RADIUS));
        displayListeners.forEach(displayListener -> displayListener.backgroundUpdated(playerView, animationCycle));
    }

    private void actionCompleted(String message) {
        displayListeners.forEach(displayListener -> displayListener.actionCompleted(message));
    }

    private void moveBlocked() {
        displayListeners.forEach(displayListener -> displayListener.actionCompleted(messages.actionResponseBlocked()));
    }

    private void moveSlowed() {
        displayListeners.forEach(displayListener -> displayListener.actionCompleted(messages.actionResponseSlowProgress()));
    }

    private void conversationIgnored() {
        displayListeners.forEach(displayListener -> displayListener.actionCompleted(messages.actionResponseIgnored()));
    }

    private void spokenTo(String text) {
        displayListeners.forEach(displayListener -> displayListener.responseRequested(text));
    }

    private void type(String input) {
        displayListeners.forEach(displayListener -> displayListener.inputReceived(input));
    }

    private void sayAndPrompt(String input, String response, String question) {
        spokenTo(input + "\n\n" + response + "\n\n" + (question == null ? messages.speechCitizenPrompt() : question));
    }

    private void endConversation() {
        actionCompleted("\n" + messages.speechCitizenBye());
        gameState.endConversation();
    }

    private boolean allowWalkTo(RenderedTile renderedTile) {
        if (renderedTile.tile().walkability() == 100) {
            return true;
        }
        return random.nextInt(100) < renderedTile.tile().walkability();
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
        Visibility visibility = new Visibility(view);
        for (int row = 0; row < size; row ++) {
            for (int col = 0; col < size; col ++) {
                if (view[row][col].render() && visibility.isVisibleToPlayer(Coordinate.forRowCol(row, col))) {
                    continue;
                }
                view[row][col] = view[row][col].hidden();
            }
        }
        return view;
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
        gameState.postTurnUpdates(random);
        updateBackground();
    }
}
