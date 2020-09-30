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

public class Conversation {
    private final int questionFlag;
    private final boolean responseAffectsHumility;
    private final int turnAwayProbability;
    private final String name;
    private final String pronoun;
    private final String lookResponse;
    private final String jobResponse;
    private final String healthResponse;
    private final String keyword1Response;
    private final String keyword2Response;
    private final String yesNoQuestion;
    private final String yesResponse;
    private final String noResponse;
    private final String keyword1;
    private final String keyword2;

    public Conversation(int questionFlag, boolean responseAffectsHumility, int turnAwayProbability, String name, String pronoun, String lookResponse, String jobResponse, String healthResponse, String keyword1Response, String keyword2Response, String yesNoQuestion, String yesResponse, String noResponse, String keyword1, String keyword2) {
        this.questionFlag = questionFlag;
        this.responseAffectsHumility = responseAffectsHumility;
        this.turnAwayProbability = turnAwayProbability;
        this.name = name;
        this.pronoun = pronoun;
        this.lookResponse = lookResponse;
        this.jobResponse = jobResponse;
        this.healthResponse = healthResponse;
        this.keyword1Response = keyword1Response;
        this.keyword2Response = keyword2Response;
        this.yesNoQuestion = yesNoQuestion;
        this.yesResponse = yesResponse;
        this.noResponse = noResponse;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
    }

    public int getQuestionFlag() {
        return questionFlag;
    }

    public boolean isResponseAffectsHumility() {
        return responseAffectsHumility;
    }

    public int getTurnAwayProbability() {
        return turnAwayProbability;
    }

    public String getName() {
        return name;
    }

    public String getPronoun() {
        return pronoun;
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

    public String getKeyword1Response() {
        return keyword1Response;
    }

    public String getKeyword2Response() {
        return keyword2Response;
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

    public String getKeyword1() {
        return keyword1;
    }

    public String getKeyword2() {
        return keyword2;
    }
}
