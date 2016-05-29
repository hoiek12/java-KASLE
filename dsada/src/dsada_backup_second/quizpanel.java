package dsada_backup_second;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;  //adds Arrays utility from java library 
import java.util.List;  //adds List utility from java library 
import java.util.Scanner;  //adds Scanner utility from java library 
import java.util.Collections;  //adds Collections utility from java library  

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class quizpanel extends JPanel {
private mainframe F;
JTextPane output_text = new JTextPane();
private JTextField input_text;

	public quizpanel(mainframe f) {
		F=f;
		setLayout(null);
		Quiz testquiz = new Quiz();
		output_text.setBounds(42, 61, 332, 125);
		add(output_text);
		JButton start = new JButton("start");
		start.setBounds(94, 28, 97, 23);
		add(start);
		input_text = new JTextField();
		input_text.setBounds(42, 231, 332, 21);
		add(input_text);
		input_text.setColumns(10);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testquiz.startquiz();
			}
		});
	}
	public class Quiz //start Quiz class (아마 여기서 엄마 클래스를 상속받아야 될듯 술탱꺼)
	{ 
	    String question;  //initializes question string  
	    String answer;  //initializes answer string  
	    int correct=0, number;  //initializes scoring variables 
	    Quiz[] quizBank = new Quiz[10];  //initializes quizBank array  
	    Quiz[] quizBankMedium = new Quiz[10];    
	    List<Quiz> quizList = Arrays.asList(quizBank);  //sets the array as a list 
	    List<Quiz> quizListMedium = Arrays.asList(quizBankMedium);  //sets the array as a list 

	    
	    public void startquiz(){ //start of main { 
	        Quiz bank = new Quiz();  //creates new Quiz object 
	        bank.bankList();  //assigns name of portion of program to build the collection of questions and answers 
	        bank.bankListMedium();
	        bank.askQuestion();//assigns name of portion of program to ask the questions 
	    }  //end main 

	    
	    public void bankList() //start of bankList 
	    { 
	       for(int i = 0; i < 10; i++){
	          quizBank[i] = new Quiz();//오브젝트 10개 생성
	       }
	        quizBank[0].question = "배";  //Initialize object variables (그림을 쓰거나 글시)
	        quizBank[0].answer = "2";  //Initialize object variables (답을 받는 것은 립모션으 ㅣ값으로 )
	         
	        quizBank[1].question = "Area of triangle with base = 4 and height = 3";  //Initialize object variables 
	        quizBank[1].answer = "6";  //Initialize object variables     
	         
	        quizBank[2].question = "Area of square with side = 5";  //Initialize object variables 
	        quizBank[2].answer = "25";  //Initialize object variables     
	         
	        quizBank[3].question = "Square root of 144";  //Initialize object variables 
	        quizBank[3].answer = "12";  //Initialize object variables     
	         
	        quizBank[4].question = "No. of states in US";  //Initialize object variables 
	        quizBank[4].answer = "50";  //Initialize object variables     
	         
	        quizBank[5].question = "No. of continents in the world";  //Initialize object variables 
	        quizBank[5].answer = "7";  //Initialize object variables 
	         
	        quizBank[6].question = "In which year did man land on the moon";  //Initialize object variables 
	        quizBank[6].answer = "1969";  //Initialize object variables     
	         
	        quizBank[7].question = "How many colors in a rainbow";  //Initialize object variables 
	        quizBank[7].answer = "7";  //Initialize object variables     
	         
	        quizBank[8].question = "How many colors in the US flag";  //Initialize object variables 
	        quizBank[8].answer = "3";  //Initialize object variables     
	         
	        quizBank[9].question = "Square of 25";  //Initialize object variables 
	        quizBank[9].answer = "625";  //Initialize object variables     
	        Collections.shuffle(quizList);  //shuffles the list 
	    }  //end of bankList 
	    public void bankListMedium(){
	       
	       for(int i = 0; i < 10; i++){
	          quizBankMedium[i] = new Quiz();//오브젝트 10개 생성
	       }
	        quizBank[0].question = "";  //Initialize object variables (그림을 쓰거나 글시)
	        quizBankMedium[0].answer = "";  //Initialize object variables (답을 받는 것은 립모션으 ㅣ값으로 )
	         
	        quizBankMedium[1].question = "";  //Initialize object variables 
	        quizBankMedium[1].answer = "";  //Initialize object variables     
	         
	        quizBankMedium[2].question = "";  //Initialize object variables 
	        quizBankMedium[2].answer = "";  //Initialize object variables     
	         
	        quizBankMedium[3].question = "";  //Initialize object variables 
	        quizBankMedium[3].answer = "";  //Initialize object variables     
	         
	        quizBankMedium[4].question = "";  //Initialize object variables 
	        quizBankMedium[4].answer = "";  //Initialize object variables     
	         
	        quizBankMedium[5].question = "";  //Initialize object variables 
	        quizBankMedium[5].answer = "";  //Initialize object variables 
	         
	        quizBankMedium[6].question = "";  //Initialize object variables 
	        quizBankMedium[6].answer = "";  //Initialize object variables     
	         
	        quizBankMedium[7].question = "";  //Initialize object variables 
	        quizBankMedium[7].answer = "";  //Initialize object variables     
	         
	        quizBankMedium[8].question = "";  //Initialize object variables 
	        quizBankMedium[8].answer = "";  //Initialize object variables     
	         
	        quizBankMedium[9].question = "";  //Initialize object variables 
	        quizBankMedium[9].answer = "";  //Initialize object variables     
	        Collections.shuffle(quizList);  //shuffles the list 
	       
	    }
	    public void Correct(){
	       //여기다 맞는 걸 표시하는 그림을 넣을거임
	    }
	    public void InCorrect(){
	       //여기다 맞는 걸 표시하는 그림을 넣을거임
	    }
	     
	    public void askQuestion() //start of askQuestion 
	    { //여기서 손 그림이 나오고 
	        
	        	output_text.setText("********************************\n"
	        			+ "Welcome to my Quiz Application\n********************************\n"
	        			+ "Please select difficulty level: Easy Medium Hard");  //prints heading
	        	String input=input_text.getText();
	        	
	        	//action listener start
	        	input_text.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    		if(input_text.getText() != null)
	        	{
	        	String entered = input;  
	        		if(entered.equals("Easy")){// 이분은 버튼으로 구현해야됨 
	        			for (number=1; number<6; number++)  //start of counter for loop (문제 숫자 조정)
	        			{ 
	        				System.out.printf("%d. %s?%n", number, quizBank[number].question);  //prints question(이걸 그림으로 할지, 아니면 워드로 할지) 
	        				entered = input;  //read input 
	        				
	        				if (entered.compareTo(quizBank[number].answer)==0)  //checks the users input 
	        				{ 
	        					System.out.println("*** Correct! ***"); 
	        					Correct();//prints correct response 
	        					correct = correct + 1;  //counts number of correct answers 
	        				}  //end of if 
	        				else  //start of response for wrong answers 
	        					InCorrect();
	        				System.out.println("--- Incorrect! ---");  //print the incorrect response 
	        			}  //end of counter for loop 
	        		}	
	        		else if(entered.equals("Medium")){//이 부분도 버튼으로 구현해야됨
	        			for (number=1; number<6; number++)  //start of counter for loop (문제 숫자 조정)
	        			{ 
	        				System.out.printf("%d. %s?%n", number, quizBankMedium[number].question);  //prints question(이걸 그림으로 할지, 아니면 워드로 할지) 
	        				entered = input;  //read input 
	        				if (entered.compareTo(quizBankMedium[number].answer)==0)  //checks the users input 
	        				{ 
	        					Correct();
	        					System.out.println("*** Correct! ***");  //prints correct response 
	        					correct = correct + 1;  //counts number of correct answers 
	        				}  //end of if 
	        				else  //start of response for wrong answers 
	        					InCorrect();
	        				System.out.println("--- Incorrect! ---");  //print the incorrect response 
	        			}  //end of counter for loop 
	        			
	        		}
	        	} // end of if input not null
	        	else output_text.setText("you must write level");
	        
	    		output_text.setText("*******************\n"
	    				+ "Your score is "+correct+"/"+(number-1)
	    				+ "\n*******************");  //prints footer bottom border 
	    			}
	    		});
	    }  //end of askQuestion 
		}  //end public class  
} // end of panel
