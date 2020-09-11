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
package com.rarysoft.u4.ui;

import com.rarysoft.u4.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements DisplayListener {
    private final Tiles tiles;

    private final int scale;

    private Tile[][] background;

    private int animationCycle;

    public GamePanel(Tiles tiles, int scale) {
        this.tiles = tiles;
        this.scale = scale;
    }

    @Override
    public void backgroundUpdated(Tile[][] background, int animationCycle) {
        this.background = background;
        this.animationCycle = animationCycle;
        this.getParent().repaint();
    }

    @Override
    public void moveBlocked() {
        // TODO: alert player
    }

    @Override
    public void moveSlowed() {
        // TODO: alert player
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        if (background != null) {
            drawBackground(graphics);
        }
    }

    private void drawBackground(Graphics graphics) {
        // Note: the background map provided has a radius that is one larger than what we actually show, which allows
        // us to look at all of the surrounding tiles of each visible tile, so we crop the first and last row and
        // column here
        int centerRow = (background.length - 1) / 2;
        int centerCol = (background[centerRow].length - 1) / 2;
        for (int row = 1; row < background.length - 1; row ++) {
            for (int col = 1; col < background[row].length - 1; col ++) {
                Tile backgroundTile = background[row][col];
                if (! handledAsSpecialCase(graphics, backgroundTile, row, col)) {
                    drawTile(graphics, backgroundTile, col * Tiles.TILE_WIDTH * scale, row * Tiles.TILE_HEIGHT * scale, false);
                }
            }
        }
        drawTile(graphics, Tile.AVATAR, centerCol * Tiles.TILE_WIDTH * scale, centerRow * Tiles.TILE_HEIGHT * scale, true);
    }

    private boolean handledAsSpecialCase(Graphics graphics, Tile tile, int row, int col) {
        if (tile == Tile.LYIN_DOWN) {
            Tile groundTile = guessGroundTile(col, row);
            if (groundTile != null) {
                drawTile(graphics, groundTile, col * Tiles.TILE_WIDTH * scale, row * Tiles.TILE_HEIGHT * scale, false);
                drawTile(graphics, tile, col * Tiles.TILE_WIDTH * scale, row * Tiles.TILE_HEIGHT * scale, true);
                return true;
            }
        }
        if (tile == Tile.WHITE_SW || tile == Tile.WHITE_SE || tile == Tile.WHITE_NW || tile == Tile.WHITE_NE) {
            drawTile(graphics, Tile.SHALLOW_WATER, col * Tiles.TILE_WIDTH * scale, row * Tiles.TILE_HEIGHT * scale, false);
            drawPartialTile(graphics, tile, Colours.COLOUR_BRIGHT_WHITE, col * Tiles.TILE_WIDTH * scale, row * Tiles.TILE_HEIGHT * scale);
            return true;
        }
        if (tile == Tile.ANKH) {
            Tile groundTile = guessGroundTile(col, row);
            if (groundTile != null) {
                drawTile(graphics, groundTile, col * Tiles.TILE_WIDTH * scale, row * Tiles.TILE_HEIGHT * scale, false);
                drawTile(graphics, tile, col * Tiles.TILE_WIDTH * scale, row * Tiles.TILE_HEIGHT * scale, true);
                return true;
            }
        }
        if (tile == Tile.CHEST) {
            Tile groundTile = guessGroundTile(col, row);
            if (groundTile == null) {
                groundTile = Tile.BRICK_FLOOR;
            }
            drawTile(graphics, groundTile, col * Tiles.TILE_WIDTH * scale, row * Tiles.TILE_HEIGHT * scale, false);
            drawTile(graphics, tile, col * Tiles.TILE_WIDTH * scale, row * Tiles.TILE_HEIGHT * scale, true);
            return true;
        }
        return false;
    }

    private void drawTile(Graphics graphics, Tile tile, int x, int y, boolean drawInForeground) {
        for (int row = 0; row < Tiles.TILE_HEIGHT; row ++) {
            for (int col = 0; col < Tiles.TILE_WIDTH; col ++) {
                int code = codeWithOffsetApplied(tile, row, col, drawInForeground);
                if (code != -1) {
                    graphics.setColor(Colours.BY_CODE[code]);
                    graphics.fillRect(x + col * scale, y + row * scale, scale, scale);
                }
            }
        }
    }

    private void drawPartialTile(Graphics graphics, Tile tile, int colour, int x, int y) {
        for (int row = 0; row < Tiles.TILE_HEIGHT; row ++) {
            for (int col = 0; col < Tiles.TILE_WIDTH; col ++) {
                int code = tiles.data()[tile.index()][row][col];
                if (code == colour) {
                    graphics.setColor(Colours.BY_CODE[code]);
                    graphics.fillRect(x + col * scale, y + row * scale, scale, scale);
                }
            }
        }
    }

    private Tile guessGroundTile(int x, int y) {
        java.util.List<Tile> groundTiles = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        for (int row = (y >= 0 ? y - 1 : y); row < (y + 1 < background.length ? y + 1 : y) + 1; row ++) {
            for (int col = (x >= 0 ? x - 1 : x); col < (x + 1 < background[row].length ? x + 1 : x) + 1; col ++) {
                Tile tile = background[row][col];
                if (tile.type() == TileType.GROUND) {
                    int tileIndex = groundTiles.indexOf(tile);
                    if (tileIndex > -1) {
                        counts.set(tileIndex, counts.get(tileIndex) + 1);
                    }
                    else {
                        groundTiles.add(tile);
                        counts.add(1);
                    }
                }
            }
        }
        int highestCount = counts.stream().mapToInt(count -> count).max().orElse(0);
        if (highestCount == 0) {
            return null;
        }
        return groundTiles.get(counts.indexOf(highestCount));
    }

    private int codeWithOffsetApplied(Tile tile, int row, int col, boolean drawInForeground) {
        if (tile == null) {
            return Colours.COLOUR_BLACK;
        }
        int animatedTile;
        int code;
        switch (tile) {
            case DEEP_WATER:
            case MEDIUM_WATER:
            case SHALLOW_WATER:
                return tiles.data()[tile.index()][row - animationCycle < 0 ? Tiles.TILE_HEIGHT + row - animationCycle : row - animationCycle][col];

            case TOWN:
                if (! ((row == 3 || row == 4) && (col == 5 || col == 7))) {
                    return tiles.data()[tile.index()][row][col];
                }
                if ((animationCycle >= 0 && animationCycle < 2) ||
                        (animationCycle >= 4 && animationCycle < 6) ||
                        (animationCycle >= 8 && animationCycle < 12) ||
                        (animationCycle >= 14 && animationCycle < 16)) {
                    return tiles.data()[tile.index()][row][col] == Colours.COLOUR_BRIGHT_RED ? Colours.COLOUR_BLACK : Colours.COLOUR_BRIGHT_RED;
                }
                return tiles.data()[tile.index()][row][col];

            case CASTLE:
                if (! ((row == 1 || row == 2) && (col == 9 || col == 11))) {
                    return tiles.data()[tile.index()][row][col];
                }
                if ((animationCycle >= 0 && animationCycle < 2) ||
                        (animationCycle >= 4 && animationCycle < 6) ||
                        (animationCycle >= 8 && animationCycle < 12) ||
                        (animationCycle >= 14 && animationCycle < 16)) {
                    return tiles.data()[tile.index()][row][col] == Colours.COLOUR_BRIGHT_RED ? Colours.COLOUR_BLACK : Colours.COLOUR_BRIGHT_RED;
                }
                return tiles.data()[tile.index()][row][col];

            case LORD_BRITISHS_CASTLE_ENTRANCE:
                if (! ((row == 1 || row == 2) && (col == 5 || col == 7))) {
                    return tiles.data()[tile.index()][row][col];
                }
                if ((animationCycle >= 0 && animationCycle < 2) ||
                        (animationCycle >= 4 && animationCycle < 6) ||
                        (animationCycle >= 8 && animationCycle < 12) ||
                        (animationCycle >= 14 && animationCycle < 16)) {
                    return tiles.data()[tile.index()][row][col] == Colours.COLOUR_BRIGHT_RED ? Colours.COLOUR_BLACK : Colours.COLOUR_BRIGHT_RED;
                }
                return tiles.data()[tile.index()][row][col];

            case LYIN_DOWN:
            case ANKH:
                code = tiles.data()[tile.index()][row][col];
                if (code == Colours.COLOUR_BLACK && drawInForeground) {
                    if (! isAdjacentToForeground(tiles.data()[tile.index()], row, col)) {
                        return -1;
                    }
                }
                return code;

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
                animatedTile = (animationCycle >= 0 && animationCycle < 2) ||
                        (animationCycle >= 4 && animationCycle < 6) ||
                        (animationCycle >= 8 && animationCycle < 12) ||
                        (animationCycle >= 14 && animationCycle < 16) ? tile.index() : tile.index() + 1;
                code = tiles.data()[animatedTile][row][col];
                if (code == Colours.COLOUR_BLACK && drawInForeground) {
                    if (! isAdjacentToForeground(tiles.data()[animatedTile], row, col)) {
                        return -1;
                    }
                }
                return code;

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
                animatedTile = (animationCycle >= 0 && animationCycle < 2) ||
                        (animationCycle >= 4 && animationCycle < 6) ||
                        (animationCycle >= 8 && animationCycle < 12) ||
                        (animationCycle >= 14 && animationCycle < 16) ? tile.index() : tile.index() - 1;
                code = tiles.data()[animatedTile][row][col];
                if (code == Colours.COLOUR_BLACK && drawInForeground) {
                    if (! isAdjacentToForeground(tiles.data()[animatedTile], row, col)) {
                        return -1;
                    }
                }
                return code;

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
                animatedTile = (animationCycle >= 0 && animationCycle < 2) || (animationCycle >= 8 && animationCycle < 12) ? tile.index() :
                        (animationCycle >= 2 && animationCycle < 4) || (animationCycle >= 12 && animationCycle < 14) ? tile.index() + 1 :
                                (animationCycle >= 4 && animationCycle < 6) || (animationCycle >= 14 && animationCycle < 16) ? tile.index() + 2 :
                                        tile.index() + 3;
                code = tiles.data()[animatedTile][row][col];
                if (code == Colours.COLOUR_BLACK && drawInForeground) {
                    if (! isAdjacentToForeground(tiles.data()[animatedTile], row, col)) {
                        return -1;
                    }
                }
                return code;

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
                animatedTile = (animationCycle >= 0 && animationCycle < 2) || (animationCycle >= 8 && animationCycle < 12) ? tile.index() :
                        (animationCycle >= 2 && animationCycle < 4) || (animationCycle >= 12 && animationCycle < 14) ? tile.index() + 1 :
                                (animationCycle >= 4 && animationCycle < 6) || (animationCycle >= 14 && animationCycle < 16) ? tile.index() + 2 :
                                        tile.index() - 1;
                code = tiles.data()[animatedTile][row][col];
                if (code == Colours.COLOUR_BLACK && drawInForeground) {
                    if (! isAdjacentToForeground(tiles.data()[animatedTile], row, col)) {
                        return -1;
                    }
                }
                return code;

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
                animatedTile = (animationCycle >= 0 && animationCycle < 2) || (animationCycle >= 8 && animationCycle < 12) ? tile.index() :
                        (animationCycle >= 2 && animationCycle < 4) || (animationCycle >= 12 && animationCycle < 14) ? tile.index() + 1 :
                                (animationCycle >= 4 && animationCycle < 6) || (animationCycle >= 14 && animationCycle < 16) ? tile.index() - 2 :
                                        tile.index() - 1;
                code = tiles.data()[animatedTile][row][col];
                if (code == Colours.COLOUR_BLACK && drawInForeground) {
                    if (! isAdjacentToForeground(tiles.data()[animatedTile], row, col)) {
                        return -1;
                    }
                }
                return code;

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
                animatedTile = (animationCycle >= 0 && animationCycle < 2) || (animationCycle >= 8 && animationCycle < 12) ? tile.index() :
                        (animationCycle >= 2 && animationCycle < 4) || (animationCycle >= 12 && animationCycle < 14) ? tile.index() - 3 :
                                (animationCycle >= 4 && animationCycle < 6) || (animationCycle >= 14 && animationCycle < 16) ? tile.index() - 2 :
                                        tile.index() - 1;
                code = tiles.data()[animatedTile][row][col];
                if (code == Colours.COLOUR_BLACK && drawInForeground) {
                    if (! isAdjacentToForeground(tiles.data()[animatedTile], row, col)) {
                        return -1;
                    }
                }
                return code;

            default:
                return tiles.data()[tile.index()][row][col];
        }
    }

    private boolean isAdjacentToForeground(int[][] tile, int row, int col) {
        return (row > 0 && col > 0 && tile[row - 1][col - 1] != Colours.COLOUR_BLACK) ||
                (row > 0 && tile[row - 1][col] != Colours.COLOUR_BLACK) ||
                (row > 0 && col + 1 < Tiles.TILE_WIDTH && tile[row - 1][col + 1] != Colours.COLOUR_BLACK) ||
                (col > 0 && tile[row][col - 1] != Colours.COLOUR_BLACK) ||
                (col + 1 < Tiles.TILE_WIDTH && tile[row][col + 1] != Colours.COLOUR_BLACK) ||
                (row + 1 < Tiles.TILE_HEIGHT && col > 0 && tile[row + 1][col - 1] != Colours.COLOUR_BLACK) ||
                (row + 1 < Tiles.TILE_HEIGHT && tile[row + 1][col] != Colours.COLOUR_BLACK) ||
                (row + 1 < Tiles.TILE_HEIGHT && col + 1 < Tiles.TILE_WIDTH && tile[row + 1][col + 1] != Colours.COLOUR_BLACK);
    }
}
