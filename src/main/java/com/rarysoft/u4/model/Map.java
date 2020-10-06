package com.rarysoft.u4.model;

import com.rarysoft.u4.model.graphics.Tile;
import com.rarysoft.u4.model.npc.Person;
import com.rarysoft.u4.model.party.Location;

import java.util.List;
import java.util.Optional;

public interface Map {
    Location location();

    int level();

    MapType type();

    int worldRow();

    int worldCol();

    int startRow();

    int startCol();

    List<Person> people();

    Optional<Person> personAt(int row, int col);

    void movePeople(PeopleMover peopleMover, int playerRow, int playerCol, Person excludedPerson);

    Tile[][] full();

    Tile[][] view(int centerRow, int centerCol, int radius);

    Tile at(int row, int col);
}
