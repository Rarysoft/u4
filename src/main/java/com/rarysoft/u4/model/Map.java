package com.rarysoft.u4.model;

import com.rarysoft.u4.model.MapType;
import com.rarysoft.u4.model.graphics.Tile;
import com.rarysoft.u4.model.npc.Person;

import java.util.List;

public interface Map {
    int locationId();

    int level();

    MapType type();

    int worldRow();

    int worldCol();

    int startRow();

    int startCol();

    List<Person> people();

    Tile[][] full();

    Tile[][] view(int centerRow, int centerCol, int radius);

    Tile at(int row, int col);
}
