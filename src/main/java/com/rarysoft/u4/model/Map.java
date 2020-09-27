package com.rarysoft.u4.model;

import java.util.List;

public interface Map {
    int id();

    MapType type();

    int worldX();

    int worldY();

    int startX();

    int startY();

    List<Person> people();

    Tile[][] full();

    Tile[][] view(int centerX, int centerY, int radius);

    Tile at(int row, int col);
}
