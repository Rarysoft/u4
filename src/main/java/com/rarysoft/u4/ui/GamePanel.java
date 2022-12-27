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

import com.rarysoft.u4.game.Tiles;
import com.rarysoft.u4.game.event.Event;
import com.rarysoft.u4.game.event.EventListener;
import com.rarysoft.u4.game.state.GameState;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements EventListener {
    private final GameViewRenderer gameViewRenderer;

    public GamePanel(GameViewRenderer gameViewRenderer) {
        this.gameViewRenderer = gameViewRenderer;
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(21 * Tiles.TILE_WIDTH * 3, 21 * Tiles.TILE_HEIGHT * 3));
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Dimension windowSize = this.getParent().getSize();
        gameViewRenderer.drawGameView(graphics, windowSize.width, windowSize.height);
    }

    @Override
    public void eventCompleted(Event event, GameState state) {
        switch (event) {
            case DISPLAY_UPDATED:
                gameViewRenderer.setBackground(state.getMapView());
                gameViewRenderer.setAnimationCycle(state.getCounter() % 16);
                gameViewRenderer.setPhaseOfTrammel(state.getPhaseOfTrammel());
                gameViewRenderer.setPhaseOfFelucca(state.getPhaseOfFelucca());
                gameViewRenderer.setWindDirection(state.getWinds());
                break;
            case INFORMATION_UPDATED:
                gameViewRenderer.setStatistics(state.getMessages());
                break;
            case COMMUNICATION_UPDATED:
                gameViewRenderer.setTextLines(state.getMessages());
                gameViewRenderer.setAllowInput(state.isRespondingToPerson());
                gameViewRenderer.setInputLine(state.getInput());
                break;
        }
        this.getParent().repaint();
    }
}
