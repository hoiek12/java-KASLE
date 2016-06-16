package test;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;

import dsada.game;

	public class mycanvas extends GLCanvas{
		public SimpleScene listener;
		public Animator animator;
		public game game;
		public mycanvas(SimpleScene listener,GLCapabilities g,game g2)
		{
			this.game=g2;
			this.setFocusable(true);
	        this.requestFocus();
	        this.setSize(1008,592);
	        this.addGLEventListener(listener);  
	        animator = new Animator(this);
	        animator.start();
	        
			this.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent arg0)
				{
					if(arg0.getX()>100&&arg0.getY()>100&&
					arg0.getX()<1000&&arg0.getY()<1200)
					{
						game.remove();	
					}
				}
			});
		}
		
	}
