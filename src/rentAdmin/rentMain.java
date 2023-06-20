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
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

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
	
	protected static boolean adminsession = false;
	private boolean session = false;
	private String sessionId = "";
	private JTextField tfSearchMember;
	private JTable tableMember;
	private JTextField tfSearchCar;
	private JTable tableCar;
	private JTextField tfSearchReservation;
	private JTable tableReservation;
	private JPopupMenu popupMenu;
	protected static String SearchCindex;
	private JTextField tfUserId;
	private JPasswordField tfUserPw;
	private JPanel panelLogin;
	private String UserName;
	private JLabel lblUserLoginName;
	private JTabbedPane tabbedPane;
	private JButton btnAdminLogin;
	private JPanel panelCar;
	private JPanel panelMember;
	private JPanel panelReservation;
	private JLabel lblStartDay;
	private JLabel lblEndDay;
	private JButton btnEndDay;
	private JButton btnStartDay;
	private JTextField tfUserSearchText;
	private JButton btnUserSearchCar;
	private JComboBox cbUserSearchCarType;
	private JTable tableUserSearchCarList;
	private DefaultTableModel cardtm;
	private DefaultTableModel memberdtm;
	private DefaultTableModel reservationdtm;
	private DefaultTableModel usersearchdtm;

	/**
	 * Create the dialog.
	 */
	public rentMain() {
		setTitle("RentCar Management System");
		setBounds(100, 100, 850, 550);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		btnAdminLogin = new JButton("\uAD00\uB9AC\uC790 \uBAA8\uB4DC");
		if(session == false) {
			btnAdminLogin.setText("관리자 모드");
		} else {
			btnAdminLogin.setText("로그아웃");
		}		
		btnAdminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminLogin();
			}
		});
		menuBar.add(btnAdminLogin);
		
		
		JButton btnNewButton_2 = new JButton("\uC885\uB8CC");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnNewButton = new JButton("\uC815\uBCF4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "<html>&nbsp;&nbsp;&nbsp;&nbsp;인천직업능력교육원<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				message = message + "&nbsp;&nbsp;&nbsp;&nbsp;제 작 : 김 현<br>candle_kh@naver.com</html>";
				JOptionPane.showMessageDialog(null, message);
			}
		});
		menuBar.add(btnNewButton);
		menuBar.add(btnNewButton_2);
		
		JLabel lblModeLabel = new JLabel("\uB80C\uD2B8\uCE74 \uC608\uC57D \uC2DC\uC2A4\uD15C");
		lblModeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(lblModeLabel);
		
		lblUserLoginName = new JLabel("");
		menuBar.add(lblUserLoginName);
		getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 834, 486);
		getContentPane().add(tabbedPane);		
		
		panelLogin = new JPanel();
		tabbedPane.addTab("로그인", null, panelLogin, null);
		panelLogin.setLayout(null);		
		
		JLabel lblNewLabel_3 = new JLabel("\uC544 \uC774 \uB514");
		lblNewLabel_3.setBounds(24, 30, 57, 15);
		panelLogin.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_3_1.setBounds(24, 76, 57, 15);
		panelLogin.add(lblNewLabel_3_1);
		
		tfUserId = new JTextField();
		tfUserId.setBounds(88, 27, 116, 21);
		panelLogin.add(tfUserId);
		tfUserId.setColumns(10);
		
		tfUserPw = new JPasswordField();
		tfUserPw.setBounds(88, 73, 116, 21);
		panelLogin.add(tfUserPw);
		
		JButton btnNewButton_5 = new JButton("\uB85C\uADF8\uC778");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLogin();
			}
		});
		btnNewButton_5.setFont(new Font("굴림", Font.PLAIN, 11));
		btnNewButton_5.setBounds(24, 134, 77, 23);
		panelLogin.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rentMember rentMember = new rentMember();
				rentMember.setModal(true);
				rentMember.setVisible(true);
			}
		});
		btnNewButton_6.setFont(new Font("굴림", Font.PLAIN, 10));
		btnNewButton_6.setBounds(127, 134, 77, 23);
		panelLogin.add(btnNewButton_6);
		
		JLabel lblNewLabel_4 = new JLabel("");
		ImageIcon icon = new ImageIcon(rentMain.class.getResource("/carimg/main.jpg"));
		Image image = icon.getImage();
		image = image.getScaledInstance(829, 457, Image.SCALE_SMOOTH);
 		lblNewLabel_4.setIcon(new ImageIcon(image));
		lblNewLabel_4.setBounds(0, 0, 829, 457);
		panelLogin.add(lblNewLabel_4);
		
		JPanel panelSearchCar = new JPanel();
		panelSearchCar.setLayout(null);
		tabbedPane.addTab("차량검색", null, panelSearchCar, null);
		
		btnUserSearchCar = new JButton("\uAC80 \uC0C9");
		btnUserSearchCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int keytype = cbUserSearchCarType.getSelectedIndex();
				String keyword = tfUserSearchText.getText();
				UserSearchCarList(keytype, keyword);
			}
		});
		btnUserSearchCar.setBounds(442, 68, 97, 23);
		panelSearchCar.add(btnUserSearchCar);
		btnUserSearchCar.setEnabled(false);
		
		JScrollPane scrollPaneC_1 = new JScrollPane();
		scrollPaneC_1.setBounds(0, 115, 829, 342);
		panelSearchCar.add(scrollPaneC_1);
		
		tableUserSearchCarList = new JTable();
		scrollPaneC_1.setViewportView(tableUserSearchCarList);
		String[] usersearchcolumns = {"번호","브랜드","차종","명칭","색상","연료","기어","대여료"};
		usersearchdtm = (DefaultTableModel)tableUserSearchCarList.getModel();
		usersearchdtm.setColumnIdentifiers(usersearchcolumns);
		
		popupMenu = new JPopupMenu();
		addPopup(tableUserSearchCarList, popupMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\uC608\uC57D\uD558\uB7EC \uAC00\uAE30");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(session == false) {
					String message = "<html><p>로그인후 예약 가능합니다<br>&nbsp;&nbsp;&nbsp;로그인 하시겠습니까?</p></html>";
					int result = JOptionPane.showConfirmDialog(null, message, "예약여부", JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_OPTION) {
						tabbedPane.setSelectedIndex(0);
					}
				}else if(session == true) {
					if(lblStartDay.getText().equals("") || lblEndDay.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "날짜를 선택하세요");
					} else {				
						rentReservationDetail rentReservationDetail = new rentReservationDetail
								(SearchCindex, sessionId, UserName, lblStartDay.getText(), lblEndDay.getText());
						rentReservationDetail.setModal(true);
						rentReservationDetail.setVisible(true);
					}
				} 
			}
		});
		popupMenu.add(mntmNewMenuItem_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("\uCC28\uB7C9\uAC80\uC0C9");
		lblNewLabel_2_1_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_2_1_1.setBounds(12, 5, 97, 55);
		panelSearchCar.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel = new JLabel("\uB300\uC5EC\uC77C : ");
		lblNewLabel.setBounds(121, 25, 50, 15);
		panelSearchCar.add(lblNewLabel);
		
		btnStartDay = new JButton("\uC120\uD0DD");
		btnStartDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rentCal cal = new rentCal();				
				cal.setModal(true);
				cal.setVisible(true);
				
				String date = cal.getDate();
				if(date != null) {
					LocalDate localDate1 = LocalDate.parse(date);
					LocalDate now = LocalDate.now();			
									
					if(localDate1.isBefore(now)) {
						JOptionPane.showMessageDialog(null, "지난 날짜는 선택할수 없습니다");
						lblStartDay.setText("");
						lblEndDay.setText("");
						btnEndDay.setEnabled(false);
						btnUserSearchCar.setEnabled(false);
					} else {
						lblStartDay.setText(cal.getDate());
						btnEndDay.setEnabled(true);
						if(!lblEndDay.getText().equals("")) {
							LocalDate localDate2 = LocalDate.parse(lblEndDay.getText());
							if(localDate2.isBefore(localDate1) || localDate2.equals(localDate1)) {
								lblEndDay.setText("");
							}
						}						
					}
				} else {
					lblStartDay.setText("");
					lblEndDay.setText("");
					btnEndDay.setEnabled(false);
					btnUserSearchCar.setEnabled(false);
				}				
			}
		});
		btnStartDay.setBounds(308, 23, 60, 23);
		panelSearchCar.add(btnStartDay);
		
		lblStartDay = new JLabel("");
		lblStartDay.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblStartDay.setOpaque(true);
		lblStartDay.setBackground(new Color(255, 255, 255));
		lblStartDay.setBounds(183, 23, 116, 20);
		panelSearchCar.add(lblStartDay);
		
		lblEndDay = new JLabel("");
		lblEndDay.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblEndDay.setBackground(new Color(255, 255, 255));
		lblEndDay.setOpaque(true);
		lblEndDay.setBounds(442, 23, 116, 20);
		panelSearchCar.add(lblEndDay);
		
		btnEndDay = new JButton("\uC120\uD0DD");
		btnEndDay.setEnabled(false);
		btnEndDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rentCal cal = new rentCal();				
				cal.setModal(true);
				cal.setVisible(true);
				
				String date = cal.getDate();
				if(date != null) {
					if(!lblStartDay.getText().equals("")) {
						LocalDate localDate1 = LocalDate.parse(lblStartDay.getText());
						LocalDate localDate2 = LocalDate.parse(cal.getDate());
						
						if(localDate2.isBefore(localDate1) || localDate2.equals(localDate1)) {
							JOptionPane.showMessageDialog(null, "반납은 대여일 이후에만 가능합니다");
							lblEndDay.setText("");
							btnUserSearchCar.setEnabled(false);
						} else {
							lblEndDay.setText(date);
							btnUserSearchCar.setEnabled(true);
							int keytype = cbUserSearchCarType.getSelectedIndex();
							String keyword = tfUserSearchText.getText();
							UserSearchCarList(keytype, keyword);
						}						
				} else {				
						lblEndDay.setText("");
					}
				}				
			}
		});
		btnEndDay.setBounds(570, 23, 60, 23);
		panelSearchCar.add(btnEndDay);
		
		JLabel lblNewLabel_5 = new JLabel("\uB300\uC5EC\uC77C : ");
		lblNewLabel_5.setBounds(380, 25, 50, 15);
		panelSearchCar.add(lblNewLabel_5);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\uD0A4\uC6CC\uB4DC :");
		lblNewLabel_1_1_1.setBounds(121, 68, 71, 22);
		panelSearchCar.add(lblNewLabel_1_1_1);
		
		cbUserSearchCarType = new JComboBox();
		cbUserSearchCarType.setModel(new DefaultComboBoxModel(new String[] {"\uC804 \uCCB4", "\uBE0C\uB79C\uB4DC", "\uCC28 \uC885", "\uBA85 \uCE6D"}));
		cbUserSearchCarType.setBounds(183, 68, 71, 23);
		panelSearchCar.add(cbUserSearchCarType);
		
		tfUserSearchText = new JTextField();
		tfUserSearchText.setColumns(10);
		tfUserSearchText.setBounds(285, 69, 129, 22);
		panelSearchCar.add(tfUserSearchText);
		
		panelCar = new JPanel();
		panelCar.setLayout(null);
		tabbedPane.addTab("차량정보", null, panelCar, null);
		tabbedPane.setEnabledAt(2,false);
		
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
		String[] carcolumns = {"번호","브랜드","차종","명칭","색상","연료","기어","대여료"};
		cardtm = (DefaultTableModel)tableCar.getModel();
		cardtm.setColumnIdentifiers(carcolumns);
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
		
		panelMember = new JPanel();
		tabbedPane.addTab("회원검색", null, panelMember, null);
		panelMember.setLayout(null);
		tabbedPane.setEnabledAt(3,false);
		
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
		String[] membercolumns = {"아이디","이름","연락처","이메일","가입일"};
		memberdtm = (DefaultTableModel)tableMember.getModel();
		memberdtm.setColumnIdentifiers(membercolumns);
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
		
		panelReservation = new JPanel();
		panelReservation.setLayout(null);
		tabbedPane.addTab("예약정보", null, panelReservation, null);
		tabbedPane.setEnabledAt(4,false);
		
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
		String[] reservationcolumns = {"예약번호","아이디","차량번호","예약일","대여일","반납일","대여료"};
		reservationdtm = (DefaultTableModel)tableReservation.getModel();
		reservationdtm.setColumnIdentifiers(reservationcolumns);
		
		JLabel lblNewLabel_2_2 = new JLabel("\uC608\uC57D\uBAA9\uB85D");
		lblNewLabel_2_2.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_2_2.setBounds(12, 5, 97, 55);
		panelReservation.add(lblNewLabel_2_2);
		
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
				rentReservationDetail rentReservationDetail = new rentReservationDetail(SearchCindex);
				rentReservationDetail.setModal(true);
				rentReservationDetail.setVisible(true);
			}
		});
		popupMenu.add(btnReservationDetail);
	}
	
	// 고객이 렌트카 검색
	protected void UserSearchCarList(int keytype, String keyword) {
		
		String sql = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
	        
				if(keytype == 0) {
					sql = "select c.cindex, c.cbrend, c.cclass, c.cname, c.ccolor, c.coil, c.ctype, c.price from cartbl c left join ";
					sql = sql + "(select * from rreservation  where rtdate <= ? and returndate >= ? or rtdate <= ? and returndate >= ?) A ";
					sql = sql + "on c.cindex = A.cindex where A.cindex is null";
				}else if(keytype == 1) {
					sql = "select * from (select c.cindex, c.cbrend, c.cclass, c.cname, c.ccolor, c.coil, c.ctype, c.price from cartbl c left join ";
					sql = sql + "(select * from rreservation  where rtdate <= ? and returndate >= ? or rtdate <= ? and returndate >= ?) A ";
					sql = sql + "on c.cindex = A.cindex where A.cindex is null) where UPPER(cbrend) Like UPPER(?)";
				}else if(keytype == 2) {
					sql = "select * from (select c.cindex, c.cbrend, c.cclass, c.cname, c.ccolor, c.coil, c.ctype, c.price from cartbl c left join ";
					sql = sql + "(select * from rreservation  where rtdate <= ? and returndate >= ? or rtdate <= ? and returndate >= ?) A ";
					sql = sql + "on c.cindex = A.cindex where A.cindex is null) where UPPER(cclass) Like UPPER(?)";
				}else if(keytype == 3) {
					sql = "select * from (select c.cindex, c.cbrend, c.cclass, c.cname, c.ccolor, c.coil, c.ctype, c.price from cartbl c left join ";
					sql = sql + "(select * from rreservation  where rtdate <= ? and returndate >= ? or rtdate <= ? and returndate >= ?) A ";
					sql = sql + "on c.cindex = A.cindex where A.cindex is null) where UPPER(cname) Like UPPER(?)";
				}
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, lblStartDay.getText().toString());
				pstmt.setString(2, lblStartDay.getText().toString());
				pstmt.setString(3, lblEndDay.getText().toString());
				pstmt.setString(4, lblEndDay.getText().toString());
				if(keytype != 0) {
					pstmt.setString(5, "%"+tfUserSearchText.getText()+"%");
				}				
				ResultSet rs = pstmt.executeQuery();
				
				String[] columns = {"번호","브랜드","차종","명칭","색상","연료","기어","대여료"};
				DefaultTableModel dtm = (DefaultTableModel)tableUserSearchCarList.getModel();
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

	// 관리자 모드 로그인
	protected void adminLogin() {
		
		String rid = "";
		String rpw = "";
		String rname = "";
		if(session == false) {
			String UserId = tfUserId.getText();
			char[] pw = tfUserPw.getPassword();
			String UserPw = new String(pw);
			if(UserId.equals("") || UserPw.equals("")) {
				JOptionPane.showMessageDialog(null, "아이디/비밀번호를 입력하세요");
			} else {
			
				String sql = "";
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
						
					sql = "select rid,rpw,rname from rmember where rid = ? and rpw = ?";
									
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setString(1, UserId);
					pstmt.setString(2, UserPw);
					ResultSet rs = pstmt.executeQuery();

					boolean findAdmin = rs.next();
					if(findAdmin) {
						rid = rs.getString("rid");
						rpw = rs.getString("rpw");
						rname = rs.getString("rname");
						if(rid.equals("admin") && rpw.equals("12345")) {				
							lblUserLoginName.setText("    (" + rname + " 관리자 모드로 로그인 하였습니다) ");
							session = true;
							LoginOk(session,rid);
							btnAdminLogin.setText("로그아웃");
							adminsession = true;
						} else {
							JOptionPane.showMessageDialog(null, "관리자 계정이 아닙니다");
						}
					} else {
						JOptionPane.showMessageDialog(null, "가입 정보가 없습니다");
					}
					
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		} else if(session == true){
			session = false;
			adminsession = false;
			sessionId = "";
			LoginOk(session,rid);
			btnAdminLogin.setText("관리자 모드");
			lblUserLoginName.setText("");
			cardtm.setRowCount(0);
			memberdtm.setRowCount(0);
			reservationdtm.setRowCount(0);
			usersearchdtm.setRowCount(0);
		}
	}

	// 회원 로그인
	protected void UserLogin() {
		
		String UserId = tfUserId.getText();
		char[] pw = tfUserPw.getPassword();
		String UserPw = new String(pw);
		if(UserId.equals("") || UserPw.equals("")) {
			JOptionPane.showMessageDialog(null, "아이디/비밀번호를 입력하세요");
		} else {
		
			String sql = "";
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
		        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
					
				sql = "select rname from rmember where rid = ? and rpw = ?";
								
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, UserId);
				pstmt.setString(2, UserPw);
				ResultSet rs = pstmt.executeQuery();
				boolean rname = rs.next();
					if(rname == false) {
						JOptionPane.showMessageDialog(null, "가입정보가 없습니다");
					} else {				
						UserName = rs.getString("rname");
						lblUserLoginName.setText("    (" + UserName + " 고객님 환영합니다) ");
						session = true;
						sessionId = UserId;
						LoginOk(session);
						tabbedPane.setSelectedIndex(1);
					}
				
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}			
		} 
	}
	
	
	private void LoginOk(boolean session) {
		
		if(session == true) {
			btnAdminLogin.setText("로그아웃");
			tabbedPane.setEnabledAt(0, false);
			tfUserId.setText("");
			tfUserPw.setText("");
		} else {
			btnAdminLogin.setText("관리자 모드");
		}
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
	
	public void LoginOk(boolean session, String rid){
		
		if(session == true && rid.equals("admin")) {
			btnAdminLogin.setText("로그아웃");
			tabbedPane.setEnabledAt(0, false);
			tabbedPane.setEnabledAt(2, true);
			tabbedPane.setSelectedIndex(2);
			tabbedPane.setEnabledAt(3, true);
			tabbedPane.setEnabledAt(4, true);
			tfUserId.setText("");
			tfUserPw.setText("");
		} else {
			btnAdminLogin.setText("관리자 모드");
			tabbedPane.setEnabledAt(0, true);
			tabbedPane.setSelectedIndex(0);
			tabbedPane.setEnabledAt(2, false);
			tabbedPane.setEnabledAt(3, false);
			tabbedPane.setEnabledAt(4, false);
		}
	}
}
