package objConverter;

import java.io.Serializable;


public class ModelData implements Serializable{

	private float[] vertices;
	private float[] textureCoords;
	private float[] normals;
	private int[] indices;
	private int[] faces;
	private int[] textureIndicies;
	private float furthestPoint;

	public ModelData(float[] vertices, float[] textureCoords, float[] normals, int[] indices,
			float furthestPoint) {
		this.vertices = vertices;
		this.textureCoords = textureCoords;
		this.normals = normals;
		this.indices = indices;
		this.furthestPoint = furthestPoint;
	}
	public ModelData(float[] vertices, float[] textureCoords, float[] normals, int[] indices, int[] faces) {
		this.vertices = vertices;
		this.textureCoords = textureCoords;
		this.normals = normals;
		this.indices = indices;
		this.faces = faces;
	}

	public ModelData(float[] vertices, float[] textureCoords, float[] normals, int[] indices, int[] faces, int[] textureIndicies) {
        this.vertices = vertices;
        this.textureCoords = textureCoords;
        this.normals = normals;
        this.indices = indices;
        this.faces = faces;
        this.textureIndicies = textureIndicies;
    }
	
	public int[] getTextureIndicies(){
	    return textureIndicies;
	}
	
	public float[] getVertices() {
		return vertices;
	}

	public float[] getTextureCoords() {
		return textureCoords;
	}

	public float[] getNormals() {
		return normals;
	}

	public int[] getIndices() {
		return indices;
	}

	public float getFurthestPoint() {
		return furthestPoint;
	}
	
	public int[] getFaces() {
	    return faces;
	}
	

}
