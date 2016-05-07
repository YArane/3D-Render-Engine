package math;

import containers.Vector3f;

public class VectorOperations {

    /**
     * returns the cross product of vector1 cross vector2
     * @param vector1
     * @param vector2
     * @return
     */
    public static Vector3f crossProduct(Vector3f vector1, Vector3f vector2){
        float x = vector1.y * vector2.z - vector1.z * vector2.y;
        float y = vector1.z * vector2.x - vector1.x * vector2.z;
        float z = vector1.x * vector2.y - vector1.y * vector2.x;
        
        return new Vector3f(x, y, z);
    }
    
    /**
     * Subtracts vector2 from vector1
     * @param vector1
     * @param vector2
     * @return
     */
    public static Vector3f subtract(Vector3f vector1, Vector3f vector2){
        return new Vector3f(vector1.x - vector2.x, vector1.y - vector2.y, vector1.z - vector2.z);
    }
    
}
