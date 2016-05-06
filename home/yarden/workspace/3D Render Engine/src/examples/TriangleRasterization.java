package examples;

import java.awt.Color;
import java.awt.EventQueue;

import containers.Vector2f;
import main.Main;
import paint.Line;
import paint.Triangle;

public class TriangleRasterization extends Main{
    public TriangleRasterization(){
        super("Triangle Rasterization - Example 1");
        method1();
    }

    public void method1(){
        Vector2f[] triangle1 = {new Vector2f(100, 200), new Vector2f(300, 300), new Vector2f(100, 300)};
        Vector2f[] triangle2 = {new Vector2f(300, 400), new Vector2f(600, 50), new Vector2f(350, 150)};
        Vector2f[] triangle3 = {new Vector2f(400, 500), new Vector2f(400, 300), new Vector2f(300, 450)};
        Triangle.fill(triangle1, Color.WHITE);
        Triangle.fill(triangle2, Color.RED);
        Triangle.fill(triangle3, Color.CYAN);
    }
    
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main m = new TriangleRasterization();
                m.setVisible(true);
            }
        });
    }
}
