package test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class RainGame extends SignListener{

	public int score=0;
	public Vector<question> question_array;
	public boolean gameend=false;
	private float speed;
	
	public RainGame(float speed)
	{
		this.SetModel("ÀÚ¸ðÀ½");
		
		this.speed=speed;
		this.second=50;
		question_array=new Vector<question>();
		start();
	}
	
	public void start()
	{
		Timer t=new Timer();
		TimerTask ts=new TimerTask(){
			public void run() {
				gameend=true;
			}
		};
		t.schedule(ts,60000);
		
		Timer t2=new Timer();
		TimerTask ts2=new TimerTask(){
			public void run() {
				create_question();
				if(gameend==true)
					cancel();
			}
		};
		t2.schedule(ts2,0,5000);
		
		Timer t3=new Timer();
		TimerTask ts3=new TimerTask(){
			public void run() {
				if(gameend==true)
				{
					question_array.clear();
					this.cancel();
				}
				iscorrect();
			}
		};
		t3.schedule(ts3,2000,20);
		
	}
	
	public void iscorrect()
	{
		for(int i=0;i<question_array.size();i++)
		{
			question_array.elementAt(i).movedown();
			if(question_array.elementAt(i).code==current_state)
			{
				question_array.removeElementAt(i);
				score++;
				current_state=4000;
				break;
			}
			if(question_array.elementAt(i).y<0)
			{
				question_array.removeElementAt(i);
				break;
			}
		}
	}
	
	public void create_question()
	{
		int code=0;
		if(question_array.size()<10)
		{
			code=((int)(Math.random()*10000f)%12)+1;
			float x=(float) (230f+(Math.random()*1000f)%530f);
			float y=700f;
			question_array.add(new question(code,x,y,this.speed));
		}
	}
}
