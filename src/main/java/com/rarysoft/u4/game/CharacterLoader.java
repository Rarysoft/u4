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

import com.rarysoft.u4.game.model.Character;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class CharacterLoader {
    public Character load(InputStream inputStream) throws IOException {
        return new Character(
                twoBytesToInt(inputStream),
                twoBytesToInt(inputStream),
                twoBytesToInt(inputStream),
                twoBytesToInt(inputStream),
                twoBytesToInt(inputStream),
                twoBytesToInt(inputStream),
                twoBytesToInt(inputStream),
                twoBytesToInt(inputStream),
                twoBytesToInt(inputStream),
                twoBytesToInt(inputStream),
                nullTerminatedString(inputStream),
                inputStream.read(),
                inputStream.read(),
                String.valueOf((char) inputStream.read())
        );
    }

    private int twoBytesToInt(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) inputStream.read();
        bytes[1] = (byte) inputStream.read();
        return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    private String nullTerminatedString(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        boolean done = false;
        for (int index = 0; index < 16; index ++) {
            int byteRead = inputStream.read();
            if (byteRead == 0) {
                done = true;
            }
            if (! done) {
                stringBuilder.append((char) byteRead);
            }
        }
        return stringBuilder.toString();
    }
}
