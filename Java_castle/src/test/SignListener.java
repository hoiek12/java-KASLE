package test;

import java.io.IOException;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;

import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;

public class SignListener extends Listener{

	private svm_model model;
	public int current_state=4000;
	public int prev_state=4000;
	public int state_count=0;
	public int second=50;
	
	public void SetModel(String s)
	{
		try {
			model=svm.svm_load_model(s+".model");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private svm_node[] HandtoNode(Hand h)
	{
		svm_node[] x=new svm_node[14];
		
		if(h.isValid()==false)
			return null;
		Finger thumb=h.fingers().fingerType(Finger.Type.TYPE_THUMB).get(0);
		Finger index=h.fingers().fingerType(Finger.Type.TYPE_INDEX).get(0);
		Finger middle=h.fingers().fingerType(Finger.Type.TYPE_MIDDLE).get(0);
		Finger pinky=h.fingers().fingerType(Finger.Type.TYPE_PINKY).get(0);
		Finger ring=h.fingers().fingerType(Finger.Type.TYPE_RING).get(0);
		
		//palm direction
		x[0]=new svm_node();
		x[0].index=1;
		x[0].value=Math.round(h.palmNormal().getX()*100)/100d;
		x[1]=new svm_node();
		x[1].index=2;
		x[1].value=Math.round(h.palmNormal().getY()*100)/100d;
		x[2]=new svm_node();
		x[2].index=3;
		x[2].value=Math.round(h.palmNormal().getZ()*100)/100d;
		//thumb distnace to palm
		x[3]=new svm_node();
		x[3].index=4;
		x[3].value=Math.round(thumb.direction().dot(h.palmNormal())*100)/100d;
		//index distnace to palm
		x[4]=new svm_node();
		x[4].index=5;
		x[4].value=Math.round(index.direction().dot(h.palmNormal())*100)/100d;
		//middle distnace to palm
		x[5]=new svm_node();
		x[5].index=6;
		x[5].value=Math.round(middle.direction().dot(h.palmNormal())*100)/100d;
		//pinky distnace to palm
		x[6]=new svm_node();
		x[6].index=7;
		x[6].value=Math.round(pinky.direction().dot(h.palmNormal())*100)/100d;
		//ring distnace to palm
		x[7]=new svm_node();
		x[7].index=8;
		x[7].value=Math.round(ring.direction().dot(h.palmNormal())*100)/100d;
		//thumb direction
		x[8]=new svm_node();
		x[8].index=9;
		x[8].value=Math.round(thumb.direction().dot(index.direction())*100)/100d;
		x[9]=new svm_node();
		x[9].index=10;
		x[9].value=Math.round(index.direction().dot(middle.direction())*100)/100d;
		x[10]=new svm_node();
		x[10].index=11;
		x[10].value=Math.round(middle.direction().dot(ring.direction())*100)/100d;
		//hand direction
		x[11]=new svm_node();
		x[11].index=12;
		x[11].value=Math.round(h.direction().getX()*100)/100d;
		x[12]=new svm_node();
		x[12].index=13;
		x[12].value=Math.round(h.direction().getY()*100)/100d;
		x[13]=new svm_node();
		x[13].index=14;
		x[13].value=Math.round(h.direction().getZ()*100)/100d;
		
		return x;
	}
	
	public void onConnect(Controller c)
	{
		System.out.println("Connected");
	}
	
	public void onDisconnect(Controller c)
	{
		System.out.println("Disconnected");
	}
	
	public void onFrame (Controller c)
	{
		svm_node[] node=HandtoNode(c.frame().hands().get(0));
		int state=(int) svm.svm_predict(model,node);
		if(prev_state!=state){
			prev_state=state;
			state_count=0;
		}
		else { 
			state_count++;
		}	
		if(state_count>second) {
			current_state=(int) state;
		}	
	}

}
