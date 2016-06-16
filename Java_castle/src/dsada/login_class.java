package dsada;

import java.awt.Color;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class login_class {
	static ImageIcon logout_icon=new ImageIcon(
			".\\image\\logout.png");
	static Panel log_panel = new Panel();
	static JButton btn_login = new JButton(new ImageIcon(
			".\\image\\login.png"));
	static JButton btn_logout = new JButton(new ImageIcon(
			".\\image\\logout.png"));

	public login_class() {
		log_panel.setBackground(Color.BLACK);
		log_panel.setBounds(896, 10, 102, 32);
		log_panel.setLayout(null);

		btn_login.setFocusable(true);
		btn_login.setBackground(Color.BLACK);
		btn_login.setBounds(2, 0, 100, 30);

		btn_login.setBorderPainted(false); // 테두리 없애기
		btn_login.setMargin(new Insets(0, 0, 0, 0)); // 여백없애기
		btn_login.setContentAreaFilled(false); // 버튼 바탕 없애기
		log_panel.add(btn_login);
	}
}
