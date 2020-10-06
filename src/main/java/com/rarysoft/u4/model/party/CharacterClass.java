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

public enum CharacterClass {
    MAGE("Mage"),
    BARD("Bard"),
    FIGHTER("Fighter"),
    DRUID("Druid"),
    TINKER("Tinker"),
    PALADIN("Paladin"),
    RANGER("Ranger"),
    SHEPHERD("Shepherd");

    private final String displayName;

    public static CharacterClass forCode(int code) {
        switch (code) {
            case 0:
                return MAGE;
            case 1:
                return BARD;
            case 2:
                return FIGHTER;
            case 3:
                return DRUID;
            case 4:
                return TINKER;
            case 5:
                return PALADIN;
            case 6:
                return RANGER;
            case 7:
                return SHEPHERD;
            default:
                throw new RuntimeException("Unknown character class: " + code);
        }
    }

    CharacterClass(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }
}
