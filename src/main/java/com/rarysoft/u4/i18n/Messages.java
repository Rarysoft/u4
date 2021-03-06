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
package com.rarysoft.u4.i18n;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Messages {
    private final ResourceBundle resourceBundle;

    public Messages(String filename) {
        resourceBundle = ResourceBundle.getBundle(filename);
    }

    public String windowTitle() {
        return resourceBundle.getString("window.title");
    }

    public String actionPass() {
        return resourceBundle.getString("action.pass");
    }

    public String actionMove(String direction) {
        return resourceBundle.getString("action.move").replace("{direction}", direction);
    }

    public String actionExit() {
        return resourceBundle.getString("action.exit");
    }

    public String actionEnter(String name) {
        return resourceBundle.getString("action.enter").replace("{name}", name);
    }

    public String actionTalk(String direction) {
        return resourceBundle.getString("action.talk").replace("{direction}", direction);
    }

    public String actionOpen(String direction) {
        return resourceBundle.getString("action.open").replace("{direction}", direction);
    }

    public String actionResponseBlocked() {
        return resourceBundle.getString("action.response.blocked");
    }

    public String actionResponseSlowProgress() {
        return resourceBundle.getString("action.response.slowProgress");
    }

    public String actionResponseIgnored() {
        return resourceBundle.getString("action.response.ignored");
    }

    public String actionResponseLockedHaveKeys() {
        return resourceBundle.getString("action.response.locked.haveKeys");
    }

    public String actionResponseLockedHaveNoKeys() {
        return resourceBundle.getString("action.response.locked.haveNoKeys");
    }

    public String actionResponseUnlocked() {
        return resourceBundle.getString("action.response.unlocked");
    }

    public String actionResponseNotUnlocked() {
        return resourceBundle.getString("action.response.notUnlocked");
    }

    public String speechCitizenIntro(String who) {
        return resourceBundle.getString("speech.citizen.intro").replace("{who}", who);
    }

    public String speechCitizenDescribe(String who) {
        return resourceBundle.getString("speech.citizen.describe").replace("{who}", who);
    }

    public String speechCitizenPrompt() {
        return resourceBundle.getString("speech.citizen.prompt");
    }

    public String speechCitizenSpeaking(String pronoun) {
        return resourceBundle.getString("speech.citizen.speaking").replace("{pronoun}", pronoun);
    }

    public String speechCitizenName(String name) {
        return resourceBundle.getString("speech.citizen.name").replace("{name}", name);
    }

    public String speechCitizenUnknown() {
        return resourceBundle.getString("speech.citizen.unknown");
    }

    public String speechCitizenNoJoin() {
        return resourceBundle.getString("speech.citizen.noJoin");
    }

    public String speechCitizenBye() {
        return resourceBundle.getString("speech.citizen.bye");
    }

    public List<String> messageResurrection(String name) {
        return Arrays.stream(resourceBundle.getString("message.resurrection").split(",")).map(message -> message.replace("{name}", name)).collect(Collectors.toList());
    }
}
