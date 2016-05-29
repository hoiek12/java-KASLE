package testhmm;

import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Vector;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Hand;

import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import be.ac.ulg.montefiore.run.jahmm.learn.BaumWelchLearner;

public class test extends Frame{

	private final int FEATURE_NUM=23;
	private final int STATE_NUM=5;
	
	Controller c;
	
	BaumWelchLearner bl;
	Hmm model;
	Vector<Vector<ObservationVector>> observation_seq_vector=new Vector<Vector<ObservationVector>>(20);
	
	private void printarray(double[] x)
	{
		for(int i=0;i<x.length;i++)
		{
			System.out.print(x[i]+" ");
		}
		System.out.println();
	}
	
	private ObservationVector FrametoObservation()
	{
		double[] x=new double[FEATURE_NUM];
		com.leapmotion.leap.Frame f=c.frame();
		
		Hand Rhand=f.hands().rightmost();
		Finger rthumb=Rhand.fingers().fingerType(Finger.Type.TYPE_THUMB).get(0);
		Finger rindex=Rhand.fingers().fingerType(Finger.Type.TYPE_INDEX).get(0);
		Finger rmiddle=Rhand.fingers().fingerType(Finger.Type.TYPE_MIDDLE).get(0);
		Finger rpinky=Rhand.fingers().fingerType(Finger.Type.TYPE_PINKY).get(0);
		Finger rring=Rhand.fingers().fingerType(Finger.Type.TYPE_RING).get(0);

		//palm direction
		x[0]=Math.round(Rhand.palmNormal().getX()*100)/100d;
		x[1]=Math.round(Rhand.palmNormal().getY()*100)/100d;
		x[2]=Math.round(Rhand.palmNormal().getZ()*100)/100d;
		//thumb distnace to palm
		x[3]=rthumb.direction().dot(Rhand.palmNormal());
		//index distnace to palm
		x[4]=rindex.direction().dot(Rhand.palmNormal());
		//middle distnace to palm
		x[5]=rmiddle.direction().dot(Rhand.palmNormal());
		//pinky distnace to palm
		x[6]=rpinky.direction().dot(Rhand.palmNormal());
		//ring distnace to palm
		x[7]=rring.direction().dot(Rhand.palmNormal());
		
		Hand Lhand=f.hands().leftmost();
		Finger lthumb=Lhand.fingers().fingerType(Finger.Type.TYPE_THUMB).get(0);
		Finger lindex=Lhand.fingers().fingerType(Finger.Type.TYPE_INDEX).get(0);
		Finger lmiddle=Lhand.fingers().fingerType(Finger.Type.TYPE_MIDDLE).get(0);
		Finger lpinky=Lhand.fingers().fingerType(Finger.Type.TYPE_PINKY).get(0);
		Finger lring=Lhand.fingers().fingerType(Finger.Type.TYPE_RING).get(0);
		//palm direction
		x[8]=Math.round(Lhand.palmNormal().getX()*100)/100d;
		x[9]=Math.round(Lhand.palmNormal().getY()*100)/100d;
		x[10]=Math.round(Lhand.palmNormal().getZ()*100)/100d;
		//thumb distnace to palm
		x[11]=lthumb.direction().dot(Rhand.palmNormal());
		//index distnace to palm
		x[12]=lindex.direction().dot(Rhand.palmNormal());
		//middle distnace to palm
		x[13]=lmiddle.direction().dot(Rhand.palmNormal());
		//pinky distnace to palm
		x[14]=lpinky.direction().dot(Rhand.palmNormal());
		//ring distnace to palm
		x[15]=lring.direction().dot(Rhand.palmNormal());	
		x[16]=Rhand.translation(c.frame(10)).normalized().getX();
		x[17]=Rhand.translation(c.frame(10)).normalized().getY();
		x[18]=Rhand.translation(c.frame(10)).normalized().getZ();
		x[19]=Lhand.translation(c.frame(10)).normalized().getX();
		x[20]=Lhand.translation(c.frame(10)).normalized().getY();
		x[21]=Lhand.translation(c.frame(10)).normalized().getZ();
		x[22]=Rhand.palmPosition().distanceTo(Lhand.palmPosition())/100;
		printarray(x);
		return new ObservationVector(x);
		
	}
	
	private Vector<ObservationVector> New_Observation_Seq_fast(int k)
	{
		Vector<ObservationVector> obseq=new Vector<ObservationVector>(k);
		
		System.out.println("ready");
		try {
			Thread.sleep(2000);
			System.out.println("start");
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		int i=0;
		long previd=0;
		System.out.println("sfsd");
		while(i<k)
		{
			if(c.frame().isValid()&&c.frame().id()>previd+9)
			{
				if(c.frame().hands().count()<2)
				{
					return null;
				}
				obseq.add(FrametoObservation());
				System.out.println("collecting..."+i*10);
				previd=c.frame().id();
				i++;
			}
		}
		return obseq;
	}
	
	private void print(int[] a)
	{
		for(int i=0;i<a.length;i++)
		{
			System.out.println(a[i]);
			if(a[i]==4)
			{
				System.out.println("correct!");
				break;
			}
		}
	}
	
	
	public test()
	{
		c=new Controller();
		
		this.addKeyListener(new KeyAdapter()
				{
					public void keyPressed(KeyEvent arg0) {
						if(arg0.getKeyCode()==KeyEvent.VK_1)
						{		
							Vector<ObservationVector> v=New_Observation_Seq_fast(20);
							print(model.mostLikelyStateSequence(v));	
							System.out.println(model.probability(v));
							System.out.println(model.lnProbability(v));
						}
						if(arg0.getKeyCode()==KeyEvent.VK_L)
						{
							FileInputStream fis;
							try {
								fis = new FileInputStream("hmm_model.dat");
								ObjectInputStream ois=new ObjectInputStream(fis);
								model=(Hmm) ois.readObject();
								fis.close();
								ois.close();
								System.out.println("load from data");
								System.out.println(model.toString());
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
								fos = new FileOutputStream("hmm_model.dat");
								ObjectOutputStream oos=new ObjectOutputStream(fos);
								oos.writeObject(model);
								oos.close();
								fos.close();
								System.out.println("save current problem");
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						else if(arg0.getKeyCode()==KeyEvent.VK_2)
						{	
							bl=new BaumWelchLearner();
							model=bl.learn(new Hmm<ObservationVector>(hmmprob.initedPi(STATE_NUM),hmmprob.initedA(STATE_NUM),hmmprob.initedOpdf(STATE_NUM,4,FEATURE_NUM)), observation_seq_vector);
							System.out.println(model);
						}
						else if(arg0.getKeyCode()==KeyEvent.VK_3)
						{		
							Vector<ObservationVector> v=New_Observation_Seq_fast(20);
							if(v!=null)
							{
								observation_seq_vector.add(v);
								System.out.println(observation_seq_vector);
								System.out.println("Size of sequence vector : "+observation_seq_vector.size());	
							}
							else
								System.out.println("collecting failed! try again");
						}
						else if(arg0.getKeyCode()==KeyEvent.VK_4)
						{		
							System.out.println(c.frame().hands().get(0).translation(c.frame(10)).normalized());			
						}
					}
				});
		setVisible(true);
		setSize(100,100);
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		test a=new test();
	
		
	}

}
