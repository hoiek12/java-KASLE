package test;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;

	public class mycanvas extends GLCanvas{
		public SimpleScene listener;
		public Animator animator;
		public dsada.game game;
		public mycanvas(SimpleScene listener,GLCapabilities g,dsada.game g2)
		{
			this.game=g2;
			this.setFocusable(true);
	        this.requestFocus();
	        this.setSize(1008,592);
	        this.addGLEventListener(listener);  
	        animator = new Animator(this);
	        animator.start();
	        
	        this.addMouseListener(new MouseAdapter(){

				public void mouseClicked(MouseEvent e) {
					if(e.getX()<1024&&e.getY()<594
							&&e.getX()>924&&e.getY()>480)
					{	
						game.remove();
					}
					
				}

	        });
		}
		
	}
