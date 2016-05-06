package paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Line {

    /**
     * Draws a line segment onto the canvas
     * 
     * @param x0
     * @param y0
     * @param x1
     * @param y1
     * @param color
     * @param g
     */
    public static void draw(int x0, int y0, int x1, int y1, Color color){
        for(int x=x0;x<=x1;x++){
            float t = (x - x0) / (float)(x1 - x0);
            int y = (int) (y0 * (1.0 - t) + y1 * t);
            Canvas.set(x,  y, color);
        }
    }
}
