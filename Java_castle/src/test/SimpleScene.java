package test;

import java.awt.Frame;
import java.io.File;
import java.io.IOException;

import com.jogamp.opengl.*;
import com.jogamp.opengl.fixedfunc.GLLightingFunc;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import com.leapmotion.leap.Controller;

public class SimpleScene implements GLEventListener{

	private int w=1008;//default if not fullscreen
	private int h=592;
	
	private GL gl;
	private int texture_hand,texture_hand2,texture_hand3,texture_background,texture_menu;
	//OpenGL
	private Controller c;
	public SignListener s;
	//sign recognition and game logic

    int k;
    
    public SimpleScene(int k)
    {
    	c=new Controller();
    	if(k==1)
    	{
    		s=new NormalGame(1);
    		c.addListener(s);		
    	}
    	else if(k==2)
    	{
    		s=new NumberGame();
    		c.addListener(s);	
    	}
    	else if(k==3)
    	{
    		s=new Tutorial();
    		c.addListener(s);
    	}
    	else if(k==4)
    	{
    		s=new VocaGame();
    		c.addListener(s);
    	}
    	else if(k==5)
    	{
    		s=new RainGame(1f);
    		c.addListener(s);
    	}
    	this.k=k;
    }
    
	public void init(GLAutoDrawable drawable) {
		gl=drawable.getGL();
		gl.glViewport(0,0, w, h);
		gl.glClearColor(0f, 0f, 0f, 0f);
		LoadTexture(gl);
	}

	private void LoadTexture(GL gl)
	{
		try {
			Texture t=TextureIO.newTexture(new File("자음.png"), false);
			texture_hand=t.getTextureObject(gl);
			t=TextureIO.newTexture(new File("모음.png"), false);
			texture_hand2=t.getTextureObject(gl);
			t=TextureIO.newTexture(new File("숫자.png"), false);
			texture_hand3=t.getTextureObject(gl);
			t=TextureIO.newTexture(new File("배경.png"), false);
			texture_background=t.getTextureObject(gl);
			t=TextureIO.newTexture(new File("메뉴.png"), false);
			texture_menu=t.getTextureObject(gl);
		} catch (GLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void init3D(GL gl,GLU glu)
	{
		((GLMatrixFunc) gl).glMatrixMode(GLMatrixFunc.GL_PROJECTION);  
		((GLMatrixFunc) gl).glLoadIdentity();
		glu.gluPerspective(80.0f, (float)1, 0.1f, 700f);
		((GLMatrixFunc) gl).glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		((GLMatrixFunc) gl).glLoadIdentity();
		glu.gluLookAt(0,0, 200, 0,0, 0, 0, 1, 0);
		gl.glDisable(GL.GL_TEXTURE_2D);
		gl.glDisable(GL.GL_BLEND);
		gl.glEnable(GL.GL_CULL_FACE);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glEnable(GLLightingFunc.GL_LIGHTING);
	    gl.glEnable(GLLightingFunc.GL_LIGHT0);
	    
	}
	
	private void init2D(GL gl,GLU glu)
	{
		((GLMatrixFunc) gl).glMatrixMode(GLMatrixFunc.GL_PROJECTION);  
		((GLMatrixFunc) gl).glLoadIdentity();
		glu.gluOrtho2D(0,w,0,h);
		((GLMatrixFunc) gl).glMatrixMode(GLMatrixFunc.GL_MODELVIEW);  
		((GLMatrixFunc) gl).glLoadIdentity();	
	
		gl.glEnable(GL.GL_TEXTURE_2D);
		gl.glEnable(GL.GL_BLEND);
		gl.glBlendFunc(GL.GL_SRC_ALPHA,GL.GL_ONE_MINUS_SRC_ALPHA);
		gl.glDisable(GL.GL_CULL_FACE);
		gl.glDisable(GL.GL_DEPTH_TEST);
		gl.glDisable(GLLightingFunc.GL_LIGHTING);
		gl.glDisable(GLLightingFunc.GL_LIGHT0);
		((GLLightingFunc) gl).glColor4f(1,1,1,0.9f);
	}
	
	public void display(GLAutoDrawable drawable) {
		GL2 gl=this.gl.getGL2();
		GLU glu=new GLU();
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT);
		
	    init3D((GL)gl,glu);
		if(c.frame().hands().get(0).isValid())
		{
			((GLMatrixFunc) gl).glPushMatrix();
			DrawFunc.DrawHand(gl,c.frame().hands().get(0));
			((GLMatrixFunc) gl).glPopMatrix();
		}
		//3D hand drawing

		init2D((GL)gl,glu);
		if(k==1)
		{
			DrawFunc.DrawUINormal(gl,(NormalGame) s, texture_hand,texture_hand2,texture_background);
		}
		else if(k==2)
		{
			DrawFunc.DrawUINumber(gl, (NumberGame)s, texture_hand3, texture_background);
		}
		else if(k==3)
		{
			DrawFunc.DrawUITutorial(gl, (Tutorial) s, texture_hand,texture_hand2,texture_background);
		}
		else if(k==4)
		{
			DrawFunc.DrawUIVoca(gl, (VocaGame) s, texture_hand,texture_hand2,texture_background);
		}
		else if(k==5)
		{
			DrawFunc.DrawUIRain(gl,(RainGame) s,c.frame().isValid(),texture_hand,texture_hand2,texture_background);
			DrawFunc.DrawGameLogic(gl, (RainGame) s, texture_hand);
		}
		
		DrawFunc.DrawMenu(gl, texture_menu);
	}
	
	public void dispose(GLAutoDrawable drawable) {
	}
	
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {	
		
	}
}