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

import com.rarysoft.u4.game.Door;
import com.rarysoft.u4.game.RenderedTile;
import com.rarysoft.u4.game.npc.Dialog;
import com.rarysoft.u4.game.npc.Person;
import com.rarysoft.u4.game.model.*;
import com.rarysoft.u4.game.model.Character;

import java.util.List;
import java.util.Set;

public final class GameState {
    private final SaveState saveState;
    private final SaveStateExtended saveStateExtended;

    public GameState(SaveState saveState) {
        this.saveState = saveState;
        this.saveStateExtended = new SaveStateExtended();
    }

    private GameState(SaveState saveState, SaveStateExtended saveStateExtended) {
        this.saveState = saveState;
        this.saveStateExtended = saveStateExtended;
    }

    public SaveState getSaveState() {
        return saveState;
    }

    public SaveStateExtended getSaveStateExtended() {
        return saveStateExtended;
    }

    public int getCounter() {
        return saveState.getCounter();
    }

    public int getMoves() {
        return saveState.getMoves();
    }

    public Character getPlayer0() {
        return saveState.getPlayer0();
    }

    public Character getPlayer1() {
        return saveState.getPlayer1();
    }

    public Character getPlayer2() {
        return saveState.getPlayer2();
    }

    public Character getPlayer3() {
        return saveState.getPlayer3();
    }

    public Character getPlayer4() {
        return saveState.getPlayer4();
    }

    public Character getPlayer5() {
        return saveState.getPlayer5();
    }

    public Character getPlayer6() {
        return saveState.getPlayer6();
    }

    public Character getPlayer7() {
        return saveState.getPlayer7();
    }

    public int getFood() {
        return saveState.getFood();
    }

    public int getGold() {
        return saveState.getGold();
    }

    public int getHonesty() {
        return saveState.getHonesty();
    }

    public int getCompassion() {
        return saveState.getCompassion();
    }

    public int getValour() {
        return saveState.getValour();
    }

    public int getJustice() {
        return saveState.getJustice();
    }

    public int getSacrifice() {
        return saveState.getSacrifice();
    }

    public int getHonour() {
        return saveState.getHonour();
    }

    public int getSpirituality() {
        return saveState.getSpirituality();
    }

    public int getHumility() {
        return saveState.getHumility();
    }

    public int getTorches() {
        return saveState.getTorches();
    }

    public int getGems() {
        return saveState.getGems();
    }

    public int getKeys() {
        return saveState.getKeys();
    }

    public int getSextants() {
        return saveState.getSextants();
    }

    public int getArmourNone() {
        return saveState.getArmourNone();
    }

    public int getArmourCloths() {
        return saveState.getArmourCloths();
    }

    public int getArmourLeathers() {
        return saveState.getArmourLeathers();
    }

    public int getArmourChains() {
        return saveState.getArmourChains();
    }

    public int getArmourPlates() {
        return saveState.getArmourPlates();
    }

    public int getArmourMagicChains() {
        return saveState.getArmourMagicChains();
    }

    public int getArmourMagicPlates() {
        return saveState.getArmourMagicPlates();
    }

    public int getArmourMysticRobes() {
        return saveState.getArmourMysticRobes();
    }

    public int getWeaponNone() {
        return saveState.getWeaponNone();
    }

    public int getWeaponStaves() {
        return saveState.getWeaponStaves();
    }

    public int getWeaponDaggers() {
        return saveState.getWeaponDaggers();
    }

    public int getWeaponSlings() {
        return saveState.getWeaponSlings();
    }

    public int getWeaponMaces() {
        return saveState.getWeaponMaces();
    }

    public int getWeaponAxes() {
        return saveState.getWeaponAxes();
    }

    public int getWeaponSwords() {
        return saveState.getWeaponSwords();
    }

    public int getWeaponBows() {
        return saveState.getWeaponBows();
    }

    public int getWeaponCrossbows() {
        return saveState.getWeaponCrossbows();
    }

    public int getWeaponFlamingOils() {
        return saveState.getWeaponFlamingOils();
    }

    public int getWeaponHalberds() {
        return saveState.getWeaponHalberds();
    }

    public int getWeaponMagicAxes() {
        return saveState.getWeaponMagicAxes();
    }

    public int getWeaponMagicSwords() {
        return saveState.getWeaponMagicSwords();
    }

    public int getWeaponMagicBows() {
        return saveState.getWeaponMagicBows();
    }

    public int getWeaponMagicWands() {
        return saveState.getWeaponMagicWands();
    }

    public int getWeaponMysticSwords() {
        return saveState.getWeaponMysticSwords();
    }

    public int getSulfurousAsh() {
        return saveState.getSulfurousAsh();
    }

    public int getGinseng() {
        return saveState.getGinseng();
    }

    public int getGarlic() {
        return saveState.getGarlic();
    }

    public int getSpiderSilk() {
        return saveState.getSpiderSilk();
    }

    public int getBloodMoss() {
        return saveState.getBloodMoss();
    }

    public int getBlackPearl() {
        return saveState.getBlackPearl();
    }

    public int getNightshade() {
        return saveState.getNightshade();
    }

    public int getMandrake() {
        return saveState.getMandrake();
    }

    public int getAwaken() {
        return saveState.getAwaken();
    }

    public int getBlink() {
        return saveState.getBlink();
    }

    public int getCure() {
        return saveState.getCure();
    }

    public int getDispel() {
        return saveState.getDispel();
    }

    public int getEnergy() {
        return saveState.getEnergy();
    }

    public int getFireball() {
        return saveState.getFireball();
    }

    public int getGate() {
        return saveState.getGate();
    }

    public int getHeal() {
        return saveState.getHeal();
    }

    public int getIceball() {
        return saveState.getIceball();
    }

    public int getJinx() {
        return saveState.getJinx();
    }

    public int getKill() {
        return saveState.getKill();
    }

    public int getLight() {
        return saveState.getLight();
    }

    public int getMagicMissile() {
        return saveState.getMagicMissile();
    }

    public int getNegate() {
        return saveState.getNegate();
    }

    public int getOpen() {
        return saveState.getOpen();
    }

    public int getProtection() {
        return saveState.getProtection();
    }

    public int getQuickness() {
        return saveState.getQuickness();
    }

    public int getResurrect() {
        return saveState.getResurrect();
    }

    public int getSleep() {
        return saveState.getSleep();
    }

    public int getTremor() {
        return saveState.getTremour();
    }

    public int getUndead() {
        return saveState.getUndead();
    }

    public int getView() {
        return saveState.getView();
    }

    public int getWinds() {
        return saveState.getWinds();
    }

    public int getXIt() {
        return saveState.getXIt();
    }

    public int getYUp() {
        return saveState.getYUp();
    }

    public int getZDown() {
        return saveState.getZDown();
    }

    public boolean hasSkull() {
        return saveState.hasSkull();
    }

    public boolean isSkullDestroyed() {
        return saveState.isSkullDestroyed();
    }

    public boolean hasCandle() {
        return saveState.hasCandle();
    }

    public boolean hasBook() {
        return saveState.hasBook();
    }

    public boolean hasBell() {
        return saveState.hasBell();
    }

    public boolean hasKeyPartC() {
        return saveState.hasKeyPartC();
    }

    public boolean hasKeyPartL() {
        return saveState.hasKeyPartL();
    }

    public boolean hasKeyPartT() {
        return saveState.hasKeyPartT();
    }

    public boolean hasHorn() {
        return saveState.hasHorn();
    }

    public boolean hasWheel() {
        return saveState.hasWheel();
    }

    public boolean isCandleUsed() {
        return saveState.isCandleUsed();
    }

    public boolean isBookUsed() {
        return saveState.isBookUsed();
    }

    public boolean isBellUsed() {
        return saveState.isBellUsed();
    }

    public boolean getUnused1() {
        return saveState.getUnused1();
    }

    public boolean getUnused2() {
        return saveState.getUnused2();
    }

    public boolean getUnused3() {
        return saveState.getUnused3();
    }

    public int getCol() {
        return saveState.getCol();
    }

    public int getRow() {
        return saveState.getRow();
    }

    public boolean hasBlueStone() {
        return saveState.hasBlueStone();
    }

    public boolean hasYellowStone() {
        return saveState.hasYellowStone();
    }

    public boolean hasRedStone() {
        return saveState.hasRedStone();
    }

    public boolean hasGreenStone() {
        return saveState.hasGreenStone();
    }

    public boolean hasOrangeStone() {
        return saveState.hasOrangeStone();
    }

    public boolean hasPurpleStone() {
        return saveState.hasPurpleStone();
    }

    public boolean hasWhiteStone() {
        return saveState.hasWhiteStone();
    }

    public boolean hasBlackStone() {
        return saveState.hasBlackStone();
    }

    public boolean hasRuneHonesty() {
        return saveState.hasRuneHonesty();
    }

    public boolean hasRunCompassion() {
        return saveState.hasRunCompassion();
    }

    public boolean hasRuneValour() {
        return saveState.hasRuneValour();
    }

    public boolean hasRuneJustice() {
        return saveState.hasRuneJustice();
    }

    public boolean hasRuneSacrifice() {
        return saveState.hasRuneSacrifice();
    }

    public boolean hasRuneHonour() {
        return saveState.hasRuneHonour();
    }

    public boolean hasRuneSpirituality() {
        return saveState.hasRuneSpirituality();
    }

    public boolean hasRuneHumility() {
        return saveState.hasRuneHumility();
    }

    public int getNumberOfCharacters() {
        return saveState.getNumberOfCharacters();
    }

    public Transportation getTransportation() {
        return saveState.getTransportation();
    }

    public BalloonStatus getBalloonStatus() {
        return saveState.getBalloonStatus();
    }

    public int getTorchDuration() {
        return saveState.getTorchDuration();
    }

    public int getPhaseOfTrammel() {
        return saveState.getPhaseOfTrammel();
    }

    public int getPhaseOfFelucca() {
        return saveState.getPhaseOfFelucca();
    }

    public int getHullIntegrity() {
        return saveState.getHullIntegrity();
    }

    public boolean isIntroducedToLordBritish() {
        return saveState.isIntroducedToLordBritish();
    }

    public int getLastSuccessfulCamp() {
        return saveState.getLastSuccessfulCamp();
    }

    public int getLastMandrakeNightshadeFind() {
        return saveState.getLastMandrakeNightshadeFind();
    }

    public int getLastSuccessfulMeditation() {
        return saveState.getLastSuccessfulMeditation();
    }

    public int getLastVirtueRelatedConversation() {
        return saveState.getLastVirtueRelatedConversation();
    }

    public int getDungeonCol() {
        return saveState.getDungeonCol();
    }

    public int getDungeonRow() {
        return saveState.getDungeonRow();
    }

    public int getOrientationInDungeon() {
        return saveState.getOrientationInDungeon();
    }

    public int getDungeonLevel() {
        return saveState.getDungeonLevel();
    }

    public Location getCurrentPartyLocation() {
        return saveState.getCurrentPartyLocation();
    }

    public RenderedTile[][] getMapView() {
        return saveStateExtended.getMapView();
    }

    public Dialog getDialog() {
        return saveStateExtended.getDialog();
    }

    public Person getPersonConversingWith() {
        return saveStateExtended.getPersonConversingWith();
    }

    public boolean isRespondingToPerson() {
        return saveStateExtended.isRespondingToPerson();
    }

    public String getInput() {
        return saveStateExtended.getInput();
    }

    public Set<Door> getDoors() {
        return saveStateExtended.getDoors();
    }

    public Door getDoorInteractingWith() {
        return saveStateExtended.getDoorInteractingWith();
    }

    public List<String> getMessages() {
        return saveStateExtended.getMessages();
    }

    public GameState withCounterIncremented() {
        return new GameState(saveState.withCounterIncremented(), saveStateExtended);
    }

    public GameState withMovesIncremented() {
        return new GameState(saveState.withMovesIncremented(), saveStateExtended);
    }

    public GameState withHonestyChanged(int delta) {
        return new GameState(saveState.withHonestyChanged(delta), saveStateExtended);
    }

    public GameState withCompassionChanged(int delta) {
        return new GameState(saveState.withCompassionChanged(delta), saveStateExtended);
    }

    public GameState withValourChanged(int delta) {
        return new GameState(saveState.withValourChanged(delta), saveStateExtended);
    }

    public GameState withJusticeChanged(int delta) {
        return new GameState(saveState.withJusticeChanged(delta), saveStateExtended);
    }

    public GameState withSacrificeChanged(int delta) {
        return new GameState(saveState.withSacrificeChanged(delta), saveStateExtended);
    }

    public GameState withHonourChanged(int delta) {
        return new GameState(saveState.withHonourChanged(delta), saveStateExtended);
    }

    public GameState withSpiritualityChanged(int delta) {
        return new GameState(saveState.withSpiritualityChanged(delta), saveStateExtended);
    }

    public GameState withHumilityChanged(int delta) {
        return new GameState(saveState.withHumilityChanged(delta), saveStateExtended);
    }

    public GameState withKeysChanged(int delta) {
        return new GameState(saveState.withKeysChanged(delta), saveStateExtended);
    }

    public GameState withKeysDecremented() {
        return new GameState(saveState.withKeysDecremented(), saveStateExtended);
    }

    public GameState withWinds(int winds) {
        return new GameState(saveState.withWinds(winds), saveStateExtended);
    }

    public GameState withRowAndCol(int row, int col) {
        return new GameState(saveState.withRowAndCol(row, col), saveStateExtended);
    }

    public GameState withRowAndColChanged(int rowDelta, int colDelta) {
        return new GameState(saveState.withRowAndColChanged(rowDelta, colDelta), saveStateExtended);
    }

    public GameState withRowAndColFromDungeonRowAndCol() {
        return new GameState(saveState.withRowAndCol(saveState.getDungeonRow(), saveState.getDungeonCol()), saveStateExtended);
    }

    public GameState withPhaseOfTrammelCycled(int cycleLength) {
        return new GameState(saveState.withPhaseOfTrammelCycled(cycleLength), saveStateExtended);
    }

    public GameState withPhaseOfFeluccaCycled(int cycleLength) {
        return new GameState(saveState.withPhaseOfFeluccaCycled(cycleLength), saveStateExtended);
    }

    public GameState withDungeonRowAndCol(int dungeonRow, int dungeonCol) {
        return new GameState(saveState.withDungeonRowAndCol(dungeonRow, dungeonCol), saveStateExtended);
    }

    public GameState withDungeonRowAndColChanged(int dungeonRowDelta, int dungeonColDelta) {
        return new GameState(saveState.withDungeonRowAndColChanged(dungeonRowDelta, dungeonColDelta), saveStateExtended);
    }

    public GameState withDungeonRowAndColFromRowAndCol() {
        return new GameState(saveState.withDungeonRowAndCol(saveState.getRow(), saveState.getCol()), saveStateExtended);
    }

    public GameState withDungeonLevel(int dungeonLevel) {
        return new GameState(saveState.withDungeonLevel(dungeonLevel), saveStateExtended);
    }

    public GameState withCurrentPartyLocation(Location currentPartyLocation) {
        return new GameState(saveState.withCurrentPartyLocation(currentPartyLocation), saveStateExtended);
    }

    public GameState withMapView(RenderedTile[][] mapView) {
        return new GameState(saveState, saveStateExtended.withMapView(mapView));
    }

    public GameState withDialog(Dialog dialog) {
        return new GameState(saveState, saveStateExtended.withDialog(dialog));
    }

    public GameState withNoDialog() {
        return new GameState(saveState, saveStateExtended.withNoDialog());
    }

    public GameState withPersonConversingWith(Person personConversingWith) {
        return new GameState(saveState, saveStateExtended.withPersonConversingWith(personConversingWith));
    }

    public GameState withNoPersonConversingWith() {
        return new GameState(saveState, saveStateExtended.withNoPersonConversingWith());
    }

    public GameState withRespondingToPerson() {
        return new GameState(saveState, saveStateExtended.withRespondingToPerson());
    }

    public GameState withNotRespondingToPerson() {
        return new GameState(saveState, saveStateExtended.withNotRespondingToPerson());
    }

    public GameState withInput(String input) {
        return new GameState(saveState, saveStateExtended.withInput(input));
    }

    public GameState withAdditionalInput(char additionalInput) {
        return new GameState(saveState, saveStateExtended.withAdditionalInput(additionalInput));
    }

    public GameState withoutPreviousAdditionalInput() {
        return new GameState(saveState, saveStateExtended.withoutPreviousAdditionalInput());
    }

    public GameState withNoInput() {
        return new GameState(saveState, saveStateExtended.withNoInput());
    }

    public GameState withDoors(Set<Door> doors) {
        return new GameState(saveState, saveStateExtended.withDoors(doors));
    }

    public GameState withNoDoors() {
        return new GameState(saveState, saveStateExtended.withNoDoors());
    }

    public GameState withDoorInteractingWith(Door doorInteractingWith) {
        return new GameState(saveState, saveStateExtended.withDoorInteractingWith(doorInteractingWith));
    }

    public GameState withDoorInteractingWithUnlocked() {
        return new GameState(saveState, saveStateExtended.withDoorInteractingWithUnlocked());
    }

    public GameState withNoDoorInteractingWith() {
        return new GameState(saveState, saveStateExtended.withNoDoorInteractingWith());
    }

    public GameState withMessage(String message) {
        return new GameState(saveState, saveStateExtended.withMessage(message));
    }
}
