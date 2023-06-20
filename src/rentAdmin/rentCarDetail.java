package rentAdmin;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.SwingConstants;
import java.awt.Font;

public class rentCarDetail extends JDialog {
	private JTextField tfCarNum;
	private JTextField tfCarBrend;
	private JTextField tfCarName;
	private JTextField tfCarType;
	private JTextField tfCarClass;
	private JTextField tfRentPrice;
	private JTextField tfCarColor;
	private JTextField tfCarOil;
	private JTextArea tfOther;
	private String filePath;
	private JLabel lblCarImg;
	private JButton btnCarUpdate;
	private JButton btnCarDelete;
	private JButton btnResetORAdd;
	private String cimg;
	private JTextField tfUrl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rentCarDetail dialog = new rentCarDetail();
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
	public rentCarDetail() {
		setTitle("\uCC28\uB7C9\uC815\uBCF4");
		setBounds(100, 100, 671, 495);
		getContentPane().setLayout(null);
		
		lblCarImg = new JLabel("\uCC28\uB7C9 \uC774\uBBF8\uC9C0");
		lblCarImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarImg.setOpaque(true);
		lblCarImg.setBackground(new Color(255, 255, 255));
		lblCarImg.setBounds(35, 30, 350, 300);
		getContentPane().add(lblCarImg);
		
		JLabel lblNewLabel_1 = new JLabel("\uCC28\uB7C9\uBC88\uD638");
		lblNewLabel_1.setBounds(435, 35, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uBE0C\uB79C\uB4DC");
		lblNewLabel_2.setBounds(435, 75, 57, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uCC28\uB7C9\uBA85\uCE6D");
		lblNewLabel_3.setBounds(435, 115, 57, 15);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\uB300\uC5EC\uB8CC/\uC77C");
		lblNewLabel_4.setBounds(435, 315, 57, 15);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("\uBE44 \uACE0");
		lblNewLabel_6.setBounds(35, 380, 57, 15);
		getContentPane().add(lblNewLabel_6);
		
		tfCarNum = new JTextField();
		tfCarNum.setEditable(false);
		tfCarNum.setBounds(505, 30, 116, 21);
		getContentPane().add(tfCarNum);
		tfCarNum.setColumns(10);
		
		tfCarBrend = new JTextField();
		tfCarBrend.setBounds(505, 70, 116, 21);
		getContentPane().add(tfCarBrend);
		tfCarBrend.setColumns(10);
		
		tfCarName = new JTextField();
		tfCarName.setBounds(505, 110, 116, 21);
		getContentPane().add(tfCarName);
		tfCarName.setColumns(10);
		
		tfCarType = new JTextField();
		tfCarType.setBounds(505, 270, 116, 21);
		getContentPane().add(tfCarType);
		tfCarType.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(85, 380, 300, 60);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		tfOther = new JTextArea();
		scrollPane.setViewportView(tfOther);
		tfOther.setLineWrap(true);
		
		JLabel lblNewLabel_2_1 = new JLabel("\uB4F1 \uAE09");
		lblNewLabel_2_1.setBounds(435, 155, 57, 15);
		getContentPane().add(lblNewLabel_2_1);
		
		tfCarClass = new JTextField();
		tfCarClass.setColumns(10);
		tfCarClass.setBounds(505, 150, 116, 21);
		getContentPane().add(tfCarClass);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("\uC0C9 \uC0C1");
		lblNewLabel_2_1_1.setBounds(435, 195, 57, 15);
		getContentPane().add(lblNewLabel_2_1_1);
		
		tfRentPrice = new JTextField();
		tfRentPrice.setColumns(10);
		tfRentPrice.setBounds(505, 310, 116, 21);
		getContentPane().add(tfRentPrice);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("\uC5F0 \uB8CC");
		lblNewLabel_2_1_2.setBounds(435, 235, 57, 15);
		getContentPane().add(lblNewLabel_2_1_2);
		
		tfCarColor = new JTextField();
		tfCarColor.setColumns(10);
		tfCarColor.setBounds(505, 190, 116, 21);
		getContentPane().add(tfCarColor);
		
		JLabel lblNewLabel_2_1_2_1 = new JLabel("\uAE30 \uC5B4");
		lblNewLabel_2_1_2_1.setBounds(435, 275, 57, 15);
		getContentPane().add(lblNewLabel_2_1_2_1);
		
		tfCarOil = new JTextField();
		tfCarOil.setColumns(10);
		tfCarOil.setBounds(505, 230, 116, 21);
		getContentPane().add(tfCarOil);
		
		btnCarUpdate = new JButton("\uC815\uBCF4\uC218\uC815");		
		btnCarUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarUpdate();
			}
		});
		btnCarUpdate.setBounds(435, 370, 85, 30);
		getContentPane().add(btnCarUpdate);
		
		btnCarDelete = new JButton("\uACC4\uC57D\uD574\uC9C0");
		btnCarDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarDelete();
			}
		});
		btnCarDelete.setBounds(435, 410, 85, 30);
		getContentPane().add(btnCarDelete);
		
		btnResetORAdd = new JButton("\uCD08\uAE30\uD654");
		btnResetORAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnResetORAdd.getText().equals("초기화")) {
					reset();
					btnResetORAdd.setText("신규등록");
					btnCarUpdate.setEnabled(false);
					btnCarDelete.setEnabled(false);
				} else {
					CarAdd();
				}
			}
		});
		btnResetORAdd.setBounds(536, 370, 85, 30);
		getContentPane().add(btnResetORAdd);
		
		JButton btnBack = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(536, 410, 85, 30);
		getContentPane().add(btnBack);
		
		JButton btnNewButton = new JButton("URL\uC801\uC6A9");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cimg = tfUrl.getText();
				lblCarImg.setText("<html><img src='" + cimg + "' width='350' height='300'></html>");
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 10));
		btnNewButton.setBounds(35, 340, 75, 23);
		getContentPane().add(btnNewButton);
		
		tfUrl = new JTextField();
		tfUrl.setBounds(122, 340, 263, 21);
		getContentPane().add(tfUrl);
		tfUrl.setColumns(10);
	}
	
	// 홈 등록버튼
	public rentCarDetail(int Add) {
		this();
		btnCarUpdate.setVisible(false);
		btnCarDelete.setVisible(false);
		btnResetORAdd.setText("신규등록");
	}

	// 차량정보 페이지 초기화
	protected void reset() {
		tfCarNum.setText("");
		tfCarBrend.setText("");
		tfCarName.setText("");
		tfCarClass.setText("");
		tfRentPrice.setText("");
		tfCarColor.setText("");
		tfCarOil.setText("");
		tfCarType.setText("");
		tfOther.setText("");
		tfUrl.setText("");
	}

	// 차량정보 등록하기
	protected void CarAdd() {

		if(tfCarBrend.getText().equals("") || tfCarName.getText().equals("") || tfCarClass.getText().equals("")	
				|| tfRentPrice.getText().equals("") || tfCarColor.getText().equals("") || tfCarOil.getText().equals("") 
				|| tfCarType.getText().equals("") || tfUrl.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "정보를 입력하세요");
		} else {
			String sql = "";
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
		        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
					
				sql = "INSERT INTO cartbl (cindex, cbrend, cclass, cname, ccolor, coil, ctype, price, cnote, cimg) VALUES (CARTBL_SEQ.nextval,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, tfCarBrend.getText());
				pstmt.setString(2, tfCarClass.getText());
				pstmt.setString(3, tfCarName.getText());
				pstmt.setString(4, tfCarColor.getText());
				pstmt.setString(5, tfCarOil.getText());
				pstmt.setString(6, tfCarType.getText());
				pstmt.setInt(7, Integer.parseInt(tfRentPrice.getText()));
				pstmt.setString(8, tfOther.getText());
				pstmt.setString(9, tfUrl.getText());
				int insertok = pstmt.executeUpdate();
				if(insertok == 1) {
					JOptionPane.showMessageDialog(null, "차량정보 등록성공");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "차량정보 등록실패");
					reset();
				}
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "차량정보 등록실패");
			}
		}
	}

	// 차량정보 삭제하기
	protected void CarDelete() {
		
		String cindex = tfCarNum.getText();
		if(cindex.equals("")) {
			JOptionPane.showMessageDialog(null, "차량정보 재조회 필요");
		} else {
			String sql = "";
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
		        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
					
				sql = "DELETE FROM cartbl WHERE cindex = " + cindex;
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				int result = JOptionPane.showConfirmDialog(null, "계약해지 할까요?", "차량정보", JOptionPane.YES_NO_OPTION);
	
				if (result == JOptionPane.YES_OPTION) {
					int deleteok = pstmt.executeUpdate();
					if(deleteok == 1) {
						JOptionPane.showMessageDialog(null, "차량정보가 삭제성공");
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "차량정보가 삭제실패");
					}
				}				
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	// 차량정보 수정하기
	protected void CarUpdate() {
		
		if(tfCarBrend.getText().equals("") || tfCarName.getText().equals("") || tfCarClass.getText().equals("")	
				|| tfRentPrice.getText().equals("") || tfCarColor.getText().equals("") || tfCarOil.getText().equals("") 
				|| tfCarType.getText().equals("") || tfUrl.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "정보를 입력하세요");
		} else {
			int result = JOptionPane.showConfirmDialog(null, "차량정보 수정할까요?", "차량정보", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				String cindex = tfCarNum.getText();
				String sql = "";
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
						
					sql = "UPDATE cartbl SET cbrend=?, cname=?, cclass=?, ccolor=?, coil=?, ctype=?, price=?, cnote=? , cimg = ? WHERE cindex = " + cindex;
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setString(1, tfCarBrend.getText());
					pstmt.setString(2, tfCarName.getText());
					pstmt.setString(3, tfCarClass.getText());
					pstmt.setString(4, tfCarColor.getText());
					pstmt.setString(5, tfCarOil.getText());
					pstmt.setString(6, tfCarType.getText());
					pstmt.setInt(7, Integer.parseInt(tfRentPrice.getText()));
					pstmt.setString(8, tfOther.getText());
					pstmt.setString(9, tfUrl.getText());
					int updateok = pstmt.executeUpdate();
					if(updateok == 1) {
						JOptionPane.showMessageDialog(null, "차량정보가 수정성공");
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "차량정보 수정실패");
					}
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "차량정보 수정실패");
				}
			}
		}
	}

	public rentCarDetail(String SearchCindex) {
		this();
		setTitle("선택하신 차량정보");
		CarDetail(SearchCindex);
	}

	// 차량정보 상세보기
	private void CarDetail(String SearchCindex) {
		
		String sql = "";
		cimg = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
				
			sql = "SELECT * FROM cartbl where cindex = ?";			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, SearchCindex);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tfCarNum.setText(SearchCindex);
				tfCarBrend.setText(rs.getString("cbrend"));
				tfCarName.setText(rs.getString("cname"));
				tfCarClass.setText(rs.getString("cclass"));
				tfCarColor.setText(rs.getString("ccolor"));
				tfCarOil.setText(rs.getString("coil"));
				tfCarType.setText(rs.getString("ctype"));
				tfRentPrice.setText(rs.getString("price"));
				tfOther.setText(rs.getString("cnote"));				
				cimg = rs.getString("cimg");
				lblCarImg.setText("<html><img src='" + cimg + "' width='350' height='300'></html>");
				tfUrl.setText(cimg);
			}				
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}	
}
