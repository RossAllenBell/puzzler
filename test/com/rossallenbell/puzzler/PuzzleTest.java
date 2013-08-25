package com.rossallenbell.puzzler;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.junit.BeforeClass;
import org.junit.Test;

public class PuzzleTest {
    
    private static BufferedImage image;
    
    @BeforeClass
    public static void initialize() throws IOException {
        URL resourceURL = PuzzlerTest.class.getClassLoader().getResource("test-resources/motorcycle.jpg");
        image = ImageIO.read(resourceURL);
    }
    
    @Test
    public void testGeneratePuzzle() {
        Puzzle puzzle = Puzzle.generate(image, 1280, 960, 80);
        
        assertEquals(16 * 12, puzzle.getPieces().size());
    }
    
}
