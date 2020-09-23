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

import java.math.BigDecimal;

public class Visibility {
    private RenderedTile[][] area;

    public Visibility(RenderedTile[][] area) {
        this.area = area;
    }

    public boolean isVisibleToPlayer(Coordinate coordinate) {
        if (area[coordinate.row()][coordinate.col()] == null) {
            return true;
        }
        Coordinate playerCoordinate = Coordinate.center();
        // Simplest case: the player tile and all adjacent tiles are always visible
        if (coordinate.isSameRowCol(playerCoordinate) || coordinate.isAdjacentTo(playerCoordinate)) {
            return true;
        }
        // Simple case: straight N, E, S, or W
        switch (coordinate.region()) {
            case NORTH:
                // Check for opaque tiles southward toward the player
                for (int checkRow = coordinate.row() + 1; checkRow < playerCoordinate.row(); checkRow ++) {
                    if (area[checkRow][coordinate.col()].tile().opaque()) {
                        return false;
                    }
                }
                return true;
            case EAST:
                // Check for opaque tiles westward toward the player
                for (int checkCol = coordinate.col() - 1; checkCol > playerCoordinate.col(); checkCol --) {
                    if (area[coordinate.row()][checkCol].tile().opaque()) {
                        return false;
                    }
                }
                return true;
            case SOUTH:
                // Check for opaque tiles northward toward the player
                for (int checkRow = coordinate.row() - 1; checkRow > playerCoordinate.row(); checkRow --) {
                    if (area[checkRow][coordinate.col()].tile().opaque()) {
                        return false;
                    }
                }
                return true;
            case WEST:
                // Check for opaque tiles eastward toward the player
                for (int checkCol = coordinate.col() + 1; checkCol < playerCoordinate.col(); checkCol ++) {
                    if (area[coordinate.row()][checkCol].tile().opaque()) {
                        return false;
                    }
                }
                return true;
        }
        // Complicated case
        Coordinate[] targetCoordinates = findTargetCoordinates(coordinate);
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
            // For steep slopes, iterate through Y and calculate X, otherwise iterate through X and calculate Y; this
            // is so we don't get really steep upward slopes where we have the same Y value for multiple X values, and
            // end up skipping tiles as we climb the slope
            if (slope.compareTo(BigDecimal.ONE) > 0 || slope.compareTo(BigDecimal.ONE.negate()) < 0) {
                Coordinate southernCoordinate = westernCoordinate;
                Coordinate northernCoordinate = easternCoordinate;
                if (southernCoordinate.isNorthOf(northernCoordinate)) {
                    northernCoordinate = westernCoordinate;
                    southernCoordinate = easternCoordinate;
                }
                for (int y = southernCoordinate.y(); y < northernCoordinate.y(); y ++) {
                    int x = xForY(y, slope);
                    if (isBlocked(targetCoordinate, x, y)) {
                        return false;
                    }
                }
            }
            else {
                for (int x = westernCoordinate.x(); x < easternCoordinate.x(); x ++) {
                    int y = yForX(x, slope);
                    if (isBlocked(targetCoordinate, x, y)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private Coordinate[] findTargetCoordinates(Coordinate coordinate) {
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
                westTileOpaque = area[coordinate.row()][coordinate.col() - 1].tile().opaque();
                southwestTileOpaque = area[coordinate.row() + 1][coordinate.col() - 1].tile().opaque();
                southTileOpaque = area[coordinate.row() + 1][coordinate.col()].tile().opaque();
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
                westTileOpaque = area[coordinate.row()][coordinate.col() - 1].tile().opaque();
                northwestTileOpaque = area[coordinate.row() - 1][coordinate.col() - 1].tile().opaque();
                northTileOpaque = area[coordinate.row() - 1][coordinate.col()].tile().opaque();
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
                eastTileOpaque = area[coordinate.row()][coordinate.col() + 1].tile().opaque();
                northeastTileOpaque = area[coordinate.row() - 1][coordinate.col() + 1].tile().opaque();
                northTileOpaque = area[coordinate.row() - 1][coordinate.col()].tile().opaque();
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
                eastTileOpaque = area[coordinate.row()][coordinate.col() + 1].tile().opaque();
                southeastTileOpaque = area[coordinate.row() + 1][coordinate.col() + 1].tile().opaque();
                southTileOpaque = area[coordinate.row() + 1][coordinate.col()].tile().opaque();
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

    private int xForY(int y, BigDecimal slope) {
        return BigDecimal.valueOf(y).divide(slope, 0, BigDecimal.ROUND_HALF_UP).intValue();
    }

    private int yForX(int x, BigDecimal slope) {
        return slope.multiply(BigDecimal.valueOf(x)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }

    private boolean isBlocked(Coordinate targetCoordinate, int passingThroughX, int passingThroughY) {
        Coordinate passingThroughCoordinate = Coordinate.forXY(passingThroughX, passingThroughY);
        if (passingThroughCoordinate.isCenter() || passingThroughCoordinate.isSameRowCol(targetCoordinate)) {
            // The tile can't be blocked by the player or by itself
            return false;
        }
        if (area[passingThroughCoordinate.row()][passingThroughCoordinate.col()].tile().opaque()) {
            return true;
        }
        return false;
    }
}
