package com.rarysoft.u4.model.npc;

public class Person {
    private final NonPlayerCharacter nonPlayerCharacter;
    private final int tileIndex;
    private final int startCol;
    private final int startRow;
    private final MovementBehaviour movementBehaviour;
    private final int conversationIndex;

    private int row;
    private int col;

    public Person(NonPlayerCharacter nonPlayerCharacter, int tileIndex, int startRow, int startCol, MovementBehaviour movementBehaviour, int conversationIndex) {
        this.nonPlayerCharacter = nonPlayerCharacter;
        this.tileIndex = tileIndex;
        this.startCol = startCol;
        this.startRow = startRow;
        this.movementBehaviour = movementBehaviour;
        this.conversationIndex = conversationIndex;
        col = startCol;
        row = startRow;
    }

    public NonPlayerCharacter nonPlayerCharacter() {
        return nonPlayerCharacter;
    }

    public int tileIndex() {
        return tileIndex;
    }

    public int startRow() {
        return startRow;
    }

    public int startCol() {
        return startCol;
    }

    public MovementBehaviour movementBehaviour() {
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
