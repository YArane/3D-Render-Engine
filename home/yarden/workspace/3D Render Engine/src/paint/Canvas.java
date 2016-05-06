package paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Canvas extends JPanel {
    
    private static Canvas instance;
    private static int width, height;
    private static BufferedImage image;
    
    /**
     * Constructor (Singleton)
     * @param width
     * @param height
     */
    private Canvas(int width, int height){
        this.width = width;
        this.height = height;
        this.image = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
    }
    
    /**
     * Public 'Constructor'
     * @return
     */
    public static Canvas getInstance(){
        if(instance == null){
            instance = new Canvas(800, 600);
        }
        return instance;
    }
    
    /**
     * Sets the input colour to the specified pixel location of the image
     * @param x
     * @param y
     * @param color
     */
    public static void set(int x, int y, Color colour){
        image.setRGB(x, y, colour.getRGB());
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Canvas.getImage(), 0, 0, Canvas.getImage().getHeight(null), Canvas.getImage().getHeight(null), null, null);
    }
    
    /**
     * @return the image
     */
    public static BufferedImage getImage() {
        return image;
    }

}