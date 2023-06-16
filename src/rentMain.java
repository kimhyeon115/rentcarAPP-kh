import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JTabbedPane;
import java.awt.Font;

public class rentMain extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rentMain dialog = new rentMain();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JTextField tfSearchMember;
	private JTable tableMember;
	private JTextField tfSearchCar;
	private JTable tableCar;
	private JTextField tfSearchReservation;
	private JTable tableReservation;

	/**
	 * Create the dialog.
	 */
	public rentMain() {
		setTitle("RentCar Management System");
		setBounds(100, 100, 850, 550);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnNewButton = new JButton("\uC815\uBCF4");
		menuBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uB85C\uADF8\uC544\uC6C3");
		menuBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\uC885\uB8CC");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuBar.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("\uB80C\uD2B8\uCE74 \uAD00\uB9AC \uC2DC\uC2A4\uD15C");
		menuBar.add(lblNewLabel);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 834, 486);
		getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("회원검색", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("\uC870\uD68C \uB300\uC0C1 :");
		lblNewLabel_1.setBounds(188, 20, 71, 22);
		panel.add(lblNewLabel_1);
		
		JComboBox cbSearchMemberType = new JComboBox();
		cbSearchMemberType.setModel(new DefaultComboBoxModel(new String[] {"\uC804 \uCCB4", "\uC774 \uB984", "\uC544\uC774\uB514"}));
		cbSearchMemberType.setBounds(260, 20, 71, 23);
		panel.add(cbSearchMemberType);
		
		tfSearchMember = new JTextField();
		tfSearchMember.setBounds(358, 20, 129, 22);
		panel.add(tfSearchMember);
		tfSearchMember.setColumns(10);
		
		JButton btnSearchMember = new JButton("\uAC80 \uC0C9");
		btnSearchMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int keytype = cbSearchMemberType.getSelectedIndex();
				String keyword = tfSearchMember.getText();
				MemberList(keytype, keyword);				
			}
		});
		btnSearchMember.setBounds(510, 20, 97, 23);
		panel.add(btnSearchMember);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 70, 829, 377);
		panel.add(scrollPane);
		
		tableMember = new JTable();
		tableMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

			}
		});
		scrollPane.setViewportView(tableMember);
		
		JLabel lblNewLabel_2 = new JLabel("\uD68C\uC6D0\uBAA9\uB85D");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_2.setBounds(12, 5, 97, 55);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("차량정보", null, panel_1, null);
		
		JLabel lblNewLabel_1_1 = new JLabel("\uC870\uD68C \uB300\uC0C1 :");
		lblNewLabel_1_1.setBounds(188, 20, 71, 22);
		panel_1.add(lblNewLabel_1_1);
		
		JComboBox cbSearchCarType = new JComboBox();
		cbSearchCarType.setModel(new DefaultComboBoxModel(new String[] {"\uC804 \uCCB4", "\uBC88 \uD638", "\uBE0C\uB79C\uB4DC", "\uCC28 \uC885", "\uBA85 \uCE6D"}));
		cbSearchCarType.setBounds(260, 20, 71, 23);
		panel_1.add(cbSearchCarType);
		
		tfSearchCar = new JTextField();
		tfSearchCar.setColumns(10);
		tfSearchCar.setBounds(358, 20, 129, 22);
		panel_1.add(tfSearchCar);
		
		JButton btnSearchCar = new JButton("\uAC80 \uC0C9");
		btnSearchCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int keytype = cbSearchCarType.getSelectedIndex();
				String keyword = tfSearchCar.getText();
				CarList(keytype, keyword);
			}
		});
		btnSearchCar.setBounds(510, 20, 97, 23);
		panel_1.add(btnSearchCar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 70, 829, 377);
		panel_1.add(scrollPane_1);
		
		tableCar = new JTable();
		scrollPane_1.setViewportView(tableCar);
		
		JLabel lblNewLabel_2_1 = new JLabel("\uCC28\uB7C9\uBAA9\uB85D");
		lblNewLabel_2_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(12, 5, 97, 55);
		panel_1.add(lblNewLabel_2_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		tabbedPane.addTab("예약정보", null, panel_2, null);
		
		JLabel lblNewLabel_1_2 = new JLabel("\uC870\uD68C \uB300\uC0C1 :");
		lblNewLabel_1_2.setBounds(188, 20, 71, 22);
		panel_2.add(lblNewLabel_1_2);
		
		JComboBox cbSearchReservationType = new JComboBox();
		cbSearchReservationType.setModel(new DefaultComboBoxModel(new String[] {"\uC804 \uCCB4", "\uC544\uC774\uB514", "\uC608\uC57D\uBC88\uD638", "\uCC28\uB7C9\uBC88\uD638"}));
		cbSearchReservationType.setBounds(260, 20, 71, 23);
		panel_2.add(cbSearchReservationType);
		
		tfSearchReservation = new JTextField();
		tfSearchReservation.setColumns(10);
		tfSearchReservation.setBounds(358, 20, 129, 22);
		panel_2.add(tfSearchReservation);
		
		JButton btnSearchReservation = new JButton("\uAC80 \uC0C9");
		btnSearchReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int keytype = cbSearchReservationType.getSelectedIndex();
				String keyword = tfSearchReservation.getText();
				ReservationList(keytype, keyword);
			}
		});
		btnSearchReservation.setBounds(510, 20, 97, 23);
		panel_2.add(btnSearchReservation);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 70, 829, 377);
		panel_2.add(scrollPane_2);
		
		tableReservation = new JTable();
		scrollPane_2.setViewportView(tableReservation);
		
		JLabel lblNewLabel_2_2 = new JLabel("\uC608\uC57D\uBAA9\uB85D");
		lblNewLabel_2_2.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_2_2.setBounds(12, 5, 97, 55);
		panel_2.add(lblNewLabel_2_2);
	}
	
	protected void ReservationList(int keytype, String keyword) {

		String sql = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
	        
				if(keytype == 0) {
					sql = "SELECT rtnum, rid, cindex, rvdate, rtdate, returndate, rtprice FROM rreservation ";
					sql = sql + "where UPPER(rid) Like UPPER(?) or ";
					sql = sql + "UPPER(rtnum) Like UPPER(?) or UPPER(cindex) Like UPPER(?)";
				}else if(keytype == 1) {
					sql = "SELECT rtnum, rid, cindex, rvdate, rtdate, returndate, rtprice FROM rreservation where UPPER(rid) Like UPPER(?)";
				}else if(keytype == 2) {
					sql = "SELECT rtnum, rid, cindex, rvdate, rtdate, returndate, rtprice FROM rreservation where UPPER(rtnum) Like UPPER(?)";
				}else if(keytype == 3) {
					sql = "SELECT rtnum, rid, cindex, rvdate, rtdate, returndate, rtprice FROM rreservation where UPPER(cindex) Like UPPER(?)";
				}
				PreparedStatement pstmt = con.prepareStatement(sql);
				if(keytype == 0) {
					pstmt.setString(2,'%'+keyword+'%');
					pstmt.setString(3,'%'+keyword+'%');
				}
				pstmt.setString(1,'%'+keyword+'%');
				ResultSet rs = pstmt.executeQuery();
				
				String[] columns = {"예약번호","아이디","차량번호","예약일","대여일","반납일","대여료"};
				DefaultTableModel dtm = (DefaultTableModel)tableReservation.getModel();
				dtm.setColumnIdentifiers(columns);		
				dtm.setRowCount(0);
				
				while(rs.next()) {
					Vector<String> vector = new Vector<String>();
					for(int i=1; i<=7; i++) {
						if(i == 4 || i == 5 || i == 6) {
							vector.add(rs.getString(i).substring(0,10));
						} else {
							vector.add(rs.getString(i));
						}
					}
					dtm.addRow(vector);
				}				
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
	}

	protected void CarList(int keytype, String keyword) {

		String sql = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
	        
				if(keytype == 0) {
					sql = "SELECT cindex, cbrend, cclass, cname, ccolor, coil, ctype, price FROM cartbl ";
					sql = sql + "where UPPER(cindex) Like UPPER(?) or UPPER(cbrend) Like UPPER(?) or ";
					sql = sql + "UPPER(cclass) Like UPPER(?) or UPPER(cname) Like UPPER(?)";
				}else if(keytype == 1) {
					sql = "SELECT cindex, cbrend, cclass, cname, ccolor, coil, ctype, price FROM cartbl where UPPER(cindex) Like UPPER(?)";
				}else if(keytype == 2) {
					sql = "SELECT cindex, cbrend, cclass, cname, ccolor, coil, ctype, price FROM cartbl where UPPER(cbrend) Like UPPER(?)";
				}else if(keytype == 3) {
					sql = "SELECT cindex, cbrend, cclass, cname, ccolor, coil, ctype, price FROM cartbl where UPPER(cclass) Like UPPER(?)";
				}else if(keytype == 4) {
					sql = "SELECT cindex, cbrend, cclass, cname, ccolor, coil, ctype, price FROM cartbl where UPPER(cname) Like UPPER(?)";
				}
				PreparedStatement pstmt = con.prepareStatement(sql);
				if(keytype == 0) {
					pstmt.setString(2,'%'+keyword+'%');
					pstmt.setString(3,'%'+keyword+'%');
					pstmt.setString(4,'%'+keyword+'%');
				}
				pstmt.setString(1,'%'+keyword+'%');
				ResultSet rs = pstmt.executeQuery();
				
				String[] columns = {"번호","브랜드","차종","명칭","색상","연료","기어","대여료"};
				DefaultTableModel dtm = (DefaultTableModel)tableCar.getModel();
				dtm.setColumnIdentifiers(columns);		
				dtm.setRowCount(0);
				
				while(rs.next()) {
					Vector<String> vector = new Vector<String>();
					for(int i=1; i<=8; i++) {
						vector.add(rs.getString(i));
					}
					dtm.addRow(vector);
				}				
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}		
	}
	
	protected void MemberList(int keytype, String keyword) {

		String sql = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
	        
				if(keytype == 0) {
					sql = "SELECT rid, rname, rmobile, remail, rdate FROM rmember where UPPER(rid) Like UPPER(?) ";
					sql = sql + "or UPPER(rname) Like UPPER(?)";
				}else if(keytype == 1) {
					sql = "SELECT rid, rname, rmobile, remail, rdate FROM rmember where UPPER(rname) Like UPPER(?)";
				}else if(keytype == 2) {
					sql = "SELECT rid, rname, rmobile, remail, rdate FROM rmember where UPPER(rid) Like UPPER(?)";
				}
				PreparedStatement pstmt = con.prepareStatement(sql);
				if(keytype == 0) {
					pstmt.setString(2,'%'+keyword+'%');
				}
				pstmt.setString(1,'%'+keyword+'%');
				ResultSet rs = pstmt.executeQuery();
				
				String[] columns = {"아이디","이름","연락처","이메일","가입일"};
				DefaultTableModel dtm = (DefaultTableModel)tableMember.getModel();
				dtm.setColumnIdentifiers(columns);		
				dtm.setRowCount(0);
				
				while(rs.next()) {
					Vector<String> vector = new Vector<String>();
					for(int i=1; i<=5; i++) {
						vector.add(rs.getString(i));
					}
					dtm.addRow(vector);
				}				
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}		
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
