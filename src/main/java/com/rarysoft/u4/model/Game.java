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
package com.rarysoft.u4.model;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class Game {
    // Note: view radius is one larger than what will actually be shown on screen, which allows the display to do
    // some necessary analysis on the tiles around each visible tile
    private static final int VIEW_RADIUS = 10;

    private final Set<DisplayListener> displayListeners;

    private final Maps maps;

    private GameState gameState;

    private int animationCycle;

    public Game(Maps maps) {
        this.displayListeners = new HashSet<>();
        this.maps = maps;
    }

    public void addDisplayListener(DisplayListener displayListener) {
        displayListeners.add(displayListener);
    }

    public void start(GameState gameState) {
        this.gameState = gameState;
        initializeAnimation();
        updateBackground();
    }

    public void onMoveUp() {
        gameState.decreaseY();
        updateBackground();
    }

    public void onMoveDown() {
        gameState.increaseY();
        updateBackground();
    }

    public void onMoveLeft() {
        gameState.decreaseX();
        updateBackground();
    }

    public void onMoveRight() {
        gameState.increaseX();
        updateBackground();
    }

    private void initializeAnimation() {
        animationCycle = 0;
        new Timer(200, event -> {
            animationCycle = animationCycle + 1 < 16 ? animationCycle + 1 : 0;
            updateBackground();
        }).start();
    }

    private void updateBackground() {
        displayListeners.forEach(displayListener -> displayListener.backgroundUpdated(maps.world().view(gameState.x(), gameState.y(), VIEW_RADIUS), animationCycle));
    }
}