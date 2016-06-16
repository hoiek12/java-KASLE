package dsada;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MemberProc extends JFrame implements ActionListener {
	
	JPanel p;
	JTextField tfId, tfName, tfAddr, tfEmail;
	JTextField tfTel1, tfTel2, tfTel3; //전화
	JComboBox<String> cbJob; //직업
	JPasswordField pfPwd; //비밀번호	
	JTextField tfYear, tfMonth, tfDate; //생년월일
	JRadioButton rbMan, rbWoman; //남, 여
	JButton btnInsert, btnCancel, btnUpdate,btnDelete; //가입, 취소, 수정 , 탈퇴 버튼
	
	GridBagLayout gb;
	GridBagConstraints gbc;
	
	public MemberProc(){ //가입용 생성자
		createUI(); // UI작성해주는 메소드

	}//생성자
	private void createUI(){
		this.setTitle("회원정보");
		gb = new GridBagLayout();
		setLayout(gb);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
	
	//아이디
	JLabel bId = new JLabel("아이디 : ");
	tfId = new JTextField(20);		
	//그리드백에 붙이기
	gbAdd(bId, 0, 0, 1, 1);
	gbAdd(tfId, 1, 0, 3, 1);
	
	//비밀번호
	JLabel bPwd = new JLabel("비밀번호 : ");
	pfPwd = new JPasswordField(20);
	gbAdd(bPwd, 0, 1, 1, 1);
	gbAdd(pfPwd, 1, 1, 3, 1);
	
	//이름
	JLabel bName = new JLabel("이름 :");
	tfName = new JTextField(20);
	gbAdd(bName,0,2,1,1);
	gbAdd(tfName,1,2,3,1);
	
	//전화
	JLabel bTel = new JLabel("전화 :");
	JPanel pTel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	tfTel1 = new JTextField(6);		
	tfTel2 = new JTextField(6);		
	tfTel3 = new JTextField(6);
	pTel.add(tfTel1);
	pTel.add(new JLabel(" - "));
	pTel.add(tfTel2); 
	pTel.add(new JLabel(" - "));
	pTel.add(tfTel3); 
	gbAdd(bTel, 0, 3, 1,1);
	gbAdd(pTel, 1, 3, 3,1);
	
	//주소
	JLabel bAddr = new JLabel("주소: ");
	tfAddr = new JTextField(20);
	gbAdd(bAddr, 0,4,1,1);
	gbAdd(tfAddr, 1, 4, 3,1);
	
	//생일
	JLabel bBirth= new JLabel("생일: ");
	tfYear = new JTextField(6);
	tfMonth = new JTextField(6);
	tfDate = new JTextField(6);
	JPanel pBirth = new JPanel(new FlowLayout(FlowLayout.LEFT));
	pBirth.add(tfYear);
	pBirth.add(new JLabel("/"));
	pBirth.add(tfMonth);
	pBirth.add(new JLabel("/"));
	pBirth.add(tfDate);
	gbAdd(bBirth, 0,5,1,1);
	gbAdd(pBirth, 1, 5, 3,1);
	
	//직업		
	JLabel bJob = new JLabel("직업 : ");
	String[] arrJob = {"---", "학생", "직장인", "주부"};
	cbJob = new JComboBox<String>(arrJob);
	JPanel pJob = new JPanel(new FlowLayout(FlowLayout.LEFT));
	pJob.add(cbJob);		
	gbAdd(bJob, 0,6,1,1);
	gbAdd(pJob,1,6,3,1);
	
	//성별
	JLabel bGender = new JLabel("성별 : ");
	JPanel pGender = new JPanel(new FlowLayout(FlowLayout.LEFT));
	rbMan = new JRadioButton("남",true);
	rbWoman = new JRadioButton("여",true);
	ButtonGroup group = new ButtonGroup();
	group.add(rbMan);
	group.add(rbWoman);
	pGender.add(rbMan);
	pGender.add(rbWoman);		
	gbAdd(bGender, 0,7,1,1);
	gbAdd(pGender,1,7,3,1);
	
	//이메일
	JLabel bEmail = new JLabel("이메일 : ");
	tfEmail = new JTextField(20);
	gbAdd(bEmail, 0,8,1,1);
	gbAdd(tfEmail,1,8,3,1);
	
	//버튼
	JPanel pButton = new JPanel();
	btnInsert = new JButton("가입");
	btnCancel = new JButton("취소");		
	pButton.add(btnInsert);
	pButton.add(btnCancel);		
	gbAdd(pButton, 0, 10, 4, 1);
	
	//버튼에 감지기를 붙이자
	btnInsert.addActionListener(this);
	btnCancel.addActionListener(this);
	
	setSize(350,500);
	setVisible(true);
	//setDefaultCloseOperation(EXIT_ON_CLOSE); //System.exit(0) //프로그램종료
	setDefaultCloseOperation(DISPOSE_ON_CLOSE); //dispose(); //현재창만 닫는다.
}//createUI
	private void gbAdd(JComponent c, int x, int y, int w, int h){
	gbc.gridx = x;
	gbc.gridy = y;
	gbc.gridwidth = w;
	gbc.gridheight = h;
	gb.setConstraints(c, gbc);
	gbc.insets = new Insets(2, 2, 2, 2);
	add(c, gbc);
	}//gbAdd
	 
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btnInsert){
			insertMember();	
			System.out.println("insertMember() 호출 종료");
		}else if(ae.getSource() == btnCancel){
			this.dispose(); //창닫기 (현재창만 닫힘) 
			//system.exit(0)=> 내가 띄운 모든 창이 다 닫힘 			
			}
		//jTable내용 갱신 메소드 호출		
		}
		private void insertMember(){
			
			//화면에서 사용자가 입력한 내용을 얻는다.
			MemberDTO dto = getViewData();
			MemberDAO dao = new MemberDAO();		
			boolean ok = dao.insertMember(dto);
			
			if(ok){
				JOptionPane.showMessageDialog(this, "가입이 완료되었습니다.");
				dispose();
				
			}else{
				JOptionPane.showMessageDialog(this, "가입이 정상적으로 처리되지 않았습니다.");
			}
			
		}//insertMember
		
		public MemberDTO getViewData(){
			
			//화면에서 사용자가 입력한 내용을 얻는다.
			MemberDTO dto = new MemberDTO();
			String id = tfId.getText();
			String pwd = pfPwd.getText();
			String name = tfName.getText();
			String tel1 = tfTel1.getText();
			String tel2 = tfTel2.getText();
			String tel3 = tfTel3.getText();
			String tel = tel1+"-"+tel2+"-"+tel3;
			String addr = tfAddr.getText();
			String birth1 = tfYear.getText();
			String birth2 = tfMonth.getText();
			String birth3 = tfDate.getText();
			//String birth = birth1+"/"+birth2+"/"+birth3;
			String birth = birth1+birth2+birth3;
			String job = (String)cbJob.getSelectedItem();
			String gender = "";
			if(rbMan.isSelected()){
				gender = "M";
			}else if(rbWoman.isSelected()){
				gender = "W";
			}
			
			String email = tfEmail.getText();		
			//dto에 담는다.
			dto.setId(id);
			dto.setPwd(pwd);
			dto.setName(name);
			dto.setTel(tel);
			dto.setAddr(addr);
			dto.setBirth(birth);
			dto.setJob(job);
			dto.setGender(gender);
			dto.setEmail(email);
			
			return dto;
		}
		public Vector<String> getColumn(){
			Vector<String> col = new Vector<String>();
			col.add("아이디");
			col.add("비밀번호");
			col.add("이름");
			col.add("전화");
			col.add("주소");
			col.add("생일");
			col.add("직업");
			col.add("성별");
			col.add("이메일");
			return col;
		}
}

