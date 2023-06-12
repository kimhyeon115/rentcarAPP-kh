import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.PreparableStatement;

public class rentMethod {

	public static DefaultTableModel dtm;
		
	public static void CarList() {

		String[] columns = {"차량번호","브랜드","등급","명칭","색상","유종","타입","대여료/일","누적대여일수"};
		dtm = (DefaultTableModel)rentMain.table.getModel();
		dtm.setColumnIdentifiers(columns);
		dtm.setRowCount(0);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
			Statement stmt = con.createStatement();
			
			String sql = "select c.cindex, c.cbrend, c.cclass, c.cname, c.ccolor, c.coil, c.ctype, c.price, NVL(r.pdate, 0) as pdate ";
			sql = sql + "from cartbl c ";
			sql = sql + "FULL OUTER JOIN (select cindex,sum(returndate - rtdate) as pdate from rreservation group by cindex) r ";
			sql = sql + "on c.cindex = r.cindex order by pdate * c.price desc";
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) {
				Vector<String> vector = new Vector<>();
				for(int i=1; i<=9; i++)
					vector.add(rs.getString(i));
				dtm.addRow(vector);
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void ReservationList() {

		String[] columns = {"예약번호","예약일","차량번호","브랜드","등급","명칭","대여일","반납일","대여비용"};
		dtm = (DefaultTableModel)rentMain.table.getModel();
		dtm.setColumnIdentifiers(columns);		
		dtm.setRowCount(0);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
			Statement stmt = con.createStatement();
			
			String sql = "select r.rtnum, r.rvdate, c.cindex, c.cbrend, c.cclass, c.cname, r.rtdate, r.returndate, r.rtprice ";
			sql = sql + "from rreservation r inner join cartbl c on r.cindex = c.cindex order by r.rvdate desc";
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) {
				Vector<String> vector = new Vector<>();
				int i;
				for(i=1; i<=9; i++)
					if(i==2 || i==7 || i==8) {
						vector.add(rs.getString(i).substring(0,10));
					} else {					
						vector.add(rs.getString(i));
					}
				dtm.addRow(vector);
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void MemberList() {
		
		String[] columns = {"아이디","이름","연락처","이메일","가입일"};
		dtm = (DefaultTableModel)rentMain.table.getModel();
		dtm.setColumnIdentifiers(columns);		
		dtm.setRowCount(0);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
			Statement stmt = con.createStatement();
			
			String sql = "select rid, rname, rmobile, remail, rdate from rmember order by rdate desc";
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) {
				Vector<String> vector = new Vector<>();
				int i;
				for(i=1; i<=5; i++)
					if(i==3) {
						String rmobile = rs.getString(i);
						rmobile = rmobile.substring(0,3) + "-" + rmobile.substring(3,7) + "-" + rmobile.substring(7,11);
						vector.add(rmobile);
					} else if(i==5) {
						vector.add(rs.getString(i).substring(0,10));
					} else {
						vector.add(rs.getString(i));
					}
				dtm.addRow(vector);
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static Vector<String> information(int listtype, String keyword) {
		
		Vector<String> vector = new Vector<>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
			
			String sql="";
			if(listtype == 1) {
				sql = "select * from rmember where rid=?";
			} else if(listtype == 2) {
				sql = "select * from cartbl where cindex=?";
			} else if(listtype == 3) {
				sql = "select * from rreservation where rtnum=?";
			}			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {				
				for(int i=0; i<=10; i++) {
					vector.add(rs.getString(i));
				}
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		return vector;
	}
}
