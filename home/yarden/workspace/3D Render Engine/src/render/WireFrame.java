package render;

import java.awt.Color;

import containers.Vector2f;
import containers.Vector3f;
import objConverter.ModelData;
import objConverter.OBJFileLoader;
import paint.Canvas;
import paint.Line;
import paint.Triangle;

public class WireFrame {

    /**
     * Draws a line segment for every 2 neighbouring vertices
     * @param filename
     * @param color
     */
    public static void draw(String filename, Color color){
        ModelData vao = OBJFileLoader.loadOBJ(filename); 
        
        float[] vertexPositions = vao.getVertices();
        
        float numVertices = vertexPositions.length/3;
        for(int i=0;i<numVertices-1;i++){

            float v0 = vertexPositions[i*3];
            float v1 = vertexPositions[i*3+1];
            float v2 = vertexPositions[(i+1)*3];
            float v3 = vertexPositions[(i+1)*3+1];
            int x0 = (int)((v0 + 1.0)* Canvas.height / 2.0);
            int y0 = (int)((v1 + 1.0)* Canvas.width / 2.0);
            int x1 = (int)((v2 + 1.0)* Canvas.height / 2.0);
            int y1 = (int)((v3 + 1.0)* Canvas.width / 2.0);
            
            try{
                Line.draw2(x0, y0, x1, y1, color);
            }catch(ArrayIndexOutOfBoundsException e){}

        }
        Canvas.getInstance().flipVertically();
    }
    
    /**
     * Draws a triangle represented by each face
     * @param filename
     * @param color
     */
    public static void draw2(String filename, Color color){
        ModelData vao = OBJFileLoader.loadOBJ(filename); 
        
        float[] vertexPositions = vao.getVertices();
        int[] faceIndicies = vao.getFaces(); 
        
        float numFaces = faceIndicies.length/3;
        for(int i=0;i<numFaces;i++){
           Vector2f[] face = new Vector2f[3];
           face[0] = new Vector2f(vertexPositions[faceIndicies[i*3]*3], vertexPositions[faceIndicies[i*3]*3+1]);
           face[1] = new Vector2f(vertexPositions[faceIndicies[i*3+1]*3], vertexPositions[faceIndicies[i*3+1]*3+1]);
           face[2] = new Vector2f(vertexPositions[faceIndicies[i*3+2]*3], vertexPositions[faceIndicies[i*3+2]*3+1]);

           Triangle.draw(face, color);
          
        }
        Canvas.getInstance().flipVertically();
    }
    
    /**
     * Fills a triangle represented by each face
     * @param filename
     * @param color
     */
    public static void fill(String filename, Color color){
        ModelData vao = OBJFileLoader.loadOBJ(filename); 
        
        float[] vertexPositions = vao.getVertices();
        int[] faceIndicies = vao.getFaces(); 
        
        float numFaces = faceIndicies.length/3;
        for(int i=0;i<numFaces;i++){
           Vector2f[] face = new Vector2f[3];
           face[0] = new Vector2f(vertexPositions[faceIndicies[i*3]*3], vertexPositions[faceIndicies[i*3]*3+1]);
           face[1] = new Vector2f(vertexPositions[faceIndicies[i*3+1]*3], vertexPositions[faceIndicies[i*3+1]*3+1]);
           face[2] = new Vector2f(vertexPositions[faceIndicies[i*3+2]*3], vertexPositions[faceIndicies[i*3+2]*3+1]);
           
           Vector2f[] screenCoordinates = new Vector2f[3];
           for(int j=0;j<3;j++){
               Vector2f worldCoordinates = face[j];
               int x = (int) ((worldCoordinates.x + 1.0) * Canvas.width / 2.0);
               int y = (int) ((worldCoordinates.y + 1.0) * Canvas.height / 2.0);
               screenCoordinates[j] = new Vector2f(x, y);
           }

           Triangle.fill(screenCoordinates, new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
          
        }
        Canvas.getInstance().flipVertically(); 
    }
    
    
}
