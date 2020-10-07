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
package com.rarysoft.u4.model.npc;

import com.rarysoft.u4.model.PeopleMover;
import com.rarysoft.u4.model.graphics.Tile;

import java.util.List;
import java.util.Random;

public class NpcMover implements PeopleMover {
    private final Random random;

    public NpcMover(Random random) {
        this.random = random;
    }

    public void movePeople(Tile[][] area, List<Person> people, int playerRow, int playerCol, Person excluded) {
        people.forEach(person -> {
            if (person == excluded) {
                return;
            }
            switch (person.movementBehaviour()) {
                case FIXED:
                    break;

                case WANDER:
                    int direction = random.nextInt(9);
                    // 0 = stay still
                    // 1 = NW
                    // 2 = N
                    // 3 = NE
                    // 4 = W
                    // 5 = E
                    // 6 = SW
                    // 7 = S
                    // 8 = SE
                    if (direction > 0) {
                        attemptToMoveTo(
                                people,
                                person,
                                direction < 4 ? person.row() - 1 : direction > 5 ? person.row() + 1 : person.row(),
                                direction == 1 || direction == 4 || direction == 6 ? person.col() - 1 : direction == 3 || direction == 5 || direction == 8 ? person.col() + 1 : person.col(),
                                playerRow,
                                playerCol,
                                area);
                    }
                    break;

                case FOLLOW:
                    attemptToMoveTo(
                            people,
                            person,
                            person.row() > playerRow ? person.row() - 1 : person.row() < playerRow ? person.row() + 1 : person.row(),
                            person.col() > playerCol ? person.col() - 1 : person.col() < playerCol ? person.col() + 1 : person.col(),
                            playerRow,
                            playerCol,
                            area);
                    break;

                case ATTACK:
                    attemptToMoveTo(
                            people,
                            person,
                            person.row() > playerRow ? person.row() - 1 : person.row() < playerRow ? person.row() + 1 : person.row(),
                            person.col() > playerCol ? person.col() - 1 : person.col() < playerCol ? person.col() + 1 : person.col(),
                            playerRow,
                            playerCol,
                            area);
                    // TODO: need to implement combat
                    break;

                default:
                    break;
            }
        });
    }

    private void attemptToMoveTo(List<Person> people, Person person, int row, int col, int playerRow, int playerCol, Tile[][] area) {
        if (row < 0 || row >= area.length || col < 0 || col >= area[row].length) {
            return;
        }
        if (row == playerRow && col == playerCol) {
            return;
        }
        int walkability = area[row][col].walkability();
        if (walkability < 100 && random.nextInt(100) >= walkability) {
            return;
        }
        if (people.stream().anyMatch(otherPerson -> otherPerson.row() == row && otherPerson.col() == col)) {
            return;
        }
        person.moveTo(row, col);
    }
}
