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
    private final RenderedTile[][] area;

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
            boolean isBlocked = false;
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
                        isBlocked = true;
                        break;
                    }
                }
            }
            else {
                for (int x = westernCoordinate.x(); x < easternCoordinate.x(); x ++) {
                    int y = yForX(x, slope);
                    if (isBlocked(targetCoordinate, x, y)) {
                        isBlocked = true;
                        break;
                    }
                }
            }
            if (! isBlocked) {
                return true;
            }
        }
        return false;
    }

    private Coordinate[] findTargetCoordinates(Coordinate coordinate) {
        Coordinate[] alternativeTargetCoordinates;
        // We'll look for a sight-line to three different points in the tile
        switch (coordinate.region()) {
            case NORTHEAST:
                alternativeTargetCoordinates = findAlternativeTargetCoordinates(coordinate.toTheWest(), coordinate.toTheSouth(), coordinate.toTheSouthwest());
                if (alternativeTargetCoordinates.length > 0) {
                    return alternativeTargetCoordinates;
                }
                return new Coordinate[] { coordinate.atSouthwestCorner(), coordinate.atSouthSide(), coordinate.atWestSide() };

            case SOUTHEAST:
                alternativeTargetCoordinates = findAlternativeTargetCoordinates(coordinate.toTheWest(), coordinate.toTheNorth(), coordinate.toTheNorthwest());
                if (alternativeTargetCoordinates.length > 0) {
                    return alternativeTargetCoordinates;
                }
                return new Coordinate[] { coordinate.atNorthwestCorner(), coordinate.atNorthSide(), coordinate.atWestSide() };

            case SOUTHWEST:
                alternativeTargetCoordinates = findAlternativeTargetCoordinates(coordinate.toTheEast(), coordinate.toTheNorth(), coordinate.toTheNortheast());
                if (alternativeTargetCoordinates.length > 0) {
                    return alternativeTargetCoordinates;
                }
                return new Coordinate[] { coordinate.atNortheastCorner(), coordinate.atNorthSide(), coordinate.atEastSide() };

            case NORTHWEST:
                alternativeTargetCoordinates = findAlternativeTargetCoordinates(coordinate.toTheEast(), coordinate.toTheSouth(), coordinate.toTheSoutheast());
                if (alternativeTargetCoordinates.length > 0) {
                    return alternativeTargetCoordinates;
                }
                return new Coordinate[] { coordinate.atSoutheastCorner(), coordinate.atSouthSide(), coordinate.atEastSide() };

            default:
                // We only handle the above four cases; the rest should have been ruled out before calling this method
                return new Coordinate[0];
        }
    }

    private Coordinate[] findAlternativeTargetCoordinates(Coordinate colAdjacentCoordinate, Coordinate rowAdjacentCoordinate, Coordinate cornerAdjacentCoordinate) {
        // Special case: if the two adjacent on the visible side are opaque but the corner one is not, this will be
        // visible if one of the adjacent tiles is visible, determined by whichever is not center-aligned
        if (area[colAdjacentCoordinate.row()][colAdjacentCoordinate.col()].tile().opaque() &&
                area[rowAdjacentCoordinate.row()][rowAdjacentCoordinate.col()].tile().opaque() &&
                ! area[cornerAdjacentCoordinate.row()][cornerAdjacentCoordinate.col()].tile().opaque()) {
            return new Coordinate[] { rowAdjacentCoordinate.isCenterRow() ? colAdjacentCoordinate.atSideFacingCenterRow() : rowAdjacentCoordinate.atSideFacingCenterCol() };
        }
        return new Coordinate[] {};
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
