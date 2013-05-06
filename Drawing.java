import java.util.ArrayList;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.AffineTransform;


/**
 * Project: Add your name and date in the header and implement
 * this class for the project.
 * A drawing is a collection of line strokes.
 * Its methods create and modify the drawing.
 * 
 * @author  Timothy Ellis - 21199844
 * @version 5.00
 */
public class Drawing
{
    private ArrayList<Stroke> drawing;
    
    /**
     * Constructor for objects of class drawing
     */
    public Drawing()
    {
        drawing = new ArrayList<Stroke>();
    }
    
    /**
     * Add a new stroke (line) to the drawing
     * @param  s  Stroke to add
     */
    public void addStroke(Stroke s)
    {
        drawing.add(s);
    }

     /**
     * Remove a new stroke (line) from the drawing
     * @param  s  Stroke to be removed
     */
    public void removeStroke(Stroke s) 
    {
        drawing.remove(s);
    }
    
    /**
     * Return the stroke at position i 
     * @param  i  int the position of the Stroke object
     * @return Stroke the stroke object at position i
     */
    public Stroke getStroke(int i)
    {
        return drawing.get(i);
    }
    
    /**
     * Size of the drawing
     * @return  int the number of Strokes in the drawing
     */
    public int numberOfStrokes() 
    {   
        return drawing.size();
    }
    
    /**
     * Move the whole drawing 
     * @param xDelta int pixels of horizontal move
     * @param yDelta int pixels of vertical move
     */
    public void move(int xDelta, int yDelta)
    {
        for (Stroke s : drawing) {
            s.setPosition(s.getX1()+xDelta, s.getY1()+yDelta, s.getX2()+xDelta, s.getY2()+yDelta);
        }
    }
    
   
    
    /**
     * Transform the drawing to its mirror reflection 
     * around a vertical line with given x value
     * e.g. line (0,0) to (2,2) reflected on x=4 line 
     * gives line (6,2) (8,0)
     * @param xaxis int the x value of the line to reflect on
     */
    public void flipHorizontal(int xaxis) 
    { 
        for (Stroke s: drawing) {
          s.setPosition(s.getX1()+(xaxis - s.getX1())*2,  s.getY1(), s.getX2()+(xaxis - s.getX2())*2, s.getY2());
       }
    }
    
    /**
     * Transform the drawing to its mirror reflection 
     * around a horizontal line with given y value
     * e.g. line (0,0) to (2,2) reflected on y=3 line 
     * gives line (0,6) (2,4)
     * @param yaxis int the y value of the line to reflect on
     */
    public void flipVertical(int yaxis) 
    { 
       for (Stroke s: drawing) {
           s.setPosition(s.getX1(), s.getY1()+(yaxis - s.getY1())*2, s.getX2(), s.getY2()+(yaxis- s.getY2())*2);
       }
    }
    
    /**
     * Create a list of all colours used in the drawing
     * CHALLENGE (optional): list distinct colours only (no duplicates)
     * @return ArrayList<Color> of all colours used
     */
    public ArrayList<Color> getColorList()
    {
        ArrayList<Color> colors = new ArrayList<Color>();
        for (Stroke s : drawing) {
            if (!colors.contains(s.getColor()))
                colors.add(s.getColor());
        }
        return colors; //TODO replace code here
    }
    
    /**
     * @return int minimum x-value of any line in the drawing
     */
    public int minX() 
    {
        if (drawing.isEmpty()) {
            return 0;
        }
        int minx = drawing.get(0).getX1();
        for (Stroke s : drawing) {
            if (s.getX1() < minx) {
                minx = s.getX1();
            }
            if (s.getX2() < minx) {
                minx = s.getX2();
            }
        }
       return minx;
    }
    
    /**
     * @return int maximum x-value of any line in the drawing
     */
    public int maxX() 
    {
        if (drawing.isEmpty()) {
            return 0;
        }
       int maxX = drawing.get(0).getX1();
        for (Stroke s : drawing) {
            if (s.getX1() > maxX) {
                maxX = s.getX1();
            }
            if (s.getX2() > maxX) {
                maxX = s.getX2();
            }
        }
       return maxX;
    }
    
        /**
     * @return int minimum y-value of any line in the drawing
     */
    public int minY() 
    {
        if (drawing.isEmpty()) {
            return 0;
        }
       int miny = drawing.get(0).getY1();
        for (Stroke s : drawing) {
            if (s.getY1() < miny) {
                miny = s.getY1();
            }
            if (s.getY2() < miny) {
                miny = s.getY2();
            }
        }
       return miny;
    }
    
        /**
     * @return int maxmum y-value of any line in the drawing
     */
    public int maxY() 
    {
        if (drawing.isEmpty()) {
            return 0;
        }
       int maxy = drawing.get(0).getY1();
        for (Stroke s : drawing) {
            if (s.getY1() > maxy) {
                maxy = s.getY1();
            }
            if (s.getY2() > maxy) {
                maxy = s.getY2();
            }
        }
       return maxy;
    }
    
    /**
     * Add four lines to the drawing that form a bounding box 
     * around the current drawing.
     */
   public void makeBoundingBox()
    {
        
        drawing.add(new Stroke(minX(), minY(), minX(), maxY(), Color.yellow, 2));
        drawing.add(new Stroke(maxX(), minY(), maxX(), maxY(), Color.yellow, 2));
        drawing.add(new Stroke(minX(), minY(), maxX(), minY(), Color.yellow, 2));
        drawing.add(new Stroke(minX(), maxY(), maxX(), maxY(), Color.yellow, 2));
    }
   
    
    
    /**
     * Give a human readable summary of the statistics of the drawing.
     * Format the string as you wish with your own choice of information.  
     * e.g. you can include information such as the number of strokes, 
     * bounding box co-ordinates, and the number of different colours used.
     * @return String summarising statistical features of the drawing
 
     */
    public String drawingStatistics() 
    {
       return "Summary Statistics\n"
               + "Number of strokes: "+drawing.size()+"\n"
               + "Different Colors: "+getColorList().size()+"\n"
               + "Bounding Box Cordinates: [("+minX()+","+minY()+"),("+maxX()+","+minY()+"),"+"("+minX()+","+maxY()+"),("+maxX()+","+maxY()+")]"; 
    }
    
   /**
    * CHALLENGES
    * Add extra methods here if you decide to do the project challenges.
    */

    /**
     * Removes all strokes.
     */
    public void removeAll()
    {
        drawing.clear();
    }
    
    /**
     * Rotates colors of the drawing.
     * @param step All +/- integers are possible.
     */
    public void rotateColor(int step)
    {
        if (step == 0) {
            return;
        }
        ArrayList<Color> colors = new ArrayList<Color>(drawing.size());
        for (Stroke s: drawing) {
            colors.add(s.getColor());
        }
        if (step < 0) {
            step = -step;
            step %= colors.size();
            step = colors.size() - step;
        } else {
            step %= colors.size();
        }
        int colorID = step;
        int colorCount = colors.size();
        for (Stroke s: drawing) {
            if (colorID == colorCount) {
                colorID = 0;
            }
            s.setColor(colors.get(colorID));
            colorID ++;
        }
    }
    
    /**
     * Rotates the entire drawing through angle.
     * 
     * @param angle Degrees to rotate.
     * @param centerX X position of rotation center
     * @param centerY Y position of rotation center
     */
    public void rotate(int angle, int centerX, int centerY)
    {
        AffineTransform at = new AffineTransform();
        at.rotate(Math.toRadians(angle));
        Point p1 = new Point();
        Point p2 = new Point();
        for (Stroke s: drawing) {
            p1.x = s.getX1() - centerX;
            p1.y = s.getY1() - centerY;
            p2.x = s.getX2() - centerX;
            p2.y = s.getY2() - centerY;
            at.transform(p1, p1);
            at.transform(p2, p2);
            s.setPosition(p1.x + centerX, p1.y + centerY, p2.x + centerX, p2.y + centerY);
        }
    }

    /**
     * Scales the entire drawing by scaleX and scaleY.
     * 
     * @param scaleX x-scale
     * @param scaleY y-scale
     * @param centerX X position of scale center
     * @param centerY Y position of scale center
     */
    public void scale(float scaleX, float scaleY, int centerX, int centerY)
    {
        Point p1 = new Point();
        Point p2 = new Point();
        for (Stroke s: drawing) {
            p1.x = (int) ((s.getX1() - centerX) * scaleX);
            p1.y = (int) ((s.getY1() - centerY) * scaleY);
            p2.x = (int) ((s.getX2() - centerX) * scaleX);
            p2.y = (int) ((s.getY2() - centerY) * scaleY);
            s.setPosition(p1.x + centerX, p1.y + centerY, p2.x + centerX, p2.y + centerY);
        }
    }
}
