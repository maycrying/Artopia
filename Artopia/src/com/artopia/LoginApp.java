package com.artopia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginApp {

	private JFrame frmArtopia;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginApp window = new LoginApp();
					window.frmArtopia.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmArtopia = new JFrame();
		frmArtopia.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginApp.class.getResource("/com/artopia/logo.png")));
		frmArtopia.setTitle("Artopia\u5B66\u751F\u4FE1\u606F\u7CFB\u7EDF");
		frmArtopia.setBounds(100, 100, 450, 300);
		frmArtopia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmArtopia.getContentPane().setLayout(new MigLayout("", "[100][100,grow 20][100,grow 20][100]", "[10][40px,grow][20][30][20][30,grow][20][30,grow][20]"));
		
		JLabel lblTitle = new JLabel("Artopia\u5B66\u751F\u7CFB\u7EDF");
		lblTitle.setFont(new Font("微软雅黑", Font.BOLD, 18));
		frmArtopia.getContentPane().add(lblTitle, "cell 1 1 2 1,alignx center,aligny center");
		
		JLabel lblUsername = new JLabel("\u7528\u6237\u540D\uFF1A");
		frmArtopia.getContentPane().add(lblUsername, "cell 1 3,alignx left");
		
		username = new JTextField();
		frmArtopia.getContentPane().add(username, "cell 2 3,alignx center");
		username.setColumns(20);
		frmArtopia.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{username, password}));
				
		JLabel lblPassword = new JLabel("\u5BC6\u7801\uFF1A");
		frmArtopia.getContentPane().add(lblPassword, "cell 1 5,alignx left");
		
		password = new JPasswordField();
		password.setColumns(20);
		frmArtopia.getContentPane().add(password, "cell 2 5,alignx center");
				
		JButton btnLogin = new JButton("\u767B\u5F55");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!username.getText().trim().equals("") ) {
					if(!String.valueOf(password.getPassword()).equals("")) {
						try {
							Account account = new Account();
							int uid = account.getUserID(username.getText(), String.valueOf(password.getPassword()));
							if(uid!=0) {
								frmArtopia.dispose();
								new CreateUserPanel(uid);
							}
							else {
								JOptionPane.showMessageDialog(null, "用户名或者密码不正确！");
								password.selectAll();
								password.requestFocus();
							}
						}
						catch (Exception exp) {
							// TODO Auto-generated catch block
							exp.printStackTrace();
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "密码不能为空！");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "用户名不能为空！");
				}
			}
		});
		frmArtopia.getContentPane().add(btnLogin, "cell 1 7,alignx center");
		
		JButton btnRegiste = new JButton("\u6CE8\u518C");
		btnRegiste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmArtopia.dispose();
				new CreateRegisterPanel();
				
			}
		});
		frmArtopia.getContentPane().add(btnRegiste, "cell 2 7,alignx center");
	
		
		//按回车跳转passowrd
		username.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					password.requestFocus();
				}
			}
		});
				
		//按回车，点击登录
		password.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						if(e.getKeyCode() == KeyEvent.VK_ENTER) {
							btnLogin.doClick();;
						}
					}
		});
		
		// 设置初始位置
		frmArtopia.setLocationRelativeTo(null);
		// 设置当窗口关闭时，保证JVM也退出
		frmArtopia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JTextField getUsername() {
		return username;
	}

	public LoginApp(String user) {
		// TODO Auto-generated constructor stub
		username = new CreateLoginPanel().getUsername();
		username.setText(user);
		username.selectAll();
						
	}




}
