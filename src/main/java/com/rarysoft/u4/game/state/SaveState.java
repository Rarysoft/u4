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
package com.rarysoft.u4.game.state;

import com.rarysoft.u4.game.model.BalloonStatus;
import com.rarysoft.u4.game.model.Character;
import com.rarysoft.u4.game.model.Location;
import com.rarysoft.u4.game.model.Transportation;

public final class SaveState {
    private final int counter;
    private final int moves;
    private final Character player0;
    private final Character player1;
    private final Character player2;
    private final Character player3;
    private final Character player4;
    private final Character player5;
    private final Character player6;
    private final Character player7;
    private final int food;
    private final int gold;
    private final int honesty;
    private final int compassion;
    private final int valour;
    private final int justice;
    private final int sacrifice;
    private final int honour;
    private final int spirituality;
    private final int humility;
    private final int torches;
    private final int gems;
    private final int keys;
    private final int sextants;
    private final int armourUnused;
    private final int armourCloths;
    private final int armourLeathers;
    private final int armourChains;
    private final int armourPlates;
    private final int armourMagicChains;
    private final int armourMagicPlates;
    private final int armourMysticRobes;
    private final int weaponUnused;
    private final int weaponStaves;
    private final int weaponDaggers;
    private final int weaponSlings;
    private final int weaponMaces;
    private final int weaponAxes;
    private final int weaponSwords;
    private final int weaponBows;
    private final int weaponCrossbows;
    private final int weaponFlamingOils;
    private final int weaponHalberds;
    private final int weaponMagicAxes;
    private final int weaponMagicSwords;
    private final int weaponMagicBows;
    private final int weaponMagicWands;
    private final int weaponMysticSwords;
    private final int sulfurousAsh;
    private final int ginseng;
    private final int garlic;
    private final int spiderSilk;
    private final int bloodMoss;
    private final int blackPearl;
    private final int nightshade;
    private final int mandrake;
    private final int awaken;
    private final int blink;
    private final int cure;
    private final int dispel;
    private final int energy;
    private final int fireball;
    private final int gate;
    private final int heal;
    private final int iceball;
    private final int jinx;
    private final int kill;
    private final int light;
    private final int magicMissile;
    private final int negate;
    private final int open;
    private final int protection;
    private final int quickness;
    private final int resurrect;
    private final int sleep;
    private final int tremour;
    private final int undead;
    private final int view;
    private final int winds;
    private final int xIt;
    private final int yUp;
    private final int zDown;
    private final boolean skull;
    private final boolean skullDestroyed;
    private final boolean candle;
    private final boolean book;
    private final boolean bell;
    private final boolean keyPartC;
    private final boolean keyPartL;
    private final boolean keyPartT;
    private final boolean horn;
    private final boolean wheel;
    private final boolean candleUsed;
    private final boolean bookUsed;
    private final boolean bellUsed;
    private final boolean unused1;
    private final boolean unused2;
    private final boolean unused3;
    private final int col;
    private final int row;
    private final boolean blueStone;
    private final boolean yellowStone;
    private final boolean redStone;
    private final boolean greenStone;
    private final boolean orangeStone;
    private final boolean purpleStone;
    private final boolean whiteStone;
    private final boolean blackStone;
    private final boolean runeHonesty;
    private final boolean runCompassion;
    private final boolean runeValour;
    private final boolean runeJustice;
    private final boolean runeSacrifice;
    private final boolean runeHonour;
    private final boolean runeSpirituality;
    private final boolean runeHumility;
    private final int numberOfCharacters;
    private final Transportation transportation;
    private final BalloonStatus balloonStatus;
    private final int torchDuration;
    private final int phaseOfTrammel;
    private final int phaseOfFelucca;
    private final int hullIntegrity;
    private final boolean introducedToLordBritish;
    private final int lastSuccessfulCamp;
    private final int lastMandrakeNightshadeFind;
    private final int lastSuccessfulMeditation;
    private final int lastVirtueRelatedConversation;
    private final int dungeonCol;
    private final int dungeonRow;
    private final int orientationInDungeon;
    private final int dungeonLevel;
    private final Location currentPartyLocation;

    public SaveState(int counter, int moves, Character player0, Character player1, Character player2, Character player3, Character player4, Character player5, Character player6, Character player7, int food, int gold, int honesty, int compassion, int valour, int justice, int sacrifice, int honour, int spirituality, int humility, int torches, int gems, int keys, int sextants, int armourUnused, int armourCloths, int armourLeathers, int armourChains, int armourPlates, int armourMagicChains, int armourMagicPlates, int armourMysticRobes, int weaponUnused, int weaponStaves, int weaponDaggers, int weaponSlings, int weaponMaces, int weaponAxes, int weaponSwords, int weaponBows, int weaponCrossbows, int weaponFlamingOils, int weaponHalberds, int weaponMagicAxes, int weaponMagicSwords, int weaponMagicBows, int weaponMagicWands, int weaponMysticSwords, int sulfurousAsh, int ginseng, int garlic, int spiderSilk, int bloodMoss, int blackPearl, int nightshade, int mandrake, int awaken, int blink, int cure, int dispel, int energy, int fireball, int gate, int heal, int iceball, int jinx, int kill, int light, int magicMissile, int negate, int open, int protection, int quickness, int resurrect, int sleep, int tremour, int undead, int view, int winds, int xIt, int yUp, int zDown, boolean skull, boolean skullDestroyed, boolean candle, boolean book, boolean bell, boolean keyPartC, boolean keyPartL, boolean keyPartT, boolean horn, boolean wheel, boolean candleUsed, boolean bookUsed, boolean bellUsed, boolean unused1, boolean unused2, boolean unused3, int col, int row, boolean blueStone, boolean yellowStone, boolean redStone, boolean greenStone, boolean orangeStone, boolean purpleStone, boolean whiteStone, boolean blackStone, boolean runeHonesty, boolean runCompassion, boolean runeValour, boolean runeJustice, boolean runeSacrifice, boolean runeHonour, boolean runeSpirituality, boolean runeHumility, int numberOfCharacters, Transportation transportation, BalloonStatus balloonStatus, int torchDuration, int phaseOfTrammel, int phaseOfFelucca, int hullIntegrity, boolean introducedToLordBritish, int lastSuccessfulCamp, int lastMandrakeNightshadeFind, int lastSuccessfulMeditation, int lastVirtueRelatedConversation, int dungeonCol, int dungeonRow, int orientationInDungeon, int dungeonLevel, Location currentPartyLocation) {
        this.counter = counter;
        this.moves = moves;
        this.player0 = player0;
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        this.player5 = player5;
        this.player6 = player6;
        this.player7 = player7;
        this.food = food;
        this.gold = gold;
        this.honesty = honesty;
        this.compassion = compassion;
        this.valour = valour;
        this.justice = justice;
        this.sacrifice = sacrifice;
        this.honour = honour;
        this.spirituality = spirituality;
        this.humility = humility;
        this.torches = torches;
        this.gems = gems;
        this.keys = keys;
        this.sextants = sextants;
        this.armourUnused = armourUnused;
        this.armourCloths = armourCloths;
        this.armourLeathers = armourLeathers;
        this.armourChains = armourChains;
        this.armourPlates = armourPlates;
        this.armourMagicChains = armourMagicChains;
        this.armourMagicPlates = armourMagicPlates;
        this.armourMysticRobes = armourMysticRobes;
        this.weaponUnused = weaponUnused;
        this.weaponStaves = weaponStaves;
        this.weaponDaggers = weaponDaggers;
        this.weaponSlings = weaponSlings;
        this.weaponMaces = weaponMaces;
        this.weaponAxes = weaponAxes;
        this.weaponSwords = weaponSwords;
        this.weaponBows = weaponBows;
        this.weaponCrossbows = weaponCrossbows;
        this.weaponFlamingOils = weaponFlamingOils;
        this.weaponHalberds = weaponHalberds;
        this.weaponMagicAxes = weaponMagicAxes;
        this.weaponMagicSwords = weaponMagicSwords;
        this.weaponMagicBows = weaponMagicBows;
        this.weaponMagicWands = weaponMagicWands;
        this.weaponMysticSwords = weaponMysticSwords;
        this.sulfurousAsh = sulfurousAsh;
        this.ginseng = ginseng;
        this.garlic = garlic;
        this.spiderSilk = spiderSilk;
        this.bloodMoss = bloodMoss;
        this.blackPearl = blackPearl;
        this.nightshade = nightshade;
        this.mandrake = mandrake;
        this.awaken = awaken;
        this.blink = blink;
        this.cure = cure;
        this.dispel = dispel;
        this.energy = energy;
        this.fireball = fireball;
        this.gate = gate;
        this.heal = heal;
        this.iceball = iceball;
        this.jinx = jinx;
        this.kill = kill;
        this.light = light;
        this.magicMissile = magicMissile;
        this.negate = negate;
        this.open = open;
        this.protection = protection;
        this.quickness = quickness;
        this.resurrect = resurrect;
        this.sleep = sleep;
        this.tremour = tremour;
        this.undead = undead;
        this.view = view;
        this.winds = winds;
        this.xIt = xIt;
        this.yUp = yUp;
        this.zDown = zDown;
        this.skull = skull;
        this.skullDestroyed = skullDestroyed;
        this.candle = candle;
        this.book = book;
        this.bell = bell;
        this.keyPartC = keyPartC;
        this.keyPartL = keyPartL;
        this.keyPartT = keyPartT;
        this.horn = horn;
        this.wheel = wheel;
        this.candleUsed = candleUsed;
        this.bookUsed = bookUsed;
        this.bellUsed = bellUsed;
        this.unused1 = unused1;
        this.unused2 = unused2;
        this.unused3 = unused3;
        this.col = col;
        this.row = row;
        this.blueStone = blueStone;
        this.yellowStone = yellowStone;
        this.redStone = redStone;
        this.greenStone = greenStone;
        this.orangeStone = orangeStone;
        this.purpleStone = purpleStone;
        this.whiteStone = whiteStone;
        this.blackStone = blackStone;
        this.runeHonesty = runeHonesty;
        this.runCompassion = runCompassion;
        this.runeValour = runeValour;
        this.runeJustice = runeJustice;
        this.runeSacrifice = runeSacrifice;
        this.runeHonour = runeHonour;
        this.runeSpirituality = runeSpirituality;
        this.runeHumility = runeHumility;
        this.numberOfCharacters = numberOfCharacters;
        this.transportation = transportation;
        this.balloonStatus = balloonStatus;
        this.torchDuration = torchDuration;
        this.phaseOfTrammel = phaseOfTrammel;
        this.phaseOfFelucca = phaseOfFelucca;
        this.hullIntegrity = hullIntegrity;
        this.introducedToLordBritish = introducedToLordBritish;
        this.lastSuccessfulCamp = lastSuccessfulCamp;
        this.lastMandrakeNightshadeFind = lastMandrakeNightshadeFind;
        this.lastSuccessfulMeditation = lastSuccessfulMeditation;
        this.lastVirtueRelatedConversation = lastVirtueRelatedConversation;
        this.dungeonCol = dungeonCol;
        this.dungeonRow = dungeonRow;
        this.orientationInDungeon = orientationInDungeon;
        this.dungeonLevel = dungeonLevel;
        this.currentPartyLocation = currentPartyLocation;
    }

    public int getCounter() {
        return counter;
    }

    public int getMoves() {
        return moves;
    }

    public Character getPlayer0() {
        return player0;
    }

    public Character getPlayer1() {
        return player1;
    }

    public Character getPlayer2() {
        return player2;
    }

    public Character getPlayer3() {
        return player3;
    }

    public Character getPlayer4() {
        return player4;
    }

    public Character getPlayer5() {
        return player5;
    }

    public Character getPlayer6() {
        return player6;
    }

    public Character getPlayer7() {
        return player7;
    }

    public int getFood() {
        return food;
    }

    public int getGold() {
        return gold;
    }

    public int getHonesty() {
        return honesty;
    }

    public int getCompassion() {
        return compassion;
    }

    public int getValour() {
        return valour;
    }

    public int getJustice() {
        return justice;
    }

    public int getSacrifice() {
        return sacrifice;
    }

    public int getHonour() {
        return honour;
    }

    public int getSpirituality() {
        return spirituality;
    }

    public int getHumility() {
        return humility;
    }

    public int getTorches() {
        return torches;
    }

    public int getGems() {
        return gems;
    }

    public int getKeys() {
        return keys;
    }

    public int getSextants() {
        return sextants;
    }

    public int getArmourNone() {
        return armourUnused;
    }

    public int getArmourCloths() {
        return armourCloths;
    }

    public int getArmourLeathers() {
        return armourLeathers;
    }

    public int getArmourChains() {
        return armourChains;
    }

    public int getArmourPlates() {
        return armourPlates;
    }

    public int getArmourMagicChains() {
        return armourMagicChains;
    }

    public int getArmourMagicPlates() {
        return armourMagicPlates;
    }

    public int getArmourMysticRobes() {
        return armourMysticRobes;
    }

    public int getWeaponNone() {
        return weaponUnused;
    }

    public int getWeaponStaves() {
        return weaponStaves;
    }

    public int getWeaponDaggers() {
        return weaponDaggers;
    }

    public int getWeaponSlings() {
        return weaponSlings;
    }

    public int getWeaponMaces() {
        return weaponMaces;
    }

    public int getWeaponAxes() {
        return weaponAxes;
    }

    public int getWeaponSwords() {
        return weaponSwords;
    }

    public int getWeaponBows() {
        return weaponBows;
    }

    public int getWeaponCrossbows() {
        return weaponCrossbows;
    }

    public int getWeaponFlamingOils() {
        return weaponFlamingOils;
    }

    public int getWeaponHalberds() {
        return weaponHalberds;
    }

    public int getWeaponMagicAxes() {
        return weaponMagicAxes;
    }

    public int getWeaponMagicSwords() {
        return weaponMagicSwords;
    }

    public int getWeaponMagicBows() {
        return weaponMagicBows;
    }

    public int getWeaponMagicWands() {
        return weaponMagicWands;
    }

    public int getWeaponMysticSwords() {
        return weaponMysticSwords;
    }

    public int getSulfurousAsh() {
        return sulfurousAsh;
    }

    public int getGinseng() {
        return ginseng;
    }

    public int getGarlic() {
        return garlic;
    }

    public int getSpiderSilk() {
        return spiderSilk;
    }

    public int getBloodMoss() {
        return bloodMoss;
    }

    public int getBlackPearl() {
        return blackPearl;
    }

    public int getNightshade() {
        return nightshade;
    }

    public int getMandrake() {
        return mandrake;
    }

    public int getAwaken() {
        return awaken;
    }

    public int getBlink() {
        return blink;
    }

    public int getCure() {
        return cure;
    }

    public int getDispel() {
        return dispel;
    }

    public int getEnergy() {
        return energy;
    }

    public int getFireball() {
        return fireball;
    }

    public int getGate() {
        return gate;
    }

    public int getHeal() {
        return heal;
    }

    public int getIceball() {
        return iceball;
    }

    public int getJinx() {
        return jinx;
    }

    public int getKill() {
        return kill;
    }

    public int getLight() {
        return light;
    }

    public int getMagicMissile() {
        return magicMissile;
    }

    public int getNegate() {
        return negate;
    }

    public int getOpen() {
        return open;
    }

    public int getProtection() {
        return protection;
    }

    public int getQuickness() {
        return quickness;
    }

    public int getResurrect() {
        return resurrect;
    }

    public int getSleep() {
        return sleep;
    }

    public int getTremour() {
        return tremour;
    }

    public int getUndead() {
        return undead;
    }

    public int getView() {
        return view;
    }

    public int getWinds() {
        return winds;
    }

    public int getXIt() {
        return xIt;
    }

    public int getYUp() {
        return yUp;
    }

    public int getZDown() {
        return zDown;
    }

    public boolean hasSkull() {
        return skull;
    }

    public boolean isSkullDestroyed() {
        return skullDestroyed;
    }

    public boolean hasCandle() {
        return candle;
    }

    public boolean hasBook() {
        return book;
    }

    public boolean hasBell() {
        return bell;
    }

    public boolean hasKeyPartC() {
        return keyPartC;
    }

    public boolean hasKeyPartL() {
        return keyPartL;
    }

    public boolean hasKeyPartT() {
        return keyPartT;
    }

    public boolean hasHorn() {
        return horn;
    }

    public boolean hasWheel() {
        return wheel;
    }

    public boolean isCandleUsed() {
        return candleUsed;
    }

    public boolean isBookUsed() {
        return bookUsed;
    }

    public boolean isBellUsed() {
        return bellUsed;
    }

    public boolean getUnused1() {
        return unused1;
    }

    public boolean getUnused2() {
        return unused2;
    }

    public boolean getUnused3() {
        return unused3;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public boolean hasBlueStone() {
        return blueStone;
    }

    public boolean hasYellowStone() {
        return yellowStone;
    }

    public boolean hasRedStone() {
        return redStone;
    }

    public boolean hasGreenStone() {
        return greenStone;
    }

    public boolean hasOrangeStone() {
        return orangeStone;
    }

    public boolean hasPurpleStone() {
        return purpleStone;
    }

    public boolean hasWhiteStone() {
        return whiteStone;
    }

    public boolean hasBlackStone() {
        return blackStone;
    }

    public boolean hasRuneHonesty() {
        return runeHonesty;
    }

    public boolean hasRunCompassion() {
        return runCompassion;
    }

    public boolean hasRuneValour() {
        return runeValour;
    }

    public boolean hasRuneJustice() {
        return runeJustice;
    }

    public boolean hasRuneSacrifice() {
        return runeSacrifice;
    }

    public boolean hasRuneHonour() {
        return runeHonour;
    }

    public boolean hasRuneSpirituality() {
        return runeSpirituality;
    }

    public boolean hasRuneHumility() {
        return runeHumility;
    }

    public int getNumberOfCharacters() {
        return numberOfCharacters;
    }

    public Transportation getTransportation() {
        return transportation;
    }

    public BalloonStatus getBalloonStatus() {
        return balloonStatus;
    }

    public int getTorchDuration() {
        return torchDuration;
    }

    public int getPhaseOfTrammel() {
        return phaseOfTrammel;
    }

    public int getPhaseOfFelucca() {
        return phaseOfFelucca;
    }

    public int getHullIntegrity() {
        return hullIntegrity;
    }

    public boolean isIntroducedToLordBritish() {
        return introducedToLordBritish;
    }

    public int getLastSuccessfulCamp() {
        return lastSuccessfulCamp;
    }

    public int getLastMandrakeNightshadeFind() {
        return lastMandrakeNightshadeFind;
    }

    public int getLastSuccessfulMeditation() {
        return lastSuccessfulMeditation;
    }

    public int getLastVirtueRelatedConversation() {
        return lastVirtueRelatedConversation;
    }

    public int getDungeonCol() {
        return dungeonCol;
    }

    public int getDungeonRow() {
        return dungeonRow;
    }

    public int getOrientationInDungeon() {
        return orientationInDungeon;
    }

    public int getDungeonLevel() {
        return dungeonLevel;
    }

    public Location getCurrentPartyLocation() {
        return currentPartyLocation;
    }

    public SaveState withCounterIncremented() {
        return new SaveState(
                counter + 1,
                moves,
                player0,
                player1,
                player2,
                player3,
                player4,
                player5,
                player6,
                player7,
                food,
                gold,
                honesty,
                compassion,
                valour,
                justice,
                sacrifice,
                honour,
                spirituality,
                humility,
                torches,
                gems,
                keys,
                sextants,
                armourUnused,
                armourCloths,
                armourLeathers,
                armourChains,
                armourPlates,
                armourMagicChains,
                armourMagicPlates,
                armourMysticRobes,
                weaponUnused,
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
                tremour,
                undead,
                view,
                winds,
                xIt,
                yUp,
                zDown,
                skull,
                skullDestroyed,
                candle,
                book,
                bell,
                keyPartC,
                keyPartL,
                keyPartT,
                horn,
                wheel,
                candleUsed,
                bookUsed,
                bellUsed,
                unused1,
                unused2,
                unused3,
                col,
                row,
                blueStone,
                yellowStone,
                redStone,
                greenStone,
                orangeStone,
                purpleStone,
                whiteStone,
                blackStone,
                runeHonesty,
                runCompassion,
                runeValour,
                runeJustice,
                runeSacrifice,
                runeHonour,
                runeSpirituality,
                runeHumility,
                numberOfCharacters,
                transportation,
                balloonStatus,
                torchDuration,
                phaseOfTrammel,
                phaseOfFelucca,
                hullIntegrity,
                introducedToLordBritish,
                lastSuccessfulCamp,
                lastMandrakeNightshadeFind,
                lastSuccessfulMeditation,
                lastVirtueRelatedConversation,
                dungeonCol,
                dungeonRow,
                orientationInDungeon,
                dungeonLevel,
                currentPartyLocation
        );
    }

    public SaveState withMovesIncremented() {
        return new SaveState(
                counter,
                moves + 1,
                player0,
                player1,
                player2,
                player3,
                player4,
                player5,
                player6,
                player7,
                food,
                gold,
                honesty,
                compassion,
                valour,
                justice,
                sacrifice,
                honour,
                spirituality,
                humility,
                torches,
                gems,
                keys,
                sextants,
                armourUnused,
                armourCloths,
                armourLeathers,
                armourChains,
                armourPlates,
                armourMagicChains,
                armourMagicPlates,
                armourMysticRobes,
                weaponUnused,
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
                tremour,
                undead,
                view,
                winds,
                xIt,
                yUp,
                zDown,
                skull,
                skullDestroyed,
                candle,
                book,
                bell,
                keyPartC,
                keyPartL,
                keyPartT,
                horn,
                wheel,
                candleUsed,
                bookUsed,
                bellUsed,
                unused1,
                unused2,
                unused3,
                col,
                row,
                blueStone,
                yellowStone,
                redStone,
                greenStone,
                orangeStone,
                purpleStone,
                whiteStone,
                blackStone,
                runeHonesty,
                runCompassion,
                runeValour,
                runeJustice,
                runeSacrifice,
                runeHonour,
                runeSpirituality,
                runeHumility,
                numberOfCharacters,
                transportation,
                balloonStatus,
                torchDuration,
                phaseOfTrammel,
                phaseOfFelucca,
                hullIntegrity,
                introducedToLordBritish,
                lastSuccessfulCamp,
                lastMandrakeNightshadeFind,
                lastSuccessfulMeditation,
                lastVirtueRelatedConversation,
                dungeonCol,
                dungeonRow,
                orientationInDungeon,
                dungeonLevel,
                currentPartyLocation
        );
    }

    public SaveState withHonestyChanged(int delta) {
        return new SaveState(
                counter,
                moves,
                player0,
                player1,
                player2,
                player3,
                player4,
                player5,
                player6,
                player7,
                food,
                gold,
                honesty + delta,
                compassion,
                valour,
                justice,
                sacrifice,
                honour,
                spirituality,
                humility,
                torches,
                gems,
                keys,
                sextants,
                armourUnused,
                armourCloths,
                armourLeathers,
                armourChains,
                armourPlates,
                armourMagicChains,
                armourMagicPlates,
                armourMysticRobes,
                weaponUnused,
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
                tremour,
                undead,
                view,
                winds,
                xIt,
                yUp,
                zDown,
                skull,
                skullDestroyed,
                candle,
                book,
                bell,
                keyPartC,
                keyPartL,
                keyPartT,
                horn,
                wheel,
                candleUsed,
                bookUsed,
                bellUsed,
                unused1,
                unused2,
                unused3,
                col,
                row,
                blueStone,
                yellowStone,
                redStone,
                greenStone,
                orangeStone,
                purpleStone,
                whiteStone,
                blackStone,
                runeHonesty,
                runCompassion,
                runeValour,
                runeJustice,
                runeSacrifice,
                runeHonour,
                runeSpirituality,
                runeHumility,
                numberOfCharacters,
                transportation,
                balloonStatus,
                torchDuration,
                phaseOfTrammel,
                phaseOfFelucca,
                hullIntegrity,
                introducedToLordBritish,
                lastSuccessfulCamp,
                lastMandrakeNightshadeFind,
                lastSuccessfulMeditation,
                lastVirtueRelatedConversation,
                dungeonCol,
                dungeonRow,
                orientationInDungeon,
                dungeonLevel,
                currentPartyLocation
        );
    }

    public SaveState withCompassionChanged(int delta) {
        return new SaveState(
                counter,
                moves,
                player0,
                player1,
                player2,
                player3,
                player4,
                player5,
                player6,
                player7,
                food,
                gold,
                honesty,
                compassion + delta,
                valour,
                justice,
                sacrifice,
                honour,
                spirituality,
                humility,
                torches,
                gems,
                keys,
                sextants,
                armourUnused,
                armourCloths,
                armourLeathers,
                armourChains,
                armourPlates,
                armourMagicChains,
                armourMagicPlates,
                armourMysticRobes,
                weaponUnused,
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
                tremour,
                undead,
                view,
                winds,
                xIt,
                yUp,
                zDown,
                skull,
                skullDestroyed,
                candle,
                book,
                bell,
                keyPartC,
                keyPartL,
                keyPartT,
                horn,
                wheel,
                candleUsed,
                bookUsed,
                bellUsed,
                unused1,
                unused2,
                unused3,
                col,
                row,
                blueStone,
                yellowStone,
                redStone,
                greenStone,
                orangeStone,
                purpleStone,
                whiteStone,
                blackStone,
                runeHonesty,
                runCompassion,
                runeValour,
                runeJustice,
                runeSacrifice,
                runeHonour,
                runeSpirituality,
                runeHumility,
                numberOfCharacters,
                transportation,
                balloonStatus,
                torchDuration,
                phaseOfTrammel,
                phaseOfFelucca,
                hullIntegrity,
                introducedToLordBritish,
                lastSuccessfulCamp,
                lastMandrakeNightshadeFind,
                lastSuccessfulMeditation,
                lastVirtueRelatedConversation,
                dungeonCol,
                dungeonRow,
                orientationInDungeon,
                dungeonLevel,
                currentPartyLocation
        );
    }

    public SaveState withValourChanged(int delta) {
        return new SaveState(
                counter,
                moves,
                player0,
                player1,
                player2,
                player3,
                player4,
                player5,
                player6,
                player7,
                food,
                gold,
                honesty,
                compassion,
                valour + delta,
                justice,
                sacrifice,
                honour,
                spirituality,
                humility,
                torches,
                gems,
                keys,
                sextants,
                armourUnused,
                armourCloths,
                armourLeathers,
                armourChains,
                armourPlates,
                armourMagicChains,
                armourMagicPlates,
                armourMysticRobes,
                weaponUnused,
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
                tremour,
                undead,
                view,
                winds,
                xIt,
                yUp,
                zDown,
                skull,
                skullDestroyed,
                candle,
                book,
                bell,
                keyPartC,
                keyPartL,
                keyPartT,
                horn,
                wheel,
                candleUsed,
                bookUsed,
                bellUsed,
                unused1,
                unused2,
                unused3,
                col,
                row,
                blueStone,
                yellowStone,
                redStone,
                greenStone,
                orangeStone,
                purpleStone,
                whiteStone,
                blackStone,
                runeHonesty,
                runCompassion,
                runeValour,
                runeJustice,
                runeSacrifice,
                runeHonour,
                runeSpirituality,
                runeHumility,
                numberOfCharacters,
                transportation,
                balloonStatus,
                torchDuration,
                phaseOfTrammel,
                phaseOfFelucca,
                hullIntegrity,
                introducedToLordBritish,
                lastSuccessfulCamp,
                lastMandrakeNightshadeFind,
                lastSuccessfulMeditation,
                lastVirtueRelatedConversation,
                dungeonCol,
                dungeonRow,
                orientationInDungeon,
                dungeonLevel,
                currentPartyLocation
        );
    }

    public SaveState withJusticeChanged(int delta) {
        return new SaveState(
                counter,
                moves,
                player0,
                player1,
                player2,
                player3,
                player4,
                player5,
                player6,
                player7,
                food,
                gold,
                honesty,
                compassion,
                valour,
                justice + delta,
                sacrifice,
                honour,
                spirituality,
                humility,
                torches,
                gems,
                keys,
                sextants,
                armourUnused,
                armourCloths,
                armourLeathers,
                armourChains,
                armourPlates,
                armourMagicChains,
                armourMagicPlates,
                armourMysticRobes,
                weaponUnused,
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
                tremour,
                undead,
                view,
                winds,
                xIt,
                yUp,
                zDown,
                skull,
                skullDestroyed,
                candle,
                book,
                bell,
                keyPartC,
                keyPartL,
                keyPartT,
                horn,
                wheel,
                candleUsed,
                bookUsed,
                bellUsed,
                unused1,
                unused2,
                unused3,
                col,
                row,
                blueStone,
                yellowStone,
                redStone,
                greenStone,
                orangeStone,
                purpleStone,
                whiteStone,
                blackStone,
                runeHonesty,
                runCompassion,
                runeValour,
                runeJustice,
                runeSacrifice,
                runeHonour,
                runeSpirituality,
                runeHumility,
                numberOfCharacters,
                transportation,
                balloonStatus,
                torchDuration,
                phaseOfTrammel,
                phaseOfFelucca,
                hullIntegrity,
                introducedToLordBritish,
                lastSuccessfulCamp,
                lastMandrakeNightshadeFind,
                lastSuccessfulMeditation,
                lastVirtueRelatedConversation,
                dungeonCol,
                dungeonRow,
                orientationInDungeon,
                dungeonLevel,
                currentPartyLocation
        );
    }

    public SaveState withSacrificeChanged(int delta) {
        return new SaveState(
                counter,
                moves,
                player0,
                player1,
                player2,
                player3,
                player4,
                player5,
                player6,
                player7,
                food,
                gold,
                honesty,
                compassion,
                valour,
                justice,
                sacrifice + delta,
                honour,
                spirituality,
                humility,
                torches,
                gems,
                keys,
                sextants,
                armourUnused,
                armourCloths,
                armourLeathers,
                armourChains,
                armourPlates,
                armourMagicChains,
                armourMagicPlates,
                armourMysticRobes,
                weaponUnused,
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
                tremour,
                undead,
                view,
                winds,
                xIt,
                yUp,
                zDown,
                skull,
                skullDestroyed,
                candle,
                book,
                bell,
                keyPartC,
                keyPartL,
                keyPartT,
                horn,
                wheel,
                candleUsed,
                bookUsed,
                bellUsed,
                unused1,
                unused2,
                unused3,
                col,
                row,
                blueStone,
                yellowStone,
                redStone,
                greenStone,
                orangeStone,
                purpleStone,
                whiteStone,
                blackStone,
                runeHonesty,
                runCompassion,
                runeValour,
                runeJustice,
                runeSacrifice,
                runeHonour,
                runeSpirituality,
                runeHumility,
                numberOfCharacters,
                transportation,
                balloonStatus,
                torchDuration,
                phaseOfTrammel,
                phaseOfFelucca,
                hullIntegrity,
                introducedToLordBritish,
                lastSuccessfulCamp,
                lastMandrakeNightshadeFind,
                lastSuccessfulMeditation,
                lastVirtueRelatedConversation,
                dungeonCol,
                dungeonRow,
                orientationInDungeon,
                dungeonLevel,
                currentPartyLocation
        );
    }

    public SaveState withHonourChanged(int delta) {
        return new SaveState(
                counter,
                moves,
                player0,
                player1,
                player2,
                player3,
                player4,
                player5,
                player6,
                player7,
                food,
                gold,
                honesty,
                compassion,
                valour,
                justice,
                sacrifice,
                honour + delta,
                spirituality,
                humility,
                torches,
                gems,
                keys,
                sextants,
                armourUnused,
                armourCloths,
                armourLeathers,
                armourChains,
                armourPlates,
                armourMagicChains,
                armourMagicPlates,
                armourMysticRobes,
                weaponUnused,
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
                tremour,
                undead,
                view,
                winds,
                xIt,
                yUp,
                zDown,
                skull,
                skullDestroyed,
                candle,
                book,
                bell,
                keyPartC,
                keyPartL,
                keyPartT,
                horn,
                wheel,
                candleUsed,
                bookUsed,
                bellUsed,
                unused1,
                unused2,
                unused3,
                col,
                row,
                blueStone,
                yellowStone,
                redStone,
                greenStone,
                orangeStone,
                purpleStone,
                whiteStone,
                blackStone,
                runeHonesty,
                runCompassion,
                runeValour,
                runeJustice,
                runeSacrifice,
                runeHonour,
                runeSpirituality,
                runeHumility,
                numberOfCharacters,
                transportation,
                balloonStatus,
                torchDuration,
                phaseOfTrammel,
                phaseOfFelucca,
                hullIntegrity,
                introducedToLordBritish,
                lastSuccessfulCamp,
                lastMandrakeNightshadeFind,
                lastSuccessfulMeditation,
                lastVirtueRelatedConversation,
                dungeonCol,
                dungeonRow,
                orientationInDungeon,
                dungeonLevel,
                currentPartyLocation
        );
    }

    public SaveState withSpiritualityChanged(int delta) {
        return new SaveState(
                counter,
                moves,
                player0,
                player1,
                player2,
                player3,
                player4,
                player5,
                player6,
                player7,
                food,
                gold,
                honesty,
                compassion,
                valour,
                justice,
                sacrifice,
                honour,
                spirituality + delta,
                humility,
                torches,
                gems,
                keys,
                sextants,
                armourUnused,
                armourCloths,
                armourLeathers,
                armourChains,
                armourPlates,
                armourMagicChains,
                armourMagicPlates,
                armourMysticRobes,
                weaponUnused,
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
                tremour,
                undead,
                view,
                winds,
                xIt,
                yUp,
                zDown,
                skull,
                skullDestroyed,
                candle,
                book,
                bell,
                keyPartC,
                keyPartL,
                keyPartT,
                horn,
                wheel,
                candleUsed,
                bookUsed,
                bellUsed,
                unused1,
                unused2,
                unused3,
                col,
                row,
                blueStone,
                yellowStone,
                redStone,
                greenStone,
                orangeStone,
                purpleStone,
                whiteStone,
                blackStone,
                runeHonesty,
                runCompassion,
                runeValour,
                runeJustice,
                runeSacrifice,
                runeHonour,
                runeSpirituality,
                runeHumility,
                numberOfCharacters,
                transportation,
                balloonStatus,
                torchDuration,
                phaseOfTrammel,
                phaseOfFelucca,
                hullIntegrity,
                introducedToLordBritish,
                lastSuccessfulCamp,
                lastMandrakeNightshadeFind,
                lastSuccessfulMeditation,
                lastVirtueRelatedConversation,
                dungeonCol,
                dungeonRow,
                orientationInDungeon,
                dungeonLevel,
                currentPartyLocation
        );
    }

    public SaveState withHumilityChanged(int delta) {
        return new SaveState(
                counter,
                moves,
                player0,
                player1,
                player2,
                player3,
                player4,
                player5,
                player6,
                player7,
                food,
                gold,
                honesty,
                compassion,
                valour,
                justice,
                sacrifice,
                honour,
                spirituality,
                humility + delta,
                torches,
                gems,
                keys,
                sextants,
                armourUnused,
                armourCloths,
                armourLeathers,
                armourChains,
                armourPlates,
                armourMagicChains,
                armourMagicPlates,
                armourMysticRobes,
                weaponUnused,
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
                tremour,
                undead,
                view,
                winds,
                xIt,
                yUp,
                zDown,
                skull,
                skullDestroyed,
                candle,
                book,
                bell,
                keyPartC,
                keyPartL,
                keyPartT,
                horn,
                wheel,
                candleUsed,
                bookUsed,
                bellUsed,
                unused1,
                unused2,
                unused3,
                col,
                row,
                blueStone,
                yellowStone,
                redStone,
                greenStone,
                orangeStone,
                purpleStone,
                whiteStone,
                blackStone,
                runeHonesty,
                runCompassion,
                runeValour,
                runeJustice,
                runeSacrifice,
                runeHonour,
                runeSpirituality,
                runeHumility,
                numberOfCharacters,
                transportation,
                balloonStatus,
                torchDuration,
                phaseOfTrammel,
                phaseOfFelucca,
                hullIntegrity,
                introducedToLordBritish,
                lastSuccessfulCamp,
                lastMandrakeNightshadeFind,
                lastSuccessfulMeditation,
                lastVirtueRelatedConversation,
                dungeonCol,
                dungeonRow,
                orientationInDungeon,
                dungeonLevel,
                currentPartyLocation
        );
    }

    public SaveState withKeysChanged(int delta) {
        return new SaveState(
                counter,
                moves,
                player0,
                player1,
                player2,
                player3,
                player4,
                player5,
                player6,
                player7,
                food,
                gold,
                honesty,
                compassion,
                valour,
                justice,
                sacrifice,
                honour,
                spirituality,
                humility,
                torches,
                gems,
                keys + delta,
                sextants,
                armourUnused,
                armourCloths,
                armourLeathers,
                armourChains,
                armourPlates,
                armourMagicChains,
                armourMagicPlates,
                armourMysticRobes,
                weaponUnused,
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
                tremour,
                undead,
                view,
                winds,
                xIt,
                yUp,
                zDown,
                skull,
                skullDestroyed,
                candle,
                book,
                bell,
                keyPartC,
                keyPartL,
                keyPartT,
                horn,
                wheel,
                candleUsed,
                bookUsed,
                bellUsed,
                unused1,
                unused2,
                unused3,
                col,
                row,
                blueStone,
                yellowStone,
                redStone,
                greenStone,
                orangeStone,
                purpleStone,
                whiteStone,
                blackStone,
                runeHonesty,
                runCompassion,
                runeValour,
                runeJustice,
                runeSacrifice,
                runeHonour,
                runeSpirituality,
                runeHumility,
                numberOfCharacters,
                transportation,
                balloonStatus,
                torchDuration,
                phaseOfTrammel,
                phaseOfFelucca,
                hullIntegrity,
                introducedToLordBritish,
                lastSuccessfulCamp,
                lastMandrakeNightshadeFind,
                lastSuccessfulMeditation,
                lastVirtueRelatedConversation,
                dungeonCol,
                dungeonRow,
                orientationInDungeon,
                dungeonLevel,
                currentPartyLocation
        );
    }

    public SaveState withKeysDecremented() {
        return new SaveState(
                counter,
                moves,
                player0,
                player1,
                player2,
                player3,
                player4,
                player5,
                player6,
                player7,
                food,
                gold,
                honesty,
                compassion,
                valour,
                justice,
                sacrifice,
                honour,
                spirituality,
                humility,
                torches,
                gems,
                keys - 1,
                sextants,
                armourUnused,
                armourCloths,
                armourLeathers,
                armourChains,
                armourPlates,
                armourMagicChains,
                armourMagicPlates,
                armourMysticRobes,
                weaponUnused,
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
                tremour,
                undead,
                view,
                winds,
                xIt,
                yUp,
                zDown,
                skull,
                skullDestroyed,
                candle,
                book,
                bell,
                keyPartC,
                keyPartL,
                keyPartT,
                horn,
                wheel,
                candleUsed,
                bookUsed,
                bellUsed,
                unused1,
                unused2,
                unused3,
                col,
                row,
                blueStone,
                yellowStone,
                redStone,
                greenStone,
                orangeStone,
                purpleStone,
                whiteStone,
                blackStone,
                runeHonesty,
                runCompassion,
                runeValour,
                runeJustice,
                runeSacrifice,
                runeHonour,
                runeSpirituality,
                runeHumility,
                numberOfCharacters,
                transportation,
                balloonStatus,
                torchDuration,
                phaseOfTrammel,
                phaseOfFelucca,
                hullIntegrity,
                introducedToLordBritish,
                lastSuccessfulCamp,
                lastMandrakeNightshadeFind,
                lastSuccessfulMeditation,
                lastVirtueRelatedConversation,
                dungeonCol,
                dungeonRow,
                orientationInDungeon,
                dungeonLevel,
                currentPartyLocation
        );
    }

    public SaveState withRowAndCol(int row, int col) {
        return new SaveState(
                counter,
                moves,
                player0,
                player1,
                player2,
                player3,
                player4,
                player5,
                player6,
                player7,
                food,
                gold,
                honesty,
                compassion,
                valour,
                justice,
                sacrifice,
                honour,
                spirituality,
                humility,
                torches,
                gems,
                keys,
                sextants,
                armourUnused,
                armourCloths,
                armourLeathers,
                armourChains,
                armourPlates,
                armourMagicChains,
                armourMagicPlates,
                armourMysticRobes,
                weaponUnused,
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
                tremour,
                undead,
                view,
                winds,
                xIt,
                yUp,
                zDown,
                skull,
                skullDestroyed,
                candle,
                book,
                bell,
                keyPartC,
                keyPartL,
                keyPartT,
                horn,
                wheel,
                candleUsed,
                bookUsed,
                bellUsed,
                unused1,
                unused2,
                unused3,
                col,
                row,
                blueStone,
                yellowStone,
                redStone,
                greenStone,
                orangeStone,
                purpleStone,
                whiteStone,
                blackStone,
                runeHonesty,
                runCompassion,
                runeValour,
                runeJustice,
                runeSacrifice,
                runeHonour,
                runeSpirituality,
                runeHumility,
                numberOfCharacters,
                transportation,
                balloonStatus,
                torchDuration,
                phaseOfTrammel,
                phaseOfFelucca,
                hullIntegrity,
                introducedToLordBritish,
                lastSuccessfulCamp,
                lastMandrakeNightshadeFind,
                lastSuccessfulMeditation,
                lastVirtueRelatedConversation,
                dungeonCol,
                dungeonRow,
                orientationInDungeon,
                dungeonLevel,
                currentPartyLocation
        );
    }

    public SaveState withWinds(int winds) {
        return new SaveState(
                counter,
                moves,
                player0,
                player1,
                player2,
                player3,
                player4,
                player5,
                player6,
                player7,
                food,
                gold,
                honesty,
                compassion,
                valour,
                justice,
                sacrifice,
                honour,
                spirituality,
                humility,
                torches,
                gems,
                keys,
                sextants,
                armourUnused,
                armourCloths,
                armourLeathers,
                armourChains,
                armourPlates,
                armourMagicChains,
                armourMagicPlates,
                armourMysticRobes,
                weaponUnused,
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
                tremour,
                undead,
                view,
                winds,
                xIt,
                yUp,
                zDown,
                skull,
                skullDestroyed,
                candle,
                book,
                bell,
                keyPartC,
                keyPartL,
                keyPartT,
                horn,
                wheel,
                candleUsed,
                bookUsed,
                bellUsed,
                unused1,
                unused2,
                unused3,
                col,
                row,
                blueStone,
                yellowStone,
                redStone,
                greenStone,
                orangeStone,
                purpleStone,
                whiteStone,
                blackStone,
                runeHonesty,
                runCompassion,
                runeValour,
                runeJustice,
                runeSacrifice,
                runeHonour,
                runeSpirituality,
                runeHumility,
                numberOfCharacters,
                transportation,
                balloonStatus,
                torchDuration,
                phaseOfTrammel,
                phaseOfFelucca,
                hullIntegrity,
                introducedToLordBritish,
                lastSuccessfulCamp,
                lastMandrakeNightshadeFind,
                lastSuccessfulMeditation,
                lastVirtueRelatedConversation,
                dungeonCol,
                dungeonRow,
                orientationInDungeon,
                dungeonLevel,
                currentPartyLocation
        );
    }

    public SaveState withRowAndColChanged(int rowDelta, int colDelta) {
        return new SaveState(
                counter,
                moves,
                player0,
                player1,
                player2,
                player3,
                player4,
                player5,
                player6,
                player7,
                food,
                gold,
                honesty,
                compassion,
                valour,
                justice,
                sacrifice,
                honour,
                spirituality,
                humility,
                torches,
                gems,
                keys,
                sextants,
                armourUnused,
                armourCloths,
                armourLeathers,
                armourChains,
                armourPlates,
                armourMagicChains,
                armourMagicPlates,
                armourMysticRobes,
                weaponUnused,
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
                tremour,
                undead,
                view,
                winds,
                xIt,
                yUp,
                zDown,
                skull,
                skullDestroyed,
                candle,
                book,
                bell,
                keyPartC,
                keyPartL,
                keyPartT,
                horn,
                wheel,
                candleUsed,
                bookUsed,
                bellUsed,
                unused1,
                unused2,
                unused3,
                col + colDelta,
                row + rowDelta,
                blueStone,
                yellowStone,
                redStone,
                greenStone,
                orangeStone,
                purpleStone,
                whiteStone,
                blackStone,
                runeHonesty,
                runCompassion,
                runeValour,
                runeJustice,
                runeSacrifice,
                runeHonour,
                runeSpirituality,
                runeHumility,
                numberOfCharacters,
                transportation,
                balloonStatus,
                torchDuration,
                phaseOfTrammel,
                phaseOfFelucca,
                hullIntegrity,
                introducedToLordBritish,
                lastSuccessfulCamp,
                lastMandrakeNightshadeFind,
                lastSuccessfulMeditation,
                lastVirtueRelatedConversation,
                dungeonCol,
                dungeonRow,
                orientationInDungeon,
                dungeonLevel,
                currentPartyLocation
        );
    }

    public SaveState withPhaseOfTrammelCycled(int cycleLength) {
        return new SaveState(
                counter,
                moves,
                player0,
                player1,
                player2,
                player3,
                player4,
                player5,
                player6,
                player7,
                food,
                gold,
                honesty,
                compassion,
                valour,
                justice,
                sacrifice,
                honour,
                spirituality,
                humility,
                torches,
                gems,
                keys,
                sextants,
                armourUnused,
                armourCloths,
                armourLeathers,
                armourChains,
                armourPlates,
                armourMagicChains,
                armourMagicPlates,
                armourMysticRobes,
                weaponUnused,
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
                tremour,
                undead,
                view,
                winds,
                xIt,
                yUp,
                zDown,
                skull,
                skullDestroyed,
                candle,
                book,
                bell,
                keyPartC,
                keyPartL,
                keyPartT,
                horn,
                wheel,
                candleUsed,
                bookUsed,
                bellUsed,
                unused1,
                unused2,
                unused3,
                col,
                row,
                blueStone,
                yellowStone,
                redStone,
                greenStone,
                orangeStone,
                purpleStone,
                whiteStone,
                blackStone,
                runeHonesty,
                runCompassion,
                runeValour,
                runeJustice,
                runeSacrifice,
                runeHonour,
                runeSpirituality,
                runeHumility,
                numberOfCharacters,
                transportation,
                balloonStatus,
                torchDuration,
                phaseOfTrammel + 1 % cycleLength,
                phaseOfFelucca,
                hullIntegrity,
                introducedToLordBritish,
                lastSuccessfulCamp,
                lastMandrakeNightshadeFind,
                lastSuccessfulMeditation,
                lastVirtueRelatedConversation,
                dungeonCol,
                dungeonRow,
                orientationInDungeon,
                dungeonLevel,
                currentPartyLocation
        );
    }

    public SaveState withPhaseOfFeluccaCycled(int cycleLength) {
        return new SaveState(
                counter,
                moves,
                player0,
                player1,
                player2,
                player3,
                player4,
                player5,
                player6,
                player7,
                food,
                gold,
                honesty,
                compassion,
                valour,
                justice,
                sacrifice,
                honour,
                spirituality,
                humility,
                torches,
                gems,
                keys,
                sextants,
                armourUnused,
                armourCloths,
                armourLeathers,
                armourChains,
                armourPlates,
                armourMagicChains,
                armourMagicPlates,
                armourMysticRobes,
                weaponUnused,
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
                tremour,
                undead,
                view,
                winds,
                xIt,
                yUp,
                zDown,
                skull,
                skullDestroyed,
                candle,
                book,
                bell,
                keyPartC,
                keyPartL,
                keyPartT,
                horn,
                wheel,
                candleUsed,
                bookUsed,
                bellUsed,
                unused1,
                unused2,
                unused3,
                col,
                row,
                blueStone,
                yellowStone,
                redStone,
                greenStone,
                orangeStone,
                purpleStone,
                whiteStone,
                blackStone,
                runeHonesty,
                runCompassion,
                runeValour,
                runeJustice,
                runeSacrifice,
                runeHonour,
                runeSpirituality,
                runeHumility,
                numberOfCharacters,
                transportation,
                balloonStatus,
                torchDuration,
                phaseOfTrammel,
                phaseOfFelucca + 1 % cycleLength,
                hullIntegrity,
                introducedToLordBritish,
                lastSuccessfulCamp,
                lastMandrakeNightshadeFind,
                lastSuccessfulMeditation,
                lastVirtueRelatedConversation,
                dungeonCol,
                dungeonRow,
                orientationInDungeon,
                dungeonLevel,
                currentPartyLocation
        );
    }

    public SaveState withDungeonRowAndCol(int dungeonRow, int dungeonCol) {
        return new SaveState(
                counter,
                moves,
                player0,
                player1,
                player2,
                player3,
                player4,
                player5,
                player6,
                player7,
                food,
                gold,
                honesty,
                compassion,
                valour,
                justice,
                sacrifice,
                honour,
                spirituality,
                humility,
                torches,
                gems,
                keys,
                sextants,
                armourUnused,
                armourCloths,
                armourLeathers,
                armourChains,
                armourPlates,
                armourMagicChains,
                armourMagicPlates,
                armourMysticRobes,
                weaponUnused,
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
                tremour,
                undead,
                view,
                winds,
                xIt,
                yUp,
                zDown,
                skull,
                skullDestroyed,
                candle,
                book,
                bell,
                keyPartC,
                keyPartL,
                keyPartT,
                horn,
                wheel,
                candleUsed,
                bookUsed,
                bellUsed,
                unused1,
                unused2,
                unused3,
                col,
                row,
                blueStone,
                yellowStone,
                redStone,
                greenStone,
                orangeStone,
                purpleStone,
                whiteStone,
                blackStone,
                runeHonesty,
                runCompassion,
                runeValour,
                runeJustice,
                runeSacrifice,
                runeHonour,
                runeSpirituality,
                runeHumility,
                numberOfCharacters,
                transportation,
                balloonStatus,
                torchDuration,
                phaseOfTrammel,
                phaseOfFelucca,
                hullIntegrity,
                introducedToLordBritish,
                lastSuccessfulCamp,
                lastMandrakeNightshadeFind,
                lastSuccessfulMeditation,
                lastVirtueRelatedConversation,
                dungeonCol,
                dungeonRow,
                orientationInDungeon,
                dungeonLevel,
                currentPartyLocation
        );
    }

    public SaveState withDungeonRowAndColChanged(int dungeonRowDelta, int dungeonColDelta) {
        return new SaveState(
                counter,
                moves,
                player0,
                player1,
                player2,
                player3,
                player4,
                player5,
                player6,
                player7,
                food,
                gold,
                honesty,
                compassion,
                valour,
                justice,
                sacrifice,
                honour,
                spirituality,
                humility,
                torches,
                gems,
                keys,
                sextants,
                armourUnused,
                armourCloths,
                armourLeathers,
                armourChains,
                armourPlates,
                armourMagicChains,
                armourMagicPlates,
                armourMysticRobes,
                weaponUnused,
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
                tremour,
                undead,
                view,
                winds,
                xIt,
                yUp,
                zDown,
                skull,
                skullDestroyed,
                candle,
                book,
                bell,
                keyPartC,
                keyPartL,
                keyPartT,
                horn,
                wheel,
                candleUsed,
                bookUsed,
                bellUsed,
                unused1,
                unused2,
                unused3,
                col,
                row,
                blueStone,
                yellowStone,
                redStone,
                greenStone,
                orangeStone,
                purpleStone,
                whiteStone,
                blackStone,
                runeHonesty,
                runCompassion,
                runeValour,
                runeJustice,
                runeSacrifice,
                runeHonour,
                runeSpirituality,
                runeHumility,
                numberOfCharacters,
                transportation,
                balloonStatus,
                torchDuration,
                phaseOfTrammel,
                phaseOfFelucca,
                hullIntegrity,
                introducedToLordBritish,
                lastSuccessfulCamp,
                lastMandrakeNightshadeFind,
                lastSuccessfulMeditation,
                lastVirtueRelatedConversation,
                dungeonCol + dungeonColDelta,
                dungeonRow + dungeonRowDelta,
                orientationInDungeon,
                dungeonLevel,
                currentPartyLocation
        );
    }

    public SaveState withDungeonLevel(int dungeonLevel) {
        return new SaveState(
                counter,
                moves,
                player0,
                player1,
                player2,
                player3,
                player4,
                player5,
                player6,
                player7,
                food,
                gold,
                honesty,
                compassion,
                valour,
                justice,
                sacrifice,
                honour,
                spirituality,
                humility,
                torches,
                gems,
                keys,
                sextants,
                armourUnused,
                armourCloths,
                armourLeathers,
                armourChains,
                armourPlates,
                armourMagicChains,
                armourMagicPlates,
                armourMysticRobes,
                weaponUnused,
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
                tremour,
                undead,
                view,
                winds,
                xIt,
                yUp,
                zDown,
                skull,
                skullDestroyed,
                candle,
                book,
                bell,
                keyPartC,
                keyPartL,
                keyPartT,
                horn,
                wheel,
                candleUsed,
                bookUsed,
                bellUsed,
                unused1,
                unused2,
                unused3,
                col,
                row,
                blueStone,
                yellowStone,
                redStone,
                greenStone,
                orangeStone,
                purpleStone,
                whiteStone,
                blackStone,
                runeHonesty,
                runCompassion,
                runeValour,
                runeJustice,
                runeSacrifice,
                runeHonour,
                runeSpirituality,
                runeHumility,
                numberOfCharacters,
                transportation,
                balloonStatus,
                torchDuration,
                phaseOfTrammel,
                phaseOfFelucca,
                hullIntegrity,
                introducedToLordBritish,
                lastSuccessfulCamp,
                lastMandrakeNightshadeFind,
                lastSuccessfulMeditation,
                lastVirtueRelatedConversation,
                dungeonCol,
                dungeonRow,
                orientationInDungeon,
                dungeonLevel,
                currentPartyLocation
        );
    }

    public SaveState withCurrentPartyLocation(Location currentPartyLocation) {
        return new SaveState(
                counter,
                moves,
                player0,
                player1,
                player2,
                player3,
                player4,
                player5,
                player6,
                player7,
                food,
                gold,
                honesty,
                compassion,
                valour,
                justice,
                sacrifice,
                honour,
                spirituality,
                humility,
                torches,
                gems,
                keys,
                sextants,
                armourUnused,
                armourCloths,
                armourLeathers,
                armourChains,
                armourPlates,
                armourMagicChains,
                armourMagicPlates,
                armourMysticRobes,
                weaponUnused,
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
                tremour,
                undead,
                view,
                winds,
                xIt,
                yUp,
                zDown,
                skull,
                skullDestroyed,
                candle,
                book,
                bell,
                keyPartC,
                keyPartL,
                keyPartT,
                horn,
                wheel,
                candleUsed,
                bookUsed,
                bellUsed,
                unused1,
                unused2,
                unused3,
                col,
                row,
                blueStone,
                yellowStone,
                redStone,
                greenStone,
                orangeStone,
                purpleStone,
                whiteStone,
                blackStone,
                runeHonesty,
                runCompassion,
                runeValour,
                runeJustice,
                runeSacrifice,
                runeHonour,
                runeSpirituality,
                runeHumility,
                numberOfCharacters,
                transportation,
                balloonStatus,
                torchDuration,
                phaseOfTrammel,
                phaseOfFelucca,
                hullIntegrity,
                introducedToLordBritish,
                lastSuccessfulCamp,
                lastMandrakeNightshadeFind,
                lastSuccessfulMeditation,
                lastVirtueRelatedConversation,
                dungeonCol,
                dungeonRow,
                orientationInDungeon,
                dungeonLevel,
                currentPartyLocation
        );
    }
}
