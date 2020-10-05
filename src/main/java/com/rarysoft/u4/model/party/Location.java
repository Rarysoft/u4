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

import java.util.stream.Stream;

public enum Location {
    SURFACE(0x00),
    CASTLE_BRITANNIA(0x01),
    THE_LYCAEUM(0x02),
    EMPATH_ABBEY(0x03),
    SERPENTS_HOLD(0x04),
    MOONGLOW(0x05),
    BRITAIN(0x06),
    JHELOM(0x07),
    YEW(0x08),
    MINOC(0x09),
    TRINSIC(0x0A),
    SKARA_BRAE(0x0B),
    MAGINCIA(0x0C),
    PAWS(0x0D),
    BUCCANEERS_DEN(0x0E),
    VESPER(0x0F),
    COVE(0x10),
    DECEIT(0x11),
    DESPISE(0x12),
    DESTARD(0x13),
    WRONG(0x14),
    COVETOUS(0x15),
    SHAME(0x16),
    HYTHLOTH(0x17),
    ABYSS(0x18),
    SHRINE_OF_HONESTY(0x19),
    SHRINE_OF_COMPASSION(0x1A),
    SHRINE_OF_VALOUR(0x1B),
    SHRINE_OF_JUSTICE(0x1C),
    SHRINE_OF_SACRIFICE(0x1D),
    SHRINE_OF_HONOUR(0x1E),
    SHRINE_OF_SPIRITUALTIY(0x1F),
    SHRINE_OF_HUMILITY(0x20);

    private final int code;

    Location(int code) {
        this.code = code;
    }

    public static Location forCode(int code) {
        return Stream.of(values()).filter(tile -> tile.code() == code).findAny().orElseThrow(() -> new RuntimeException("Unknown location: " + code));
    }

    public int code() {
        return code;
    }
}
