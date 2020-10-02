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
package com.rarysoft.u4.ui;

import com.rarysoft.u4.model.DisplayListener;
import com.rarysoft.u4.model.RenderedTile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CommunicationListener implements DisplayListener {
    private static final int LINE_LENGTH = 28;
    private static final int MAX_LINES = 20;

    private final CommunicationProvider communicationProvider;

    private final List<String> textLines = new ArrayList<>();

    public CommunicationListener(CommunicationProvider communicationProvider) {
        this.communicationProvider = communicationProvider;
    }

    @Override
    public void initialize() {
        communicationProvider.showText(getDisplayedTextLines());
    }

    @Override
    public void backgroundUpdated(RenderedTile[][] background, int animationCycle) {
        // nothing to update here
    }

    @Override
    public void actionCompleted(String message) {
        textLines.addAll(wrapText(message).stream().map(this::pad).collect(Collectors.toList()));
        scrollTextLinesIfNeeded();
        communicationProvider.showText(getDisplayedTextLines());
    }

    @Override
    public void responseRequested(List<String> messages) {
        textLines.addAll(messages.stream().map(this::wrapText).flatMap(Collection::stream).map(this::pad).collect(Collectors.toList()));
        scrollTextLinesIfNeeded();
        communicationProvider.showTextAndAwaitResponse(getDisplayedTextLines());
    }

    @Override
    public void inputReceived(String input) {
        communicationProvider.showInput(pad(input, LINE_LENGTH - 1));
    }

    private List<String> wrapText(String text) {
        if (text.length() <= LINE_LENGTH) {
            return Collections.singletonList(text);
        }
        List<String> lines = new ArrayList<>();
        String textToWrap = text;
        while (textToWrap.length() > LINE_LENGTH) {
            int lastSpaceIndex = -1;
            for (int index = 0; index < LINE_LENGTH; index ++) {
                char character = textToWrap.charAt(index);
                if (character == ' ') {
                    lastSpaceIndex = index;
                }
            }
            if (lastSpaceIndex == -1) {
                // This shouldn't happen; just truncate the whole thing if it does
                lines.add(textToWrap.substring(0, LINE_LENGTH));
                textToWrap = "";
                continue;
            }
            lines.add(textToWrap.substring(0, lastSpaceIndex));
            textToWrap = textToWrap.substring(lastSpaceIndex + 1);
        }
        if (! textToWrap.isEmpty()) {
            lines.add(textToWrap);
        }
        return lines;
    }

    private String pad(String text) {
        return pad(text, LINE_LENGTH);
    }

    private String pad(String text, int lineLength) {
        StringBuilder stringBuilder = new StringBuilder(text);
        for (int index = text.length(); index < lineLength; index ++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    private void scrollTextLinesIfNeeded() {
        while(textLines.size() > MAX_LINES) {
            textLines.remove(0);
        }
    }

    private List<String> getDisplayedTextLines() {
        List<String> displayedTextLines = new ArrayList<>(textLines);
        while (displayedTextLines.size() < MAX_LINES) {
            displayedTextLines.add(pad(""));
        }
        return displayedTextLines;
    }
}
