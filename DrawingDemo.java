import java.awt.Color;
import java.util.Random;

/**
 * Project: Add your name and date in the header and implement this class for
 * the project. Class DrawingDemo - demonstrates the functionality you have
 * implemented in the Drawing class by using the Pen class to create and show
 * one or more drawings. Wheel example is from Barnes and Koelling.
 *
 * @author Timothy Ellis - 21199844    
 * @version 3.0
 */
public class DrawingDemo {

    public void allDemos() {
        //drawWheel();
        //New methods here
        rotateWheelDemo();
        drawTreeDemo();
    }

    /**
     * Draw a wheel made of many alternating color squares.
     */
    public void drawWheel() {
        int dir; //direction of move
        Pen pen = new Pen();
        pen.setWidth(2.0f);
        pen.setColor(Color.RED);

        for (int i = 0; i < 36; i++) {
            square(pen);
            pen.turn(10);
            //now change colour every 9 steps
            switch (i) {
                case 5:
                    pen.setColor(Color.ORANGE);
                    break;
                case 10:
                    pen.setColor(Color.YELLOW);
                    break;
                case 15:
                    pen.setColor(Color.GREEN);
                    break;
                case 20:
                    pen.setColor(Color.BLUE);
                    break;
                case 25:
                    pen.setColor(Color.BLACK);
                    break;
                case 30:
                    pen.setColor(Color.MAGENTA);
                    break;
                default: //no change
                    break;
            }
        }

        //display the drawing, pause demo to see the drawing then clear
        pen.drawDisplay();
        pen.delay(2000);
        pen.clearDisplay();

        // print summary of the drawing properties
        System.out.println(pen.getDrawing().drawingStatistics());
    }

    /**
     * Helper method to draw a square in the pen's color at the pen's location.
     */
    private void square(Pen pen) {
        for (int i = 0; i < 4; i++) {
            pen.move(100);
            pen.turn(90);
        }
    }

    /**
     * Draws a wheel made of many alternating color squares.
     *
     * @param pen A Pen to draw.
     * @param startAngle Start angle.
     */
    private void drawMyWheel(Pen pen, int startAngle) {
        pen.setWidth(2.0f);
        pen.setColor(Color.RED);
        pen.turnTo(startAngle);

        for (int i = 0; i < 36; i++) {
            square(pen);
            pen.turn(10);
            //now change colour every 9 steps
            switch (i) {
                case 5:
                    pen.setColor(Color.ORANGE);
                    break;
                case 10:
                    pen.setColor(Color.YELLOW);
                    break;
                case 15:
                    pen.setColor(Color.GREEN);
                    break;
                case 20:
                    pen.setColor(Color.BLUE);
                    break;
                case 25:
                    pen.setColor(Color.BLACK);
                    break;
                case 30:
                    pen.setColor(Color.MAGENTA);
                    break;
                default: //no change
                    break;
            }
        }
        //display the drawing, pause demo to see the drawing then clear
        pen.drawDisplay();

        // print summary of the drawing properties
        System.out.println(pen.getDrawing().drawingStatistics());
    }

    /**
     * Draws a wheel made of many alternating color squares and rotates colors of the wheel.
     */
    public void rotateWheelDemo() {
        Pen pen = new Pen();
        drawMyWheel(pen, 0);
        // rotate color
        for (int i = 0; i < 100; i++) {
            pen.delay(30);
            rotateColor(pen, 1);
        }
        pen.delay(1000);
        // change color
        changeWithStandardJavaColor(pen, true);
        // print summary of the drawing properties
        System.out.println(pen.getDrawing().drawingStatistics());
        // rotate color
        for (int i = 0; i < 180; i++) {
            pen.delay(30);
            rotateColor(pen, 1);
        }
    }

    /**
     * Rotates colors of the drawing of pen through the number of step.
     *
     * @param pen Pen object
     * @param step All +/- integers are available
     */
    public static void rotateColor(Pen pen, int step) {
        pen.getDrawing().rotateColor(step);
        pen.clearDisplay();
        pen.drawDisplay();
    }

    /**
     * Changes color of drawing with standard Java Colors
     * @param pen Pen object to change color on
     * @param cover If true, 1 cycle of colors are covered over the drawing.
     * If false, colors are covered cyclicly.
     */
    public static void changeWithStandardJavaColor(Pen pen, boolean cover) {
        Color[] colors = new Color[]{
            Color.BLACK, Color.BLUE, Color.CYAN,
            Color.DARK_GRAY, Color.GRAY, Color.GREEN,
            Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
            Color.PINK, Color.RED, Color.YELLOW,};

        Drawing d = pen.getDrawing();
        int n = d.numberOfStrokes();
        float colorStep = (cover) ? (float) colors.length / n: 1;
        float colorID = 0;
        int nID;
        for (int i = 0; i < n; i++) {
            Stroke s = d.getStroke(i);
            nID = (int) colorID;
            nID %= colors.length;
            s.setColor(colors[nID]);
            colorID += colorStep;
        }
        pen.clearDisplay();
        pen.drawDisplay();
    }

    /**
     * Draws a black tree.
     * Shows horizontal or vertical flipping.
     * Shows rotation of drawing.
     * Shows scaling of drawing.
     * Shows color rotation and moves the tree.
     */
    public void drawTreeDemo() {
        Pen pen = new Pen();
        pen.setColor(Color.BLACK);
        drawTree(pen, 250, 400);
        pen.getDrawing().rotate(20, 250, 400);
        pen.drawDisplay();
        
        for (int i=0;i<2; i++) {
            // flip horizontal
            pen.delay(1000);
            pen.clearDisplay();
            pen.getDrawing().flipHorizontal(250);
            pen.drawDisplay();

            // flip vertical
            pen.delay(1000);
            pen.clearDisplay();
            pen.getDrawing().flipVertical(250);
            pen.drawDisplay();
        }
        
        // rotate tree
        pen.clearDisplay();
        pen.getDrawing().makeBoundingBox();
        pen.drawDisplay();
        for (int i=0; i<10; i++) {
            pen.delay(50);
            pen.clearDisplay();
            pen.getDrawing().rotate(-1, 250, 400);
            pen.drawDisplay();
        }
        
        // scale drawing
        pen.getDrawing().scale(1.5f, 1.5f, 250, 250);
        
        // paint tree
        pen.delay(1000);
        changeWithStandardJavaColor(pen, false);
        
        // print summary of the drawing properties
        System.out.println(pen.getDrawing().drawingStatistics());

        // move and rotate color
        pen.delay(1000);
        for (int i=0; i<100; i++) {
            pen.delay(100);
            rotateColor(pen, 1);
            if (i < 50) {
                pen.getDrawing().move(1, 1);
            } else {
                pen.getDrawing().move(1, -1);
            }
        }
    }

    /**
     * Draws a leaf to the pen.
     * @param pen Pen object to draw on
     * @param scale Scale of leaf drawn
     */
    private void drawLeaf(Pen pen, float scale) {
        int len = (int) (100 * scale);
        int l2 = (int) ((float) len / 7);
        pen.move(len);
        pen.turn(147);
        pen.move(l2);
        pen.turn(10);
        pen.move(l2);
        pen.turn(10);
        pen.move(l2);
        pen.turn(15);
        pen.move(l2);
        pen.turn(20);
        pen.move(l2);
        pen.turn(35);
        pen.move(l2);
        pen.turn(66);
        pen.move(l2);
        pen.turn(35);
        pen.move(l2);
        pen.turn(20);
        pen.move(l2);
        pen.turn(15);
        pen.move(l2);
        pen.turn(12);
        pen.move(l2);
        pen.turn(10);
        pen.move(l2);
    }

    /**
     * Draws a tree on (x, y)
     * @param pen Pen object to draw on
     * @param x x position of the tree
     * @param y y position of the tree
     */
    public void drawTree(Pen pen, int x, int y) {
        float scale = 1;
        for (int i = 0; i < 5; i++) {
            pen.penUp();
            pen.moveTo(x, y);
            pen.penDown();
            y -= 80 * scale;
            // draw trunk
            pen.setWidth(4);
            pen.moveTo(x, y);
            pen.setWidth(2);

            // draw right leaf
            pen.turnTo(-20);
            drawLeaf(pen, scale);

            pen.penUp();
            pen.moveTo(x, y);
            pen.penDown();
            // draw left leaf
            pen.turnTo(-160);
            drawLeaf(pen, scale);
            scale *= 0.8;
        }
        pen.penUp();
        pen.moveTo(x, y);
        pen.penDown();
        pen.turnTo(-90);
        drawLeaf(pen, scale / 0.8f);
    }
}
