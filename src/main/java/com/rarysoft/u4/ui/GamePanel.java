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

import com.rarysoft.u4.model.RenderedTile;
import com.rarysoft.u4.model.graphics.Tiles;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GamePanel extends JPanel implements BorderProvider, GameProvider, StatisticsProvider, CommunicationProvider {
    private final GameViewRenderer gameViewRenderer;

    public GamePanel(GameViewRenderer gameViewRenderer) {
        this.gameViewRenderer = gameViewRenderer;
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(21 * Tiles.TILE_WIDTH * 3, 21 * Tiles.TILE_HEIGHT * 3));
    }

    @Override
    public void showGameView(RenderedTile[][] background, int animationCycle) {
        gameViewRenderer.setBackground(background);
        gameViewRenderer.setAnimationCycle(animationCycle);
        this.getParent().repaint();
    }

    @Override
    public void showText(List<String> textLines) {
        gameViewRenderer.setTextLines(textLines);
        gameViewRenderer.setAllowInput(false);
        this.getParent().repaint();
    }

    @Override
    public void showTextAndAwaitResponse(List<String> textLines) {
        gameViewRenderer.setTextLines(textLines);
        gameViewRenderer.setAllowInput(true);
        this.getParent().repaint();
    }

    @Override
    public void showInput(String input) {
        gameViewRenderer.setInputLine(input);
        this.getParent().repaint();
    }

    @Override
    public void drawBorder(int phaseOfTrammel, int phaseOfFelucca, int windDirection) {
        gameViewRenderer.setPhaseOfTrammel(phaseOfTrammel);
        gameViewRenderer.setPhaseOfFelucca(phaseOfFelucca);
        gameViewRenderer.setWindDirection(windDirection);
        this.getParent().repaint();
    }

    @Override
    public void showStatistics(List<String> textLines) {
        gameViewRenderer.setStatistics(textLines);
        this.getParent().repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Dimension windowSize = this.getParent().getSize();
        gameViewRenderer.drawGameView(graphics, windowSize.width, windowSize.height);
    }
}
