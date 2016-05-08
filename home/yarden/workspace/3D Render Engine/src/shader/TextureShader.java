package shader;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import math.VectorOperations;
import objConverter.ModelData;
import objConverter.OBJFileLoader;
import objConverter.TargaReader;
import paint.Canvas;
import paint.Triangle;
import containers.Vector2f;
import containers.Vector3f;

public class TextureShader {

    private static Vector2f[] texture;
    private static BufferedImage textureImage;
    private static float intensity;
    
    public static void shade(String filename, Vector3f light) throws IOException{
        // read data
        ModelData vao = OBJFileLoader.loadOBJ(filename); 
        textureImage = (BufferedImage) TargaReader.getImage("res/african_head_diffuse.tga");
        
        float[] vertexPositions = vao.getVertices();
        float[] textureCoords = vao.getTextureCoords();
        int[] faceIndicies = vao.getFaces();
        int[] textureIndicies = vao.getTextureIndicies();
        
        System.out.println(faceIndicies.length);
        System.out.println(textureIndicies.length);
        System.out.println(faceIndicies[0] + ", " + faceIndicies[1] + ", " + faceIndicies[2]);

        System.out.println(textureCoords[0] + ", " + textureCoords[1]);
        System.out.println(textureCoords[2] + ", " + textureCoords[3]);

        System.out.println(textureIndicies[0] + ", " + textureIndicies[1] + ", " + textureIndicies[2]);
        
        int k = 0;
        System.out.println(textureIndicies[k*3] + ", " + textureIndicies[k*3+1] + ", " + textureIndicies[k*3+2]);
        System.out.println(textureCoords[textureIndicies[k*3]*2] + ", " + textureCoords[textureIndicies[k*3]*2+1]);
        System.out.println(textureCoords[textureIndicies[k*3+1]*2] + ", " + textureCoords[textureIndicies[k*3+1]*2+1]);
        System.out.println(textureCoords[textureIndicies[k*3+2]*2] + ", " + textureCoords[textureIndicies[k*3+2]*2+1]);
        
        //int[] uvMap = calcUVMap(textureCoords, textureIndicies); 

        float[][] zBuffer = FlatShader.initZBuffer();
        
        float numFaces = faceIndicies.length/3;
        for(int i=0, t=0;i<numFaces;i++, t++){
           // extract face vertices (world coordinates)
           Vector3f[] face = new Vector3f[3];
           face[0] = new Vector3f(vertexPositions[faceIndicies[i*3]*3], vertexPositions[faceIndicies[i*3]*3+1], vertexPositions[faceIndicies[i*3]*3+2]);
           face[1] = new Vector3f(vertexPositions[faceIndicies[i*3+1]*3], vertexPositions[faceIndicies[i*3+1]*3+1], vertexPositions[faceIndicies[i*3+1]*3+2]);
           face[2] = new Vector3f(vertexPositions[faceIndicies[i*3+2]*3], vertexPositions[faceIndicies[i*3+2]*3+1], vertexPositions[faceIndicies[i*3+2]*3+2]);

          // extract the texture coords (uv coordinates)
           texture = new Vector2f[3];
           try{
               texture[0] = new Vector2f(textureCoords[textureIndicies[t*3]*2], textureCoords[textureIndicies[t*3]*2+1]);
               texture[1] = new Vector2f(textureCoords[textureIndicies[t*3+1]*2], textureCoords[textureIndicies[t*3+1]*2+1]);
               texture[2] = new Vector2f(textureCoords[textureIndicies[t*3+2]*2], textureCoords[textureIndicies[t*3+2]*2+1]);
           }catch(Exception e){
               System.err.println("uh-oh");
               texture[0] = new Vector2f(0, 0);
           }
           
           // convert both triangles to screen coordinates
           Vector2f[] screenCoords = new Vector2f[3];
           Vector3f[] worldCoords = new Vector3f[3];
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
           intensity = Vector3f.dot(normal, light);
           // if less than zero, the light is coming from behind the face.
           // Back-face culling: removing triangles that are invisble (performance boost)
           if(intensity > 0){
               //intensity = Math.min(intensity, 1);
               try{
                   Color color = new Color(textureImage.getRGB((int)(texture[0].x*textureImage.getWidth()), (int)(texture[0].y*textureImage.getHeight())));
                   Triangle.fill(screenCoords, /*new Color((int)(intensity*color.getRed()), (int)(intensity*color.getGreen()), (int)(intensity*color.getBlue())), */zBuffer);
               }catch(IllegalArgumentException e){}
           }
        }
        Canvas.getInstance().flipVertically(); 
    }

    public static Color calcUVMap(Vector3f barycentricCood){
       Vector3f u, v;
       u = new Vector3f(texture[0].x, texture[1].x, texture[2].x); 
       v = new Vector3f(texture[0].y, texture[1].y, texture[2].y); 
       
       float uMap = Vector3f.dot(u, barycentricCood);
       float vMap = Vector3f.dot(v, barycentricCood);
       
       Color color = new Color(textureImage.getRGB((int)(uMap*textureImage.getWidth()), (int)(vMap*textureImage.getHeight())));
       color = new Color((int) (color.getRed()*intensity), (int)(color.getGreen()*intensity), (int)(color.getBlue()*intensity));
       
       return color;
        
    }
    
}
