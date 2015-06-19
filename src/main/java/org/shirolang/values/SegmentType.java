package org.shirolang.values;

/**
 *
 */
public enum SegmentType {
    PLAIN, INPUT, OUTPUT;

    /**
     * Checks if the segment type is plain
     * @return true if the segment type is plain
     */
    public boolean isPlain(){
        return this.equals(PLAIN);
    }

    /**
     * Checks if the segment type is input
     * @return true if the segment type is input
     */
    public boolean isInput(){
        return this.equals(INPUT);
    }

    /**
     * Checks if the segment type is output
     * @return true if the segment type is output
     */
    public boolean isOutput(){
        return this.equals(PLAIN);
    }
}
