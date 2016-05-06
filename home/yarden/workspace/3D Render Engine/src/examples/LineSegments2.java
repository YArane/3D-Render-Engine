package examples;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import paint.Canvas;
import paint.Line;
import main.Main;

public class LineSegments2 extends Main{
    
    public LineSegments2(){
        super("Line Segments - Example 2");
        method2();
    }

    public void method2(){
        Line.draw2(100, 100, 400, 500, new Color(200, 150, 200));
        Line.draw2(100, 500, 400, 100, new Color(50, 150, 80));
        Line.draw2(50, 500, 150, 50, Color.YELLOW);
        Line.draw2(50, 500, 100, 50, Color.CYAN);
        Line.draw2(50, 500, 75, 50, Color.RED);
    }
    
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main m = new LineSegments2();
                m.setVisible(true);
            }
        });
    }
}
