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

import com.rarysoft.u4.ui.graphics.Colours;

public class DefaultTileMapper implements TileMapper {
    private static final int TILE_COUNT = 256;

    @Override
    public int tileCount() {
        return TILE_COUNT;
    }

    @Override
    public int[][][][] map(int[][][][] data) {
        int[][][][] mappedTiles = new int[TILE_COUNT][16][][];
        for (int tileIndex = 0; tileIndex < TILE_COUNT; tileIndex ++) {
            int[][] tileData = data[tileIndex][0];
            Tile tile = Tile.forIndex(tileIndex);
            for (int frameIndex = 0; frameIndex < Tiles.FRAME_COUNT; frameIndex ++) {
                switch (tile) {
                    case DEEP_WATER:
                    case MEDIUM_WATER:
                    case SHALLOW_WATER:
                    case POISON_FIELD:
                    case ENERGY_FIELD:
                    case FIRE_FIELD:
                    case SLEEP_FIELD:
                        mappedTiles[tileIndex][frameIndex] = TileEnhancer.animateDescending(tileData, frameIndex);
                        break;

                    case TOWN:
                        mappedTiles[tileIndex][frameIndex] = TileEnhancer.animateFlag(tileData, frameIndex, 3, 4, 5, 7, Colours.COLOUR_BRIGHT_RED);
                        break;

                    case CASTLE:
                        mappedTiles[tileIndex][frameIndex] = TileEnhancer.animateFlag(tileData, frameIndex, 1, 2, 9, 11, Colours.COLOUR_BRIGHT_RED);
                        break;

                    case CASTLE_BRITANNIA_ENTRANCE:
                        mappedTiles[tileIndex][frameIndex] = TileEnhancer.animateFlag(tileData, frameIndex, 1, 2, 5, 7, Colours.COLOUR_BRIGHT_RED);
                        break;

                    case MAGE_1:
                    case BARD_1:
                    case FIGHTER_1:
                    case DRUID_1:
                    case TINKER_1:
                    case PALADIN_1:
                    case RANGER_1:
                    case SHEPHERD_1:
                    case GUARD_1:
                    case CITIZEN_1:
                    case SINGING_BARD_1:
                    case JESTER_1:
                    case BEGGAR_1:
                    case CHILD_1:
                    case BULL_1:
                    case LORD_BRITISH_1:
                    case NIXIE_1:
                    case GIANT_SQUID_1:
                    case SEA_SERPENT_1:
                    case SEAHORSE_1:
                    case WHIRLPOOL_1:
                    case STORM_1:
                        mappedTiles[tileIndex][frameIndex] = selectFrame(frameIndex, TileEnhancer.enhance(tile, tileData), TileEnhancer.enhance(tile, data[tile.index() + 1][0]));
                        break;

                    case MAGE_2:
                    case BARD_2:
                    case FIGHTER_2:
                    case DRUID_2:
                    case TINKER_2:
                    case PALADIN_2:
                    case RANGER_2:
                    case SHEPHERD_2:
                    case GUARD_2:
                    case CITIZEN_2:
                    case SINGING_BARD_2:
                    case JESTER_2:
                    case BEGGAR_2:
                    case CHILD_2:
                    case BULL_2:
                    case LORD_BRITISH_2:
                    case NIXIE_2:
                    case GIANT_SQUID_2:
                    case SEA_SERPENT_2:
                    case SEAHORSE_2:
                    case WHIRLPOOL_2:
                    case STORM_2:
                        mappedTiles[tileIndex][frameIndex] = selectFrame(frameIndex, TileEnhancer.enhance(tile, tileData), TileEnhancer.enhance(tile, data[tile.index() - 1][0]));
                        break;

                    case RAT_1:
                    case BAT_1:
                    case GIANT_SPIDER_1:
                    case GHOST_1:
                    case SLIME_1:
                    case TROLL_1:
                    case GREMLIN_1:
                    case MIMIC_1:
                    case REAPER_1:
                    case INSECT_SWARM_1:
                    case GAZER_1:
                    case PHANTOM_1:
                    case ORC_1:
                    case SKELETON_1:
                    case ROGUE_1:
                    case PYTHON_1:
                    case ETTIN_1:
                    case HEADLESS_1:
                    case CYCLOPS_1:
                    case WISP_1:
                    case EVIL_MAGE_1:
                    case LICH_1:
                    case LAVA_LIZARD_1:
                    case ZORN_1:
                    case DAEMON_1:
                    case HYDRA_1:
                    case DRAGON_1:
                    case BALRON_1:
                        mappedTiles[tileIndex][frameIndex] = selectFrame(frameIndex, TileEnhancer.enhance(tile, tileData), TileEnhancer.enhance(tile, data[tile.index() + 1][0]), TileEnhancer.enhance(tile, data[tile.index() + 2][0]), TileEnhancer.enhance(tile, data[tile.index() + 3][0]));
                        break;

                    case RAT_2:
                    case BAT_2:
                    case GIANT_SPIDER_2:
                    case GHOST_2:
                    case SLIME_2:
                    case TROLL_2:
                    case GREMLIN_2:
                    case MIMIC_2:
                    case REAPER_2:
                    case INSECT_SWARM_2:
                    case GAZER_2:
                    case PHANTOM_2:
                    case ORC_2:
                    case SKELETON_2:
                    case ROGUE_2:
                    case PYTHON_2:
                    case ETTIN_2:
                    case HEADLESS_2:
                    case CYCLOPS_2:
                    case WISP_2:
                    case EVIL_MAGE_2:
                    case LICH_2:
                    case LAVA_LIZARD_2:
                    case ZORN_2:
                    case DAEMON_2:
                    case HYDRA_2:
                    case DRAGON_2:
                    case BALRON_2:
                        mappedTiles[tileIndex][frameIndex] = selectFrame(frameIndex, TileEnhancer.enhance(tile, tileData), TileEnhancer.enhance(tile, data[tile.index() + 1][0]), TileEnhancer.enhance(tile, data[tile.index() + 2][0]), TileEnhancer.enhance(tile, data[tile.index() - 1][0]));
                        break;

                    case RAT_3:
                    case BAT_3:
                    case GIANT_SPIDER_3:
                    case GHOST_3:
                    case SLIME_3:
                    case TROLL_3:
                    case GREMLIN_3:
                    case MIMIC_3:
                    case REAPER_3:
                    case INSECT_SWARM_3:
                    case GAZER_3:
                    case PHANTOM_3:
                    case ORC_3:
                    case SKELETON_3:
                    case ROGUE_3:
                    case PYTHON_3:
                    case ETTIN_3:
                    case HEADLESS_3:
                    case CYCLOPS_3:
                    case WISP_3:
                    case EVIL_MAGE_3:
                    case LICH_3:
                    case LAVA_LIZARD_3:
                    case ZORN_3:
                    case DAEMON_3:
                    case HYDRA_3:
                    case DRAGON_3:
                    case BALRON_3:
                        mappedTiles[tileIndex][frameIndex] = selectFrame(frameIndex, TileEnhancer.enhance(tile, tileData), TileEnhancer.enhance(tile, data[tile.index() + 1][0]), TileEnhancer.enhance(tile, data[tile.index() - 2][0]), TileEnhancer.enhance(tile, data[tile.index() - 1][0]));

                    case RAT_4:
                    case BAT_4:
                    case GIANT_SPIDER_4:
                    case GHOST_4:
                    case SLIME_4:
                    case TROLL_4:
                    case GREMLIN_4:
                    case MIMIC_4:
                    case REAPER_4:
                    case INSECT_SWARM_4:
                    case GAZER_4:
                    case PHANTOM_4:
                    case ORC_4:
                    case SKELETON_4:
                    case ROGUE_4:
                    case PYTHON_4:
                    case ETTIN_4:
                    case HEADLESS_4:
                    case CYCLOPS_4:
                    case WISP_4:
                    case EVIL_MAGE_4:
                    case LICH_4:
                    case LAVA_LIZARD_4:
                    case ZORN_4:
                    case DAEMON_4:
                    case HYDRA_4:
                    case DRAGON_4:
                    case BALRON_4:
                        mappedTiles[tileIndex][frameIndex] = selectFrame(frameIndex, TileEnhancer.enhance(tile, tileData), TileEnhancer.enhance(tile, data[tile.index() - 3][0]), TileEnhancer.enhance(tile, data[tile.index() - 2][0]), TileEnhancer.enhance(tile, data[tile.index() - 1][0]));
                        break;

                    default:
                        mappedTiles[tileIndex][frameIndex] = TileEnhancer.enhance(tile, tileData);
                        break;
                }
            }
        }
        return mappedTiles;
    }

    private int[][] selectFrame(int frameIndex, int[][] frame1, int[][] frame2) {
        return frameIndex < 2 ||
                (frameIndex >= 4 && frameIndex < 6) ||
                (frameIndex >= 8 && frameIndex < 12) ||
                frameIndex >= 14 ? frame1 : frame2;
    }

    private int[][] selectFrame(int frameIndex, int[][] frame1, int[][] frame2, int[][] frame3, int[][] frame4) {
        return (frameIndex >= 0 && frameIndex < 2) || (frameIndex >= 8 && frameIndex < 12) ? frame1 :
                (frameIndex >= 2 && frameIndex < 4) || (frameIndex >= 12 && frameIndex < 14) ? frame2 :
                        (frameIndex >= 4 && frameIndex < 6) || (frameIndex >= 14 && frameIndex < 16) ? frame3 :
                                frame4;
    }
}
