package dsada;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class statics extends JPanel {
	private mainframe F;
	private JTextField textField;
	MemberDAO dao = new MemberDAO();

	public statics(mainframe f) {
		F=f;
		setBackground(Color.BLACK);
		setBounds(100, 100, 1008, 592);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(249, 192, 566, 268);
		add(panel);
		panel.setLayout(new GridLayout(4, 2, 0, 0));
		
		JTextField statistic_I1 = new JTextField("Number of BasicPractice");
		statistic_I1.setForeground(Color.WHITE);
		statistic_I1.setBackground(Color.BLACK);
		panel.add(statistic_I1);
		
		JTextField statistic_O1 = new JTextField(get_num_basic());
		statistic_O1.setForeground(Color.WHITE);
		statistic_O1.setBackground(Color.BLACK);
		panel.add(statistic_O1);
		
		JTextField statistic_I2 = new JTextField("Number of NumberPractice");
		statistic_I2.setForeground(Color.WHITE);
		statistic_I2.setBackground(Color.BLACK);
		panel.add(statistic_I2);
		
		JTextField statistic_O2 = new JTextField(get_num_num());
		statistic_O2.setForeground(Color.WHITE);
		statistic_O2.setBackground(Color.BLACK);
		panel.add(statistic_O2);
		
		JTextField statistic_I3 = new JTextField("Number of WordPractice");
		statistic_I3.setForeground(Color.WHITE);
		statistic_I3.setBackground(Color.BLACK);
		panel.add(statistic_I3);
		
		JTextField statistic_O3 = new JTextField(get_num_word());
		statistic_O3.setForeground(Color.WHITE);
		statistic_O3.setBackground(Color.BLACK);
		panel.add(statistic_O3);
		
		JTextField statistic_I4 = new JTextField("Game Score");
		statistic_I4.setForeground(Color.WHITE);
		statistic_I4.setBackground(Color.BLACK);
		panel.add(statistic_I4);
		
		JTextField statistic_O4 = new JTextField(get_score());
		statistic_O4.setForeground(Color.WHITE);
		statistic_O4.setBackground(Color.BLACK);
		panel.add(statistic_O4);
		
		JLabel what_id = new JLabel("ID : "+F.current_ID);
		what_id.setFont(new Font("굴림", Font.PLAIN, 24));
		what_id.setHorizontalAlignment(SwingConstants.CENTER);
		what_id.setForeground(Color.WHITE);
		what_id.setBackground(Color.BLACK);
		what_id.setBounds(411, 101, 246, 81);
		add(what_id);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(918, 113, 78, 61);
		add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		ImageIcon home = new ImageIcon(".\\image\\home.png");
		JButton goto_home_btn = new JButton(home);
		panel_1.add(goto_home_btn);
		goto_home_btn.setBorderPainted(false); // 테두리 없애기
		goto_home_btn.setMargin(new Insets(0, 0, 0, 0)); // 여백없애기
		goto_home_btn.setContentAreaFilled(false); // 버튼 바탕 없애기
		goto_home_btn.setSize(new Dimension(home.getIconWidth(), home.getIconHeight())); // 크기맞추기
		goto_home_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				F.gotomainpage();
			}
		});
	}

	public static void insert_score(int score){
			String name = mainframe.current_ID;
	        MemberDAO dao = new MemberDAO();
	        Connection con = null;       //연결
	        PreparedStatement ps = null; //명령
	        int rs = 0;    				//결과
	        {
	        try{   
	            con = dao.getConn();
	            String sql = "update tb_member SET PlayerScore="+score+" where id = '"+name+"';";
	            ps = con.prepareStatement(sql);
	            rs = ps.executeUpdate();
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }
	      }
	   }
	public static void insert_basic_prac(int num_basic){
		String name = mainframe.current_ID;
        MemberDAO dao = new MemberDAO();
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
        int rs = 0;         //결과
        {
        try{   
            con = dao.getConn();
            String sql = "update tb_member SET Num_Basic="+num_basic+" where id = '"+name+"';";
            ps = con.prepareStatement(sql);
            rs = ps.executeUpdate();
               
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
      }
   }
	public static void insert_word_prac(int num_word){
		String name = mainframe.current_ID;
        MemberDAO dao = new MemberDAO();
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
        int rs = 0;         //결과
        {
        try{   
            con = dao.getConn();
            String sql = "update tb_member SET Num_Word="+num_word+" where id = '"+name+"';";
            ps = con.prepareStatement(sql);
            rs = ps.executeUpdate();
               
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
      }
   }
	public static void insert_num_prac(int num_num){
        String name = mainframe.current_ID;
        MemberDAO dao = new MemberDAO();
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
        int rs = 0;         //결과
        {
        try{   
            con = dao.getConn();
            String sql = "update tb_member SET Num_Num="+num_num+" where id = '"+name+"';";
            ps = con.prepareStatement(sql);
            rs = ps.executeUpdate();
               
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
      }
   }
	public static int get_score(){
    	int temp = 0;
		String name = mainframe.current_ID;
        MemberDAO dao = new MemberDAO();
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
        ResultSet rs = null;         //결과
        {
        try{
            con = dao.getConn();
            String sql = "select PlayerScore from tb_member where id = '"+name+"';";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
            	temp=Integer.parseInt(rs.getString("Playerscore"));
            }
            return temp;
        }	
        catch(Exception e){
            e.printStackTrace();
        }
      }
        return temp;
	}
	public static int get_num_basic(){
    	int temp = 0;
		String name = mainframe.current_ID;
        MemberDAO dao = new MemberDAO();
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
        ResultSet rs = null;         //결과
        {
        try{
            con = dao.getConn();
            String sql = "select PlayerScore from tb_member where id = '"+name+"';";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
            	temp=Integer.parseInt(rs.getString("Num_Basic"));
            }
            return temp;
        }	
        catch(Exception e){
            e.printStackTrace();
        }
      }
        return temp;
	}
	public static int get_num_word(){
    	int temp = 0;
		String name = mainframe.current_ID;
        MemberDAO dao = new MemberDAO();
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
        ResultSet rs = null;         //결과
        {
        try{
            con = dao.getConn();
            String sql = "select PlayerScore from tb_member where id = '"+name+"';";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
            	temp=Integer.parseInt(rs.getString("Num_Word"));
            }
            return temp;
        }	
        catch(Exception e){
            e.printStackTrace();
        }
      }
        return temp;
	}
	public static int get_num_num(){
    	int temp = 0;
		String name = mainframe.current_ID;
        MemberDAO dao = new MemberDAO();
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
        ResultSet rs = null;         //결과
        {
        try{
            con = dao.getConn();
            String sql = "select Num_Num from tb_member where id = '"+name+"';";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
            	temp=Integer.parseInt(rs.getString("Num_Num"));
            }
            return temp;
        }	
        catch(Exception e){
            e.printStackTrace();
        }
      }
        return temp;
	}
}
