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

import com.rarysoft.u4.model.Charset;
import com.rarysoft.u4.model.Tiles;

import javax.swing.*;
import java.awt.*;

public class UiBuilder {
    public GameListener buildGamePanel(JFrame gameWindow, Tiles tiles, int scale) {
        GamePanel gamePanel = new GamePanel(tiles, scale);
        gameWindow.add(gamePanel, BorderLayout.LINE_START);
        return new GameListener(gamePanel);
    }

    public CommunicationListener buildCommunicationPanel(JFrame gameWindow, Charset charset, int scale) {
        CommunicationPanel communicationPanel = new CommunicationPanel(charset, scale);
        JPanel panel = new JPanel(new BorderLayout());
        gameWindow.add(panel, BorderLayout.CENTER);
        panel.add(communicationPanel, BorderLayout.CENTER);
        return new CommunicationListener(communicationPanel);
    }

    public JFrame buildGameWindow(String title) {
        return new JFrame(title);
    }
}
