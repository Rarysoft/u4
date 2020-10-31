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

import com.rarysoft.u4.game.physics.ViewFinder;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ViewFinderTest {
    private Area<Tile> area;

    @Before
    public void prepareArea() {
        // prepare a large area (99 x 99) with an identifying feature around the center tile
        Tile[][] tiles = new Tile[99][99];
        for (int row = 0; row < 99; row ++) {
            for (int col = 0; col < 99; col ++) {
                tiles[row][col] = Tile.BRICK_FLOOR;
            }
        }
        tiles[49][50] = Tile.BRICK_WALL;
        tiles[50][49] = Tile.BRICK_WALL;
        tiles[50][51] = Tile.BRICK_WALL;
        tiles[51][50] = Tile.BRICK_WALL;
        area = new Area<>(tiles);
    }

    @Test
    public void viewWhenFullViewAvailableReturnsCorrectViewAroundTargetCenter() {
        ViewFinder viewFinder = new ViewFinder();

        Area<Tile> result = viewFinder.view(area, Tile.GRASSLANDS, 3, 50, 50);

        assertThat(result).isNotNull();
        assertThat(result.rows()).isEqualTo(7);
        assertThat(result.cols()).isEqualTo(7);
        for (int row = 0; row < 7; row ++) {
            for (int col = 0; col < 7; col ++) {
                Tile tile = result.get(row, col);
                Tile expectedTile = Tile.BRICK_FLOOR;
                if ((row == 2 && col == 3) || (row == 3 && (col == 2 || col == 4)) || (row == 4 && col == 3)) {
                    expectedTile = Tile.BRICK_WALL;
                }
                assertThat(tile).isEqualTo(expectedTile);
            }
        }
    }

    @Test
    public void viewWhenNorthPartUnavailableReturnsCorrectViewAroundTargetCenter() {
        ViewFinder viewFinder = new ViewFinder();

        Area<Tile> result = viewFinder.view(area, Tile.GRASSLANDS, 3, 1, 50);

        assertThat(result).isNotNull();
        assertThat(result.rows()).isEqualTo(7);
        assertThat(result.cols()).isEqualTo(7);
        for (int row = 0; row < 7; row ++) {
            for (int col = 0; col < 7; col ++) {
                Tile tile = result.get(row, col);
                Tile expectedTile = Tile.BRICK_FLOOR;
                if (row < 2) {
                    expectedTile = Tile.GRASSLANDS;
                }
                assertThat(tile).isEqualTo(expectedTile);
            }
        }
    }

    @Test
    public void viewWhenNortheastPartUnavailableReturnsCorrectViewAroundTargetCenter() {
        ViewFinder viewFinder = new ViewFinder();

        Area<Tile> result = viewFinder.view(area, Tile.GRASSLANDS, 3, 1, 97);

        assertThat(result).isNotNull();
        assertThat(result.rows()).isEqualTo(7);
        assertThat(result.cols()).isEqualTo(7);
        for (int row = 0; row < 7; row ++) {
            for (int col = 0; col < 7; col ++) {
                Tile tile = result.get(row, col);
                Tile expectedTile = Tile.BRICK_FLOOR;
                if (row < 2 || col > 4) {
                    expectedTile = Tile.GRASSLANDS;
                }
                assertThat(tile).isEqualTo(expectedTile);
            }
        }
    }

    @Test
    public void viewWhenEastPartUnavailableReturnsCorrectViewAroundTargetCenter() {
        ViewFinder viewFinder = new ViewFinder();

        Area<Tile> result = viewFinder.view(area, Tile.GRASSLANDS, 3, 50, 97);

        assertThat(result).isNotNull();
        assertThat(result.rows()).isEqualTo(7);
        assertThat(result.cols()).isEqualTo(7);
        for (int row = 0; row < 7; row ++) {
            for (int col = 0; col < 7; col ++) {
                Tile tile = result.get(row, col);
                Tile expectedTile = Tile.BRICK_FLOOR;
                if (col > 4) {
                    expectedTile = Tile.GRASSLANDS;
                }
                assertThat(tile).isEqualTo(expectedTile);
            }
        }
    }

    @Test
    public void viewWhenSoutheastPartUnavailableReturnsCorrectViewAroundTargetCenter() {
        ViewFinder viewFinder = new ViewFinder();

        Area<Tile> result = viewFinder.view(area, Tile.GRASSLANDS, 3, 97, 97);

        assertThat(result).isNotNull();
        assertThat(result.rows()).isEqualTo(7);
        assertThat(result.cols()).isEqualTo(7);
        for (int row = 0; row < 7; row ++) {
            for (int col = 0; col < 7; col ++) {
                Tile tile = result.get(row, col);
                Tile expectedTile = Tile.BRICK_FLOOR;
                if (row > 4 || col > 4) {
                    expectedTile = Tile.GRASSLANDS;
                }
                assertThat(tile).isEqualTo(expectedTile);
            }
        }
    }

    @Test
    public void viewWhenSouthPartUnavailableReturnsCorrectViewAroundTargetCenter() {
        ViewFinder viewFinder = new ViewFinder();

        Area<Tile> result = viewFinder.view(area, Tile.GRASSLANDS, 3, 97, 50);

        assertThat(result).isNotNull();
        assertThat(result.rows()).isEqualTo(7);
        assertThat(result.cols()).isEqualTo(7);
        for (int row = 0; row < 7; row ++) {
            for (int col = 0; col < 7; col ++) {
                Tile tile = result.get(row, col);
                Tile expectedTile = Tile.BRICK_FLOOR;
                if (row > 4) {
                    expectedTile = Tile.GRASSLANDS;
                }
                assertThat(tile).isEqualTo(expectedTile);
            }
        }
    }

    @Test
    public void viewWhenSouthwestPartUnavailableReturnsCorrectViewAroundTargetCenter() {
        ViewFinder viewFinder = new ViewFinder();

        Area<Tile> result = viewFinder.view(area, Tile.GRASSLANDS, 3, 97, 1);

        assertThat(result).isNotNull();
        assertThat(result.rows()).isEqualTo(7);
        assertThat(result.cols()).isEqualTo(7);
        for (int row = 0; row < 7; row ++) {
            for (int col = 0; col < 7; col ++) {
                Tile tile = result.get(row, col);
                Tile expectedTile = Tile.BRICK_FLOOR;
                if (row > 4 || col < 2) {
                    expectedTile = Tile.GRASSLANDS;
                }
                assertThat(tile).isEqualTo(expectedTile);
            }
        }
    }

    @Test
    public void viewWhenWestPartUnavailableReturnsCorrectViewAroundTargetCenter() {
        ViewFinder viewFinder = new ViewFinder();

        Area<Tile> result = viewFinder.view(area, Tile.GRASSLANDS, 3, 50, 1);

        assertThat(result).isNotNull();
        assertThat(result.rows()).isEqualTo(7);
        assertThat(result.cols()).isEqualTo(7);
        for (int row = 0; row < 7; row ++) {
            for (int col = 0; col < 7; col ++) {
                Tile tile = result.get(row, col);
                Tile expectedTile = Tile.BRICK_FLOOR;
                if (col < 2) {
                    expectedTile = Tile.GRASSLANDS;
                }
                assertThat(tile).isEqualTo(expectedTile);
            }
        }
    }

    @Test
    public void viewWhenNorthwestPartUnavailableReturnsCorrectViewAroundTargetCenter() {
        ViewFinder viewFinder = new ViewFinder();

        Area<Tile> result = viewFinder.view(area, Tile.GRASSLANDS, 3, 1, 1);

        assertThat(result).isNotNull();
        assertThat(result.rows()).isEqualTo(7);
        assertThat(result.cols()).isEqualTo(7);
        for (int row = 0; row < 7; row ++) {
            for (int col = 0; col < 7; col ++) {
                Tile tile = result.get(row, col);
                Tile expectedTile = Tile.BRICK_FLOOR;
                if (row < 2 || col < 2) {
                    expectedTile = Tile.GRASSLANDS;
                }
                assertThat(tile).isEqualTo(expectedTile);
            }
        }
    }
}
