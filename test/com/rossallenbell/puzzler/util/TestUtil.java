package com.rossallenbell.puzzler.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.rossallenbell.puzzler.PuzzlerTest;

public class TestUtil {
    
    public static BufferedImage MOTORCYCLE;
    public static BufferedImage MOTORCYCLE_1_1_80;
    
    static {
        try {
            MOTORCYCLE = ImageIO.read(PuzzlerTest.class.getClassLoader().getResource("test-resources/motorcycle.jpg"));
            MOTORCYCLE_1_1_80 = ImageIO.read(PuzzlerTest.class.getClassLoader().getResource("test-resources/motorcycle_1_1_80.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static final int MOTORCYLEWIDTH = MOTORCYCLE.getWidth();
    public static final int MOTORCYLEHEIGHT = MOTORCYCLE.getHeight();
    public static final int PIECESIZE = MOTORCYCLE_1_1_80.getWidth();
    
    public static boolean bufferedImagesEqual(BufferedImage imageA, BufferedImage imageB) {
        int width;
        int height;
        
        if (imageA.getWidth() == (width = imageB.getWidth()) && imageA.getHeight() == (height = imageB.getHeight())) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (imageA.getRGB(x, y) != imageB.getRGB(x, y)) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        
        return true;
    }
    
}
