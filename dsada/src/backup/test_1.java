package backup;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextPane;

import com.leapmotion.leap.Controller;

import javax.swing.JLabel;
import java.util.*;

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

public class test_1 extends JPanel {
	
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

	ImageIcon asd = new ImageIcon("C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\basicicon\\1.png");
	JLabel slide_1= new JLabel(image_basic[2]);
	JLabel slide_2= new JLabel(asd);

	SignListner leaplisten= new SignListner();
	Controller leapcont = new Controller();
	private String what_category="";  // identify category
	public String entered=""; // it is used for stop at input
	public int leap=2;

	public test_1(mainframe f) {
		
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
		
		panel.add(slide_1);
		panel.add(slide_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(911, 52, 73, 150);
		add(panel_1);
		panel_1.setLayout(new GridLayout(3, 0, 0, 0));
		
		 ImageIcon home = new ImageIcon("C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\home.png");
		JButton btnMain = new JButton(home);
		panel_1.add(btnMain);
		btnMain.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent evt) {
				make_sel_category();
			}
		});
		
		 ImageIcon restart = new ImageIcon("C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\restart.png");
		JButton btnNewButton = new JButton(restart);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_1.add(btnNewButton);
		
		 ImageIcon change_cate = new ImageIcon("C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\change_cate.png");
		JButton btnChangeDifficulty = new JButton(change_cate);
		panel_1.add(btnChangeDifficulty);
	
		for(int ii=1;ii<41;ii++){
			String tempname = Integer.toString(ii);
			image_basic[ii-1]=new ImageIcon("C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\basicicon\\2.png");
		}

	}

	
	
	

		   int correct=0, number, i =0;
	       String VowelQuestion,VowelAnswer,ConsonantQuestion,ConsonantAnswer,
	             ConsonantVowelQuestion,ConsonantVowelAnswer;
	       List Vowel_Question = new ArrayList();
	       List Vowel_Answer = new ArrayList();
	       List Consonant_Question = new ArrayList();
	       List Consonant_Answer = new ArrayList();
	       List Consonant_Vowel_Question = new ArrayList();
	       List Consonant_Vowel_Answer = new ArrayList();
	       String path = test_1.class.getResource("").getPath();
	           
	      public void start_prac_basic(){  
	    	  VowelList();  //assigns name of portion of program to build the collection of questions and answers 
	           ConsonantList();
	           Consonant_VowelList();
	           Basic_askQuestion();//assigns name of portion of program to ask the questions 
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
                 Consonant_Vowel_Question.add(i,Consonant_Question.get(i));
                  Consonant_Vowel_Answer .add(i,Consonant_Answer.get(i));

              }
           }
           Collections.shuffle(Consonant_Vowel_Question );  //shuffles the list 
           Collections.shuffle(Consonant_Vowel_Answer );  //shuffles the list 
        }
    
       public void Basic_askQuestion() //start of askQuestion 
       { //여기서 손 그림이 나오고 

           if(what_category=="Vowel"){// 이분은 버튼으로 구현해야됨 
           for (number = 0; number < 19; number++){  //start of counter for loop (문제 숫자 조정)
              
        	   while(true)
    		   {
    			   try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    			   
    			   String enter = Integer.toString(leaplisten.current_state);
    			   
    			   //if (entered==Vowel_Answer.get(number).toString()){
    			   if(leap==2){
    			       System.out.println("core1111");
    				   number++;
    				   break;
                 }  //end of if 
    		   }
               }
           }  //end of counter for loop 
           else if(what_category=="Consonant"){// 이분은 버튼으로 구현해야됨 
               for (number = 0; number < 21; number++){  //start of counter for loop (문제 숫자 조정)
                   
            	   while(true)
        		   {
        			   try {
    					Thread.sleep(1000);
    				} catch (InterruptedException e) {
    					e.printStackTrace();
    				}
        			   String enter = Integer.toString(leaplisten.current_state);
        			   //if (entered==Consonant_Answer.get(number).toString()){      
        			   if(leap==2){
        			       System.out.println("core1111");
        				   number++;
        				   break;
                     }  //end of if 
        		   }
                   }
               }
              else if(what_category=="both"){// 이분은 버튼으로 구현해야됨 
                  for (number = 0; number < 40; number++){  //start of counter for loop (문제 숫자 조정)
                      
               	   while(true)
           		   {
           			   try {
       					Thread.sleep(1000);
       				} catch (InterruptedException e) {
       					e.printStackTrace();
       				}
           			   String enter = Integer.toString(leaplisten.current_state);
           			   //if (entered==Consonant_Vowel_Answer.get(number).toString()){ 	      
           			   if(leap==2){
           			       System.out.println("core1111");
           				   number++;
           				   break;
                        }  //end of if 
           		   }
                      }
                  }

           System.out.println("*******************");  //prints footer top border 
           System.out.printf(" Your score is %d/%d%n", correct, number);  //prints results 
           System.out.println("*******************");  //prints footer bottom border 
   }
	   
       public void make_sel_category(){
	    		 JFrame mf = new JFrame();
				 mf.setSize(240,150);
				 JPanel select_category = new JPanel();
				 select_category.setLayout(new GridLayout(1, 3, 0, 0));
				 
				 JButton sel_Vowel= new JButton("Vowel");
				 sel_Vowel.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent e){
						what_category="Vowel";
		        		   textField_3.setText("*** Correct! ***");
						 mf.setVisible(false);
						 start_prac_basic();
					 }
				 });
				 
				 JButton sel_Consonant  = new JButton("Consonant");
				 sel_Consonant.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent e){
						 what_category=sel_Consonant.getName();
						 mf.setVisible(false);
						 start_prac_basic();
					 }
				 });
				 JButton sel_Both = new JButton("Both");
				 sel_Both.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent e){
						 what_category=sel_Both.getName();
						 mf.setVisible(false);
						 start_prac_basic();
					 }
				 });
				 select_category.add(sel_Vowel);
				 select_category.add(sel_Consonant);
				 select_category.add(sel_Both);
				 mf.getContentPane().add(select_category);
				 mf.setVisible(true);
			}


}