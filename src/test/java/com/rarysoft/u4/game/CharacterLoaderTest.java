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

import com.rarysoft.u4.game.model.*;
import com.rarysoft.u4.game.model.Character;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class CharacterLoaderTest {
    private final InputStream inputStream = new InputStream() {
        private final int[] data = {
                0x2B, 0x01, // HP (299)
                0x2C, 0x01, // MHP (300)
                0xF0, 0x00, // XP (240)
                0x10, 0x00, // STR (16)
                0x19, 0x00, // DEX (25)
                0x12, 0x00, // INT (18)
                0x06, 0x00, // MP (6)
                0x00, 0x00, // Unknown (0)
                0x03, 0x00, // Weapon (Sling)
                0x01, 0x00, // Armour (Cloth)
                0x50, 0x6C, 0x61, 0x79, 0x65, 0x72, 0x00, 0x79, 0x90, 0x17, 0x20, 0x8D, 0x9D, 0x88, 0x70, 0x95, // Name (Player)
                0x0B,   // Sex (M)
                0x01,   // Class (Bard)
                0x47    // Status (Good)
        };

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

    private final CharacterLoader characterLoader = new CharacterLoader();

    @Test
    public void loadWhenInputStreamIsValidReturnsPopulatedCharacter() throws IOException {
        Character result = characterLoader.load(inputStream);

        assertThat(result).isNotNull();
    }

    @Test
    public void loadAlwaysLoadsCorrectHitPoints() throws IOException {
        Character result = characterLoader.load(inputStream);

        assertThat(result.getHp()).isEqualTo(299);
    }

    @Test
    public void loadAlwaysLoadsCorrectMaximumHitPoints() throws IOException {
        Character result = characterLoader.load(inputStream);

        assertThat(result.getMaxHp()).isEqualTo(300);
    }

    @Test
    public void loadAlwaysLoadsCorrectExperiencePoints() throws IOException {
        Character result = characterLoader.load(inputStream);

        assertThat(result.getXp()).isEqualTo(240);
    }

    @Test
    public void loadAlwaysLoadsCorrectStrength() throws IOException {
        Character result = characterLoader.load(inputStream);

        assertThat(result.getStrength()).isEqualTo(16);
    }

    @Test
    public void loadAlwaysLoadsCorrectDexterity() throws IOException {
        Character result = characterLoader.load(inputStream);

        assertThat(result.getDexterity()).isEqualTo(25);
    }

    @Test
    public void loadAlwaysLoadsCorrectIntelligence() throws IOException {
        Character result = characterLoader.load(inputStream);

        assertThat(result.getIntelligence()).isEqualTo(18);
    }

    @Test
    public void loadAlwaysLoadsCorrectMagicPoints() throws IOException {
        Character result = characterLoader.load(inputStream);

        assertThat(result.getMp()).isEqualTo(6);
    }

    @Test
    public void loadAlwaysLoadsCorrectUnknownField() throws IOException {
        Character result = characterLoader.load(inputStream);

        assertThat(result.getUnknown()).isEqualTo(0);
    }

    @Test
    public void loadAlwaysLoadsCorrectWeapon() throws IOException {
        Character result = characterLoader.load(inputStream);

        assertThat(result.getWeapon()).isEqualTo(Weapon.SLING);
    }

    @Test
    public void loadAlwaysLoadsCorrectArmour() throws IOException {
        Character result = characterLoader.load(inputStream);

        assertThat(result.getArmour()).isEqualTo(Armour.CLOTH);
    }

    @Test
    public void loadWhenLoadsCorrectName() throws IOException {
        Character result = characterLoader.load(inputStream);

        assertThat(result.getName()).isEqualTo("Player");
    }

    @Test
    public void loadWhenLoadsCorrectSex() throws IOException {
        Character result = characterLoader.load(inputStream);

        assertThat(result.getSex()).isEqualTo(Sex.MALE);
    }

    @Test
    public void loadWhenLoadsCorrectCharacterClass() throws IOException {
        Character result = characterLoader.load(inputStream);

        assertThat(result.getCharacterClass()).isEqualTo(CharacterClass.BARD);
    }

    @Test
    public void loadWhenLoadsCorrectStatus() throws IOException {
        Character result = characterLoader.load(inputStream);

        assertThat(result.getStatus()).isEqualTo(Status.GOOD);
    }
}
