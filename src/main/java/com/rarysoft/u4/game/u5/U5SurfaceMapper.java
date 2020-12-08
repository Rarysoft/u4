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

import com.rarysoft.u4.game.SurfaceMapper;
import com.rarysoft.u4.game.Tile;

public class U5SurfaceMapper implements SurfaceMapper {
    @Override
    public Tile[][] map(Tile[][] data) {
        // Rivers are added before beaches so that it doesn't try to put beaches next to rivers, since river tiles
        // already have beaches on them.
        Tile[][] dataWithRivers = mapRivers(data);
        Tile[][] dataWithBeaches = mapBeaches(dataWithRivers);
        return mapAllOtherFeatures(dataWithBeaches);
    }

    private Tile[][] mapRivers(Tile[][] data) {
        Tile [][] mappedData = new Tile[256][256];
        for (int row = 0; row < 256; row ++) {
            for (int col = 0; col < 256; col ++) {
                Tile tile = data[row][col];
                if (row == 0 || row == 255 || col == 0 || col == 255) {
                    mappedData[row][col] = tile;
                    continue;
                }
                Tile northTile = data[row - 1][col];
                Tile northeastTile = data[row - 1][col + 1];
                Tile eastTile = data[row][col + 1];
                Tile southeastTile = data[row + 1][col + 1];
                Tile southTile = data[row + 1][col];
                Tile southwestTile = data[row + 1][col - 1];
                Tile westTile = data[row][col - 1];
                Tile northwestTile = data[row - 1][col - 1];
                if (isWaterTile(tile)) {
                    if (tile == Tile.SHALLOW_WATER) {
                        if (isGroundTile(northTile) && isGroundTile(southTile)) {
                            if (isGroundTile(westTile)) {
                                mappedData[row][col] = Tile.RIVER_END_WEST;
                                continue;
                            }
                            if (isGroundTile(eastTile)) {
                                mappedData[row][col] = Tile.RIVER_END_EAST;
                                continue;
                            }
                            mappedData[row][col] = Tile.RIVER_EW;
                            continue;
                        }
                        if (isGroundTile(eastTile) && isGroundTile(westTile)) {
                            if (isGroundTile(northTile)) {
                                mappedData[row][col] = Tile.RIVER_END_NORTH;
                                continue;
                            }
                            if (isGroundTile(southTile)) {
                                mappedData[row][col] = Tile.RIVER_END_SOUTH;
                                continue;
                            }
                            mappedData[row][col] = Tile.RIVER_NS;
                            continue;
                        }
                        if (isGroundTile(westTile) && isGroundTile(southTile) && isGroundTile(northeastTile)) {
                            mappedData[row][col] = Tile.RIVER_NE;
                            continue;
                        }
                        if (isGroundTile(northTile) && isGroundTile(westTile) && isGroundTile(southeastTile)) {
                            mappedData[row][col] = Tile.RIVER_SE;
                            continue;
                        }
                        if (isGroundTile(eastTile) && isGroundTile(northTile) && isGroundTile(southwestTile)) {
                            mappedData[row][col] = Tile.RIVER_SW;
                            continue;
                        }
                        if (isGroundTile(southTile) && isGroundTile(eastTile) && isGroundTile(northwestTile)) {
                            mappedData[row][col] = Tile.RIVER_NW;
                            continue;
                        }
                        if (isGroundTile(westTile) && isGroundTile(northeastTile) && isGroundTile(southeastTile)) {
                            mappedData[row][col] = Tile.RIVER_NES;
                            continue;
                        }
                        if (isGroundTile(northTile) && isGroundTile(southwestTile) && isGroundTile(southeastTile)) {
                            mappedData[row][col] = Tile.RIVER_WSE;
                            continue;
                        }
                        if (isGroundTile(eastTile) && isGroundTile(northwestTile) && isGroundTile(southwestTile)) {
                            mappedData[row][col] = Tile.RIVER_NWS;
                            continue;
                        }
                        if (isGroundTile(southTile) && isGroundTile(northwestTile) && isGroundTile(northeastTile)) {
                            mappedData[row][col] = Tile.RIVER_WNE;
                            continue;
                        }
                    }
                    mappedData[row][col] = tile;
                    continue;
                }
                mappedData[row][col] = tile;
            }
        }
        return mappedData;
    }

    private Tile[][] mapBeaches(Tile[][] data) {
        Tile [][] mappedData = new Tile[256][256];
        for (int row = 0; row < 256; row ++) {
            for (int col = 0; col < 256; col ++) {
                Tile tile = data[row][col];
                if (row == 0 || row == 255 || col == 0 || col == 255) {
                    mappedData[row][col] = tile;
                    continue;
                }
                Tile northTile = data[row - 1][col];
                Tile northeastTile = data[row - 1][col + 1];
                Tile eastTile = data[row][col + 1];
                Tile southeastTile = data[row + 1][col + 1];
                Tile southTile = data[row + 1][col];
                Tile southwestTile = data[row + 1][col - 1];
                Tile westTile = data[row][col - 1];
                Tile northwestTile = data[row - 1][col - 1];
                if (isGroundTile(tile)) {
                    if (isWaterTile(northTile) && (isWaterTile(northeastTile) || isLandEdgeTile(northeastTile)) && (isWaterTile(northwestTile) || isLandEdgeTile(northwestTile))) {
                        mappedData[row][col] = Tile.BEACH_N;
                        continue;
                    }
                    if (isWaterTile(eastTile) && (isWaterTile(northeastTile) || isLandEdgeTile(northeastTile)) && (isWaterTile(southeastTile) || isLandEdgeTile(southeastTile))) {
                        mappedData[row][col] = Tile.BEACH_E;
                        continue;
                    }
                    if (isWaterTile(southTile) && (isWaterTile(southeastTile) || isLandEdgeTile(southeastTile)) && (isWaterTile(southwestTile) || isLandEdgeTile(southwestTile))) {
                        mappedData[row][col] = Tile.BEACH_S;
                        continue;
                    }
                    if (isWaterTile(westTile) && (isWaterTile(northwestTile) || isLandEdgeTile(northwestTile)) && (isWaterTile(southwestTile) || isLandEdgeTile(southwestTile))) {
                        mappedData[row][col] = Tile.BEACH_W;
                        continue;
                    }
                    mappedData[row][col] = tile;
                    continue;
                }
                mappedData[row][col] = tile;
            }
        }
        return mappedData;
    }

    private Tile[][] mapAllOtherFeatures(Tile[][] data) {
        Tile [][] mappedData = new Tile[256][256];
        for (int row = 0; row < 256; row ++) {
            for (int col = 0; col < 256; col ++) {
                Tile tile = data[row][col];
                if (row == 0 || row == 255 || col == 0 || col == 255) {
                    mappedData[row][col] = tile;
                    continue;
                }
                if (isGroundTile(tile)) {
                    Tile southTile = data[row + 1][col];
                    if (southTile == Tile.CASTLE_BRITANNIA_WEST) {
                        mappedData[row][col] = Tile.CASTLE_BRITANNIA_NW;
                        continue;
                    }
                    if (southTile == Tile.CASTLE_BRITANNIA_ENTRANCE) {
                        mappedData[row][col] = Tile.CASTLE_BRITANNIA_N;
                        continue;
                    }
                    if (southTile == Tile.CASTLE_BRITANNIA_EAST) {
                        mappedData[row][col] = Tile.CASTLE_BRITANNIA_NE;
                        continue;
                    }
                    mappedData[row][col] = tile;
                    continue;
                }
                mappedData[row][col] = tile;
            }
        }
        return mappedData;
    }

    private boolean isWaterTile(Tile tile) {
        return tile == Tile.DEEP_WATER || tile == Tile.MEDIUM_WATER || tile == Tile.SHALLOW_WATER;
    }

    private boolean isGroundTile(Tile tile) {
        return tile == Tile.GRASSLANDS || tile == Tile.SWAMP || tile == Tile.SCRUBLAND || tile == Tile.FOREST || tile == Tile.HILLS || tile == Tile.MOUNTAINS || tile == Tile.TOWN;
    }

    private boolean isLandEdgeTile(Tile tile) {
        return tile == Tile.BEACH_NE || tile == Tile.BEACH_NW || tile == Tile.BEACH_SW || tile == Tile.BEACH_SE;
    }
}
