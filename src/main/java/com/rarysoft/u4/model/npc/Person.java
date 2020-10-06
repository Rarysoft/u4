package com.rarysoft.u4.model.npc;

import com.rarysoft.u4.model.graphics.Tile;

public class Person {
    private final Tile tile;
    private final int startCol;
    private final int startRow;
    private final int movementBehaviour;
    private final int conversationIndex;

    private int row;
    private int col;

    public Person(Tile tile, int startCol, int startRow, int movementBehaviour, int conversationIndex) {
        this.tile = tile;
        this.startCol = startCol;
        this.startRow = startRow;
        this.movementBehaviour = movementBehaviour;
        this.conversationIndex = conversationIndex;
        col = startCol;
        row = startRow;
    }

    public Tile tile() {
        return tile;
    }

    public int startRow() {
        return startRow;
    }

    public int startCol() {
        return startCol;
    }

    public int movementBehaviour() {
        return movementBehaviour;
    }

    public int conversationIndex() {
        return conversationIndex;
    }

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }

    public void moveTo(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
