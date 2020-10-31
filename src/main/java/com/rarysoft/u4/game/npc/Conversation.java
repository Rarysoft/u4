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
package com.rarysoft.u4.game.npc;

import java.util.Optional;
import java.util.Random;

public class Conversation {
    private static final Random RANDOM = new Random();

    private final Dialog dialog;

    private final String player;
    private final int honestyKarma;
    private final int compassionKarma;
    private final int valourKarma;
    private final int justiceKarma;
    private final int sacrificeKarma;
    private final int honourKarma;
    private final int spiritualityKarma;
    private final int humilityKarma;

    private final String response;
    private final String question;
    private final int humilityDelta;
    private final boolean healPlayer;
    private final boolean endConversation;

    public Conversation(Dialog dialog, String player, int honestyKarma, int compassionKarma, int valourKarma, int justiceKarma, int sacrificeKarma, int honourKarma, int spiritualityKarma, int humilityKarma) {
        this.dialog = dialog;
        this.player = player;
        this.honestyKarma = honestyKarma;
        this.compassionKarma = compassionKarma;
        this.valourKarma = valourKarma;
        this.justiceKarma = justiceKarma;
        this.sacrificeKarma = sacrificeKarma;
        this.honourKarma = honourKarma;
        this.spiritualityKarma = spiritualityKarma;
        this.humilityKarma = humilityKarma;
        this.response = getConversationStarter();
        this.question = null;
        this.humilityDelta = 0;
        this.healPlayer = false;
        this.endConversation = false;
    }

    private Conversation(Dialog dialog, String player, int honestyKarma, int compassionKarma, int valourKarma, int justiceKarma, int sacrificeKarma, int honourKarma, int spiritualityKarma, int humilityKarma, String response, String question, int humilityDelta, boolean healPlayer, boolean endConversation) {
        this.dialog = dialog;
        this.player = player;
        this.honestyKarma = honestyKarma;
        this.compassionKarma = compassionKarma;
        this.valourKarma = valourKarma;
        this.justiceKarma = justiceKarma;
        this.sacrificeKarma = sacrificeKarma;
        this.honourKarma = honourKarma;
        this.spiritualityKarma = spiritualityKarma;
        this.humilityKarma = humilityKarma;
        this.response = response;
        this.question = question;
        this.humilityDelta = humilityDelta;
        this.healPlayer = healPlayer;
        this.endConversation = endConversation;
    }

    public Optional<String> response() {
        return Optional.ofNullable(response);
    }

    public Optional<String> question() {
        return Optional.ofNullable(question);
    }

    public int humilityDelta() {
        return humilityDelta;
    }

    public boolean healPlayer() {
        return healPlayer;
    }

    public Conversation forInput(String input) {
        String playerInput = input.toUpperCase();
        if (playerInput.length() > 4) {
            playerInput = playerInput.substring(0, 4);
        }
        String response = null;
        String question = null;
        boolean conversationHasEnded = false;
        if (characterWillRespond()) {
            switch (playerInput) {
                case "LOOK":
                    response = dialog.getLookResponse();
                    break;

                case "NAME":
                    response = dialog.getNameResponse();
                    break;

                case "JOB":
                    response = dialog.getJobResponse();
                    if (dialog.getQuestionFlag() == Dialog.QUESTION_FLAG_JOB) {
                        question = dialog.getYesNoQuestion();
                    }
                    break;

                case "HEAL":
                    response = dialog.getHealthResponse();
                    if (dialog.getQuestionFlag() == Dialog.QUESTION_FLAG_HEALTH) {
                        question = dialog.getYesNoQuestion();
                    }
                    break;

                case "JOIN":
                    // TODO: deal with NPCs that can join the party
                    response = dialog.getNoJoinResponse();
                    break;

                case "BYE":
                    response = dialog.getByeResponse();
                    conversationHasEnded = true;
                    break;

                default:
                    if (dialog.getNpc() == NonPlayerCharacter.CITIZEN) {
                        if (playerInput.equals(dialog.getKeyword(0))) {
                            response = dialog.getKeywordResponse(0);
                            if (dialog.getQuestionFlag() == Dialog.QUESTION_FLAG_KEYWORD1) {
                                question = dialog.getYesNoQuestion();
                            }
                        } else if (playerInput.equals(dialog.getKeyword(1))) {
                            response = dialog.getKeywordResponse(1);
                            if (dialog.getQuestionFlag() == Dialog.QUESTION_FLAG_KEYWORD2) {
                                question = dialog.getYesNoQuestion();
                            }
                        } else {
                             response = dialog.getUnknownResponse();
                        }
                    }
                    else if (dialog.getNpc() == NonPlayerCharacter.LORD_BRITISH) {
                        Optional<String> lordBritishResponse = dialog.getKeywordResponse(playerInput);
                        response = lordBritishResponse.orElseGet(dialog::getUnknownResponse);
                    }
                    else if (dialog.getNpc() == NonPlayerCharacter.HAWKWIND) {
                        String modifiedInput = modifyInputWithKarmaLevel(playerInput);
                        Optional<String> hawkwindResponse = dialog.getKeywordResponse(modifiedInput);
                        response = hawkwindResponse.orElseGet(dialog::getUnknownResponse);
                    }
                    break;
            }
        }
        return new Conversation(
                dialog,
                player,
                honestyKarma,
                compassionKarma,
                valourKarma,
                justiceKarma,
                sacrificeKarma,
                honourKarma,
                spiritualityKarma,
                humilityKarma,
                personalizeResponse(response),
                question,
                0,
                false,
                conversationHasEnded
        );
    }

    public Conversation forResponse(String response) {
        if (response.toUpperCase().startsWith("Y")) {
            return new Conversation(
                    dialog,
                    player,
                    honestyKarma,
                    compassionKarma,
                    valourKarma,
                    justiceKarma,
                    sacrificeKarma,
                    honourKarma,
                    spiritualityKarma,
                    humilityKarma,
                    dialog.getYesResponse(),
                    null,
                    dialog.responseAffectsHumility() ? -1 : 1,
                    false,
                    false
            );
        }
        return new Conversation(
                dialog,
                player,
                honestyKarma,
                compassionKarma,
                valourKarma,
                justiceKarma,
                sacrificeKarma,
                honourKarma,
                spiritualityKarma,
                humilityKarma,
                dialog.getNoResponse(),
                null,
                0,
                dialog.getNpc() == NonPlayerCharacter.LORD_BRITISH,
                false
        );
    }

    private String getConversationStarter() {
        String response = null;
        if (characterWillRespond()) {
            response = personalizeResponse(dialog.getIntro());
            if (characterWillIntroduceSelf()) {
                response += "\n\n";
                response += dialog.getNameResponse();
            }
        }
        return response;
    }

    private boolean characterWillRespond() {
        return RANDOM.nextInt(256) <= 255 - dialog.getTurnAwayProbability();
    }

    private boolean characterWillIntroduceSelf() {
        return dialog.getNpc() != NonPlayerCharacter.HAWKWIND || (dialog.getNpc() != NonPlayerCharacter.CITIZEN && RANDOM.nextBoolean());
    }

    private String modifyInputWithKarmaLevel(String input) {
        int karmaLevel = 0;
        switch (input) {
            case "HONE":
                karmaLevel = getKarmaLevel(honestyKarma);
                break;
            case "COMP":
                karmaLevel = getKarmaLevel(compassionKarma);
                break;
            case "VALO":
                karmaLevel = getKarmaLevel(valourKarma);
                break;
            case "JUST":
                karmaLevel = getKarmaLevel(justiceKarma);
                break;
            case "SACR":
                karmaLevel = getKarmaLevel(honestyKarma);
                break;
            case "HONO":
                karmaLevel = getKarmaLevel(honourKarma);
                break;
            case "SPIR":
                karmaLevel = getKarmaLevel(spiritualityKarma);
                break;
            case "HUMI":
                karmaLevel = getKarmaLevel(humilityKarma);
                break;
            default:
                break;
        }
        return input + "-" + karmaLevel;
    }

    private int getKarmaLevel(int karma) {
        if (karma < 40) {
            return 1;
        }
        if (karma < 50) {
            return 2;
        }
        if (karma < 60) {
            return 3;
        }
        if (karma < 70) {
            return 4;
        }
        if (karma < 100) {
            return 5;
        }
        return 6;
    }

    private String personalizeResponse(String response) {
        if (response == null) {
            return response;
        }
        return response.replace("{player}", player);
    }
}
