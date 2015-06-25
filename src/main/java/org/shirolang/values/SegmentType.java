package org.shirolang.values;

/**
 * Describes th types of segments
 */
public enum SegmentType {
    SIMPLE, INPUT, OUTPUT;

    /**
     * Checks if the segment type is plain
     * @return true if the segment type is plain
     */
    public boolean isSimple(){
        return this.equals(SIMPLE);
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
        return this.equals(OUTPUT);
    }
}
