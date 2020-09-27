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

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class Maps {
    private final World world;

    private final Set<Map> maps;

    public static Maps fromFiles(String worldFilename) throws IOException {
        World world = World.fromStream(ClassLoader.getSystemClassLoader().getResourceAsStream(worldFilename));
        Set<Map> maps = new HashSet<>();
        maps.add(loadMap("data/empath.ult", 1, 28, 50, 15, 30, Tile.GRASSLANDS));
        maps.add(loadMap("data/lcb_1.ult", 2, 86, 107, 15, 30, Tile.GRASSLANDS));
        maps.add(loadMap("data/lcb_2.ult", 3, -1, -1, 15, 30, Tile.GRASSLANDS));
        maps.add(loadMap("data/lycaeum.ult", 4, 218, 107, 15, 30, Tile.GRASSLANDS));
        maps.add(loadMap("data/serpent.ult", 5, 146, 241, 15, 30, Tile.GRASSLANDS));
        maps.add(loadMap("data/britain.ult", 6, 82, 106, 1, 15, Tile.GRASSLANDS));
        maps.add(loadMap("data/jhelom.ult", 7, 36, 222, 1, 15, Tile.GRASSLANDS));
        maps.add(loadMap("data/magincia.ult", 8, 187, 169, 1, 15, Tile.GRASSLANDS));
        maps.add(loadMap("data/minoc.ult", 9, 159, 20, 1, 15, Tile.GRASSLANDS));
        maps.add(loadMap("data/moonglow.ult", 10, 232, 135, 1, 15, Tile.GRASSLANDS));
        maps.add(loadMap("data/skara.ult", 11, 22, 128, 1, 15, Tile.GRASSLANDS));
        maps.add(loadMap("data/trinsic.ult", 12, 106, 184, 1, 15, Tile.GRASSLANDS));
        maps.add(loadMap("data/yew.ult", 13, 58, 43, 1, 15, Tile.GRASSLANDS));
        maps.add(loadMap("data/cove.ult", 14, 136, 90, 1, 15, Tile.GRASSLANDS));
        maps.add(loadMap("data/den.ult", 15, 136, 158, 1, 15, Tile.GRASSLANDS));
        maps.add(loadMap("data/paws.ult", 16, 98, 145, 1, 15, Tile.GRASSLANDS));
        maps.add(loadMap("data/vesper.ult", 17, 201, 59, 1, 15, Tile.GRASSLANDS));
        return new Maps(world, maps);
    }

    private Maps(World world, Set<Map> maps) {
        this.world = world;
        this.maps = maps;
    }

    public World world() {
        return world;
    }

    public Map mapAt(int x, int y) {
        return maps.stream().filter(map -> map.worldX() == x && map.worldY() == y).findAny().orElseThrow(RuntimeException::new);
    }

    private static Map loadMap(String mapFilename, int id, int worldX, int worldY, int startX, int startY, Tile areaTile) throws IOException {
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(mapFilename);
        return Settlement.fromStream(stream, id, worldX, worldY, startX, startY, areaTile);
    }
}
