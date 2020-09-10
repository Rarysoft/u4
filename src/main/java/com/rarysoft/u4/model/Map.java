package com.rarysoft.u4.model;

public interface Map {
    int[][] view(int centerX, int centerY, int radius);

    int at(int x, int y);
}
