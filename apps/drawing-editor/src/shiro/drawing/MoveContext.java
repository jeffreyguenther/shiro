package shiro.drawing;
/**
 * Info needed to drag a node around the canvas
 * A copy of http://docs.oracle.com/javafx/2/events/DraggablePanelsExample.java.htm
 * @author jeffreyguenther
 *
 */
public class MoveContext {
	private double mouseAnchorX;
    private double mouseAnchorY;
    
    private double initialTranslateX;
    private double initialTranslateY;

    public MoveContext(){
    	this(0,0,0,0);
    }

    /**
     * Create a move context
     * @param mouseAnchorX - start mouse x position
     * @param mouseAnchorY - start mouse y position
     * @param initialTranslateX - initial object x position
     * @param initialTranslateY - initial object y position
     */
	public MoveContext(double mouseAnchorX, double mouseAnchorY,
			double initialTranslateX, double initialTranslateY) {
		this.mouseAnchorX = mouseAnchorX;
		this.mouseAnchorY = mouseAnchorY;
		this.initialTranslateX = initialTranslateX;
		this.initialTranslateY = initialTranslateY;
	}
    
	public double getDragDestX(double destX){
        return initialTranslateX + destX - mouseAnchorX;
    }
    
    public double getDragDestY(double destY){
        return initialTranslateY + destY - mouseAnchorY;
    }

    /**
     * Get the value of initialTranslateY
     *
     * @return the value of initialTranslateY
     */
    public double getInitialTranslateY() {
        return initialTranslateY;
    }

    /**
     * Set the value of initialTranslateY
     *
     * @param initialTranslateY new value of initialTranslateY
     */
    public void setInitialTranslateY(double initialTranslateY) {
        this.initialTranslateY = initialTranslateY;
    }

    /**
     * Get the value of initialTranslateX
     *
     * @return the value of initialTranslateX
     */
    public double getInitialTranslateX() {
        return initialTranslateX;
    }

    /**
     * Set the value of initialTranslateX
     *
     * @param initialTranslateX new value of initialTranslateX
     */
    public void setInitialTranslateX(double initialTranslateX) {
        this.initialTranslateX = initialTranslateX;
    }

    /**
     * Get the value of mouseAnchorY
     *
     * @return the value of mouseAnchorY
     */
    public double getMouseAnchorY() {
        return mouseAnchorY;
    }

    /**
     * Set the value of mouseAnchorY
     *
     * @param mouseAnchorY new value of mouseAnchorY
     */
    public void setMouseAnchorY(double mouseAnchorY) {
        this.mouseAnchorY = mouseAnchorY;
    }


    /**
     * Get the value of mouseAnchorX
     *
     * @return the value of mouseAnchorX
     */
    public double getMouseAnchorX() {
        return mouseAnchorX;
    }

    /**
     * Set the value of mouseAnchorX
     *
     * @param mouseAnchorX new value of mouseAnchorX
     */
    public void setMouseAnchorX(double mouseAnchorX) {
        this.mouseAnchorX = mouseAnchorX;
    }

    @Override
    public String toString() {
        return "MoveContext{" + "mouseAnchorX=" + mouseAnchorX + ", mouseAnchorY=" + mouseAnchorY + ", initialTranslateX=" + initialTranslateX + ", initialTranslateY=" + initialTranslateY + '}';
    }
    
}
