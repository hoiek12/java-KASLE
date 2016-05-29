package testsvm;

import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;

import libsvm.*;

public class test extends Frame{
	
	private svm_parameter param;
	private serial_svm_problem prob;
	private svm_problem new_prob;
	private svm_model model;
	
	private final int NUM_INSTANCE=100;
	private final int NUM_FEATURES=14;
	private int nr_fold;
	
	private Controller c;
	
	public void set_param()
	{
		param = new svm_parameter();
		// default values
		param.svm_type = svm_parameter.C_SVC;
		param.kernel_type = svm_parameter.RBF;
		param.degree = 3;
		param.gamma = 1d/(double)NUM_FEATURES;
		param.coef0 = 0;
		param.nu = 0.5;
		param.cache_size = 100;
		param.C = 1000;
		param.eps = 1e-3;
		param.p = 0.1;
		param.shrinking = 1;
		param.probability = 0;
		param.nr_weight = 0;
		param.weight_label = new int[0];
		param.weight = new double[0];
		this.nr_fold=10;	
	}
	
	private svm_node[] HandtoNode()
	{
		svm_node[] x=new svm_node[NUM_FEATURES];
		Hand h=c.frame().hands().get(0);
		
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
		x[3].value=thumb.direction().dot(h.palmNormal());
		//index distnace to palm
		x[4]=new svm_node();
		x[4].index=5;
		x[4].value=index.direction().dot(h.palmNormal());
		//middle distnace to palm
		x[5]=new svm_node();
		x[5].index=6;
		x[5].value=middle.direction().dot(h.palmNormal());
		//pinky distnace to palm
		x[6]=new svm_node();
		x[6].index=7;
		x[6].value=pinky.direction().dot(h.palmNormal());
		//ring distnace to palm
		x[7]=new svm_node();
		x[7].index=8;
		x[7].value=ring.direction().dot(h.palmNormal());
		//thumb direction
		x[8]=new svm_node();
		x[8].index=9;
		x[8].value=thumb.direction().dot(index.direction());
		x[9]=new svm_node();
		x[9].index=10;
		x[9].value=index.direction().dot(middle.direction());
		x[10]=new svm_node();
		x[10].index=11;
		x[10].value=middle.direction().dot(ring.direction());
		
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

	private void do_cross_validation()
	{
		int i;
		int total_correct = 0;
		double[] target = new double[prob.l];

		svm.svm_cross_validation(prob,param,nr_fold,target);
	
		for(i=0;i<prob.l;i++)
			if(target[i] == prob.y[i])
				++total_correct;
		System.out.print("Cross Validation Accuracy = "+100.0*total_correct/prob.l+"%\n");
	}

	private serial_svm_problem combine_problem(svm_problem origin_prob, svm_problem new_prob)
	{
		serial_svm_problem comb_prob=new serial_svm_problem();
		comb_prob.l=origin_prob.l+new_prob.l;
		comb_prob.x=new svm_node[comb_prob.l][NUM_FEATURES];
		comb_prob.y=new double[comb_prob.l];
		
		int i=0;
		double max_y=0;
		
		for(;i<origin_prob.l;i++)
		{
			for(int j=0;j<NUM_FEATURES;j++)
			{
				comb_prob.x[i][j]=new svm_node();
				comb_prob.x[i][j].value=origin_prob.x[i][j].value;
				comb_prob.x[i][j].index=origin_prob.x[i][j].index;
			}
			comb_prob.y[i]=origin_prob.y[i];
			max_y=Math.max(max_y,origin_prob.y[i]);
		}
		
		Vector<Double> vy=new Vector<Double>();
		int numofy=0;
		
		for(int a=0;a<new_prob.l;a++)
		{
			if(!vy.contains(new_prob.y[a]))
			{
				vy.add(new_prob.y[a]);
				numofy++;
			}
		}
		Vector<Double> newvy=new Vector<Double>();
		
		for(int a=0;a<numofy;a++)
		{
			newvy.addElement(++max_y);
		}
		
		for(int k=0;k<new_prob.l;i++,k++)
		{
			for(int j=0;j<NUM_FEATURES;j++)
			{
				comb_prob.x[i][j]=new svm_node();
				comb_prob.x[i][j].value=new_prob.x[k][j].value;
				comb_prob.x[i][j].index=new_prob.x[k][j].index;
			}
			int index=vy.indexOf(new_prob.y[k]);
			comb_prob.y[i]=newvy.elementAt(index);
		}
		return comb_prob;	
	}
	
	private svm_problem make_problem_data(int num_inst) throws InterruptedException
	{
		svm_problem prob=new svm_problem();
		prob.l=num_inst;
		prob.x=new svm_node[num_inst][];
		prob.y=new double[num_inst];
		
		int i=0;
		long previd=0;
		while(i<num_inst)
		{
			if(c.frame().id()>previd+3)
			{
				svm_node[] node=HandtoNode();
				if(node==null)
					return null;
				prob.x[i]=node;
				prob.y[i]=1;
				i++;previd=c.frame().id();
				System.out.println("Hand data instacnes collecting... "+(double)i/(double)num_inst*100d+"%");
			}
		}
		System.out.println("data collecting completed");
		
		return prob;
		
	}
	
	public void print_svm_problem(svm_problem prob)
	{
		for(int i=0;i<prob.l;i++)
		{
			System.out.print(prob.y[i]+"  ");
			for(int j=0;j<NUM_FEATURES;j++)
			{
				System.out.print(prob.x[i][j].index+":"+prob.x[i][j].value+" ");
			}
			System.out.println();
		}
		System.out.println("total number of instances : "+prob.l);
	}
		
	private void scale_svm_problem(svm_problem prob)
	{
		double feature_max[]=new double[NUM_FEATURES];
		double feature_min[]=new double[NUM_FEATURES];
		
		for(int i=0;i<NUM_FEATURES;i++)
		{
			feature_max[i]=-Double.MAX_VALUE;
			feature_min[i]=Double.MAX_VALUE;
		}
		
		for(int i=0;i<prob.l;i++)
		{
			for(int j=0;j<NUM_FEATURES;j++)
			{
				feature_max[j]=Math.max(prob.x[i][j].value,feature_max[j]);
				feature_min[j]=Math.min(prob.x[i][j].value,feature_min[j]);
			}
		}
		
		for(int i=0;i<NUM_FEATURES;i++)
		{
			System.out.println(feature_max[i]+","+feature_min[i]);
			
		}
		
		for(int i=0;i<prob.l;i++)
		{
			for(int j=0;j<NUM_FEATURES;j++)
			{
				if(feature_max[j]!=feature_min[j])
				prob.x[i][j].value=2*(feature_max[j]-prob.x[i][j].value)/(feature_max[j]-feature_min[j])-1;
			}
		}
		
	}
	
	public test()
	{
		this.setVisible(true);
		this.set_param();
		
		prob=new serial_svm_problem();
		prob.l=0;
		new_prob=new svm_problem();
		new_prob.l=0;
		
		this.c=new Controller();
		
		this.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_1)
				{				
					model=svm.svm_train(prob, param);
					try {
						svm.svm_save_model("asd.model", model);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(arg0.getKeyCode()==KeyEvent.VK_2)
				{
					try {
						double k=svm.svm_predict(svm.svm_load_model("asd.model"),HandtoNode());
						System.out.println(k);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(arg0.getKeyCode()==KeyEvent.VK_C)
				{
					do_cross_validation();
				}
				if(arg0.getKeyCode()==KeyEvent.VK_L)
				{
					FileInputStream fis;
					try {
						fis = new FileInputStream("svm_problem.dat");
						ObjectInputStream ois=new ObjectInputStream(fis);
						prob=(serial_svm_problem) ois.readObject();
						print_svm_problem(prob);
						fis.close();
						ois.close();
						System.out.println("load from data");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}	
				}
				if(arg0.getKeyCode()==KeyEvent.VK_F)
				{
					FileInputStream fis;
					try {
						fis = new FileInputStream("¸ðÀ½.dat");
						ObjectInputStream ois=new ObjectInputStream(fis);
						new_prob=(serial_svm_problem) ois.readObject();
						print_svm_problem(prob);
						fis.close();
						ois.close();
						System.out.println("load from data");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}	
				}
				if(arg0.getKeyCode()==KeyEvent.VK_S)
				{
					FileOutputStream fos;
					try {
						fos = new FileOutputStream("svm_problem.dat");
						ObjectOutputStream oos=new ObjectOutputStream(fos);
						oos.writeObject(prob);
						oos.close();
						fos.close();
						System.out.println("save current problem");
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(arg0.getKeyCode()==KeyEvent.VK_D)
				{
					FileOutputStream fos;
					try {
						fos = new FileOutputStream("svm_problem_new.dat");
						ObjectOutputStream oos=new ObjectOutputStream(fos);
						oos.writeObject(new_prob);
						oos.close();
						fos.close();
						System.out.println("save new current problem");
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				if(arg0.getKeyCode()==KeyEvent.VK_3)
				{
						try {
						svm_problem problem=make_problem_data(NUM_INSTANCE);
						if(problem!=null){
							new_prob=combine_problem(new_prob,problem);
							print_svm_problem(new_prob);
							System.out.println("new problem data added"); 
						}
						else{
							System.out.println("data collecting failed");
						}
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
				if(arg0.getKeyCode()==KeyEvent.VK_4)
				{
					prob=(serial_svm_problem) combine_problem(prob,new_prob);
					print_svm_problem(prob);
					System.out.println("combine new data with current problem");
				}
			}//KeyPressed
		});//addKeyListener
		this.setSize(300, 300);
	}

	public static void main(String[] args) throws InterruptedException{
		test a=new test();

	}	
}




























































































































































































































































































