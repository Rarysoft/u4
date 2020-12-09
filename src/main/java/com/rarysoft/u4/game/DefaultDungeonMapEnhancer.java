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
package com.rarysoft.u4.game;

public class DefaultDungeonMapEnhancer implements DungeonMapEnhancer {
    @Override
    public void enhance(Tile[][] data) {
        for (int row = DungeonLevel.borderWidth(); row < data.length - DungeonLevel.borderWidth() * 2; row += DungeonLevel.heightScale()) {
            for (int col = DungeonLevel.borderWidth(); col < data[row].length - DungeonLevel.borderWidth() * 2; col += DungeonLevel.widthScale()) {
                Tile tile = data[row][col];
                switch (tile) {
                    case UNLOCKED_DOOR:
                        handleDoor(data, row, col);
                        break;
                    case HIDDEN_PASSAGE:
                        handleHiddenPassage(data, row, col);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void handleDoor(Tile[][] data, int startRow, int startCol) {
        handlePassageway(data, startRow, startCol, Tile.TILE_FLOOR);
    }

    private void handleHiddenPassage(Tile[][] data, int startRow, int startCol) {
        handlePassageway(data, startRow, startCol, Tile.HIDDEN_PASSAGE);
    }

    private void handlePassageway(Tile[][] data, int startRow, int startCol, Tile entryTile) {
        int centerRow = startRow + (DungeonLevel.heightScale() - 1) / 2;
        int centerCol = startCol + (DungeonLevel.widthScale() - 1) / 2;
        boolean openNorth = data[startRow - 1][centerCol] == Tile.TILE_FLOOR;
        boolean openEast = data[centerRow][startCol + DungeonLevel.widthScale()] == Tile.TILE_FLOOR;
        boolean openSouth = data[startRow + DungeonLevel.heightScale()][centerCol] == Tile.TILE_FLOOR;
        boolean openWest = data[centerRow][startCol - 1] == Tile.TILE_FLOOR;
        for (int row = startRow; row < startRow + DungeonLevel.heightScale(); row ++) {
            for (int col = startCol; col < startCol + DungeonLevel.widthScale(); col ++) {
                if ((openNorth && row == startRow && col == centerCol)
                        || (openEast && col == startCol + DungeonLevel.widthScale() - 1 && row == centerRow)
                        || (openSouth && row == startRow + DungeonLevel.heightScale() - 1 && col == centerCol)
                        || (openWest && col == startCol && row == centerRow)) {
                    data[row][col] = entryTile;
                }
                else if (((openNorth || openSouth) && col == centerCol) || ((openEast || openWest) && row == centerRow)) {
                    data[row][col] = Tile.TILE_FLOOR;
                }
                else {
                    data[row][col] = Tile.BRICK_WALL;
                }
            }
        }
    }
}
