package com.artopia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class UserFrame {

	private JFrame frmUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserFrame window = new UserFrame();
					window.frmUser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUser = new JFrame();
		frmUser.setTitle("\u7528\u6237\u4E2D\u5FC3");
		frmUser.setBounds(100, 100, 450, 300);
		frmUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUser.getContentPane().setLayout(new MigLayout("", "[]", "[]"));
		
		JMenuBar menuBar = new JMenuBar();
		frmUser.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u5B66\u751F");
		menuBar.add(menu);
		
		JMenuItem miAddDel = new JMenuItem("\u6DFB\u52A0/\u5220\u9664\u5B66\u751F");
		menu.add(miAddDel);
		
		JMenuItem miBook = new JMenuItem("\u8BA2\u8BFE\u4FE1\u606F");
		menu.add(miBook);
		
		JMenuItem miRecord = new JMenuItem("\u4E0A\u8BFE\u660E\u7EC6");
		menu.add(miRecord);
		
		JMenu menu_1 = new JMenu("\u4E2A\u4EBA\u8BBE\u7F6E");
		menuBar.add(menu_1);
		
		JMenuItem miPassword = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		menu_1.add(miPassword);
		
		JMenuItem miPersonal = new JMenuItem("\u4FEE\u6539\u4E2A\u4EBA\u4FE1\u606F");
		menu_1.add(miPersonal);
	}

}
