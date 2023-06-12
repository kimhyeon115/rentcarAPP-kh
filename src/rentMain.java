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
import javax.swing.JPopupMenu;
import java.awt.Component;

public class rentMain extends JDialog {
	public static JTable table;

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

	protected int listtype;
	protected static String keyword;

	/**
	 * Create the dialog.
	 */
	public rentMain() {
		setTitle("\uB80C\uD2B8\uCE74 \uC608\uC57D \uD504\uB85C\uADF8\uB7A8 (\uAD00\uB9AC\uC790)");
		setBounds(100, 100, 850, 550);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setFillsViewportHeight(true);

		scrollPane.setViewportView(table);		
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		JMenuItem mntmNewMenuItem_15 = new JMenuItem("\uC815\uBCF4 \uBCF4\uAE30");
		mntmNewMenuItem_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rentCarDetail carDetail = new rentCarDetail(listtype,keyword);
				carDetail.setModal(true);
				carDetail.setVisible(true);
			}
		});
		popupMenu.add(mntmNewMenuItem_15);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\uD68C\uC6D0\uAD00\uB9AC");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("\uD68C\uC6D0\uC815\uBCF4 \uAC80\uC0C9");
		mnNewMenu.add(mntmNewMenuItem_9);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("\uD68C\uC6D0\uC815\uBCF4 \uBAA9\uB85D");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listtype = 1;
				rentMethod.MemberList();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_10);
		
		JMenu mnNewMenu_1 = new JMenu("\uCC28\uB7C9\uAD00\uB9AC");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("\uCC28\uB7C9\uC815\uBCF4 \uAC80\uC0C9");
		mnNewMenu_1.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\uCC28\uB7C9\uC815\uBCF4 \uB4F1\uB85D");
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\uCC28\uB7C9\uC815\uBCF4 \uC218\uC815");
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("\uCC28\uB7C9\uC815\uBCF4 \uC0AD\uC81C");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JSeparator separator = new JSeparator();
		mnNewMenu_1.add(separator);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\uCC28\uB7C9\uC815\uBCF4 \uBAA9\uB85D");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listtype = 2;
				rentMethod.CarList();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_2 = new JMenu("\uC608\uC57D\uAD00\uB9AC");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("\uC608\uC57D\uCC28\uB7C9 \uAC80\uC0C9");
		mnNewMenu_2.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("\uC608\uC57D\uCC28\uB7C9 \uBAA9\uB85D");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listtype = 3;
				rentMethod.ReservationList();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_4);
		
		JSeparator separator_1 = new JSeparator();
		mnNewMenu_2.add(separator_1);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("\uC608\uC57D\uD68C\uC6D0 \uAC80\uC0C9");
		mnNewMenu_2.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("\uC608\uC57D\uD68C\uC6D0 \uBAA9\uB85D");
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_3 = new JMenu("Help");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("\uD68C\uC0AC\uC815\uBCF4");
		mnNewMenu_3.add(mntmNewMenuItem_11);
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
					
					if(!source.isRowSelected(row))
						source.changeSelection(row, column, false, false);
					
					keyword = source.getValueAt(row, 0).toString();
					showMenu(e);					
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
