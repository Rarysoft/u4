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
package com.rarysoft.u4.game;

import com.rarysoft.u4.PartyLoader;
import com.rarysoft.u4.game.party.*;
import com.rarysoft.u4.game.party.Character;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PartyLoaderTest {
    private final int[] data = {
            0x40, 0xE2, 0x01, 0x00, // Counter (123456)
            0xA6, 0x1A, 0x00, 0x00, // Moves (6822)
            // we skip the bytes for the characters, as that part is handled by CharacterLoader and is mocked here
            0xA3, 0x45, 0x00, 0x00, // Food in hundredths (17827)
            0x2F, 0x07, // Gold (1839)
            0x0A, 0x00, // Honesty (10)
            0x14, 0x00, // Compassion (20)
            0x1E, 0x00, // Valour (30)
            0x28, 0x00, // Justice (40)
            0x32, 0x00, // Sacrifice (50)
            0x3C, 0x00, // Honour (60)
            0x46, 0x00, // Spirituality (70)
            0x50, 0x00, // Humility (80)
            0x2C, 0x01, // Torches (300)
            0x90, 0x01, // Gems (400)
            0xF4, 0x01, // Keys (500)
            0x58, 0x02, // Sextants (600)
            0x00, 0x00, // Armour None (0)
            0x01, 0x00, // Armour Cloth (1)
            0x02, 0x00, // Armour Leather (2)
            0x03, 0x00, // Armour Chain (3)
            0x04, 0x00, // Armour Plate (4)
            0x05, 0x00, // Armour Magic Chain (5)
            0x06, 0x00, // Armour Magic Plate (6)
            0x07, 0x00, // Armour Mystic Robes (7)
            0x08, 0x00, // Weapon None (8)
            0x09, 0x00, // Weapon Staves (9)
            0x0A, 0x00, // Weapon Daggers (10)
            0x0B, 0x00, // Weapon Slings (11)
            0x0C, 0x00, // Weapon Maces (12)
            0x0D, 0x00, // Weapon Axes (13)
            0x0E, 0x00, // Weapon Swords (14)
            0x0F, 0x00, // Weapon Bows (15)
            0x10, 0x00, // Weapon Crossbows (16)
            0x11, 0x00, // Weapon Oil (17)
            0x12, 0x00, // Weapon Halberds (18)
            0x13, 0x00, // Weapon Magic Axes (19)
            0x14, 0x00, // Weapon Magic Swords (20)
            0x15, 0x00, // Weapon Magic Bows (21)
            0x16, 0x00, // Weapon Magic Wands (22)
            0x17, 0x00, // Weapon Mystic Swords (23)
            0x18, 0x00, // Sulfurous Ash (24)
            0x19, 0x00, // Ginseng (25)
            0x1A, 0x00, // Garlic (26)
            0x1B, 0x00, // Spider Silk (27)
            0x1C, 0x00, // Blood Moss (28)
            0x1D, 0x00, // Black Pearl (29)
            0x1E, 0x00, // Nightshade (30)
            0x1F, 0x00, // Mandrake (31)
            0x20, 0x00, // Awaken (32)
            0x21, 0x00, // Blink (33)
            0x22, 0x00, // Cure (34)
            0x23, 0x00, // Dispel (35)
            0x24, 0x00, // Energy (36)
            0x25, 0x00, // Fireball (37)
            0x26, 0x00, // Gate (38)
            0x27, 0x00, // Heal (39)
            0x28, 0x00, // Iceball (40)
            0x29, 0x00, // Jinx (41)
            0x2A, 0x00, // Kill (42)
            0x2B, 0x00, // Light (43)
            0x2C, 0x00, // Magic Missile (44)
            0x2D, 0x00, // Negate (45)
            0x2E, 0x00, // Open (46)
            0x2F, 0x00, // Protection (47)
            0x30, 0x00, // Quickness (48)
            0x31, 0x00, // Resurrect (49)
            0x32, 0x00, // Sleep (50)
            0x33, 0x00, // Tremor (51)
            0x34, 0x00, // Undead (52)
            0x35, 0x00, // View (53)
            0x36, 0x00, // Winds (54)
            0x37, 0x00, // X-it (55)
            0x38, 0x00, // Y-Up (56)
            0x39, 0x00, // Z-Down (57)
            0x00,   // Skull/Skull Destroyed/Candle/Book/Bell/Key Part C/Key Part L/Key Part T
            0x00,   // Horn/Wheel/Candle Used at Abyss Entrance/Book Used at Abyss Entrance/Bell Used at Abyss Entrance/Unused/Unused/Unused
            0x7B,   // X (123)
            0xEA,   // Y (234)
            0x00,   // Blue Stone/Yellow Stone/Red Stone/Green Stone/Orange Stone/Purple Stone/White Stone/Black Stone
            0x00,   // Honesty Rune/Compassion Rune/Valour Rune/Justice Rune/Sacrifice Rune/Honour Rune/Spirituality Rune/Humility Rune
            0x04, 0x00, // Characters in Party (4)
            0x1F, 0x00, // Transportation (On Foot)
            0x01, 0x00, // Balloon Status (In Air)
            0x03, 0x00, // Phase of Trammel (3)
            0x05, 0x00, // Phase of Felucca (5)
            0x7B, 0x00, // Ship Hull Integrity (123)
            0x01, 0x00, // Introduced to Lord British (yes)
            0x39, 0x30, // Last Successful Camp (12345)
            0xA0, 0x5B, // Last Mandrake/Nightshade Find (23456)
            0x07, 0x87, // Last Successful Meditation (34567)
            0x6E, 0xB2, // Last Virtue-Related Conversation (45678)
            0xEA, 0x7B, // Dungeon Coordinates (234, 123)
            0x03, 0x00, // Dungeon Orientation (3-South)
            0x04, 0x00, // Dungeon Level (4)
            0x0E, 0x00  // Current Location (Buccaneer's Den)
    };

    private final InputStream inputStream = new InputStream() {
        private int pointer = 0;

        @Override
        public int read() {
            if (pointer < data.length) {
                int byteRead = data[pointer];
                pointer ++;
                return byteRead;
            }
            return -1;
        }
    };

    @Mock
    private CharacterLoader characterLoader;

    @Mock
    private Character character0;

    @Mock
    private Character character1;

    @Mock
    private Character character2;

    @Mock
    private Character character3;

    @Mock
    private Character character4;

    @Mock
    private Character character5;

    @Mock
    private Character character6;

    @Mock
    private Character character7;

    @InjectMocks
    private PartyLoader partyLoader;

    @Test
    public void loadWhenInputStreamIsValidReturnsPopulatedParty() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result).isNotNull();
    }

    @Test
    public void loadAlwaysLoadsCorrectCounter() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getCounter()).isEqualTo(123456);
    }

    @Test
    public void loadAlwaysLoadsCorrectMoves() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getMoves()).isEqualTo(6822);
    }

    @Test
    public void loadAlwaysLoadsCorrectCharacters() throws IOException {
        when(characterLoader.load(inputStream)).thenReturn(character0, character1, character2, character3, character4, character5, character6, character7);

        Party result = partyLoader.load(inputStream);

        assertThat(result.getPlayer0()).isNotNull().isEqualTo(character0);
        assertThat(result.getPlayer1()).isNotNull().isEqualTo(character1);
        assertThat(result.getPlayer2()).isNotNull().isEqualTo(character2);
        assertThat(result.getPlayer3()).isNotNull().isEqualTo(character3);
        assertThat(result.getPlayer4()).isNotNull().isEqualTo(character4);
        assertThat(result.getPlayer5()).isNotNull().isEqualTo(character5);
        assertThat(result.getPlayer6()).isNotNull().isEqualTo(character6);
        assertThat(result.getPlayer7()).isNotNull().isEqualTo(character7);
    }

    @Test
    public void loadAlwaysLoadsCorrectFood() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getFood()).isEqualTo(17827);
    }

    @Test
    public void loadAlwaysLoadsCorrectGold() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getGold()).isEqualTo(1839);
    }

    @Test
    public void loadAlwaysLoadsCorrectHonesty() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getHonesty()).isEqualTo(10);
    }

    @Test
    public void loadAlwaysLoadsCorrectCompassion() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getCompassion()).isEqualTo(20);
    }

    @Test
    public void loadAlwaysLoadsCorrectValour() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getValour()).isEqualTo(30);
    }

    @Test
    public void loadAlwaysLoadsCorrectJustice() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getJustice()).isEqualTo(40);
    }

    @Test
    public void loadAlwaysLoadsCorrectSacrifice() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getSacrifice()).isEqualTo(50);
    }

    @Test
    public void loadAlwaysLoadsCorrectHonour() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getHonour()).isEqualTo(60);
    }

    @Test
    public void loadAlwaysLoadsCorrectSpirituality() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getSpirituality()).isEqualTo(70);
    }

    @Test
    public void loadAlwaysLoadsCorrectHumility() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getHumility()).isEqualTo(80);
    }

    @Test
    public void loadAlwaysLoadsCorrectTorches() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getTorches()).isEqualTo(300);
    }

    @Test
    public void loadAlwaysLoadsCorrectGems() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getGems()).isEqualTo(400);
    }

    @Test
    public void loadAlwaysLoadsCorrectKeys() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getKeys()).isEqualTo(500);
    }

    @Test
    public void loadAlwaysLoadsCorrectSextants() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getSextants()).isEqualTo(600);
    }

    @Test
    public void loadAlwaysLoadsCorrectArmourNone() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getArmourNone()).isEqualTo(0);
    }

    @Test
    public void loadAlwaysLoadsCorrectArmourCloths() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getArmourCloths()).isEqualTo(1);
    }

    @Test
    public void loadAlwaysLoadsCorrectArmourLeathers() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getArmourLeathers()).isEqualTo(2);
    }

    @Test
    public void loadAlwaysLoadsCorrectArmourChains() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getArmourChains()).isEqualTo(3);
    }

    @Test
    public void loadAlwaysLoadsCorrectArmourPlates() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getArmourPlates()).isEqualTo(4);
    }

    @Test
    public void loadAlwaysLoadsCorrectArmourMagicChains() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getArmourMagicChains()).isEqualTo(5);
    }

    @Test
    public void loadAlwaysLoadsCorrectArmourMagicPlates() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getArmourMagicPlates()).isEqualTo(6);
    }

    @Test
    public void loadAlwaysLoadsCorrectArmourMysticRobes() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getArmourMysticRobes()).isEqualTo(7);
    }

    @Test
    public void loadAlwaysLoadsCorrectWeaponNone() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getWeaponNone()).isEqualTo(8);
    }

    @Test
    public void loadAlwaysLoadsCorrectWeaponStaves() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getWeaponStaves()).isEqualTo(9);
    }

    @Test
    public void loadAlwaysLoadsCorrectWeaponDaggers() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getWeaponDaggers()).isEqualTo(10);
    }

    @Test
    public void loadAlwaysLoadsCorrectWeaponSlings() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getWeaponSlings()).isEqualTo(11);
    }

    @Test
    public void loadAlwaysLoadsCorrectWeaponMaces() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getWeaponMaces()).isEqualTo(12);
    }

    @Test
    public void loadAlwaysLoadsCorrectWeaponAxes() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getWeaponAxes()).isEqualTo(13);
    }

    @Test
    public void loadAlwaysLoadsCorrectWeaponSwords() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getWeaponSwords()).isEqualTo(14);
    }

    @Test
    public void loadAlwaysLoadsCorrectWeaponBows() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getWeaponBows()).isEqualTo(15);
    }

    @Test
    public void loadAlwaysLoadsCorrectWeaponCrossbows() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getWeaponCrossbows()).isEqualTo(16);
    }

    @Test
    public void loadAlwaysLoadsCorrectWeaponFlamingOils() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getWeaponFlamingOils()).isEqualTo(17);
    }

    @Test
    public void loadAlwaysLoadsCorrectWeaponHalberds() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getWeaponHalberds()).isEqualTo(18);
    }

    @Test
    public void loadAlwaysLoadsCorrectWeaponMagicAxes() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getWeaponMagicAxes()).isEqualTo(19);
    }

    @Test
    public void loadAlwaysLoadsCorrectWeaponMagicSwords() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getWeaponMagicSwords()).isEqualTo(20);
    }

    @Test
    public void loadAlwaysLoadsCorrectWeaponMagicBows() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getWeaponMagicBows()).isEqualTo(21);
    }

    @Test
    public void loadAlwaysLoadsCorrectWeaponMagicWands() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getWeaponMagicWands()).isEqualTo(22);
    }

    @Test
    public void loadAlwaysLoadsCorrectWeaponMysticSwords() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getWeaponMysticSwords()).isEqualTo(23);
    }

    @Test
    public void loadAlwaysLoadsCorrectSulfurousAsh() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getSulfurousAsh()).isEqualTo(24);
    }

    @Test
    public void loadAlwaysLoadsCorrectGinseng() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getGinseng()).isEqualTo(25);
    }

    @Test
    public void loadAlwaysLoadsCorrectGarlic() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getGarlic()).isEqualTo(26);
    }

    @Test
    public void loadAlwaysLoadsCorrectSpiderSilk() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getSpiderSilk()).isEqualTo(27);
    }

    @Test
    public void loadAlwaysLoadsCorrectBloodMoss() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getBloodMoss()).isEqualTo(28);
    }

    @Test
    public void loadAlwaysLoadsCorrectBlackPearl() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getBlackPearl()).isEqualTo(29);
    }

    @Test
    public void loadAlwaysLoadsCorrectNightshade() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getNightshade()).isEqualTo(30);
    }

    @Test
    public void loadAlwaysLoadsCorrectMandrake() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getMandrake()).isEqualTo(31);
    }

    @Test
    public void loadAlwaysLoadsCorrectAwaken() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getAwaken()).isEqualTo(32);
    }

    @Test
    public void loadAlwaysLoadsCorrectBlink() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getBlink()).isEqualTo(33);
    }

    @Test
    public void loadAlwaysLoadsCorrectCure() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getCure()).isEqualTo(34);
    }

    @Test
    public void loadAlwaysLoadsCorrectDispel() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getDispel()).isEqualTo(35);
    }

    @Test
    public void loadAlwaysLoadsCorrectEnergy() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getEnergy()).isEqualTo(36);
    }

    @Test
    public void loadAlwaysLoadsCorrectFireball() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getFireball()).isEqualTo(37);
    }

    @Test
    public void loadAlwaysLoadsCorrectGate() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getGate()).isEqualTo(38);
    }

    @Test
    public void loadAlwaysLoadsCorrectHeal() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getHeal()).isEqualTo(39);
    }

    @Test
    public void loadAlwaysLoadsCorrectIceball() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getIceball()).isEqualTo(40);
    }

    @Test
    public void loadAlwaysLoadsCorrectJinx() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getJinx()).isEqualTo(41);
    }

    @Test
    public void loadAlwaysLoadsCorrectKill() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getKill()).isEqualTo(42);
    }

    @Test
    public void loadAlwaysLoadsCorrectLight() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getLight()).isEqualTo(43);
    }

    @Test
    public void loadAlwaysLoadsCorrectMagicMissile() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getMagicMissile()).isEqualTo(44);
    }

    @Test
    public void loadAlwaysLoadsCorrectNegate() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getNegate()).isEqualTo(45);
    }

    @Test
    public void loadAlwaysLoadsCorrectOpen() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getOpen()).isEqualTo(46);
    }

    @Test
    public void loadAlwaysLoadsCorrectProtection() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getProtection()).isEqualTo(47);
    }

    @Test
    public void loadAlwaysLoadsCorrectQuickness() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getQuickness()).isEqualTo(48);
    }

    @Test
    public void loadAlwaysLoadsCorrectResurrect() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getResurrect()).isEqualTo(49);
    }

    @Test
    public void loadAlwaysLoadsCorrectSleep() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getSleep()).isEqualTo(50);
    }

    @Test
    public void loadAlwaysLoadsCorrectTremor() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getTremor()).isEqualTo(51);
    }

    @Test
    public void loadAlwaysLoadsCorrectUndead() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getUndead()).isEqualTo(52);
    }

    @Test
    public void loadAlwaysLoadsCorrectView() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getView()).isEqualTo(53);
    }

    @Test
    public void loadAlwaysLoadsCorrectWinds() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getWinds()).isEqualTo(54);
    }

    @Test
    public void loadAlwaysLoadsCorrectXIt() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getxIt()).isEqualTo(55);
    }

    @Test
    public void loadAlwaysLoadsCorrectYUp() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getYUp()).isEqualTo(56);
    }

    @Test
    public void loadAlwaysLoadsCorrectZDown() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getZDown()).isEqualTo(57);
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectSkull() throws IOException {
        data[154] = 0x7F;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isSkull()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectSkull() throws IOException {
        data[154] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isSkull()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectSkullDestroyed() throws IOException {
        data[154] = 0xBF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isSkullDestroyed()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectSkullDestroyed() throws IOException {
        data[154] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isSkullDestroyed()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectCandle() throws IOException {
        data[154] = 0xDF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isCandle()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectCandle() throws IOException {
        data[154] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isCandle()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectBook() throws IOException {
        data[154] = 0xEF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isBook()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectBook() throws IOException {
        data[154] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isBook()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectBell() throws IOException {
        data[154] = 0xF7;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isBell()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectBell() throws IOException {
        data[154] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isBell()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectKeyPartC() throws IOException {
        data[154] = 0xFB;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isKeyPartC()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectKeyPartC() throws IOException {
        data[154] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isKeyPartC()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectKeyPartL() throws IOException {
        data[154] = 0xFD;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isKeyPartL()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectKeyPartL() throws IOException {
        data[154] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isKeyPartL()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectKeyPartT() throws IOException {
        data[154] = 0xFE;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isKeyPartT()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectKeyPartT() throws IOException {
        data[154] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isKeyPartT()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectHorn() throws IOException {
        data[155] = 0x7F;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isHorn()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectHorn() throws IOException {
        data[155] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isHorn()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectWheel() throws IOException {
        data[155] = 0xBF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isWheel()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectWheel() throws IOException {
        data[155] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isWheel()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectCandleUsed() throws IOException {
        data[155] = 0xDF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isCandleUsed()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectCandleUsed() throws IOException {
        data[155] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isCandleUsed()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectBookUsed() throws IOException {
        data[155] = 0xEF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isBookUsed()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectBookUsed() throws IOException {
        data[155] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isBookUsed()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectBellUsed() throws IOException {
        data[155] = 0xF7;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isBellUsed()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectBellUsed() throws IOException {
        data[155] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isBellUsed()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectUnused1() throws IOException {
        data[155] = 0xFB;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isUnused1()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectUnused1() throws IOException {
        data[155] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isUnused1()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectUnused2() throws IOException {
        data[155] = 0xFD;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isUnused2()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectUnused2() throws IOException {
        data[155] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isUnused2()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectUnused3() throws IOException {
        data[155] = 0xFE;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isUnused3()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectUnused3() throws IOException {
        data[155] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isUnused3()).isTrue();
    }

    @Test
    public void loadAlwaysLoadsCorrectCol() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getCol()).isEqualTo(123);
    }

    @Test
    public void loadAlwaysLoadsCorrectRow() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getRow()).isEqualTo(234);
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectBlueStone() throws IOException {
        data[158] = 0x7F;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isBlueStone()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectBlueStone() throws IOException {
        data[158] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isBlueStone()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectYellowStone() throws IOException {
        data[158] = 0xBF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isYellowStone()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectYellowStone() throws IOException {
        data[158] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isYellowStone()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectRedStone() throws IOException {
        data[158] = 0xDF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isRedStone()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectRedStone() throws IOException {
        data[158] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isRedStone()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectGreenStone() throws IOException {
        data[158] = 0xEF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isGreenStone()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectGreenStone() throws IOException {
        data[158] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isGreenStone()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectOrangeStone() throws IOException {
        data[158] = 0xF7;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isOrangeStone()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectOrangeStone() throws IOException {
        data[158] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isOrangeStone()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectPurpleStone() throws IOException {
        data[158] = 0xFB;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isPurpleStone()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectPurpleStone() throws IOException {
        data[158] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isPurpleStone()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectWhiteStone() throws IOException {
        data[158] = 0xFD;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isWhiteStone()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectWhiteStone() throws IOException {
        data[158] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isWhiteStone()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectBlackStone() throws IOException {
        data[158] = 0xFE;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isBlackStone()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectBlackStone() throws IOException {
        data[158] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isBlackStone()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectRuneHonesty() throws IOException {
        data[159] = 0x7F;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isRuneHonesty()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectRunHonesty() throws IOException {
        data[159] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isRuneHonesty()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectRuneCompassion() throws IOException {
        data[159] = 0xBF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isRunCompassion()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectRuneCompassion() throws IOException {
        data[159] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isRunCompassion()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectRuneValour() throws IOException {
        data[159] = 0xDF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isRuneValour()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectRuneValour() throws IOException {
        data[159] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isRuneValour()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectRuneJustice() throws IOException {
        data[159] = 0xEF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isRuneJustice()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectRuneJustice() throws IOException {
        data[159] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isRuneJustice()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectRuneSacrifice() throws IOException {
        data[159] = 0xF7;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isRuneSacrifice()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectRuneSacrifice() throws IOException {
        data[159] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isRuneSacrifice()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectRuneHonour() throws IOException {
        data[159] = 0xFB;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isRuneHonour()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectRuneHonour() throws IOException {
        data[159] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isRuneHonour()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectRuneSpirituality() throws IOException {
        data[159] = 0xFD;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isRuneSpirituality()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectRuneSpirituality() throws IOException {
        data[159] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isRuneSpirituality()).isTrue();
    }

    @Test
    public void loadWhenItemsBitIsNotSetLoadsCorrectRuneHumility() throws IOException {
        data[159] = 0xFE;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isRuneHumility()).isFalse();
    }

    @Test
    public void loadWhenItemsBitIsSetLoadsCorrectRuneHumility() throws IOException {
        data[159] = 0xFF;

        Party result = partyLoader.load(inputStream);

        assertThat(result.isRuneHumility()).isTrue();
    }

    @Test
    public void loadAlwaysLoadsCorrectNumberOfCharacters() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getNumberOfCharacters()).isEqualTo(4);
    }

    @Test
    public void loadAlwaysLoadsCorrectTransportation() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getTransportation()).isEqualTo(Transportation.ON_FOOT);
    }

    @Test
    public void loadAlwaysLoadsCorrectBalloonStatus() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getBalloonStatus()).isEqualTo(BalloonStatus.FLYING);
    }

    @Test
    public void loadAlwaysLoadsCorrectPhaseOfTrammel() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getPhaseOfTrammel()).isEqualTo(3);
    }

    @Test
    public void loadAlwaysLoadsCorrectPhaseOfFelucca() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getPhaseOfFelucca()).isEqualTo(5);
    }

    @Test
    public void loadAlwaysLoadsCorrectShipHullIntegrity() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getHullIntegrity()).isEqualTo(123);
    }

    @Test
    public void loadAlwaysLoadsCorrectIntroducedToLordBritish() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.isIntroducedToLordBritish()).isTrue();
    }

    @Test
    public void loadAlwaysLoadsCorrectLastSuccessfulCamp() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getLastSuccessfulCamp()).isEqualTo(12345);
    }

    @Test
    public void loadAlwaysLoadsCorrectLastMandrakeNightshadeFind() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getLastMandrakeNightshadeFind()).isEqualTo(23456);
    }

    @Test
    public void loadAlwaysLoadsCorrectLastSuccessfulMeditation() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getLastSuccessfulMeditation()).isEqualTo(34567);
    }

    @Test
    public void loadAlwaysLoadsCorrectLastVirtueRelatedConversation() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getLastVirtueRelatedConversation()).isEqualTo(45678);
    }

    @Test
    public void loadAlwaysLoadsCorrectDungeonCol() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getDungeonCol()).isEqualTo(234);
    }

    @Test
    public void loadAlwaysLoadsCorrectDungeonRow() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getDungeonRow()).isEqualTo(123);
    }

    @Test
    public void loadAlwaysLoadsCorrectOrientationInDungeon() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getOrientationInDungeon()).isEqualTo(3);
    }

    @Test
    public void loadAlwaysLoadsCorrectDungeonLevel() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getDungeonLevel()).isEqualTo(4);
    }

    @Test
    public void loadAlwaysLoadsCorrectCurrentPartyLocation() throws IOException {
        Party result = partyLoader.load(inputStream);

        assertThat(result.getCurrentPartyLocation()).isEqualTo(Location.BUCCANEERS_DEN);
    }
}
