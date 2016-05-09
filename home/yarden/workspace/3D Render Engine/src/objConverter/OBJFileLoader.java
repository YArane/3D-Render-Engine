package objConverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarException;

import containers.Vector2f;
import containers.Vector3f;

public class OBJFileLoader{
	
	private static final String RES_LOC = "";

	public static ModelData loadOBJ(String objFileName) {
		FileReader isr = null;
		File objFile = new File(RES_LOC + objFileName /*+ ".obj"*/);
		try {
			isr = new FileReader(objFile);
		} catch (FileNotFoundException e) {
			System.err.println("File not found in res; don't use any extention");
		}
		BufferedReader reader = new BufferedReader(isr);
		String line;
		List<Vertex> vertices = new ArrayList<Vertex>();
		List<Vector2f> textures = new ArrayList<Vector2f>();
		List<Vector3f> normals = new ArrayList<Vector3f>();
		List<Integer> indices = new ArrayList<Integer>();
		List<Vector3f> faces = new ArrayList<Vector3f>(); 
		List<Vector3f> textureIndicies = new ArrayList<Vector3f>(); 
		List<Vector3f> normalIndicies = new ArrayList<Vector3f>(); 
		
		try {
			while (true) {
				line = reader.readLine();
				if (line.startsWith("v ")) {
					String[] currentLine = line.split(" ");
					Vector3f vertex = new Vector3f((float) Float.valueOf(currentLine[1]),
							(float) Float.valueOf(currentLine[2]),
							(float) Float.valueOf(currentLine[3]));
					Vertex newVertex = new Vertex(vertices.size(), vertex);
					vertices.add(newVertex);

				} else if (line.startsWith("vt ")) {
					String[] currentLine = line.split(" ");
					try{
					Vector2f texture = new Vector2f((float) Float.valueOf(currentLine[1]),
							(float) Float.valueOf(currentLine[2]));
					//System.out.println(texture);
					textures.add(texture);
					}catch(java.lang.NumberFormatException e){
					   System.err.println(e); 
					}
				} else if (line.startsWith("vn ")) {
					String[] currentLine = line.split(" ");
					Vector3f normal = new Vector3f((float) Float.valueOf(currentLine[1]),
							(float) Float.valueOf(currentLine[2]),
							(float) Float.valueOf(currentLine[3]));
					normals.add(normal);
				} else if (line.startsWith("f ")) {
					break;
				}
			}
			while (line != null && line.startsWith("f ")) {
				//System.out.println(line);
				String[] currentLine = line.split(" ");
				String[] vertex1 = currentLine[1].split("/");
				String[] vertex2 = currentLine[2].split("/");
				String[] vertex3 = currentLine[3].split("/");
				// in wavefront obj, all indicies start at 1, not 0
				faces.add(new Vector3f(Integer.parseInt(vertex1[0])-1, Integer.parseInt(vertex2[0])-1, Integer.parseInt(vertex3[0])-1));
				textureIndicies.add(new Vector3f(Integer.parseInt(vertex1[1])-1, Integer.parseInt(vertex2[1])-1, Integer.parseInt(vertex3[1])-1));
				normalIndicies.add(new Vector3f(Integer.parseInt(vertex1[2])-1, Integer.parseInt(vertex2[2])-1, Integer.parseInt(vertex3[2])-1));
				//System.out.println((Integer.parseInt(vertex1[1])-1) + ", " + (Integer.parseInt(vertex2[1])-1));
			    //processVertex(vertex1, vertices, indices);
				//processVertex(vertex2, vertices, indices);
				//processVertex(vertex3, vertices, indices);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("Error reading the file");
		}
		//removeUnusedVertices(vertices);
		float[] verticesArray = new float[vertices.size() * 3];
		float[] texturesArray = new float[vertices.size() * 2];
		float[] normalsArray = new float[vertices.size() * 3];
		float furthest = convertDataToArrays(vertices, textures, normals, verticesArray,
				texturesArray, normalsArray);
	    
		texturesArray = convertTextureCoordsToArray(textures);
		normalsArray = convertNormalsToArray(normals);
		int[] indicesArray = convertIndicesListToArray(indices);
		int[] facesArray = convertFacesListToArray(faces);
		int[] textureIndiciesArray = convertFacesListToArray(textureIndicies);
		int[] normalIndiciesArray = convertFacesListToArray(normalIndicies);
		
		ModelData data = new ModelData(verticesArray, texturesArray, normalsArray, indicesArray, facesArray, textureIndiciesArray, normalIndiciesArray);
		return data;
	}
	
	static void printTexturesArray(float[] textures){
		for(int i=0;i<textures.length;i++){
			System.out.println(textures[i]);
		}
	}

	private static void processVertex(String[] vertex, List<Vertex> vertices, List<Integer> indices) {
		int index = Integer.parseInt(vertex[0]) - 1;
		Vertex currentVertex = vertices.get(index);
		int textureIndex = Integer.parseInt(vertex[1]) - 1;
		int normalIndex = Integer.parseInt(vertex[2]) - 1;
		if (!currentVertex.isSet()) {
			currentVertex.setTextureIndex(textureIndex);
			currentVertex.setNormalIndex(normalIndex);
			indices.add(index);
		} else {
			dealWithAlreadyProcessedVertex(currentVertex, textureIndex, normalIndex, indices,
					vertices);
		}
	}
	
	private static float[] convertTextureCoordsToArray(List<Vector2f> textureCoords){
	    float[] array = new float[textureCoords.size()*2];
	    for(int i=0;i<textureCoords.size();i++){
	        array[2*i] = textureCoords.get(i).x;
	        array[2*i+1] = textureCoords.get(i).y;
	    }
	    return array;	    
	}
	
	private static float[] convertNormalsToArray(List<Vector3f> normals){
        float[] array = new float[normals.size()*3];
        for(int i=0;i<normals.size();i++){
            array[3*i] = normals.get(i).x;
            array[3*i+1] = normals.get(i).y;
            array[3*i+2] = normals.get(i).z;
        }
        return array;       
    }

	private static int[] convertIndicesListToArray(List<Integer> indices) {
		int[] indicesArray = new int[indices.size()];
		for (int i = 0; i < indicesArray.length; i++) {
			indicesArray[i] = indices.get(i);
		}
		return indicesArray;
	}
	
	private static int[] convertFacesListToArray(List<Vector3f> faces){
	   int[] facesArray = new int[faces.size()*3];
	   for(int i=0;i<faces.size();i++){
	      facesArray[i*3] = (int) faces.get(i).x;
	      facesArray[i*3+1] = (int) faces.get(i).y;
	      facesArray[i*3+2] = (int) faces.get(i).z;
	   }
	   return facesArray;
	}
	
	private static int[] convertTexturesListToArray(List<Vector2f> textures){
	       int[] texturesArray = new int[textures.size()*2];
	       for(int i=0;i<textures.size();i++){
	          texturesArray[i*2] = (int) textures.get(i).x;
	          texturesArray[i*2+1] = (int) textures.get(i).y;
	       }
	       return texturesArray;
	    }

	private static float convertDataToArrays(List<Vertex> vertices, List<Vector2f> textures,
			List<Vector3f> normals, float[] verticesArray, float[] texturesArray,
			float[] normalsArray) {
		float furthestPoint = 0;
		for (int i = 0; i < vertices.size(); i++) {
			Vertex currentVertex = vertices.get(i);
			if (currentVertex.getLength() > furthestPoint) {
				furthestPoint = currentVertex.getLength();
			}
			Vector3f position = currentVertex.getPosition();
			/*Vector2f textureCoord = textures.get(currentVertex.getTextureIndex());
			Vector3f normalVector = normals.get(currentVertex.getNormalIndex());*/
			verticesArray[i * 3] = position.x;
			verticesArray[i * 3 + 1] = position.y;
			verticesArray[i * 3 + 2] = position.z;
			/*texturesArray[i * 2] = textureCoord.x;
			texturesArray[i * 2 + 1] = 1 - textureCoord.y;
			normalsArray[i * 3] = normalVector.x;
			normalsArray[i * 3 + 1] = normalVector.y;
			normalsArray[i * 3 + 2] = normalVector.z;*/
		}
		return furthestPoint;
	}
	
	private static void dealWithAlreadyProcessedVertex(Vertex previousVertex, int newTextureIndex,
			int newNormalIndex, List<Integer> indices, List<Vertex> vertices) {
		if (previousVertex.hasSameTextureAndNormal(newTextureIndex, newNormalIndex)) {
			indices.add(previousVertex.getIndex());
		} else {
			Vertex anotherVertex = previousVertex.getDuplicateVertex();
			if (anotherVertex != null) {
				dealWithAlreadyProcessedVertex(anotherVertex, newTextureIndex, newNormalIndex,
						indices, vertices);
			} else {
				Vertex duplicateVertex = new Vertex(vertices.size(), previousVertex.getPosition());
				duplicateVertex.setTextureIndex(newTextureIndex);
				duplicateVertex.setNormalIndex(newNormalIndex);
				previousVertex.setDuplicateVertex(duplicateVertex);
				vertices.add(duplicateVertex);
				indices.add(duplicateVertex.getIndex());
			}

		}
	}
	
	private static void removeUnusedVertices(List<Vertex> vertices){
		for(Vertex vertex:vertices){
			if(!vertex.isSet()){
				vertex.setTextureIndex(0);
				vertex.setNormalIndex(0);
			}
		}
	}

}