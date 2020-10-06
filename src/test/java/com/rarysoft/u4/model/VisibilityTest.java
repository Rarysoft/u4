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

import com.rarysoft.u4.model.graphics.Coordinate;
import com.rarysoft.u4.model.graphics.Tile;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VisibilityTest {
    private final static int VIEW_SIZE = 21;
    private final static int CENTER = (VIEW_SIZE - 1) / 2;
    private final RenderedTile clear = new RenderedTile(Tile.GRASSLANDS, null);
    private final RenderedTile solid = new RenderedTile(Tile.MOUNTAINS, null);
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
    public void isVisibleToPlayerWhenTargetIsCenterTileReturnsTrue() {
        boolean result = new Visibility(area).isVisibleToPlayer(Coordinate.center());

        assertThat(result).isTrue();
    }

    @Test
    public void isVisibleToPlayerWhenTargetIsAdjacentTileReturnsTrue() {
        for (int row = CENTER - 1; row <= CENTER + 1; row ++) {
            for (int col = CENTER - 1; col <= CENTER + 1; col ++) {
                area[row][col] = solid;
            }
        }
        Visibility visibility = new Visibility(area);

        for (int row = CENTER - 1; row <= CENTER + 1; row ++) {
            for (int col = CENTER - 1; col <= CENTER + 1; col ++) {
                assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(row, col))).isTrue();
            }
        }
    }

    @Test
    public void isVisibleToPlayerWhenTargetIsInVerticalLineWithPlayerWithNoBlockingTileReturnsTrue() {
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
                Visibility visibility = new Visibility(area);
                assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(row, col))).isTrue();
            }
        }
    }

    @Test
    public void isVisibleToPlayerWhenTargetIsInHorizontalLineWithPlayerWithNoBlockingTileReturnsTrue() {
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
                Visibility visibility = new Visibility(area);
                assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(row, col))).isTrue();
            }
        }
    }

    @Test
    public void isVisibleToPlayerWhenTargetIsInVerticalLineWithPlayerWithBlockingTileReturnsFalse() {
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
                Visibility visibility = new Visibility(area);
                assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(row, col))).isFalse();
            }
        }
    }

    @Test
    public void isVisibleToPlayerWhenTargetIsInHorizontalLineWithPlayerWithBlockingTileReturnsFalse() {
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
                Visibility visibility = new Visibility(area);
                assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(row, col))).isFalse();
            }
        }
    }

    @Test
    public void isVisibleToPlayerHasNoBlockingTileReturnsTrue() {
        area[0][1] = solid;
        area[1][0] = solid;
        area[1][8] = solid;
        area[3][6] = solid;
        area[6][3] = solid;
        area[8][1] = solid;

        area[0][19] = solid;
        area[1][20] = solid;
        area[1][12] = solid;
        area[3][14] = solid;
        area[6][17] = solid;
        area[8][19] = solid;

        area[20][1] = solid;
        area[19][0] = solid;
        area[19][8] = solid;
        area[17][6] = solid;
        area[14][3] = solid;
        area[12][1] = solid;

        area[20][19] = solid;
        area[19][20] = solid;
        area[19][12] = solid;
        area[17][14] = solid;
        area[14][17] = solid;
        area[12][19] = solid;

        Visibility visibility = new Visibility(area);

        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(0, 0))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(0, 8))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(2, 6))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(3, 5))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(5, 3))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(6, 2))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(8, 0))).isTrue();

        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(0, 20))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(0, 12))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(2, 14))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(3, 15))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(5, 17))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(6, 18))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(8, 20))).isTrue();

        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(20, 0))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(20, 8))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(18, 6))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(17, 5))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(15, 3))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(14, 2))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(12, 0))).isTrue();

        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(20, 20))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(20, 12))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(18, 14))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(17, 15))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(15, 17))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(14, 18))).isTrue();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(12, 20))).isTrue();
    }

    @Test
    public void isVisibleToPlayerHasBlockingTileReturnsFalse() {
        area[1][1] = solid;
        area[2][9] = solid;
        area[5][7] = solid;
        area[5][8] = solid;
        area[7][5] = solid;
        area[8][5] = solid;
        area[9][2] = solid;

        area[1][19] = solid;
        area[2][11] = solid;
        area[5][13] = solid;
        area[5][12] = solid;
        area[7][15] = solid;
        area[8][15] = solid;
        area[9][18] = solid;

        area[19][1] = solid;
        area[18][9] = solid;
        area[15][7] = solid;
        area[15][8] = solid;
        area[13][5] = solid;
        area[12][5] = solid;
        area[11][2] = solid;

        area[19][19] = solid;
        area[18][11] = solid;
        area[15][13] = solid;
        area[15][12] = solid;
        area[13][15] = solid;
        area[12][15] = solid;
        area[11][18] = solid;

        Visibility visibility = new Visibility(area);

        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(0, 0))).isFalse();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(1, 9))).isFalse();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(4, 7))).isFalse();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(7, 4))).isFalse();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(9, 1))).isFalse();

        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(0, 20))).isFalse();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(1, 11))).isFalse();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(4, 13))).isFalse();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(7, 16))).isFalse();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(9, 19))).isFalse();

        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(20, 0))).isFalse();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(19, 9))).isFalse();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(16, 7))).isFalse();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(13, 4))).isFalse();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(11, 1))).isFalse();

        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(20, 20))).isFalse();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(19, 11))).isFalse();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(16, 13))).isFalse();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(13, 16))).isFalse();
        assertThat(visibility.isVisibleToPlayer(Coordinate.forRowCol(11, 19))).isFalse();
    }
}
