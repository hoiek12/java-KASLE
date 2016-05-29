package dsada;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Color;

public class mainframe extends JFrame {

	private JPanel contentPane;
	private CardLayout cards = new CardLayout();
    public static boolean is_logined = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainframe frame = new mainframe();
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public mainframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 625);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(cards);
		mainpage mainpage_ = new mainpage(this);
		mainpage_.setBackground(Color.BLACK);
		contentPane.add(mainpage_,"main");
		contentPane.add(new words_prac(this),"words_prac");
		contentPane.add(new word_quiz_easy(this),"wordquiz");
		contentPane.add(new basic_prac(this),"basic_prac");
	setVisible(true);
	}
	
	public void changepaneltowords_prac(){
		cards.show(contentPane,"words_prac");
	}
	public void changepaneltoquiz(){
		cards.show(contentPane,"wordquiz");
	}
	public void gotomainpage(){
		cards.show(contentPane,"main");
	}
	public void gotobasic_prac(){
		cards.show(contentPane,"basic_prac");
	}
}
