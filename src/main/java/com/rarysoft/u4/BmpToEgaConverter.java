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
package com.rarysoft.u4;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class BmpToEgaConverter {
    public static void main(String[] args) {
        try {
            InputStream in = Launcher.class.getResourceAsStream("/data/u5/tiles.bmp");
            OutputStream out = new FileOutputStream("src/main/resources/data/tiles.ega");
            in.skip(54);
            int[] pixels = new int[131072];
            for (int pixel = 0; pixel < 131072; pixel ++) {
                int b = in.read();
                int g = in.read();
                int r = in.read();
                int code = -1;
                if (r == 0x00 && g == 0x00 && b == 0x00) {
                    code = 0;
                }
                else if (r == 0x00 && g == 0xAA && b == 0xAA) {
                    code = 1;
                }
                else if (r == 0x00 && g == 0xAA && b == 0x00) {
                    code = 2;
                }
                else if (r == 0x00 && g == 0x00 && b == 0xAA) {
                    code = 3;
                }
                else if (r == 0xAA && g == 0x00 && b == 0x00) {
                    code = 4;
                }
                else if (r == 0xAA && g == 0x00 && b == 0xAA) {
                    code = 5;
                }
                else if (r == 0xAA && g == 0x55 && b == 0x00) {
                    code = 6;
                }
                else if (r == 0xAA && g == 0xAA && b == 0xAA) {
                    code = 7;
                }
                else if (r == 0x55 && g == 0x55 && b == 0x55) {
                    code = 8;
                }
                else if (r == 0x55 && g == 0x55 && b == 0xFF) {
                    code = 9;
                }
                else if (r == 0x55 && g == 0xFF && b == 0x55) {
                    code = 10;
                }
                else if (r == 0x55 && g == 0xFF && b == 0xFF) {
                    code = 11;
                }
                else if (r == 0xFF && g == 0x55 && b == 0x55) {
                    code = 12;
                }
                else if (r == 0xFF && g == 0x55 && b == 0xFF) {
                    code = 13;
                }
                else if (r == 0xFF && g == 0xFF && b == 0x55) {
                    code = 14;
                }
                else if (r == 0xFF && g == 0xFF && b == 0xFF) {
                    code = 15;
                }
                pixels[pixel] = code;
            }
            int[][][] tiles = new int[512][16][16];
            int index = 0;
            for (int tileRow = 16 - 1; tileRow >= 0; tileRow --) {
                for (int row = 16 - 1; row >= 0; row --) {
                    for (int tileInRow = 0; tileInRow < 32; tileInRow ++) {
                        int tile = tileRow * 32 + tileInRow;
                        for (int col = 0; col < 16; col ++) {
                            tiles[tile][row][col] = pixels[index];
                            index ++;
                        }
                    }
                }
            }
            for (int tile = 0; tile < 512; tile ++) {
                for (int row = 0; row < 16; row ++) {
                    for (int col = 0; col < 16; col += 2) {
                        int leftPixel = tiles[tile][row][col];
                        int rightPixel = tiles[tile][row][col + 1];
                        int value = (leftPixel << 4 & 0xFF) + rightPixel;
                        out.write(value);
                    }
                }
            }
            out.close();
            in.close();
        }
        catch (Throwable t) {
            throw new RuntimeException();
        }
    }
}
