package org.shirolang.interpreter.ast;

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

    @Override
    public String toString() {
        switch(this){
            case READWRITE:
                return "input";
            case READ:
                return "output";
            default:
                return "";
        }
    }
}
