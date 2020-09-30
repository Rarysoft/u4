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

import javax.swing.Timer;
import java.util.*;

public class Game {
    // Note: view radius is one larger than what will actually be shown on screen, which allows the display to do
    // some necessary analysis on the tiles around each visible tile
    private static final int VIEW_RADIUS = 10;

    private final Messages messages;

    private final Conversations conversations;

    private final Set<DisplayListener> displayListeners;

    private final Random random;

    private GameState gameState;

    private int animationCycle;

    public Game(Messages messages, Conversations conversations) {
        this.messages = messages;
        this.conversations = conversations;
        this.displayListeners = new HashSet<>();
        this.random = new Random();
    }

    public void addDisplayListener(DisplayListener displayListener) {
        displayListeners.add(displayListener);
    }

    public void start(GameState gameState) {
        this.gameState = gameState;
        initializeAnimation();
        updateBackground();
        spokenTo(Collections.singletonList(""));
        type("");
    }

    public void onNorthPressed() {
        if (gameState.inNormalPlay()) {
            type("Go north");
            attemptMove(-1, 0);
        }
    }

    public void onNortheastPressed() {
        if (gameState.inNormalPlay()) {
            type("Go northeast");
            attemptMove(-1, 1);
        }
    }

    public void onEastPressed() {
        if (gameState.inNormalPlay()) {
            type("Go east");
            attemptMove(0, 1);
        }
    }

    public void onSoutheastPressed() {
        if (gameState.inNormalPlay()) {
            type("Go southeast");
            attemptMove(1, 1);
        }
    }

    public void onSouthPressed() {
        if (gameState.inNormalPlay()) {
            type("Go south");
            attemptMove(1, 0);
        }
    }

    public void onSouthwestPressed() {
        if (gameState.inNormalPlay()) {
            type("Go southwest");
            attemptMove(1, -1);
        }
    }

    public void onWestPressed() {
        if (gameState.inNormalPlay()) {
            type("Go west");
            attemptMove(0, -1);
        }
    }

    public void onNorthwestPressed() {
        if (gameState.inNormalPlay()) {
            type("Go northwest");
            attemptMove(-1, -1);
        }
    }

    public void onUserInput(char input) {
        if (gameState.inConversation()) {
            if (input == '\n') {
                String playerInput = gameState.input().toUpperCase();
                if (playerInput.length() > 4) {
                    playerInput = playerInput.substring(0, 4);
                }
                Conversation conversation = gameState.conversation();
                List<String> speech = new ArrayList<>();
                switch (playerInput) {
                    case "LOOK":
                        speech.add(messages.speechCitizenIntro(conversation.getLookResponse()));
                        speech.add("");
                        speech.add(messages.speechCitizenPrompt());
                        spokenTo(speech);
                        break;

                    case "NAME":
                        speech.add(messages.speechCitizenSpeaking(conversation.getPronoun()) + " " + messages.speechCitizenName(conversation.getName()));
                        speech.add("");
                        speech.add(messages.speechCitizenPrompt());
                        spokenTo(speech);
                        break;

                    case "JOB":
                        speech.add(messages.speechCitizenSpeaking(conversation.getPronoun()) + " " + conversation.getJobResponse());
                        speech.add("");
                        speech.add(messages.speechCitizenPrompt());
                        spokenTo(speech);
                        break;

                    case "HEAL":
                        speech.add(messages.speechCitizenSpeaking(conversation.getPronoun()) + " " + conversation.getHealthResponse());
                        speech.add("");
                        speech.add(messages.speechCitizenPrompt());
                        spokenTo(speech);
                        break;

                    case "JOIN":
                        // TODO: deal with NPCs that can join the party
                        speech.add(messages.speechCitizenSpeaking(conversation.getPronoun()) + " " + messages.speechCitizenNoJoin());
                        speech.add("");
                        speech.add(messages.speechCitizenPrompt());
                        spokenTo(speech);
                        break;

                    case "BYE":
                        // TODO: what do we say here?
                        speech.add(messages.speechCitizenBye());
                        gameState.endConversation();
                        spokenTo(speech);
                        break;

                    default:
                        if (playerInput.equals(conversation.getKeyword1())) {
                            speech.add(messages.speechCitizenSpeaking(conversation.getPronoun()) + " " + conversation.getKeyword1Response());
                            speech.add("");
                            speech.add(messages.speechCitizenPrompt());
                            spokenTo(speech);
                        }
                        else if (playerInput.equals(conversation.getKeyword2())) {
                            speech.add(messages.speechCitizenSpeaking(conversation.getPronoun()) + " " + conversation.getKeyword2Response());
                            speech.add("");
                            speech.add(messages.speechCitizenPrompt());
                            spokenTo(speech);
                        }
                        else {
                            speech.add(messages.speechCitizenUnknown());
                            speech.add("");
                            speech.add(messages.speechCitizenPrompt());
                            spokenTo(speech);
                        }
                        break;
                }
                gameState.resetInput();
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

    private void attemptMove(int rowDelta, int colDelta) {
        RenderedTile renderedTile = gameState.tileAt(gameState.row() + rowDelta, gameState.col() + colDelta);
        if (renderedTile.tile() == null) {
            gameState.returnToSurface();
        }
        else {
            if (renderedTile.tile().walkability() == 0) {
                if (renderedTile.tile().canTalkAcross()) {
                    RenderedTile adjacentTile = gameState.tileAt(gameState.row() + rowDelta + rowDelta, gameState.col() + colDelta + colDelta);
                    if (adjacentTile.person().isPresent()) {
                        attemptConversationWith(adjacentTile.person().get());
                    }
                }
                moveBlocked();
                afterPlayerMove();
                return;
            }
            // Special case: don't allow northward exit from LB's castle
            if (rowDelta == -1 && gameState.tileAt(gameState.row(), gameState.col()).tile() == Tile.CASTLE_BRITANNIA_ENTRANCE) {
                moveBlocked();
                afterPlayerMove();
                return;
            }
            // Special case: don't allow entry to LB's castle from the north
            if (rowDelta == 1 && renderedTile.tile() == Tile.CASTLE_BRITANNIA_ENTRANCE) {
                moveBlocked();
                afterPlayerMove();
                return;
            }
            if (renderedTile.person().isPresent()) {
                attemptConversationWith(renderedTile.person().get());
                afterPlayerMove();
                return;
            }
            if (! allowWalkTo(renderedTile)) {
                moveSlowed();
                afterPlayerMove();
                return;
            }
            gameState.changeRow(rowDelta);
            gameState.changeCol(colDelta);
            if (renderedTile.tile().type() == TileType.PORTAL) {
                gameState.enter();
            }
        }
        updateBackground();
        afterPlayerMove();
    }

    private void attemptConversationWith(Person person) {
        Optional<Conversation> possibleConversation = conversations.findConversationFor(gameState.locationId(), person);
        if (possibleConversation.isPresent()) {
            Conversation conversation = possibleConversation.get();
            if (random.nextInt(256) > 255 - conversation.getTurnAwayProbability()) {
                conversationIgnored();
                afterPlayerMove();
                return;
            }
            gameState.startConversation(conversation);
            List<String> speech = new ArrayList<>();
            speech.add(messages.speechCitizenIntro(conversation.getLookResponse()));
            speech.add("");
            if (random.nextBoolean()) {
                speech.add(messages.speechCitizenSpeaking(conversation.getPronoun()) + " " + messages.speechCitizenName(conversation.getName()));
                speech.add("");
            }
            speech.add(messages.speechCitizenPrompt());
            spokenTo(speech);
            afterPlayerMove();
            return;
        }
        moveBlocked();
        afterPlayerMove();
    }

    private void updateBackground() {
        RenderedTile[][] playerView = determinePlayerView(gameState.mapView(VIEW_RADIUS));
        displayListeners.forEach(displayListener -> displayListener.backgroundUpdated(playerView, animationCycle));
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

    private void spokenTo(List<String> textLines) {
        displayListeners.forEach(displayListener -> displayListener.responseRequested(textLines));
    }

    private void type(String input) {
        displayListeners.forEach(displayListener -> displayListener.inputReceived(input));
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
        gameState.movePeople();
        updateBackground();
    }
}
