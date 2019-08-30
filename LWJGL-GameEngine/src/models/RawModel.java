package models;

//CLASSE QUE REPRESENTA O MODELO 3D ARMAZENADO EM MEMÓRIA
public class RawModel {

	//PEGA O ID DO VAO E O NUMERO DE VERTICES 
	private int vaoID;
	private int vertexCount;
	
	public RawModel(int vaoID, int vertexCount) {
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}
	
}
