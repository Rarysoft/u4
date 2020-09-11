package com.rarysoft.u4.model;

public interface Map {
    MapType type();

    int worldX();

    int worldY();

    int startX();

    int startY();

    Tile[][] view(int centerX, int centerY, int radius);

    Tile at(int x, int y);
}
