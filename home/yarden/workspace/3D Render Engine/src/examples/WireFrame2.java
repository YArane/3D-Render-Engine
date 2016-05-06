package examples;

import java.awt.Color;
import java.awt.EventQueue;

import main.Main;
import paint.Line;
import render.WireFrame;

public class WireFrame2 extends Main{
    public WireFrame2(){
        super("Wire Frame - Example 2");
        method2();
    }

    public void method2(){
        WireFrame.draw2("res/african_head.obj", Color.WHITE);
    }
    
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main m = new WireFrame2();
                m.setVisible(true);
            }
        });
    }
}
