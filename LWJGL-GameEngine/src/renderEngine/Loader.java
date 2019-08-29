package renderEngine;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

//CLASSE RESPONSAVEL EM ARAMAZENAR O MODELO 3D NA MEMORIA POR MEIO DE UM VAO.
public class Loader {
	
	//ARRAY LISTS QUE PERMITEM ARMAZENAR OS VAOS E VBOS
	private ArrayList<Integer> vaos = new ArrayList<Integer>();
	private ArrayList<Integer> vbos = new ArrayList<Integer>();
	
	//METODO QUE NOS PERMITE CARREGAR OS DADOA PARA O VAO
	public RawModel loadToVAO(float[] positions) {
		int vaoID = createVAO();
		storeDataInAttributeList(0, positions);
		unbindVAO();
		
		return new RawModel(vaoID, positions.length / 3);
	}
	
	//LIMPAR AS ARRAYLIST QUANDO FECHAR O JOGO
	public void cleanUp() {
		for(int vao:vaos) {
			GL30.glDeleteVertexArrays(vao);
		}
		for(int vbo:vbos) {
			GL15.glDeleteBuffers(vbo);
		}
	}
	
	//CRIAR O VAO VAZIO
	private int createVAO() {
		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		
		return vaoID;
	}
	
	//AMAZENAR OS DADOS NAS LISTAS DE ATRIBUTOS DO VAO COMO UM VBO
	private void storeDataInAttributeList(int attributeNumber, float[] data) {
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, 3, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	//DESABILITAR O VAO QUANDO TERMINAR O USO
	private void unbindVAO() {
		GL30.glBindVertexArray(0);
	}
	
	//CONVERSOR PARA FLOATBUFFER
	private FloatBuffer storeDataInFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		
		return buffer;
	}

}
