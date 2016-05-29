package dsada_backup_second;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;

public class mainframe extends JFrame {

	private JPanel contentPane;
	private CardLayout cards = new CardLayout();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainframe frame = new mainframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public mainframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(cards);
		contentPane.add(new mainpage(this),"main");
		contentPane.add(new submain(this),"submain");
		contentPane.add(new quizpanel(this),"quizpanel");
	setVisible(true);
	}
	
	public void changepaneltosubmain(){
		cards.show(contentPane,"submain");
	}
	public void changepaneltoquiz(){
		cards.show(contentPane,"quizpanel");
	}

}
