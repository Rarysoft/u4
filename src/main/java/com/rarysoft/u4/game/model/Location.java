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
package com.rarysoft.u4.game.model;

import java.util.stream.Stream;

public enum Location {
    SURFACE(0x00, "Britannia"),
    CASTLE_BRITANNIA(0x01, "Castle Britannia"),
    THE_LYCAEUM(0x02, "The Lycaeum"),
    EMPATH_ABBEY(0x03, "Empath Abbey"),
    SERPENTS_HOLD(0x04, "Serpent's Hold"),
    MOONGLOW(0x05, "Moonglow"),
    BRITAIN(0x06, "Britain"),
    JHELOM(0x07, "Jhelom"),
    YEW(0x08, "Yew"),
    MINOC(0x09, "Minoc"),
    TRINSIC(0x0A, "Trinsic"),
    SKARA_BRAE(0x0B, "Skara Brae"),
    MAGINCIA(0x0C, "Magincia"),
    PAWS(0x0D, "Paws"),
    BUCCANEERS_DEN(0x0E, "Buccaneer's Den"),
    VESPER(0x0F, "Vesper"),
    COVE(0x10, "Cove"),
    DECEIT(0x11, "Deceit", true),
    DESPISE(0x12, "Despise", true),
    DESTARD(0x13, "Destard", true),
    WRONG(0x14, "Wrong", true),
    COVETOUS(0x15, "Covetous", true),
    SHAME(0x16, "Shame", true),
    HYTHLOTH(0x17, "Hythloth", true),
    ABYSS(0x18, "the Great Stygian Abyss", true),
    SHRINE_OF_HONESTY(0x19, "the Shrine of Honesty"),
    SHRINE_OF_COMPASSION(0x1A, "the Shrine of Compassion"),
    SHRINE_OF_VALOUR(0x1B, "the Shrine of Valour"),
    SHRINE_OF_JUSTICE(0x1C, "the Shrine of Justice"),
    SHRINE_OF_SACRIFICE(0x1D, "the Shrine of Sacrifice"),
    SHRINE_OF_HONOUR(0x1E, "the Shrine of Honour"),
    SHRINE_OF_SPIRITUALTIY(0x1F, "the Shrine of Spirituality"),
    SHRINE_OF_HUMILITY(0x20, "the Shrine of Humility");

    private final int code;

    private final String displayName;

    private final boolean isDungeon;

    Location(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
        this.isDungeon = false;
    }

    Location(int code, String displayName, boolean isDungeon) {
        this.code = code;
        this.displayName = displayName;
        this.isDungeon = isDungeon;
    }

    public static Location forCode(int code) {
        return Stream.of(values()).filter(tile -> tile.code() == code).findAny().orElseThrow(() -> new RuntimeException("Unknown location: " + code));
    }

    public int code() {
        return code;
    }

    public String displayName() {
        return displayName;
    }

    public boolean isDungeon() {
        return isDungeon;
    }
}
