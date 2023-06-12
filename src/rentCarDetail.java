import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class rentCarDetail extends JDialog {
	private JTextField tfCarNum;
	private JTextField tfCarBrend;
	private JTextField tfCarName;
	private JTextField tfRentPrice;
	private JTextField tfDetail;

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
		setBounds(100, 100, 900, 500);
		getContentPane().setLayout(null);
		
		JLabel lblCarImg = new JLabel("\uCC28\uB7C9 \uC774\uBBF8\uC9C0");
		lblCarImg.setOpaque(true);
		lblCarImg.setBackground(new Color(255, 255, 255));
		lblCarImg.setBounds(35, 30, 300, 300);
		getContentPane().add(lblCarImg);
		
		JLabel lblNewLabel_1 = new JLabel("\uCC28\uB7C9\uBC88\uD638");
		lblNewLabel_1.setBounds(408, 30, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uBE0C\uB79C\uB4DC");
		lblNewLabel_2.setBounds(408, 70, 57, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uCC28\uB7C9\uBA85\uCE6D");
		lblNewLabel_3.setBounds(408, 115, 57, 15);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\uB300\uC5EC\uB8CC/\uC77C");
		lblNewLabel_4.setBounds(408, 162, 57, 15);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\uCC28\uB7C9 \uC815\uBCF4");
		lblNewLabel_5.setBounds(408, 214, 57, 15);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("\uBE44\uACE0");
		lblNewLabel_6.setBounds(408, 265, 57, 15);
		getContentPane().add(lblNewLabel_6);
		
		tfCarNum = new JTextField();
		tfCarNum.setBounds(531, 30, 116, 21);
		getContentPane().add(tfCarNum);
		tfCarNum.setColumns(10);
		
		tfCarBrend = new JTextField();
		tfCarBrend.setBounds(531, 67, 116, 21);
		getContentPane().add(tfCarBrend);
		tfCarBrend.setColumns(10);
		
		tfCarName = new JTextField();
		tfCarName.setBounds(531, 112, 116, 21);
		getContentPane().add(tfCarName);
		tfCarName.setColumns(10);
		
		tfRentPrice = new JTextField();
		tfRentPrice.setBounds(531, 159, 116, 21);
		getContentPane().add(tfRentPrice);
		tfRentPrice.setColumns(10);
		
		tfDetail = new JTextField();
		tfDetail.setBounds(531, 211, 238, 21);
		getContentPane().add(tfDetail);
		tfDetail.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(531, 265, 320, 136);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		JTextArea tfOther = new JTextArea();
		tfOther.setLineWrap(true);
		scrollPane.setViewportView(tfOther);
	}
	
	public rentCarDetail(int listtype, String keyword) {
		this();
		Vector<String> vector = rentMethod.information(listtype,keyword);
		tfCarNum.setText(vector.get(1));
	}
}
