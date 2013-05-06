import java.awt.Color;
import java.util.Random;

/**
 * Project library class: do not change this class.
 * A pen can be used to add to a drawing on a mycanvas. The pen maintains a position, direction, color,
 * and an up/down state. The pen can be moved across the mycanvas. If the pen is down, it 
 * leaves a line on the canvas when moved. (If it is up, it will not draw a line.)
 * Pen is used to create drawing objects and also to render those drawings on a canvas.
 * @author Michael Kölling & David J. Barnes
 * @version Modified RCO Feb 2013, Original 2011.07.31
 */
public class Pen
{

    // default constants for canvas size
    private static final int CANVAS_WIDTH = 500;
    private static final int CANVAS_HEIGHT = 500;
    private static final int TITLE_POSITION = 475;

    // attributes for the pen
    private int xPosition;
    private int yPosition;
    private int rotation;
    private Color color;
    private float width; //stroke width
    //private String partName; //name of the part currently being drawn
    private boolean penDown;

    private Drawing mydrawing;
    private Canvas mycanvas;

    /**
     * Create a new Pen with its own canvas. The pen will create a new canvas for 
     * itself to draw on, and start in the default state (centre of canvas, direction
     * right, color black, pen down).
     */
    public Pen()
    {
        this (CANVAS_HEIGHT/2, CANVAS_WIDTH/2);
    }

    /**
     * Create a new Pen for a given canvas. The direction is initially 0 (to the right),
     * the color is black, and the pen is down.
     *
     * @param xPos  the initial horizontal coordinate of the pen
     * @param yPos  the initial vertical coordinate of the pen
     */
    public Pen(int xPos, int yPos)
    {
        xPosition = xPos;
        yPosition = yPos;
        rotation = 0;
        penDown = true;
        color = Color.BLACK;
        width = 1.0f;
        //partName = ""; //not yet defined
        mydrawing = new Drawing();
        mycanvas = new Canvas("Demo",CANVAS_HEIGHT, CANVAS_WIDTH); //noname
    }

    public Drawing getDrawing()
    {
        return mydrawing;
    }

    /**
     * Move the specified distance in the current direction. If the pen is down, 
     * leave a line on the canvas.
     * 
     * @param distance  The distance to move forward from the current location.
     */
    public void move(int distance)
    {
        double angle = Math.toRadians(rotation);
        int newX = (int) Math.round(xPosition + Math.cos(angle) * distance);
        int newY = (int) Math.round(yPosition + Math.sin(angle) * distance);

        moveTo(newX, newY);
    }

    /**
     * Move to the specified location. If the pen is down, leave a line on the canvas.
     * 
     * @param x   The x-coordinate to move to.
     * @param y   The y-coordinate to move to.
     */
    public void moveTo(int x, int y)
    {
        if (penDown) {
            Stroke s = new Stroke(xPosition, yPosition, x, y, color, width); //, partName);
            mydrawing.addStroke(s);
        }
        xPosition = x;
        yPosition = y;
    }

    /**
     * Turn the specified amount (out of a 360 degree circle) clockwise from the current 
     * rotation.
     * 
     * @param degrees  The amount of degrees to turn. (360 is a full circle.)
     */
    public void turn(int degrees)
    {
        rotation = rotation + degrees;
    }

    /**
     * Turn to the specified direction. 0 is right, 90 is down, 180 is left, 270 is up.
     * 
     * @param angle  The angle to turn to.
     */
    public void turnTo(int angle)
    {
        rotation = angle;
    }

    /**
     * Set the drawing color.
     * 
     * @param newColor  The color to use for subsequent drawing operations.
     */
    public void setColor(Color newColor)
    {
        color = newColor;
    }

    public void setWidth(float newWidth)
    {
        width = newWidth;
    }

    /*public void setPartName(String currentPartName)
    {
        partName = currentPartName;
    }*/

    /**
     * Lift the pen up. Moving afterwards will not leave a line on the mycanvas.
     */
    public void penUp()
    {
        penDown = false;
    }

    /**
     * Put the pen down. Moving afterwards will leave a line on the mycanvas.
     */
    public void penDown()
    {
        penDown = true;
    }

    //TODO maybe canvas stuff should be another class
    /**
     * clear screen 
     */
    public void clearDisplay()
    {
        mycanvas.erase();
    }

    public void delay(int milliseconds)
    {
        mycanvas.wait(milliseconds);
    }

    public void drawDisplay() {
        int size = mydrawing.numberOfStrokes();
        for (int i=0; i<size; i++)
        {
            Stroke s = mydrawing.getStroke(i);
            mycanvas.setForegroundColor(s.getColor());
            mycanvas.setStrokeWidth(s.getWidth()); //stroke width
            mycanvas.drawLine(s.getX1(), s.getY1(), s.getX2(), s.getY2());
        }
    }

}
