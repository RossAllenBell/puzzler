package com.rossallenbell.puzzler;

import static org.junit.Assert.*;

import org.junit.Test;

public class PuzzlerTest {
    
    @Test
    public void testGetPieceLength() {
        assertEquals(2, Puzzler.getPieceLength(10, 10, 3));
        assertEquals(10, Puzzler.getPieceLength(100, 100, 10));
    }
    
}
