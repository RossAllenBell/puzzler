package com.rossallenbell.puzzler;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.rossallenbell.puzzler.util.TestUtil;

public class TestScript {
    
    public static void main(String[] args) throws IOException {
        Puzzle puzzle = new Puzzler().puzzlefy(TestUtil.MOTORCYCLE, TestUtil.PIECESIZE);
        for (int x = 0; x < puzzle.getWidth() / puzzle.getPieceSize(); x++) {
            for (int y = 0; y < puzzle.getHeight() / puzzle.getPieceSize(); y++) {
                File file = new File("test/debugging-output/" + y + "_" + x + ".png");
                file.createNewFile();
                ImageIO.write(puzzle.getPieceAt(x, y).getImage(), "png", file);
            }
        }
    }
    
}
