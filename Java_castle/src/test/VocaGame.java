package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class VocaGame extends SignListener{
	
	int problem_index=0;
	int index=0;
	public Vector<question[]> current_problem;
	public String[] current_string;
	public boolean iscorrect=false;
	public boolean gameend=false;
	
	public VocaGame()
	{
		this.SetModel("자모음");
		
		FileReader fis;
		BufferedReader br;
		int num=3;
		current_problem=new Vector<question[]>();
		current_string=new String[num];
		try {
			fis = new FileReader("단어.txt");
			br=new BufferedReader(fis);
			for(int i=0;i<num;i++)
			{
				for(int j=0;j<(Math.random()*20d);j++)
				{
					System.out.println(j);
					br.readLine();
				}
				current_string[i]=new String(br.readLine());
				StringtoProblem(current_string[i]);
			}
			fis.close();
			br.close();	
		} 
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}
	
	public void StringtoProblem(String s)
	{
		String st=UnicodeKorean.divide(s);
		System.out.println(st);
		question[] a=new question[st.length()];
		for(int i=0;i<a.length;i++)
		{
			a[i]=new question(UnicodeKorean.UnicodetoNum(st.charAt(i)));
		}
		current_problem.add(a);
	}
	
	
	public void start(){
		Timer t=new Timer();
		TimerTask ts=new TimerTask(){
			public void run() {
				if(current_state==current_problem.elementAt(index)[problem_index].code)
				{			
						current_problem.elementAt(index)[problem_index].iscorrect=true;	
						problem_index++;
						current_state=0;
						
						if(problem_index==current_problem.elementAt(index).length)
						{
							if(index==current_problem.size()-1)
							{
								gameend=true;
								this.cancel();
							}
							index++;
							problem_index=0;
							
							iscorrect=true;
							Timer t=new Timer();
							TimerTask ts=new TimerTask(){
								public void run() {
									iscorrect=false;
								}
							};
							t.schedule(ts,2000);
						}		
				}
		};};
		t.schedule(ts,20,20);
	}
}

