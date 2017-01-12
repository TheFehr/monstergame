package view.graphicEngine;

import view.utils.Vector3f;

import static org.lwjgl.opengl.GL20.*;
import static view.utils.Utilities.*;
import static view.utils.Utilities.loadShader;

/**
*
* Diese ist eine Hilfsklasse die fÃ¼r die Initialiserung von vertexShader-Dateien und FragmentShader-Dateien sorgt.
*
*/
public class Shader {

	private int programID;
	private int vertexShaderID;
	private int fragmentShaderID;

	public Shader(String vertexFile, String fragmentFile) {
		vertexShaderID = loadShader(getClass().getResourceAsStream(vertexFile), GL_VERTEX_SHADER);
		fragmentShaderID = loadShader(getClass().getResourceAsStream(fragmentFile), GL_FRAGMENT_SHADER);

		programID = glCreateProgram();
		glAttachShader(programID, fragmentShaderID);
		glAttachShader(programID, vertexShaderID);
		glLinkProgram(programID);
		glValidateProgram(programID);

	}

	public int getID(){
		return programID;
	}

	public int getUniform(String name){
		int result = glGetUniformLocation(programID, name);
		if(result == -1){
			System.err.println("Could not find uniform variable " + name + "!");
		}
		return result;
	}

	public void setUniform3f(String name, Vector3f position){
		glUniform3f(getUniform(name), position.x, position.y, position.z);
	}

	public void start(){
		glUseProgram(programID);
	}

	public void stop(){
		glUseProgram(0);
	}
}
