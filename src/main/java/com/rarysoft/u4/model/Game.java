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
import java.util.Random;
import java.util.Set;

public class Game {
    // Note: view radius is one larger than what will actually be shown on screen, which allows the display to do
    // some necessary analysis on the tiles around each visible tile
    private static final int VIEW_RADIUS = 10;

    private final Set<DisplayListener> displayListeners;

    private final Random random;

    private GameState gameState;

    private int animationCycle;

    public Game() {
        this.displayListeners = new HashSet<>();
        this.random = new Random();
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
        Tile tile = gameState.tileAt(gameState.x(), gameState.y() - 1);
        if (tile == null) {
            gameState.returnToSurface();
        }
        else {
            if (tile.walkability() == 0) {
                moveBlocked();
                return;
            }
            // Special case: don't allow northward exit from LB's castle
            if (gameState.tileAt(gameState.x(), gameState.y()) == Tile.LORD_BRITISHS_CASTLE_ENTRANCE) {
                moveBlocked();
                return;
            }
            if (! allowWalkTo(tile)) {
                moveSlowed();
                return;
            }
            gameState.decreaseY();
            if (tile.type() == TileType.PORTAL) {
                gameState.enter();
            }
        }
        updateBackground();
    }

    public void onMoveDown() {
        Tile tile = gameState.tileAt(gameState.x(), gameState.y() + 1);
        if (tile == null) {
            gameState.returnToSurface();
        }
        else {
            if (tile.walkability() == 0) {
                moveBlocked();
                return;
            }
            // Special case: don't allow entry to LB's castle from the north
            if (tile == Tile.LORD_BRITISHS_CASTLE_ENTRANCE) {
                moveBlocked();
                return;
            }
            if (! allowWalkTo(tile)) {
                moveSlowed();
                return;
            }
            gameState.increaseY();
            if (tile.type() == TileType.PORTAL) {
                gameState.enter();
            }
        }
        updateBackground();
    }

    public void onMoveLeft() {
        Tile tile = gameState.tileAt(gameState.x() - 1, gameState.y());
        if (tile == null) {
            gameState.returnToSurface();
        }
        else {
            if (tile.walkability() == 0) {
                moveBlocked();
                return;
            }
            if (! allowWalkTo(tile)) {
                moveSlowed();
                return;
            }
            gameState.decreaseX();
            if (tile.type() == TileType.PORTAL) {
                gameState.enter();
            }
        }
        updateBackground();
    }

    public void onMoveRight() {
        Tile tile = gameState.tileAt(gameState.x() + 1, gameState.y());
        if (tile == null) {
            gameState.returnToSurface();
        }
        else {
            if (tile.walkability() == 0) {
                moveBlocked();
                return;
            }
            if (! allowWalkTo(tile)) {
                moveSlowed();
                return;
            }
            gameState.increaseX();
            if (tile.type() == TileType.PORTAL) {
                gameState.enter();
            }
        }
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
        RenderedTile[][] playerView = determinePlayerView(gameState.mapView(VIEW_RADIUS));
        displayListeners.forEach(displayListener -> displayListener.backgroundUpdated(playerView, animationCycle));
    }

    private void moveBlocked() {
        displayListeners.forEach(DisplayListener::moveBlocked);
    }

    private void moveSlowed() {
        displayListeners.forEach(DisplayListener::moveSlowed);
    }

    private boolean allowWalkTo(Tile tile) {
        if (tile.walkability() == 100) {
            return true;
        }
        return random.nextInt(100) < tile.walkability();
    }

    private RenderedTile[][] determinePlayerView(Tile[][] view) {
        int size = view.length;
        int center = (size - 1) / 2;
        RenderedTile[][] playerView = new RenderedTile[size][size];
        for (int row = 0; row < size; row ++) {
            for (int col = 0; col < size; col ++) {
                if (isInStandardView(row, col)) {
                    playerView[row][col] = new RenderedTile(view[row][col]);
                }
                else {
                    playerView[row][col] = new RenderedTile(view[row][col]).hidden();
                }
            }
        }
        for (int row = center - 1; row <= center + 1; row ++) {
            for (int col = center - 1; col <= center + 1; col ++) {
                RenderedTile renderedTile = playerView[row][col];
                if (row == center && col == center || ! renderedTile.render() || ! renderedTile.tile().opaque()) {
                    continue;
                }
                //
            }
        }
        return playerView;
    }

    private boolean isInStandardView(int row, int col) {
        // Eliminate corners to make a circular view; this could be done mathematically, but brute force is simple
        switch (row) {
            case 1:
            case 19:
                return col > 8 && col < 12;
            case 2:
            case 18:
                return col > 6 && col < 14;
            case 3:
            case 17:
                return col > 4 && col < 16;
            case 4:
            case 16:
                return col > 3 && col < 17;
            case 5:
            case 6:
            case 15:
            case 14:
                return col > 2 && col < 18;
            case 7:
            case 8:
            case 13:
            case 12:
                return col > 1 && col < 19;
            case 9:
            case 10:
            case 11:
                return true;
            default:
                return false;
        }
    }
}
