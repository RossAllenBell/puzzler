package com.rossallenbell.puzzler;

import static com.rossallenbell.puzzler.util.TestUtil.MOTORCYCLE;
import static com.rossallenbell.puzzler.util.TestUtil.MOTORCYCLE_1_1_80;
import static com.rossallenbell.puzzler.util.TestUtil.MOTORCYLEHEIGHT;
import static com.rossallenbell.puzzler.util.TestUtil.MOTORCYLEWIDTH;
import static com.rossallenbell.puzzler.util.TestUtil.PIECESIZE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.rossallenbell.puzzler.util.TestUtil;

public class PuzzleTest {
    
    @Test
    public void testGeneratePuzzle() throws IOException {
        Puzzle puzzle = Puzzle.generate(MOTORCYCLE, MOTORCYLEWIDTH, MOTORCYLEHEIGHT, PIECESIZE);
        
        assertEquals((MOTORCYLEWIDTH / PIECESIZE) * (MOTORCYLEHEIGHT / PIECESIZE), puzzle.getPieces().size());
        //assertTrue(TestUtil.bufferedImagesEqual(MOTORCYCLE_1_1_80, puzzle.getPieceAt(1,1).getImage()));
    }
    
}
