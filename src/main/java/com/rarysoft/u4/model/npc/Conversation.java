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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Conversation {
    public static final int QUESTION_FLAG_JOB = 3;
    public static final int QUESTION_FLAG_HEALTH = 4;
    public static final int QUESTION_FLAG_KEYWORD1 = 5;
    public static final int QUESTION_FLAG_KEYWORD2 = 6;

    private final ConversationType type;
    private final int questionFlag;
    private final boolean responseAffectsHumility;
    private final int turnAwayProbability;
    private final String intro;
    private final String nameResponse;
    private final String lookResponse;
    private final String jobResponse;
    private final String healthResponse;
    private final String noJoinResponse;
    private final List<String> keywordResponses;
    private final String yesNoQuestion;
    private final String yesResponse;
    private final String noResponse;
    private final String unknownResponse;
    private final List<String> keywords;

    public Conversation(int questionFlag, boolean responseAffectsHumility, int turnAwayProbability, String intro, String nameResponse, String lookResponse, String jobResponse, String healthResponse, String noJoinResponse, String keyword1Response, String keyword2Response, String yesNoQuestion, String yesResponse, String noResponse, String unknownResponse, String keyword1, String keyword2) {
        this.type = ConversationType.CITIZEN;
        this.questionFlag = questionFlag;
        this.responseAffectsHumility = responseAffectsHumility;
        this.turnAwayProbability = turnAwayProbability;
        this.intro = intro;
        this.nameResponse = nameResponse;
        this.lookResponse = lookResponse;
        this.jobResponse = jobResponse;
        this.healthResponse = healthResponse;
        this.noJoinResponse = noJoinResponse;
        this.keywordResponses = Arrays.asList(keyword1Response, keyword2Response);
        this.yesNoQuestion = yesNoQuestion;
        this.yesResponse = yesResponse;
        this.noResponse = noResponse;
        this.unknownResponse = unknownResponse;
        this.keywords = Arrays.asList(keyword1, keyword2);
    }

    public Conversation(ConversationType type, int questionFlag, boolean responseAffectsHumility, int turnAwayProbability, String intro, String nameResponse, String lookResponse, String jobResponse, String healthResponse, String noJoinResponse, List<String> keywordResponses, String yesNoQuestion, String yesResponse, String noResponse, String unknownResponse, List<String> keywords) {
        this.type = type;
        this.questionFlag = questionFlag;
        this.responseAffectsHumility = responseAffectsHumility;
        this.turnAwayProbability = turnAwayProbability;
        this.intro = intro;
        this.nameResponse = nameResponse;
        this.lookResponse = lookResponse;
        this.jobResponse = jobResponse;
        this.healthResponse = healthResponse;
        this.noJoinResponse = noJoinResponse;
        this.keywordResponses = keywordResponses;
        this.yesNoQuestion = yesNoQuestion;
        this.yesResponse = yesResponse;
        this.noResponse = noResponse;
        this.unknownResponse = unknownResponse;
        this.keywords = keywords;
    }

    public ConversationType getType() {
        return type;
    }

    public int getQuestionFlag() {
        return questionFlag;
    }

    public boolean responseAffectsHumility() {
        return responseAffectsHumility;
    }

    public int getTurnAwayProbability() {
        return turnAwayProbability;
    }

    public String getIntro() {
        return intro;
    }

    public String getNameResponse() {
        return nameResponse;
    }

    public String getLookResponse() {
        return lookResponse;
    }

    public String getJobResponse() {
        return jobResponse;
    }

    public String getHealthResponse() {
        return healthResponse;
    }

    public String getNoJoinResponse() {
        return noJoinResponse;
    }

    public String getKeywordResponse(int index) {
        return keywordResponses.get(index);
    }

    public String getYesNoQuestion() {
        return yesNoQuestion;
    }

    public String getYesResponse() {
        return yesResponse;
    }

    public String getNoResponse() {
        return noResponse;
    }

    public String getUnknownResponse() {
        return unknownResponse;
    }

    public String getKeyword(int index) {
        return keywords.get(index);
    }

    public Optional<String> getKeywordResponse(String keyword) {
        int index = keywords.indexOf(keyword);
        if (index < 0) {
            return Optional.empty();
        }
        return Optional.of(keywordResponses.get(index));
    }
}
