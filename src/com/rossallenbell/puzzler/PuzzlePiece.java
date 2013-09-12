package com.rossallenbell.puzzler;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class PuzzlePiece {
    
    public static enum DIR {
        NORTH {
            
            @Override
            public DIR invert() {
                return SOUTH;
            }
            
            @Override
            public double getRotation() {
                return 0;
            }
        },
        EAST {
            @Override
            public DIR invert() {
                return WEST;
            }
            
            @Override
            public double getRotation() {
                return Math.PI / 2;
            }
        },
        SOUTH {
            @Override
            public DIR invert() {
                return NORTH;
            }
            
            @Override
            public double getRotation() {
                return Math.PI;
            }
        },
        WEST {
            @Override
            public DIR invert() {
                return EAST;
            }
            
            @Override
            public double getRotation() {
                return Math.PI * 1.5;
            }
        };
        
        public abstract DIR invert();
        
        public abstract double getRotation();
    }
    
    private BufferedImage image;
    private Map<DIR, PuzzlePieceEdge> edges;
    
    public PuzzlePiece(BufferedImage pieceBoundsImage, PuzzlePieceEdge north, PuzzlePieceEdge east, PuzzlePieceEdge south, PuzzlePieceEdge west) {
        edges = new HashMap<>();
        edges.put(DIR.NORTH, north);
        edges.put(DIR.EAST, east);
        edges.put(DIR.SOUTH, south);
        edges.put(DIR.WEST, west);
        
        this.image = PuzzlePiece.cutPieceImage(pieceBoundsImage, this);
    }
    
    private static BufferedImage cutPieceImage(BufferedImage pieceBoundsImage, PuzzlePiece puzzlePiece) {
        double edgeThickness = pieceBoundsImage.getHeight() / ((1 / PuzzlePieceEdge.FEATURE_WIDTH_RATIO) + 2);
        
        Graphics2D g = pieceBoundsImage.createGraphics();        
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        for (DIR dir : DIR.values()) {
            BufferedImage scaledFeatureImage = scaleImage(PuzzlePieceEdge.FEATURE_IMAGES.get(puzzlePiece.getEdge(dir).getFeature()), pieceBoundsImage.getWidth(), pieceBoundsImage.getHeight());
            AffineTransform rotateToDir = new AffineTransform();
            double rotation = dir.getRotation();
            AlphaComposite includeOrExcludeFeatureShape = AlphaComposite.getInstance(AlphaComposite.DST_IN);
            
            
            if (puzzlePiece.getEdge(dir).isInverted()) {
                AffineTransform dropDown = new AffineTransform();
                dropDown.translate(0, pieceBoundsImage.getWidth() - (edgeThickness * 2));
                BufferedImage tempBI = new BufferedImage(scaledFeatureImage.getWidth(), scaledFeatureImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D tempG = tempBI.createGraphics();
                tempG.drawImage(scaledFeatureImage, dropDown, null);
                tempG.dispose();
                scaledFeatureImage = tempBI;
                
                rotation += Math.PI;
                rotation = rotation % (2 * Math.PI);
                includeOrExcludeFeatureShape = AlphaComposite.getInstance(AlphaComposite.DST_OUT);
            }
            
            rotateToDir.translate(pieceBoundsImage.getWidth() / 2, pieceBoundsImage.getHeight() / 2);
            rotateToDir.rotate(rotation);
            rotateToDir.translate(-pieceBoundsImage.getWidth() / 2, -pieceBoundsImage.getHeight() / 2);
            
            
            BufferedImage rotatedFeatureImage = new BufferedImage(pieceBoundsImage.getWidth(), pieceBoundsImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D gF = rotatedFeatureImage.createGraphics();
            gF.drawImage(scaledFeatureImage, rotateToDir, null);
            gF.dispose();
            g.setComposite(includeOrExcludeFeatureShape);
            g.drawImage(rotatedFeatureImage, 0, 0, rotatedFeatureImage.getWidth(), rotatedFeatureImage.getHeight(), null);
        }
        
        g.dispose();
        
        return pieceBoundsImage;
    }
    
    private static BufferedImage scaleImage(BufferedImage image, int width, int height) {
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
        AffineTransform at = new AffineTransform();
        at.scale(width / (double) originalWidth, height / (double) originalHeight);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        return scaleOp.filter(image, null);
    }
    
    public BufferedImage getImage() {
        return image;
    }
    
    public PuzzlePieceEdge getEdge(DIR direction) {
        return edges.get(direction);
    }
    
}
