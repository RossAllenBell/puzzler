package com.rossallenbell.puzzler;

public class PuzzlePieceEdge {
    
    public static enum FEATURE {
        NONE, NUB_STRAIGHT, NUB_LEFT, NUB_RIGHT;

        public FEATURE invert() {
            switch(this) {
                case NONE: return NONE;
                case NUB_STRAIGHT: return NUB_STRAIGHT;
                case NUB_LEFT: return NUB_RIGHT;
                case NUB_RIGHT: return NUB_LEFT;
            }
            return NONE;
        }
    }
    
    public static final PuzzlePieceEdge FLAT = new PuzzlePieceEdge(FEATURE.NONE, false);
    public static final double FEATURE_WIDTH_RATIO = 0.25;
    
    private static FEATURE[] featuresWithoutNone = new FEATURE[]{FEATURE.NUB_STRAIGHT, FEATURE.NUB_LEFT, FEATURE.NUB_RIGHT};
    
    private FEATURE feature;
    private boolean inverted;
    
    public PuzzlePieceEdge(FEATURE feature, boolean inverted) {
        this.feature = feature;
        this.inverted = inverted;
    }
    
    public FEATURE getFeature() {
        return feature;
    }
    
    public boolean isInverted() {
        return inverted;
    }

    public PuzzlePieceEdge creatInverted() {
        return new PuzzlePieceEdge(this.feature.invert(), this.feature == FEATURE.NONE? false : !this.inverted);
    }

    public static PuzzlePieceEdge createRandom() {
        return new PuzzlePieceEdge(featuresWithoutNone[(int) (featuresWithoutNone.length * Math.random())], Math.random() > 0.5? true : false);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((feature == null) ? 0 : feature.hashCode());
        result = prime * result + (inverted ? 1231 : 1237);
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PuzzlePieceEdge other = (PuzzlePieceEdge) obj;
        if (feature != other.feature)
            return false;
        if (inverted != other.inverted)
            return false;
        return true;
    }
    
}
