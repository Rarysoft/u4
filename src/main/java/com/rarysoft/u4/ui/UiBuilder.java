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

import com.rarysoft.u4.game.Game;
import com.rarysoft.u4.ui.graphics.Charset;
import com.rarysoft.u4.ui.graphics.ExtendedCharset;
import com.rarysoft.u4.game.Tiles;

import javax.swing.*;
import java.awt.*;

public class UiBuilder {
    public void buildGamePanel(JFrame gameWindow, Game game, Tiles tiles, Charset charset) {
        GameViewRenderer gameViewRenderer = new GameViewRenderer(tiles, charset, new ExtendedCharset());
        GamePanel gamePanel = new GamePanel(gameViewRenderer);
        gameWindow.add(gamePanel, BorderLayout.CENTER);
        game.addInformationListener(new CommunicationListener(gamePanel, gamePanel, gamePanel));
        game.addViewListener(new GameListener(gamePanel));
    }

    public JFrame buildGameWindow(String title) {
        return new JFrame(title);
    }
}
