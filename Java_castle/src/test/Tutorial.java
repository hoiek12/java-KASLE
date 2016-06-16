package test;

import java.util.Timer;
import java.util.TimerTask;

public class Tutorial extends SignListener{

	public int current_problem=1;
	public boolean iscorrect=false;
	public int step=1;
	public boolean gameend=false;
	
	public Timer nt;
	public TimerTask nts;
	
	public Tutorial()
	{
		this.SetModel("ÀÚ¸ðÀ½");
		
		start();
	}
	
	public void start(){
		
		nt=new Timer();
		nts=new TimerTask(){
			public void run() {
				if(current_state==current_problem&&iscorrect==false)
				{
					iscorrect=true;
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
		
		Timer t2=new Timer();
		TimerTask ts2=new TimerTask(){
			public void run() {
				
				if(step==11)	
				{
					nt.schedule(nts,20,20);
					step++;
				}
				else if(step==12&&current_problem==15)
					step++;
				else if(step==12);
				else if(step==15)
				{
					gameend=true;
					this.cancel();
				}
				else
					step++;
			}
		};
		t2.schedule(ts2,5000,5000);
	}	

}
