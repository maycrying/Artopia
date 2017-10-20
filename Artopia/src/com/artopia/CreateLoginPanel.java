package com.artopia;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;


public class CreateLoginPanel extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2775085425178834816L;
	
	JButton login,register;
	JPanel jpusername,jppassowrd,jpbutton;
	JLabel jlusername,jlpassword;
	JTextField username;
	JPasswordField password;
	
	public CreateLoginPanel() {
		
		jpusername = new JPanel();
		jppassowrd = new JPanel();
		jpbutton = new JPanel();
		
		login = new JButton("Login");
		register = new JButton("Register");
		
		jlusername = new JLabel("Username:");
		jlpassword = new JLabel("Passowrd:");
		
		username = new JTextField(10);
		username.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					password.requestFocus();
				}
			}
		});
		password = new JPasswordField(10);
		password.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					login.doClick();;
				}
			}
		});
		setLayout(new GridLayout(3,1));
		
		jpusername.add(jlusername);
		jpusername.add(username);
		
		jppassowrd.add(jlpassword);
		jppassowrd.add(password);
		
		jpbutton.add(login);
		jpbutton.add(register);
		
		login.addActionListener(this);
		register.addActionListener(this);
		
		login.setActionCommand("Login");
		register.setActionCommand("Register");
		
		add(jpusername);
		add(jppassowrd);
		add(jpbutton);
		
		setTitle("Artopia");
		
		// 设置大小，按像素计算  width,height
		setSize(400, 400);
		// 设置初始位置
		setLocationRelativeTo(null);
		// 设置当窗口关闭时，保证JVM也退出
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 显示
		setVisible(true);
		
		}
	
		public JTextField getUsername() {
		return username;
	}

		public CreateLoginPanel(String user) {
		// TODO Auto-generated constructor stub
			username = new CreateLoginPanel().getUsername();
			username.setText(user);
			username.selectAll();
						
	}


		public void actionPerformed(ActionEvent e){
				//判断是哪个按钮被点击
				if(e.getActionCommand().equals("Login")){
					if(!username.getText().trim().equals("") ) {
						if(!String.valueOf(password.getPassword()).equals("")) {
							try {
								Account account = new Account();
								int uid = account.getUserID(username.getText(), String.valueOf(password.getPassword()));
								if(uid!=0) {
									this.dispose();
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
				else if(e.getActionCommand().equals("Register")){
					this.dispose();
					new CreateRegisterPanel();
					
				}
				
		}


}
