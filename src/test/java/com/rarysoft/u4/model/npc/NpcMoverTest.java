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

import com.rarysoft.u4.model.graphics.Tile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NpcMoverTest {
    @Mock
    private Random random;

    @InjectMocks
    private NpcMover npcMover;

    @Test
    public void movePeopleWhenPersonIsFixedThenPersonStaysStill() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 1, MovementBehaviour.FIXED, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 10, 10, null);

        verifyNoInteractions(random);
        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsWanderButIsBlockedThenPersonStaysStill() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_WALL, Tile.BRICK_WALL, Tile.BRICK_WALL },
                { Tile.BRICK_WALL, Tile.BRICK_FLOOR, Tile.BRICK_WALL },
                { Tile.BRICK_WALL, Tile.BRICK_WALL, Tile.BRICK_WALL }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(2);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsWanderAndStayIsChosenThenPersonStaysStill() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(0);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsWanderAndNorthwestIsChosenThenPersonMovesNorthwest() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(1);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsWanderAndNorthIsChosenThenPersonMovesNorth() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(2);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsWanderAndNortheastIsChosenThenPersonMovesNortheast() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(3);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(2);
    }

    @Test
    public void movePeopleWhenPersonIsWanderAndWestIsChosenThenPersonMovesWest() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(4);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsWanderAndEastIsChosenThenPersonMovesEast() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(5);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(2);
    }

    @Test
    public void movePeopleWhenPersonIsWanderAndSouthwestIsChosenThenPersonMovesSouthwest() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(6);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsWanderAndSouthIsChosenThenPersonMovesSouth() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(7);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsWanderAndSoutheastIsChosenThenPersonMovesSoutheast() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(8);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(2);
    }

    @Test
    public void movePeopleWhenPersonIsWanderAndTargetTileSlowsProgressThenPersonStaysStill() {
        Tile[][] area = new Tile[][] {
                { Tile.SCRUBLAND, Tile.SCRUBLAND, Tile.SCRUBLAND },
                { Tile.SCRUBLAND, Tile.SCRUBLAND, Tile.SCRUBLAND },
                { Tile.SCRUBLAND, Tile.SCRUBLAND, Tile.SCRUBLAND }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(2);
        when(random.nextInt(100)).thenReturn(60);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsWanderAndTargetTileFailsToSlowProgressThenPersonMoves() {
        Tile[][] area = new Tile[][] {
                { Tile.SCRUBLAND, Tile.SCRUBLAND, Tile.SCRUBLAND },
                { Tile.SCRUBLAND, Tile.SCRUBLAND, Tile.SCRUBLAND },
                { Tile.SCRUBLAND, Tile.SCRUBLAND, Tile.SCRUBLAND }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(2);
        when(random.nextInt(100)).thenReturn(59);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNorthOfPersonThenPersonMovesNorth() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 2, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 0, 1, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNorthOfPersonAndPersonIsBlockedThenPersonStaysStill() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_WALL, Tile.BRICK_WALL, Tile.BRICK_WALL },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 2, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 0, 1, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNorthOfPersonAndPersonIsPartiallyBlockedWithNortheastAccessThenPersonMovesNortheast() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_WALL, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 2, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 0, 1, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(2);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNorthOfPersonAndPersonIsPartiallyBlockedWithNorthwestAccessThenPersonMovesNorthwest() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_WALL },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 2, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 0, 1, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNorthOfPersonAndPersonIsPartiallyBlockedWithNortheastAndNorthwestAccessThenPersonMovesNorthwest() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 2, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(true);

        npcMover.movePeople(area, people, 0, 1, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNorthOfPersonAndPersonIsPartiallyBlockedWithNortheastAndNorthwestAccessThenPersonMovesNortheast() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 2, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(false);

        npcMover.movePeople(area, people, 0, 1, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(2);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNortheastOfPersonThenPersonMovesNortheast() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 2, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 0, 2, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNortheastOfPersonAndPersonIsBlockedOnNorthSideThenPersonMovesEast() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_WALL, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 2, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 0, 2, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNortheastOfPersonAndPersonIsBlockedOnEastSideThenPersonMovesNorth() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 2, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 0, 2, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNortheastOfPersonAndPersonIsPartiallyBlockedWithNorthAndEastAccessThenPersonMovesNorth() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 2, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(true);

        npcMover.movePeople(area, people, 0, 2, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNortheastOfPersonAndPersonIsPartiallyBlockedWithNorthAndEastAccessThenPersonMovesEast() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 2, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(false);

        npcMover.movePeople(area, people, 0, 2, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsEastOfPersonThenPersonMovesEast() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 1, 2, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsEastOfPersonAndPersonIsBlockedOnEastSideThenPersonStaysStill() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 1, 2, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsEastOfPersonAndPersonIsPartiallyBlockedWithNortheastAccessThenPersonMovesNortheast() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 1, 2, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsEastOfPersonAndPersonIsPartiallyBlockedWithSoutheastAccessThenPersonMovesSoutheast() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 1, 2, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsEastOfPersonAndPersonIsPartiallyBlockedWithNortheastAndSoutheastAccessThenPersonMovesNortheast() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(true);

        npcMover.movePeople(area, people, 1, 2, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsEastOfPersonAndPersonIsPartiallyBlockedWithNortheastAndSoutheastAccessThenPersonMovesSoutheast() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(false);

        npcMover.movePeople(area, people, 1, 2, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSoutheastOfPersonThenPersonMovesSoutheast() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 0, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 2, 2, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSoutheastOfPersonAndPersonIsBlockedOnSouthSideThenPersonMovesEast() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_WALL, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 0, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 2, 2, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSoutheastOfPersonAndPersonIsBlockedOnEastSideThenPersonMovesSouth() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 0, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 2, 2, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSoutheastOfPersonAndPersonIsPartiallyBlockedWithSouthAndEastAccessThenPersonMovesEast() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 0, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(true);

        npcMover.movePeople(area, people, 2, 2, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSoutheastOfPersonAndPersonIsPartiallyBlockedWithSouthAndEastAccessThenPersonMovesSouth() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 0, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(false);

        npcMover.movePeople(area, people, 2, 2, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSouthOfPersonThenPersonMovesSouth() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 0, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 2, 1, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSouthOfPersonAndPersonIsBlockedThenPersonStaysStill() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_WALL, Tile.BRICK_WALL, Tile.BRICK_WALL },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 0, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 2, 1, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSouthOfPersonAndPersonIsPartiallyBlockedWithSoutheastAccessThenPersonMovesSoutheast() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_WALL, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 0, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 2, 1, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(2);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSouthOfPersonAndPersonIsPartiallyBlockedWithSouthwestAccessThenPersonMovesSouthwest() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_WALL },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 0, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 2, 1, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSouthOfPersonAndPersonIsPartiallyBlockedWithSoutheastAndSouthwestAccessThenPersonMovesSoutheast() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 0, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(true);

        npcMover.movePeople(area, people, 2, 1, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(2);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSouthOfPersonAndPersonIsPartiallyBlockedWithSoutheastAndSouthwestAccessThenPersonMovesSouthwest() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 0, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(false);

        npcMover.movePeople(area, people, 2, 1, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSouthwestOfPersonThenPersonMovesSouthwest() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 0, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 2, 0, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSouthwestOfPersonAndPersonIsBlockedOnSouthSideThenPersonMovesWest() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_WALL },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 0, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 2, 0, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSouthwestOfPersonAndPersonIsBlockedOnEastSideThenPersonMovesSouth() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 0, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 2, 0, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(2);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSouthwestOfPersonAndPersonIsPartiallyBlockedWithSouthAndEastAccessThenPersonMovesSouth() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 0, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(true);

        npcMover.movePeople(area, people, 2, 0, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(2);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSouthwestOfPersonAndPersonIsPartiallyBlockedWithSouthAndEastAccessThenPersonMovesWest() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 0, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(false);

        npcMover.movePeople(area, people, 2, 0, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsWestOfPersonThenPersonMovesWest() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 1, 0, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsWestOfPersonAndPersonIsBlockedOnWestSideThenPersonStaysStill() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 1, 0, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(2);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsWestOfPersonAndPersonIsPartiallyBlockedWithNorthWestAccessThenPersonMovesNorthwest() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 1, 0, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsWestOfPersonAndPersonIsPartiallyBlockedWithSouthwestAccessThenPersonMovesSouthwest() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 1, 0, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsWestOfPersonAndPersonIsPartiallyBlockedWithNorthwestAndSouthwestAccessThenPersonMovesSouthwest() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(true);

        npcMover.movePeople(area, people, 1, 0, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsWestOfPersonAndPersonIsPartiallyBlockedWithNorthwestAndSouthwestAccessThenPersonMovesNorthwest() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 1, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(false);

        npcMover.movePeople(area, people, 1, 0, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNorthwestOfPersonThenPersonMovesNorthwest() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 2, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 0, 0, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNorthwestOfPersonAndPersonIsBlockedOnNorthSideThenPersonMovesWest() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_WALL },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 2, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 0, 0, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNorthwestOfPersonAndPersonIsBlockedOnEastSideThenPersonMovesNorth() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 2, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 0, 0, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(2);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNorthwestOfPersonAndPersonIsPartiallyBlockedWithNorthAndWestAccessThenPersonMovesWest() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 2, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(true);

        npcMover.movePeople(area, people, 0, 0, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNorthwestOfPersonAndPersonIsPartiallyBlockedWithNorthAndWestAccessThenPersonMovesNorth() {
        Tile[][] area = new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        };
        Person person = new Person(Tile.CITIZEN_1, 2, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(false);

        npcMover.movePeople(area, people, 0, 0, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(2);
    }
}
