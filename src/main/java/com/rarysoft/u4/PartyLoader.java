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
package com.rarysoft.u4;

import com.rarysoft.u4.game.CharacterLoader;
import com.rarysoft.u4.game.party.BalloonStatus;
import com.rarysoft.u4.game.party.Character;
import com.rarysoft.u4.game.party.Location;
import com.rarysoft.u4.game.party.Party;
import com.rarysoft.u4.game.party.Transportation;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class PartyLoader {
    private final CharacterLoader characterLoader;

    public PartyLoader(CharacterLoader characterLoader) {
        this.characterLoader = characterLoader;
    }

    public Party load(InputStream inputStream) throws IOException {
        int counter = fourBytesToInt(inputStream);
        int moves = fourBytesToInt(inputStream);
        Character character0 = characterLoader.load(inputStream);
        Character character1 = characterLoader.load(inputStream);
        Character character2 = characterLoader.load(inputStream);
        Character character3 = characterLoader.load(inputStream);
        Character character4 = characterLoader.load(inputStream);
        Character character5 = characterLoader.load(inputStream);
        Character character6 = characterLoader.load(inputStream);
        Character character7 = characterLoader.load(inputStream);
        int food = fourBytesToInt(inputStream);
        int gold = twoBytesToInt(inputStream);
        int karmaHonesty = twoBytesToInt(inputStream);
        int karmaCompassion = twoBytesToInt(inputStream);
        int karmaValour = twoBytesToInt(inputStream);
        int karmaJustice = twoBytesToInt(inputStream);
        int karmaSacrifice = twoBytesToInt(inputStream);
        int karmaHonour = twoBytesToInt(inputStream);
        int karmaSpirituality = twoBytesToInt(inputStream);
        int karmaHumility = twoBytesToInt(inputStream);
        int torches = twoBytesToInt(inputStream);
        int gems = twoBytesToInt(inputStream);
        int keys = twoBytesToInt(inputStream);
        int sextants = twoBytesToInt(inputStream);
        int armourNone = twoBytesToInt(inputStream);
        int armourCloth = twoBytesToInt(inputStream);
        int armourLeather = twoBytesToInt(inputStream);
        int armourChain = twoBytesToInt(inputStream);
        int armourPlate = twoBytesToInt(inputStream);
        int armourMagicChain = twoBytesToInt(inputStream);
        int armourMagicPlate = twoBytesToInt(inputStream);
        int armourMysticRobes = twoBytesToInt(inputStream);
        int weaponNone = twoBytesToInt(inputStream);
        int weaponStaves = twoBytesToInt(inputStream);
        int weaponDaggers = twoBytesToInt(inputStream);
        int weaponSlings = twoBytesToInt(inputStream);
        int weaponMaces = twoBytesToInt(inputStream);
        int weaponAxes = twoBytesToInt(inputStream);
        int weaponSwords = twoBytesToInt(inputStream);
        int weaponBows = twoBytesToInt(inputStream);
        int weaponCrossbows = twoBytesToInt(inputStream);
        int weaponFlamingOils = twoBytesToInt(inputStream);
        int weaponHalberds = twoBytesToInt(inputStream);
        int weaponMagicAxes = twoBytesToInt(inputStream);
        int weaponMagicSwords = twoBytesToInt(inputStream);
        int weaponMagicBows = twoBytesToInt(inputStream);
        int weaponMagicWands = twoBytesToInt(inputStream);
        int weaponMysticSwords = twoBytesToInt(inputStream);
        int sulfurousAsh = twoBytesToInt(inputStream);
        int ginseng = twoBytesToInt(inputStream);
        int garlic = twoBytesToInt(inputStream);
        int spiderSilk = twoBytesToInt(inputStream);
        int bloodMoss = twoBytesToInt(inputStream);
        int blackPearl = twoBytesToInt(inputStream);
        int nightshade = twoBytesToInt(inputStream);
        int mandrake = twoBytesToInt(inputStream);
        int awaken = twoBytesToInt(inputStream);
        int blink = twoBytesToInt(inputStream);
        int cure = twoBytesToInt(inputStream);
        int dispel = twoBytesToInt(inputStream);
        int energy = twoBytesToInt(inputStream);
        int fireball = twoBytesToInt(inputStream);
        int gate = twoBytesToInt(inputStream);
        int heal = twoBytesToInt(inputStream);
        int iceball = twoBytesToInt(inputStream);
        int jinx = twoBytesToInt(inputStream);
        int kill = twoBytesToInt(inputStream);
        int light = twoBytesToInt(inputStream);
        int magicMissile = twoBytesToInt(inputStream);
        int negate = twoBytesToInt(inputStream);
        int open = twoBytesToInt(inputStream);
        int protection = twoBytesToInt(inputStream);
        int quickness = twoBytesToInt(inputStream);
        int resurrect = twoBytesToInt(inputStream);
        int sleep = twoBytesToInt(inputStream);
        int tremor = twoBytesToInt(inputStream);
        int undead = twoBytesToInt(inputStream);
        int view = twoBytesToInt(inputStream);
        int winds = twoBytesToInt(inputStream);
        int xIt = twoBytesToInt(inputStream);
        int yUp = twoBytesToInt(inputStream);
        int zDown = twoBytesToInt(inputStream);
        boolean[] items = intBitsToBoolean(inputStream);
        boolean[] moreItems = intBitsToBoolean(inputStream);
        int col = inputStream.read();
        int row = inputStream.read();
        boolean[] stones = intBitsToBoolean(inputStream);
        boolean[] runes = intBitsToBoolean(inputStream);
        int numberOfCharacters = twoBytesToInt(inputStream);
        Transportation transportation = Transportation.forCode(twoBytesToInt(inputStream));
        int balloonStatusOrTorchDuration = twoBytesToInt(inputStream);
        int phaseOfTrammel = twoBytesToInt(inputStream);
        int phaseOfFelucca = twoBytesToInt(inputStream);
        int shipHullIntegrity = twoBytesToInt(inputStream);
        boolean introducedToLordBritish = twoBytesToInt(inputStream) > 0;
        int lastSuccessfulCamp = twoBytesToInt(inputStream);
        int lastSuccessfulMandrakeNightshade = twoBytesToInt(inputStream);
        int lastSuccessfulMeditation = twoBytesToInt(inputStream);
        int lastVirtueRelatedConversation = twoBytesToInt(inputStream);
        int dungeonCol = inputStream.read();
        int dungeonRow = inputStream.read();
        int dungeonOrientation = twoBytesToInt(inputStream);
        int dungeonLevel = twoBytesToInt(inputStream);
        Location currentLocation = Location.forCode(twoBytesToInt(inputStream));
        BalloonStatus balloonStatus = null;
        int torchDuration = 0;
        if (currentLocation.isDungeon()) {
            torchDuration = balloonStatusOrTorchDuration;
        }
        else {
            balloonStatus = BalloonStatus.forCode(balloonStatusOrTorchDuration);
        }
        return new Party(
                counter,
                moves,
                character0,
                character1,
                character2,
                character3,
                character4,
                character5,
                character6,
                character7,
                food,
                gold,
                karmaHonesty,
                karmaCompassion,
                karmaValour,
                karmaJustice,
                karmaSacrifice,
                karmaHonour,
                karmaSpirituality,
                karmaHumility,
                torches,
                gems,
                keys,
                sextants,
                armourNone,
                armourCloth,
                armourLeather,
                armourChain,
                armourPlate,
                armourMagicChain,
                armourMagicPlate,
                armourMysticRobes,
                weaponNone,
                weaponStaves,
                weaponDaggers,
                weaponSlings,
                weaponMaces,
                weaponAxes,
                weaponSwords,
                weaponBows,
                weaponCrossbows,
                weaponFlamingOils,
                weaponHalberds,
                weaponMagicAxes,
                weaponMagicSwords,
                weaponMagicBows,
                weaponMagicWands,
                weaponMysticSwords,
                sulfurousAsh,
                ginseng,
                garlic,
                spiderSilk,
                bloodMoss,
                blackPearl,
                nightshade,
                mandrake,
                awaken,
                blink,
                cure,
                dispel,
                energy,
                fireball,
                gate,
                heal,
                iceball,
                jinx,
                kill,
                light,
                magicMissile,
                negate,
                open,
                protection,
                quickness,
                resurrect,
                sleep,
                tremor,
                undead,
                view,
                winds,
                xIt,
                yUp,
                zDown,
                items[0],
                items[1],
                items[2],
                items[3],
                items[4],
                items[5],
                items[6],
                items[7],
                moreItems[0],
                moreItems[1],
                moreItems[2],
                moreItems[3],
                moreItems[4],
                moreItems[5],
                moreItems[6],
                moreItems[7],
                col,
                row,
                stones[0],
                stones[1],
                stones[2],
                stones[3],
                stones[4],
                stones[5],
                stones[6],
                stones[7],
                runes[0],
                runes[1],
                runes[2],
                runes[3],
                runes[4],
                runes[5],
                runes[6],
                runes[7],
                numberOfCharacters,
                transportation,
                balloonStatus,
                torchDuration,
                phaseOfTrammel,
                phaseOfFelucca,
                shipHullIntegrity,
                introducedToLordBritish,
                lastSuccessfulCamp,
                lastSuccessfulMandrakeNightshade,
                lastSuccessfulMeditation,
                lastVirtueRelatedConversation,
                dungeonCol,
                dungeonRow,
                dungeonOrientation,
                dungeonLevel,
                currentLocation
        );
    }

    private int fourBytesToInt(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) inputStream.read();
        bytes[1] = (byte) inputStream.read();
        bytes[2] = (byte) inputStream.read();
        bytes[3] = (byte) inputStream.read();
        return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    private int twoBytesToInt(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) inputStream.read();
        bytes[1] = (byte) inputStream.read();
        return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    private boolean[] intBitsToBoolean(InputStream inputStream) throws IOException {
        int value = inputStream.read();
        boolean[] bits = new boolean[8];
        bits[0] = (value & 0x80) == 0x80;
        bits[1] = (value & 0x40) == 0x40;
        bits[2] = (value & 0x20) == 0x20;
        bits[3] = (value & 0x10) == 0x10;
        bits[4] = (value & 0x08) == 0x08;
        bits[5] = (value & 0x04) == 0x04;
        bits[6] = (value & 0x02) == 0x02;
        bits[7] = (value & 0x01) == 0x01;
        return bits;
    }
}
