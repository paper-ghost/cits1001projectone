import java.awt.Color;

/**
 * Project library class: do not change this class.
 * A stroke is a single line with width, color
 * with x,y coordinates for start and end of the line.
 * 
 * @author Rachel Cardell-Oliver
 * @version Feb 2013
 */
public class Stroke
{
    
    private int x1pos;
    private int y1pos;

    private int x2pos;
    private int y2pos;
    
    private Color color;
    private float width; 


    public Stroke(int x1pos, int y1pos, int x2pos, int y2pos, 
                  Color color, float width)
    {
        this.x1pos = x1pos;
        this.y1pos = y1pos;
        this.x2pos = x2pos;
        this.y2pos = y2pos;
        this.color = color;
        this.width = width;
    }
    
    /**
     * Getter methods for stroke attributes
     */
    public Color getColor() { return color; }
    
    public float getWidth() { return width; }
    
    public int getX1() { return x1pos; }
    
    public int getY1() { return y1pos; }
    
    public int getX2() { return x2pos; }
    
    public int getY2() { return y2pos; }


    /**
     * Set the stroke color.
     * @param newColor  The color to use for subsequent drawing operations.
     */
    public void setColor(Color newColor)
    {
        color = newColor;
    }
    
    /**
     * Set the stroke width.
     * @param newWidth  The width to use for subsequent drawing operations.
    */
    public void setWidth(int newWidth)
    {
        width = newWidth;
    }
    
    /**
     * Change the start and end points of a line while keeping
     * its original width and color.
     * @param x1 int coordinates of one end of the line
     * @param y1 int
     * @param x2 int coordinates of the other end of the line
     * @param y2 int
     */
    public void setPosition(int x1, int y1, int x2, int y2)
    {
        x1pos = x1;
        y1pos = y1;
        x2pos = x2;
        y2pos = y2;
    }
    
    /**
     * Move the specified distance in the current direction. 
     * If the pen is down, leave a line on the canvas.
     * @param distance  The distance to move forward from the 
     * current location.
   */
    public void moveStroke(int xDelta, int yDelta)
    {
        x1pos = x1pos + xDelta;
        y1pos = y1pos + yDelta;
        
        x2pos = x2pos + xDelta;
        y2pos = y2pos + yDelta;
        
    }
    
   /**
    * Create and return a cloned stroke object using 
    * the same values as this object. 
    */
  public Stroke clone(String clonename)
  {
      Stroke newStroke;
      newStroke = new Stroke(x1pos,y1pos,x2pos,y2pos, color, width);
      return newStroke;
  }

}
