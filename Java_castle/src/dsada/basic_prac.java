package dsada;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JTextPane;
import javax.swing.SwingWorker;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;
import com.leapmotion.leap.Controller;

import test.SimpleScene;

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

	private mainframe F;

	JPanel panel = new JPanel();

	JLabel[] prac_slide = new JLabel[5];
	ImageIcon[] image_basic = new ImageIcon[43];
	ImageIcon[] image_basic_correct = new ImageIcon[41];
	JButton btn_restart;
	JButton btnMain;
	JButton btnChangeDifficulty;

	ImageIcon empty_icon = new ImageIcon(
			"C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\basicicon\\0.png");
	ImageIcon empty_icon_black = new ImageIcon(
			"C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\basicicon\\00.png");
	SimpleScene s = new SimpleScene(2);
	SignListner leaplisten = new SignListner();

	private String what_category = ""; // identify category
	public String entered = ""; // it is used for stop at input
	public int leap = 2;
	static boolean task_cancel,shuffle_one=false;
	int send, send_1, send_2, both_count;
	int temp_num_2, temp_num = 0;
	// int enter;

	public basic_prac(mainframe f) {
		task_cancel=false;
		setBackground(Color.BLACK);
		F=f;
		setBounds(100, 100, 1008, 568);
		setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 222, 619, 325);
		add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(652, 222, 332, 318);
		add(panel_3);
		panel_3.setLayout(null);
		panel.setBackground(Color.BLACK);
		
		panel.setBounds(59, 52, 840, 153);
		add(panel);
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		
		for(int j=0;j<5;j++){
			prac_slide[j]=new JLabel("");
			prac_slide[j].setIcon(empty_icon_black);
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
			image_basic[ii]=new ImageIcon("C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\basicicon\\"+tempname+".png");
			image_basic_correct[ii]=new ImageIcon("C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\basicicon\\"+tempname+"_1.png");
		}
		image_basic[41]=new ImageIcon("C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\basicicon\\0.png");
		image_basic[42]=new ImageIcon("C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\basicicon\\0.png");
				
		panel_2.setBackground(Color.BLACK);
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		GLCanvas canvas = new GLCanvas(caps);
		canvas.setFocusable(true);
		canvas.requestFocus();
		canvas.setSize(444,268);
		canvas.addGLEventListener(s);
		
		Animator animator = new Animator(canvas);
		animator.start();
		//panel_2.add(canvas);
		
		Controller c=new Controller();
		c.addListener(leaplisten);
	}
	// end of constructor

	int correct = 0, number = 0, i = 0;
	String BasicPractice, BasicPracticeShuffle;
	ArrayList<String> Basic_Practice = new ArrayList<String>();
	ArrayList<String> Basic_Practice_Shuffle = new ArrayList<String>();
	String path = basic_prac.class.getResource("").getPath();
	// make btn icon and btn
	ImageIcon cate_icon_v = new ImageIcon(
			"C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\vowel.png");
	ImageIcon cate_icon_v1 = new ImageIcon(
			"C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\vowel_1.png");
	ImageIcon cate_icon_c = new ImageIcon(
			"C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\conso.png");
	ImageIcon cate_icon_c1 = new ImageIcon(
			"C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\conso_1.png");
	ImageIcon cate_icon_b = new ImageIcon(
			"C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\both.png");
	ImageIcon cate_icon_b1 = new ImageIcon(
			"C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\both_1.png");

	public void start_prac_basic() {
		Consonant_VowelList();
		final Basic_askQuestion task = new Basic_askQuestion();
		task.execute();// assigns name of portion of program to ask the
						// questions
	}

	public void Consonant_VowelList() {
		try {
			Basic_Practice_Shuffle.clear();
			
			BufferedReader Basic_Practice_File = new BufferedReader(new FileReader(path + "BasicPractice"));
			BufferedReader Basic_Practice_Shuffle_File = new BufferedReader(new FileReader(path + "BasicPractice"));
			while ((BasicPractice = Basic_Practice_File.readLine()) != null) {
				{
					Basic_Practice.add(new String(BasicPractice));
					Basic_Practice_Shuffle.add(new String(BasicPractice));
					i++;
				}
			}
			Basic_Practice.add("40");
			Basic_Practice_Shuffle.add("40");
		} catch (FileNotFoundException e) {// 파일이 예외처리
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.shuffle(Basic_Practice_Shuffle); // shuffles the list
		Basic_Practice_Shuffle.add("1");
		Basic_Practice_Shuffle.add("12");
		shuffle_one=true;
		}

	public void make_sel_category() {
		JFrame mf = new JFrame();
		mf.setSize(460, 100);
		mf.move(300, 300);
		JPanel select_category = new JPanel();
		select_category.setSize(450, 90);
		select_category.setLayout(new GridLayout(1, 3, 0, 0));
		number = 0;

		JButton sel_Vowel = new JButton(cate_icon_v);
		sel_Vowel.setBorderPainted(false); // 테두리 없애기
		sel_Vowel.setMargin(new Insets(0, 0, 0, 0)); // 여백없애기
		sel_Vowel.setContentAreaFilled(false); // 버튼 바탕 없애기
		sel_Vowel.setSize(new Dimension(cate_icon_v.getIconWidth(), cate_icon_v.getIconHeight())); // 크기
																									// 맞추기
		sel_Vowel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				what_category = "Vowel";
				mf.setVisible(false);
				start_prac_basic();
			}
		});
		sel_Vowel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				sel_Vowel.setIcon(cate_icon_v1);
			}

			public void mouseExited(MouseEvent evt) {
				sel_Vowel.setIcon(cate_icon_v);
			}
		});

		JButton sel_Consonant = new JButton(cate_icon_c);
		sel_Consonant.setBorderPainted(false); // 테두리 없애기
		sel_Consonant.setMargin(new Insets(0, 0, 0, 0)); // 여백없애기
		sel_Consonant.setContentAreaFilled(false); // 버튼 바탕 없애기
		sel_Consonant.setSize(new Dimension(cate_icon_c.getIconWidth(), cate_icon_c.getIconHeight())); // 크기
																										// 맞추기
		sel_Consonant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				what_category = "Consonant";
				mf.setVisible(false);
				start_prac_basic();
			}
		});
		sel_Consonant.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				sel_Consonant.setIcon(cate_icon_c1);
			}

			public void mouseExited(MouseEvent evt) {
				sel_Consonant.setIcon(cate_icon_c);
			}
		});
		JButton sel_Both = new JButton(cate_icon_b);
		sel_Both.setBorderPainted(false); // 테두리 없애기
		sel_Both.setMargin(new Insets(0, 0, 0, 0)); // 여백없애기
		sel_Both.setContentAreaFilled(false); // 버튼 바탕 없애기
		sel_Both.setSize(new Dimension(cate_icon_v.getIconWidth(), cate_icon_v.getIconHeight())); // 크기
																									// 맞추기
		sel_Both.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				what_category = "Both";
				mf.setVisible(false);
				start_prac_basic();
			}
		});
		sel_Both.addMouseListener(new MouseAdapter() {
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

	class Basic_askQuestion extends SwingWorker<Void, Integer> {
		public Void doInBackground() {
			correct=0;
			if (what_category == "Vowel") {// 이분은 버튼으로 구현해야됨
				for (number = 1; number < 20; number++) { // start of counter
					publish(number);
					while (true) {
						if (task_cancel == true) {
							cancel(true);
							task_cancel = false;
							return null;
						}
						try {
							Thread.sleep(1500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						int enter = leaplisten.current_state;
						System.out.println(F.current_ID);
						System.out.println(enter);
						send = Integer.parseInt(Basic_Practice.get(number).toString());
						// if (enter==send){
						if (leap == 2) {
							System.out.println("core111588");
							correct++;
							break;
						} // end of if
					}
				}
			} else if (what_category == "Consonant") {// 이분은 버튼으로 구현해야됨
				for (number = 20; number < 41; number++) { // start of counter
					publish(number);
					while (true) {
						if (task_cancel == true) {
							cancel(true);
							task_cancel = false;
							return null;
							}
						try {
							Thread.sleep(1500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						// String enter =
						// Integer.toString(leaplisten.current_state);
						// if (entered==Basic_Practice.get(number).toString()){
						int enter = leaplisten.current_state;
						System.out.println(enter);
						send = Integer.parseInt(Basic_Practice.get(number).toString());
						if (leap == 2) {
							// if (enter==send){
							System.out.println("core111588");
							break;
						} // end of if
					}
				}
			} else if (what_category == "Both") {// 이분은 버튼으로 구현해야됨
				both_count = 0;
				for (number = 0; number < 41; number++) { // start of counter
					while (true) {
						if (task_cancel == true) {
							cancel(true);
							task_cancel = false;
							return null;
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						// String enter =
						// Integer.toString(leaplisten.current_state);
						// if
						// (entered==Basic_Practice_Shuffle.get(number).toString()){
						int enter = leaplisten.current_state;
						System.out.println(enter);
						send = Integer.parseInt(Basic_Practice_Shuffle.get(number).toString());
						send_1 = Integer.parseInt(Basic_Practice_Shuffle.get(number + 1).toString());
						send_2 = Integer.parseInt(Basic_Practice_Shuffle.get(number + 2).toString());
						System.out.println(number);
						if (what_category == "Both") {
							publish(send, send_1, send_2);
						} else
							publish(send);
						if (leap == 2) {
							System.out.println("core111588");
							break;
						} // end of if
					}
				}
			}
				System.out.printf(" Your score is %d/%d%n", correct, number); // prints
				if(F.is_logined = true){
					F.statics(correct,F.current_ID);
				}
			return null;
		}
		

		protected void process(List<Integer> a) {
			int number_0 = a.get(0);
			if (what_category == "Both") {
				int number_1 = a.get(1);
				int number_2 = a.get(2);
				if (both_count == 0) {
					prac_slide[2].setIcon(image_basic_correct[number_0]);
					prac_slide[3].setIcon(image_basic[number_1]);
					prac_slide[4].setIcon(image_basic[number_2]);
					temp_num = number_0;
					both_count++;
				} else if (both_count == 1) {
					prac_slide[1].setIcon(image_basic[temp_num]);
					prac_slide[2].setIcon(image_basic_correct[number_0]);
					prac_slide[3].setIcon(image_basic[number_1]);
					prac_slide[4].setIcon(image_basic[number_2]);
					temp_num_2 = number_0;
					both_count++;
				}
				else if (both_count == 39) {
					prac_slide[0].setIcon(image_basic[temp_num]);
					prac_slide[1].setIcon(image_basic[temp_num_2]);
					prac_slide[2].setIcon(image_basic_correct[number_0]);
					prac_slide[3].setIcon(image_basic[number_1]);
					prac_slide[4].setIcon(empty_icon);
					System.out.println("d second last");
					temp_num = temp_num_2;
					temp_num_2 = number_0;			
					both_count++;
				} 
				else if (both_count == 40) {
					System.out.println("here2");
					prac_slide[0].setIcon(image_basic[temp_num]);
					prac_slide[1].setIcon(image_basic[temp_num_2]);
					prac_slide[2].setIcon(image_basic_correct[number_0]);
					prac_slide[3].setIcon(empty_icon);
					prac_slide[4].setIcon(empty_icon);
					both_count++;
				}
				else {
					prac_slide[0].setIcon(image_basic[temp_num]);
					prac_slide[1].setIcon(image_basic[temp_num_2]);
					prac_slide[2].setIcon(image_basic_correct[number_0]);
					prac_slide[3].setIcon(image_basic[number_1]);
					prac_slide[4].setIcon(image_basic[number_2]);
					System.out.println(both_count);
					temp_num = temp_num_2;
					temp_num_2 = number_0;
					both_count++;
				}
			}
			else if (number_0 == 1 || number_0 == 20) {
				prac_slide[2].setIcon(empty_icon);
				prac_slide[2].setIcon(empty_icon);
				prac_slide[2].setIcon(image_basic_correct[number]);
				prac_slide[3].setIcon(image_basic[number + 1]);
				prac_slide[4].setIcon(image_basic[number + 2]);
			} else if (number_0 ==2 || number_0 == 21) {
				prac_slide[2].setIcon(empty_icon);
				prac_slide[1].setIcon(image_basic[number - 1]);
				prac_slide[2].setIcon(image_basic_correct[number]);
				prac_slide[3].setIcon(image_basic[number + 1]);
				prac_slide[4].setIcon(image_basic[number + 2]);
			} else {
				prac_slide[0].setIcon(image_basic[number - 2]);
				prac_slide[1].setIcon(image_basic[number - 1]);
				prac_slide[2].setIcon(image_basic_correct[number]);
				prac_slide[3].setIcon(image_basic[number + 1]);
				prac_slide[4].setIcon(image_basic[number + 2]);
			}
		}
	}
}