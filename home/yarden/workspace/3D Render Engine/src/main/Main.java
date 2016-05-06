package main;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import paint.Canvas;
import paint.Line;



public class Main extends JFrame {
    
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    
    private static String frame;

    /**
     * Constructor
     */
    public Main(String frame) {
        this.frame = frame;
        initUI();
    }

    /**
     * Initializes the frame and adds the canvas
     */
    private void initUI() {

        add(Canvas.getInstance());
        
        setTitle(this.frame);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // (Center)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Main Method
     * @param args
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main m = new Main("3D Render Engine");
                m.setVisible(true);
            }
        });
    }
    
}