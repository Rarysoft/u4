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
package com.rarysoft.u4.model.npc;

import com.rarysoft.u4.model.Virtue;

import java.util.Optional;
import java.util.Random;

public class Conversation {
    private static final Random RANDOM = new Random();

    private final CharacterConversation characterConversation;

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
    private final Virtue affectedVirtue;
    private final int virtueDelta;
    private final boolean healPlayer;

    public Conversation(CharacterConversation characterConversation, int honestyKarma, int compassionKarma, int valourKarma, int justiceKarma, int sacrificeKarma, int honourKarma, int spiritualityKarma, int humilityKarma) {
        this.characterConversation = characterConversation;
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
        this.affectedVirtue = null;
        this.virtueDelta = 0;
        this.healPlayer = false;
    }

    private Conversation(CharacterConversation characterConversation, int honestyKarma, int compassionKarma, int valourKarma, int justiceKarma, int sacrificeKarma, int honourKarma, int spiritualityKarma, int humilityKarma, String response, String question, Virtue affectedVirtue, int virtueDelta, boolean healPlayer) {
        this.characterConversation = characterConversation;
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
        this.affectedVirtue = affectedVirtue;
        this.virtueDelta = virtueDelta;
        this.healPlayer = healPlayer;
    }

    public Optional<String> response() {
        return Optional.ofNullable(response);
    }

    public Optional<String> question() {
        return Optional.ofNullable(question);
    }

    public Optional<Virtue> affectedVirtue() {
        return Optional.ofNullable(affectedVirtue);
    }

    public int virtueDelta() {
        return virtueDelta;
    }

    public boolean healPlayer() {
        return healPlayer;
    }

    public Conversation forInput(String input) {
        String response = null;
        String question = null;
        if (characterWillRespond()) {
            switch (input) {
                case "LOOK":
                    response = characterConversation.getLookResponse();
                    break;

                case "NAME":
                    response = characterConversation.getNameResponse();
                    break;

                case "JOB":
                    response = characterConversation.getJobResponse();
                    if (characterConversation.getQuestionFlag() == CharacterConversation.QUESTION_FLAG_JOB) {
                        question = characterConversation.getYesNoQuestion();
                    }
                    break;

                case "HEAL":
                    response = characterConversation.getHealthResponse();
                    if (characterConversation.getQuestionFlag() == CharacterConversation.QUESTION_FLAG_HEALTH) {
                        question = characterConversation.getYesNoQuestion();
                    }
                    break;

                case "JOIN":
                    // TODO: deal with NPCs that can join the party
                    response = characterConversation.getNoJoinResponse();
                    break;

                case "BYE":
                    break;

                default:
                    if (characterConversation.getType() == ConversationType.CITIZEN) {
                        if (input.equals(characterConversation.getKeyword(0))) {
                            response = characterConversation.getKeywordResponse(0);
                            if (characterConversation.getQuestionFlag() == CharacterConversation.QUESTION_FLAG_KEYWORD1) {
                                question = characterConversation.getYesNoQuestion();
                            }
                        } else if (input.equals(characterConversation.getKeyword(1))) {
                            response = characterConversation.getKeywordResponse(1);
                            if (characterConversation.getQuestionFlag() == CharacterConversation.QUESTION_FLAG_KEYWORD2) {
                                question = characterConversation.getYesNoQuestion();
                            }
                        } else {
                             response = characterConversation.getUnknownResponse();
                        }
                    }
                    else if (characterConversation.getType() == ConversationType.LORD_BRITISH) {
                        Optional<String> lordBritishResponse = characterConversation.getKeywordResponse(input);
                        response = lordBritishResponse.orElseGet(characterConversation::getUnknownResponse);
                    }
                    break;
            }
        }
        return new Conversation(
                characterConversation,
                honestyKarma,
                compassionKarma,
                valourKarma,
                justiceKarma,
                sacrificeKarma,
                honourKarma,
                spiritualityKarma,
                humilityKarma,
                response,
                question,
                null,
                0,
                false
        );
    }

    public Conversation forResponse(String response) {
        if (response.startsWith("Y")) {
            return new Conversation(
                    characterConversation,
                    honestyKarma,
                    compassionKarma,
                    valourKarma,
                    justiceKarma,
                    sacrificeKarma,
                    honourKarma,
                    spiritualityKarma,
                    humilityKarma,
                    characterConversation.getYesResponse(),
                    null,
                    characterConversation.responseAffectsHumility() ? Virtue.HUMILITY : null,
                    characterConversation.responseAffectsHumility() ? -1 : 0,
                    false
            );
        }
        return new Conversation(
                characterConversation,
                honestyKarma,
                compassionKarma,
                valourKarma,
                justiceKarma,
                sacrificeKarma,
                honourKarma,
                spiritualityKarma,
                humilityKarma,
                characterConversation.getNoResponse(),
                null,
                null,
                0,
                characterConversation.getType() == ConversationType.LORD_BRITISH
        );
    }

    private String getConversationStarter() {
        String response = null;
        if (characterWillRespond()) {
            response = characterConversation.getIntro();
            if (characterWillIntroduceSelf()) {
                response += "\n\n";
                response += characterConversation.getNameResponse();
            }
        }
        return response;
    }

    private boolean characterWillRespond() {
        return RANDOM.nextInt(256) <= 255 - characterConversation.getTurnAwayProbability();
    }

    private boolean characterWillIntroduceSelf() {
        return RANDOM.nextBoolean();
    }
}
