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
    public void isVisibleToPlayerWhenExtremelySteepSlopeTowardTargetTileHiddenBehindOpaqueTileReturnsFalse() {
        Coordinate center = Coordinate.center();
        area[2][center.col()] = solid;
        area[2][center.col() + 1] = solid;

        boolean result = new Visibility(area).isVisibleToPlayer(Coordinate.forRowCol(1, center.col() + 1));

        assertThat(result).isFalse();
    }
}
