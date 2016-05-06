package render;

import java.awt.Color;

import containers.Vector2f;
import objConverter.ModelData;
import objConverter.OBJFileLoader;
import paint.Canvas;
import paint.Line;

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
        for(int i=0;i<vertexPositions.length/3-1;i++){

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
            }catch(ArrayIndexOutOfBoundsException e){
            }

        }
        Canvas.getInstance().flipVertically();
    }
    
    /**
     * Draws a line segment for the triangle represented by the face
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

            for(int j=0;j<3;j++){
                int x0 = (int)((face[j].x + 1.0)* Canvas.height / 2.0);
                int y0 = (int)((face[j].y + 1.0)* Canvas.width / 2.0);
                int x1 = (int)((face[(j+1)%3].x + 1.0)* Canvas.height / 2.0);
                int y1 = (int)((face[(j+1)%3].y + 1.0)* Canvas.width / 2.0);

                try{
                    Line.draw2(x0, y0, x1, y1, color);
                }catch(ArrayIndexOutOfBoundsException e){}
            }

        }
        Canvas.getInstance().flipVertically();
    }
}
