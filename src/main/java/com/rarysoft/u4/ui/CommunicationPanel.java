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

import com.rarysoft.u4.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CommunicationPanel extends JPanel implements DisplayListener {
    private static final int LINE_LENGTH = 28;
    private static final int MAX_LINES = 20;
    private static final int OFFSET = 48;

    private final Charset charset;
    private final int scale;

    private final List<String> textLines = new ArrayList<>();

    public CommunicationPanel(Charset charset, int scale) {
        this.charset = charset;
        this.scale = scale;
    }

    @Override
    public void backgroundUpdated(RenderedTile[][] background, int animationCycle) {
        // nothing to update here
    }

    @Override
    public void actionCompleted(String message) {
        textLines.addAll(wrapText(message).stream().map(this::pad).collect(Collectors.toList()));
        while (textLines.size() > MAX_LINES) {
            textLines.remove(0);
        }
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        for (int row = 0; row < MAX_LINES; row ++) {
            String textLine;
            if (row < textLines.size()) {
                textLine = textLines.get(row);
            }
            else {
                textLine = pad("");
            }
            for (int col = 0; col < LINE_LENGTH; col ++) {
                int charCode = textLine.charAt(col);
                int[][] character = charset.data()[charCode];
                drawCharacter(graphics, character, row * Charset.CHAR_HEIGHT * scale, col * Charset.CHAR_WIDTH * scale);
            }
        }
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
        StringBuilder stringBuilder = new StringBuilder(text);
        for (int index = text.length(); index < LINE_LENGTH; index ++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    private void drawCharacter(Graphics graphics, int[][] character, int row, int col) {
        for (int pixelRow = 0; pixelRow < Charset.CHAR_HEIGHT; pixelRow ++) {
            for (int pixelCol = 0; pixelCol < Charset.CHAR_WIDTH; pixelCol ++) {
                int code = character[pixelRow][pixelCol];
                graphics.setColor(Colours.BY_CODE[code]);
                graphics.fillRect(col + pixelCol * scale, row + pixelRow * scale + OFFSET, scale, scale);
            }
        }
    }
}
