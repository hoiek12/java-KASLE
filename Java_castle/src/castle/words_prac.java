package dsada;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import com.leapmotion.leap.Controller;

import test.SimpleScene;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class words_prac extends JPanel {
	private mainframe F;

	public words_prac(mainframe f) {
		setBackground(Color.BLACK);
		F = f;
		setLayout(null);
		setBounds(100, 100, 1008, 592);

		JPanel panel = new JPanel();
		panel.setBounds(32, 48, 248, 228);
		add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(330, 10, 522, 566);
		add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(32, 318, 248, 228);
		add(panel_2);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(886, 10, 73, 150);
		add(panel_3);
		panel_3.setLayout(new GridLayout(3, 1, 0, 0));

		JButton btn_main = new JButton("main");
		btn_main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				F.gotomainpage();
			}
		});
		panel_3.add(btn_main);

		JButton btn_start = new JButton("start");
		btn_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				start_words_prac();
			}
		});
		panel_3.add(btn_start);

		JButton btn_stop = new JButton("stop");
		btn_stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				task_cancel = true;
			}
		});
		panel_3.add(btn_stop);
		icon_label.setBounds(32, 34, 248, 228);
		add(icon_label);

		for (int j = 1; j < 7; j++) {
			String tempname = Integer.toString(j);
			word_icon[j] = new ImageIcon(
					"C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\word_pro\\"
							+ tempname + ".png");
		}
		Controller c = new Controller();
		c.addListener(leaplisten);
	}
	// end of constructor

	ImageIcon[] word_icon = new ImageIcon[7];
	JLabel icon_label = new JLabel();
	Words_askQuestion task = new Words_askQuestion();
	SimpleScene s = new SimpleScene(1);
	SignListner leaplisten = new SignListner();
	int leap_entered;
	boolean task_cancel = false;

	int correct = 0, number, i;
	String WordPractice;
	List<String> Word_Practice = new ArrayList<String>();
	String path = words_prac.class.getResource("").getPath();

	public void start_words_prac() {
		WordPractice_BankList(); // assigns name of portion of program to build
		Words_askQuestion task = new Words_askQuestion();
		task.execute();// assigns name of portion of program to ask the
	}

	public void WordPractice_BankList() {
		try {
			BufferedReader WordPracticeReader = new BufferedReader(new FileReader(path + "WordPractice"));
			while ((WordPractice = WordPracticeReader.readLine()) != null) {
				{
					Word_Practice.add(new String(WordPractice));
					i++;
				}
			}

			Word_Practice.add("의");
		} catch (FileNotFoundException e) {// 파일이 예외처리
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.shuffle(Word_Practice);
	}

	class Words_askQuestion extends SwingWorker<Void, Integer> {
		public Void doInBackground() {
			for (number = 0; number < 5; number++) { // start of counter for
														// loop (문제 숫자 조정)
				while (true) {
					if (task_cancel == true) {
						cancel(true);
						task_cancel = false;
						return null;
					}
					int enter = leaplisten.current_state;
					System.out.println(enter);
					leap_entered = Integer.parseInt(Word_Practice.get(number).toString());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					publish(leap_entered);
					int leap=2;
					if(leap==2){
					//if (enter == leap_entered) {
						System.out.println("*** Correct! ***");
						correct = correct + 1;// counts number of correct
						break;
					} // end of if
				}
			} // end of counter for loop
				System.out.printf(" Your score is %d/%d%n", correct, number); // prints
			return null;
		}

		protected void process(List<Integer> a) {
			int icon_index;
			icon_index = a.get(0);
			System.out.println(icon_index);
			icon_label.setIcon(word_icon[icon_index]);
		}
	}
}
