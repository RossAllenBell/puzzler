package com.rossallenbell.puzzler;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rossallenbell.puzzler.PuzzlePieceEdge.FEATURE;

public class PuzzlePieceEdgeTest {
    
    @Test
    public void testCreatInverted() {
        assertEquals(PuzzlePieceEdge.FLAT, new PuzzlePieceEdge(FEATURE.NONE, false).creatInverted());
        assertEquals(new PuzzlePieceEdge(FEATURE.NUB_STRAIGHT, true), new PuzzlePieceEdge(FEATURE.NUB_STRAIGHT, false).creatInverted());
        assertEquals(new PuzzlePieceEdge(FEATURE.NUB_STRAIGHT, false), new PuzzlePieceEdge(FEATURE.NUB_STRAIGHT, true).creatInverted());
        assertEquals(new PuzzlePieceEdge(FEATURE.NUB_LEFT, false), new PuzzlePieceEdge(FEATURE.NUB_LEFT, true).creatInverted());
        assertEquals(new PuzzlePieceEdge(FEATURE.NUB_RIGHT, true), new PuzzlePieceEdge(FEATURE.NUB_RIGHT, false).creatInverted());
    }
    
}
