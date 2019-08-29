package shaders;

public class StaticShader extends ShaderProgram{
	
	private static final String VERYEX_FILE = "src/shaders/vertexShader.txt";
	private static final String FRAGMENT_FILE = "src/shaders/fragmentShader.txt";

	public StaticShader() {
		super(VERYEX_FILE, FRAGMENT_FILE);
	}

	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}
	
}