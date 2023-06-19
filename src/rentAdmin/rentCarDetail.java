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
		lblNewLabel_6.setBounds(35, 355, 57, 15);
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
		panel.setBounds(85, 350, 300, 60);
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
		btnCarUpdate.setBounds(435, 345, 85, 30);
		getContentPane().add(btnCarUpdate);
		
		btnCarDelete = new JButton("\uACC4\uC57D\uD574\uC9C0");
		btnCarDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarDelete();
			}
		});
		btnCarDelete.setBounds(435, 385, 85, 30);
		getContentPane().add(btnCarDelete);
		
		btnResetORAdd = new JButton("\uCD08\uAE30\uD654");
		btnResetORAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnResetORAdd.getText().equals("�ʱ�ȭ")) {
					reset();
					btnResetORAdd.setText("�űԵ��");
					btnCarUpdate.setEnabled(false);
					btnCarDelete.setEnabled(false);
				} else {
					CarAdd();
				}
			}
		});
		btnResetORAdd.setBounds(536, 345, 85, 30);
		getContentPane().add(btnResetORAdd);
		
		JButton btnBack = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(536, 385, 85, 30);
		getContentPane().add(btnBack);
	}
	
	// Ȩ ��Ϲ�ư
	public rentCarDetail(int Add) {
		this();
		btnCarUpdate.setVisible(false);
		btnCarDelete.setVisible(false);
		btnResetORAdd.setText("�űԵ��");
	}

	// �������� ������ �ʱ�ȭ
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
	}

	// �������� ����ϱ�
	protected void CarAdd() {

		if(tfCarBrend.getText().equals("") || tfCarName.getText().equals("") || tfCarClass.getText().equals("")	|| tfRentPrice.getText().equals("") || tfCarColor.getText().equals("") || tfCarOil.getText().equals("") || tfCarType.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "������ �Է��ϼ���");
		} else {
			String sql = "";
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
		        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
					
				sql = "INSERT INTO cartbl (cindex, cbrend, cclass, cname, ccolor, coil, ctype, price, cimg, cnote) VALUES (CARTBL_SEQ.nextval,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, tfCarBrend.getText());
				pstmt.setString(2, tfCarClass.getText());
				pstmt.setString(3, tfCarName.getText());
				pstmt.setString(4, tfCarColor.getText());
				pstmt.setString(5, tfCarOil.getText());
				pstmt.setString(6, tfCarType.getText());
				pstmt.setInt(7, Integer.parseInt(tfRentPrice.getText()));
				pstmt.setString(8, "");
				pstmt.setString(9, tfOther.getText());			
				int insertok = pstmt.executeUpdate();
				if(insertok == 1) {
					JOptionPane.showMessageDialog(null, "�������� ��ϼ���");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "�������� ��Ͻ���");
					reset();
				}
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "�������� ��Ͻ���");
			}
		}
	}

	// �������� �����ϱ�
	protected void CarDelete() {
		
		String cindex = tfCarNum.getText();
		if(cindex.equals("")) {
			JOptionPane.showMessageDialog(null, "�������� ����ȸ �ʿ�");
		} else {
			String sql = "";
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
		        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
					
				sql = "DELETE FROM cartbl WHERE cindex = " + cindex;
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				int result = JOptionPane.showConfirmDialog(null, "������� �ұ��?", "��������", JOptionPane.YES_NO_OPTION);
	
				if (result == JOptionPane.YES_OPTION) {
					int deleteok = pstmt.executeUpdate();
					if(deleteok == 1) {
						JOptionPane.showMessageDialog(null, "���������� ��������");
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "���������� ��������");
					}
				}				
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	// �������� �����ϱ�
	protected void CarUpdate() {
		
		int result = JOptionPane.showConfirmDialog(null, "�������� �����ұ��?", "��������", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			String cindex = tfCarNum.getText();
			String sql = "";
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
		        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
					
				sql = "UPDATE cartbl SET cbrend=?, cname=?, cclass=?, ccolor=?, coil=?, ctype=?, price=?, cnote=? WHERE cindex = " + cindex;
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, tfCarBrend.getText());
				pstmt.setString(2, tfCarName.getText());
				pstmt.setString(3, tfCarClass.getText());
				pstmt.setString(4, tfCarColor.getText());
				pstmt.setString(5, tfCarOil.getText());
				pstmt.setString(6, tfCarType.getText());
				pstmt.setInt(7, Integer.parseInt(tfRentPrice.getText()));
				pstmt.setString(8, tfOther.getText());			
				int updateok = pstmt.executeUpdate();
				if(updateok == 1) {
					JOptionPane.showMessageDialog(null, "���������� ��������");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "�������� ��������");
				}
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "�������� ��������");
			}
		}
	}

	public rentCarDetail(String SearchCindex) {
		this();
		CarDetail(SearchCindex);
	}

	// �������� �󼼺���
	private void CarDetail(String SearchCindex) {
		
		String sql = "";
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
				
				String cimg = rs.getString("cimg");				
				if(cimg.substring(0,1).equals("/")) {
					String[] cimgs = cimg.split("/");
					filePath = "/carimg/" + cimgs[cimgs.length - 1];
				} else {
					filePath = cimg;
				}
				lblCarImg.setText("<html><img src='" + filePath + "' width='350' height='300'></html>");
			}				
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}	
}
