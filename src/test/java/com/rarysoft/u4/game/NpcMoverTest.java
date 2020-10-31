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

import com.rarysoft.u4.game.npc.MovementBehaviour;
import com.rarysoft.u4.game.npc.NonPlayerCharacter;
import com.rarysoft.u4.game.npc.Person;
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
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 1, MovementBehaviour.FIXED, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 10, 10, null);

        verifyNoInteractions(random);
        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsWanderButIsBlockedThenPersonStaysStill() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_WALL, Tile.BRICK_WALL, Tile.BRICK_WALL },
                { Tile.BRICK_WALL, Tile.BRICK_FLOOR, Tile.BRICK_WALL },
                { Tile.BRICK_WALL, Tile.BRICK_WALL, Tile.BRICK_WALL }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(2);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsWanderAndStayIsChosenThenPersonStaysStill() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(0);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsWanderAndNorthwestIsChosenThenPersonMovesNorthwest() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(1);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsWanderAndNorthIsChosenThenPersonMovesNorth() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(2);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsWanderAndNortheastIsChosenThenPersonMovesNortheast() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(3);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(2);
    }

    @Test
    public void movePeopleWhenPersonIsWanderAndWestIsChosenThenPersonMovesWest() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(4);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsWanderAndEastIsChosenThenPersonMovesEast() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(5);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(2);
    }

    @Test
    public void movePeopleWhenPersonIsWanderAndSouthwestIsChosenThenPersonMovesSouthwest() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(6);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsWanderAndSouthIsChosenThenPersonMovesSouth() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(7);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsWanderAndSoutheastIsChosenThenPersonMovesSoutheast() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(8);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(2);
    }

    @Test
    public void movePeopleWhenPersonIsWanderAndTargetTileSlowsProgressThenPersonStaysStill() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.SCRUBLAND, Tile.SCRUBLAND, Tile.SCRUBLAND },
                { Tile.SCRUBLAND, Tile.SCRUBLAND, Tile.SCRUBLAND },
                { Tile.SCRUBLAND, Tile.SCRUBLAND, Tile.SCRUBLAND }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(2);
        when(random.nextInt(100)).thenReturn(60);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsWanderAndTargetTileFailsToSlowProgressThenPersonMoves() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.SCRUBLAND, Tile.SCRUBLAND, Tile.SCRUBLAND },
                { Tile.SCRUBLAND, Tile.SCRUBLAND, Tile.SCRUBLAND },
                { Tile.SCRUBLAND, Tile.SCRUBLAND, Tile.SCRUBLAND }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 1, MovementBehaviour.WANDER, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextInt(9)).thenReturn(2);
        when(random.nextInt(100)).thenReturn(59);

        npcMover.movePeople(area, people, 10, 10, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNorthOfPersonThenPersonMovesNorth() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 2, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 0, 1, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNorthOfPersonAndPersonIsBlockedThenPersonStaysStill() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_WALL, Tile.BRICK_WALL, Tile.BRICK_WALL },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 2, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 0, 1, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNorthOfPersonAndPersonIsPartiallyBlockedWithNortheastAccessThenPersonMovesNortheast() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_WALL, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 2, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 0, 1, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(2);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNorthOfPersonAndPersonIsPartiallyBlockedWithNorthwestAccessThenPersonMovesNorthwest() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_WALL },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 2, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 0, 1, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNorthOfPersonAndPersonIsPartiallyBlockedWithNortheastAndNorthwestAccessThenPersonMovesNorthwest() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 2, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(true);

        npcMover.movePeople(area, people, 0, 1, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNorthOfPersonAndPersonIsPartiallyBlockedWithNortheastAndNorthwestAccessThenPersonMovesNortheast() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 2, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(false);

        npcMover.movePeople(area, people, 0, 1, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(2);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNortheastOfPersonThenPersonMovesNortheast() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 2, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 0, 2, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNortheastOfPersonAndPersonIsBlockedOnNorthSideThenPersonMovesEast() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_WALL, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 2, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 0, 2, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNortheastOfPersonAndPersonIsBlockedOnEastSideThenPersonMovesNorth() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 2, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 0, 2, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNortheastOfPersonAndPersonIsPartiallyBlockedWithNorthAndEastAccessThenPersonMovesNorth() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 2, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(true);

        npcMover.movePeople(area, people, 0, 2, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNortheastOfPersonAndPersonIsPartiallyBlockedWithNorthAndEastAccessThenPersonMovesEast() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 2, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(false);

        npcMover.movePeople(area, people, 0, 2, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsEastOfPersonThenPersonMovesEast() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 1, 2, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsEastOfPersonAndPersonIsBlockedOnEastSideThenPersonStaysStill() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 1, 2, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsEastOfPersonAndPersonIsPartiallyBlockedWithNortheastAccessThenPersonMovesNortheast() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 1, 2, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsEastOfPersonAndPersonIsPartiallyBlockedWithSoutheastAccessThenPersonMovesSoutheast() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 1, 2, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsEastOfPersonAndPersonIsPartiallyBlockedWithNortheastAndSoutheastAccessThenPersonMovesNortheast() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(true);

        npcMover.movePeople(area, people, 1, 2, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsEastOfPersonAndPersonIsPartiallyBlockedWithNortheastAndSoutheastAccessThenPersonMovesSoutheast() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(false);

        npcMover.movePeople(area, people, 1, 2, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSoutheastOfPersonThenPersonMovesSoutheast() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 0, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 2, 2, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSoutheastOfPersonAndPersonIsBlockedOnSouthSideThenPersonMovesEast() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_WALL, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 0, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 2, 2, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSoutheastOfPersonAndPersonIsBlockedOnEastSideThenPersonMovesSouth() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 0, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 2, 2, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSoutheastOfPersonAndPersonIsPartiallyBlockedWithSouthAndEastAccessThenPersonMovesEast() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 0, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(true);

        npcMover.movePeople(area, people, 2, 2, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSoutheastOfPersonAndPersonIsPartiallyBlockedWithSouthAndEastAccessThenPersonMovesSouth() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 0, 0, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(false);

        npcMover.movePeople(area, people, 2, 2, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSouthOfPersonThenPersonMovesSouth() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 0, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 2, 1, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSouthOfPersonAndPersonIsBlockedThenPersonStaysStill() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_WALL, Tile.BRICK_WALL, Tile.BRICK_WALL },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 0, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 2, 1, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSouthOfPersonAndPersonIsPartiallyBlockedWithSoutheastAccessThenPersonMovesSoutheast() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_WALL, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 0, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 2, 1, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(2);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSouthOfPersonAndPersonIsPartiallyBlockedWithSouthwestAccessThenPersonMovesSouthwest() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_WALL },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 0, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 2, 1, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSouthOfPersonAndPersonIsPartiallyBlockedWithSoutheastAndSouthwestAccessThenPersonMovesSoutheast() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 0, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(true);

        npcMover.movePeople(area, people, 2, 1, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(2);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSouthOfPersonAndPersonIsPartiallyBlockedWithSoutheastAndSouthwestAccessThenPersonMovesSouthwest() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 0, 1, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(false);

        npcMover.movePeople(area, people, 2, 1, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(0);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSouthwestOfPersonThenPersonMovesSouthwest() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 0, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 2, 0, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSouthwestOfPersonAndPersonIsBlockedOnSouthSideThenPersonMovesWest() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_WALL },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 0, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 2, 0, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSouthwestOfPersonAndPersonIsBlockedOnEastSideThenPersonMovesSouth() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 0, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 2, 0, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(2);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSouthwestOfPersonAndPersonIsPartiallyBlockedWithSouthAndEastAccessThenPersonMovesSouth() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 0, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(true);

        npcMover.movePeople(area, people, 2, 0, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(2);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsSouthwestOfPersonAndPersonIsPartiallyBlockedWithSouthAndEastAccessThenPersonMovesWest() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 0, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(false);

        npcMover.movePeople(area, people, 2, 0, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsWestOfPersonThenPersonMovesWest() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 1, 0, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsWestOfPersonAndPersonIsBlockedOnWestSideThenPersonStaysStill() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 1, 0, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(2);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsWestOfPersonAndPersonIsPartiallyBlockedWithNorthWestAccessThenPersonMovesNorthwest() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 1, 0, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsWestOfPersonAndPersonIsPartiallyBlockedWithSouthwestAccessThenPersonMovesSouthwest() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 1, 0, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsWestOfPersonAndPersonIsPartiallyBlockedWithNorthwestAndSouthwestAccessThenPersonMovesSouthwest() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(true);

        npcMover.movePeople(area, people, 1, 0, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsWestOfPersonAndPersonIsPartiallyBlockedWithNorthwestAndSouthwestAccessThenPersonMovesNorthwest() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 1, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(false);

        npcMover.movePeople(area, people, 1, 0, null);

        assertThat(person.row()).isEqualTo(0);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNorthwestOfPersonThenPersonMovesNorthwest() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 2, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 0, 0, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNorthwestOfPersonAndPersonIsBlockedOnNorthSideThenPersonMovesWest() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_WALL },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 2, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 0, 0, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNorthwestOfPersonAndPersonIsBlockedOnEastSideThenPersonMovesNorth() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 2, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);

        npcMover.movePeople(area, people, 0, 0, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(2);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNorthwestOfPersonAndPersonIsPartiallyBlockedWithNorthAndWestAccessThenPersonMovesWest() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 2, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(true);

        npcMover.movePeople(area, people, 0, 0, null);

        assertThat(person.row()).isEqualTo(2);
        assertThat(person.col()).isEqualTo(1);
    }

    @Test
    public void movePeopleWhenPersonIsFollowAndPlayerIsNorthwestOfPersonAndPersonIsPartiallyBlockedWithNorthAndWestAccessThenPersonMovesNorth() {
        Area<Tile> area = new Area<>(new Tile[][] {
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_WALL, Tile.BRICK_FLOOR },
                { Tile.BRICK_FLOOR, Tile.BRICK_FLOOR, Tile.BRICK_FLOOR }
        });
        Person person = new Person(NonPlayerCharacter.CITIZEN, 1, 2, 2, MovementBehaviour.FOLLOW, 0);
        List<Person> people = Collections.singletonList(person);
        when(random.nextBoolean()).thenReturn(false);

        npcMover.movePeople(area, people, 0, 0, null);

        assertThat(person.row()).isEqualTo(1);
        assertThat(person.col()).isEqualTo(2);
    }
}
