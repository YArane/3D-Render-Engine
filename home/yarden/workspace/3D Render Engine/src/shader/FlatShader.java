package shader;

import java.awt.Color;

import math.VectorOperations;
import objConverter.ModelData;
import objConverter.OBJFileLoader;
import paint.Canvas;
import paint.Triangle;
import containers.Vector2f;
import containers.Vector3f;

public class FlatShader {
    
    /**
     * Shades a 3D model with the input light source
     * @param filename
     * @param color
     * @param light
     */
    public static void shade(String filename, Color color, Vector3f light){
        ModelData vao = OBJFileLoader.loadOBJ(filename); 
        
        float[] vertexPositions = vao.getVertices();
        int[] faceIndicies = vao.getFaces(); 
        
        float numFaces = faceIndicies.length/3;
        for(int i=0;i<numFaces;i++){
           // extract face vertices (world coordinates)
           Vector3f[] face = new Vector3f[3];
           face[0] = new Vector3f(vertexPositions[faceIndicies[i*3]*3], vertexPositions[faceIndicies[i*3]*3+1], vertexPositions[faceIndicies[i*3]*3+2]);
           face[1] = new Vector3f(vertexPositions[faceIndicies[i*3+1]*3], vertexPositions[faceIndicies[i*3+1]*3+1], vertexPositions[faceIndicies[i*3+1]*3+2]);
           face[2] = new Vector3f(vertexPositions[faceIndicies[i*3+2]*3], vertexPositions[faceIndicies[i*3+2]*3+1], vertexPositions[faceIndicies[i*3+2]*3+2]);
           
           Vector2f[] screenCoords = new Vector2f[3];
           Vector3f[] worldCoords = new Vector3f[3];
           
           // convert both triangles to screen coordinates
           for(int j=0;j<3;j++){
               worldCoords[j] = face[j];
               int x = (int) ((worldCoords[j].x + 1.0) * Canvas.width / 2.0);
               int y = (int) ((worldCoords[j].y + 1.0) * Canvas.height / 2.0);
               screenCoords[j] = new Vector2f(x, y);
           }
           // compute the normal
           Vector3f vector1 = VectorOperations.subtract(worldCoords[2], worldCoords[0]);
           Vector3f vector2 = VectorOperations.subtract(worldCoords[1],  worldCoords[0]);
           Vector3f normal = VectorOperations.crossProduct(vector1, vector2);
           normal.normalise(normal);
           
           // the intensity of illumination
           float intensity = Vector3f.dot(normal, light);
           // if less than zero, the light is coming from behind the face.
           // Back-face culling: removing triangles that are invisble (performance boost)
           if(intensity > 0){
               //intensity = Math.min(intensity, 1);
               try{
               Triangle.fill(screenCoords, new Color((int)(intensity*color.getRed()), (int)(intensity*color.getGreen()), (int)(intensity*color.getBlue())));
               }catch(IllegalArgumentException e){}
           }
        }
        Canvas.getInstance().flipVertically(); 
    }
    
}
