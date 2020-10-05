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

import com.rarysoft.u4.model.graphics.Charset;
import com.rarysoft.u4.model.graphics.Tiles;

public class Scale {
    private final int multiplier;

    public Scale(int width, int height) {
        int widthScale = determineAppropriateScaleForWidth(width);
        int heightScale = determineAppropriateScaleForHeight(height);
        this.multiplier = Math.min(widthScale, heightScale);
    }

    public int multiplier() {
        return multiplier;
    }

    private int determineAppropriateScaleForWidth(int width) {
        if (width < 21 * Tiles.TILE_WIDTH * 2 + 20 * Charset.CHAR_WIDTH * 2) {
            return 1;
        }
        if (width < 21 * Tiles.TILE_WIDTH * 3 + 20 * Charset.CHAR_WIDTH * 3) {
            return 2;
        }
        return 3;
    }

    private int determineAppropriateScaleForHeight(int height) {
        if (height < 21 * Tiles.TILE_HEIGHT * 2) {
            return 1;
        }
        if (height < 21 * Tiles.TILE_HEIGHT * 3) {
            return 2;
        }
        return 3;
    }
}
