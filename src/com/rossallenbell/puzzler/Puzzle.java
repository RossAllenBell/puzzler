package com.rossallenbell.puzzler;

public class Puzzle {
    
    private int width;
    private int height;
    private int pieceSize;
    
    public Puzzle(int width, int height, int pieceSize) {
        this.width = width;
        this.height = height;
        this.pieceSize = pieceSize;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPieceSize() {
        return pieceSize;
    }
    
}
