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

import com.rarysoft.u4.model.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter {
    private final Game game;

    public KeyboardListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_NUMPAD8:
                game.onNorthPressed();
                break;
            case KeyEvent.VK_NUMPAD9:
                game.onNortheastPressed();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_NUMPAD6:
                game.onEastPressed();
                break;
            case KeyEvent.VK_NUMPAD3:
                game.onSoutheastPressed();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_NUMPAD2:
                game.onSouthPressed();
                break;
            case KeyEvent.VK_NUMPAD1:
                game.onSouthwestPressed();
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_NUMPAD4:
                game.onWestPressed();
                break;
            case KeyEvent.VK_NUMPAD7:
                game.onNorthwestPressed();
                break;
            case KeyEvent.VK_BACK_SPACE:
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_0:
            case KeyEvent.VK_1:
            case KeyEvent.VK_2:
            case KeyEvent.VK_3:
            case KeyEvent.VK_4:
            case KeyEvent.VK_5:
            case KeyEvent.VK_6:
            case KeyEvent.VK_7:
            case KeyEvent.VK_8:
            case KeyEvent.VK_9:
            case KeyEvent.VK_A:
            case KeyEvent.VK_B:
            case KeyEvent.VK_C:
            case KeyEvent.VK_D:
            case KeyEvent.VK_E:
            case KeyEvent.VK_F:
            case KeyEvent.VK_G:
            case KeyEvent.VK_H:
            case KeyEvent.VK_I:
            case KeyEvent.VK_J:
            case KeyEvent.VK_K:
            case KeyEvent.VK_L:
            case KeyEvent.VK_M:
            case KeyEvent.VK_N:
            case KeyEvent.VK_O:
            case KeyEvent.VK_P:
            case KeyEvent.VK_Q:
            case KeyEvent.VK_R:
            case KeyEvent.VK_S:
            case KeyEvent.VK_T:
            case KeyEvent.VK_U:
            case KeyEvent.VK_V:
            case KeyEvent.VK_W:
            case KeyEvent.VK_X:
            case KeyEvent.VK_Y:
            case KeyEvent.VK_Z:
                game.onUserInput(event.getKeyChar());
                break;
            default:
                break;
        }
    }
}
