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

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VisibilityTest {
    private final static int VIEW_SIZE = 19;
    private final static int CENTER = (VIEW_SIZE - 1) / 2;
    private final RenderedTile clear = new RenderedTile().withBaseTile(Tile.GRASSLANDS);
    private final RenderedTile solid = new RenderedTile().withBaseTile(Tile.MOUNTAINS);
    private final RenderedTile[][] area = new RenderedTile[VIEW_SIZE][VIEW_SIZE];

    @Before
    public void prepareArea() {
        for (int row = 0; row < VIEW_SIZE; row ++) {
            for (int col = 0; col < VIEW_SIZE; col ++) {
                area[row][col] = clear;
            }
        }
    }

    @Test
    public void isVisibleFromCenterWhenTargetIsCenterTileReturnsTrue() {
        boolean result = new Visibility().isVisibleFromCenter(area, Coordinate.center());

        assertThat(result).isTrue();
    }

    @Test
    public void isVisibleFromCenterWhenTargetIsAdjacentTileReturnsTrue() {
        for (int row = CENTER - 1; row <= CENTER + 1; row ++) {
            for (int col = CENTER - 1; col <= CENTER + 1; col ++) {
                area[row][col] = solid;
            }
        }
        Visibility visibility = new Visibility();

        for (int row = CENTER - 1; row <= CENTER + 1; row ++) {
            for (int col = CENTER - 1; col <= CENTER + 1; col ++) {
                assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(row, col))).isTrue();
            }
        }
    }

    @Test
    public void isVisibleFromCenterWhenTargetIsInVerticalLineWithPlayerWithNoBlockingTileReturnsTrue() {
        for (int row = 0; row < VIEW_SIZE; row ++) {
            int col = CENTER;
            area[row][col] = clear;
            if (row > 0 && row < CENTER) {
                area[row - 1][col] = solid;
            }
            if (row > CENTER && row < VIEW_SIZE - 1) {
                area[row + 1][col] = solid;
            }
            if (row < CENTER - 1 || row > CENTER + 1) {
                Visibility visibility = new Visibility();
                assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(row, col))).isTrue();
            }
        }
    }

    @Test
    public void isVisibleFromCenterWhenTargetIsInHorizontalLineWithPlayerWithNoBlockingTileReturnsTrue() {
        int row = CENTER;
        for (int col = 0; col < VIEW_SIZE; col ++) {
            area[row][col] = clear;
            if (col > 0 && col < CENTER) {
                area[row][col - 1] = solid;
            }
            if (col > CENTER && col < VIEW_SIZE - 1) {
                area[row][col + 1] = solid;
            }
            if (col < CENTER - 1 || col > CENTER + 1) {
                Visibility visibility = new Visibility();
                assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(row, col))).isTrue();
            }
        }
    }

    @Test
    public void isVisibleFromCenterWhenTargetIsInVerticalLineWithPlayerWithBlockingTileReturnsFalse() {
        for (int row = 0; row < VIEW_SIZE; row ++) {
            int col = CENTER;
            area[row][col] = clear;
            if (row < CENTER) {
                area[row + 1][col] = solid;
            }
            if (row > CENTER) {
                area[row][col] = solid;
            }
            if (row < CENTER - 1 || row > CENTER + 1) {
                Visibility visibility = new Visibility();
                assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(row, col))).isFalse();
            }
        }
    }

    @Test
    public void isVisibleFromCenterWhenTargetIsInHorizontalLineWithPlayerWithBlockingTileReturnsFalse() {
        int row = CENTER;
        for (int col = 0; col < VIEW_SIZE; col ++) {
            area[row][col] = clear;
            if (col < CENTER) {
                area[row][col + 1] = solid;
            }
            if (col > CENTER) {
                area[row][col - 1] = solid;
            }
            if (col < CENTER - 1 || col > CENTER + 1) {
                Visibility visibility = new Visibility();
                assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(row, col))).isFalse();
            }
        }
    }

    @Test
    public void isVisibleFromCenterWhenTargetIsAlongsideNorthernRowOfBlockingTilesReturnsTrue() {
        for (int col = 0; col < VIEW_SIZE; col ++) {
            area[CENTER - 1][col] = solid;
        }

        for (int row = 0; row < VIEW_SIZE; row ++) {
            for (int col = 0; col < VIEW_SIZE; col ++) {
                Visibility visibility = new Visibility();
                if (row > CENTER - 1) {
                    assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(row, col))).isTrue();
                }
                else if (row == CENTER - 1) {
                    if (col > 3 && col < VIEW_SIZE - 4) {
                        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(row, col))).isTrue();
                    }
                }
                else {
                    assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(row, col))).isFalse();
                }
            }
        }
    }

    @Test
    public void isVisibleFromCenterWhenTargetIsAlongsideEasternRowOfBlockingTilesReturnsTrue() {
        for (int row = 0; row < VIEW_SIZE; row ++) {
            area[row][CENTER + 1] = solid;
        }

        for (int row = 0; row < VIEW_SIZE; row ++) {
            for (int col = 0; col < VIEW_SIZE; col ++) {
                Visibility visibility = new Visibility();
                if (col < CENTER + 1) {
                    assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(row, col))).isTrue();
                }
                else if (col == CENTER + 1) {
                    if (row > 3 && row < VIEW_SIZE - 4) {
                        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(row, col))).isTrue();
                    }
                }
                else {
                    assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(row, col))).isFalse();
                }
            }
        }
    }

    @Test
    public void isVisibleFromCenterWhenTargetIsAlongsideSouthernRowOfBlockingTilesReturnsTrue() {
        for (int col = 0; col < VIEW_SIZE; col ++) {
            area[CENTER + 1][col] = solid;
        }

        for (int row = 0; row < VIEW_SIZE; row ++) {
            for (int col = 0; col < VIEW_SIZE; col ++) {
                Visibility visibility = new Visibility();
                if (row < CENTER + 1) {
                    assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(row, col))).isTrue();
                }
                else if (row == CENTER + 1) {
                    if (col > 3 && col < VIEW_SIZE - 4) {
                        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(row, col))).isTrue();
                    }
                }
                else {
                    assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(row, col))).isFalse();
                }
            }
        }
    }

    @Test
    public void isVisibleFromCenterWhenTargetIsAlongsideWesternRowOfBlockingTilesReturnsTrue() {
        for (int row = 0; row < VIEW_SIZE; row ++) {
            area[row][CENTER - 1] = solid;
        }

        for (int row = 0; row < VIEW_SIZE; row ++) {
            for (int col = 0; col < VIEW_SIZE; col ++) {
                Visibility visibility = new Visibility();
                if (col > CENTER - 1) {
                    assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(row, col))).isTrue();
                }
                else if (col == CENTER - 1) {
                    if (row > 3 && row < VIEW_SIZE - 4) {
                        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(row, col))).isTrue();
                    }
                }
                else {
                    assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(row, col))).isFalse();
                }
            }
        }
    }

    @Test
    public void isVisibleFromCenterWhenHasNoBlockingTileReturnsTrue() {
        area[0][1] = solid;
        area[1][0] = solid;
        area[1][7] = solid;
        area[2][5] = solid;
        area[5][2] = solid;
        area[7][1] = solid;

        area[0][17] = solid;
        area[1][18] = solid;
        area[1][11] = solid;
        area[2][13] = solid;
        area[5][16] = solid;
        area[7][17] = solid;

        area[18][1] = solid;
        area[17][0] = solid;
        area[17][7] = solid;
        area[16][5] = solid;
        area[13][2] = solid;
        area[11][1] = solid;

        area[18][17] = solid;
        area[17][18] = solid;
        area[17][11] = solid;
        area[16][13] = solid;
        area[13][16] = solid;
        area[11][17] = solid;

        Visibility visibility = new Visibility();

        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(0, 0))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(0, 7))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(1, 5))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(2, 4))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(4, 2))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(5, 1))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(7, 0))).isTrue();

        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(0, 18))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(0, 11))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(1, 13))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(2, 14))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(4, 16))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(5, 17))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(7, 18))).isTrue();

        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(18, 0))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(18, 7))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(17, 5))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(16, 4))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(14, 2))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(13, 1))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(11, 0))).isTrue();

        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(18, 18))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(18, 11))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(17, 13))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(16, 14))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(14, 16))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(13, 17))).isTrue();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(11, 18))).isTrue();
    }

    @Test
    public void isVisibleFromCenterWhenHasBlockingTileReturnsFalse() {
        area[1][1] = solid;
        area[2][8] = solid;
        area[4][6] = solid;
        area[4][7] = solid;
        area[6][4] = solid;
        area[7][4] = solid;
        area[8][2] = solid;

        area[1][17] = solid;
        area[2][10] = solid;
        area[4][12] = solid;
        area[4][11] = solid;
        area[6][14] = solid;
        area[7][14] = solid;
        area[8][16] = solid;

        area[17][1] = solid;
        area[16][8] = solid;
        area[14][6] = solid;
        area[14][7] = solid;
        area[12][4] = solid;
        area[11][4] = solid;
        area[10][2] = solid;

        area[17][17] = solid;
        area[16][10] = solid;
        area[14][12] = solid;
        area[14][11] = solid;
        area[12][14] = solid;
        area[11][14] = solid;
        area[10][16] = solid;

        Visibility visibility = new Visibility();

        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(0, 0))).isFalse();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(1, 8))).isFalse();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(3, 6))).isFalse();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(6, 3))).isFalse();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(8, 1))).isFalse();

        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(0, 18))).isFalse();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(1, 10))).isFalse();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(3, 12))).isFalse();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(6, 15))).isFalse();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(8, 17))).isFalse();

        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(18, 0))).isFalse();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(17, 8))).isFalse();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(15, 6))).isFalse();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(12, 3))).isFalse();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(10, 1))).isFalse();

        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(18, 18))).isFalse();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(17, 10))).isFalse();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(15, 12))).isFalse();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(12, 15))).isFalse();
        assertThat(visibility.isVisibleFromCenter(area, Coordinate.forRowCol(10, 17))).isFalse();
    }
}
