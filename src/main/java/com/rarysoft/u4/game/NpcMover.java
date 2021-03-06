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

import com.rarysoft.u4.game.npc.Person;
import com.rarysoft.u4.game.physics.ViewFinder;
import com.rarysoft.u4.game.physics.WayFinder;

import java.util.List;
import java.util.Random;

public class NpcMover implements PeopleMover {
    private final Random random;

    private final ViewFinder viewFinder;

    private final WayFinder wayFinder;

    public NpcMover(Random random, ViewFinder viewFinder, WayFinder wayFinder) {
        this.random = random;
        this.viewFinder = viewFinder;
        this.wayFinder = wayFinder;
    }

    public void movePeople(Area<Tile> area, List<Person> people, int playerRow, int playerCol, Person excluded) {
        people.forEach(person -> {
            if (person == excluded) {
                return;
            }
            switch (person.movementBehaviour()) {
                case FIXED:
                    break;

                case WANDER:
                    wander(people, person, playerRow, playerCol, area);
                    break;

                case FOLLOW:
                    follow(people, person, playerRow, playerCol, area);
                    break;

                case ATTACK:
                    attack(people, person, playerRow, playerCol, area);
                    break;

                default:
                    break;
            }
        });
    }

    private void wander(List<Person> people, Person person, int playerRow, int playerCol, Area<Tile> area) {
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
    }

    private void follow(List<Person> people, Person person, int playerRow, int playerCol, Area<Tile> area) {
        attemptToMoveTowardPlayer(
                people,
                person,
                playerRow,
                playerCol,
                area);
    }

    private void attack(List<Person> people, Person person, int playerRow, int playerCol, Area<Tile> area) {
        // TODO: need to implement combat; for now just follow
        follow(people, person, playerRow, playerCol, area);
    }

    private void attemptToMoveTo(List<Person> people, Person person, int row, int col, int playerRow, int playerCol, Area<Tile> area) {
        if (row < 0 || row >= area.rows() || col < 0 || col >= area.cols()) {
            return;
        }
        if (row == playerRow && col == playerCol) {
            return;
        }
        int walkability = area.get(row, col).walkability();
        if (walkability < 100 && random.nextInt(100) >= walkability) {
            return;
        }
        if (people.stream().anyMatch(otherPerson -> otherPerson.row() == row && otherPerson.col() == col)) {
            return;
        }
        person.moveTo(row, col);
    }

    private void attemptToMoveTowardPlayer(List<Person> people, Person person, int playerRow, int playerCol, Area<Tile> area) {
        int personViewRadius = 9;
        int playerRowInPersonViewArea = playerRow - person.row() + personViewRadius;
        int playerColInPersonViewArea = playerCol - person.col() + personViewRadius;
        Movement movement = wayFinder.selectMovementTowardTarget(viewFinder.view(area, Tile.GRASSLANDS, personViewRadius, person.row(), person.col()).map(Tile::walkability), playerRowInPersonViewArea, playerColInPersonViewArea);
        if (! movement.isStatic()) {
            attemptToMoveTo(people, person, person.row() + movement.getDeltaRow(), person.col() + movement.getDeltaCol(), playerRow, playerCol, area);
        }
    }
}
