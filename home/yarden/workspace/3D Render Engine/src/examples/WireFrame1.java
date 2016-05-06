package examples;

import java.awt.Color;
import java.awt.EventQueue;

import main.Main;
import paint.Line;
import render.WireFrame;

public class WireFrame1 extends Main{
    public WireFrame1(){
        super("Wire Frame - Example 1");
        method1();
    }

    public void method1(){
        WireFrame.draw("res/african_head.obj", Color.WHITE);
    }
    
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main m = new WireFrame1();
                m.setVisible(true);
            }
        });
    }
}
