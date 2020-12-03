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
package com.rarysoft.u4.game.u5;

import com.rarysoft.u4.game.Tile;
import com.rarysoft.u4.game.TileEnhancer;
import com.rarysoft.u4.game.TileMapper;
import com.rarysoft.u4.game.Tiles;
import com.rarysoft.u4.ui.graphics.Colours;

public class U5TileMapper implements TileMapper {
    private static final int TILE_COUNT = 512;

    @Override
    public int tileCount() {
        return TILE_COUNT;
    }

    @Override
    public int[][][][] map(int[][][][] data) {
        int[][][][] mappedData = new int[281][][][];
        for (int frameIndex = 0; frameIndex < Tiles.FRAME_COUNT; frameIndex ++) {
            mappedData[Tile.DEEP_WATER.index()] = framesForTileAnimatedDescending(TileEnhancer.enhance(Tile.DEEP_WATER, data[1][0]));
            mappedData[Tile.MEDIUM_WATER.index()] = framesForTileAnimatedDescending(TileEnhancer.enhance(Tile.MEDIUM_WATER, data[2][0]));
            mappedData[Tile.SHALLOW_WATER.index()] = framesForTileAnimatedDescending(TileEnhancer.enhance(Tile.SHALLOW_WATER, data[3][0]));
            mappedData[Tile.SWAMP.index()] = framesForStillTile(TileEnhancer.enhance(Tile.SWAMP, data[4][0]));
            mappedData[Tile.GRASSLANDS.index()] = framesForStillTile(TileEnhancer.enhance(Tile.GRASSLANDS, data[5][0]));
            mappedData[Tile.SCRUBLAND.index()] = framesForStillTile(TileEnhancer.enhance(Tile.SCRUBLAND, data[8][0]));
            mappedData[Tile.FOREST.index()] = framesForStillTile(TileEnhancer.enhance(Tile.FOREST, data[9][0]));
            mappedData[Tile.HILLS.index()] = framesForStillTile(TileEnhancer.enhance(Tile.HILLS, data[11][0]));
            mappedData[Tile.MOUNTAINS.index()] = framesForStillTile(TileEnhancer.enhance(Tile.MOUNTAINS, data[13][0]));
            mappedData[Tile.DUNGEON_ENTRANCE.index()] = framesForStillTile(TileEnhancer.enhance(Tile.DUNGEON_ENTRANCE, data[23][0]));
            mappedData[Tile.TOWN.index()] = framesForTileWithAnimatedFlag(TileEnhancer.enhance(Tile.TOWN, data[20][0]), 3, 4, 5, 7, Colours.COLOUR_BRIGHT_YELLOW);
            mappedData[Tile.CASTLE.index()] = framesForTileWithAnimatedFlag(TileEnhancer.enhance(Tile.CASTLE, data[21][0]), 1, 2, 6, 8, Colours.COLOUR_BRIGHT_WHITE);
            mappedData[Tile.VILLAGE.index()] = framesForStillTile(TileEnhancer.enhance(Tile.VILLAGE, data[19][0]));
            mappedData[Tile.CASTLE_BRITANNIA_WEST.index()] = framesForStillTile(TileEnhancer.enhance(Tile.CASTLE_BRITANNIA_WEST, data[61][0]));
            mappedData[Tile.CASTLE_BRITANNIA_ENTRANCE.index()] = framesForTileWithAnimatedFlag(TileEnhancer.enhance(Tile.CASTLE_BRITANNIA_ENTRANCE, data[62][0]), 0, 2, 7, 9, Colours.COLOUR_BRIGHT_BLUE);
            mappedData[Tile.CASTLE_BRITANNIA_EAST.index()] = framesForStillTile(TileEnhancer.enhance(Tile.CASTLE_BRITANNIA_EAST, data[63][0]));
            mappedData[Tile.SHIP_WEST.index()] = framesForStillTile(TileEnhancer.enhance(Tile.SHIP_WEST, data[291][0]));
            mappedData[Tile.SHIP_NORTH.index()] = framesForStillTile(TileEnhancer.enhance(Tile.SHIP_NORTH, data[288][0]));
            mappedData[Tile.SHIP_EAST.index()] = framesForStillTile(TileEnhancer.enhance(Tile.SHIP_EAST, data[289][0]));
            mappedData[Tile.SHIP_SOUTH.index()] = framesForStillTile(TileEnhancer.enhance(Tile.SHIP_SOUTH, data[290][0]));
            mappedData[Tile.HORSE_WEST.index()] = framesForStillTile(TileEnhancer.enhance(Tile.HORSE_WEST, data[273][0]));
            mappedData[Tile.HORSE_EAST.index()] = framesForStillTile(TileEnhancer.enhance(Tile.HORSE_EAST, data[272][0]));
            mappedData[Tile.TILE_FLOOR.index()] = framesForStillTile(TileEnhancer.enhance(Tile.TILE_FLOOR, data[69][0]));
            mappedData[Tile.BRIDGE.index()] = framesForStillTile(TileEnhancer.enhance(Tile.BRIDGE, data[106][0]));
            mappedData[Tile.BALLOON.index()] = framesForStillTile(TileEnhancer.enhance(Tile.BALLOON, balloon()));
            mappedData[Tile.BRIDGE_NORTH.index()] = framesForStillTile(TileEnhancer.enhance(Tile.BRIDGE_NORTH, data[64][0]));
            mappedData[Tile.BRIDGE_SOUTH.index()] = framesForStillTile(TileEnhancer.enhance(Tile.BRIDGE_SOUTH, data[64][0]));
            mappedData[Tile.LADDER_UP.index()] = framesForStillTile(TileEnhancer.enhance(Tile.LADDER_UP, data[200][0]));
            mappedData[Tile.LADDER_DOWN.index()] = framesForStillTile(TileEnhancer.enhance(Tile.LADDER_DOWN, data[201][0]));
            mappedData[Tile.RUINS.index()] = framesForStillTile(TileEnhancer.enhance(Tile.RUINS, ruins()));
            mappedData[Tile.SHRINE.index()] = framesForStillTile(TileEnhancer.enhance(Tile.SHRINE, data[25][0]));
            mappedData[Tile.AVATAR.index()] = framesForStillTile(TileEnhancer.enhance(Tile.AVATAR, data[284][0]));
            mappedData[Tile.MAGE_1.index()] = framesForRotatingAnimatedTile(    // U4 mage uses U5 mage
                    TileEnhancer.enhance(Tile.MAGE_1, data[320][0]),
                    TileEnhancer.enhance(Tile.MAGE_2, data[321][0]),
                    TileEnhancer.enhance(Tile.MAGE_1, data[322][0]),
                    TileEnhancer.enhance(Tile.MAGE_2, data[323][0])
            );
            mappedData[Tile.MAGE_2.index()] = framesForRotatingAnimatedTile(
                    TileEnhancer.enhance(Tile.MAGE_2, data[322][0]),
                    TileEnhancer.enhance(Tile.MAGE_2, data[323][0]),
                    TileEnhancer.enhance(Tile.MAGE_2, data[320][0]),
                    TileEnhancer.enhance(Tile.MAGE_2, data[321][0])
            );
            mappedData[Tile.BARD_1.index()] = framesForRotatingAnimatedTile(    // U4 bard uses U5 bard
                    TileEnhancer.enhance(Tile.BARD_1, data[324][0]),
                    TileEnhancer.enhance(Tile.BARD_1, data[325][0]),
                    TileEnhancer.enhance(Tile.BARD_1, data[326][0]),
                    TileEnhancer.enhance(Tile.BARD_1, data[327][0])
            );
            mappedData[Tile.BARD_2.index()] = framesForRotatingAnimatedTile(
                    TileEnhancer.enhance(Tile.BARD_2, data[326][0]),
                    TileEnhancer.enhance(Tile.BARD_2, data[327][0]),
                    TileEnhancer.enhance(Tile.BARD_2, data[324][0]),
                    TileEnhancer.enhance(Tile.BARD_2, data[325][0])
            );
            mappedData[Tile.FIGHTER_1.index()] = framesForRotatingAnimatedTile( // U4 fighter uses U5 fighter
                    TileEnhancer.enhance(Tile.FIGHTER_1, data[328][0]),
                    TileEnhancer.enhance(Tile.FIGHTER_1, data[329][0]),
                    TileEnhancer.enhance(Tile.FIGHTER_1, data[330][0]),
                    TileEnhancer.enhance(Tile.FIGHTER_1, data[331][0])
            );
            mappedData[Tile.FIGHTER_2.index()] = framesForRotatingAnimatedTile(
                    TileEnhancer.enhance(Tile.FIGHTER_2, data[330][0]),
                    TileEnhancer.enhance(Tile.FIGHTER_2, data[331][0]),
                    TileEnhancer.enhance(Tile.FIGHTER_2, data[328][0]),
                    TileEnhancer.enhance(Tile.FIGHTER_2, data[329][0])
            );
            mappedData[Tile.DRUID_1.index()] = framesForRotatingAnimatedTile(   // U4 druid uses U5 mage
                    TileEnhancer.enhance(Tile.DRUID_1, data[320][0]),
                    TileEnhancer.enhance(Tile.DRUID_1, data[321][0]),
                    TileEnhancer.enhance(Tile.DRUID_1, data[322][0]),
                    TileEnhancer.enhance(Tile.DRUID_1, data[323][0])
            );
            mappedData[Tile.DRUID_2.index()] = framesForRotatingAnimatedTile(
                    TileEnhancer.enhance(Tile.DRUID_2, data[322][0]),
                    TileEnhancer.enhance(Tile.DRUID_2, data[323][0]),
                    TileEnhancer.enhance(Tile.DRUID_2, data[320][0]),
                    TileEnhancer.enhance(Tile.DRUID_2, data[321][0])
            );
            mappedData[Tile.TINKER_1.index()] = framesForRotatingAnimatedTile(  // U4 tinker uses U5 bard
                    TileEnhancer.enhance(Tile.TINKER_1, data[324][0]),
                    TileEnhancer.enhance(Tile.TINKER_1, data[325][0]),
                    TileEnhancer.enhance(Tile.TINKER_1, data[326][0]),
                    TileEnhancer.enhance(Tile.TINKER_1, data[327][0])
            );
            mappedData[Tile.TINKER_2.index()] = framesForRotatingAnimatedTile(
                    TileEnhancer.enhance(Tile.TINKER_2, data[326][0]),
                    TileEnhancer.enhance(Tile.TINKER_2, data[327][0]),
                    TileEnhancer.enhance(Tile.TINKER_2, data[324][0]),
                    TileEnhancer.enhance(Tile.TINKER_2, data[325][0])
            );
            mappedData[Tile.PALADIN_1.index()] = framesForRotatingAnimatedTile( // U4 paladin uses U5 avatar
                    TileEnhancer.enhance(Tile.PALADIN_1, data[332][0]),
                    TileEnhancer.enhance(Tile.PALADIN_1, data[333][0]),
                    TileEnhancer.enhance(Tile.PALADIN_1, data[334][0]),
                    TileEnhancer.enhance(Tile.PALADIN_1, data[335][0])
            );
            mappedData[Tile.PALADIN_2.index()] = framesForRotatingAnimatedTile(
                    TileEnhancer.enhance(Tile.PALADIN_2, data[334][0]),
                    TileEnhancer.enhance(Tile.PALADIN_2, data[335][0]),
                    TileEnhancer.enhance(Tile.PALADIN_2, data[332][0]),
                    TileEnhancer.enhance(Tile.PALADIN_2, data[333][0])
            );
            mappedData[Tile.RANGER_1.index()] = framesForRotatingAnimatedTile(  // U4 ranger uses U5 avatar
                    TileEnhancer.enhance(Tile.RANGER_1, data[332][0]),
                    TileEnhancer.enhance(Tile.RANGER_1, data[333][0]),
                    TileEnhancer.enhance(Tile.RANGER_1, data[334][0]),
                    TileEnhancer.enhance(Tile.RANGER_1, data[335][0])
            );
            mappedData[Tile.RANGER_2.index()] = framesForRotatingAnimatedTile(
                    TileEnhancer.enhance(Tile.RANGER_2, data[334][0]),
                    TileEnhancer.enhance(Tile.RANGER_2, data[335][0]),
                    TileEnhancer.enhance(Tile.RANGER_2, data[332][0]),
                    TileEnhancer.enhance(Tile.RANGER_2, data[333][0])
            );
            mappedData[Tile.SHEPHERD_1.index()] = framesForRotatingAnimatedTile(    // U4 shepherd uses U5 fighter
                    TileEnhancer.enhance(Tile.SHEPHERD_1, data[328][0]),
                    TileEnhancer.enhance(Tile.SHEPHERD_1, data[329][0]),
                    TileEnhancer.enhance(Tile.SHEPHERD_1, data[330][0]),
                    TileEnhancer.enhance(Tile.SHEPHERD_1, data[331][0])
            );
            mappedData[Tile.SHEPHERD_2.index()] = framesForRotatingAnimatedTile(
                    TileEnhancer.enhance(Tile.SHEPHERD_2, data[330][0]),
                    TileEnhancer.enhance(Tile.SHEPHERD_2, data[331][0]),
                    TileEnhancer.enhance(Tile.SHEPHERD_2, data[328][0]),
                    TileEnhancer.enhance(Tile.SHEPHERD_2, data[329][0])
            );
            mappedData[Tile.COLUMN.index()] = framesForStillTile(TileEnhancer.enhance(Tile.COLUMN, data[70][0]));
            mappedData[Tile.WHITE_SW.index()] = framesForStillTile(TileEnhancer.enhance(Tile.WHITE_SW, data[231][0]));
            mappedData[Tile.WHITE_SE.index()] = framesForStillTile(TileEnhancer.enhance(Tile.WHITE_SE, data[230][0]));
            mappedData[Tile.WHITE_NW.index()] = framesForStillTile(TileEnhancer.enhance(Tile.WHITE_NW, data[228][0]));
            mappedData[Tile.WHITE_NE.index()] = framesForStillTile(TileEnhancer.enhance(Tile.WHITE_NE, data[229][0]));
            mappedData[Tile.MAST.index()] = framesForStillTile(TileEnhancer.enhance(Tile.MAST, data[66][0]));
            mappedData[Tile.SHIPS_WHEEL.index()] = framesForStillTile(TileEnhancer.enhance(Tile.SHIPS_WHEEL, data[67][0]));
            mappedData[Tile.ROCKS.index()] = framesForStillTile(TileEnhancer.enhance(Tile.ROCKS, data[76][0]));
            mappedData[Tile.LYIN_DOWN.index()] = framesForStillTile(TileEnhancer.enhance(Tile.LYIN_DOWN, data[286][0]));
            mappedData[Tile.STONE_WALL.index()] = framesForStillTile(TileEnhancer.enhance(Tile.STONE_WALL, data[77][0]));
            mappedData[Tile.LOCKED_DOOR.index()] = framesForStillTile(TileEnhancer.enhance(Tile.LOCKED_DOOR, data[185][0]));
            mappedData[Tile.UNLOCKED_DOOR.index()] = framesForStillTile(TileEnhancer.enhance(Tile.UNLOCKED_DOOR, data[184][0]));
            mappedData[Tile.CHEST.index()] = framesForStillTile(TileEnhancer.enhance(Tile.CHEST, data[257][0]));
            mappedData[Tile.ANKH.index()] = framesForStillTile(TileEnhancer.enhance(Tile.ANKH, ankh()));
            mappedData[Tile.BRICK_FLOOR.index()] = framesForStillTile(TileEnhancer.enhance(Tile.BRICK_FLOOR, data[68][0]));
            mappedData[Tile.WOODEN_PLANKS.index()] = framesForStillTile(TileEnhancer.enhance(Tile.WOODEN_PLANKS, data[64][0]));
            mappedData[Tile.MOONGATE_1.index()] = framesForStillTile(TileEnhancer.enhance(Tile.MOONGATE_1, data[188][0]));
            mappedData[Tile.MOONGATE_2.index()] = framesForStillTile(TileEnhancer.enhance(Tile.MOONGATE_2, data[188][0]));
            mappedData[Tile.MOONGATE_3.index()] = framesForStillTile(TileEnhancer.enhance(Tile.MOONGATE_3, data[188][0]));
            mappedData[Tile.MOONGATE_4.index()] = framesForStillTile(TileEnhancer.enhance(Tile.MOONGATE_4, data[188][0]));
            mappedData[Tile.POISON_FIELD.index()] = framesForStillTile(colourize(data[39][0], Colours.COLOUR_BRIGHT_GREEN, Colours.COLOUR_GREEN));
            mappedData[Tile.ENERGY_FIELD.index()] = framesForStillTile(colourize(data[39][0], Colours.COLOUR_BRIGHT_BLUE, Colours.COLOUR_BLUE));
            mappedData[Tile.FIRE_FIELD.index()] = framesForStillTile(TileEnhancer.enhance(Tile.FIRE_FIELD, data[40][0]));
            mappedData[Tile.SLEEP_FIELD.index()] = framesForStillTile(colourize(data[40][0], Colours.COLOUR_BRIGHT_MAGENTA, Colours.COLOUR_MAGENTA));
            mappedData[Tile.SOLID_BARRIER.index()] = framesForStillTile(TileEnhancer.enhance(Tile.SOLID_BARRIER, data[254][0]));
            mappedData[Tile.HIDDEN_PASSAGE.index()] = framesForStillTile(TileEnhancer.enhance(Tile.HIDDEN_PASSAGE, data[78][0]));
            mappedData[Tile.ALTAR.index()] = framesForStillTile(TileEnhancer.enhance(Tile.ALTAR, data[0][0]));
            mappedData[Tile.SPIT.index()] = framesForStillTile(TileEnhancer.enhance(Tile.SPIT, data[0][0]));
            mappedData[Tile.LAVA_FLOW.index()] = framesForStillTile(TileEnhancer.enhance(Tile.LAVA_FLOW, data[0][0]));
            mappedData[Tile.MISSILE.index()] = framesForStillTile(TileEnhancer.enhance(Tile.MISSILE, data[0][0]));
            mappedData[Tile.MAGIC_SPHERE.index()] = framesForStillTile(TileEnhancer.enhance(Tile.MAGIC_SPHERE, data[0][0]));
            mappedData[Tile.ATTACK_FLASH.index()] = framesForStillTile(TileEnhancer.enhance(Tile.ATTACK_FLASH, data[0][0]));
            mappedData[Tile.GUARD_1.index()] = framesForStillTile(TileEnhancer.enhance(Tile.GUARD_1, data[368][0]));
            mappedData[Tile.GUARD_2.index()] = framesForStillTile(TileEnhancer.enhance(Tile.GUARD_2, data[369][0]));
            mappedData[Tile.CITIZEN_1.index()] = framesForStillTile(TileEnhancer.enhance(Tile.CITIZEN_1, data[0][0]));
            mappedData[Tile.CITIZEN_2.index()] = framesForStillTile(TileEnhancer.enhance(Tile.CITIZEN_2, data[0][0]));
            mappedData[Tile.SINGING_BARD_1.index()] = framesForStillTile(TileEnhancer.enhance(Tile.SINGING_BARD_1, data[0][0]));
            mappedData[Tile.SINGING_BARD_2.index()] = framesForStillTile(TileEnhancer.enhance(Tile.SINGING_BARD_2, data[0][0]));
            mappedData[Tile.JESTER_1.index()] = framesForStillTile(TileEnhancer.enhance(Tile.JESTER_1, data[0][0]));
            mappedData[Tile.JESTER_2.index()] = framesForStillTile(TileEnhancer.enhance(Tile.JESTER_2, data[0][0]));
            mappedData[Tile.BEGGAR_1.index()] = framesForStillTile(TileEnhancer.enhance(Tile.BEGGAR_1, data[0][0]));
            mappedData[Tile.BEGGAR_2.index()] = framesForStillTile(TileEnhancer.enhance(Tile.BEGGAR_2, data[0][0]));
            mappedData[Tile.CHILD_1.index()] = framesForStillTile(TileEnhancer.enhance(Tile.CHILD_1, data[0][0]));
            mappedData[Tile.CHILD_2.index()] = framesForStillTile(TileEnhancer.enhance(Tile.CHILD_2, data[0][0]));
            mappedData[Tile.BULL_1.index()] = framesForStillTile(TileEnhancer.enhance(Tile.BULL_1, data[0][0]));
            mappedData[Tile.BULL_2.index()] = framesForStillTile(TileEnhancer.enhance(Tile.BULL_2, data[0][0]));
            mappedData[Tile.LORD_BRITISH_1.index()] = framesForStillTile(TileEnhancer.enhance(Tile.LORD_BRITISH_1, data[0][0]));
            mappedData[Tile.LORD_BRITISH_2.index()] = framesForStillTile(TileEnhancer.enhance(Tile.LORD_BRITISH_2, data[0][0]));
            mappedData[Tile.A.index()] = framesForStillTile(TileEnhancer.enhance(Tile.A, data[0][0]));
            mappedData[Tile.B.index()] = framesForStillTile(TileEnhancer.enhance(Tile.B, data[0][0]));
            mappedData[Tile.C.index()] = framesForStillTile(TileEnhancer.enhance(Tile.C, data[0][0]));
            mappedData[Tile.D.index()] = framesForStillTile(TileEnhancer.enhance(Tile.D, data[0][0]));
            mappedData[Tile.E.index()] = framesForStillTile(TileEnhancer.enhance(Tile.E, data[0][0]));
            mappedData[Tile.F.index()] = framesForStillTile(TileEnhancer.enhance(Tile.F, data[0][0]));
            mappedData[Tile.G.index()] = framesForStillTile(TileEnhancer.enhance(Tile.G, data[0][0]));
            mappedData[Tile.H.index()] = framesForStillTile(TileEnhancer.enhance(Tile.H, data[0][0]));
            mappedData[Tile.I.index()] = framesForStillTile(TileEnhancer.enhance(Tile.I, data[0][0]));
            mappedData[Tile.J.index()] = framesForStillTile(TileEnhancer.enhance(Tile.J, data[0][0]));
            mappedData[Tile.K.index()] = framesForStillTile(TileEnhancer.enhance(Tile.K, data[0][0]));
            mappedData[Tile.L.index()] = framesForStillTile(TileEnhancer.enhance(Tile.L, data[0][0]));
            mappedData[Tile.M.index()] = framesForStillTile(TileEnhancer.enhance(Tile.M, data[0][0]));
            mappedData[Tile.N.index()] = framesForStillTile(TileEnhancer.enhance(Tile.N, data[0][0]));
            mappedData[Tile.O.index()] = framesForStillTile(TileEnhancer.enhance(Tile.O, data[0][0]));
            mappedData[Tile.P.index()] = framesForStillTile(TileEnhancer.enhance(Tile.P, data[0][0]));
            mappedData[Tile.Q.index()] = framesForStillTile(TileEnhancer.enhance(Tile.Q, data[0][0]));
            mappedData[Tile.R.index()] = framesForStillTile(TileEnhancer.enhance(Tile.R, data[0][0]));
            mappedData[Tile.S.index()] = framesForStillTile(TileEnhancer.enhance(Tile.S, data[0][0]));
            mappedData[Tile.T.index()] = framesForStillTile(TileEnhancer.enhance(Tile.T, data[0][0]));
            mappedData[Tile.U.index()] = framesForStillTile(TileEnhancer.enhance(Tile.U, data[0][0]));
            mappedData[Tile.V.index()] = framesForStillTile(TileEnhancer.enhance(Tile.V, data[0][0]));
            mappedData[Tile.W.index()] = framesForStillTile(TileEnhancer.enhance(Tile.W, data[0][0]));
            mappedData[Tile.X.index()] = framesForStillTile(TileEnhancer.enhance(Tile.X, data[0][0]));
            mappedData[Tile.Y.index()] = framesForStillTile(TileEnhancer.enhance(Tile.Y, data[0][0]));
            mappedData[Tile.Z.index()] = framesForStillTile(TileEnhancer.enhance(Tile.Z, data[0][0]));
            mappedData[Tile.SPACE.index()] = framesForStillTile(TileEnhancer.enhance(Tile.SPACE, data[0][0]));
            mappedData[Tile.RIGHT.index()] = framesForStillTile(TileEnhancer.enhance(Tile.RIGHT, data[0][0]));
            mappedData[Tile.LEFT.index()] = framesForStillTile(TileEnhancer.enhance(Tile.LEFT, data[0][0]));
            mappedData[Tile.WINDOW.index()] = framesForStillTile(TileEnhancer.enhance(Tile.WINDOW, data[0][0]));
            mappedData[Tile.BLANK.index()] = framesForStillTile(TileEnhancer.enhance(Tile.BLANK, data[0][0]));
            mappedData[Tile.BRICK_WALL.index()] = framesForStillTile(TileEnhancer.enhance(Tile.BRICK_WALL, data[79][0]));
            mappedData[Tile.PIRATE_SHIP_WEST.index()] = framesForStillTile(TileEnhancer.enhance(Tile.PIRATE_SHIP_WEST, data[0][0]));
            mappedData[Tile.PIRATE_SHIP_NORTH.index()] = framesForStillTile(TileEnhancer.enhance(Tile.PIRATE_SHIP_NORTH, data[0][0]));
            mappedData[Tile.PIRATE_SHIP_EAST.index()] = framesForStillTile(TileEnhancer.enhance(Tile.PIRATE_SHIP_EAST, data[0][0]));
            mappedData[Tile.PIRATE_SHIP_SOUTH.index()] = framesForStillTile(TileEnhancer.enhance(Tile.PIRATE_SHIP_SOUTH, data[0][0]));
            mappedData[Tile.NIXIE_1.index()] = framesForStillTile(TileEnhancer.enhance(Tile.NIXIE_1, data[0][0]));
            mappedData[Tile.NIXIE_2.index()] = framesForStillTile(TileEnhancer.enhance(Tile.NIXIE_2, data[0][0]));
            mappedData[Tile.GIANT_SQUID_1.index()] = framesForStillTile(TileEnhancer.enhance(Tile.GIANT_SQUID_1, data[0][0]));
            mappedData[Tile.GIANT_SQUID_2.index()] = framesForStillTile(TileEnhancer.enhance(Tile.GIANT_SQUID_2, data[0][0]));
            mappedData[Tile.SEA_SERPENT_1.index()] = framesForStillTile(TileEnhancer.enhance(Tile.SEA_SERPENT_1, data[0][0]));
            mappedData[Tile.SEA_SERPENT_2.index()] = framesForStillTile(TileEnhancer.enhance(Tile.SEA_SERPENT_2, data[0][0]));
            mappedData[Tile.SEAHORSE_1.index()] = framesForStillTile(TileEnhancer.enhance(Tile.SEAHORSE_1, data[0][0]));
            mappedData[Tile.SEAHORSE_2.index()] = framesForStillTile(TileEnhancer.enhance(Tile.SEAHORSE_2, data[0][0]));
            mappedData[Tile.WHIRLPOOL_1.index()] = framesForStillTile(TileEnhancer.enhance(Tile.WHIRLPOOL_1, data[0][0]));
            mappedData[Tile.WHIRLPOOL_2.index()] = framesForStillTile(TileEnhancer.enhance(Tile.WHIRLPOOL_2, data[0][0]));
            mappedData[Tile.STORM_1.index()] = framesForStillTile(TileEnhancer.enhance(Tile.STORM_1, data[0][0]));
            mappedData[Tile.STORM_2.index()] = framesForStillTile(TileEnhancer.enhance(Tile.STORM_2, data[0][0]));
            mappedData[144] = framesForStillTile(data[0][0]);
            mappedData[145] = framesForStillTile(data[0][0]);
            mappedData[146] = framesForStillTile(data[0][0]);
            mappedData[147] = framesForStillTile(data[0][0]);
            mappedData[148] = framesForStillTile(data[0][0]);
            mappedData[149] = framesForStillTile(data[0][0]);
            mappedData[150] = framesForStillTile(data[0][0]);
            mappedData[151] = framesForStillTile(data[0][0]);
            mappedData[152] = framesForStillTile(data[0][0]);
            mappedData[153] = framesForStillTile(data[0][0]);
            mappedData[154] = framesForStillTile(data[0][0]);
            mappedData[155] = framesForStillTile(data[0][0]);
            mappedData[156] = framesForStillTile(data[0][0]);
            mappedData[157] = framesForStillTile(data[0][0]);
            mappedData[158] = framesForStillTile(data[0][0]);
            mappedData[159] = framesForStillTile(data[0][0]);
            mappedData[160] = framesForStillTile(data[0][0]);
            mappedData[161] = framesForStillTile(data[0][0]);
            mappedData[162] = framesForStillTile(data[0][0]);
            mappedData[163] = framesForStillTile(data[0][0]);
            mappedData[164] = framesForStillTile(data[0][0]);
            mappedData[165] = framesForStillTile(data[0][0]);
            mappedData[166] = framesForStillTile(data[0][0]);
            mappedData[167] = framesForStillTile(data[0][0]);
            mappedData[168] = framesForStillTile(data[0][0]);
            mappedData[169] = framesForStillTile(data[0][0]);
            mappedData[170] = framesForStillTile(data[0][0]);
            mappedData[171] = framesForStillTile(data[0][0]);
            mappedData[172] = framesForStillTile(data[0][0]);
            mappedData[173] = framesForStillTile(data[0][0]);
            mappedData[174] = framesForStillTile(data[0][0]);
            mappedData[175] = framesForStillTile(data[0][0]);
            mappedData[176] = framesForStillTile(data[0][0]);
            mappedData[177] = framesForStillTile(data[0][0]);
            mappedData[178] = framesForStillTile(data[0][0]);
            mappedData[179] = framesForStillTile(data[0][0]);
            mappedData[180] = framesForStillTile(data[0][0]);
            mappedData[181] = framesForStillTile(data[0][0]);
            mappedData[182] = framesForStillTile(data[0][0]);
            mappedData[183] = framesForStillTile(data[0][0]);
            mappedData[184] = framesForStillTile(data[0][0]);
            mappedData[185] = framesForStillTile(data[0][0]);
            mappedData[186] = framesForStillTile(data[0][0]);
            mappedData[187] = framesForStillTile(data[0][0]);
            mappedData[188] = framesForStillTile(data[0][0]);
            mappedData[189] = framesForStillTile(data[0][0]);
            mappedData[190] = framesForStillTile(data[0][0]);
            mappedData[191] = framesForStillTile(data[0][0]);
            mappedData[192] = framesForStillTile(data[0][0]);
            mappedData[193] = framesForStillTile(data[0][0]);
            mappedData[194] = framesForStillTile(data[0][0]);
            mappedData[195] = framesForStillTile(data[0][0]);
            mappedData[196] = framesForStillTile(data[0][0]);
            mappedData[197] = framesForStillTile(data[0][0]);
            mappedData[198] = framesForStillTile(data[0][0]);
            mappedData[199] = framesForStillTile(data[0][0]);
            mappedData[200] = framesForStillTile(data[0][0]);
            mappedData[201] = framesForStillTile(data[0][0]);
            mappedData[202] = framesForStillTile(data[0][0]);
            mappedData[203] = framesForStillTile(data[0][0]);
            mappedData[204] = framesForStillTile(data[0][0]);
            mappedData[205] = framesForStillTile(data[0][0]);
            mappedData[206] = framesForStillTile(data[0][0]);
            mappedData[207] = framesForStillTile(data[0][0]);
            mappedData[208] = framesForStillTile(data[0][0]);
            mappedData[209] = framesForStillTile(data[0][0]);
            mappedData[210] = framesForStillTile(data[0][0]);
            mappedData[211] = framesForStillTile(data[0][0]);
            mappedData[212] = framesForStillTile(data[0][0]);
            mappedData[213] = framesForStillTile(data[0][0]);
            mappedData[214] = framesForStillTile(data[0][0]);
            mappedData[215] = framesForStillTile(data[0][0]);
            mappedData[216] = framesForStillTile(data[0][0]);
            mappedData[217] = framesForStillTile(data[0][0]);
            mappedData[218] = framesForStillTile(data[0][0]);
            mappedData[219] = framesForStillTile(data[0][0]);
            mappedData[220] = framesForStillTile(data[0][0]);
            mappedData[221] = framesForStillTile(data[0][0]);
            mappedData[222] = framesForStillTile(data[0][0]);
            mappedData[223] = framesForStillTile(data[0][0]);
            mappedData[224] = framesForStillTile(data[0][0]);
            mappedData[225] = framesForStillTile(data[0][0]);
            mappedData[226] = framesForStillTile(data[0][0]);
            mappedData[227] = framesForStillTile(data[0][0]);
            mappedData[228] = framesForStillTile(data[0][0]);
            mappedData[229] = framesForStillTile(data[0][0]);
            mappedData[230] = framesForStillTile(data[0][0]);
            mappedData[231] = framesForStillTile(data[0][0]);
            mappedData[232] = framesForStillTile(data[0][0]);
            mappedData[233] = framesForStillTile(data[0][0]);
            mappedData[234] = framesForStillTile(data[0][0]);
            mappedData[235] = framesForStillTile(data[0][0]);
            mappedData[236] = framesForStillTile(data[0][0]);
            mappedData[237] = framesForStillTile(data[0][0]);
            mappedData[238] = framesForStillTile(data[0][0]);
            mappedData[239] = framesForStillTile(data[0][0]);
            mappedData[240] = framesForStillTile(data[0][0]);
            mappedData[241] = framesForStillTile(data[0][0]);
            mappedData[242] = framesForStillTile(data[0][0]);
            mappedData[243] = framesForStillTile(data[0][0]);
            mappedData[244] = framesForStillTile(data[0][0]);
            mappedData[245] = framesForStillTile(data[0][0]);
            mappedData[246] = framesForStillTile(data[0][0]);
            mappedData[247] = framesForStillTile(data[0][0]);
            mappedData[248] = framesForStillTile(data[0][0]);
            mappedData[249] = framesForStillTile(data[0][0]);
            mappedData[250] = framesForStillTile(data[0][0]);
            mappedData[251] = framesForStillTile(data[0][0]);
            mappedData[252] = framesForStillTile(data[0][0]);
            mappedData[253] = framesForStillTile(data[0][0]);
            mappedData[254] = framesForStillTile(data[0][0]);
            mappedData[255] = framesForStillTile(data[0][0]);
            // Extended tileset from Ultima V
            mappedData[Tile.BEACH_N.index()] = framesForStillTile(TileEnhancer.enhance(Tile.BEACH_N, data[48][0]));
            mappedData[Tile.BEACH_NE.index()] = framesForStillTile(eraseSouthwest(data[55][0], Colours.COLOUR_BROWN));
            mappedData[Tile.BEACH_E.index()] = framesForStillTile(TileEnhancer.enhance(Tile.BEACH_E, data[49][0]));
            mappedData[Tile.BEACH_SE.index()] = framesForStillTile(eraseNorthwest(data[52][0], Colours.COLOUR_BROWN));
            mappedData[Tile.BEACH_S.index()] = framesForStillTile(TileEnhancer.enhance(Tile.BEACH_S, data[50][0]));
            mappedData[Tile.BEACH_SW.index()] = framesForStillTile(eraseNortheast(data[53][0], Colours.COLOUR_BROWN));
            mappedData[Tile.BEACH_W.index()] = framesForStillTile(TileEnhancer.enhance(Tile.BEACH_E, data[51][0]));
            mappedData[Tile.BEACH_NW.index()] = framesForStillTile(eraseSoutheast(data[54][0], Colours.COLOUR_BROWN));
            mappedData[264] = framesForStillTile(data[96][0]);
            mappedData[265] = framesForStillTile(data[97][0]);
            mappedData[266] = framesForStillTile(data[98][0]);
            mappedData[267] = framesForStillTile(data[99][0]);
            mappedData[268] = framesForStillTile(data[100][0]);
            mappedData[269] = framesForStillTile(data[101][0]);
            mappedData[270] = framesForStillTile(data[102][0]);
            mappedData[271] = framesForStillTile(data[103][0]);
            mappedData[272] = framesForStillTile(data[104][0]);
            mappedData[273] = framesForStillTile(data[105][0]);
            mappedData[274] = framesForStillTile(data[108][0]);
            mappedData[275] = framesForStillTile(data[109][0]);
            mappedData[276] = framesForStillTile(data[110][0]);
            mappedData[277] = framesForStillTile(data[111][0]);
            mappedData[278] = framesForStillTile(data[58][0]);
            mappedData[279] = framesForStillTile(data[59][0]);
            mappedData[280] = framesForStillTile(data[60][0]);
        }
        return mappedData;
    }

    private int[][][] framesForStillTile(int[][] data) {
        int[][][] frames = new int[Tiles.FRAME_COUNT][][];
        for (int frameIndex = 0; frameIndex < Tiles.FRAME_COUNT; frameIndex ++) {
            frames[frameIndex] = data;
        }
        return frames;
    }

    private int[][][] framesForTileAnimatedDescending(int[][] data) {
        int[][][] frames = new int[Tiles.FRAME_COUNT][][];
        for (int frameIndex = 0; frameIndex < Tiles.FRAME_COUNT; frameIndex ++) {
            frames[frameIndex] = TileEnhancer.animateDescending(data, frameIndex);
        }
        return frames;
    }

    private int[][][] framesForTileWithAnimatedFlag(int[][] data, int firstRow, int lastRow, int firstCol, int lastCol, int flagColour) {
        int[][][] frames = new int[Tiles.FRAME_COUNT][][];
        for (int frameIndex = 0; frameIndex < Tiles.FRAME_COUNT; frameIndex ++) {
            frames[frameIndex] = TileEnhancer.animateFlag(data, frameIndex, firstRow, lastRow, firstCol, lastCol, flagColour);
        }
        return frames;
    }

    private int[][][] framesForRotatingAnimatedTile(int[][] frame1, int[][] frame2, int[][] frame3, int[][] frame4) {
        int[][][] frames = new int[Tiles.FRAME_COUNT][][];
        for (int frameIndex = 0; frameIndex < Tiles.FRAME_COUNT; frameIndex ++) {
             frames[frameIndex] = frameIndex < 2 || (frameIndex >= 8 && frameIndex < 12) ? frame1 :
                     frameIndex < 4 || (frameIndex >= 12 && frameIndex < 14) ? frame2 :
                             frameIndex < 6 || frameIndex >= 14 ? frame3 :
                                     frame4;
        }
        return frames;
    }

    private int[][] eraseNortheast(int[][] tile, int exceptionColour) {
        int[][] modifiedTile = new int[Tiles.TILE_HEIGHT][Tiles.TILE_WIDTH];
        for (int row = 0; row < Tiles.TILE_HEIGHT; row ++) {
            for (int col = 0; col < Tiles.TILE_WIDTH; col ++) {
                if (col > row && tile[row][col] != exceptionColour) {
                    modifiedTile[row][col] = Colours.COLOUR_TRANSPARENT;
                }
                else {
                    modifiedTile[row][col] = tile[row][col];
                }
            }
        }
        return modifiedTile;
    }

    private int[][] eraseSoutheast(int[][] tile, int exceptionColour) {
        int[][] modifiedTile = new int[Tiles.TILE_HEIGHT][Tiles.TILE_WIDTH];
        for (int row = 0; row < Tiles.TILE_HEIGHT; row ++) {
            for (int col = 0; col < Tiles.TILE_WIDTH; col ++) {
                if (col > Tiles.TILE_WIDTH - row && tile[row][col] != exceptionColour) {
                    modifiedTile[row][col] = Colours.COLOUR_TRANSPARENT;
                }
                else {
                    modifiedTile[row][col] = tile[row][col];
                }
            }
        }
        return modifiedTile;
    }

    private int[][] eraseSouthwest(int[][] tile, int exceptionColour) {
        int[][] modifiedTile = new int[Tiles.TILE_HEIGHT][Tiles.TILE_WIDTH];
        for (int row = 0; row < Tiles.TILE_HEIGHT; row ++) {
            for (int col = 0; col < Tiles.TILE_WIDTH; col ++) {
                if (col < row && tile[row][col] != exceptionColour) {
                    modifiedTile[row][col] = Colours.COLOUR_TRANSPARENT;
                }
                else {
                    modifiedTile[row][col] = tile[row][col];
                }
            }
        }
        return modifiedTile;
    }

    private int[][] eraseNorthwest(int[][] tile, int exceptionColour) {
        int[][] modifiedTile = new int[Tiles.TILE_HEIGHT][Tiles.TILE_WIDTH];
        for (int row = 0; row < Tiles.TILE_HEIGHT; row ++) {
            for (int col = 0; col < Tiles.TILE_WIDTH; col ++) {
                if (col < Tiles.TILE_WIDTH - (row + 1) && tile[row][col] != exceptionColour) {
                    modifiedTile[row][col] = Colours.COLOUR_TRANSPARENT;
                }
                else {
                    modifiedTile[row][col] = tile[row][col];
                }
            }
        }
        return modifiedTile;
    }

    private int[][] balloon() {
        // There's no balloon in Ultima V, so use this copy of the Ultima IV tile.
        return new int[][] {
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x0E, 0x0D, 0x0D, 0x0E, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x0E, 0x0E, 0x02, 0x0D, 0x0D, 0x02, 0x0E, 0x0E, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x0E, 0x02, 0x02, 0x0D, 0x0D, 0x0D, 0x0D, 0x02, 0x02, 0x0E, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x0E, 0x02, 0x0D, 0x0D, 0x0D, 0x0D, 0x0D, 0x0D, 0x02, 0x0E, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x0D, 0x0D, 0x0D, 0x02, 0x0D, 0x0D, 0x02, 0x0D, 0x0D, 0x0D, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x0E, 0x0D, 0x0D, 0x02, 0x0D, 0x0D, 0x02, 0x0D, 0x0D, 0x0E, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x0E, 0x02, 0x0D, 0x02, 0x0D, 0x0D, 0x02, 0x0D, 0x02, 0x0E, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x0E, 0x0D, 0x0D, 0x0D, 0x0D, 0x0D, 0x0D, 0x0E, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x0E, 0x02, 0x0D, 0x0D, 0x02, 0x0E, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x04, 0x0E, 0x0D, 0x0D, 0x0E, 0x04, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x04, 0x00, 0x00, 0x00, 0x00, 0x04, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x04, 0x00, 0x06, 0x06, 0x00, 0x04, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x06, 0x00, 0x00, 0x06, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x0A, 0x06, 0x06, 0x06, 0x06, 0x06, 0x06, 0x0A, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x0A, 0x00, 0x06, 0x00, 0x00, 0x06, 0x00, 0x0A, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x0A, 0x06, 0x06, 0x06, 0x06, 0x06, 0x06, 0x0A, 0x00, 0x00, 0x00, 0x00 },
        };
    }
    private int[][] ruins() {
        // There's no ruins in Ultima V, so use this hand-crafted tile that consists of the original
        // Ultima IV ruins tile superimposed over the Ultima V grasslands tile.
        return new int[][] {
                { 0x00, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02 },
                { 0x00, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02 },
                { 0x00, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00 },
                { 0x02, 0x00, 0x00, 0x08, 0x0F, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x08, 0x0F, 0x08, 0x00, 0x00, 0x0F, 0x08, 0x0F, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x08, 0x0F, 0x08, 0x0F, 0x08, 0x0F, 0x08, 0x00, 0x08, 0x0F, 0x08, 0x0F, 0x00, 0x00, 0x00 },
                { 0x00, 0x0F, 0x08, 0x00, 0x00, 0x08, 0x08, 0x00, 0x00, 0x00, 0x08, 0x0F, 0x08, 0x00, 0x00, 0x00 },
                { 0x00, 0x08, 0x00, 0x00, 0x0F, 0x08, 0x0F, 0x00, 0x00, 0x00, 0x0F, 0x08, 0x0F, 0x08, 0x00, 0x00 },
                { 0x00, 0x0F, 0x08, 0x08, 0x08, 0x0F, 0x08, 0x00, 0x00, 0x00, 0x08, 0x08, 0x0F, 0x08, 0x08, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x02, 0x00, 0x00, 0x08, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00, 0x08, 0x0F, 0x00, 0x00 },
                { 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x0F, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00 },
        };
    }

    private int[][] ankh() {
        // There's no giant ankh in Ultima V, so use this hand-crafted tile that consists of the ankh
        // from the Ultima V ankh banner tile removed from the banner.
        return new int[][] {
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x06, 0x0E, 0x0F, 0x0F, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x06, 0x0E, 0x00, 0x00, 0x0E, 0x0F, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x06, 0x0E, 0x00, 0x00, 0x0E, 0x0F, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x06, 0x0E, 0x0E, 0x0F, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x06, 0x06, 0x0E, 0x0E, 0x0E, 0x0E, 0x0E, 0x0F, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x06, 0x0F, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x06, 0x0F, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x06, 0x0F, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x06, 0x0F, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x06, 0x0F, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },
                { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },
        };
    }

    private int[][] colourize(int[][] tile, int brightColour, int colour) {
        // There are no force fields in Ultima V, so use the roof tiles from Ultima V with modified colours.
        int[][] colourizedTile = new int[16][16];
        for (int row = 0; row < 16; row ++) {
            for (int col = 0; col < 16; col ++) {
                int pixel = tile[row][col];
                switch (pixel) {
                    case Colours.COLOUR_BRIGHT_RED:
                        colourizedTile[row][col] = brightColour;
                        break;
                    case Colours.COLOUR_RED:
                        colourizedTile[row][col] = colour;
                        break;
                    default:
                        colourizedTile[row][col] = pixel;
                }
            }
        }
        return colourizedTile;
    }
}
