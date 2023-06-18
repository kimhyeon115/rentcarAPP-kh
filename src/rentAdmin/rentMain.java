package rentAdmin;
import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import oracle.net.aso.r;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
import javax.swing.SwingConstants;

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
	private JPopupMenu popupMenu;
	protected static String SearchCindex;

	/**
	 * Create the dialog.
	 */
	public rentMain() {
		setTitle("RentCar Management System");
		setBounds(100, 100, 850, 550);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnNewButton = new JButton("\uC815\uBCF4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "<html>&nbsp;&nbsp;&nbsp;&nbsp;인천직업능력교육원<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				message = message + "&nbsp;&nbsp;&nbsp;&nbsp;제 작 : 김 현<br>candle_kh@naver.com</html>";
				JOptionPane.showMessageDialog(null, message);
			}
		});
		menuBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uB85C\uADF8\uC544\uC6C3");
		menuBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\uC885\uB8CC");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		menuBar.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("\uB80C\uD2B8\uCE74 \uAD00\uB9AC \uC2DC\uC2A4\uD15C");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(lblNewLabel);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 834, 486);
		getContentPane().add(tabbedPane);
		
		JPanel panelMember = new JPanel();
		tabbedPane.addTab("회원검색", null, panelMember, null);
		panelMember.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("\uC870\uD68C \uB300\uC0C1 :");
		lblNewLabel_1.setBounds(188, 20, 71, 22);
		panelMember.add(lblNewLabel_1);
		
		JComboBox cbSearchMemberType = new JComboBox();
		cbSearchMemberType.setModel(new DefaultComboBoxModel(new String[] {"\uC804 \uCCB4", "\uC774 \uB984", "\uC544\uC774\uB514"}));
		cbSearchMemberType.setBounds(260, 20, 71, 23);
		panelMember.add(cbSearchMemberType);
		
		tfSearchMember = new JTextField();
		tfSearchMember.setBounds(358, 20, 129, 22);
		panelMember.add(tfSearchMember);
		tfSearchMember.setColumns(10);
		
		JButton btnSearchMember = new JButton("\uAC80 \uC0C9");		
		btnSearchMember.setBounds(510, 20, 97, 23);
		panelMember.add(btnSearchMember);
		btnSearchMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int keytype = cbSearchMemberType.getSelectedIndex();
				String keyword = tfSearchMember.getText();
				MemberList(keytype, keyword);				
			}
		});
		
		JScrollPane scrollPaneM = new JScrollPane();
		scrollPaneM.setBounds(0, 70, 829, 377);
		panelMember.add(scrollPaneM);
				
		tableMember = new JTable();		
		scrollPaneM.setViewportView(tableMember);
		tableMember.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {		    	
		    	int keytype = cbSearchMemberType.getSelectedIndex();
				String keyword = tfSearchMember.getText();
				MemberList(keytype, keyword);
		    }
		});
		
		JLabel lblNewLabel_2 = new JLabel("\uD68C\uC6D0\uBAA9\uB85D");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_2.setBounds(12, 5, 97, 55);
		panelMember.add(lblNewLabel_2);
		
		JButton btnNewButton_4 = new JButton("\uD68C\uC6D0\uB4F1\uB85D");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rentMember rentMember = new rentMember();
				rentMember.setModal(true);
				rentMember.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(619, 20, 95, 23);
		panelMember.add(btnNewButton_4);
		
		JPanel panelCar = new JPanel();
		panelCar.setLayout(null);
		tabbedPane.addTab("차량정보", null, panelCar, null);
		
		JLabel lblNewLabel_1_1 = new JLabel("\uC870\uD68C \uB300\uC0C1 :");
		lblNewLabel_1_1.setBounds(188, 20, 71, 22);
		panelCar.add(lblNewLabel_1_1);
		
		JComboBox cbSearchCarType = new JComboBox();
		cbSearchCarType.setModel(new DefaultComboBoxModel(new String[] {"\uC804 \uCCB4", "\uBC88 \uD638", "\uBE0C\uB79C\uB4DC", "\uCC28 \uC885", "\uBA85 \uCE6D"}));
		cbSearchCarType.setBounds(260, 20, 71, 23);
		panelCar.add(cbSearchCarType);
		
		tfSearchCar = new JTextField();
		tfSearchCar.setColumns(10);
		tfSearchCar.setBounds(358, 20, 129, 22);
		panelCar.add(tfSearchCar);
		
		JButton btnSearchCar = new JButton("\uAC80 \uC0C9");
		btnSearchCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int keytype = cbSearchCarType.getSelectedIndex();
				String keyword = tfSearchCar.getText();
				CarList(keytype, keyword);
			}
		});
		btnSearchCar.setBounds(510, 20, 97, 23);
		panelCar.add(btnSearchCar);
		
		JScrollPane scrollPaneC = new JScrollPane();
		scrollPaneC.setBounds(0, 70, 829, 377);
		panelCar.add(scrollPaneC);
		
		tableCar = new JTable();
		scrollPaneC.setViewportView(tableCar);
		tableCar.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	int keytype = cbSearchCarType.getSelectedIndex();
				String keyword = tfSearchCar.getText();
				CarList(keytype, keyword);
		    }
		});
		
		JLabel lblNewLabel_2_1 = new JLabel("\uCC28\uB7C9\uBAA9\uB85D");
		lblNewLabel_2_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(12, 5, 97, 55);
		panelCar.add(lblNewLabel_2_1);
		
		JPanel panelReservation = new JPanel();
		panelReservation.setLayout(null);
		tabbedPane.addTab("예약정보", null, panelReservation, null);
		
		JLabel lblNewLabel_1_2 = new JLabel("\uC870\uD68C \uB300\uC0C1 :");
		lblNewLabel_1_2.setBounds(188, 20, 71, 22);
		panelReservation.add(lblNewLabel_1_2);
		
		JComboBox cbSearchReservationType = new JComboBox();
		cbSearchReservationType.setModel(new DefaultComboBoxModel(new String[] {"\uC804 \uCCB4", "\uC544\uC774\uB514", "\uC608\uC57D\uBC88\uD638", "\uCC28\uB7C9\uBC88\uD638"}));
		cbSearchReservationType.setBounds(260, 20, 71, 23);
		panelReservation.add(cbSearchReservationType);
		
		tfSearchReservation = new JTextField();
		tfSearchReservation.setColumns(10);
		tfSearchReservation.setBounds(358, 20, 129, 22);
		panelReservation.add(tfSearchReservation);
		
		JButton btnSearchReservation = new JButton("\uAC80 \uC0C9");
		btnSearchReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int keytype = cbSearchReservationType.getSelectedIndex();
				String keyword = tfSearchReservation.getText();
				ReservationList(keytype, keyword);
			}
		});
		btnSearchReservation.setBounds(510, 20, 97, 23);
		panelReservation.add(btnSearchReservation);
		
		JScrollPane scrollPaneR = new JScrollPane();
		scrollPaneR.setBounds(0, 70, 829, 377);
		panelReservation.add(scrollPaneR);
		
		tableReservation = new JTable();
		scrollPaneR.setViewportView(tableReservation);
		
		JLabel lblNewLabel_2_2 = new JLabel("\uC608\uC57D\uBAA9\uB85D");
		lblNewLabel_2_2.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_2_2.setBounds(12, 5, 97, 55);
		panelReservation.add(lblNewLabel_2_2);
		
		popupMenu = new JPopupMenu();
		addPopup(tableCar, popupMenu);
		
		JMenuItem btnCarDetail = new JMenuItem("상세정보");
		btnCarDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rentCarDetail carDetail = new rentCarDetail(SearchCindex);
				carDetail.setModal(true);
				carDetail.setVisible(true);
			}
		});
		popupMenu.add(btnCarDetail);
		
		popupMenu = new JPopupMenu();
		addPopup(tableMember, popupMenu);
		
		JMenuItem btnMemberDelete;
		btnMemberDelete = new JMenuItem("\uCD94\uBC29");
		btnMemberDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberDelete(SearchCindex);
			}
		});
		popupMenu.add(btnMemberDelete);
		
		popupMenu = new JPopupMenu();
		addPopup(tableReservation, popupMenu);
		
		JMenuItem btnReservationDetail;
		btnReservationDetail = new JMenuItem("\uC138\uBD80\uC815\uBCF4");
		btnReservationDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renReservationDetail renReservationDetail = new renReservationDetail(SearchCindex);
				renReservationDetail.setModal(true);
				renReservationDetail.setVisible(true);
			}
		});
		popupMenu.add(btnReservationDetail);
		
		JButton btnNewButton_3 = new JButton("\uCC28\uB7C9\uB4F1\uB85D");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rentCarDetail rentCarDetail = new  rentCarDetail(0);
				rentCarDetail.setModal(true);
				rentCarDetail.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(619, 20, 95, 23);
		panelCar.add(btnNewButton_3);
	}
	
	protected void MemberDelete(String searchRid) {
		
		int result = JOptionPane.showConfirmDialog(null, "해당 회원을 추방할까요?", "회원정보", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			String sql = "";		
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
		        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
					
				sql = "DELETE FROM rmember WHERE rid = '" + searchRid + "'";
				PreparedStatement pstmt = con.prepareStatement(sql);

				int deleteok = pstmt.executeUpdate();
				if(deleteok == 1) {
					JOptionPane.showMessageDialog(null, "회원정보가 삭제성공");
				} else {
					JOptionPane.showMessageDialog(null, "차량정보가 삭제실패");
				}						
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	// 예약정보 검색
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

	// 차량정보 검색
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
	
	// 회원정보 검색
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
						if(i == 3) {
							vector.add(rs.getString(i).substring(0,3) + "-" + rs.getString(i).substring(3,7) + "-" + rs.getString(i).substring(7,11));
						} else if(i == 5) {
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
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					JTable source = (JTable)e.getSource();
					int row = source.rowAtPoint(e.getPoint());
					int column = source.columnAtPoint(e.getPoint());
					
					// 행이 선택되지 않았다면 그 행을 선택한다.
					if(!source.isRowSelected(row))  
						source.changeSelection(row, column, false, false);					
					SearchCindex = source.getValueAt(row, 0).toString();

					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
