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
import java.util.List;

public class CommunicationPanel extends JPanel implements CommunicationProvider {
    private static final int OFFSET = 48;

    private final Charset charset;
    private final int scale;

    private final List<String> textLines = new ArrayList<>();

    private boolean allowInput = false;
    private String inputLine = "";

    public CommunicationPanel(Charset charset, int scale) {
        this.charset = charset;
        this.scale = scale;
    }

    @Override
    public void showText(List<String> textLines) {
        this.textLines.clear();
        this.textLines.addAll(textLines);
        this.getParent().repaint();
    }

    @Override
    public void showTextAndAwaitResponse(List<String> textLines) {
        this.textLines.clear();
        this.textLines.addAll(textLines);
        this.allowInput = true;
        this.getParent().repaint();
    }

    @Override
    public void showInput(String input) {
        inputLine = input;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        for (int row = 0; row < textLines.size(); row ++) {
            String textLine = textLines.get(row);
            for (int col = 0; col < textLine.length(); col ++) {
                int charCode = textLine.charAt(col);
                int[][] character = charset.data()[charCode];
                drawCharacter(graphics, character, row, col);
            }
        }
        int inputRow = 20;
        drawCharacter(graphics, charset.data()[16], inputRow, 0);
        for (int index = 0; index < inputLine.length(); index ++) {
            drawCharacter(graphics, charset.data()[inputLine.charAt(index)], inputRow, index + 1);
        }
        if (allowInput) {
            // TODO: show animated cursor
        }
    }

    private void drawCharacter(Graphics graphics, int[][] character, int row, int col) {
        int y = yForRow(row);
        int x = xForCol(col);
        for (int pixelY = 0; pixelY < Charset.CHAR_HEIGHT; pixelY ++) {
            for (int pixelX = 0; pixelX < Charset.CHAR_WIDTH; pixelX ++) {
                int code = character[pixelY][pixelX];
                graphics.setColor(Colours.BY_CODE[code]);
                graphics.fillRect(x + pixelX * scale, y + pixelY * scale, scale, scale);
            }
        }
    }

    private int yForRow(int row) {
        return OFFSET + row * Charset.CHAR_HEIGHT * scale;
    }

    private int xForCol(int col) {
        return col * Charset.CHAR_WIDTH * scale;
    }
}
