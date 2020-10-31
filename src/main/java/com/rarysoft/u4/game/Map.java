package com.rarysoft.u4.game;

import com.rarysoft.u4.game.npc.Person;
import com.rarysoft.u4.game.party.Location;

import java.util.List;
import java.util.Optional;

public interface Map {
    MapType type();

    Location location();

    int level();

    Tile[][] data();

    int worldRow();

    int worldCol();

    int startRow();

    int startCol();

    Tile surroundingTile();

    List<Person> people();

    Optional<Person> personAt(int row, int col);

    void movePeople(PeopleMover peopleMover, int playerRow, int playerCol, Person excludedPerson);

    Tile at(int row, int col);
}
