package shiro;

/**
 * Specifies the value of a index in a port's result tuple. It encapsulates 
 * a reference to the value and has a field that indicates the value's type.
 * It is used to make the identification of types easier and to prevent generics
 * from destroying an object's type.
 * @author jeffreyguenther
 */
public class Value{
    private Object value; // value of a tuple index
    private Class type;   // type of the value

    public Value(Object value, Class type) {
        this.value = value;
        this.type = type;
    }

    /**
     * Get the type of the value
     * @return the type of the value
     */
    public Class getType() {
        return type;
    }

    /**
     * Set the type of the value
     * @param type of the value to be set
     */
    public void setType(Class type) {
        this.type = type;
    }

    /**
     * Get the object value stored in the object
     * @return the object stored
     */
    public Object getValue() {
        return value;
    }

    /**
     * Set the object value
     * @param value is the object value to be set
     */
    public void setValue(Object value) {
        this.value = value;
    }
    
    public Value plus(Value v){
        if(this.type.equals(v.type) && this.type.equals(Double.class)){
            return Value.createDouble((Double) value + v.getValueAsDouble());
        }
        return null;
    }
    
    public Value subtract(Value v){
        if(this.type.equals(v.type) && this.type.equals(Double.class)){
            return Value.createDouble((Float) value - v.getValueAsFloat());
        }
        return null;
    }
    
    public Value times(Value v){
        if(this.type.equals(v.type) && this.type.equals(Double.class)){
            return Value.createDouble((Double) value * v.getValueAsDouble());
        }
        return null;
    }
    
    public Value over(Value v){
        if(this.type.equals(v.type) && this.type.equals(Double.class)){
            return Value.createDouble((Double) value / v.getValueAsFloat());
        }
        return null;
    }
    
    public Value mod(Value v){
        if(this.type.equals(v.type) && this.type.equals(Float.class)){
            return Value.createFloat((Float) value % v.getValueAsFloat());
        }
        return null;
    }
    
    /***
     * Create <code>Value</code> object from an <code>int</code>
     * @param value is the integer to be turned into a <code>Value</code> object
     * @return the wrapped <code>int</code>
     */
    public static Value createInteger(int value){
        return new Value(value, Integer.class);
    }
    
    /***
     * Create <code>Value</code> object from an <code>String</code>
     * @param value is the string to be turned into a <code>Value</code> object
     * @return the wrapped <code>String</code>
     */
    public static Value createString(String value){
        return new Value(value, String.class);
    }
    
    /***
     * Create <code>Value</code> object from an <code>float</code>
     * @param value is the float to be turned into a <code>Value</code> object
     * @return the wrapped <code>float</code>
     */
    public static Value createFloat(float value){
        return new Value(value, Float.class);
    }
    
    /***
     * Create <code>Value</code> object from an <code>float</code>
     * @param value is the float to be turned into a <code>Value</code> object
     * @return the wrapped <code>float</code>
     */
    public static Value createDouble(double value){
        return new Value(value, Double.class);
    }
    
    /**
     * Get the object as an <code>int</code>
     * @return the object value as an <code>Integer</code>
     */
    public Integer getValueAsInt(){
        return ((Number) value).intValue();
    }
    
    /**
     * Get the object as an <code>float</code>
     * @return the object value as an <code>Float</code>
     */
    public Float getValueAsFloat(){
        return ((Number) value).floatValue();
    }
    
    /**
     * Get the object as an <code>Double</code>
     * @return the object value as an <code>Double</code>
     */
    public Double getValueAsDouble(){
        return ((Number) value).doubleValue();
    }
    
    /**
     * Get the object as an <code>String</code>
     * @return the object value as an <code>String</code>
     */
    public String getValueAsString(){
        return (String) value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Value other = (Value) obj;
        if (this.value != other.value && (this.value == null || !this.value.equals(other.value))) {
            return false;
        }
        if (this.type != other.type && (this.type == null || !this.type.equals(other.type))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.value != null ? this.value.hashCode() : 0);
        hash = 79 * hash + (this.type != null ? this.type.hashCode() : 0);
        return hash;
    }
    
    @Override
    public String toString(){
        return value.toString();
    }
}
