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

public class Door {
    private final int row;
    private final int col;

    private boolean closed;
    private boolean locked;

    private int closeTurnCounter;

    public Door(int row, int col, boolean closed, boolean locked) {
        this.row = row;
        this.col = col;
        this.closed = closed;
        this.locked = locked;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isClosed() {
        return closed;
    }

    public boolean isLocked() {
        return locked;
    }

    public void open(int turns) {
        closed = false;
        closeTurnCounter = turns;
    }

    public void unlock() {
        locked = false;
    }

    public void turnCompleted() {
        if (closeTurnCounter > 0) {
            closeTurnCounter --;
            if (closeTurnCounter == 0) {
                closed = true;
            }
        }
    }
}
