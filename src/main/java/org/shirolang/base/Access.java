package org.shirolang.base;

/**
 *
 */
public enum Access {
    READWRITE, READ, INTERNAL;

    public boolean isReadWrite(){
        return this.equals(READWRITE);
    }

    public boolean isRead(){
        return this.equals(READ);
    }

    public boolean isInternal(){
        return this.equals(INTERNAL);
    }
}
