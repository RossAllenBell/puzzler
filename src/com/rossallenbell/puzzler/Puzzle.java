package com.rossallenbell.puzzler;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Puzzle {
    
    private int width;
    private int height;
    private int pieceSize;
    
    private List<PuzzlePiece> pieces;

    public static Puzzle generate(BufferedImage image, int width, int height, int pieceSize) {
        Puzzle puzzle = new Puzzle(width, height, pieceSize);
        
        for (int y=0; y<puzzle.height / puzzle.pieceSize; y++) {
            for (int x=0; x<puzzle.width / puzzle.pieceSize; x++) {
                puzzle.pieces.add(new PuzzlePiece());
            }
        }
        
        return puzzle;
    }
    
    private Puzzle(int width, int height, int pieceSize) {
        this.width = width;
        this.height = height;
        this.pieceSize = pieceSize;
        this.pieces = new ArrayList<PuzzlePiece>();
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

    public List<PuzzlePiece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }
    
}
