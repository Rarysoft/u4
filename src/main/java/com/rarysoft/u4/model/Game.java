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
import com.rarysoft.u4.model.npc.Conversations;
import com.rarysoft.u4.model.npc.Person;
import com.rarysoft.u4.model.graphics.*;

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
        actionCompleted("Go north");
        attemptMove(-1, 0);
    }

    public void onNortheastPressed() {
        if (gameState.inConversation()) {
            endConversation();
        }
        actionCompleted("Go northeast");
        attemptMove(-1, 1);
    }

    public void onEastPressed() {
        if (gameState.inConversation()) {
            endConversation();
        }
        actionCompleted("Go east");
        attemptMove(0, 1);
    }

    public void onSoutheastPressed() {
        if (gameState.inConversation()) {
            endConversation();
        }
        actionCompleted("Go southeast");
        attemptMove(1, 1);
    }

    public void onSouthPressed() {
        if (gameState.inConversation()) {
            endConversation();
        }
        actionCompleted("Go south");
        attemptMove(1, 0);
    }

    public void onSouthwestPressed() {
        if (gameState.inConversation()) {
            endConversation();
        }
        actionCompleted("Go southwest");
        attemptMove(1, -1);
    }

    public void onWestPressed() {
        if (gameState.inConversation()) {
            endConversation();
        }
        actionCompleted("Go west");
        attemptMove(0, -1);
    }

    public void onNorthwestPressed() {
        if (gameState.inConversation()) {
            endConversation();
        }
        actionCompleted("Go northwest");
        attemptMove(-1, -1);
    }

    public void onUserInput(char input) {
        if (gameState.inConversation()) {
            if (input == '\n') {
                String playerInput = gameState.input().toUpperCase();
                if (playerInput.length() > 4) {
                    playerInput = playerInput.substring(0, 4);
                }
                Conversation conversation = gameState.conversation();
                if (gameState.inConversationRespondingYesOrNo()) {
                    if (playerInput.startsWith("Y")) {
                        sayAndPrompt(conversationYesResponse(conversation));
                    }
                    else {
                        sayAndPrompt(conversationNoResponse(conversation));
                    }
                    gameState.queryAnswered();
                }
                else {
                    boolean playerQueried = false;
                    switch (playerInput) {
                        case "LOOK":
                            sayAndPrompt(conversationLookResponse(conversation));
                            break;

                        case "NAME":
                            sayAndPrompt(conversationNameResponse(conversation));
                            break;

                        case "JOB":
                            playerQueried = sayAndPrompt(conversationJobResponse(conversation), Conversation.QUESTION_FLAG_JOB, conversation);
                            break;

                        case "HEAL":
                            playerQueried = sayAndPrompt(conversationHealthResponse(conversation), Conversation.QUESTION_FLAG_HEALTH, conversation);
                            break;

                        case "JOIN":
                            // TODO: deal with NPCs that can join the party
                            sayAndPrompt(conversationNoJoinResponse(conversation));
                            break;

                        case "BYE":
                            endConversation();
                            break;

                        default:
                            if (playerInput.equals(conversation.getKeyword1())) {
                                playerQueried = sayAndPrompt(conversationKeyword1Response(conversation), Conversation.QUESTION_FLAG_KEYWORD1, conversation);
                            }
                            else if (playerInput.equals(conversation.getKeyword2())) {
                                playerQueried = sayAndPrompt(conversationKeyword2Response(conversation), Conversation.QUESTION_FLAG_KEYWORD2, conversation);
                            }
                            else {
                                sayAndPrompt(conversationUnknownResponse(conversation));
                            }
                            break;
                    }
                    if (playerQueried) {
                        gameState.playerQueried();
                    }
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

    private void attemptMove(int rowDelta, int colDelta) {
        RenderedTile renderedTile = gameState.tileAt(gameState.row() + rowDelta, gameState.col() + colDelta);
        if (renderedTile.tile() == null) {
            gameState.returnToSurface();
        }
        else {
            if (renderedTile.tile().walkability() == 0) {
                if (renderedTile.tile() == Tile.UNLOCKED_DOOR) {
                    gameState.openDoor(gameState.row() + rowDelta, gameState.col() + colDelta);
                }
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
        Optional<Conversation> possibleConversation = conversations.findConversationFor(gameState.location(), person);
        if (possibleConversation.isPresent()) {
            Conversation conversation = possibleConversation.get();
            if (random.nextInt(256) > 255 - conversation.getTurnAwayProbability()) {
                conversationIgnored();
                afterPlayerMove();
                return;
            }
            gameState.startConversation(conversation, person);
            List<String> speech = new ArrayList<>();
            speech.add(conversationIntro(conversation));
            speech.add("");
            if (random.nextBoolean()) {
                speech.add(conversationNameResponse(conversation));
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

    private void initializeDisplay() {
        displayListeners.forEach(displayListener -> displayListener.initialize());
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

    private void spokenTo(List<String> textLines) {
        displayListeners.forEach(displayListener -> displayListener.responseRequested(textLines));
    }

    private void type(String input) {
        displayListeners.forEach(displayListener -> displayListener.inputReceived(input));
    }

    private void sayAndPrompt(String message) {
        spokenTo(Arrays.asList(
                "",
                message,
                "",
                messages.speechCitizenPrompt()
        ));
    }

    private boolean sayAndPrompt(String message, int questionFlag, Conversation conversation) {
        boolean playerQueried = conversation.getQuestionFlag() == questionFlag;
        spokenTo(Arrays.asList(
                "",
                message,
                "",
                playerQueried ? conversation.getYesNoQuestion() : messages.speechCitizenPrompt()
        ));
        return playerQueried;
    }

    private String conversationIntro(Conversation conversation) {
        return messages.speechCitizenIntro(withinSentence(conversation.getLookResponse()));
    }

    private String conversationLookResponse(Conversation conversation) {
        return messages.speechCitizenDescribe(withinSentence(conversation.getLookResponse()));
    }

    private String conversationNameResponse(Conversation conversation) {
        return conversationResponse(messages.speechCitizenName(conversation.getName()), conversation);
    }

    private String conversationJobResponse(Conversation conversation) {
        return conversationResponse(conversation.getJobResponse(), conversation);
    }

    private String conversationHealthResponse(Conversation conversation) {
        return conversationResponse(conversation.getHealthResponse(), conversation);
    }

    private String conversationNoJoinResponse(Conversation conversation) {
        return conversationResponse(messages.speechCitizenNoJoin(), conversation);
    }

    private String conversationKeyword1Response(Conversation conversation) {
        return conversationResponse(conversation.getKeyword1Response(), conversation);
    }

    private String conversationKeyword2Response(Conversation conversation) {
        return conversationResponse(conversation.getKeyword2Response(), conversation);
    }

    private String conversationYesResponse(Conversation conversation) {
        return conversationResponse(conversation.getYesResponse(), conversation);
    }

    private String conversationNoResponse(Conversation conversation) {
        return conversationResponse(conversation.getNoResponse(), conversation);
    }

    private String conversationUnknownResponse(Conversation conversation) {
        return conversationResponse(messages.speechCitizenUnknown(), conversation);
    }

    private String conversationResponse(String message, Conversation conversation) {
        return messages.speechCitizenSpeaking(conversation.getPronoun()) + " " + message;
    }

    private String withinSentence(String message) {
        if (message.length() <= 1) {
            return message.toLowerCase();
        }
        return message.substring(0, 1).toLowerCase() + message.substring(1);
    }

    private void endConversation() {
        Conversation conversation = gameState.conversation();
        spokenTo(Arrays.asList(
                messages.speechCitizenSpeaking(conversation.getPronoun()) + " " + messages.speechCitizenBye(),
                ""
        ));
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
