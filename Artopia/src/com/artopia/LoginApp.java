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
import java.awt.Rectangle;

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
		
		new CommonUse().setOptionPaneFont();
		
		frmArtopia = new JFrame();
		frmArtopia.getContentPane().setBounds(new Rectangle(15, 15, 270, 270));
		frmArtopia.setResizable(false);
		frmArtopia.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginApp.class.getResource("/com/artopia/logo.png")));
		frmArtopia.setTitle("Artopia\u5B66\u751F\u4FE1\u606F\u7CFB\u7EDF");
		frmArtopia.setBounds(100, 100, 300, 300);
		frmArtopia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmArtopia.getContentPane().setLayout(new MigLayout("", "[100][150]", "[10][20px,grow][40][40][20][30,grow]"));
		
		JLabel lblTitle = new JLabel("Artopia\u5B66\u751F\u7CFB\u7EDF");
		lblTitle.setFont(new Font("微软雅黑", Font.BOLD, 18));
		frmArtopia.getContentPane().add(lblTitle, "cell 0 1 2 1,alignx center,aligny center");
		
		JLabel lblUsername = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblUsername.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		frmArtopia.getContentPane().add(lblUsername, "cell 0 2,alignx trailing");
		
		username = new JTextField();
		username.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		frmArtopia.getContentPane().add(username, "cell 1 2,alignx center");
		username.setColumns(20);
		frmArtopia.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{username, password}));
				
		JLabel lblPassword = new JLabel("\u5BC6\u7801\uFF1A");
		lblPassword.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		frmArtopia.getContentPane().add(lblPassword, "cell 0 3,alignx trailing");
		
		password = new JPasswordField();
		password.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		password.setColumns(20);
		frmArtopia.getContentPane().add(password, "cell 1 3,alignx center");
				
		JButton btnLogin = new JButton("\u767B\u5F55");
		btnLogin.setFont(new Font("微软雅黑", Font.PLAIN, 12));
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
		frmArtopia.getContentPane().add(btnLogin, "cell 0 5,alignx right");
		
		JButton btnRegiste = new JButton("\u6CE8\u518C");
		btnRegiste.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnRegiste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmArtopia.dispose();
				new RegisteFrame();
				
			}
		});
		frmArtopia.getContentPane().add(btnRegiste, "cell 1 5,alignx center");
	
		
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
		
		//
		frmArtopia.setVisible(true);
		// 设置初始位置
		frmArtopia.setLocationRelativeTo(null);
		// 设置当窗口关闭时，保证JVM也退出
		frmArtopia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmArtopia.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{username, password}));
	}
	
	public JTextField getUsername() {
		return username;
	}

	public LoginApp(String user) {
		// TODO Auto-generated constructor stub
		initialize();
		username.setText(user);
		username.selectAll();
						
	}




}
