package backup;

import javax.swing.JPanel;
import javax.swing.JTextField;
// edit

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JTextPane;
import javax.swing.SwingWorker;

import com.leapmotion.leap.Controller;

import javax.swing.JLabel;
import java.util.*;
import java.util.concurrent.ExecutionException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class basic_prac extends JPanel {
	
	public int cproblem=1;
	
	//public void paint(Graphics  g)
	//{
		//Image image = Toolkit.getDefaultToolkit().getImage("C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\basicicon\\"+cproblem+".png");
		//g.setColor(Color.black);
		//g.drawImage(image, 100, 100, null);
	//}
	
	private JTextField textField_2;
	private JTextField textField_3;
    
	private mainframe F;
	
	JPanel panel = new JPanel();

	JLabel[] prac_slide = new JLabel[5];
	ImageIcon[] image_basic = new ImageIcon[40];
	ImageIcon[] image_basic_correct = new ImageIcon[40];
	JButton btn_restart;
	JButton btnMain;
	JButton btnChangeDifficulty;

	ImageIcon empty_icon = new ImageIcon("C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\basicicon\\0.png");

	SignListner leaplisten= new SignListner();
	Controller leapcont = new Controller();
	private String what_category="";  // identify category
	public String entered=""; // it is used for stop at input
	public int leap=2;
	static boolean task_cancel;

	public basic_prac(mainframe f) {
		task_cancel=false;
		setBackground(Color.BLACK);
		F=f;
		setBounds(100, 100, 1008, 568);
		setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(34, 233, 444, 268);
		add(panel_2);
		panel_2.setLayout(null);
		
		textField_2 = new JTextField();
		textField_2.setBounds(0, 0, 444, 268);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(540, 233, 444, 268);
		add(panel_3);
		panel_3.setLayout(null);
		
		textField_3 = new JTextField();
		textField_3.setBounds(0, 0, 444, 268);
		panel_3.add(textField_3);
		textField_3.setColumns(10);
		
		panel.setBounds(59, 52, 840, 153);
		add(panel);
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		
		for(int j=0;j<5;j++){
			prac_slide[j]=new JLabel("");
			panel.add(prac_slide[j]);
		}

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(911, 52, 73, 150);
		add(panel_1);
		panel_1.setLayout(new GridLayout(3, 0, 0, 0));
		
		ImageIcon home = new ImageIcon("C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\home.png");
		JButton btnMain = new JButton(home);
		panel_1.add(btnMain);
		
		ImageIcon restart = new ImageIcon("C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\restart.png");
		JButton btn_restart = new JButton(restart);
		panel_1.add(btn_restart);
		
		ImageIcon change_cate = new ImageIcon("C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\change_cate.png");
		JButton btnChangeDifficulty = new JButton(change_cate);
		panel_1.add(btnChangeDifficulty);
		
		btnMain.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					F.gotomainpage();
				}
			});
		btn_restart.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent arg0) {
  				task_cancel=true;
  				}
  		});
		btnChangeDifficulty.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					make_sel_category();
				}
			});
	
		for(int ii=1;ii<41;ii++){
			String tempname = Integer.toString(ii);
			image_basic[ii-1]=new ImageIcon("C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\basicicon\\"+tempname+".png");
			image_basic_correct[ii-1]=new ImageIcon("C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\basicicon\\"+tempname+"_1.png");
		}
	}
//end of constructor
	
		   int correct=0, number=0, i =0;
	       String VowelQuestion,VowelAnswer,ConsonantQuestion,ConsonantAnswer,
	             ConsonantVowelQuestion,ConsonantVowelAnswer;
	       List Vowel_Question = new ArrayList();
	       List Vowel_Answer = new ArrayList();
	       List Consonant_Question = new ArrayList();
	       List Consonant_Answer = new ArrayList();
	       List Consonant_Vowel_Question = new ArrayList();
	       List Consonant_Vowel_Answer = new ArrayList();
	       String path = basic_prac.class.getResource("").getPath();
	       
	    // make btn icon and btn
			ImageIcon cate_icon_v = new ImageIcon("C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\vowel.png");
			ImageIcon cate_icon_v1 = new ImageIcon("C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\vowel_1.png");
			ImageIcon cate_icon_c = new ImageIcon("C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\conso.png");
			ImageIcon cate_icon_c1 = new ImageIcon("C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\conso_1.png");
			ImageIcon cate_icon_b = new ImageIcon("C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\both.png");
			ImageIcon cate_icon_b1 = new ImageIcon("C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\both_1.png");

			
	      public void start_prac_basic(){  
	    	   VowelList();  //assigns name of portion of program to build the collection of questions and answers 
	           ConsonantList();
	           Consonant_VowelList();
	           final Basic_askQuestion task = new Basic_askQuestion();
	           task.execute();//assigns name of portion of program to ask the questions 
	      }
	
	       public void VowelList() //start of bankList 
	       { 
	    	   try {
	    		   BufferedReader Vowel_Question_file = new BufferedReader(new FileReader(path + "VowelQuestion"));
	    		   BufferedReader Vowel_Answer_file = new BufferedReader(new FileReader(path + "VowelAnswer"));
	    		   while((VowelQuestion = Vowel_Question_file.readLine()) != null){
	    			   {
	    				   Vowel_Question.add(new String(VowelQuestion));
	    				   i++;
	    			   }
	    		   } 
	    		   i = 0;
	    		   while((VowelAnswer = Vowel_Answer_file.readLine()) != null){
	    			   {
	    				   Vowel_Answer.add(new String(VowelAnswer));
	    				   i++;
	    			   }
	    		   } 
	    		   Vowel_Question.add("의");
	    		   Vowel_Answer.add("의");
	    		   
	    	   } catch (FileNotFoundException e) {//파일이 예외처리
	    		   e.printStackTrace();
	    	   } 
	    	   catch (IOException e) {
	    		   // TODO Auto-generated catch block
	    		   e.printStackTrace();
	    	   }
	       }  
      //end of bankList 
       public void ConsonantList(){
          try {
               BufferedReader Consonant_Question_file = new BufferedReader(new FileReader(path + "ConsonantQuestion"));
               BufferedReader Consonant_Answer_file = new BufferedReader(new FileReader(path + "ConsonantAnswer"));
               while((ConsonantQuestion = Consonant_Question_file.readLine()) != null){
                  {
                     Consonant_Question.add(new String(ConsonantQuestion));
                      i++;
                  }
               } 
               i = 0;
               while((ConsonantAnswer = Consonant_Answer_file.readLine()) != null){
                  {
                     Consonant_Answer.add(new String(ConsonantAnswer));
                        i++;
                  }
              } 
               Consonant_Question.add("의");
               Consonant_Answer.add("의");

            } catch (FileNotFoundException e) {//파일이 예외처리
               e.printStackTrace();
               } 
               catch (IOException e) {
               // TODO Auto-generated catch block
                  e.printStackTrace();
               }
       }  //end of bankList    

       public void Consonant_VowelList(){
    	   
           for(int i = 0; i < 40; i++){
              if(i < 19){
                  Consonant_Vowel_Question.add(i,Vowel_Question.get(i));
                  Consonant_Vowel_Answer .add(i,Vowel_Answer.get(i));
              }
              else{
                  Consonant_Vowel_Question.add(i,Consonant_Question.get(i-19));
                  Consonant_Vowel_Answer .add(i,Consonant_Answer.get(i-19));

              }
           }
           Collections.shuffle(Consonant_Vowel_Question );  //shuffles the list 
           Collections.shuffle(Consonant_Vowel_Answer );  //shuffles the list 
        }
       
       public void make_sel_category(){
	    		 JFrame mf = new JFrame();
				 mf.setSize(460,100);
				 mf.move(300,300);
				 JPanel select_category = new JPanel();
				 select_category.setSize(450,90);
				 select_category.setLayout(new GridLayout(1, 3, 0, 0));
				 number=0;
				 
				 JButton sel_Vowel= new JButton(cate_icon_v);
				 sel_Vowel.setBorderPainted(false); // 테두리 없애기
				 sel_Vowel.setMargin(new Insets(0,0,0,0)); // 여백없애기
				 sel_Vowel.setContentAreaFilled(false); // 버튼 바탕 없애기
				 sel_Vowel.setSize(new Dimension(cate_icon_v.getIconWidth(), cate_icon_v.getIconHeight())); //크기 맞추기
				 sel_Vowel.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent e){
						what_category="Vowel";
						 mf.setVisible(false);
						 start_prac_basic();
					 }
				 });
				 sel_Vowel.addMouseListener(new MouseAdapter(){
						public void mouseEntered(MouseEvent evt) {
							sel_Vowel.setIcon(cate_icon_v1);
						}
						public void mouseExited(MouseEvent evt) {
							sel_Vowel.setIcon(cate_icon_v);
						}
					});
				 
				 JButton sel_Consonant  = new JButton(cate_icon_c);
				 sel_Consonant.setBorderPainted(false); // 테두리 없애기
				 sel_Consonant.setMargin(new Insets(0,0,0,0)); // 여백없애기
				 sel_Consonant.setContentAreaFilled(false); // 버튼 바탕 없애기
				 sel_Consonant.setSize(new Dimension(cate_icon_c.getIconWidth(), cate_icon_c.getIconHeight())); //크기 맞추기
				 sel_Consonant.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent e){
						 what_category="Consonant";
						 mf.setVisible(false);
						 start_prac_basic();
					 }
				 });
				 sel_Consonant.addMouseListener(new MouseAdapter(){
						public void mouseEntered(MouseEvent evt) {
							sel_Consonant.setIcon(cate_icon_c1);
						}
						public void mouseExited(MouseEvent evt) {
							sel_Consonant.setIcon(cate_icon_c);
						}
					});
				 JButton sel_Both = new JButton(cate_icon_b);
				 sel_Both.setBorderPainted(false); // 테두리 없애기
				 sel_Both.setMargin(new Insets(0,0,0,0)); // 여백없애기
				 sel_Both.setContentAreaFilled(false); // 버튼 바탕 없애기
				 sel_Both.setSize(new Dimension(cate_icon_v.getIconWidth(), cate_icon_v.getIconHeight())); //크기 맞추기
				 sel_Both.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent e){
						 what_category="Both";
						 mf.setVisible(false);
						 start_prac_basic();
					 }
				 });
				 sel_Both.addMouseListener(new MouseAdapter(){
						public void mouseEntered(MouseEvent evt) {
							sel_Both.setIcon(cate_icon_b1);
						}
						public void mouseExited(MouseEvent evt) {
							sel_Both.setIcon(cate_icon_b);
						}
					});
				 
				 select_category.add(sel_Vowel);
				 select_category.add(sel_Consonant);
				 select_category.add(sel_Both);
				 mf.getContentPane().add(select_category);
				 mf.setVisible(true);
			}
       
       		class Basic_askQuestion extends SwingWorker<Void,Integer>{
    		   public Void doInBackground() {
    	    	   if(what_category=="Vowel"){// 이분은 버튼으로 구현해야됨 
    	               for (number = 0; number < 19; number++){  //start of counter for loop (문제 숫자 조정)
    	            	   if(task_cancel==true){
    	            		   cancel(true);
    	            		   number=0;
    	            		   task_cancel=false;
    	            		   break;
    	            	   }
    	            	   publish(number);		
    	            	   		while(true)
    	    	        		   {
    	    	        			   try {
    	    	    					Thread.sleep(1000);
    	    	    				} catch (InterruptedException e) {
    	    	    					e.printStackTrace();
    	    	    				}
    	    	        			   //String enter = Integer.toString(leaplisten.current_state);
    	    	        			   //if (entered==Vowel_Answer.get(number).toString()){
    	    	        			   if(leap==2){
    	    	        			       System.out.println("core111588");
    	    	        				   break;
    	    	                     }  //end of if 
    	    	        		   }
    	                   }
    	               } 
    	    	   else if(what_category=="Consonant"){// 이분은 버튼으로 구현해야됨 
    	               for (number = 19; number < 40; number++){  //start of counter for loop (문제 숫자 조정)
    	            	   if(task_cancel==true){
    	            		   cancel(true);
    	            		   number=0;
    	            		   task_cancel=false;
    	            		   break;
    	            	   }
    	            	   publish(number);		
    	            	   		while(true)
    	    	        		   {
    	    	        			   try {
    	    	    					Thread.sleep(1000);
    	    	    				} catch (InterruptedException e) {
    	    	    					e.printStackTrace();
    	    	    				}
    	    	        			   //String enter = Integer.toString(leaplisten.current_state);
    	    	        			   //if (entered==Vowel_Answer.get(number).toString()){
    	    	        			   if(leap==2){
    	    	        			       System.out.println("core111588");
    	    	        				   break;
    	    	                     }  //end of if 
    	    	        		   }
    	                   }
    	               }
    	          else if(what_category=="both"){// 이분은 버튼으로 구현해야됨 
    	                  for (number = 0; number < 40; number++){  //start of counter for loop (문제 숫자 조정)
    	                	  if(task_cancel==true){
       	            		   cancel(true);
       	            		   number=0;
       	            		   task_cancel=false;
       	            		   break;
       	            	   }
       	            	   publish(number);		
       	            	   		while(true)
       	    	        		   {
       	    	        			   try {
       	    	    					Thread.sleep(1000);
       	    	    				} catch (InterruptedException e) {
       	    	    					e.printStackTrace();
       	    	    				}
       	    	        			   //String enter = Integer.toString(leaplisten.current_state);
       	    	        			   //if (entered==Vowel_Answer.get(number).toString()){
       	    	        			   if(leap==2){
       	    	        			       System.out.println("core111588");
       	    	        				   break;
       	    	                     }  //end of if 
       	    	        		   }
    	                      }
    	                  }
    	           System.out.printf(" Your score is %d/%d%n", correct, number);  //prints results 
				return null;
    	   }
    		   protected void process(List<Integer> a) {
    			   number=a.get(0);
    			   System.out.printf(" Your score is sdadssa");
    		   			if(number==0){
    		   			   prac_slide[0].setIcon(empty_icon);
    		   			   prac_slide[1].setIcon(empty_icon);
 	            		   prac_slide[2].setIcon(image_basic_correct[0]);
 	            		   prac_slide[3].setIcon(image_basic[1]);
 	            		   prac_slide[4].setIcon(image_basic[2]);
 	            	   }
 	            	   else if(number==1){
 	            		   prac_slide[0].setIcon(empty_icon);
 	            		   prac_slide[1].setIcon(image_basic[0]);
 	            		   prac_slide[2].setIcon(image_basic_correct[1]);
 	            		   prac_slide[3].setIcon(image_basic[2]);
 	            		   prac_slide[4].setIcon(image_basic[3]);
 	            	   }
 	            	   else{
 	            		   prac_slide[0].setIcon(image_basic[number-2]);
 	            		   prac_slide[1].setIcon(image_basic[number-1]);
 	            		   prac_slide[2].setIcon(image_basic_correct[number]);
 	            		   prac_slide[3].setIcon(image_basic[number+1]);
 	            		   prac_slide[4].setIcon(image_basic[number+2]);

 	            	   }
    		   }
    		   }
    	   }
