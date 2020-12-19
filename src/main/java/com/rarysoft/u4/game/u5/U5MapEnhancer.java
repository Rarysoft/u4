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
package com.rarysoft.u4.game.u5;

import com.rarysoft.u4.game.Map;
import com.rarysoft.u4.game.MapEnhancer;
import com.rarysoft.u4.game.Tile;

import java.util.Optional;

public class U5MapEnhancer implements MapEnhancer {
    @Override
    public Optional<Tile> replacementTile(Map map, int row, int col) {
        switch (map.location()) {
            case TRINSIC:
                if (row == 2) {
                    if (col == 1) {
                        return Optional.of(Tile.LEFT);
                    }
                    if (col == 9) {
                        return Optional.of(Tile.RIGHT);
                    }
                    if (col == 10) {
                        return Optional.of(Tile.BRICK_WALL);
                    }
                }
                else if (row == 4) {
                    if (col >= 10 && col <= 12) {
                        return Optional.of(Tile.BRICK_WALL);
                    }
                }
                break;
            default:
                break;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Tile> overlayTile(Map map, Tile[][] data, Tile tile, int row, int col) {
        Tile overlayTile = null;
        if (! (row < 1 || row >= map.height() - 1 || col < 1 || col >= map.width() - 1)) {
            Tile northTile = data[row - 1][col];
            Tile eastTile = data[row][col + 1];
            Tile southTile = data[row + 1][col];
            Tile westTile = data[row][col - 1];
            if (isWaterTile(tile)) {
                if (isWaterTile(northTile) && isGroundTile(westTile) && isGroundTile(southTile)) {
                    overlayTile = Tile.BEACH_SW;
                }
                else if (isWaterTile(northTile) && isGroundTile(eastTile) && isGroundTile(southTile)) {
                    overlayTile = Tile.BEACH_SE;
                }
                else if (isWaterTile(southTile) && isGroundTile(westTile) && isGroundTile(northTile)) {
                    overlayTile = Tile.BEACH_NW;
                }
                else if (isWaterTile(southTile) && isGroundTile(eastTile) && isGroundTile(northTile)) {
                    overlayTile = Tile.BEACH_NE;
                }
            }
        }
        return Optional.ofNullable(overlayTile);
    }

    private boolean isWaterTile(Tile tile) {
        return tile == Tile.DEEP_WATER || tile == Tile.MEDIUM_WATER || tile == Tile.SHALLOW_WATER;
    }

    private boolean isGroundTile(Tile tile) {
        return tile == Tile.GRASSLANDS
                || tile == Tile.SWAMP
                || tile == Tile.SCRUBLAND
                || tile == Tile.FOREST
                || tile == Tile.HILLS
                || tile == Tile.MOUNTAINS
                || tile == Tile.TOWN
                || tile == Tile.BEACH_N
                || tile == Tile.BEACH_E
                || tile == Tile.BEACH_S
                || tile == Tile.BEACH_W;
    }
}
