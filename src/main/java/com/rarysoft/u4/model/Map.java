package com.rarysoft.u4.model;

public interface Map {
    MapType type();

    int worldX();

    int worldY();

    int startX();

    int startY();

    int[][] view(int centerX, int centerY, int radius);

    int at(int x, int y);
}
