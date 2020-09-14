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
import java.math.BigDecimal;
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
        RenderedTile[][] playerView = new RenderedTile[size][size];
        for (int row = 0; row < size; row ++) {
            for (int col = 0; col < size; col ++) {
                if (isInStandardView(row, col) && isVisibleToPlayer(playerView, row, col)) {
                    playerView[row][col] = new RenderedTile(view[row][col]);
                }
                else {
                    playerView[row][col] = new RenderedTile(view[row][col]).hidden();
                }
            }
        }
        for (int row = 0; row < size; row ++) {
            for (int col = 0; col < size; col ++) {
                if (playerView[row][col].render() && isVisibleToPlayer(playerView, row, col)) {
                    continue;
                }
                playerView[row][col] = new RenderedTile(view[row][col]).hidden();
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

    private boolean isVisibleToPlayer(RenderedTile[][] view, int row, int col) {
        if (view[row][col] == null) {
            return true;
        }
        int size = view.length;
        int center = (size - 1) / 2;
        // Simple case: straight N, S, E, or W
        if (row == center) {
            if (col < center) {
                // Check for opaque tiles eastward toward the player
                for (int x = col; x < center; x ++) {
                    if (view[row][x].tile().opaque()) {
                        return false;
                    }
                }
            }
            else {
                // Check for opaque tiles westward toward the player
                for (int x = col; x > center; x --) {
                    if (view[row][x].tile().opaque()) {
                        return false;
                    }
                }
            }
        }
        else if (col == center) {
            if (row < center) {
                // Check for opaque tiles southward toward the player
                for (int y = row; y < center; y ++) {
                    if (view[y][col].tile().opaque()) {
                        return false;
                    }
                }
            }
            else {
                // Check for opaque tiles northward toward the player
                for (int y = row; y > center; y --) {
                    if (view[y][col].tile().opaque()) {
                        return false;
                    }
                }
            }
        }
        else {
            // Complicated case
            int x1 = 0; // Treat center as 0,0
            int y1 = 0;
            int x2 = colToX(col);
            int y2 = rowToY(row);
            int rise = y2 - y1;
            int run = x2 - x1;
            // Negate the slope since the y-axis is inverted
            BigDecimal slope = BigDecimal.valueOf(rise).divide(BigDecimal.valueOf(run), 8, BigDecimal.ROUND_HALF_UP);
            if (x1 < x2) {
                for (int x = x1; x < x2; x ++) {
                    int y = slope.multiply(BigDecimal.valueOf(x)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
                    int passingThroughRow = yToRow(y);
                    int passingThroughCol = xToCol(x);
                    if (view[passingThroughRow][passingThroughCol].tile().opaque()) {
                        return false;
                    }
                }
            }
            else {
                for (int x = x2; x < x1; x ++) {
                    int y = slope.multiply(BigDecimal.valueOf(x)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
                    int passingThroughRow = yToRow(y);
                    int passingThroughCol = xToCol(x);
                    if (view[passingThroughRow][passingThroughCol].tile().opaque()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int rowToY(int row) {
        // Convert a row index, which starts at the top and increases going down, to a proper y value where each tile
        // is itself an 11x11 grid with the center of the player tile at 0,0 and the y value representing the value at
        // the center of the tile
        int center = 10;
        int TILE_SIZE = 11;
        if (row == center) {
            return 0;
        }
        return (center - row) * TILE_SIZE;
    }

    public int colToX(int col) {
        // Convert a col index to a proper x value where each tile is itself an 11x11 grid with the center of the
        // player tile at 0,0 and the x value representing the value at the center of the tile
        int center = 10;
        int TILE_SIZE = 11;
        if (col == center) {
            return 0;
        }
        return (col - center) * TILE_SIZE;
    }

    public int yToRow(int y) {
        int center = 10;
        int TILE_SIZE = 11;
        if (y == 0) {
            return center;
        }
        return center - y / TILE_SIZE;
    }

    public int xToCol(int x) {
        int center = 10;
        int TILE_SIZE = 11;
        if (x == 0) {
            return center;
        }
        return center + x / TILE_SIZE;
    }
}
