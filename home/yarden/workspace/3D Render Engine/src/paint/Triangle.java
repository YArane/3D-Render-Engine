package paint;

import java.awt.Color;

import math.VectorOperations;
import containers.Vector2f;
import containers.Vector3f;

public class Triangle {

    /**
     * Draws a triangle corresponding to the 3 input vertices onto the canvas
     * @param vertices
     * @param color
     */
    public static void draw(Vector2f vertices[], Color color){
        for(int j=0;j<3;j++){
            int x0 = (int)((vertices[j].x + 1.0)* Canvas.height / 2.0);
            int y0 = (int)((vertices[j].y + 1.0)* Canvas.width / 2.0);
            int x1 = (int)((vertices[(j+1)%3].x + 1.0)* Canvas.height / 2.0);
            int y1 = (int)((vertices[(j+1)%3].y + 1.0)* Canvas.width / 2.0);

            try{
                Line.draw2(x0, y0, x1, y1, color);
            }catch(ArrayIndexOutOfBoundsException e){}
        } 
    }
    
    
    public static void fill(Vector2f vertices[], Color color){
        Vector2f boundingBoxMin = new Vector2f(Canvas.width-1, Canvas.height-1);
        Vector2f boundingBoxMax = new Vector2f(0, 0);
        Vector2f clamp = new Vector2f(Canvas.width-1, Canvas.height-1);

        for(int i=0;i<3;i++){
            boundingBoxMin.x = Math.max(0, Math.min(boundingBoxMin.x, vertices[i].x));
            boundingBoxMin.y = Math.max(0, Math.min(boundingBoxMin.y, vertices[i].y));
            boundingBoxMax.x = Math.min(clamp.x, Math.max(boundingBoxMax.x, vertices[i].x));
            boundingBoxMax.y = Math.min(clamp.y, Math.max(boundingBoxMax.y, vertices[i].y));
        }
        
        Vector2f p = new Vector2f();
        for(p.x = boundingBoxMin.x;p.x<=boundingBoxMax.x;p.x++){
            for(p.y = boundingBoxMin.y;p.y<=boundingBoxMax.y;p.y++){
                Vector3f barycentricCoord = calcBarycentricCoord(vertices, p);
                if(barycentricCoord.x < 0 || barycentricCoord.y < 0 || barycentricCoord.z < 0) continue;
                Canvas.getInstance().set((int)p.x, (int)p.y, color);
            }
        }
    }
    
    /**
     * Calculates the barycentric coordinate given a triangle and a point
     * @param vertices
     * @param p
     * @return
     */
    private static Vector3f calcBarycentricCoord(Vector2f[] vertices, Vector2f p){
        Vector3f vector1 = new Vector3f(vertices[2].x-vertices[0].x, vertices[1].x - vertices[0].x, vertices[0].x - p.x);
        Vector3f vector2 = new Vector3f(vertices[2].y-vertices[0].y, vertices[1].y - vertices[0].y, vertices[0].y - p.y);
        Vector3f crossProduct  = VectorOperations.crossProduct(vector1, vector2);
        
        // triangle is flat
        if(Math.abs(crossProduct.z) < 1)
            return new Vector3f(-1, 1, 1);

        float x = (float) (1.0 - (crossProduct.x + crossProduct.y) / crossProduct.z);
        float y = crossProduct.y / crossProduct.z;
        float z = crossProduct.x / crossProduct.z;
        return new Vector3f(x, y, z);
    }
    
}
