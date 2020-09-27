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
package com.rarysoft.u4.model;

import java.util.*;
import java.util.Map;

public class PeopleTracker {
    private static final Random RANDOM = new Random();

    private final Map<Integer, List<Person>> people = new HashMap<>();

    public void addPeople(Integer id, List<Person> people) {
        if (! this.people.containsKey(id)) {
            this.people.put(id, people);
        }
    }

    public Optional<Person> personAt(int id, int row, int col) {
        return people.get(id)
                .stream()
                .filter(person -> person.row() == row && person.col() == col)
                .findAny();
    }

    public void movePeople(Tile[][] area, int playerRow, int playerCol) {
        people.forEach((id, regionalPeople) -> regionalPeople.forEach(person -> {
            switch (person.movementBehaviour()) {
                case 0x00:
                    // fixed
                    break;

                case 0x01:
                    // wander
                    int direction = RANDOM.nextInt(9);
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
                                id,
                                person,
                                direction < 4 ? person.row() - 1 : direction > 5 ? person.row() + 1 : person.row(),
                                direction == 1 || direction == 4 || direction == 6 ? person.col() - 1 : direction == 3 || direction == 5 || direction == 8 ? person.col() + 1 : person.col(),
                                playerRow,
                                playerCol,
                                area);
                    }
                    break;

                case 0x80:
                    // follow
                    attemptToMoveTo(
                            id,
                            person,
                            person.row() > playerRow ? person.row() - 1 : person.row() < playerRow ? person.row() + 1 : person.row(),
                            person.col() > playerCol ? person.col() - 1 : person.col() < playerCol ? person.col() + 1 : person.col(),
                            playerRow,
                            playerCol,
                            area);
                    break;

                case 0xFF:
                    // attack
                    attemptToMoveTo(
                            id,
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
        }));
    }

    private void attemptToMoveTo(int id, Person person, int row, int col, int playerRow, int playerCol, Tile[][] area) {
        if (row == playerRow && col == playerCol) {
            return;
        }
        int walkability = area[row][col].walkability();
        if (walkability < 100 && RANDOM.nextInt(100) >= walkability) {
            return;
        }
        if (people.get(id).stream().anyMatch(otherPerson -> otherPerson.row() == row && otherPerson.col() == col)) {
            return;
        }
        person.moveTo(row, col);
    }
}
