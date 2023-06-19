package rentAdmin;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class rentCal extends JDialog {

	private JPanel panelCalendar;
	private JComboBox cbMonth;
	private JComboBox cbYear;
	private JButton btnNextMonth;
	private JButton btnPrevMonth;
	private JButton btnNextYear;
	private JButton btnPrevYear;
	
	private int gYear;
	private int gMonth;
	private int gDay;
	
	
	private String selectedDate;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rentCal dialog = new rentCal();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String getDate() {
		return selectedDate;
	}

	/**
	 * Create the dialog.
	 */
	public rentCal() {
		setTitle("달력");
		setBounds(100, 100, 450, 392);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		panelCalendar = new JPanel();
		getContentPane().add(panelCalendar, BorderLayout.CENTER);
		panelCalendar.setLayout(new GridLayout(0, 7, 2, 2));
		
		cbMonth = new JComboBox();
		cbMonth.setForeground(new Color(0, 0, 0));
		cbMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCalendar();
			}
		});
		
		btnPrevMonth = new JButton("<");
		btnPrevMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int year = Integer.parseInt(cbYear.getSelectedItem().toString());
				int month = Integer.parseInt(cbMonth.getSelectedItem().toString());
				month--;		
				if(month==0) {
					year--;
					month=12;
				}
				cbYear.setSelectedItem(year);
				cbMonth.setSelectedIndex(month-1);
				showCalendar();
			}
		});
		
		btnPrevYear = new JButton("<<");
		btnPrevYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int year = Integer.parseInt(cbYear.getSelectedItem().toString());
				
				year--;
				if(year == 2023)
					year=2023;
				
				cbYear.setSelectedItem(year);
				
				showCalendar();
			}
		});
		panel.add(btnPrevYear);
		panel.add(btnPrevMonth);
		
		cbYear = new JComboBox();
		cbYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCalendar();
			}
		});
		panel.add(cbYear);
		
		lblNewLabel = new JLabel("\uB144");
		panel.add(lblNewLabel);
		cbMonth.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		panel.add(cbMonth);
		
		btnNextMonth = new JButton(">");
		btnNextMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int year = Integer.parseInt(cbYear.getSelectedItem().toString());
				int month = Integer.parseInt(cbMonth.getSelectedItem().toString());
				month++;		
				if(month==13) {
					year++;
					month=1;
				}
				cbYear.setSelectedItem(year);
				cbMonth.setSelectedIndex(month-1);	
				
				showCalendar();
			}
		});
		
		lblNewLabel_1 = new JLabel("\uC6D4");
		panel.add(lblNewLabel_1);
		panel.add(btnNextMonth);
		
		btnNextYear = new JButton(">>");
		btnNextYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int year = Integer.parseInt(cbYear.getSelectedItem().toString());
				
				year++;
				if(year == 2123)
					year=2123;
				
				cbYear.setSelectedItem(year);
				
				showCalendar();
			}
		});
		panel.add(btnNextYear);

		for(int year=2023;year<=2025;year++)
			cbYear.addItem(year);
		
		// 현재 연도와 월
		Calendar today = Calendar.getInstance();		
		gYear = today.get(Calendar.YEAR);
		gMonth = today.get(Calendar.MONTH) + 1;
		gDay = today.get(Calendar.DAY_OF_MONTH);
		  
		cbYear.setSelectedItem(gYear);
		cbMonth.setSelectedItem(Integer.toString(gMonth));
	}

	protected void showCalendar() {

		Component []componentList = panelCalendar.getComponents();
		for(Component c: componentList) {
			if(c instanceof JButton )
				panelCalendar.remove(c);
		}
		panelCalendar.revalidate();
		panelCalendar.repaint();
		
		 String yoil = "일월화수목금토";
	      for(int i=0;i<yoil.length();i++) {
	    	 JButton btn = null;			
	         btn = new JButton(yoil.substring(i,i+1));
	         if(i == 0 || i == 6) {
	        	 btn.setBackground(new Color(250,0,0));	        	 
	         } else {
	        	 btn.setBackground(new Color(106,90,205));
	         }
	         btn.setFont(new Font("굴림", Font.BOLD, 16));
	         btn.setForeground(new Color(255,255,255));
	         panelCalendar.add(btn);
	      }

	    int Month[] = {31,28,31,30,31,30,31,31,30,31,30,31};
	    int year = Integer.parseInt(cbYear.getSelectedItem().toString());
		int month = Integer.parseInt(cbMonth.getSelectedItem().toString());
		int sum = 0;
		
		// 해당하는 전연도까지의 합을 구하시오.(1923.1.1~2022.12.31)
		for(int i=1923;i<year;i++) {
			if(i%4==0 && i%100 !=0 || i%400==0)
				sum = sum + 366;
			else
				sum = sum + 365;
		}
		// 해당하는 전월까지의 날짜 수의 합을 구하시오. 
		for(int i=0;i<month-1;i++) {
			if(i==1 && (year%4==0 && year%100!=0 || year%400==0))
				sum = sum + ++Month[i];
			else
				sum = sum + Month[i];
		}		
	    
	    // 1923년도 1월 1일의 시작은 월요일(1)부터 시작이다.
	      
	    int start = 1;
	    start = (start + sum ) % 7;
	    
	    for(int i=1;i<=start;i++) {  
			JButton btn = new JButton("");
			panelCalendar.add(btn);			
			btn.setVisible(false);
		}
	    
	      
		// 해당하는 달의 마지막 날짜까지 버튼을 생성한다.		
		int last = Month[month-1];
		if(month==2 &&  (year%4==0 && year%100!=0 || year%400==0) )
			last++;
		for(int i=1;i<=last;i++) {  // 1일부터 해당월의 마지막 날짜를 출력한다.
			JButton btn = new JButton(i + "");
			
			btn.addActionListener(new ActionListener() {				

				public void actionPerformed(ActionEvent e) {
					JButton btn1 = (JButton)e.getSource();
					selectedDate = year + "-" + (month < 10 ? "0"+month : month) + "-" + (btn1.getText().length() < 2 ? "0"+ btn1.getText() : btn1.getText());
					dispose();
				}
			});
			if(year == gYear && month == gMonth	&& i == gDay) {
				btn.setBackground(new Color(106,90,205));
			} else {
				btn.setBackground(new Color(230,230,250));
			}				
			panelCalendar.add(btn);			
			panelCalendar.revalidate();			
		}
	}

}
