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
package com.rarysoft.u4.model.npc;

import com.rarysoft.u4.model.Area;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WayFinderTest {
    private final static int VIEW_SIZE = 21;
    private final static int CENTER = (VIEW_SIZE - 1) / 2;
    private Area<Integer> area;

    @Before
    public void prepareArea() {
        Integer[][] area = new Integer[VIEW_SIZE][VIEW_SIZE];
        for (int row = 0; row < VIEW_SIZE; row ++) {
            for (int col = 0; col < VIEW_SIZE; col ++) {
                area[row][col] = 100;
            }
        }
        this.area = new Area<>(area);
    }

    @Test
    public void selectMovementTowardTargetWhenTargetIsAdjacentReturnsNoMovement() {
        WayFinder wayFinder = new WayFinder();

        Movement result = wayFinder.selectMovementTowardTarget(area, CENTER - 1, CENTER - 1);

        assertThat(result).isNotNull();
        assertThat(result.isStatic()).isTrue();
    }

    @Test
    public void selectMovementTowardTargetWhenThereIsClearPathNorthToTargetReturnsMovementNorth() {
        WayFinder wayFinder = new WayFinder();
        int[] rows = new int[] {2, 4, 6, 8, 9, 8, 6, 4, 2};

        for (int col = 6; col < 15; col ++) {
            for (int row = 0; row < rows[col - 6]; row ++) {
                Movement result = wayFinder.selectMovementTowardTarget(area, row, col);
                assertThat(result).isNotNull();
                assertThat(result.getDeltaRow()).isEqualTo(-1);
                assertThat(result.getDeltaCol()).isEqualTo(0);
            }
        }
    }

    @Test
    public void selectMovementTowardTargetWhenThereIsClearPathEastToTargetReturnsMovementEast() {
        WayFinder wayFinder = new WayFinder();
        int[] cols = new int[] {2, 4, 6, 8, 9, 8, 6, 4, 2};

        for (int row = 6; row < 15; row ++) {
            for (int col = 21 - cols[row - 6]; col < 21; col ++) {
                Movement result = wayFinder.selectMovementTowardTarget(area, row, col);
                assertThat(result).isNotNull();
                assertThat(result.getDeltaRow()).isEqualTo(0);
                assertThat(result.getDeltaCol()).isEqualTo(1);
            }
        }
    }

    @Test
    public void selectMovementTowardTargetWhenThereIsClearPathSouthToTargetReturnsMovementSouth() {
        WayFinder wayFinder = new WayFinder();
        int[] rows = new int[] {2, 4, 6, 8, 9, 8, 6, 4, 2};

        for (int col = 6; col < 15; col ++) {
            for (int row = 21 - rows[col - 6]; row < 21; row ++) {
                Movement result = wayFinder.selectMovementTowardTarget(area, row, col);
                assertThat(result).isNotNull();
                assertThat(result.getDeltaRow()).isEqualTo(1);
                assertThat(result.getDeltaCol()).isEqualTo(0);
            }
        }
    }

    @Test
    public void selectMovementTowardTargetWhenThereIsClearPathWestToTargetReturnsMovementWest() {
        WayFinder wayFinder = new WayFinder();
        int[] cols = new int[] {2, 4, 6, 8, 9, 8, 6, 4, 2};

        for (int row = 6; row < 15; row ++) {
            for (int col = 0; col < cols[row - 6]; col ++) {
                Movement result = wayFinder.selectMovementTowardTarget(area, row, col);
                assertThat(result).isNotNull();
                assertThat(result.getDeltaRow()).isEqualTo(0);
                assertThat(result.getDeltaCol()).isEqualTo(-1);
            }
        }
    }

    @Test
    public void selectMovementTowardTargetWhenThereIsClearPathNortheastToTargetReturnsMovementNortheast() {
        WayFinder wayFinder = new WayFinder();
        int[][] colRange = new int[][] {{0, 5}, {0, 5}, {0, 6}, {0, 6}, {0, 7}, {0, 7}, {2, 8}, {4, 8}, {6, 9}};

        for (int row = 0; row < 9; row ++) {
            for (int col = 21 - colRange[row][1]; col < 21 - colRange[row][0]; col ++) {
                Movement result = wayFinder.selectMovementTowardTarget(area, row, col);
                assertThat(result).isNotNull();
                assertThat(result.getDeltaRow()).isEqualTo(-1);
                assertThat(result.getDeltaCol()).isEqualTo(1);
            }
        }
    }

    @Test
    public void selectMovementTowardTargetWhenThereIsClearPathSoutheastToTargetReturnsMovementSoutheast() {
        WayFinder wayFinder = new WayFinder();
        int[][] colRange = new int[][] {{6, 9}, {4, 8}, {2, 8}, {0, 7}, {0, 7}, {0, 6}, {0, 6}, {0, 5}, {0, 5}};

        for (int row = 12; row < 21; row ++) {
            for (int col = 21 - colRange[row - 12][1]; col < 21 - colRange[row - 12][0]; col ++) {
                Movement result = wayFinder.selectMovementTowardTarget(area, row, col);
                assertThat(result).isNotNull();
                assertThat(result.getDeltaRow()).isEqualTo(1);
                assertThat(result.getDeltaCol()).isEqualTo(1);
            }
        }
    }

    @Test
    public void selectMovementTowardTargetWhenThereIsClearPathSouthwestToTargetReturnsMovementSouthwest() {
        WayFinder wayFinder = new WayFinder();
        int[][] colRange = new int[][] {{6, 9}, {4, 8}, {2, 8}, {0, 7}, {0, 7}, {0, 6}, {0, 6}, {0, 5}, {0, 5}};

        for (int row = 12; row < 21; row ++) {
            for (int col = colRange[row - 12][0]; col < colRange[row - 12][1]; col ++) {
                Movement result = wayFinder.selectMovementTowardTarget(area, row, col);
                assertThat(result).isNotNull();
                assertThat(result.getDeltaRow()).isEqualTo(1);
                assertThat(result.getDeltaCol()).isEqualTo(-1);
            }
        }
    }

    @Test
    public void selectMovementTowardTargetWhenThereIsClearPathNorthwestToTargetReturnsMovementNorthwest() {
        WayFinder wayFinder = new WayFinder();
        int[][] colRange = new int[][] {{0, 5}, {0, 5}, {0, 6}, {0, 6}, {0, 7}, {0, 7}, {2, 8}, {4, 8}, {6, 9}};

        for (int row = 0; row < 9; row ++) {
            for (int col = colRange[row][0]; col < colRange[row][1]; col ++) {
                Movement result = wayFinder.selectMovementTowardTarget(area, row, col);
                assertThat(result).isNotNull();
                assertThat(result.getDeltaRow()).isEqualTo(-1);
                assertThat(result.getDeltaCol()).isEqualTo(-1);
            }
        }
    }

    @Test
    public void selectMovementTowardTargetWhenNorthAndSlightlyEastButIsBlockedOnNorthSideReturnsLateralMovementEast() {
        WayFinder wayFinder = new WayFinder();
        for (int col = 0; col < 21; col ++) {
            area.set(9, col, 0);
        }

        Movement result = wayFinder.selectMovementTowardTarget(area, 3, 11);

        assertThat(result).isNotNull();
        assertThat(result.getDeltaRow()).isEqualTo(0);
        assertThat(result.getDeltaCol()).isEqualTo(1);
    }

    @Test
    public void selectMovementTowardTargetWhenNorthAndSlightlyWestButIsBlockedOnNorthSideReturnsLateralMovementWest() {
        WayFinder wayFinder = new WayFinder();
        for (int col = 0; col < 21; col ++) {
            area.set(9, col, 0);
        }

        Movement result = wayFinder.selectMovementTowardTarget(area, 3, 9);

        assertThat(result).isNotNull();
        assertThat(result.getDeltaRow()).isEqualTo(0);
        assertThat(result.getDeltaCol()).isEqualTo(-1);
    }

    @Test
    public void selectMovementTowardTargetWhenEastAndSlightlyNorthButIsBlockedOnEastSideReturnsLateralMovementNorth() {
        WayFinder wayFinder = new WayFinder();
        for (int row = 0; row < 21; row ++) {
            area.set(row, 11, 0);
        }

        Movement result = wayFinder.selectMovementTowardTarget(area, 9, 17);

        assertThat(result).isNotNull();
        assertThat(result.getDeltaRow()).isEqualTo(-1);
        assertThat(result.getDeltaCol()).isEqualTo(0);
    }

    @Test
    public void selectMovementTowardTargetWhenEastAndSlightlySouthButIsBlockedOnEastSideReturnsLateralMovementSouth() {
        WayFinder wayFinder = new WayFinder();
        for (int row = 0; row < 21; row ++) {
            area.set(row, 11, 0);
        }

        Movement result = wayFinder.selectMovementTowardTarget(area, 11, 17);

        assertThat(result).isNotNull();
        assertThat(result.getDeltaRow()).isEqualTo(1);
        assertThat(result.getDeltaCol()).isEqualTo(0);
    }

    @Test
    public void selectMovementTowardTargetWhenSouthAndSlightlyEastButIsBlockedOnSouthSideReturnsLateralMovementEast() {
        WayFinder wayFinder = new WayFinder();
        for (int col = 0; col < 21; col ++) {
            area.set(11, col, 0);
        }

        Movement result = wayFinder.selectMovementTowardTarget(area, 17, 11);

        assertThat(result).isNotNull();
        assertThat(result.getDeltaRow()).isEqualTo(0);
        assertThat(result.getDeltaCol()).isEqualTo(1);
    }

    @Test
    public void selectMovementTowardTargetWhenSouthAndSlightlyWestButIsBlockedOnSouthSideReturnsLateralMovementWest() {
        WayFinder wayFinder = new WayFinder();
        for (int col = 0; col < 21; col ++) {
            area.set(11, col, 0);
        }

        Movement result = wayFinder.selectMovementTowardTarget(area, 17, 9);

        assertThat(result).isNotNull();
        assertThat(result.getDeltaRow()).isEqualTo(0);
        assertThat(result.getDeltaCol()).isEqualTo(-1);
    }

    @Test
    public void selectMovementTowardTargetWhenWestAndSlightlyNorthButIsBlockedOnWestSideReturnsLateralMovementNorth() {
        WayFinder wayFinder = new WayFinder();
        for (int row = 0; row < 21; row ++) {
            area.set(row, 9, 0);
        }

        Movement result = wayFinder.selectMovementTowardTarget(area, 9, 3);

        assertThat(result).isNotNull();
        assertThat(result.getDeltaRow()).isEqualTo(-1);
        assertThat(result.getDeltaCol()).isEqualTo(0);
    }

    @Test
    public void selectMovementTowardTargetWhenWestAndSlightlySouthButIsBlockedOnWestSideReturnsLateralMovementSouth() {
        WayFinder wayFinder = new WayFinder();
        for (int row = 0; row < 21; row ++) {
            area.set(row, 9, 0);
        }

        Movement result = wayFinder.selectMovementTowardTarget(area, 11, 3);

        assertThat(result).isNotNull();
        assertThat(result.getDeltaRow()).isEqualTo(1);
        assertThat(result.getDeltaCol()).isEqualTo(0);
    }
}
