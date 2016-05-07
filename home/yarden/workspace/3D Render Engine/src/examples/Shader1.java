package examples;

import java.awt.Color;
import java.awt.EventQueue;

import containers.Vector3f;
import main.Main;
import paint.Line;
import render.WireFrame;
import shader.FlatShader;

public class Shader1 extends Main{
    public Shader1(){
        super("Flat Shader - Example 1");
        method1();
    }

    public void method1(){
        FlatShader.shade("res/african_head.obj", Color.CYAN, new Vector3f(0, 0, -1));
    }
    
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main m = new Shader1();
                m.setVisible(true);
            }
        });
    }
}
