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
import com.rarysoft.u4.model.graphics.Region;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

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
    public void centerAlwaysCreatesCoordinateWithCorrectRow() {
        Coordinate result = Coordinate.center();

        assertThat(result.row()).isEqualTo(10);
    }

    @Test
    public void centerAlwaysCreatesCoordinateWithCorrectCol() {
        Coordinate result = Coordinate.center();

        assertThat(result.col()).isEqualTo(10);
    }

    @Test
    public void centerAlwaysCreatesCoordinateWithCorrectX() {
        Coordinate result = Coordinate.center();

        assertThat(result.x()).isEqualTo(0);
    }

    @Test
    public void centerAlwaysCreatesCoordinateWithCorrectY() {
        Coordinate result = Coordinate.center();

        assertThat(result.y()).isEqualTo(0);
    }

    @Test
    public void regionWhenCoordinateIsInCenterReturnsCenter() {
        assertThat(Coordinate.forRowCol(10, 10).region()).isEqualTo(Region.CENTER);
    }

    @Test
    public void regionWhenCoordinateIsInNorthReturnsNorth() {
        for (int row = 0; row < 10; row ++) {
            assertThat(Coordinate.forRowCol(row, 10).region()).isEqualTo(Region.NORTH);
        }
    }

    @Test
    public void regionWhenCoordinateIsInNortheastReturnsNortheast() {
        for (int row = 0; row < 10; row ++) {
            for (int col = 11; col < 21; col ++) {
                assertThat(Coordinate.forRowCol(row, col).region()).isEqualTo(Region.NORTHEAST);
            }
        }
    }

    @Test
    public void regionWhenCoordinateIsInEastReturnsEast() {
        for (int col = 11; col < 21; col ++) {
            assertThat(Coordinate.forRowCol(10, col).region()).isEqualTo(Region.EAST);
        }
    }

    @Test
    public void regionWhenCoordinateIsInSoutheastReturnsSoutheast() {
        for (int row = 11; row < 21; row ++) {
            for (int col = 11; col < 21; col ++) {
                assertThat(Coordinate.forRowCol(row, col).region()).isEqualTo(Region.SOUTHEAST);
            }
        }
    }

    @Test
    public void regionWhenCoordinateIsInSouthReturnsSouth() {
        for (int row = 11; row < 21; row ++) {
            assertThat(Coordinate.forRowCol(row, 10).region()).isEqualTo(Region.SOUTH);
        }
    }

    @Test
    public void regionWhenCoordinateIsInSouthwestReturnsSouthwest() {
        for (int row = 11; row < 21; row ++) {
            for (int col = 0; col < 10; col ++) {
                assertThat(Coordinate.forRowCol(row, col).region()).isEqualTo(Region.SOUTHWEST);
            }
        }
    }

    @Test
    public void regionWhenCoordinateIsInWestReturnsWest() {
        for (int col = 0; col < 10; col ++) {
            assertThat(Coordinate.forRowCol(10, col).region()).isEqualTo(Region.WEST);
        }
    }

    @Test
    public void regionWhenCoordinateIsInNorthwestReturnsNorthwest() {
        for (int row = 0; row < 10; row ++) {
            for (int col = 0; col < 10; col ++) {
                assertThat(Coordinate.forRowCol(row, col).region()).isEqualTo(Region.NORTHWEST);
            }
        }
    }

    @Test
    public void isCenterWhenCoordinateIsCenterReturnsTrue() {
        for (int x = -5; x < 6; x ++) {
            for (int y = -5; y < 6; y ++) {
                assertThat(Coordinate.forXY(x, y).isCenter()).isTrue();
            }
        }
    }

    @Test
    public void isCenterWhenCoordinateIsNotCenterReturnsFalse() {
        for (int x = -115; x < 116; x ++) {
            for (int y = -115; y < 116; y ++) {
                if (! (x > -6 && x < 6 && y > -6 && y < 6)) {
                    assertThat(Coordinate.forXY(x, y).isCenter()).isFalse();
                }
            }
        }
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

    @Test
    public void isNorthOfWhenCoordinateIsNorthOfArgumentReturnsTrue() {
        for (int x = -115; x < 116; x ++) {
            for (int y = 1; y < 116; y ++) {
                assertThat(Coordinate.forXY(x, y).isNorthOf(Coordinate.center())).isTrue();
            }
        }
    }

    @Test
    public void isNorthOfWhenCoordinateIsNotNorthOfArgumentReturnsFalse() {
        for (int x = -115; x < 116; x ++) {
            for (int y = -115; y < 0; y ++) {
                assertThat(Coordinate.forXY(x, y).isNorthOf(Coordinate.center())).isFalse();
            }
        }
    }

    @Test
    public void isEastOfWhenCoordinateIsEastOfArgumentReturnsTrue() {
        for (int x = 1; x < 116; x ++) {
            for (int y = -115; y < 116; y ++) {
                assertThat(Coordinate.forXY(x, y).isEastOf(Coordinate.center())).isTrue();
            }
        }
    }

    @Test
    public void isEastOfWhenCoordinateIsNotEastOfArgumentReturnsFalse() {
        for (int x = -115; x < 1; x ++) {
            for (int y = -115; y < 116; y ++) {
                assertThat(Coordinate.forXY(x, y).isEastOf(Coordinate.center())).isFalse();
            }
        }
    }

    @Test
    public void slopeToWhenTargetCoordinateIsNotEastOfCoordinateThrowsIllegalArgumentException() {
        for (int x = 0; x < 116; x ++) {
            for (int y = -115; y < 116; y ++) {
                try {
                    Coordinate.forXY(x, y).slopeTo(Coordinate.center());
                    fail("expected IllegalArgumentException but none was thrown");
                }
                catch (IllegalArgumentException e) {}
            }
        }
    }

    @Test
    public void slopeToWhenTargetCoordinateIsEastOfCoordinateThrowsIllegalArgumentException() {
        for (int x = -115; x < 0; x ++) {
            for (int y = -115; y < 116; y ++) {
                Coordinate source = Coordinate.forXY(x, y);
                Coordinate target = Coordinate.center();
                BigDecimal rise = BigDecimal.valueOf(source.y() - target.y());
                BigDecimal run = BigDecimal.valueOf(source.x() - target.x());
                BigDecimal slope = rise.divide(run, 2, BigDecimal.ROUND_HALF_UP);
                assertThat(source.slopeTo(target)).isEqualTo(slope);
            }
        }
    }

    @Test
    public void isSameRowColWhenCoordinateIsSameRowAndColAsArgumentReturnsTrue() {
        for (int x = -60; x < -51; x ++) {
            for (int y = 50; y < 61; y ++) {
                assertThat(Coordinate.forXY(x, y).isSameRowCol(Coordinate.forRowCol(5, 5))).isTrue();
            }
        }
    }

    @Test
    public void isSameRowColWhenCoordinateIsNotSameRowAndColAsArgumentReturnsFalse() {
        for (int x = -115; x < 116; x ++) {
            for (int y = -115; y < 116; y ++) {
                if (! (x > -61 && x < -49 && y > 49 && y < 61)) {
                    assertThat(Coordinate.forXY(x, y).isSameRowCol(Coordinate.forRowCol(5, 5))).isFalse();
                }
            }
        }
    }

    @Test
    public void toTheNorthAlwaysReturnsTileWithCorrectRow() {
        Coordinate result = Coordinate.forRowCol(5, 5).toTheNorth();

        assertThat(result.row()).isEqualTo(4);
    }

    @Test
    public void toTheNorthAlwaysReturnsTileWithCorrectCol() {
        Coordinate result = Coordinate.forRowCol(5, 5).toTheNorth();

        assertThat(result.col()).isEqualTo(5);
    }

    @Test
    public void toTheNortheastAlwaysReturnsTileWithCorrectRow() {
        Coordinate result = Coordinate.forRowCol(5, 5).toTheNortheast();

        assertThat(result.row()).isEqualTo(4);
    }

    @Test
    public void toTheNortheastAlwaysReturnsTileWithCorrectCol() {
        Coordinate result = Coordinate.forRowCol(5, 5).toTheNortheast();

        assertThat(result.col()).isEqualTo(6);
    }

    @Test
    public void toTheEastAlwaysReturnsTileWithCorrectRow() {
        Coordinate result = Coordinate.forRowCol(5, 5).toTheEast();

        assertThat(result.row()).isEqualTo(5);
    }

    @Test
    public void toTheEastAlwaysReturnsTileWithCorrectCol() {
        Coordinate result = Coordinate.forRowCol(5, 5).toTheEast();

        assertThat(result.col()).isEqualTo(6);
    }

    @Test
    public void toTheSoutheastAlwaysReturnsTileWithCorrectRow() {
        Coordinate result = Coordinate.forRowCol(5, 5).toTheSoutheast();

        assertThat(result.row()).isEqualTo(6);
    }

    @Test
    public void toTheSoutheastAlwaysReturnsTileWithCorrectCol() {
        Coordinate result = Coordinate.forRowCol(5, 5).toTheSoutheast();

        assertThat(result.col()).isEqualTo(6);
    }

    @Test
    public void toTheSouthAlwaysReturnsTileWithCorrectRow() {
        Coordinate result = Coordinate.forRowCol(5, 5).toTheSouth();

        assertThat(result.row()).isEqualTo(6);
    }

    @Test
    public void toTheSouthAlwaysReturnsTileWithCorrectCol() {
        Coordinate result = Coordinate.forRowCol(5, 5).toTheSouth();

        assertThat(result.col()).isEqualTo(5);
    }

    @Test
    public void toTheSouthwestAlwaysReturnsTileWithCorrectRow() {
        Coordinate result = Coordinate.forRowCol(5, 5).toTheSouthwest();

        assertThat(result.row()).isEqualTo(6);
    }

    @Test
    public void toTheSouthwestAlwaysReturnsTileWithCorrectCol() {
        Coordinate result = Coordinate.forRowCol(5, 5).toTheSouthwest();

        assertThat(result.col()).isEqualTo(4);
    }

    @Test
    public void toTheWestAlwaysReturnsTileWithCorrectRow() {
        Coordinate result = Coordinate.forRowCol(5, 5).toTheWest();

        assertThat(result.row()).isEqualTo(5);
    }

    @Test
    public void toTheWestAlwaysReturnsTileWithCorrectCol() {
        Coordinate result = Coordinate.forRowCol(5, 5).toTheWest();

        assertThat(result.col()).isEqualTo(4);
    }

    @Test
    public void toTheNorthwestAlwaysReturnsTileWithCorrectRow() {
        Coordinate result = Coordinate.forRowCol(5, 5).toTheNorthwest();

        assertThat(result.row()).isEqualTo(4);
    }

    @Test
    public void toTheNorthwestAlwaysReturnsTileWithCorrectCol() {
        Coordinate result = Coordinate.forRowCol(5, 5).toTheNorthwest();

        assertThat(result.col()).isEqualTo(4);
    }

    @Test
    public void atNorthSideAlwaysReturnsCoordinateWithCorrectX() {
        Coordinate result = Coordinate.center().atNorthSide();

        assertThat(result.x()).isEqualTo(0);
    }

    @Test
    public void atNorthSideAlwaysReturnsCoordinateWithCorrectY() {
        Coordinate result = Coordinate.center().atNorthSide();

        assertThat(result.y()).isEqualTo(5);
    }

    @Test
    public void atNortheastCornerAlwaysReturnsCoordinateWithCorrectX() {
        Coordinate result = Coordinate.center().atNortheastCorner();

        assertThat(result.x()).isEqualTo(5);
    }

    @Test
    public void atNortheastCornerAlwaysReturnsCoordinateWithCorrectY() {
        Coordinate result = Coordinate.center().atNortheastCorner();

        assertThat(result.y()).isEqualTo(5);
    }

    @Test
    public void atEastSideAlwaysReturnsCoordinateWithCorrectX() {
        Coordinate result = Coordinate.center().atEastSide();

        assertThat(result.x()).isEqualTo(5);
    }

    @Test
    public void atEastSideAlwaysReturnsCoordinateWithCorrectY() {
        Coordinate result = Coordinate.center().atEastSide();

        assertThat(result.y()).isEqualTo(0);
    }

    @Test
    public void atSoutheastCornerAlwaysReturnsCoordinateWithCorrectX() {
        Coordinate result = Coordinate.center().atSoutheastCorner();

        assertThat(result.x()).isEqualTo(5);
    }

    @Test
    public void atSoutheastCornerAlwaysReturnsCoordinateWithCorrectY() {
        Coordinate result = Coordinate.center().atSoutheastCorner();

        assertThat(result.y()).isEqualTo(-5);
    }

    @Test
    public void atSouthSideAlwaysReturnsCoordinateWithCorrectX() {
        Coordinate result = Coordinate.center().atSouthSide();

        assertThat(result.x()).isEqualTo(0);
    }

    @Test
    public void atSouthSideAlwaysReturnsCoordinateWithCorrectY() {
        Coordinate result = Coordinate.center().atSouthSide();

        assertThat(result.y()).isEqualTo(-5);
    }

    @Test
    public void atSouthwestCornerAlwaysReturnsCoordinateWithCorrectX() {
        Coordinate result = Coordinate.center().atSouthwestCorner();

        assertThat(result.x()).isEqualTo(-5);
    }

    @Test
    public void atSouthwestCornerAlwaysReturnsCoordinateWithCorrectY() {
        Coordinate result = Coordinate.center().atSouthwestCorner();

        assertThat(result.y()).isEqualTo(-5);
    }

    @Test
    public void atWestSideAlwaysReturnsCoordinateWithCorrectX() {
        Coordinate result = Coordinate.center().atWestSide();

        assertThat(result.x()).isEqualTo(-5);
    }

    @Test
    public void atWestSideAlwaysReturnsCoordinateWithCorrectY() {
        Coordinate result = Coordinate.center().atWestSide();

        assertThat(result.y()).isEqualTo(0);
    }

    @Test
    public void atNorthwestCornerAlwaysReturnsCoordinateWithCorrectX() {
        Coordinate result = Coordinate.center().atNorthwestCorner();

        assertThat(result.x()).isEqualTo(-5);
    }

    @Test
    public void atNorthwestCornerAlwaysReturnsCoordinateWithCorrectY() {
        Coordinate result = Coordinate.center().atNorthwestCorner();

        assertThat(result.y()).isEqualTo(5);
    }

    @Test
    public void atSideFacingCenterRowWhenOnCenterRowReturnsCoordinateWithCorrectX() {
        Coordinate result = Coordinate.forRowCol(10, 10).atSideFacingCenterRow();

        assertThat(result.x()).isEqualTo(0);
    }

    @Test
    public void atSideFacingCenterRowWhenOnCenterRowReturnsCoordinateWithCorrectY() {
        Coordinate result = Coordinate.forRowCol(10, 10).atSideFacingCenterRow();

        assertThat(result.y()).isEqualTo(0);
    }

    @Test
    public void atSideFacingCenterRowWhenNorthOfCenterRowReturnsCoordinateWithCorrectX() {
        Coordinate result = Coordinate.forRowCol(9, 10).atSideFacingCenterRow();

        assertThat(result.x()).isEqualTo(0);
    }

    @Test
    public void atSideFacingCenterRowWhenNorthOfCenterRowReturnsCoordinateWithCorrectY() {
        Coordinate result = Coordinate.forRowCol(9, 10).atSideFacingCenterRow();

        assertThat(result.y()).isEqualTo(6);
    }

    @Test
    public void atSideFacingCenterRowWhenSouthOfCenterRowReturnsCoordinateWithCorrectX() {
        Coordinate result = Coordinate.forRowCol(11, 10).atSideFacingCenterRow();

        assertThat(result.x()).isEqualTo(0);
    }

    @Test
    public void atSideFacingCenterRowWhenSouthOfCenterRowReturnsCoordinateWithCorrectY() {
        Coordinate result = Coordinate.forRowCol(11, 10).atSideFacingCenterRow();

        assertThat(result.y()).isEqualTo(-6);
    }

    @Test
    public void atSideFacingCenterColWhenOnCenterColReturnsCoordinateWithCorrectX() {
        Coordinate result = Coordinate.forRowCol(10, 10).atSideFacingCenterCol();

        assertThat(result.x()).isEqualTo(0);
    }

    @Test
    public void atSideFacingCenterColWhenOnCenterColReturnsCoordinateWithCorrectY() {
        Coordinate result = Coordinate.forRowCol(10, 10).atSideFacingCenterCol();

        assertThat(result.y()).isEqualTo(0);
    }

    @Test
    public void atSideFacingCenterColWhenEastOfCenterColReturnsCoordinateWithCorrectX() {
        Coordinate result = Coordinate.forRowCol(10, 11).atSideFacingCenterCol();

        assertThat(result.x()).isEqualTo(6);
    }

    @Test
    public void atSideFacingCenterColWhenEastOfCenterColReturnsCoordinateWithCorrectY() {
        Coordinate result = Coordinate.forRowCol(10, 11).atSideFacingCenterCol();

        assertThat(result.y()).isEqualTo(0);
    }

    @Test
    public void atSideFacingCenterColWhenWestOfCenterColReturnsCoordinateWithCorrectX() {
        Coordinate result = Coordinate.forRowCol(10, 9).atSideFacingCenterCol();

        assertThat(result.x()).isEqualTo(-6);
    }

    @Test
    public void atSideFacingCenterColWhenWestOfCenterColReturnsCoordinateWithCorrectY() {
        Coordinate result = Coordinate.forRowCol(10, 9).atSideFacingCenterCol();

        assertThat(result.y()).isEqualTo(0);
    }
}
