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

import com.rarysoft.u4.model.DisplayListener;
import com.rarysoft.u4.model.RenderedTile;

import java.util.List;

public class BorderListener implements DisplayListener {
    private final BorderProvider borderProvider;

    public BorderListener(BorderProvider borderProvider) {
        this.borderProvider = borderProvider;
    }

    @Override
    public void initialize() {
        borderProvider.drawBorder();
    }

    @Override
    public void backgroundUpdated(RenderedTile[][] background, int animationCycle) {
        // nothing to update here
    }

    @Override
    public void actionCompleted(String message) {
        // nothing to update here
    }

    @Override
    public void responseRequested(List<String> messages) {
        // nothing to update here
    }

    @Override
    public void inputReceived(String input) {
        // nothing to update here
    }
}