package com.rossallenbell.puzzler;

import java.awt.image.BufferedImage;

public class PuzzlePiece {
    
    private BufferedImage image;

    public PuzzlePiece(BufferedImage pieceImage) {
        this.image = pieceImage;
    }

    public BufferedImage getImage() {
        return image;
    }
    
}
