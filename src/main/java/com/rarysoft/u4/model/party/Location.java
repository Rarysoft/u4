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

public enum Location {
    SURFACE,
    CASTLE_BRITANNIA,
    THE_LYCAEUM,
    EMPATH_ABBEY,
    SERPENTS_HOLD,
    MOONGLOW,
    BRITAIN,
    JHELOM,
    YEW,
    MINOC,
    TRINSIC,
    SKARA_BRAE,
    MAGINCIA,
    PAWS,
    BUCCANEERS_DEN,
    VESPER,
    COVE,
    DECEIT,
    DESPISE,
    DESTARD,
    WRONG,
    COVETOUS,
    SHAME,
    HYTHLOTH,
    ABYSS,
    SHRINE_OF_HONESTY,
    SHRINE_OF_COMPASSION,
    SHRINE_OF_VALOUR,
    SHRINE_OF_JUSTICE,
    SHRINE_OF_SACRIFICE,
    SHRINE_OF_HONOUR,
    SHRINE_OF_SPIRITUALTIY,
    SHRINE_OF_HUMILITY;

    public static Location forCode(int code) {
        switch (code) {
            case 0x00:
                return SURFACE;
            case 0x01:
                return CASTLE_BRITANNIA;
            case 0x02:
                return THE_LYCAEUM;
            case 0x03:
                return EMPATH_ABBEY;
            case 0x04:
                return SERPENTS_HOLD;
            case 0x05:
                return MOONGLOW;
            case 0x06:
                return BRITAIN;
            case 0x07:
                return JHELOM;
            case 0x08:
                return YEW;
            case 0x09:
                return MINOC;
            case 0x0A:
                return TRINSIC;
            case 0x0B:
                return SKARA_BRAE;
            case 0x0C:
                return MAGINCIA;
            case 0x0D:
                return PAWS;
            case 0x0E:
                return BUCCANEERS_DEN;
            case 0x0F:
                return VESPER;
            case 0x10:
                return COVE;
            case 0x11:
                return DECEIT;
            case 0x12:
                return DESPISE;
            case 0x13:
                return DESTARD;
            case 0x14:
                return WRONG;
            case 0x15:
                return COVETOUS;
            case 0x16:
                return SHAME;
            case 0x17:
                return HYTHLOTH;
            case 0x18:
                return ABYSS;
            case 0x19:
                return SHRINE_OF_HONESTY;
            case 0x1A:
                return SHRINE_OF_COMPASSION;
            case 0x1B:
                return SHRINE_OF_VALOUR;
            case 0x1C:
                return SHRINE_OF_JUSTICE;
            case 0x1D:
                return SHRINE_OF_SACRIFICE;
            case 0x1E:
                return SHRINE_OF_HONOUR;
            case 0x1F:
                return SHRINE_OF_SPIRITUALTIY;
            case 0x20:
                return SHRINE_OF_HUMILITY;
            default:
                throw new RuntimeException("Unknown location: " + code);
        }
    }
}
