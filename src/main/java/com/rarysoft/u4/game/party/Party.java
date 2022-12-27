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

public class Party {
    private int counter;
    private int moves;
    private Character player0;
    private Character player1;
    private Character player2;
    private Character player3;
    private Character player4;
    private Character player5;
    private Character player6;
    private Character player7;
    private int food;
    private int gold;
    private int honesty;
    private int compassion;
    private int valour;
    private int justice;
    private int sacrifice;
    private int honour;
    private int spirituality;
    private int humility;
    private int torches;
    private int gems;
    private int keys;
    private int sextants;
    private int armourUnused;
    private int armourCloths;
    private int armourLeathers;
    private int armourChains;
    private int armourPlates;
    private int armourMagicChains;
    private int armourMagicPlates;
    private int armourMysticRobes;
    private int weaponUnused;
    private int weaponStaves;
    private int weaponDaggers;
    private int weaponSlings;
    private int weaponMaces;
    private int weaponAxes;
    private int weaponSwords;
    private int weaponBows;
    private int weaponCrossbows;
    private int weaponFlamingOils;
    private int weaponHalberds;
    private int weaponMagicAxes;
    private int weaponMagicSwords;
    private int weaponMagicBows;
    private int weaponMagicWands;
    private int weaponMysticSwords;
    private int sulfurousAsh;
    private int ginseng;
    private int garlic;
    private int spiderSilk;
    private int bloodMoss;
    private int blackPearl;
    private int nightshade;
    private int mandrake;
    private int awaken;
    private int blink;
    private int cure;
    private int dispel;
    private int energy;
    private int fireball;
    private int gate;
    private int heal;
    private int iceball;
    private int jinx;
    private int kill;
    private int light;
    private int magicMissile;
    private int negate;
    private int open;
    private int protection;
    private int quickness;
    private int resurrect;
    private int sleep;
    private int tremour;
    private int undead;
    private int view;
    private int winds;
    private int xIt;
    private int yUp;
    private int zDown;
    private boolean skull;
    private boolean skullDestroyed;
    private boolean candle;
    private boolean book;
    private boolean bell;
    private boolean keyPartC;
    private boolean keyPartL;
    private boolean keyPartT;
    private boolean horn;
    private boolean wheel;
    private boolean candleUsed;
    private boolean bookUsed;
    private boolean bellUsed;
    private boolean unused1;
    private boolean unused2;
    private boolean unused3;
    private int col;
    private int row;
    private boolean blueStone;
    private boolean yellowStone;
    private boolean redStone;
    private boolean greenStone;
    private boolean orangeStone;
    private boolean purpleStone;
    private boolean whiteStone;
    private boolean blackStone;
    private boolean runeHonesty;
    private boolean runCompassion;
    private boolean runeValour;
    private boolean runeJustice;
    private boolean runeSacrifice;
    private boolean runeHonour;
    private boolean runeSpirituality;
    private boolean runeHumility;
    private int numberOfCharacters;
    private Transportation transportation;
    private BalloonStatus balloonStatus;
    private int torchDuration;
    private int phaseOfTrammel;
    private int phaseOfFelucca;
    private int hullIntegrity;
    private boolean introducedToLordBritish;
    private int lastSuccessfulCamp;
    private int lastMandrakeNightshadeFind;
    private int lastSuccessfulMeditation;
    private int lastVirtueRelatedConversation;
    private int dungeonCol;
    private int dungeonRow;
    private int orientationInDungeon;
    private int dungeonLevel;
    private Location currentPartyLocation;

    public Party(int counter, int moves, Character player0, Character player1, Character player2, Character player3, Character player4, Character player5, Character player6, Character player7, int food, int gold, int honesty, int compassion, int valour, int justice, int sacrifice, int honour, int spirituality, int humility, int torches, int gems, int keys, int sextants, int armourUnused, int armourCloths, int armourLeathers, int armourChains, int armourPlates, int armourMagicChains, int armourMagicPlates, int armourMysticRobes, int weaponUnused, int weaponStaves, int weaponDaggers, int weaponSlings, int weaponMaces, int weaponAxes, int weaponSwords, int weaponBows, int weaponCrossbows, int weaponFlamingOils, int weaponHalberds, int weaponMagicAxes, int weaponMagicSwords, int weaponMagicBows, int weaponMagicWands, int weaponMysticSwords, int sulfurousAsh, int ginseng, int garlic, int spiderSilk, int bloodMoss, int blackPearl, int nightshade, int mandrake, int awaken, int blink, int cure, int dispel, int energy, int fireball, int gate, int heal, int iceball, int jinx, int kill, int light, int magicMissile, int negate, int open, int protection, int quickness, int resurrect, int sleep, int tremour, int undead, int view, int winds, int xIt, int yUp, int zDown, boolean skull, boolean skullDestroyed, boolean candle, boolean book, boolean bell, boolean keyPartC, boolean keyPartL, boolean keyPartT, boolean horn, boolean wheel, boolean candleUsed, boolean bookUsed, boolean bellUsed, boolean unused1, boolean unused2, boolean unused3, int col, int row, boolean blueStone, boolean yellowStone, boolean redStone, boolean greenStone, boolean orangeStone, boolean purpleStone, boolean whiteStone, boolean blackStone, boolean runeHonesty, boolean runCompassion, boolean runeValour, boolean runeJustice, boolean runeSacrifice, boolean runeHonour, boolean runeSpirituality, boolean runeHumility, int numberOfCharacters, Transportation transportation, BalloonStatus balloonStatus, int torchDuration, int phaseOfTrammel, int phaseOfFelucca, int hullIntegrity, boolean introducedToLordBritish, int lastSuccessfulCamp, int lastMandrakeNightshadeFind, int lastSuccessfulMeditation, int lastVirtueRelatedConversation, int dungeonCol, int dungeonRow, int orientationInDungeon, int dungeonLevel, Location currentPartyLocation) {
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

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public Character getPlayer0() {
        return player0;
    }

    public void setPlayer0(Character player0) {
        this.player0 = player0;
    }

    public Character getPlayer1() {
        return player1;
    }

    public void setPlayer1(Character player1) {
        this.player1 = player1;
    }

    public Character getPlayer2() {
        return player2;
    }

    public void setPlayer2(Character player2) {
        this.player2 = player2;
    }

    public Character getPlayer3() {
        return player3;
    }

    public void setPlayer3(Character player3) {
        this.player3 = player3;
    }

    public Character getPlayer4() {
        return player4;
    }

    public void setPlayer4(Character player4) {
        this.player4 = player4;
    }

    public Character getPlayer5() {
        return player5;
    }

    public void setPlayer5(Character player5) {
        this.player5 = player5;
    }

    public Character getPlayer6() {
        return player6;
    }

    public void setPlayer6(Character player6) {
        this.player6 = player6;
    }

    public Character getPlayer7() {
        return player7;
    }

    public void setPlayer7(Character player7) {
        this.player7 = player7;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getHonesty() {
        return honesty;
    }

    public void setHonesty(int honesty) {
        this.honesty = honesty;
    }

    public int getCompassion() {
        return compassion;
    }

    public void setCompassion(int compassion) {
        this.compassion = compassion;
    }

    public int getValour() {
        return valour;
    }

    public void setValour(int valour) {
        this.valour = valour;
    }

    public int getJustice() {
        return justice;
    }

    public void setJustice(int justice) {
        this.justice = justice;
    }

    public int getSacrifice() {
        return sacrifice;
    }

    public void setSacrifice(int sacrifice) {
        this.sacrifice = sacrifice;
    }

    public int getHonour() {
        return honour;
    }

    public void setHonour(int honour) {
        this.honour = honour;
    }

    public int getSpirituality() {
        return spirituality;
    }

    public void setSpirituality(int spirituality) {
        this.spirituality = spirituality;
    }

    public int getHumility() {
        return humility;
    }

    public void setHumility(int humility) {
        this.humility = humility;
    }

    public int getTorches() {
        return torches;
    }

    public void setTorches(int torches) {
        this.torches = torches;
    }

    public int getGems() {
        return gems;
    }

    public void setGems(int gems) {
        this.gems = gems;
    }

    public int getKeys() {
        return keys;
    }

    public void setKeys(int keys) {
        this.keys = keys;
    }

    public int getSextants() {
        return sextants;
    }

    public void setSextants(int sextants) {
        this.sextants = sextants;
    }

    public int getArmourNone() {
        return armourUnused;
    }

    public void setArmourUnused(int armourUnused) {
        this.armourUnused = armourUnused;
    }

    public int getArmourCloths() {
        return armourCloths;
    }

    public void setArmourCloths(int armourCloths) {
        this.armourCloths = armourCloths;
    }

    public int getArmourLeathers() {
        return armourLeathers;
    }

    public void setArmourLeathers(int armourLeathers) {
        this.armourLeathers = armourLeathers;
    }

    public int getArmourChains() {
        return armourChains;
    }

    public void setArmourChains(int armourChains) {
        this.armourChains = armourChains;
    }

    public int getArmourPlates() {
        return armourPlates;
    }

    public void setArmourPlates(int armourPlates) {
        this.armourPlates = armourPlates;
    }

    public int getArmourMagicChains() {
        return armourMagicChains;
    }

    public void setArmourMagicChains(int armourMagicChains) {
        this.armourMagicChains = armourMagicChains;
    }

    public int getArmourMagicPlates() {
        return armourMagicPlates;
    }

    public void setArmourMagicPlates(int armourMagicPlates) {
        this.armourMagicPlates = armourMagicPlates;
    }

    public int getArmourMysticRobes() {
        return armourMysticRobes;
    }

    public void setArmourMysticRobes(int armourMysticRobes) {
        this.armourMysticRobes = armourMysticRobes;
    }

    public int getWeaponNone() {
        return weaponUnused;
    }

    public void setWeaponUnused(int weaponUnused) {
        this.weaponUnused = weaponUnused;
    }

    public int getWeaponStaves() {
        return weaponStaves;
    }

    public void setWeaponStaves(int weaponStaves) {
        this.weaponStaves = weaponStaves;
    }

    public int getWeaponDaggers() {
        return weaponDaggers;
    }

    public void setWeaponDaggers(int weaponDaggers) {
        this.weaponDaggers = weaponDaggers;
    }

    public int getWeaponSlings() {
        return weaponSlings;
    }

    public void setWeaponSlings(int weaponSlings) {
        this.weaponSlings = weaponSlings;
    }

    public int getWeaponMaces() {
        return weaponMaces;
    }

    public void setWeaponMaces(int weaponMaces) {
        this.weaponMaces = weaponMaces;
    }

    public int getWeaponAxes() {
        return weaponAxes;
    }

    public void setWeaponAxes(int weaponAxes) {
        this.weaponAxes = weaponAxes;
    }

    public int getWeaponSwords() {
        return weaponSwords;
    }

    public void setWeaponSwords(int weaponSwords) {
        this.weaponSwords = weaponSwords;
    }

    public int getWeaponBows() {
        return weaponBows;
    }

    public void setWeaponBows(int weaponBows) {
        this.weaponBows = weaponBows;
    }

    public int getWeaponCrossbows() {
        return weaponCrossbows;
    }

    public void setWeaponCrossbows(int weaponCrossbows) {
        this.weaponCrossbows = weaponCrossbows;
    }

    public int getWeaponFlamingOils() {
        return weaponFlamingOils;
    }

    public void setWeaponFlamingOils(int weaponFlamingOils) {
        this.weaponFlamingOils = weaponFlamingOils;
    }

    public int getWeaponHalberds() {
        return weaponHalberds;
    }

    public void setWeaponHalberds(int weaponHalberds) {
        this.weaponHalberds = weaponHalberds;
    }

    public int getWeaponMagicAxes() {
        return weaponMagicAxes;
    }

    public void setWeaponMagicAxes(int weaponMagicAxes) {
        this.weaponMagicAxes = weaponMagicAxes;
    }

    public int getWeaponMagicSwords() {
        return weaponMagicSwords;
    }

    public void setWeaponMagicSwords(int weaponMagicSwords) {
        this.weaponMagicSwords = weaponMagicSwords;
    }

    public int getWeaponMagicBows() {
        return weaponMagicBows;
    }

    public void setWeaponMagicBows(int weaponMagicBows) {
        this.weaponMagicBows = weaponMagicBows;
    }

    public int getWeaponMagicWands() {
        return weaponMagicWands;
    }

    public void setWeaponMagicWands(int weaponMagicWands) {
        this.weaponMagicWands = weaponMagicWands;
    }

    public int getWeaponMysticSwords() {
        return weaponMysticSwords;
    }

    public void setWeaponMysticSwords(int weaponMysticSwords) {
        this.weaponMysticSwords = weaponMysticSwords;
    }

    public int getSulfurousAsh() {
        return sulfurousAsh;
    }

    public void setSulfurousAsh(int sulfurousAsh) {
        this.sulfurousAsh = sulfurousAsh;
    }

    public int getGinseng() {
        return ginseng;
    }

    public void setGinseng(int ginseng) {
        this.ginseng = ginseng;
    }

    public int getGarlic() {
        return garlic;
    }

    public void setGarlic(int garlic) {
        this.garlic = garlic;
    }

    public int getSpiderSilk() {
        return spiderSilk;
    }

    public void setSpiderSilk(int spiderSilk) {
        this.spiderSilk = spiderSilk;
    }

    public int getBloodMoss() {
        return bloodMoss;
    }

    public void setBloodMoss(int bloodMoss) {
        this.bloodMoss = bloodMoss;
    }

    public int getBlackPearl() {
        return blackPearl;
    }

    public void setBlackPearl(int blackPearl) {
        this.blackPearl = blackPearl;
    }

    public int getNightshade() {
        return nightshade;
    }

    public void setNightshade(int nightshade) {
        this.nightshade = nightshade;
    }

    public int getMandrake() {
        return mandrake;
    }

    public void setMandrake(int mandrake) {
        this.mandrake = mandrake;
    }

    public int getAwaken() {
        return awaken;
    }

    public void setAwaken(int awaken) {
        this.awaken = awaken;
    }

    public int getBlink() {
        return blink;
    }

    public void setBlink(int blink) {
        this.blink = blink;
    }

    public int getCure() {
        return cure;
    }

    public void setCure(int cure) {
        this.cure = cure;
    }

    public int getDispel() {
        return dispel;
    }

    public void setDispel(int dispel) {
        this.dispel = dispel;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getFireball() {
        return fireball;
    }

    public void setFireball(int fireball) {
        this.fireball = fireball;
    }

    public int getGate() {
        return gate;
    }

    public void setGate(int gate) {
        this.gate = gate;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public int getIceball() {
        return iceball;
    }

    public void setIceball(int iceball) {
        this.iceball = iceball;
    }

    public int getJinx() {
        return jinx;
    }

    public void setJinx(int jinx) {
        this.jinx = jinx;
    }

    public int getKill() {
        return kill;
    }

    public void setKill(int kill) {
        this.kill = kill;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public int getMagicMissile() {
        return magicMissile;
    }

    public void setMagicMissile(int magicMissile) {
        this.magicMissile = magicMissile;
    }

    public int getNegate() {
        return negate;
    }

    public void setNegate(int negate) {
        this.negate = negate;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }

    public int getQuickness() {
        return quickness;
    }

    public void setQuickness(int quickness) {
        this.quickness = quickness;
    }

    public int getResurrect() {
        return resurrect;
    }

    public void setResurrect(int resurrect) {
        this.resurrect = resurrect;
    }

    public int getSleep() {
        return sleep;
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }

    public int getTremor() {
        return tremour;
    }

    public void setTremour(int tremour) {
        this.tremour = tremour;
    }

    public int getUndead() {
        return undead;
    }

    public void setUndead(int undead) {
        this.undead = undead;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getWinds() {
        return winds;
    }

    public void setWinds(int winds) {
        this.winds = winds;
    }

    public int getxIt() {
        return xIt;
    }

    public void setxIt(int xIt) {
        this.xIt = xIt;
    }

    public int getYUp() {
        return yUp;
    }

    public void setyUp(int yUp) {
        this.yUp = yUp;
    }

    public int getZDown() {
        return zDown;
    }

    public void setzDown(int zDown) {
        this.zDown = zDown;
    }

    public boolean isSkull() {
        return skull;
    }

    public void setSkull(boolean skull) {
        this.skull = skull;
    }

    public boolean isSkullDestroyed() {
        return skullDestroyed;
    }

    public void setSkullDestroyed(boolean skullDestroyed) {
        this.skullDestroyed = skullDestroyed;
    }

    public boolean isCandle() {
        return candle;
    }

    public void setCandle(boolean candle) {
        this.candle = candle;
    }

    public boolean isBook() {
        return book;
    }

    public void setBook(boolean book) {
        this.book = book;
    }

    public boolean isBell() {
        return bell;
    }

    public void setBell(boolean bell) {
        this.bell = bell;
    }

    public boolean isKeyPartC() {
        return keyPartC;
    }

    public void setKeyPartC(boolean keyPartC) {
        this.keyPartC = keyPartC;
    }

    public boolean isKeyPartL() {
        return keyPartL;
    }

    public void setKeyPartL(boolean keyPartL) {
        this.keyPartL = keyPartL;
    }

    public boolean isKeyPartT() {
        return keyPartT;
    }

    public void setKeyPartT(boolean keyPartT) {
        this.keyPartT = keyPartT;
    }

    public boolean isHorn() {
        return horn;
    }

    public void setHorn(boolean horn) {
        this.horn = horn;
    }

    public boolean isWheel() {
        return wheel;
    }

    public void setWheel(boolean wheel) {
        this.wheel = wheel;
    }

    public boolean isCandleUsed() {
        return candleUsed;
    }

    public void setCandleUsed(boolean candleUsed) {
        this.candleUsed = candleUsed;
    }

    public boolean isBookUsed() {
        return bookUsed;
    }

    public void setBookUsed(boolean bookUsed) {
        this.bookUsed = bookUsed;
    }

    public boolean isBellUsed() {
        return bellUsed;
    }

    public void setBellUsed(boolean bellUsed) {
        this.bellUsed = bellUsed;
    }

    public boolean isUnused1() {
        return unused1;
    }

    public void setUnused1(boolean unused1) {
        this.unused1 = unused1;
    }

    public boolean isUnused2() {
        return unused2;
    }

    public void setUnused2(boolean unused2) {
        this.unused2 = unused2;
    }

    public boolean isUnused3() {
        return unused3;
    }

    public void setUnused3(boolean unused3) {
        this.unused3 = unused3;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean isBlueStone() {
        return blueStone;
    }

    public void setBlueStone(boolean blueStone) {
        this.blueStone = blueStone;
    }

    public boolean isYellowStone() {
        return yellowStone;
    }

    public void setYellowStone(boolean yellowStone) {
        this.yellowStone = yellowStone;
    }

    public boolean isRedStone() {
        return redStone;
    }

    public void setRedStone(boolean redStone) {
        this.redStone = redStone;
    }

    public boolean isGreenStone() {
        return greenStone;
    }

    public void setGreenStone(boolean greenStone) {
        this.greenStone = greenStone;
    }

    public boolean isOrangeStone() {
        return orangeStone;
    }

    public void setOrangeStone(boolean orangeStone) {
        this.orangeStone = orangeStone;
    }

    public boolean isPurpleStone() {
        return purpleStone;
    }

    public void setPurpleStone(boolean purpleStone) {
        this.purpleStone = purpleStone;
    }

    public boolean isWhiteStone() {
        return whiteStone;
    }

    public void setWhiteStone(boolean whiteStone) {
        this.whiteStone = whiteStone;
    }

    public boolean isBlackStone() {
        return blackStone;
    }

    public void setBlackStone(boolean blackStone) {
        this.blackStone = blackStone;
    }

    public boolean isRuneHonesty() {
        return runeHonesty;
    }

    public void setRuneHonesty(boolean runeHonesty) {
        this.runeHonesty = runeHonesty;
    }

    public boolean isRunCompassion() {
        return runCompassion;
    }

    public void setRunCompassion(boolean runCompassion) {
        this.runCompassion = runCompassion;
    }

    public boolean isRuneValour() {
        return runeValour;
    }

    public void setRuneValour(boolean runeValour) {
        this.runeValour = runeValour;
    }

    public boolean isRuneJustice() {
        return runeJustice;
    }

    public void setRuneJustice(boolean runeJustice) {
        this.runeJustice = runeJustice;
    }

    public boolean isRuneSacrifice() {
        return runeSacrifice;
    }

    public void setRuneSacrifice(boolean runeSacrifice) {
        this.runeSacrifice = runeSacrifice;
    }

    public boolean isRuneHonour() {
        return runeHonour;
    }

    public void setRuneHonour(boolean runeHonour) {
        this.runeHonour = runeHonour;
    }

    public boolean isRuneSpirituality() {
        return runeSpirituality;
    }

    public void setRuneSpirituality(boolean runeSpirituality) {
        this.runeSpirituality = runeSpirituality;
    }

    public boolean isRuneHumility() {
        return runeHumility;
    }

    public void setRuneHumility(boolean runeHumility) {
        this.runeHumility = runeHumility;
    }

    public int getNumberOfCharacters() {
        return numberOfCharacters;
    }

    public void setNumberOfCharacters(int numberOfCharacters) {
        this.numberOfCharacters = numberOfCharacters;
    }

    public Transportation getTransportation() {
        return transportation;
    }

    public void setTransportation(Transportation transportation) {
        this.transportation = transportation;
    }

    public BalloonStatus getBalloonStatus() {
        return balloonStatus;
    }

    public void setBalloonStatus(BalloonStatus balloonStatus) {
        this.balloonStatus = balloonStatus;
    }

    public int getTorchDuration() {
        return torchDuration;
    }

    public void setTorchDuration(int torchDuration) {
        this.torchDuration = torchDuration;
    }

    public int getPhaseOfTrammel() {
        return phaseOfTrammel;
    }

    public void setPhaseOfTrammel(int phaseOfTrammel) {
        this.phaseOfTrammel = phaseOfTrammel;
    }

    public int getPhaseOfFelucca() {
        return phaseOfFelucca;
    }

    public void setPhaseOfFelucca(int phaseOfFelucca) {
        this.phaseOfFelucca = phaseOfFelucca;
    }

    public int getHullIntegrity() {
        return hullIntegrity;
    }

    public void setHullIntegrity(int hullIntegrity) {
        this.hullIntegrity = hullIntegrity;
    }

    public boolean isIntroducedToLordBritish() {
        return introducedToLordBritish;
    }

    public void setIntroducedToLordBritish(boolean introducedToLordBritish) {
        this.introducedToLordBritish = introducedToLordBritish;
    }

    public int getLastSuccessfulCamp() {
        return lastSuccessfulCamp;
    }

    public void setLastSuccessfulCamp(int lastSuccessfulCamp) {
        this.lastSuccessfulCamp = lastSuccessfulCamp;
    }

    public int getLastMandrakeNightshadeFind() {
        return lastMandrakeNightshadeFind;
    }

    public void setLastMandrakeNightshadeFind(int lastMandrakeNightshadeFind) {
        this.lastMandrakeNightshadeFind = lastMandrakeNightshadeFind;
    }

    public int getLastSuccessfulMeditation() {
        return lastSuccessfulMeditation;
    }

    public void setLastSuccessfulMeditation(int lastSuccessfulMeditation) {
        this.lastSuccessfulMeditation = lastSuccessfulMeditation;
    }

    public int getLastVirtueRelatedConversation() {
        return lastVirtueRelatedConversation;
    }

    public void setLastVirtueRelatedConversation(int lastVirtueRelatedConversation) {
        this.lastVirtueRelatedConversation = lastVirtueRelatedConversation;
    }

    public int getDungeonCol() {
        return dungeonCol;
    }

    public void setDungeonCol(int dungeonCol) {
        this.dungeonCol = dungeonCol;
    }

    public int getDungeonRow() {
        return dungeonRow;
    }

    public void setDungeonRow(int dungeonRow) {
        this.dungeonRow = dungeonRow;
    }

    public int getOrientationInDungeon() {
        return orientationInDungeon;
    }

    public void setOrientationInDungeon(int orientationInDungeon) {
        this.orientationInDungeon = orientationInDungeon;
    }

    public int getDungeonLevel() {
        return dungeonLevel;
    }

    public void setDungeonLevel(int dungeonLevel) {
        this.dungeonLevel = dungeonLevel;
    }

    public Location getCurrentPartyLocation() {
        return currentPartyLocation;
    }

    public void setCurrentPartyLocation(Location currentPartyLocation) {
        this.currentPartyLocation = currentPartyLocation;
    }
}
