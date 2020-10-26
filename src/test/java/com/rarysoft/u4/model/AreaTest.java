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

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AreaTest {
    @Test
    public void mapWhenAreaOfRenderedTileMappedToTileReturnsCorrectAreaOfTile() {
        RenderedTile[][] renderedTiles = new RenderedTile[][] {
                {
                    new RenderedTile(Tile.GRASSLANDS, null),
                    new RenderedTile(Tile.SCRUBLAND, null),
                    new RenderedTile(Tile.MOUNTAINS, null)
                },
                {
                    new RenderedTile(Tile.BRICK_FLOOR, null),
                    new RenderedTile(Tile.BRICK_WALL, null),
                    new RenderedTile(Tile.LOCKED_DOOR, null)
                }
        };
        Area<RenderedTile> area = new Area<>(renderedTiles);

        Area<Tile> result = area.map(RenderedTile::tile);

        assertThat(result).isNotNull();
        assertThat(result.rows()).isEqualTo(2);
        assertThat(result.cols()).isEqualTo(3);
        assertThat(result.get(0, 0)).isEqualTo(Tile.GRASSLANDS);
        assertThat(result.get(0, 1)).isEqualTo(Tile.SCRUBLAND);
        assertThat(result.get(0, 2)).isEqualTo(Tile.MOUNTAINS);
        assertThat(result.get(1, 0)).isEqualTo(Tile.BRICK_FLOOR);
        assertThat(result.get(1, 1)).isEqualTo(Tile.BRICK_WALL);
        assertThat(result.get(1, 2)).isEqualTo(Tile.LOCKED_DOOR);
    }
}
