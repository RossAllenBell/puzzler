package com.rossallenbell.puzzler;

import java.awt.image.BufferedImage;

public class Puzzler {

    public Puzzle puzzlefy(BufferedImage image, int pieceSize) {
        return puzzlefy(image, image.getWidth(), image.getHeight(), pieceSize);
    }
    
    public Puzzle puzzlefy(BufferedImage image, int width, int height, int pieceSize) {
        if (width % pieceSize != 0 || height % pieceSize != 0) {
            throw new IllegalArgumentException(String.format("Cannot use a piece size [%s] that doesn't divide evenly into the width [%s] and height[%s]", pieceSize, width, height));
        }
        
        return Puzzle.generate(image, width, height, pieceSize);
    }
    
}
