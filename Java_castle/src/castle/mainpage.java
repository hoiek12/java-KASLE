package dsada;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Canvas;

public class mainpage extends JPanel {
	private mainframe F;

	public mainpage(mainframe f) {
		setBackground(Color.BLACK);
		F = f;
		setLayout(null);
		setBounds(100, 100, 1008, 592);
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(12, 221, 247, 361);
		add(panel);
		panel.setLayout(new GridLayout(4, 0, 0, 15));
		
		// starting of main_image

		ImageIcon maininfoimage = new ImageIcon(".\\image\\main_image.png");
		JLabel infoimage = new JLabel(maininfoimage);
		infoimage.setBounds(271, 221, 466, 361);
		add(infoimage);

		// start of explainimage
		
		ImageIcon tutorial_explain = new ImageIcon(".\\image\\explain\\tutorial_explain.png");
		ImageIcon basic_explain = new ImageIcon(".\\image\\explain\\basic_explain.png");
		ImageIcon num_explain = new ImageIcon(".\\image\\explain\\num_explain.png");
		ImageIcon voca_explain = new ImageIcon(".\\image\\explain\\voca_explain.png");
		ImageIcon raingame_explain = new ImageIcon(".\\image\\explain\\raingame_explain.png");
		ImageIcon statistic_explain = new ImageIcon(".\\image\\explain\\statistic_explain.png");
		ImageIcon exit_explain = new ImageIcon(".\\image\\explain\\exit_explain.png");
		
		// end of explainimage
		
		// button sector start
		
		ImageIcon tutorial = new ImageIcon(".\\image\\tutorial.png");
		ImageIcon tutorial2 = new ImageIcon(".\\image\\tutorial2.png");
		JButton prac_tutorial_button = new JButton(tutorial);
		prac_tutorial_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				F.goto_tutorial();
			}
		});
		panel.add(prac_tutorial_button);
		prac_tutorial_button.setBorderPainted(false); // 테두리 없애기
		prac_tutorial_button.setMargin(new Insets(0, 0, 0, 0)); // 여백없애기
		prac_tutorial_button.setContentAreaFilled(false); // 버튼 바탕 없애기
		prac_tutorial_button.setSize(new Dimension(tutorial.getIconWidth(), tutorial.getIconHeight())); // 크기
		
		prac_tutorial_button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				prac_tutorial_button.setIcon(tutorial2);
				infoimage.setIcon(tutorial_explain);
			}

			public void mouseExited(MouseEvent evt) {
				prac_tutorial_button.setIcon(tutorial);
				infoimage.setIcon(maininfoimage);
			}
		});

		ImageIcon basic = new ImageIcon(".\\image\\basic.png");
		ImageIcon basic2 = new ImageIcon(".\\image\\basic2.png");
		JButton prac_basic_button = new JButton(basic);
		prac_basic_button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				prac_basic_button.setIcon(basic2);
				infoimage.setIcon(basic_explain);
			}

			public void mouseExited(MouseEvent evt) {
				prac_basic_button.setIcon(basic);
				infoimage.setIcon(maininfoimage);
			}
		});
		prac_basic_button.setBorderPainted(false); // 테두리 없애기
		prac_basic_button.setMargin(new Insets(0, 0, 0, 0)); // 여백없애기
		prac_basic_button.setContentAreaFilled(false); // 버튼 바탕 없애기
		prac_basic_button.setSize(new Dimension(basic.getIconWidth(), basic.getIconHeight())); // 크기맞추기
		panel.add(prac_basic_button);
		prac_basic_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				F.goto_basic_prac();
			}
		});
		
		ImageIcon num_prac = new ImageIcon(".\\image\\num.png");
		ImageIcon num_prac2 = new ImageIcon(".\\image\\num2.png");
		JButton prac_num_btn = new JButton(num_prac);
		panel.add(prac_num_btn);
		prac_num_btn.setBorderPainted(false); // 테두리 없애기
		prac_num_btn.setMargin(new Insets(0, 0, 0, 0)); // 여백없애기
		prac_num_btn.setContentAreaFilled(false); // 버튼 바탕 없애기
		prac_num_btn.setSize(new Dimension(num_prac.getIconWidth(), num_prac.getIconHeight())); // 크기
		prac_basic_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				F.goto_num_prac();
			}
		});
																										// 맞추기
		prac_num_btn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				prac_num_btn.setIcon(num_prac2);
				infoimage.setIcon(num_explain);
			}

			public void mouseExited(MouseEvent evt) {
				prac_num_btn.setIcon(num_prac);
				infoimage.setIcon(maininfoimage);
			}
		});


		// button listener end
		/*
		ImageIcon setting = new ImageIcon(
				"C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\setting.png");
		ImageIcon setting2 = new ImageIcon(
				"C:\\Users\\LeeJooHyun\\Desktop\\잡동사니\\University\\3학년 1학기\\Software Engineering\\project\\GUI\\setting2.png");
		JButton setting_button = new JButton(setting);
		setting_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		setting_button.setBorderPainted(false); // 테두리 없애기
		setting_button.setMargin(new Insets(0, 0, 0, 0)); // 여백없애기
		setting_button.setContentAreaFilled(false); // 버튼 바탕 없애기
		setting_button.setSize(new Dimension(setting.getIconWidth(), setting.getIconHeight())); // 크기
																								// 맞추기
		panel.add(setting_button);
		setting_button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				setting_button.setIcon(setting2);
			}

			public void mouseExited(MouseEvent evt) {
				setting_button.setIcon(setting);
			}
		});
*/
		// end of mainimage and staring right button

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(749, 221, 247, 361);
		add(panel_1);
		panel_1.setLayout(new GridLayout(4, 0, 0, 15));
		
		ImageIcon voca = new ImageIcon(".\\image\\voca.png");
		ImageIcon voca2 = new ImageIcon(".\\image\\voca2.png");
		JButton prac_voca_button = new JButton(voca);
		prac_voca_button.setBorderPainted(false); // 테두리 없애기
		prac_voca_button.setMargin(new Insets(0, 0, 0, 0)); // 여백없애기
		prac_voca_button.setContentAreaFilled(false); // 버튼 바탕 없애기
		prac_voca_button.setSize(new Dimension(voca.getIconWidth(), voca.getIconHeight())); // 크기
																							// 맞추기
		panel_1.add(prac_voca_button);

		prac_voca_button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				prac_voca_button.setIcon(voca2);
				infoimage.setIcon(voca_explain);
		}

			public void mouseExited(MouseEvent evt) {
				prac_voca_button.setIcon(voca);
				infoimage.setIcon(maininfoimage);
			}
		});
		prac_voca_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				F.goto_voca_prac();
			}
		});

		ImageIcon raingame = new ImageIcon(".\\image\\raingame.png");
		ImageIcon raingame2 = new ImageIcon(".\\image\\raingame2.png");
		JButton raingame_btn = new JButton(raingame);
		raingame_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				F.goto_raingame();
			}
		});
		panel_1.add(raingame_btn);
		raingame_btn.setBorderPainted(false); // 테두리 없애기
		raingame_btn.setMargin(new Insets(0, 0, 0, 0)); // 여백없애기
		raingame_btn.setContentAreaFilled(false); // 버튼 바탕 없애기
		raingame_btn.setSize(new Dimension(raingame.getIconWidth(), raingame.getIconHeight())); // 크기맞추기
		raingame_btn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				raingame_btn.setIcon(raingame2);
				infoimage.setIcon(raingame_explain);
			}

			public void mouseExited(MouseEvent evt) {
				raingame_btn.setIcon(raingame);
				infoimage.setIcon(maininfoimage);
			}
		});

		ImageIcon statistic = new ImageIcon(".\\image\\statistic.png");
		ImageIcon statistic2= new ImageIcon(".\\image\\statistic2.png");
		JButton statistic_btn = new JButton(statistic);
		statistic_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				F.goto_statics();
			}
		});
		panel_1.add(statistic_btn);
		statistic_btn.setBorderPainted(false); // 테두리 없애기
		statistic_btn.setMargin(new Insets(0, 0, 0, 0)); // 여백없애기
		statistic_btn.setContentAreaFilled(false); // 버튼 바탕 없애기
		statistic_btn.setSize(new Dimension(statistic.getIconWidth(),statistic.getIconHeight())); // 크기
																														// 맞추기
		statistic_btn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				statistic_btn.setIcon(statistic2);
				infoimage.setIcon(statistic_explain);
			}

			public void mouseExited(MouseEvent evt) {
				statistic_btn.setIcon(statistic);
				infoimage.setIcon(maininfoimage);
			}
		});

		ImageIcon exit = new ImageIcon(
				".\\image\\exit.png");
		ImageIcon exit2 = new ImageIcon(
				".\\image\\exit2.png");
		JButton exit_button = new JButton(exit);
		exit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				F.dispose();
			}
		});
		panel_1.add(exit_button);
		exit_button.setBorderPainted(false); // 테두리 없애기
		exit_button.setMargin(new Insets(0, 0, 0, 0)); // 여백없애기
		exit_button.setContentAreaFilled(false); // 버튼 바탕 없애기
		exit_button.setSize(new Dimension(exit.getIconWidth(), exit.getIconHeight())); // 크기
																						// 맞추기
		exit_button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				exit_button.setIcon(exit2);
				infoimage.setIcon(exit_explain);
			}

			public void mouseExited(MouseEvent evt) {
				exit_button.setIcon(exit);
				infoimage.setIcon(maininfoimage);
			}
		});

		// end of right button panel

		JPanel panel_2 = new JPanel();
		ImageIcon mainlogoicon = new ImageIcon(
				".\\image\\logo2.png");
		JLabel mainlogo = new JLabel(mainlogoicon);
		panel_2.add(mainlogo);
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(12, 49, 984, 162);
		add(panel_2);

		add(login_class.log_panel);
		login_class.log_panel.setLayout(null);

		login_class.btn_login.setFocusable(true);
		login_class.btn_login.setBackground(Color.BLACK);
		login_class.btn_login.setBounds(2, 0, 100, 30);
		
		login_class.btn_login.setBorderPainted(false); // 테두리 없애기
		login_class.btn_login.setMargin(new Insets(0, 0, 0, 0)); // 여백없애기
		login_class.btn_login.setContentAreaFilled(false); // 버튼 바탕 없애기
		login_class.log_panel.add(login_class.btn_login);
		
		login_class.btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Problem_Login lo = new Problem_Login(f);
			}
		});
		setVisible(true);
	}
}