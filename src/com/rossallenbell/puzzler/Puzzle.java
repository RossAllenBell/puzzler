package com.rossallenbell.puzzler;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rossallenbell.puzzler.PuzzlePiece.DIR;

public class Puzzle {
    
    private int widthInPixels;
    private int heightInPixels;
    private int pieceSizeInPixels;
    
    private List<PuzzlePiece> pieces;
    
    public static Puzzle generate(BufferedImage image, int widthInPixels, int heightInPixels, int pieceSize) {
        Puzzle puzzle = new Puzzle(widthInPixels, heightInPixels, pieceSize);
        
        int outerPieceSize = puzzle.getPuzzlePieceActualSizeInPixels();
        for (int y = 0; y < puzzle.heightInPixels / puzzle.pieceSizeInPixels; y++) {
            for (int x = 0; x < puzzle.widthInPixels / puzzle.pieceSizeInPixels; x++) {
                BufferedImage pieceBoundsImage = new BufferedImage(outerPieceSize, outerPieceSize, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = pieceBoundsImage.createGraphics();
                
                int dx2 = pieceBoundsImage.getWidth();
                int dy2 = pieceBoundsImage.getHeight();
                int sx1 = (x * pieceSize) - (int) (PuzzlePieceEdge.FEATURE_WIDTH_RATIO * pieceSize);
                int sy1 = (y * pieceSize) - (int) (PuzzlePieceEdge.FEATURE_WIDTH_RATIO * pieceSize);
                int sx2 = (x * pieceSize) + pieceSize + (int) (PuzzlePieceEdge.FEATURE_WIDTH_RATIO * pieceSize);
                int sy2 = (y * pieceSize) + pieceSize + (int) (PuzzlePieceEdge.FEATURE_WIDTH_RATIO * pieceSize);
                g.drawImage(image, 0, 0, dx2, dy2, sx1, sy1, sx2, sy2, null);
                
                g.dispose();
                
                PuzzlePieceEdge north = y == 0? PuzzlePieceEdge.FLAT : puzzle.getPieceAt(x, y-1).getEdge(DIR.SOUTH).creatInverted();
                PuzzlePieceEdge east = x + 1 == puzzle.widthInPixels / puzzle.pieceSizeInPixels? PuzzlePieceEdge.FLAT : PuzzlePieceEdge.createRandom();
                PuzzlePieceEdge south = y + 1 == puzzle.heightInPixels / puzzle.pieceSizeInPixels? PuzzlePieceEdge.FLAT : PuzzlePieceEdge.createRandom();
                PuzzlePieceEdge west = x == 0? PuzzlePieceEdge.FLAT : puzzle.getPieceAt(x-1, y).getEdge(DIR.EAST).creatInverted();
                
                puzzle.pieces.add(new PuzzlePiece(pieceBoundsImage, north, east, south, west));
            }
        }
        
        return puzzle;
    }
    
    private Puzzle(int width, int height, int pieceSize) {
        this.widthInPixels = width;
        this.heightInPixels = height;
        this.pieceSizeInPixels = pieceSize;
        this.pieces = new ArrayList<PuzzlePiece>();
    }
    
    public int getPuzzlePieceActualSizeInPixels() {
        return pieceSizeInPixels + (int) (PuzzlePieceEdge.FEATURE_WIDTH_RATIO * pieceSizeInPixels * 2);
    }
    
    public int getWidthInPixels() {
        return widthInPixels;
    }
    
    public int getHeightInPixels() {
        return heightInPixels;
    }
    
    public int getWidthInPieces() {
        return widthInPixels / pieceSizeInPixels;
    }
    
    public int getHeightInPieces() {
        return heightInPixels / pieceSizeInPixels;
    }
    
    public int getPieceSizeInPixels() {
        return pieceSizeInPixels;
    }
    
    public List<PuzzlePiece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }
    
    public PuzzlePiece getPieceAt(int x, int y) {
        return pieces.get((y * (widthInPixels / pieceSizeInPixels)) + x);
    }
    
}
