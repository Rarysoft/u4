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

import com.rarysoft.u4.game.RenderedTile;
import com.rarysoft.u4.game.Tile;
import com.rarysoft.u4.game.Tiles;
import com.rarysoft.u4.ui.graphics.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class GameViewRenderer {
    private static final int BACKGROUND_OFFSET_X = Charset.CHAR_WIDTH;
    private static final int BACKGROUND_OFFSET_Y = Charset.CHAR_HEIGHT;

    private static final int STATS_AREA_OFFSET_X = Charset.CHAR_WIDTH + 19 * Tiles.TILE_WIDTH + Charset.CHAR_WIDTH * 2;
    private static final int STATS_AREA_OFFSET_Y = Charset.CHAR_HEIGHT;

    private static final int TEXT_AREA_OFFSET_X = Charset.CHAR_WIDTH + 19 * Tiles.TILE_WIDTH + Charset.CHAR_WIDTH;
    private static final int TEXT_AREA_OFFSET_Y = Charset.CHAR_HEIGHT * 19;

    private final Tiles tiles;
    private final Charset charset;
    private final ExtendedCharset extendedCharset;

    private int phaseOfTrammel;
    private int phaseOfFelucca;
    private int windDirection;

    private RenderedTile[][] background;
    private int animationCycle;

    private List<String> statsLines = new ArrayList<>();

    private List<String> textLines = new ArrayList<>();
    private boolean allowInput = false;
    private String inputLine = "";

    public GameViewRenderer(Tiles tiles, Charset charset, ExtendedCharset extendedCharset) {
        this.tiles = tiles;
        this.charset = charset;
        this.extendedCharset = extendedCharset;
    }

    public void setPhaseOfTrammel(int phaseOfTrammel) {
        this.phaseOfTrammel = phaseOfTrammel;
    }

    public void setPhaseOfFelucca(int phaseOfFelucca) {
        this.phaseOfFelucca = phaseOfFelucca;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    public void setBackground(RenderedTile[][] background) {
        this.background = background;
    }

    public void setAnimationCycle(int animationCycle) {
        this.animationCycle = animationCycle;
    }

    public void setTextLines(List<String> textLines) {
        this.textLines = textLines;
    }

    public void setAllowInput(boolean allowInput) {
        this.allowInput = allowInput;
    }

    public void setInputLine(String inputLine) {
        this.inputLine = inputLine;
    }

    public void setStatistics(List<String> statsLines) {
        this.statsLines = statsLines;
    }

    public void drawGameView(Graphics graphics, int windowWidth, int windowHeight) {
        AffineTransform originalTransform = scaleToWindowSize(graphics, windowWidth, windowHeight);
        drawBorder(graphics);
        drawBackground(graphics);
        drawStatsArea(graphics);
        drawTextArea(graphics);
        restoreOriginalTransform(graphics, originalTransform);
    }

    private AffineTransform scaleToWindowSize(Graphics graphics, int windowWidth, int windowHeight) {
        double xScale = windowWidth / 544.0;
        double yScale = windowHeight / 320.0;
        Graphics2D graphics2D = (Graphics2D) graphics;
        AffineTransform originalTransform = graphics2D.getTransform();
        AffineTransform transform = graphics2D.getTransform();
        transform.scale(xScale, yScale);
        graphics2D.setTransform(transform);
        return originalTransform;
    }

    private void restoreOriginalTransform(Graphics graphics, AffineTransform originalTransform) {
        ((Graphics2D) graphics).setTransform(originalTransform);
    }

    private void drawBorder(Graphics graphics) {
        drawCharacter(graphics, extendedCharset.borderCornerNorthwest(), 0, 0, 0, 0);
        drawCharacter(graphics, extendedCharset.borderCornerSouthwest(), 39, 0, 0, 0);
        for (int index = 1; index < 39; index ++) {
            if (index < 18 || index > 21) {
                drawCharacter(graphics, extendedCharset.borderHorizontal(), 0, index, 0, 0);
            }
            else {
                switch (index) {
                    case 18:
                        drawCharacter(graphics, charset.data()[16], 0, index, 0, 0);
                        break;
                    case 19:
                        drawCharacter(graphics, charset.data()[20 + phaseOfTrammel], 0, index, 0, 0);
                        break;
                    case 20:
                        drawCharacter(graphics, charset.data()[20 + phaseOfFelucca], 0, index, 0, 0);
                        break;
                    case 21:
                        drawCharacter(graphics, charset.data()[17], 0, index, 0, 0);
                        break;
                }
            }
            if (index < 12 || index > 27) {
                drawCharacter(graphics, extendedCharset.borderHorizontal(), 39, index, 0, 0);
            }
            else {
                switch (index) {
                    case 12:
                        drawCharacter(graphics, charset.data()[16], 39, index, 0, 0);
                        break;
                    case 13:
                        drawCharacter(graphics, charset.data()[87], 39, index, 0, 0);
                        break;
                    case 14:
                        drawCharacter(graphics, charset.data()[105], 39, index, 0, 0);
                        break;
                    case 15:
                        drawCharacter(graphics, charset.data()[110], 39, index, 0, 0);
                        break;
                    case 16:
                        drawCharacter(graphics, charset.data()[100], 39, index, 0, 0);
                        break;
                    case 17:
                        drawCharacter(graphics, charset.data()[58], 39, index, 0, 0);
                        break;
                    case 27:
                        drawCharacter(graphics, charset.data()[17], 39, index, 0, 0);
                        break;
                }
            }
            drawCharacter(graphics, extendedCharset.borderVertical(), index, 0, 0, 0);
            drawCharacter(graphics, extendedCharset.borderVertical(), index, 39, 0, 0);
        }
        drawCharacter(graphics, extendedCharset.borderCornerNortheast(), 0, 39, 0, 0);
        drawCharacter(graphics, extendedCharset.borderCornerSoutheast(), 39, 39, 0, 0);

        drawCharacter(graphics, extendedCharset.borderCornerNorthwest(), 0, 40, 0, 0);
        drawCharacter(graphics, extendedCharset.borderCornerSouthwest(), 18, 40, 0, 0);
        for (int row = 1; row < 18; row ++) {
            drawCharacter(graphics, extendedCharset.borderVertical(), row, 40, 0, 0);
            drawCharacter(graphics, extendedCharset.borderVertical(), row, 67, 0, 0);
        }
        for (int col = 41; col < 67; col ++) {
            drawCharacter(graphics, extendedCharset.borderHorizontal(), 0, col, 0, 0);
            drawCharacter(graphics, extendedCharset.borderHorizontal(), 18, col, 0, 0);
        }
        drawCharacter(graphics, extendedCharset.borderCornerNortheast(), 0, 67, 0, 0);
        drawCharacter(graphics, extendedCharset.borderCornerSoutheast(), 18, 67, 0, 0);
    }

    private void drawBackground(Graphics graphics) {
        if (background == null) {
            return;
        }
        for (int row = 0; row < background.length; row ++) {
            for (int col = 0; col < background[row].length; col ++) {
                RenderedTile renderedTile = background[row][col];
                if (renderedTile.render()) {
                    for (Tile tile : renderedTile.baseTiles()) {
                        drawTile(graphics, tile, row, col);
                    }
                    int tileRow = row;
                    int tileCol = col;
                    renderedTile.transientTile().ifPresent(tile -> drawTile(graphics, tile, tileRow, tileCol));
                }
            }
        }
    }

    private void drawTile(Graphics graphics, Tile tile, int row, int col) {
        for (int pixelRow = 0; pixelRow < Tiles.TILE_HEIGHT; pixelRow ++) {
            for (int pixelCol = 0; pixelCol < Tiles.TILE_WIDTH; pixelCol ++) {
                int code = tiles.data()[tile.index()][animationCycle][pixelRow][pixelCol];
                if (code != Colours.COLOUR_TRANSPARENT) {
                    graphics.setColor(Colours.BY_CODE[code]);
                    graphics.fillRect(BACKGROUND_OFFSET_X + col * Tiles.TILE_WIDTH + pixelCol,
                            BACKGROUND_OFFSET_Y + row * Tiles.TILE_HEIGHT + pixelRow, 1, 1);
                }
            }
        }
    }

    private void drawStatsArea(Graphics graphics) {
        for (int row = 0; row < statsLines.size(); row ++) {
            String textLine = statsLines.get(row);
            for (int col = 0; col < textLine.length(); col ++) {
                int charCode = textLine.charAt(col);
                int[][] character = charset.data()[charCode];
                drawCharacter(graphics, character, row, col, STATS_AREA_OFFSET_X, STATS_AREA_OFFSET_Y);
            }
        }
    }

    private void drawTextArea(Graphics graphics) {
        for (int row = 0; row < textLines.size(); row ++) {
            String textLine = textLines.get(row);
            for (int col = 0; col < textLine.length(); col ++) {
                int charCode = textLine.charAt(col);
                int[][] character = charset.data()[charCode];
                drawCharacter(graphics, character, row, col, TEXT_AREA_OFFSET_X, TEXT_AREA_OFFSET_Y);
            }
        }
        int inputRow = 19;
        for (int index = 0; index < inputLine.length(); index ++) {
            drawCharacter(graphics, charset.data()[inputLine.charAt(index)], inputRow, index, TEXT_AREA_OFFSET_X, TEXT_AREA_OFFSET_Y);
        }
        if (allowInput) {
            int cursorCol = inputLine.length();
            drawCharacter(graphics, charset.data()[31 - animationCycle % 4], inputRow, cursorCol, TEXT_AREA_OFFSET_X, TEXT_AREA_OFFSET_Y);
        }
    }

    private void drawCharacter(Graphics graphics, int[][] character, int row, int col, int xOffset, int yOffset) {
        int x = col * Charset.CHAR_WIDTH;
        int y = row * Charset.CHAR_HEIGHT;
            y += yOffset;
            x += xOffset;
        for (int pixelY = 0; pixelY < Charset.CHAR_HEIGHT; pixelY ++) {
            for (int pixelX = 0; pixelX < Charset.CHAR_WIDTH; pixelX ++) {
                int code = character[pixelY][pixelX];
                graphics.setColor(Colours.BY_CODE[code]);
                graphics.fillRect(x + pixelX, y + pixelY, 1, 1);
            }
        }
    }
}
