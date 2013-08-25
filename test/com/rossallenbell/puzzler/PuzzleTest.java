package com.rossallenbell.puzzler;

import static com.rossallenbell.puzzler.util.TestUtil.MOTORCYCLE;
import static com.rossallenbell.puzzler.util.TestUtil.MOTORCYLEHEIGHT;
import static com.rossallenbell.puzzler.util.TestUtil.MOTORCYLEWIDTH;
import static com.rossallenbell.puzzler.util.TestUtil.PIECESIZE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.io.IOException;

import org.junit.Test;

import com.rossallenbell.puzzler.PuzzlePiece.DIR;

//import static com.rossallenbell.puzzler.util.TestUtil.MOTORCYCLE_1_1_80;
//import static org.junit.Assert.assertTrue;
//import com.rossallenbell.puzzler.util.TestUtil;

public class PuzzleTest {
    
    @Test
    public void testGeneratePuzzle() throws IOException {
        Puzzle puzzle = Puzzle.generate(MOTORCYCLE, MOTORCYLEWIDTH, MOTORCYLEHEIGHT, PIECESIZE);
        
        assertEquals((MOTORCYLEWIDTH / PIECESIZE) * (MOTORCYLEHEIGHT / PIECESIZE), puzzle.getPieces().size());
        //assertTrue(TestUtil.bufferedImagesEqual(MOTORCYCLE_1_1_80, puzzle.getPieceAt(1,1).getImage()));
    }
    
    @Test
    public void testCornerPieces() throws IOException {
        Puzzle puzzle = Puzzle.generate(MOTORCYCLE, MOTORCYLEWIDTH, MOTORCYLEHEIGHT, PIECESIZE);
        
        assertEquals(PuzzlePieceEdge.FLAT, puzzle.getPieceAt(0, 0).getEdge(DIR.NORTH));
        assertEquals(PuzzlePieceEdge.FLAT, puzzle.getPieceAt(0, 0).getEdge(DIR.WEST));
        assertEquals(PuzzlePieceEdge.FLAT, puzzle.getPieceAt(0, (MOTORCYLEHEIGHT / PIECESIZE) - 1).getEdge(DIR.SOUTH));
        assertEquals(PuzzlePieceEdge.FLAT, puzzle.getPieceAt(0, (MOTORCYLEHEIGHT / PIECESIZE) - 1).getEdge(DIR.WEST));
        assertEquals(PuzzlePieceEdge.FLAT, puzzle.getPieceAt((MOTORCYLEWIDTH / PIECESIZE) - 1, 0).getEdge(DIR.NORTH));
        assertEquals(PuzzlePieceEdge.FLAT, puzzle.getPieceAt((MOTORCYLEWIDTH / PIECESIZE) - 1, 0).getEdge(DIR.EAST));
        assertEquals(PuzzlePieceEdge.FLAT, puzzle.getPieceAt((MOTORCYLEWIDTH / PIECESIZE) - 1, (MOTORCYLEHEIGHT / PIECESIZE) - 1).getEdge(DIR.SOUTH));
        assertEquals(PuzzlePieceEdge.FLAT, puzzle.getPieceAt((MOTORCYLEWIDTH / PIECESIZE) - 1, (MOTORCYLEHEIGHT / PIECESIZE) - 1).getEdge(DIR.EAST));
    }
    
    @Test
    public void test1_1Piece() throws IOException {
        Puzzle puzzle = Puzzle.generate(MOTORCYCLE, MOTORCYLEWIDTH, MOTORCYLEHEIGHT, PIECESIZE);
        
        assertNotSame(PuzzlePieceEdge.FEATURE.NONE, puzzle.getPieceAt(1, 1).getEdge(DIR.NORTH).getFeature());
        assertNotSame(PuzzlePieceEdge.FEATURE.NONE, puzzle.getPieceAt(1, 1).getEdge(DIR.EAST).getFeature());
        assertNotSame(PuzzlePieceEdge.FEATURE.NONE, puzzle.getPieceAt(1, 1).getEdge(DIR.SOUTH).getFeature());
        assertNotSame(PuzzlePieceEdge.FEATURE.NONE, puzzle.getPieceAt(1, 1).getEdge(DIR.WEST).getFeature());
    }
    
}
