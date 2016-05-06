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
            //System.out.println(x + ", " + y);
            Canvas.getInstance().set(x,  y, color);
        }
    }
    
    public static void draw(float x0, float y0, float x1, float y1, Color color){
        for(float x=x0;x<=x1;x++){
            float t = (x - x0) / (float)(x1 - x0);
            int y = (int) (y0 * (1.0 - t) + y1 * t);
            //System.out.println(x + ", " + y);
            Canvas.getInstance().set((int)x,  y, color);
        }
    }
    
    public static void draw2(int x0, int y0, int x1, int y1, Color color){
        boolean steep = false;
        int temp0, temp1;
        // if line is steep, transpose the line (avoid dividing by zero)
        if(isSteep(x0, y0, x1, y1)){
            steep = true;
            temp0 = y0;
            temp1 = y1;
            y0 = x0;
            y1 = x1;
            x0 = temp0;
            x1 = temp1;
        }
        int tempX, tempY;
        // force increasing function
        if(x0 > x1){
           tempX = x1;
           tempY = y1;
           x1 = x0;
           y1 = y0;
           x0 = tempX;
           y0 = tempY;
        }
        int dx = x1 - x0;
        int dy = y1 - y0;
        int derror2 = Math.abs(dy) * 2;
        int error2 = 0;
        int y = y0;
        for(int x=x0;x<=x1;x++){
            if(steep){
                Canvas.getInstance().set(y, x, color);
            }else{
                Canvas.getInstance().set(x,  y, color);
            }
            error2 += derror2;
            if(error2 > dx){
                y+= (y1 > y0? 1 : -1);
                error2 -= dx * 2;
            }
        }
    }
    public static void draw2(float x0, float y0, float x1, float y1, Color color){
        boolean steep = false;
        float temp0, temp1;
        // if line is steep, transpose the line (avoid dividing by zero)
        if(isSteep(x0, y0, x1, y1)){
            steep = true;
            temp0 = y0;
            temp1 = y1;
            y0 = x0;
            y1 = x1;
            x0 = temp0;
            x1 = temp1;
        }
        float tempX, tempY;
        // force increasing function
        if(x0 > x1){
           tempX = x1;
           tempY = y1;
           x1 = x0;
           y1 = y0;
           x0 = tempX;
           y0 = tempY;
        }
        float dx = x1 - x0;
        float dy = y1 - y0;
        float derror2 = Math.abs(dy) * 2;
        int error2 = 0;
        float y = y0;
        for(float x=x0;x<=x1;x++){
            if(steep){
                //System.out.println(x + ", " + y);
                Canvas.getInstance().set((int)y, (int)x, color);
            }else{
                Canvas.getInstance().set((int)x,  (int)y, color);
            }
            error2 += derror2;
            if(error2 > dx){
                y+= (y1 > y0? 1 : -1);
                error2 -= dx * 2;
            }
        }
    }
    private static boolean isSteep(int x0, int y0, int x1, int y1) {
        return Math.abs(x0 - x1) < Math.abs(y0 - y1);
    }
    
    private static boolean isSteep(float x0, float y0, float x1, float y1) {
        return Math.abs(x0 - x1) < Math.abs(y0 - y1);
    }
}
