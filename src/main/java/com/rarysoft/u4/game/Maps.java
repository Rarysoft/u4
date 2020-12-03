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

import com.rarysoft.u4.game.party.Location;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Maps {
    private final HashMap<LocationLevel, Map> maps;

    public static Maps fromFiles(String directory) throws IOException {
        return fromFiles(directory, null);
    }

    public static Maps fromFiles(String directory, SurfaceMapper surfaceMapper) throws IOException {
        HashMap<LocationLevel, Map> maps = new HashMap<>();
        maps.put(LocationLevel.from(Location.SURFACE, 0), Surface.fromStream(Maps.class.getResourceAsStream(path(directory, "world.map")), surfaceMapper));
        maps.put(LocationLevel.from(Location.BRITAIN, 0), loadMap(path(directory, "britain.ult"), Location.BRITAIN, 0, 106, 82, 15, 1));
        maps.put(LocationLevel.from(Location.COVE, 0), loadMap(path(directory, "cove.ult"), Location.COVE, 0, 90, 136, 15, 1));
        maps.put(LocationLevel.from(Location.BUCCANEERS_DEN, 0), loadMap(path(directory, "den.ult"), Location.BUCCANEERS_DEN, 0, 158, 136, 15, 1));
        maps.put(LocationLevel.from(Location.EMPATH_ABBEY, 0), loadMap(path(directory, "empath.ult"), Location.EMPATH_ABBEY, 0, 50, 28, 30, 15));
        maps.put(LocationLevel.from(Location.JHELOM, 0), loadMap(path(directory, "jhelom.ult"), Location.JHELOM, 0, 222, 36, 15, 1));
        maps.put(LocationLevel.from(Location.CASTLE_BRITANNIA, 0), loadMap(path(directory, "lcb_1.ult"), Location.CASTLE_BRITANNIA, 0, 107, 86, 30, 15));
        maps.put(LocationLevel.from(Location.CASTLE_BRITANNIA, 1), loadMap(path(directory, "lcb_2.ult"), Location.CASTLE_BRITANNIA, 1, 107, 86, 30, 15));
        maps.put(LocationLevel.from(Location.THE_LYCAEUM, 0), loadMap(path(directory, "lycaeum.ult"), Location.THE_LYCAEUM, 0, 107, 218, 30, 15));
        maps.put(LocationLevel.from(Location.SERPENTS_HOLD, 0), loadMap(path(directory, "serpent.ult"), Location.SERPENTS_HOLD, 0, 241, 146, 30, 15));
        maps.put(LocationLevel.from(Location.MAGINCIA, 0), loadMap(path(directory, "magincia.ult"), Location.MAGINCIA, 0, 169, 187, 15, 1));
        maps.put(LocationLevel.from(Location.MINOC, 0), loadMap(path(directory, "minoc.ult"), Location.MINOC, 0, 20, 159, 15, 1));
        maps.put(LocationLevel.from(Location.MOONGLOW, 0), loadMap(path(directory, "moonglow.ult"), Location.MOONGLOW, 0, 135, 232, 15, 1));
        maps.put(LocationLevel.from(Location.PAWS, 0), loadMap(path(directory, "paws.ult"), Location.PAWS, 0, 145, 98, 15, 1));
        maps.put(LocationLevel.from(Location.SKARA_BRAE, 0), loadMap(path(directory, "skara.ult"), Location.SKARA_BRAE, 0, 128, 22, 15, 1));
        maps.put(LocationLevel.from(Location.TRINSIC, 0), loadMap(path(directory, "trinsic.ult"), Location.TRINSIC, 0, 184, 106, 15, 1));
        maps.put(LocationLevel.from(Location.VESPER, 0), loadMap(path(directory, "vesper.ult"), Location.VESPER, 0, 59, 201, 15, 1));
        maps.put(LocationLevel.from(Location.YEW, 0), loadMap(path(directory, "yew.ult"), Location.YEW, 0, 43, 58, 15, 1));
        loadDungeonMaps(path(directory, "covetous.dng"), Location.COVETOUS, 27, 156).forEach(dungeonLevel -> maps.put(LocationLevel.from(dungeonLevel.location(), dungeonLevel.level()), dungeonLevel));
        loadDungeonMaps(path(directory, "deceit.dng"), Location.DECEIT, 73, 240).forEach(dungeonLevel -> maps.put(LocationLevel.from(dungeonLevel.location(), dungeonLevel.level()), dungeonLevel));
        loadDungeonMaps(path(directory, "despise.dng"), Location.DESPISE, 67, 91).forEach(dungeonLevel -> maps.put(LocationLevel.from(dungeonLevel.location(), dungeonLevel.level()), dungeonLevel));
        loadDungeonMaps(path(directory, "destard.dng"), Location.DESTARD, 168, 72).forEach(dungeonLevel -> maps.put(LocationLevel.from(dungeonLevel.location(), dungeonLevel.level()), dungeonLevel));
        // TODO: not sure how this will work yet; entrance to Hythloth is in LB's castle, not on the surface
        loadDungeonMaps(path(directory, "hythloth.dng"), Location.HYTHLOTH, -1, -1).forEach(dungeonLevel -> maps.put(LocationLevel.from(dungeonLevel.location(), dungeonLevel.level()), dungeonLevel));
        loadDungeonMaps(path(directory, "shame.dng"), Location.SHAME, 102, 58).forEach(dungeonLevel -> maps.put(LocationLevel.from(dungeonLevel.location(), dungeonLevel.level()), dungeonLevel));
        loadDungeonMaps(path(directory, "wrong.dng"), Location.WRONG, 20, 126).forEach(dungeonLevel -> maps.put(LocationLevel.from(dungeonLevel.location(), dungeonLevel.level()), dungeonLevel));
        return new Maps(maps);
    }

    private static String path(String directory, String filename) {
        return String.format("%s%s%s", directory, File.separator, filename);
    }

    private Maps(HashMap<LocationLevel, Map> maps) {
        this.maps = maps;
    }

    public Map surface() {
        return maps.get(LocationLevel.from(Location.SURFACE, 0));
    }

    public Map mapAt(int row, int col) {
        return maps.values().stream().filter(map -> map.worldCol() == col && map.worldRow() == row && map.level() == 0).findAny().orElseThrow(RuntimeException::new);
    }

    public Map map(Location location, int level) {
        return maps.get(LocationLevel.from(location, level));
    }

    private static Map loadMap(String mapFilename, Location id, int level, int worldRow, int worldCol, int startRow, int startCol) throws IOException {
        InputStream stream = Maps.class.getResourceAsStream(mapFilename);
        return Settlement.fromStream(stream, id, level, worldRow, worldCol, startRow, startCol, Tile.GRASSLANDS);
    }

    private static List<Map> loadDungeonMaps(String mapFilename, Location id, int worldRow, int worldCol) throws IOException {
        InputStream stream = Maps.class.getResourceAsStream(mapFilename);
        List<Map> dungeonLevels = new ArrayList<>();
        for (int index = 0; index < 8; index ++) {
            dungeonLevels.add(DungeonLevel.fromStream(stream, id, index, worldRow, worldCol));
        }
        return dungeonLevels;
    }
}
