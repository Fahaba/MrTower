package al.artofsoul.ndihma;

import static org.lwjgl.opengl.GL11.*;

import java.io.*;

import java.io.IOException;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;



import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


public class Artist {
	
	public static final int WIDTH =960;
    public static final int HEIGHT = 480; //960 3X32 - 894 640,
	public static final int TILE_SIZE = 32;

	private Artist() {
		throw new IllegalStateException("Utility class");
	}

	public static void beginSession(){
		
		Display.setTitle("Mr Tower");
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1); //camera that project the screen
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
	}
	
	public static boolean checkCollosion(float x1, float y1, float width1, float height1,
                                         float x2, float y2, float width2) {
		return (x1 + width1 > x2
                && x1 < x2 + width2
                && y1 + height1 > y2
                && y1 > y2 + height1);
	}
	
	public static void vizatoKatror(float x, float y, float width, float height) {
		//deklarimi i katerorit
		glBegin(GL_QUADS);
		glVertex2f(x, y); // Top left corner
		glVertex2f(x + width, y); // Top right corner
		glVertex2f(x + width, y + height); // Top right Corner
		glVertex2f(x, y + height); // Top left Corner
		glEnd();
		
	}
	
	public static void vizatoKatrorTex(Texture tex, float x, float y, float width, float height){
		tex.bind();
		glTranslatef(x, y, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0);
		glTexCoord2f(1, 0);
		glVertex2f(width, 0);
		glTexCoord2f(1, 1);
		glVertex2f(width, height);
		glTexCoord2f(0, 1);
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();
		
	}
	
	public static void vizatoKatrorTexRot(Texture tex, float x, float y, float width, float height, float angle){
		tex.bind();
		glTranslatef(x + width / 2, y + height / 2, 0);
		glRotatef(angle, 0, 0, 1);
		glTranslatef( - width / 2, - height / 2, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0);
		glTexCoord2f(1, 0);
		glVertex2f(width, 0);
		glTexCoord2f(1, 1);
		glVertex2f(width, height);
		glTexCoord2f(0, 1);
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();
		
	}


	public static Texture loadTexture(String path, String filetype) {
		Texture tex = null;
		InputStream in = ResourceLoader.getResourceAsStream(path);
		try {
			tex = TextureLoader.getTexture(filetype, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tex;
	}
	
	public static Texture quickLoad(String name){
		Texture tex = null;
		tex = loadTexture("" + name + ".png", "PNG");
		return tex;
	}
}
