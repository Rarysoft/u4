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

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class CharsetTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void fromStreamWhenStreamIsIncompleteThrowsIOException() throws IOException {
        InputStream stream = new ByteArrayInputStream(new byte[] { 0, 0, 0, 0 });

        exception.expect(IOException.class);
        Charset.fromStream(stream);
    }

    @Test
    public void fromStreamWhenStreamIsCompleteGeneratesCorrectCharset() throws IOException {
        byte[] bytes = new byte[256 * 8 * 4];
        int index = 0;
        for (int character = 0; character < 256; character ++) {
            for (int row = 0; row < 8; row ++) {
                for (int col = 0; col < 4; col ++) {
                    bytes[index] = (byte) character;
                    index ++;
                }
            }
        }
        InputStream stream = new ByteArrayInputStream(bytes);

        Charset result = Charset.fromStream(stream);

        assertThat(result.data()[42][0]).contains(2, 10, 2, 10, 2, 10, 2, 10);
        assertThat(result.data()[42][1]).contains(2, 10, 2, 10, 2, 10, 2, 10);
        assertThat(result.data()[42][2]).contains(2, 10, 2, 10, 2, 10, 2, 10);
        assertThat(result.data()[42][3]).contains(2, 10, 2, 10, 2, 10, 2, 10);
        assertThat(result.data()[42][4]).contains(2, 10, 2, 10, 2, 10, 2, 10);
        assertThat(result.data()[42][5]).contains(2, 10, 2, 10, 2, 10, 2, 10);
        assertThat(result.data()[42][6]).contains(2, 10, 2, 10, 2, 10, 2, 10);
        assertThat(result.data()[42][7]).contains(2, 10, 2, 10, 2, 10, 2, 10);
    }
}
