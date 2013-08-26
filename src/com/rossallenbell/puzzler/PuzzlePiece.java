package com.rossallenbell.puzzler;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
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
        Graphics2D g = pieceBoundsImage.createGraphics();        
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.DST_IN);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setComposite(ac);
        
        for (DIR dir : DIR.values()) {
            BufferedImage featureImage = PuzzlePieceEdge.FEATURE_IMAGES.get(puzzlePiece.getEdge(dir).getFeature());
            BufferedImage scaledFeatureImage = new BufferedImage(pieceBoundsImage.getWidth(), pieceBoundsImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D gF = scaledFeatureImage.createGraphics();
            AffineTransform at = new AffineTransform();
            at.translate(scaledFeatureImage.getWidth() / 2, scaledFeatureImage.getHeight() / 2);
            at.rotate(dir.getRotation());
            at.translate(-scaledFeatureImage.getWidth() / 2, -scaledFeatureImage.getHeight() / 2);
            at.scale(scaledFeatureImage.getWidth() / (double) featureImage.getWidth(), scaledFeatureImage.getHeight() / (double) featureImage.getHeight());
            gF.drawImage(featureImage, at, null);
            gF.dispose();
            g.drawImage(scaledFeatureImage, 0, 0, scaledFeatureImage.getWidth(), scaledFeatureImage.getHeight(), null);
        }
        
        g.dispose();
        
        return pieceBoundsImage;
    }
    
    public BufferedImage getImage() {
        return image;
    }
    
    public PuzzlePieceEdge getEdge(DIR direction) {
        return edges.get(direction);
    }
    
}
