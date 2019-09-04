package engineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		//INICIA E  CRIA O DISPLAY
		DisplayManager.createDisplay();
		
		//IMPORTANDO CLASSES
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);

		RawModel model = OBJLoader.loadObjModel("camp", loader);
		
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("camp")));
		
		Entity entity = new Entity(staticModel, new Vector3f(0, -1, -10), 0, 0, 0, 1);
		Light light = new Light(new Vector3f(0,0,-5), new Vector3f(1,1,1));
		
		Camera camera = new Camera();
		
		//LOOP
		while(!Display.isCloseRequested()) {
			entity.increaseRotation(0, 0.01f, 0);
			camera.move();
			renderer.prepare();
			shader.start();
			shader.loadLight(light);
			shader.loadViewMatrix(camera);
			renderer.render(entity, shader);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		//LIMPANDO A MEMORIA
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
