package paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import javax.swing.JPanel;
import javax.swing.RepaintManager;

import main.Main;

public class Canvas extends JPanel {
    
    private static Canvas instance;
    public static int width, height;
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
            //instance = new Canvas(Main.WIDTH, Main.HEIGHT);
            instance = new Canvas(800, 800);
        }
        return instance;
    }
    
    /**
     * Sets the input colour to the specified pixel location of the image
     * @param x
     * @param y
     * @param color
     */
    public void set(int x, int y, Color colour){
        image.setRGB(x, y, colour.getRGB());
    }
    
    public void flipVertically(){
       for(int i=0;i<width;i++){
          for(int j=0;j<height/2;j++){
             try{
                 int temp = image.getRGB(i, j);
                 image.setRGB(i, j, image.getRGB(i, height-j));
                 image.setRGB(i, height-j, temp);
             }catch(ArrayIndexOutOfBoundsException e){
                 //TODO: remove hack try-catch and figure out bounds properly
             }
          }
       }
    }
    
    public static void refresh(){
        instance.repaint();
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
    
    public static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

}