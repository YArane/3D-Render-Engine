package examples;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;

import containers.Vector3f;
import main.Main;
import paint.Line;
import render.WireFrame;
import shader.FlatShader;
import shader.TextureShader;

public class TextureShader1 extends Main{
    public TextureShader1(){
        super("Texture Shader - Example 3");
        method1();
    }

    public void method1(){
        try {
            TextureShader.shade("res/african_head.obj", new Vector3f(0, 0f, -1));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main m = new TextureShader1();
                m.setVisible(true);
            }
        });
    }
}
