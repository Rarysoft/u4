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

import java.util.ResourceBundle;

public class Messages {
    private final ResourceBundle resourceBundle;

    public Messages(String filename) {
        resourceBundle = ResourceBundle.getBundle(filename);
    }

    public String windowTitle() {
        return resourceBundle.getString("window.title");
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
}
