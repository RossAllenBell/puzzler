package com.rossallenbell.puzzler;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.rossallenbell.puzzler.util.TestUtil;

public class TestScript {
    
    public static void main(String[] args) throws IOException {
        System.out.println("starting test script");
        Puzzle puzzle = new Puzzler().puzzlefy(TestUtil.MOTORCYCLE, TestUtil.PIECESIZE);
        BufferedImage fullPuzzledImage = new BufferedImage(puzzle.getPuzzlePieceActualSizeInPixels() * puzzle.getWidthInPieces(), puzzle.getPuzzlePieceActualSizeInPixels() * puzzle.getHeightInPieces(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D fpiG = fullPuzzledImage.createGraphics();
        for (int x = 0; x < puzzle.getWidthInPixels() / puzzle.getPieceSizeInPixels(); x++) {
            for (int y = 0; y < puzzle.getHeightInPixels() / puzzle.getPieceSizeInPixels(); y++) {
                File file = new File("test/debugging-output/" + y + "_" + x + ".png");
                file.createNewFile();
                BufferedImage pieceImage = puzzle.getPieceAt(x, y).getImage();
                ImageIO.write(pieceImage, "png", file);
                fpiG.drawImage(pieceImage, x * puzzle.getPuzzlePieceActualSizeInPixels(),  y * puzzle.getPuzzlePieceActualSizeInPixels(), pieceImage.getWidth(), pieceImage.getHeight(), null);
            }
        }
        File file = new File("test/debugging-output/full-puzzled-image.png");
        file.createNewFile();
        ImageIO.write(fullPuzzledImage, "png", file);
        System.out.println("finished test script");
    }
    
}
