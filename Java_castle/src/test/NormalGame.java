package test;

import java.util.Timer;
import java.util.TimerTask;

public class NormalGame extends SignListener{
	
	public int current_problem=1;
	public boolean iscorrect=false;
	public boolean gameend;
	private int last_problem;
	
	
	public NormalGame(int k)
	{
		if(k==1)
			last_problem=31;
		if(k==2)
			last_problem=14;
		if(k==3)
		{
			current_problem=15;
			last_problem=31;
		}
		start();
	}
	
	public void start(){
		gameend=false;
		Timer t=new Timer();
		TimerTask ts=new TimerTask(){
			public void run() {
				if(current_state==current_problem&&iscorrect==false)
				{
					iscorrect=true;
					if(current_problem==last_problem)
					{
						gameend=true;
						this.cancel();
					}
					Timer t=new Timer();
					TimerTask ts=new TimerTask(){
						public void run() {
							iscorrect=false;
							current_problem++;
						}
					};
					t.schedule(ts,2000);
				}	
			}
		};
		t.schedule(ts,20,20);
	}	
}

