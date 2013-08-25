package com.rossallenbell.puzzler;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class PuzzlePiece {
    
    public static enum DIR {
        NORTH, EAST, SOUTH, WEST;
        
        public DIR invert() {
            switch(this) {
                case NORTH: return SOUTH;
                case EAST: return WEST;
                case SOUTH: return NORTH;
                case WEST: return EAST;
            }
            return NORTH;
        }
    }
    
    private BufferedImage image;
    private Map<DIR, PuzzlePieceEdge> edges;
    
    public PuzzlePiece(BufferedImage pieceImage, PuzzlePieceEdge north, PuzzlePieceEdge east, PuzzlePieceEdge south, PuzzlePieceEdge west) {
        this.image = pieceImage;
        edges = new HashMap<>();
        edges.put(DIR.NORTH, north);
        edges.put(DIR.EAST, east);
        edges.put(DIR.SOUTH, south);
        edges.put(DIR.WEST, west);
    }
    
    public BufferedImage getImage() {
        return image;
    }
    
    public PuzzlePieceEdge getEdge(DIR direction) {
        return edges.get(direction);
    }
    
}
