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
package com.rarysoft.u4.model.party;

public enum Weapon {
    HANDS("Hands"),
    STAFF("Staff"),
    DAGGER("Dagger"),
    SLING("Sling"),
    MACE("Mace"),
    AXE("Axe"),
    SWORD("Sword"),
    BOW("Bow"),
    CROSSBOW("Crossbow"),
    FLAMING_OIL("Flaming Oil"),
    HALBERD("Halberd"),
    MAGIC_AXE("Magic Axe"),
    MAGIC_SWORD("Magic Sword"),
    MAGIC_BOW("Magic Bow"),
    MAGIC_WAND("Magic Wand"),
    MYSTIC_SWORD("Mystic Sword");

    private final String displayName;

    public static Weapon forCode(int code) {
        switch (code) {
            case 0:
                return HANDS;
            case 1:
                return STAFF;
            case 2:
                return DAGGER;
            case 3:
                return SLING;
            case 4:
                return MACE;
            case 5:
                return AXE;
            case 6:
                return SWORD;
            case 7:
                return BOW;
            case 8:
                return CROSSBOW;
            case 9:
                return FLAMING_OIL;
            case 10:
                return HALBERD;
            case 11:
                return MAGIC_AXE;
            case 12:
                return MAGIC_SWORD;
            case 13:
                return MAGIC_BOW;
            case 14:
                return MAGIC_WAND;
            case 15:
                return MYSTIC_SWORD;
            default:
                throw new RuntimeException("Unknown weapon: " + code);
        }
    }

    Weapon(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }
}
