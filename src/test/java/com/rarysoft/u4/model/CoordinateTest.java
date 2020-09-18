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

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class CoordinateTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void forRowColWhenRowIsValidCreatesCoordinateWithCorrectRow() {
        for (int row = 0; row < 21; row ++) {
            assertThat(Coordinate.forRowCol(row, 0).row()).isEqualTo(row);
        }
    }

    @Test
    public void forRowColWhenRowIsValidCreatesCoordinateWithCorrectY() {
        for (int row = 0; row < 21; row ++) {
            assertThat(Coordinate.forRowCol(row, 0).y()).isEqualTo(110 - (row * 11));
        }
    }

    @Test
    public void forRowColWhenRowIsTooLowThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        Coordinate.forRowCol(-1, 0);
    }

    @Test
    public void forRowColWhenRowIsTooHighThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        Coordinate.forRowCol(21, 0);
    }

    @Test
    public void forRowColWhenColIsValidCreatesCoordinateWithCorrectCol() {
        for (int col = 0; col < 21; col ++) {
            assertThat(Coordinate.forRowCol(0, col).col()).isEqualTo(col);
        }
    }

    @Test
    public void forRowColWhenColIsValidCreatesCoordinateWithCorrectX() {
        for (int col = 0; col < 21; col ++) {
            assertThat(Coordinate.forRowCol(0, col).x()).isEqualTo((col * 11) - 110);
        }
    }

    @Test
    public void forRowColWhenColIsTooLowThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        Coordinate.forRowCol(0, -1);
    }

    @Test
    public void forRowColWhenColIsTooHighThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        Coordinate.forRowCol(0, 21);
    }

    @Test
    public void forXYWhenXIsValidCreatesCoordinateWithCorrectX() {
        for (int x = -115; x < 116; x ++) {
            assertThat(Coordinate.forXY(x, 0).x()).isEqualTo(x);
        }
    }

    @Test
    public void forXYWhenXIsValidCreatesCoordinateWithCorrectCol() {
        for (int x = -115; x < 116; x ++) {
            assertThat(Coordinate.forXY(x, 0).col()).isEqualTo((x + 110 + 5) / 11);
        }
    }

    @Test
    public void forXYWhenXIsTooLowThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        Coordinate.forXY(-116, 0);
    }

    @Test
    public void forXYWhenXIsTooHighThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        Coordinate.forXY(116, 0);
    }

    @Test
    public void forXYWhenYIsValidCreatesCoordinateWithCorrectY() {
        for (int y = -115; y < 116; y ++) {
            assertThat(Coordinate.forXY(0, y).y()).isEqualTo(y);
        }
    }

    @Test
    public void forXYWhenYIsValidCreatesCoordinateWithCorrectRow() {
        for (int y = -115; y < 116; y ++) {
            assertThat(Coordinate.forXY(0, y).row()).isEqualTo(20 - ((y + (10 * 11) + 5) / 11));
        }
    }

    @Test
    public void forXYWhenYIsTooLowThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        Coordinate.forXY(0, -116);
    }

    @Test
    public void forXYWhenYIsTooHighThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        Coordinate.forXY(0, 116);
    }

    @Test
    public void centerAlwaysCreatesCoordinateAtCenterRowAndColWithAppropriateXAndY() {
        Coordinate result = Coordinate.center();

        assertThat(result.row()).isEqualTo(10);
        assertThat(result.col()).isEqualTo(10);
        assertThat(result.x()).isEqualTo(0);
        assertThat(result.y()).isEqualTo(0);
    }

    @Test
    public void isAdjacentToWhenCoordinateIsEqualReturnsFalse() {
        Coordinate coordinate = Coordinate.forRowCol(5, 5);

        assertThat(coordinate.isAdjacentTo(coordinate)).isFalse();
    }

    @Test
    public void isAdjacentToWhenCoordinateIsAdjacentReturnsTrue() {
        Coordinate coordinate = Coordinate.forRowCol(5, 5);

        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(4, 4))).isTrue();
        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(4, 5))).isTrue();
        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(4, 6))).isTrue();
        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(5, 4))).isTrue();
        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(5, 6))).isTrue();
        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(6, 4))).isTrue();
        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(6, 5))).isTrue();
        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(6, 6))).isTrue();
    }

    @Test
    public void isAdjacentToWhenCoordinateIsNotAdjacentReturnsFalse() {
        Coordinate coordinate = Coordinate.forRowCol(5, 5);

        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(3, 3))).isFalse();
        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(3, 4))).isFalse();
        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(3, 5))).isFalse();
        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(3, 6))).isFalse();
        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(3, 7))).isFalse();
        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(4, 3))).isFalse();
        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(4, 7))).isFalse();
        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(5, 3))).isFalse();
        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(5, 7))).isFalse();
        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(6, 3))).isFalse();
        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(6, 7))).isFalse();
        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(7, 3))).isFalse();
        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(7, 4))).isFalse();
        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(7, 5))).isFalse();
        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(7, 6))).isFalse();
        assertThat(coordinate.isAdjacentTo(Coordinate.forRowCol(7, 7))).isFalse();
    }
}
