package test;

import java.awt.Font;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.fixedfunc.GLLightingFunc;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.leapmotion.leap.Bone;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Vector;

public class DrawFunc {

	public static void renderCylinder(float x1, float y1, float z1, float x2,float y2, float z2, float radius,int subdivisions,GL gl,GLUquadric quadric)
	{
		float vx = x2-x1;
		float vy = y2-y1;
		float vz = z2-z1;

		GLU glu=new GLU();
		//handle the degenerate case of z1 == z2 with an approximation
		if(vz == 0)
			vz = .0001f;

		float v = (float) Math.sqrt( vx*vx + vy*vy + vz*vz );
		float ax = (float) (57.2957795*Math.acos( vz/v ));
		if ( vz < 0.0 )
			ax = -ax;
		float rx = -vy*vz;
		float ry = vx*vz;
		((GLMatrixFunc)gl).glPushMatrix();

		//draw the cylinder body
		((GLMatrixFunc)gl).glTranslatef( x1,y1,z1 );
		((GLMatrixFunc)gl).glRotatef(ax, rx, ry, 0.0f);
		glu.gluQuadricOrientation(quadric,glu.GLU_OUTSIDE);
		glu.gluCylinder(quadric, radius, radius, v, subdivisions, 1);

		//draw the first cap
		glu.gluQuadricOrientation(quadric,glu.GLU_INSIDE);
		glu.gluDisk( quadric, 0.0, radius, subdivisions, 1);
		((GLMatrixFunc)gl).glTranslatef( 0,0,v );

		//draw the second cap
		glu.gluQuadricOrientation(quadric,glu.GLU_OUTSIDE);
		glu.gluDisk( quadric, 0.0, radius, subdivisions, 1);
		((GLMatrixFunc)gl).glPopMatrix();
	}
	
	public static void renderCylinder_convenient(GL gl,Vector v1,Vector v2, float radius,int subdivisions)
	{
	//the same quadric can be re-used for drawing many cylinders
		GLU glu=new GLU();
		GLUquadric quadric=glu.gluNewQuadric();
		glu.gluQuadricNormals(quadric,GLU.GLU_SMOOTH);
		renderCylinder(v1.getX(),v1.getY(),v1.getZ(),v2.getX(),v2.getY(),v2.getZ(),radius,subdivisions,gl,quadric);
		glu.gluDeleteQuadric(quadric);
	}
	
	public static void DrawFinger(GL2 gl,Finger f,Hand h)
	{
		GLMatrixFunc glm=((GLMatrixFunc) gl);
		GLU glu=new GLU();
		
		Bone tipBone=f.bone(Bone.Type.TYPE_DISTAL);
		Bone pipBone=f.bone(Bone.Type.TYPE_INTERMEDIATE);
		Bone dipBone=f.bone(Bone.Type.TYPE_PROXIMAL);
		Bone mcpBone=f.bone(Bone.Type.TYPE_METACARPAL);
		
		Vector tippos=tipBone.nextJoint().minus(h.palmPosition());
		Vector pippos=pipBone.nextJoint().minus(h.palmPosition());
		Vector dippos=dipBone.nextJoint().minus(h.palmPosition());
		Vector mcppos=mcpBone.nextJoint().minus(h.palmPosition());		
		Vector mcppos2=mcpBone.prevJoint().minus(h.palmPosition());
		
		doLighting((GL2)gl,0.5f,0.5f,0.5f);
		renderCylinder_convenient(gl,tippos,pippos,4f,20);
		renderCylinder_convenient(gl,pippos,dippos,4f,20);
		renderCylinder_convenient(gl,dippos,mcppos,4f,20);
		
		if(f.type()==Finger.Type.TYPE_THUMB) 
			renderCylinder_convenient(gl,mcppos2,mcppos,4f,20);
		else if(f.type()==Finger.Type.TYPE_INDEX) 
		{
			renderCylinder_convenient(gl,mcppos,h.fingers().fingerType(Finger.Type.TYPE_THUMB).get(0).bone(Bone.Type.TYPE_METACARPAL).prevJoint().minus(h.palmPosition()),4f,20);
			renderCylinder_convenient(gl,mcppos,h.fingers().fingerType(Finger.Type.TYPE_MIDDLE).get(0).bone(Bone.Type.TYPE_METACARPAL).nextJoint().minus(h.palmPosition()),4f,20);
		}
		else if(f.type()==Finger.Type.TYPE_MIDDLE) 
			renderCylinder_convenient(gl,mcppos,h.fingers().fingerType(Finger.Type.TYPE_RING).get(0).bone(Bone.Type.TYPE_METACARPAL).nextJoint().minus(h.palmPosition()),4f,20);
		else if(f.type()==Finger.Type.TYPE_RING) 
			renderCylinder_convenient(gl,mcppos,h.fingers().fingerType(Finger.Type.TYPE_PINKY).get(0).bone(Bone.Type.TYPE_METACARPAL).nextJoint().minus(h.palmPosition()),4f,20);
		else if(f.type()==Finger.Type.TYPE_PINKY) 
		{
			renderCylinder_convenient(gl,mcppos,mcppos2,4f,20);
			renderCylinder_convenient(gl,mcppos2,h.fingers().fingerType(Finger.Type.TYPE_THUMB).get(0).bone(Bone.Type.TYPE_METACARPAL).prevJoint().minus(h.palmPosition()),4f,20);
			doLighting((GL2)gl,0f,1f,0f);
			glm.glPushMatrix();
			glm.glTranslatef(mcppos2.getX(),mcppos2.getY(),mcppos2.getZ());
			glu.gluSphere(glu.gluNewQuadric(),6d,10,10);
			glm.glPopMatrix();
		}
		doLighting((GL2)gl,0f,1f,0f);
		glm.glPushMatrix();
		glm.glTranslatef(tippos.getX(),tippos.getY(),tippos.getZ());
		glu.gluSphere(glu.gluNewQuadric(),6d,10,10);
		glm.glPopMatrix();
		
		glm.glPushMatrix();
		glm.glTranslatef(pippos.getX(),pippos.getY(),pippos.getZ());
		glu.gluSphere(glu.gluNewQuadric(),6d,10,10);
		glm.glPopMatrix();
		
		glm.glPushMatrix();
		glm.glTranslatef(dippos.getX(),dippos.getY(),dippos.getZ());
		glu.gluSphere(glu.gluNewQuadric(),6d,10,10);
		glm.glPopMatrix();
		
		glm.glPushMatrix();
		glm.glTranslatef(mcppos.getX(),mcppos.getY(),mcppos.getZ());
		glu.gluSphere(glu.gluNewQuadric(),6d,10,10);
		glm.glPopMatrix();
		
	}
	
	public static void DrawHand(GL2 gl,Hand h)
	{
		FingerList fl=h.fingers();
		Vector hpos=h.palmPosition();
		GLMatrixFunc glm=((GLMatrixFunc) gl);
		GLU glu=new GLU();
		
		glm.glPushMatrix();
		//glm.glMultMatrixf(h.basis().toArray4x4(),0);
		glm.glTranslatef(hpos.getX(), hpos.getY()-180, hpos.getZ());
		doLighting(gl,0,1,0);
		glu.gluSphere(glu.gluNewQuadric(),6d,10,10);
		DrawFinger(gl,fl.fingerType(Finger.Type.TYPE_INDEX).get(0),h);
		DrawFinger(gl,fl.fingerType(Finger.Type.TYPE_MIDDLE).get(0),h);
		DrawFinger(gl,fl.fingerType(Finger.Type.TYPE_PINKY).get(0),h);
		DrawFinger(gl,fl.fingerType(Finger.Type.TYPE_RING).get(0),h);
		DrawFinger(gl,fl.fingerType(Finger.Type.TYPE_THUMB).get(0),h);
		glm.glPopMatrix();
	}

	private static void doLighting( GL2 gl ,float a,float b,float c)
	 {
		 float lightpos[]={50005,30000,50000,1};
	     float[] noAmbient ={ a, b, c, 1f }; // low ambient light
	     
	     gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_AMBIENT, noAmbient, 0);
	     gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_POSITION, lightpos, 0);
	 }
	
	public static void DrawHandImageJaum(GL2 gl,double x,double y,double w,double h,int texture,int hand_state)
	{
		double a,b,c,d;
		a=(1d/5d)*((hand_state-1)%5);b=1-(1d/3d)*((hand_state-1)/5);
		c=(1d/5d)*((hand_state-1)%5)+(1d/5d);d=(2d/3d)-(1d/3d)*((hand_state-1)/5);
		
		gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2d(a,d);gl.glVertex2d(x,y);
		gl.glTexCoord2d(c,d);gl.glVertex2d(x+w,y);
		gl.glTexCoord2d(c,b);gl.glVertex2d(x+w,y+h);
		gl.glTexCoord2d(a,b);gl.glVertex2d(x,y+h);
		gl.glEnd();
	}

	public static void DrawHandImageMoum(GL2 gl,double x,double y,double w,double h,int texture,int hand_state)
	{
		double a,b,c,d;
		a=(1d/6d)*((hand_state-15)%6);b=1-(1d/3d)*((hand_state-15)/6);
		c=(1d/6d)*((hand_state-15)%6)+(1d/6d);d=(2d/3d)-(1d/3d)*((hand_state-15)/6);
		
		gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2d(a,d);gl.glVertex2d(x,y);
		gl.glTexCoord2d(c,d);gl.glVertex2d(x+w,y);
		gl.glTexCoord2d(c,b);gl.glVertex2d(x+w,y+h);
		gl.glTexCoord2d(a,b);gl.glVertex2d(x,y+h);
		gl.glEnd();
	}
	
	public static void DrawHandImageNumber(GL2 gl,double x,double y,double w,double h,int texture,int hand_state)
	{
		double a,b,c,d;
		a=(1d/6d)*((hand_state-1)%6);b=1-(1d/3d)*((hand_state-1)/6);
		c=(1d/6d)*((hand_state-1)%6)+(1d/6d);d=(2d/3d)-(1d/3d)*((hand_state-1)/6);
		
		gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2d(a,d);gl.glVertex2d(x,y);
		gl.glTexCoord2d(c,d);gl.glVertex2d(x+w,y);
		gl.glTexCoord2d(c,b);gl.glVertex2d(x+w,y+h);
		gl.glTexCoord2d(a,b);gl.glVertex2d(x,y+h);
		gl.glEnd();
	}
	
	public static void DrawUINormal(GL2 gl,NormalGame s,int texture_hand,int texture_hand2,int texture_background)
	{
		gl.glBindTexture(GL2.GL_TEXTURE_2D, texture_background);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2d(0,0);gl.glVertex2d(20,30);
		gl.glTexCoord2d(1,0);gl.glVertex2d(180,30);
		gl.glTexCoord2d(1,1);gl.glVertex2d(180,532);
		gl.glTexCoord2d(0,1);gl.glVertex2d(20,532);
		gl.glEnd();
		
		if(s.current_state<15)
			DrawFunc.DrawHandImageJaum(gl,50,250,100,100,texture_hand,s.current_state);
		else if(14<s.current_state&&32>s.current_state)
			DrawFunc.DrawHandImageMoum(gl,50,250,100,100,texture_hand2,s.current_state);
		
		if(s.current_problem<15)
			DrawFunc.DrawHandImageJaum(gl,50,400,100,100,texture_hand,s.current_problem);
		else if(14<s.current_problem&&32>s.current_problem)
			DrawFunc.DrawHandImageMoum(gl,50,400,100,100,texture_hand2,s.current_problem);
			
		DrawFunc.DrawAnswer(gl,s.iscorrect,s.gameend);
		DrawFunc.DrawUIstring();
	}
	
	public static void DrawUINumber(GL2 gl,NumberGame s,int texture_hand,int texture_background)
	{
		gl.glBindTexture(GL2.GL_TEXTURE_2D, texture_background);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2d(0,0);gl.glVertex2d(20,30);
		gl.glTexCoord2d(1,0);gl.glVertex2d(180,30);
		gl.glTexCoord2d(1,1);gl.glVertex2d(180,532);
		gl.glTexCoord2d(0,1);gl.glVertex2d(20,532);
		gl.glEnd();
		
		DrawFunc.DrawHandImageNumber(gl,50,250,100,100,texture_hand,s.current_state);
		
		DrawFunc.DrawHandImageNumber(gl,50,400,100,100,texture_hand,s.current_problem);
			
		DrawFunc.DrawAnswer(gl,s.iscorrect,s.gameend);
		DrawFunc.DrawUIstring();
	}
	
	public static void DrawUIstring()
	{
		Font f=new Font("Malgun Gothic", Font.BOLD, 20);
		TextRenderer trend = new TextRenderer(f,true,true);
		
		trend.beginRendering(1008,592);
		trend.draw("현재 문제", 50, 370);
		trend.endRendering();
		trend.beginRendering(1008,592);
		trend.draw("인식 상태", 50, 230);
		trend.endRendering();
	}
	
	public static void DrawAnswer(GL2 gl,boolean iscorrect,boolean isend)
	{
		Font f=new Font("Malgun Gothic", Font.BOLD, 30);
		TextRenderer trend = new TextRenderer(f,true,true);
		trend.setColor(1f,1f,1f,0.8f);
		gl.glBindTexture(GL.GL_TEXTURE_2D,0);
		trend.beginRendering(1008,592);
		if(isend)
			trend.draw("끝났습니다",450,100);
		else if(iscorrect)
			trend.draw("정답입니다", 450, 100);
		else
			trend.draw("맞는 지화를 해주세요", 450, 100);
		trend.endRendering();
	}
	
	public static void DrawUIRain(GL2 gl,RainGame s,boolean isvalid,int texture_hand,int texture_hand2,int texture_background)
	{
		gl.glBindTexture(GL2.GL_TEXTURE_2D, texture_background);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2d(0,0);gl.glVertex2d(20,30);
		gl.glTexCoord2d(1,0);gl.glVertex2d(180,30);
		gl.glTexCoord2d(1,1);gl.glVertex2d(180,532);
		gl.glTexCoord2d(0,1);gl.glVertex2d(20,532);
		gl.glEnd();
			
		if(s.current_state<15)
			DrawFunc.DrawHandImageJaum(gl,50,250,100,100,texture_hand,s.current_state);
		else
			DrawFunc.DrawHandImageMoum(gl,50,250,100,100,texture_hand2,s.current_state);
		DrawFunc.DrawUIstring();
	}
	
	public static void DrawGameLogic(GL2 gl,RainGame s,int texture_hand)
	{
		Font f=new Font("Malgun Gothic", Font.BOLD, 30);
		TextRenderer trend = new TextRenderer(f,true,true);
		
		java.util.Vector<question> v=s.question_array;
		for(int i=0;i<v.size();i++)
		{
			DrawHandImageJaum(gl,v.elementAt(i).x,v.elementAt(i).y,70,70,texture_hand,v.elementAt(i).code);
		}
		
		trend.setColor(1f, 1f,1f,0.8f);
		gl.glBindTexture(GL.GL_TEXTURE_2D,0);
		trend.beginRendering(1008,592);
		if(s.gameend)
			trend.draw("GAME OVER", 512,100);
		else
			trend.draw("SCORE : "+s.score, 512, 100);
		trend.endRendering();
	}
	
	public static void DrawUITutorial(GL2 gl,Tutorial s,int texture_hand,int texture_hand2,int texture_background)
	{
		gl.glBindTexture(GL2.GL_TEXTURE_2D, texture_background);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2d(0,0);gl.glVertex2d(20,30);
		gl.glTexCoord2d(1,0);gl.glVertex2d(180,30);
		gl.glTexCoord2d(1,1);gl.glVertex2d(180,532);
		gl.glTexCoord2d(0,1);gl.glVertex2d(20,532);
		gl.glEnd();
			
		if(s.current_state<15)
			DrawFunc.DrawHandImageJaum(gl,50,250,100,100,texture_hand,s.current_state);
		else
			DrawFunc.DrawHandImageMoum(gl,50,250,100,100,texture_hand2,s.current_state);
		
		if(s.current_problem<15)
			DrawFunc.DrawHandImageJaum(gl,50,400,100,100,texture_hand,s.current_problem);
		else
			DrawFunc.DrawHandImageMoum(gl,50,400,100,100,texture_hand2,s.current_problem);
			
		if(s.step<12||s.step==13||s.step==14)
			DrawFunc.DrawTutorialString(gl,s.step);
		else
			DrawFunc.DrawAnswer(gl,s.iscorrect,s.gameend);
		
			DrawFunc.DrawUIstring();
	}
	
	
	public static void DrawTutorialString(GL2 gl,int current_state)
	{
		Font f=new Font("Malgun Gothic", Font.BOLD, 25);
		TextRenderer trend = new TextRenderer(f,true,true);
		
		trend.setColor(1,1,1,(float) 0.8);
		gl.glBindTexture(GL.GL_TEXTURE_2D,0);
		trend.beginRendering(1008,592);
		if(current_state==1)
			trend.draw("튜토리얼에 온것을 환영합니다", 312, 100);
		else if(current_state==2)
			trend.draw("간단한 메뉴설명부터 드리겠습니다", 312, 100);
		else if(current_state==3)
			trend.draw("왼쪽 상단의 글자는 현재 문제입니다.", 312, 100);
		else if(current_state==4)
			trend.draw("그 아래의 손그림은 현재 인식되고 있는 손 상태입니다", 312, 100);
		else if(current_state==5)
			trend.draw("그럼 지화를 배워보도록 하겠습니다", 312, 100);
		else if(current_state==6)
			trend.draw("지화를 할때는 디바이스를 아래에 두고", 312, 100);
		else if(current_state==7)
			trend.draw("화면 방향으로 모션을 취해주십시오", 312, 100);
		else if(current_state==8)
			trend.draw("화면 중앙 화면에 시각화된 손을 보면", 312, 100);
		else if(current_state==9)
			trend.draw("기기가 손을 어떻게 인식하는지 확인 가능합니다", 312, 100);
		else if(current_state==10)
			trend.draw("이제 부터 자음 지화를 배워보도록 하겠습니다", 312, 100);
		else if(current_state==11)
			trend.draw("제스쳐를 그림에 따라 취해주십시오", 312, 100);
		else if(current_state==13)
			trend.draw("잘하셨습니다.다음 으로 모음 지화를 배워보겠습니다", 312, 100);
		else if(current_state==14)
			trend.draw("제스쳐를 그림에 따라 취해주십시오", 312, 100);
		trend.endRendering();	
	}

	public static void DrawUIVoca(GL2 gl,VocaGame s,int texture_hand,int texture_hand2,int texture_background)
	{
		gl.glBindTexture(GL2.GL_TEXTURE_2D, texture_background);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2d(0,0);gl.glVertex2d(20,30);
		gl.glTexCoord2d(1,0);gl.glVertex2d(180,30);
		gl.glTexCoord2d(1,1);gl.glVertex2d(180,532);
		gl.glTexCoord2d(0,1);gl.glVertex2d(20,532);
		gl.glEnd();
		
		if(s.current_state<15)
			DrawFunc.DrawHandImageJaum(gl,50,250,100,100,texture_hand,s.current_state);
		else if(14<s.current_state)
			DrawFunc.DrawHandImageMoum(gl,50,250,100,100,texture_hand2,s.current_state);
		if(!s.gameend)
		{
			for(int i=0;i<s.current_problem.elementAt(s.index).length;i++)
			{
				if(!s.current_problem.elementAt(s.index)[i].iscorrect)
				{
					if(s.current_problem.elementAt(s.index)[i].code<15)
						DrawFunc.DrawHandImageJaum(gl,400+50*i,450,50,50,texture_hand,s.current_problem.elementAt(s.index)[i].code);
					else if(14<s.current_problem.elementAt(s.index)[i].code&&32>s.current_problem.elementAt(s.index)[i].code)
						DrawFunc.DrawHandImageMoum(gl,400+50*i,450,50,50,texture_hand2,s.current_problem.elementAt(s.index)[i].code);
				}
			}
		
			Font f=new Font("Malgun Gothic", Font.BOLD, 20);
			TextRenderer trend = new TextRenderer(f,true,true);
			trend.setColor(1f,1f,1f,0.8f);
			gl.glBindTexture(GL.GL_TEXTURE_2D,0);
			trend.beginRendering(1008,592);
			trend.draw(s.current_string[s.index], 65, 420);
			trend.endRendering();
		}
		DrawFunc.DrawAnswer(gl,s.iscorrect,s.gameend);
		DrawFunc.DrawUIstring();
	}
	
	public static void DrawMenu(GL2 gl,int texture)
	{
		gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2d(0,0);gl.glVertex2d(936,10);
		gl.glTexCoord2d(1,0);gl.glVertex2d(998,10);
		gl.glTexCoord2d(1,1);gl.glVertex2d(998,60);
		gl.glTexCoord2d(0,1);gl.glVertex2d(936,60);
		gl.glEnd();
	}
	
}
