package com.rossallenbell.puzzler;

import java.awt.image.BufferedImage;

public class Puzzler {
    
    public Puzzle puzzlefy(BufferedImage image, int desiredPieceLengthInPixels) {
        return new Puzzle();
    }
    
    public static int getPieceLength(int imageWidth, int imageHeight, int desired) {
        if (imageWidth % desired == 0 && imageHeight % desired == 0) {
            return desired;
        }
        
        return desired;
    }
    
}
