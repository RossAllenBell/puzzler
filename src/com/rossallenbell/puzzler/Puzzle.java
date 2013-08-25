package com.rossallenbell.puzzler;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rossallenbell.puzzler.PuzzlePiece.DIR;

public class Puzzle {
    
    private int width;
    private int height;
    private int pieceSize;
    
    private List<PuzzlePiece> pieces;
    
    public static Puzzle generate(BufferedImage image, int width, int height, int pieceSize) {
        Puzzle puzzle = new Puzzle(width, height, pieceSize);
        
        for (int y = 0; y < puzzle.height / puzzle.pieceSize; y++) {
            for (int x = 0; x < puzzle.width / puzzle.pieceSize; x++) {
                BufferedImage subImage = image.getSubimage(x * pieceSize, y * pieceSize, pieceSize, pieceSize);
                BufferedImage copiedSubImage = new BufferedImage(subImage.getWidth(), subImage.getHeight(), subImage.getType());
                Graphics2D g = copiedSubImage.createGraphics();
                g.drawImage(subImage, 0, 0, null);
                g.dispose();
                
                PuzzlePieceEdge north = y == 0? PuzzlePieceEdge.FLAT : puzzle.getPieceAt(x, y-1).getEdge(DIR.SOUTH).creatInverted();
                PuzzlePieceEdge east = x + 1 == puzzle.width / puzzle.pieceSize? PuzzlePieceEdge.FLAT : PuzzlePieceEdge.createRandom();
                PuzzlePieceEdge south = y + 1 == puzzle.height / puzzle.pieceSize? PuzzlePieceEdge.FLAT : PuzzlePieceEdge.createRandom();
                PuzzlePieceEdge west = x == 0? PuzzlePieceEdge.FLAT : puzzle.getPieceAt(x-1, y).getEdge(DIR.EAST).creatInverted();
                
                puzzle.pieces.add(new PuzzlePiece(copiedSubImage, north, east, south, west));
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
    
    public PuzzlePiece getPieceAt(int x, int y) {
        return pieces.get((y * (width / pieceSize)) + x);
    }
    
}
