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
                if (isInStandardView(row, col)) {
                    playerView[row][col] = new RenderedTile(view[row][col]);
                }
                else {
                    playerView[row][col] = new RenderedTile(view[row][col]).hidden();
                }
            }
        }
        for (int row = 0; row < size; row ++) {
            for (int col = 0; col < size; col ++) {
                if (playerView[row][col].render() && isVisibleToPlayer(playerView, Coordinate.forRowCol(row, col))) {
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

    private boolean isVisibleToPlayer(RenderedTile[][] view, Coordinate coordinate) {
        if (view[coordinate.row()][coordinate.col()] == null) {
            return true;
        }
        Coordinate playerCoordinate = Coordinate.center();
        // Simplest case: the player tile and all adjacent tiles are always visible
        if (coordinate.equals(playerCoordinate) || coordinate.isAdjacentTo(playerCoordinate)) {
            return true;
        }
        // Simple case: straight N, E, S, or W
        switch (coordinate.region()) {
            case NORTH:
                // Check for opaque tiles southward toward the player
                for (int checkRow = coordinate.row() + 1; checkRow < playerCoordinate.row(); checkRow ++) {
                    if (view[checkRow][coordinate.col()].tile().opaque()) {
                        return false;
                    }
                }
                return true;
            case EAST:
                // Check for opaque tiles westward toward the player
                for (int checkCol = coordinate.col() - 1; checkCol > playerCoordinate.col(); checkCol --) {
                    if (view[coordinate.row()][checkCol].tile().opaque()) {
                        return false;
                    }
                }
                return true;
            case SOUTH:
                // Check for opaque tiles northward toward the player
                for (int checkRow = coordinate.row() - 1; checkRow > playerCoordinate.row(); checkRow --) {
                    if (view[checkRow][coordinate.col()].tile().opaque()) {
                        return false;
                    }
                }
                return true;
            case WEST:
                // Check for opaque tiles eastward toward the player
                for (int checkCol = coordinate.col() + 1; checkCol < playerCoordinate.col(); checkCol ++) {
                    if (view[coordinate.row()][checkCol].tile().opaque()) {
                        return false;
                    }
                }
                return true;
        }
        // Complicated case
        Coordinate[] targetCoordinates = findTargetCoordinates(view, coordinate);
        if (targetCoordinates.length == 0) {
            return false;
        }
        for (Coordinate targetCoordinate : targetCoordinates) {
            Coordinate westernCoordinate = playerCoordinate;
            Coordinate easternCoordinate = targetCoordinate;
            if (westernCoordinate.isEastOf(easternCoordinate)) {
                // Swap the coordinates so we can loop ascending from west to east
                westernCoordinate = targetCoordinate;
                easternCoordinate = playerCoordinate;
            }
            BigDecimal slope = westernCoordinate.slopeTo(easternCoordinate);
            for (int x = westernCoordinate.x(); x < easternCoordinate.x(); x ++) {
                int y = slope.multiply(BigDecimal.valueOf(x)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
                Coordinate passingThroughCoordinate = Coordinate.forXY(x, y);
                if (passingThroughCoordinate.isCenter() || passingThroughCoordinate.isSameRowCol(targetCoordinate)) {
                    // The tile can't be blocked by the player or by itself
                    continue;
                }
                if (view[passingThroughCoordinate.row()][passingThroughCoordinate.col()].tile().opaque()) {
                    return false;
                }
            }
        }
        return true;
    }

    private Coordinate[] findTargetCoordinates(RenderedTile[][] view, Coordinate coordinate) {
        // Determine which surrounding tiles to analyze based on the region
        boolean northTileOpaque;
        boolean northeastTileOpaque;
        boolean eastTileOpaque;
        boolean southeastTileOpaque;
        boolean southTileOpaque;
        boolean southwestTileOpaque;
        boolean westTileOpaque;
        boolean northwestTileOpaque;

        switch (coordinate.region()) {
            case NORTHEAST:
                westTileOpaque = view[coordinate.row()][coordinate.col() - 1].tile().opaque();
                southwestTileOpaque = view[coordinate.row() + 1][coordinate.col() - 1].tile().opaque();
                southTileOpaque = view[coordinate.row() + 1][coordinate.col()].tile().opaque();
                if (westTileOpaque && southwestTileOpaque && southTileOpaque) {
                    // Totally blocked, no need to map a sight-line to any coordinates at all
                    return new Coordinate[0];
                }
                if (westTileOpaque && ! southTileOpaque) {
                    return new Coordinate[] { coordinate.atSouthSide() };
                }
                if (southTileOpaque && ! westTileOpaque) {
                    return new Coordinate[] { coordinate.atWestSide() };
                }
                if (southwestTileOpaque) {
                    return new Coordinate[] { coordinate.atSouthSide(), coordinate.atWestSide() };
                }
                if (! (westTileOpaque)) {
                    return new Coordinate[] { coordinate };
                }
                return new Coordinate[] { coordinate.atSouthwestCorner() };

            case SOUTHEAST:
                westTileOpaque = view[coordinate.row()][coordinate.col() - 1].tile().opaque();
                northwestTileOpaque = view[coordinate.row() - 1][coordinate.col() - 1].tile().opaque();
                northTileOpaque = view[coordinate.row() - 1][coordinate.col()].tile().opaque();
                if (westTileOpaque && northwestTileOpaque && northTileOpaque) {
                    // Totally blocked, no need to map a sight-line to any coordinates at all
                    return new Coordinate[0];
                }
                if (westTileOpaque && ! northTileOpaque) {
                    return new Coordinate[] { coordinate.atNorthSide() };
                }
                if (northTileOpaque && ! westTileOpaque) {
                    return new Coordinate[] { coordinate.atWestSide() };
                }
                if (northwestTileOpaque) {
                    return new Coordinate[] { coordinate.atNorthSide(), coordinate.atWestSide() };
                }
                if (! (westTileOpaque)) {
                    return new Coordinate[] { coordinate };
                }
                return new Coordinate[] { coordinate.atNorthwestCorner() };

            case SOUTHWEST:
                eastTileOpaque = view[coordinate.row()][coordinate.col() + 1].tile().opaque();
                northeastTileOpaque = view[coordinate.row() - 1][coordinate.col() + 1].tile().opaque();
                northTileOpaque = view[coordinate.row() - 1][coordinate.col()].tile().opaque();
                if (eastTileOpaque && northeastTileOpaque && northTileOpaque) {
                    // Totally blocked, no need to map a sight-line to any coordinates at all
                    return new Coordinate[0];
                }
                if (eastTileOpaque && ! northTileOpaque) {
                    return new Coordinate[] { coordinate.atNorthSide() };
                }
                if (northTileOpaque && ! eastTileOpaque) {
                    return new Coordinate[] { coordinate.atEastSide() };
                }
                if (northeastTileOpaque) {
                    return new Coordinate[] { coordinate.atNorthSide(), coordinate.atEastSide() };
                }
                if (! (eastTileOpaque)) {
                    return new Coordinate[] { coordinate };
                }
                return new Coordinate[] { coordinate.atNortheastCorner() };

            case NORTHWEST:
                eastTileOpaque = view[coordinate.row()][coordinate.col() + 1].tile().opaque();
                southeastTileOpaque = view[coordinate.row() + 1][coordinate.col() + 1].tile().opaque();
                southTileOpaque = view[coordinate.row() + 1][coordinate.col()].tile().opaque();
                if (eastTileOpaque && southeastTileOpaque && southTileOpaque) {
                    // Totally blocked, no need to map a sight-line to any coordinates at all
                    return new Coordinate[0];
                }
                if (eastTileOpaque && ! southTileOpaque) {
                    return new Coordinate[] { coordinate.atSouthSide() };
                }
                if (southTileOpaque && ! eastTileOpaque) {
                    return new Coordinate[] { coordinate.atEastSide() };
                }
                if (southeastTileOpaque) {
                    return new Coordinate[] { coordinate.atSouthSide(), coordinate.atEastSide() };
                }
                if (! (eastTileOpaque)) {
                    return new Coordinate[] { coordinate };
                }
                return new Coordinate[] { coordinate.atSoutheastCorner() };

            default:
                // We only handle the above four cases; the rest should have been ruled out before calling this method
                return new Coordinate[0];
        }
    }
}
