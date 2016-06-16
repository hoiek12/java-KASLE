package dsada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;

import test.SimpleScene;
import test.mycanvas;

import java.awt.CardLayout;
import java.awt.Color;

public class mainframe extends JFrame {

	public JPanel contentPane;
	private CardLayout cards = new CardLayout();
	
	public static login_class log = new login_class();
	public static boolean is_logined = false;
	public static String current_ID;
	GLProfile glp;
	GLCapabilities caps;
	
	
	public static void main(String[] args) {
					mainframe frame = new mainframe();
					frame.setResizable(false);
					frame.setVisible(true);
	}

	public mainframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 625);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(cards);
		setContentPane(contentPane);
		
		mainpage mainpage_ = new mainpage(this);
		mainpage_.setBackground(Color.BLACK);
		contentPane.add(mainpage_, "main");
		
	    glp = GLProfile.getDefault();
        caps = new GLCapabilities(glp);

		setVisible(true);
	}
	
	public void goto_tutorial() {
		game p=new game(this);
		p.add(new mycanvas(new SimpleScene(3),caps,p));
		contentPane.add(p, "wordquiz");
		cards.show(contentPane, "wordquiz");
	}
	
	public void goto_basic_prac() {
		game p=new game(this);
		p.add(new mycanvas(new SimpleScene(1),caps,p));
		contentPane.add(p, "basic_prac");
		cards.show(contentPane, "basic_prac");
		if(is_logined==true){
			int sco = statics.get_num_basic();
			statics.insert_basic_prac(sco+1);
		}
	}
	
	public void goto_num_prac(){
		game p=new game(this);
		p.add(new mycanvas(new SimpleScene(2),caps,p));
		contentPane.add(p, "wordquiz");
		cards.show(contentPane, "wordquiz");
		if(is_logined==true){
			int sco = statics.get_num_num();
			statics.insert_num_prac(sco+1);
		}
	}
	
	public void goto_voca_prac() {
		game p=new game(this);
		p.add(new mycanvas(new SimpleScene(4),caps,p));
		contentPane.add(p, "words_prac");
		cards.show(contentPane, "words_prac");
		if(is_logined==true){
			int sco = statics.get_num_word();
			statics.insert_word_prac(sco+1);
		}
	}
	
	public void goto_raingame(){
		game p=new game(this);
		p.add(new mycanvas(new SimpleScene(5),caps,p));
		contentPane.add(p, "wordquiz");
		cards.show(contentPane, "wordquiz");
		if(is_logined==true){
			int sco = statics.get_score();
			statics.insert_score(sco);
		}
	}
	
	public void goto_statics(){
		if(is_logined==true)
		{	
			contentPane.add(new statics(this), "statics");
			cards.show(contentPane,"statics");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "it need login. after login, try again!");
		}
	}
	
	public void gotomainpage() {
		cards.show(contentPane, "main");
	}
}
