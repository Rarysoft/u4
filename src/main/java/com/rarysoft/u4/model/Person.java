package com.rarysoft.u4.model;

public class Person {
    private final int tile;
    private final int startX;
    private final int startY;
    private final int movementBehaviour;
    private final int conversationIndex;

    public Person(int tile, int startX, int startY, int movementBehaviour, int conversationIndex) {
        this.tile = tile;
        this.startX = startX;
        this.startY = startY;
        this.movementBehaviour = movementBehaviour;
        this.conversationIndex = conversationIndex;
    }

    public int tile() {
        return tile;
    }

    public int startX() {
        return startX;
    }

    public int startY() {
        return startY;
    }

    public int movementBehaviour() {
        return movementBehaviour;
    }

    public int conversationIndex() {
        return conversationIndex;
    }
}
