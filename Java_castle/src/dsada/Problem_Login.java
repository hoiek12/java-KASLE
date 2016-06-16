package dsada;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.jdbc.Statement;

public class Problem_Login{
	mainframe F;
	String entered_ID;
	String entered_Pass;
	JPanel log_window = new JPanel();
	Problem_Login(mainframe f){
		this.F=f;
		JFrame pf = new JFrame();
		pf.setSize(300,150);
		pf.move(500,500);
		log_window.setBackground(Color.BLACK);
		log_window.setLayout(null);
		
		JTextField text_ID_enter = new JTextField();
		text_ID_enter.setForeground(Color.WHITE);
		text_ID_enter.setBackground(Color.DARK_GRAY);
		text_ID_enter.setBounds(91, 10, 179, 27);
		log_window.add(text_ID_enter);
		text_ID_enter.setColumns(10);
	
		JPasswordField text_Pass_enter = new JPasswordField();
		text_Pass_enter.setBackground(Color.DARK_GRAY);
		text_Pass_enter.setColumns(10);
		text_Pass_enter.setBounds(91, 47, 179, 27);
		log_window.add(text_Pass_enter);
		
		JTextField text_ID = new JTextField("ID :");
		text_ID.setEnabled(false);
		text_ID.setBackground(Color.BLACK);
		text_ID.setForeground(Color.WHITE);
		text_ID.setBounds(12, 10, 77, 27);
		log_window.add(text_ID);
		text_ID.setColumns(10);
		
		JTextField text_pass = new JTextField("Password :");
		text_pass.setEnabled(false);
		text_pass.setBackground(Color.BLACK);
		text_pass.setForeground(Color.WHITE);
		text_pass.setColumns(10);
		text_pass.setBounds(12, 48, 77, 27);
		log_window.add(text_pass);
		pf.setVisible(true);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entered_ID=text_ID_enter.getText();
				entered_Pass=text_Pass_enter.getText();
				Login(entered_ID,entered_Pass);
				pf.setVisible(false);
			}
		});
		btnNewButton.setBounds(124, 84, 67, 23);
		log_window.add(btnNewButton);
		
		JButton button = new JButton("Sign in");
		button.setBounds(203, 84, 67, 23);
		log_window.add(button);
		
		pf.getContentPane().add(log_window);

	}
	String name;
    String password;
    String databaseUsername;
    String databasePassword;
	MemberDAO dao = new MemberDAO();
	public void Login(String name,String password){
			Connection con = null;       //연결
	        PreparedStatement ps = null; //명령
	        ResultSet rs = null;         //결과
	        {
	        try{	
	        	con = dao.getConn();
	        
	            String sql = "select * from tb_member where id = '" + name + "' && pwd ='" + password+ "'";

	            ps = con.prepareStatement(sql);
	            rs = ps.executeQuery();
	            	
	            while(rs.next()){
	            	databaseUsername = rs.getString("id");
	                databasePassword = rs.getString("pwd");
	            }

	            if (name.equals(databaseUsername) && password.equals(databasePassword)) {
	            	mainframe.is_logined=true;
	            	mainframe.current_ID=name;
	            	System.out.println(mainframe.is_logined);
	            	System.out.println(mainframe.current_ID);
					login_class.btn_login.setIcon(login_class.logout_icon);
					login_class.btn_login.repaint();
	            } else {
	            	JOptionPane.showMessageDialog(log_window, "Can`t login please try again");
	            }
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }
	      }
		}
}
