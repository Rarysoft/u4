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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class Maps {
    private final World world;

    private final Set<Map> maps;

    public static Maps fromFiles(String directory) throws IOException {
        World world = World.fromStream(Maps.class.getResourceAsStream(path(directory, "world.map")));
        Set<Map> maps = new HashSet<>();
        maps.add(loadMap(path(directory, "britain.ult"), LocationIds.BRITAIN, 106, 82, 15, 1, Tile.GRASSLANDS));
        maps.add(loadMap(path(directory, "cove.ult"), LocationIds.COVE, 90, 136, 15, 1, Tile.GRASSLANDS));
        maps.add(loadMap(path(directory, "den.ult"), LocationIds.BUCCANEERS_DEN, 158, 136, 15, 1, Tile.GRASSLANDS));
        maps.add(loadMap(path(directory, "empath.ult"), LocationIds.EMPATH_ABBEY, 50, 28, 30, 15, Tile.GRASSLANDS));
        maps.add(loadMap(path(directory, "jhelom.ult"), LocationIds.JHELOM, 222, 36, 15, 1, Tile.GRASSLANDS));
        maps.add(loadMap(path(directory, "lcb_1.ult"), LocationIds.CASTLE_BRITANNIA_1, 107, 86, 30, 15, Tile.GRASSLANDS));
        maps.add(loadMap(path(directory, "lcb_2.ult"), LocationIds.CASTLE_BRITANNIA_2, -1, -1, 30, 15, Tile.GRASSLANDS));
        maps.add(loadMap(path(directory, "lycaeum.ult"), LocationIds.LYCAEUM, 107, 218, 30, 15, Tile.GRASSLANDS));
        maps.add(loadMap(path(directory, "serpent.ult"), LocationIds.SERPENTS_HOLD, 241, 146, 30, 15, Tile.GRASSLANDS));
        maps.add(loadMap(path(directory, "magincia.ult"), LocationIds.MAGINCIA, 169, 187, 15, 1, Tile.GRASSLANDS));
        maps.add(loadMap(path(directory, "minoc.ult"), LocationIds.MINOC, 20, 159, 15, 1, Tile.GRASSLANDS));
        maps.add(loadMap(path(directory, "moonglow.ult"), LocationIds.MOONGLOW, 135, 232, 15, 1, Tile.GRASSLANDS));
        maps.add(loadMap(path(directory, "paws.ult"), LocationIds.PAWS, 145, 98, 15, 1, Tile.GRASSLANDS));
        maps.add(loadMap(path(directory, "skara.ult"), LocationIds.SKARA_BRAE, 128, 22, 15, 1, Tile.GRASSLANDS));
        maps.add(loadMap(path(directory, "trinsic.ult"), LocationIds.TRINSIC, 184, 106, 15, 1, Tile.GRASSLANDS));
        maps.add(loadMap(path(directory, "vesper.ult"), LocationIds.VESPER, 59, 201, 15, 1, Tile.GRASSLANDS));
        maps.add(loadMap(path(directory, "yew.ult"), LocationIds.YEW, 43, 58, 15, 1, Tile.GRASSLANDS));
        return new Maps(world, maps);
    }

    private static String path(String directory, String filename) {
        return String.format("%s%s%s", directory, File.separator, filename);
    }

    private Maps(World world, Set<Map> maps) {
        this.world = world;
        this.maps = maps;
    }

    public World world() {
        return world;
    }

    public Map mapAt(int row, int col) {
        return maps.stream().filter(map -> map.worldCol() == col && map.worldRow() == row).findAny().orElseThrow(RuntimeException::new);
    }

    public Map map(int id) {
        return maps.stream().filter(map -> map.id() == id).findAny().orElseThrow(RuntimeException::new);
    }

    private static Map loadMap(String mapFilename, int id, int worldRow, int worldCol, int startRow, int startCol, Tile areaTile) throws IOException {
        InputStream stream = Maps.class.getResourceAsStream(mapFilename);
        return Settlement.fromStream(stream, id, worldCol, worldRow, startCol, startRow, areaTile);
    }
}
