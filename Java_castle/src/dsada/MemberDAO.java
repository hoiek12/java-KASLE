package dsada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class MemberDAO {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL= "jdbc:mysql://127.0.0.1:3306/practices";//DB의 위치와 URL의 값 여기선 기본 local host

	private static final String USER = "root"; //DB ID
	private static final String PASS = ""; //DB 패스워드

	public MemberDAO() {

	}
	public Connection getConn(){
	Connection con = null;
	
	try {
		Class.forName(DRIVER); //1. 드라이버 로딩
		con = DriverManager.getConnection(URL,USER,PASS); //2. 드라이버 연결
		
	}   catch (Exception e) {
		e.printStackTrace();
	}
	
		return con;
	}
	public boolean insertMember(MemberDTO dto){
		
		boolean ok = false;
		
		Connection con = null; 		 //연결
		PreparedStatement ps = null; //명령
		
		try{
			
			con = getConn();
			String sql = "insert into tb_member(" +
						"id,pwd,name,tel,addr,birth," +
						"job,gender,email) "+
						"values(?,?,?,?,?,?,?,?,?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPwd());
			ps.setString(3, dto.getName());
			ps.setString(4, dto.getTel());
			ps.setString(5, dto.getAddr());
			ps.setString(6, dto.getBirth());
			ps.setString(7, dto.getJob());
			ps.setString(8, dto.getGender());
			ps.setString(9, dto.getEmail());
			int r = ps.executeUpdate(); //실행 -> 저장
			
			
			if(r>0){
				System.out.println("가입 성공");	
				ok=true;
			}else{
				System.out.println("가입 실패");
			}
			
				
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ok;
	}//insertMmeber
	
	public Vector getMemberList(){
	       
		Vector data = new Vector();  //Jtable에 값을 쉽게 넣는 방법 1. 2차원배열   2. Vector 에 vector추가
       
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
        ResultSet rs = null;         //결과
       
        try{
            con = getConn();
            String sql = "select * from tb_member order by name asc";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
           
            while(rs.next()){
                String id = rs.getString("id");
                String pwd = rs.getString("pwd");
               
                Vector row = new Vector();
                row.add(id);
                row.add(pwd);
               
               
                data.add(row);             
            }//while
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }//getMemberList()
	   
}
