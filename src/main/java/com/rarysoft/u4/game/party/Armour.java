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
package com.rarysoft.u4.game.party;

public enum Armour {
    SKIN("Skin"),
    CLOTH("Cloth"),
    LEATHER("Leather"),
    CHAIN_MAIL("Chain Mail"),
    PLATE_MAIL("Plate Mail"),
    MAGIC_CHAIN("Magic Chain"),
    MAGIC_PLATE("Magic Plate"),
    MYSTIC_ROBE("Mystic Robe");

    private final String displayName;

    public static Armour forCode(int code) {
        switch (code) {
            case 0:
                return SKIN;
            case 1:
                return CLOTH;
            case 2:
                return LEATHER;
            case 3:
                return CHAIN_MAIL;
            case 4:
                return PLATE_MAIL;
            case 5:
                return MAGIC_CHAIN;
            case 6:
                return MAGIC_PLATE;
            case 7:
                return MYSTIC_ROBE;
            default:
                throw new RuntimeException("Unknown armour: " + code);
        }
    }

    Armour(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }
}
