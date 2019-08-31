package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	
	//LARGURA, ALTURA, FPS
	private static final int W = 1280;
	private static final int H = 720;
	private static final int FPS = 720;
	
	public static void createDisplay() {
		
		ContextAttribs attribs = new ContextAttribs(3,2)
		.withForwardCompatible(true)
		.withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(W, H));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle("LWJGL - Game");
			Display.setResizable(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		GL11.glViewport(0, 0, W, H);
	}
	
	public static void updateDisplay() {
		Display.sync(FPS);
		Display.update();
	}
	
	public static void closeDisplay() {
		Display.destroy();
	}

}
