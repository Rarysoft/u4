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

    private int[][] background;

    private int animationCycle;

    public GamePanel(Tiles tiles, int scale) {
        this.tiles = tiles;
        this.scale = scale;
    }

    @Override
    public void backgroundUpdated(int[][] background, int animationCycle) {
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
                int backgroundTile = background[row][col];
                if (! handledAsSpecialCase(graphics, backgroundTile, row, col)) {
                    drawTile(graphics, backgroundTile, col * Tiles.TILE_WIDTH * scale, row * Tiles.TILE_HEIGHT * scale, false);
                }
            }
        }
        drawTile(graphics, Tile.AVATAR.index(), centerCol * Tiles.TILE_WIDTH * scale, centerRow * Tiles.TILE_HEIGHT * scale, true);
    }

    private boolean handledAsSpecialCase(Graphics graphics, int tile, int row, int col) {
        if (tile == Tile.LYIN_DOWN.index()) {
            int groundTile = guessGroundTile(col, row);
            if (groundTile > -1) {
                drawTile(graphics, groundTile, col * Tiles.TILE_WIDTH * scale, row * Tiles.TILE_HEIGHT * scale, false);
                drawTile(graphics, tile, col * Tiles.TILE_WIDTH * scale, row * Tiles.TILE_HEIGHT * scale, true);
                return true;
            }
        }
        if (tile == Tile.WHITE_SW.index() || tile == Tile.WHITE_SE.index() || tile == Tile.WHITE_NW.index() || tile == Tile.WHITE_NE.index()) {
            drawTile(graphics, Tile.SHALLOW_WATER.index(), col * Tiles.TILE_WIDTH * scale, row * Tiles.TILE_HEIGHT * scale, false);
            drawPartialTile(graphics, tile, Colours.COLOUR_BRIGHT_WHITE, col * Tiles.TILE_WIDTH * scale, row * Tiles.TILE_HEIGHT * scale);
            return true;
        }
        if (tile == Tile.ANKH.index()) {
            int groundTile = guessGroundTile(col, row);
            if (groundTile > -1) {
                drawTile(graphics, groundTile, col * Tiles.TILE_WIDTH * scale, row * Tiles.TILE_HEIGHT * scale, false);
                drawTile(graphics, tile, col * Tiles.TILE_WIDTH * scale, row * Tiles.TILE_HEIGHT * scale, true);
                return true;
            }
        }
        if (tile == Tile.CHEST.index()) {
            int groundTile = guessGroundTile(col, row);
            if (groundTile == -1) {
                groundTile = Tile.BRICK_FLOOR.index();
            }
            drawTile(graphics, groundTile, col * Tiles.TILE_WIDTH * scale, row * Tiles.TILE_HEIGHT * scale, false);
            drawTile(graphics, tile, col * Tiles.TILE_WIDTH * scale, row * Tiles.TILE_HEIGHT * scale, true);
            return true;
        }
        return false;
    }

    private void drawTile(Graphics graphics, int tile, int x, int y, boolean drawInForeground) {
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

    private void drawPartialTile(Graphics graphics, int tile, int colour, int x, int y) {
        for (int row = 0; row < Tiles.TILE_HEIGHT; row ++) {
            for (int col = 0; col < Tiles.TILE_WIDTH; col ++) {
                int code = tiles.data()[tile][row][col];
                if (code == colour) {
                    graphics.setColor(Colours.BY_CODE[code]);
                    graphics.fillRect(x + col * scale, y + row * scale, scale, scale);
                }
            }
        }
    }

    private int guessGroundTile(int x, int y) {
        java.util.List<Integer> groundTiles = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        for (int row = (y >= 0 ? y - 1 : y); row < (y + 1 < background.length ? y + 1 : y) + 1; row ++) {
            for (int col = (x >= 0 ? x - 1 : x); col < (x + 1 < background[row].length ? x + 1 : x) + 1; col ++) {
                int tile = background[row][col];
                if (Tile.forIndex(tile).type() == TileType.GROUND) {
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
            return -1;
        }
        return groundTiles.get(counts.indexOf(highestCount));
    }

    private int codeWithOffsetApplied(int tile, int row, int col, boolean drawInForeground) {
        int animatedTile;
        int code;
        switch (tile) {
            case 0:
            case 1:
            case 2:
                return tiles.data()[tile][row - animationCycle < 0 ? Tiles.TILE_HEIGHT + row - animationCycle : row - animationCycle][col];

            case 10:
                if (! ((row == 3 || row == 4) && (col == 5 || col == 7))) {
                    return tiles.data()[tile][row][col];
                }
                if ((animationCycle >= 0 && animationCycle < 2) ||
                        (animationCycle >= 4 && animationCycle < 6) ||
                        (animationCycle >= 8 && animationCycle < 12) ||
                        (animationCycle >= 14 && animationCycle < 16)) {
                    return tiles.data()[tile][row][col] == Colours.COLOUR_BRIGHT_RED ? Colours.COLOUR_BLACK : Colours.COLOUR_BRIGHT_RED;
                }
                return tiles.data()[tile][row][col];

            case 11:
                if (! ((row == 1 || row == 2) && (col == 9 || col == 11))) {
                    return tiles.data()[tile][row][col];
                }
                if ((animationCycle >= 0 && animationCycle < 2) ||
                        (animationCycle >= 4 && animationCycle < 6) ||
                        (animationCycle >= 8 && animationCycle < 12) ||
                        (animationCycle >= 14 && animationCycle < 16)) {
                    return tiles.data()[tile][row][col] == Colours.COLOUR_BRIGHT_RED ? Colours.COLOUR_BLACK : Colours.COLOUR_BRIGHT_RED;
                }
                return tiles.data()[tile][row][col];

            case 14:
                if (! ((row == 1 || row == 2) && (col == 5 || col == 7))) {
                    return tiles.data()[tile][row][col];
                }
                if ((animationCycle >= 0 && animationCycle < 2) ||
                        (animationCycle >= 4 && animationCycle < 6) ||
                        (animationCycle >= 8 && animationCycle < 12) ||
                        (animationCycle >= 14 && animationCycle < 16)) {
                    return tiles.data()[tile][row][col] == Colours.COLOUR_BRIGHT_RED ? Colours.COLOUR_BLACK : Colours.COLOUR_BRIGHT_RED;
                }
                return tiles.data()[tile][row][col];

            case 56:
            case 61:
                code = tiles.data()[tile][row][col];
                if (code == Colours.COLOUR_BLACK && drawInForeground) {
                    if (! isAdjacentToForeground(tiles.data()[tile], row, col)) {
                        return -1;
                    }
                }
                return code;

            case 32:
            case 34:
            case 36:
            case 38:
            case 40:
            case 42:
            case 44:
            case 46:
            case 80:
            case 82:
            case 84:
            case 86:
            case 88:
            case 90:
            case 92:
            case 94:
            case 132:
            case 134:
            case 136:
            case 138:
            case 140:
            case 142:
                animatedTile = (animationCycle >= 0 && animationCycle < 2) ||
                        (animationCycle >= 4 && animationCycle < 6) ||
                        (animationCycle >= 8 && animationCycle < 12) ||
                        (animationCycle >= 14 && animationCycle < 16) ? tile : tile + 1;
                code = tiles.data()[animatedTile][row][col];
                if (code == Colours.COLOUR_BLACK && drawInForeground) {
                    if (! isAdjacentToForeground(tiles.data()[animatedTile], row, col)) {
                        return -1;
                    }
                }
                return code;

            case 33:
            case 35:
            case 37:
            case 39:
            case 41:
            case 43:
            case 45:
            case 47:
            case 81:
            case 83:
            case 85:
            case 87:
            case 89:
            case 91:
            case 93:
            case 95:
            case 133:
            case 135:
            case 137:
            case 139:
            case 141:
            case 143:
                animatedTile = (animationCycle >= 0 && animationCycle < 2) ||
                        (animationCycle >= 4 && animationCycle < 6) ||
                        (animationCycle >= 8 && animationCycle < 12) ||
                        (animationCycle >= 14 && animationCycle < 16) ? tile : tile - 1;
                code = tiles.data()[animatedTile][row][col];
                if (code == Colours.COLOUR_BLACK && drawInForeground) {
                    if (! isAdjacentToForeground(tiles.data()[animatedTile], row, col)) {
                        return -1;
                    }
                }
                return code;

            case 144:
            case 148:
            case 152:
            case 156:
            case 160:
            case 164:
            case 168:
            case 172:
            case 176:
            case 180:
            case 184:
            case 188:
            case 192:
            case 196:
            case 200:
            case 204:
            case 208:
            case 212:
            case 216:
            case 220:
            case 224:
            case 228:
            case 232:
            case 236:
            case 240:
            case 244:
            case 248:
            case 252:
                animatedTile = (animationCycle >= 0 && animationCycle < 2) || (animationCycle >= 8 && animationCycle < 12) ? tile :
                        (animationCycle >= 2 && animationCycle < 4) || (animationCycle >= 12 && animationCycle < 14) ? tile + 1 :
                                (animationCycle >= 4 && animationCycle < 6) || (animationCycle >= 14 && animationCycle < 16) ? tile + 2 :
                                        tile + 3;
                code = tiles.data()[animatedTile][row][col];
                if (code == Colours.COLOUR_BLACK && drawInForeground) {
                    if (! isAdjacentToForeground(tiles.data()[animatedTile], row, col)) {
                        return -1;
                    }
                }
                return code;

            case 145:
            case 149:
            case 153:
            case 157:
            case 161:
            case 165:
            case 169:
            case 173:
            case 177:
            case 181:
            case 185:
            case 189:
            case 193:
            case 197:
            case 201:
            case 205:
            case 209:
            case 213:
            case 217:
            case 221:
            case 225:
            case 229:
            case 233:
            case 237:
            case 241:
            case 245:
            case 249:
            case 253:
                animatedTile = (animationCycle >= 0 && animationCycle < 2) || (animationCycle >= 8 && animationCycle < 12) ? tile :
                        (animationCycle >= 2 && animationCycle < 4) || (animationCycle >= 12 && animationCycle < 14) ? tile + 1 :
                                (animationCycle >= 4 && animationCycle < 6) || (animationCycle >= 14 && animationCycle < 16) ? tile + 2 :
                                        tile - 1;
                code = tiles.data()[animatedTile][row][col];
                if (code == Colours.COLOUR_BLACK && drawInForeground) {
                    if (! isAdjacentToForeground(tiles.data()[animatedTile], row, col)) {
                        return -1;
                    }
                }
                return code;

            case 146:
            case 150:
            case 154:
            case 158:
            case 162:
            case 166:
            case 170:
            case 174:
            case 178:
            case 182:
            case 186:
            case 190:
            case 194:
            case 198:
            case 202:
            case 206:
            case 210:
            case 214:
            case 218:
            case 222:
            case 226:
            case 230:
            case 234:
            case 238:
            case 242:
            case 246:
            case 250:
            case 254:
                animatedTile = (animationCycle >= 0 && animationCycle < 2) || (animationCycle >= 8 && animationCycle < 12) ? tile :
                        (animationCycle >= 2 && animationCycle < 4) || (animationCycle >= 12 && animationCycle < 14) ? tile + 1 :
                                (animationCycle >= 4 && animationCycle < 6) || (animationCycle >= 14 && animationCycle < 16) ? tile - 2 :
                                        tile - 1;
                code = tiles.data()[animatedTile][row][col];
                if (code == Colours.COLOUR_BLACK && drawInForeground) {
                    if (! isAdjacentToForeground(tiles.data()[animatedTile], row, col)) {
                        return -1;
                    }
                }
                return code;

            case 147:
            case 151:
            case 155:
            case 159:
            case 163:
            case 167:
            case 171:
            case 175:
            case 179:
            case 183:
            case 187:
            case 191:
            case 195:
            case 199:
            case 203:
            case 207:
            case 211:
            case 215:
            case 219:
            case 223:
            case 227:
            case 231:
            case 235:
            case 239:
            case 243:
            case 247:
            case 251:
            case 255:
                animatedTile = (animationCycle >= 0 && animationCycle < 2) || (animationCycle >= 8 && animationCycle < 12) ? tile :
                        (animationCycle >= 2 && animationCycle < 4) || (animationCycle >= 12 && animationCycle < 14) ? tile - 3 :
                                (animationCycle >= 4 && animationCycle < 6) || (animationCycle >= 14 && animationCycle < 16) ? tile - 2 :
                                        tile - 1;
                code = tiles.data()[animatedTile][row][col];
                if (code == Colours.COLOUR_BLACK && drawInForeground) {
                    if (! isAdjacentToForeground(tiles.data()[animatedTile], row, col)) {
                        return -1;
                    }
                }
                return code;

            default:
                return tiles.data()[tile][row][col];
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
