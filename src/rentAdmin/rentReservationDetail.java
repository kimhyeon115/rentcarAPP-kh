package rentAdmin;


import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;

public class rentReservationDetail extends JDialog {
	private JTextField tfCindex;
	private JTextField tfCbrend;
	private JTextField tfCname;
	private JTextField tfCtype;
	private JTextField tfCclass;
	private JTextField tfRtprice;
	private JTextField tfCcolor;
	private JTextField tfCoil;
	private JTextField tfRtnum;
	private JTextField tfRid;
	private JTextField tfRvdate;
	private JTextField tfRRdate;
	private JButton btnCarDelete;
	private JLabel lblRtnumOrRname;
	private JLabel lblCarImg;

	public rentReservationDetail() {
		setBounds(100, 100, 659, 555);
		getContentPane().setLayout(null);
		
		lblCarImg = new JLabel("\uCC28\uB7C9 \uC774\uBBF8\uC9C0");
		lblCarImg.setOpaque(true);
		lblCarImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarImg.setBackground(Color.WHITE);
		lblCarImg.setBounds(30, 30, 350, 300);
		getContentPane().add(lblCarImg);
		
		JLabel lblNewLabel_1 = new JLabel("\uCC28\uB7C9\uBC88\uD638");
		lblNewLabel_1.setBounds(425, 35, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uBE0C\uB79C\uB4DC");
		lblNewLabel_2.setBounds(425, 75, 57, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uCC28\uB7C9\uBA85\uCE6D");
		lblNewLabel_3.setBounds(425, 115, 57, 15);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\uCD1D \uB300\uC5EC\uB8CC");
		lblNewLabel_4.setBounds(425, 315, 57, 15);
		getContentPane().add(lblNewLabel_4);
		
		tfCindex = new JTextField();
		tfCindex.setEditable(false);
		tfCindex.setColumns(10);
		tfCindex.setBounds(495, 30, 116, 21);
		getContentPane().add(tfCindex);
		
		tfCbrend = new JTextField();
		tfCbrend.setEditable(false);
		tfCbrend.setColumns(10);
		tfCbrend.setBounds(495, 70, 116, 21);
		getContentPane().add(tfCbrend);
		
		tfCname = new JTextField();
		tfCname.setEditable(false);
		tfCname.setColumns(10);
		tfCname.setBounds(495, 110, 116, 21);
		getContentPane().add(tfCname);
		
		tfCtype = new JTextField();
		tfCtype.setEditable(false);
		tfCtype.setColumns(10);
		tfCtype.setBounds(495, 270, 116, 21);
		getContentPane().add(tfCtype);
		
		JLabel lblNewLabel_2_1 = new JLabel("\uB4F1 \uAE09");
		lblNewLabel_2_1.setBounds(425, 155, 57, 15);
		getContentPane().add(lblNewLabel_2_1);
		
		tfCclass = new JTextField();
		tfCclass.setEditable(false);
		tfCclass.setColumns(10);
		tfCclass.setBounds(495, 150, 116, 21);
		getContentPane().add(tfCclass);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("\uC0C9 \uC0C1");
		lblNewLabel_2_1_1.setBounds(425, 195, 57, 15);
		getContentPane().add(lblNewLabel_2_1_1);
		
		tfRtprice = new JTextField();
		tfRtprice.setEditable(false);
		tfRtprice.setColumns(10);
		tfRtprice.setBounds(495, 310, 116, 21);
		getContentPane().add(tfRtprice);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("\uC5F0 \uB8CC");
		lblNewLabel_2_1_2.setBounds(425, 235, 57, 15);
		getContentPane().add(lblNewLabel_2_1_2);
		
		tfCcolor = new JTextField();
		tfCcolor.setEditable(false);
		tfCcolor.setColumns(10);
		tfCcolor.setBounds(495, 190, 116, 21);
		getContentPane().add(tfCcolor);
		
		JLabel lblNewLabel_2_1_2_1 = new JLabel("\uAE30 \uC5B4");
		lblNewLabel_2_1_2_1.setBounds(425, 275, 57, 15);
		getContentPane().add(lblNewLabel_2_1_2_1);
		
		tfCoil = new JTextField();
		tfCoil.setEditable(false);
		tfCoil.setColumns(10);
		tfCoil.setBounds(495, 230, 116, 21);
		getContentPane().add(tfCoil);
		
		btnCarDelete = new JButton("\uC608\uC57D\uAC70\uBD80");
		btnCarDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnCarDelete.getText().equals("예약거부")) {
					ReservationDelete(tfRtnum.getText());
				} else if(btnCarDelete.getText().equals("예약하기")) {
					ReservationAdd();
				}
			}
		});
		btnCarDelete.setBounds(226, 436, 85, 30);
		getContentPane().add(btnCarDelete);
		
		JButton btnBack = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(341, 436, 85, 30);
		getContentPane().add(btnBack);
		
		lblRtnumOrRname = new JLabel("\uC608\uC57D\uBC88\uD638");
		lblRtnumOrRname.setBounds(30, 360, 57, 15);
		getContentPane().add(lblRtnumOrRname);
		
		tfRtnum = new JTextField();
		tfRtnum.setEditable(false);
		tfRtnum.setColumns(10);
		tfRtnum.setBounds(100, 355, 116, 21);
		getContentPane().add(tfRtnum);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\uC544 \uC774 \uB514");
		lblNewLabel_1_1_1.setBounds(30, 400, 57, 15);
		getContentPane().add(lblNewLabel_1_1_1);
		
		tfRid = new JTextField();
		tfRid.setEditable(false);
		tfRid.setColumns(10);
		tfRid.setBounds(100, 395, 116, 21);
		getContentPane().add(tfRid);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("\uC608 \uC57D \uC77C");
		lblNewLabel_1_1_2.setBounds(296, 359, 57, 15);
		getContentPane().add(lblNewLabel_1_1_2);
		
		tfRvdate = new JTextField();
		tfRvdate.setEditable(false);
		tfRvdate.setColumns(10);
		tfRvdate.setBounds(366, 354, 180, 21);
		getContentPane().add(tfRvdate);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("\uB300\uC5EC\uAE30\uAC04");
		lblNewLabel_1_1_1_1.setBounds(296, 399, 57, 15);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		tfRRdate = new JTextField();
		tfRRdate.setEditable(false);
		tfRRdate.setColumns(10);
		tfRRdate.setBounds(366, 394, 180, 21);
		getContentPane().add(tfRRdate);

	}

	protected void ReservationAdd() {
		
		int result = JOptionPane.showConfirmDialog(null, "예약 할까요??", "예약정보", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {	
			String sql = "";
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
		        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
					
				sql = "INSERT INTO rreservation (rtnum, rid, cindex, rvdate, rtdate, returndate, rpdate, rtprice) ";
				sql = sql + "VALUES (rreservation_seq.nextval, ?,?,?,?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, tfRid.getText());
				pstmt.setInt(2, Integer.parseInt(tfCindex.getText()));
				pstmt.setString(3, tfRvdate.getText());
				String[] parts = tfRRdate.getText().split("~");
				String startDate = parts[0].trim();
				String endDate = parts[1].trim();
				pstmt.setString(4, startDate);
				pstmt.setString(5, endDate);
				pstmt.setString(6, tfRRdate.getText());
				pstmt.setInt(7, Integer.parseInt(tfRtprice.getText()));
				int reservationok = pstmt.executeUpdate();
				if(reservationok == 1) {
					JOptionPane.showMessageDialog(null, "예약이 정상 처리되었습니다");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "예약이 실패하였습니다");
				}				
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}		
		}
	}

	protected void ReservationDelete(String rtnum) {
		
		int result = JOptionPane.showConfirmDialog(null, "해당 예약을 취소할까요?", "회원정보", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {		
			String sql = "";
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
		        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
					
				sql = "DELETE FROM rreservation WHERE rtnum = ?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, rtnum);
				int deleteok = pstmt.executeUpdate();
				if(deleteok == 1) {
					JOptionPane.showMessageDialog(null, "해당 예약을 거절하였습니다");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "예약 거절 싶패");
				}				
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public rentReservationDetail(String rtnum) {
		this();
		setTitle("선택하신 예약정보");
		ReservationDetail(rtnum);
	}

	private void ReservationDetail(String rtnum) {
		
		String sql = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
				
			sql = "select r.rid, c.cname, c.cbrend, c.cclass, c.cindex, r.rvdate, r.rtdate, r.returndate, ";
			sql = sql + "r.rtprice, c.ccolor, c.ctype, c.coil, c.cimg from rreservation r ";
			sql = sql + "inner join cartbl c on r.cindex = c.cindex where r.rtnum = ?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rtnum);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tfRtnum.setText(rtnum);
				tfRid.setText(rs.getString("rid"));
				tfCname.setText(rs.getString("cname"));
				tfCbrend.setText(rs.getString("cbrend"));
				tfCclass.setText(rs.getString("cclass"));
				tfCindex.setText(rs.getString("cindex"));
				tfRvdate.setText(rs.getString("rvdate").substring(0,10));
				tfRRdate.setText(rs.getString("rtdate").substring(0,10) + " ~ " + rs.getString("returndate").substring(0,10));				
				tfRtprice.setText(rs.getString("rtprice"));
				tfCcolor.setText(rs.getString("ccolor"));
				tfCtype.setText(rs.getString("ctype"));
				tfCoil.setText(rs.getString("coil"));
				String cimg = rs.getString("cimg");
		 		lblCarImg.setText("<html><img src='" + cimg + "' width='350' height='300'></html>");
			}
			
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public rentReservationDetail(String cindex, String sessionId, String UserName, String sDay, String eDay) {
		this();
		setTitle("선택하신 예약정보");
		btnCarDelete.setText("예약하기");
		lblRtnumOrRname.setText("예 약 자");
		tfRid.setText(sessionId);
		tfRtnum.setText(UserName);
		tfRvdate.setText(LocalDate.now().toString());
		tfRRdate.setText(sDay + " ~ " + eDay);
		ReservationCarDetail(cindex,sDay,eDay);

	}

	private void ReservationCarDetail(String cindex, String sDay, String eDay) {

		String sql = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
				
	        sql = "select * from cartbl where cindex = ?";			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cindex);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tfCindex.setText(rs.getString("cindex"));
				tfCbrend.setText(rs.getString("cbrend"));
				tfCname.setText(rs.getString("cname"));
				tfCclass.setText(rs.getString("cclass"));				
				tfCcolor.setText(rs.getString("ccolor"));
				tfCoil.setText(rs.getString("coil"));
				tfCtype.setText(rs.getString("ctype"));
				int price = Integer.parseInt(rs.getString("price"));
				LocalDate startDate = LocalDate.parse(sDay);
		        LocalDate endDate = LocalDate.parse(eDay);
				long daysDiff = ChronoUnit.DAYS.between(startDate, endDate);
				int totalprice = (int)daysDiff * price;
				tfRtprice.setText(totalprice+"");
				String cimg = rs.getString("cimg");
		 		lblCarImg.setText("<html><img src='" + cimg + "' width='350' height='300'></html>");
			}	
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}	
	
}
