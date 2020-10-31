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

import com.rarysoft.u4.game.InformationListener;

import java.util.ArrayList;
import java.util.List;

public class CommunicationListener implements InformationListener {
    private static final int LINE_LENGTH = 28;
    private static final int VISIBLE_LINES = 19;

    private final BorderProvider borderProvider;
    private final StatisticsProvider statisticsProvider;
    private final CommunicationProvider communicationProvider;

    private final List<String> statsLines = new ArrayList<>();
    private final List<String> textLines = new ArrayList<>();

    public CommunicationListener(BorderProvider borderProvider, StatisticsProvider statisticsProvider, CommunicationProvider communicationProvider) {
        this.borderProvider = borderProvider;
        this.statisticsProvider = statisticsProvider;
        this.communicationProvider = communicationProvider;
    }

    @Override
    public void initialize() {
        for (int index = 0; index < 17; index ++) {
            statsLines.add("");
        }
        borderProvider.drawBorder(0, 0, 0);
    }

    @Override
    public void playerUpdated(int index, String name, String status) {
        String stat = separateNameFromStatus(name, status);
        statsLines.set(index, stat);
        statisticsProvider.showStatistics(statsLines);
    }

    @Override
    public void actionCompleted(String message) {
        textLines.addAll(wrapText(message));
        communicationProvider.showText(getDisplayedTextLines());
    }

    @Override
    public void responseRequested(String message) {
        textLines.addAll(wrapText(message));
        communicationProvider.showTextAndAwaitResponse(getDisplayedTextLines());
    }

    @Override
    public void inputReceived(String input) {
        communicationProvider.showInput(input);
    }

    @Override
    public void environmentUpdated(int phaseOfTrammel, int phaseOfFelucca, int windDirection) {
        borderProvider.drawBorder(phaseOfTrammel, phaseOfFelucca, windDirection);
    }

    private String separateNameFromStatus(String name, String status) {
        StringBuilder result = new StringBuilder();
        result.append(name);
        for (int index = 0; index < 26 - name.length() - status.length(); index ++) {
            result.append(" ");
        }
        result.append(status);
        return result.toString();
    }

    private List<String> wrapText(String text) {
        List<String> wrappedLines = new ArrayList<>();
        for (String line : text.split("\n")) {
            String trimmedLine = line.trim();
            if (trimmedLine.length() <= LINE_LENGTH) {
                wrappedLines.add(trimmedLine);
                continue;
            }
            String textToWrap = trimmedLine;
            while (textToWrap.length() > LINE_LENGTH) {
                int lastSpaceIndex = -1;
                for (int index = 0; index < LINE_LENGTH; index ++) {
                    char character = textToWrap.charAt(index);
                    if (character == ' ') {
                        lastSpaceIndex = index;
                    }
                }
                if (textToWrap.charAt(LINE_LENGTH) == ' ') {
                    // The next character will be a space, so break at the full line length, not the previous space
                    wrappedLines.add(textToWrap.substring(0, LINE_LENGTH));
                    textToWrap = textToWrap.substring(LINE_LENGTH + 1);
                }
                else if (lastSpaceIndex == -1) {
                    // This shouldn't happen; just break at the the full line length if it does
                    wrappedLines.add(textToWrap.substring(0, LINE_LENGTH));
                    textToWrap = textToWrap.substring(LINE_LENGTH + 1);
                }
                else {
                    wrappedLines.add(textToWrap.substring(0, lastSpaceIndex));
                    textToWrap = textToWrap.substring(lastSpaceIndex + 1);
                }
            }
            wrappedLines.add(textToWrap);
        }
        return wrappedLines;
    }

    private List<String> getDisplayedTextLines() {
        if (textLines.size() < VISIBLE_LINES) {
            return textLines;
        }
        return textLines.subList(textLines.size() - VISIBLE_LINES, textLines.size());
    }
}
