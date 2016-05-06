package examples;

import java.awt.Color;
import java.awt.EventQueue;

import main.Main;
import paint.Line;
import render.WireFrame;

public class WireFrame3 extends Main{
    public WireFrame3(){
        super("Wire Frame - Example 3");
        method2();
    }

    public void method2(){
        WireFrame.fill("res/african_head.obj", null);
    }
    
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main m = new WireFrame3();
                m.setVisible(true);
            }
        });
    }
}
