package com.rossallenbell.puzzler;

import static org.junit.Assert.*;

import org.junit.Test;

public class PuzzlerTest {
    
    @Test
    public void testGetPieceLength() {
        assertEquals(10, Puzzler.getPieceLength(100, 100, 10));
        assertEquals(3, Puzzler.getPieceLength(10, 10, 3));
        assertEquals(3, Puzzler.getPieceLength(20, 20, 6));
    }
    
}
