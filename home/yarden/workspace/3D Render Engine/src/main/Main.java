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
    
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    /**
     * Constructor
     */
    public Main() {
        initUI();
    }

    /**
     * Initializes the frame and adds the canvas
     */
    private void initUI() {

        add(Canvas.getInstance());
        Line.draw(100, 100, 400, 500, new Color(200, 150, 200));
        Line.draw(100, 500, 400, 100, new Color(50, 150, 80));

        setTitle("3D Render Engine");
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
                Main m = new Main();
                m.setVisible(true);
            }
        });
    }
}