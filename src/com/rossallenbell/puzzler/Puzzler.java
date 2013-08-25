package com.rossallenbell.puzzler;

import java.awt.image.BufferedImage;

public class Puzzler {
    
    public Puzzle puzzlefy(BufferedImage image, int width, int height, int pieceSize) {
        return new Puzzle(width, height, pieceSize);
    }
    
}
