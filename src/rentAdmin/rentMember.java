package rentAdmin;


import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class rentMember extends JDialog {
	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfMobile;
	private JTextField tfEmail;
	private boolean IdCheck = false;
	private JComboBox comboBox;
	private JPasswordField tfPw1;
	private JPasswordField tfPw2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rentMember dialog = new rentMember();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public rentMember() {
		setTitle("\uC2E0\uADDC\uD68C\uC6D0 \uB4F1\uB85D");
		setBounds(100, 100, 432, 459);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("\uC544 \uC774 \uB514");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(40, 70, 70, 20);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(40, 110, 70, 20);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uBE44\uBC88\uD655\uC778");
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(40, 150, 70, 20);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\uC774 \uB984");
		lblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(40, 190, 70, 20);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\uC774 \uBA54 \uC77C");
		lblNewLabel_5.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(40, 270, 70, 20);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("\uD68C\uC6D0 \uD504\uB85C\uD544 \uB4F1\uB85D");
		lblNewLabel_6.setFont(new Font("굴림", Font.BOLD, 22));
		lblNewLabel_6.setBounds(91, 21, 180, 40);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_5_1 = new JLabel("\uC5F0 \uB77D \uCC98");
		lblNewLabel_5_1.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_5_1.setBounds(40, 230, 70, 20);
		getContentPane().add(lblNewLabel_5_1);
		
		tfId = new JTextField();
		tfId.setBounds(135, 70, 125, 21);
		getContentPane().add(tfId);
		tfId.setColumns(10);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(135, 190, 125, 21);
		getContentPane().add(tfName);
		
		tfMobile = new JTextField();
		tfMobile.setColumns(10);
		tfMobile.setBounds(135, 230, 125, 21);
		getContentPane().add(tfMobile);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(135, 270, 125, 21);
		getContentPane().add(tfEmail);
		
		JButton btnIdCheck = new JButton("\uC911\uBCF5\uD655\uC778");
		btnIdCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tfId.getText().equals("")) {
					IdCheck();
				}
			}
		});
		btnIdCheck.setFont(new Font("굴림", Font.PLAIN, 9));
		btnIdCheck.setBounds(292, 69, 78, 23);
		getContentPane().add(btnIdCheck);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"gmail.com", "naver.com", "hotmail.com", "daum.net"}));
		comboBox.setBounds(160, 300, 100, 23);
		getContentPane().add(comboBox);
		
		JLabel lblNewLabel = new JLabel("@");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel.setBounds(135, 300, 20, 20);
		getContentPane().add(lblNewLabel);
		
		JButton btnAdd = new JButton("\uD68C\uC6D0\uB4F1\uB85D");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberAdd();
			}
		});
		btnAdd.setBounds(91, 355, 95, 23);
		getContentPane().add(btnAdd);
		
		JButton btnBack = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(227, 355, 95, 23);
		getContentPane().add(btnBack);
		
		tfPw1 = new JPasswordField();
		tfPw1.setBounds(135, 110, 125, 21);
		getContentPane().add(tfPw1);
		
		tfPw2 = new JPasswordField();
		tfPw2.setBounds(135, 150, 125, 21);
		getContentPane().add(tfPw2);

	}

	protected void IdCheck() {
		
		IdCheck  = false;
		String sql = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
	        
			sql = "SELECT count(rid) as N FROM rmember where rid = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tfId.getText());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getInt("N") == 1) {
					JOptionPane.showMessageDialog(null, "사용중인 아이디");
					tfId.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "사용가능 아이디");
					IdCheck  = true;
				}
			}

		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}		
	}

	protected void MemberAdd() {		

		char[] pw1 = tfPw1.getPassword();
		char[] pw2 = tfPw2.getPassword();
		String spw1 = new String(pw1);
		String spw2 = new String(pw2);
		
		if(tfId.getText().equals("") || spw1.equals("") || spw2.equals("") || tfName.getText().equals("") || tfMobile.getText().equals("") || tfEmail.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "정보를 입력하세요");
		} else if(IdCheck == false) {
			JOptionPane.showMessageDialog(null, "아이디 중복체크 확인");
		} else if(!spw1.equals(spw2)){
			JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다");
			tfPw1.setText("");
			tfPw2.setText("");
		} else {
		
			String sql = "";
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
		        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
		        
				sql = "INSERT INTO rmember (rid, rpw, rname, rmobile, remail) VALUES (?,?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, tfId.getText());
				pstmt.setString(2, new String(tfPw1.getPassword()));
				pstmt.setString(3, tfName.getText());
				pstmt.setString(4, tfMobile.getText());
				pstmt.setString(5, tfEmail.getText()+"@"+comboBox.getSelectedItem().toString());
				int insertok = pstmt.executeUpdate();
				if(insertok == 1) {
					JOptionPane.showMessageDialog(null, "회원등록 성공");
					dispose();					
				} else {
					JOptionPane.showMessageDialog(null, "회원등록 실패");
				}

			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}			
		}
	}
}
