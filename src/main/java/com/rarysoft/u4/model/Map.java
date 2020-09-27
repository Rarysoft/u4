package com.rarysoft.u4.model;

import java.util.List;

public interface Map {
    int id();

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
