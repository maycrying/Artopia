package com.artopia;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javax.swing.*;


public class CreateRegisterPanel extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7715411245312075933L;
	JButton create,back,newstudent;
	JPanel jpup,jpbutton,jplabel,jptext;
	JLabel jlusername,jlpassword,jlrepwd,jlfname,jllname,jlmobile,jlclass;
	JTextField username,fname,lname,mobile;
	JPasswordField password,repwd;
	JComboBox<Object> cbclass;
	ArtClass artClass;
	
	Student student;
	List<String> list = new ArrayList<String>();
	String[] students = null;
	
	public CreateRegisterPanel() {
		
		
		jpup = new JPanel();
		jplabel = new JPanel();
		jptext = new JPanel();
		jpbutton = new JPanel();
		
		create = new JButton("创建");
		back = new JButton("返回");
		newstudent= new JButton("新学生");
		
		create.addActionListener(this);
		back.addActionListener(this);
		newstudent.addActionListener(this);
		
		create.setActionCommand("Create");
		back.setActionCommand("Back");
		newstudent.setActionCommand("New");
		
		
		jlusername = new JLabel("用户名:");
		jlpassword = new JLabel("密码:");
		jlrepwd = new JLabel("再次确认密码:");
		jlfname = new JLabel("学生的名:");
		jllname = new JLabel("学生的姓:");
		jlmobile = new JLabel("手机号码:");
		jlclass = new JLabel("孩子所在的班级:");
		
		username = new JTextField(10);
		username.setName("User Name");
		username.requestFocus();
		password = new JPasswordField(10);
		password.setName("Password");
		repwd = new JPasswordField(10);
		repwd.setName("Confirmed Password");
		fname = new JTextField(10);
		fname.setName("First name");
		lname = new JTextField(10);
		lname.setName("Last name");
		mobile = new JTextField(10);
		mobile.setName("Mobile number");
		
		cbclass = new JComboBox<>();
		cbclass.addItem("未分配班级");
		artClass = new ArtClass();
		String[] classnames = artClass.getAllClasses();
		new CommonUse().addItems(cbclass, classnames);
		
		username.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				//只能输入数字，字母和下划线
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					password.requestFocus();
				}
				Matcher matcher = Pattern.compile("^\\w+$").matcher(String.valueOf(e.getKeyChar()));
				if(matcher.matches()) {
					return;
				}
				e.consume();
			}
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					password.requestFocus();
				}
			}
		});
		
		password.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					repwd.requestFocus();
				}
			}
			
		});
		
		repwd.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					fname.requestFocus();
				}
			}
			
		});
		fname.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				//只能输入字母
				Matcher matcher = Pattern.compile("^[A-Za-z]+$").matcher(String.valueOf(e.getKeyChar()));
				if(matcher.matches()) {
					return;
				}
				e.consume();
			}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					lname.requestFocus();
				}
			}
		});
		
		lname.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				//只能输入字母
				Matcher matcher = Pattern.compile("^[A-Za-z]+$").matcher(String.valueOf(e.getKeyChar()));
				if(matcher.matches()) {
					return;
				}
				e.consume();
			}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					mobile.requestFocus();
				}
			}
		});
		
		mobile.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				//只能输入数字
				Matcher matcher = Pattern.compile("^[0-9]$").matcher(String.valueOf(e.getKeyChar()));
				
				if(matcher.matches()) {
					return;				
				}
				e.consume();
			}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					cbclass.requestFocus();
				}
			}
		});
		
		cbclass.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					create.doClick();;
				}
			}
		});
		//col,row
		setLayout(new FlowLayout());
		
		jpup.add(jplabel);
		jplabel.setLayout(new GridLayout(0, 1,0,10));
		jplabel.add(jlusername);
		jplabel.add(jlpassword);
		jplabel.add(jlrepwd);
		jplabel.add(jlmobile);
		jplabel.add(jlfname);
		jplabel.add(jllname);
		
		jplabel.add(jlclass);
		
		jpup.add(jptext);
		jptext.setLayout(new GridLayout(0, 1));
		jptext.add(username);
		jptext.add(password);
		jptext.add(repwd);
		jptext.add(mobile);
		jptext.add(fname);
		jptext.add(lname);
		
		jptext.add(cbclass);
		
		jpbutton.add(newstudent);
		jpbutton.add(create);
		jpbutton.add(back);
		
		
		
		add(jpup);
		add(jpbutton);
		
		setTitle("Register");
		
		// 设置大小，按像素计算
		setSize(300, 300);
		// 设置初始位置
		setLocationRelativeTo(null);
		// 设置当窗口关闭时，保证JVM也退出
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 显示
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		//判断是哪个按钮被点击
		if(e.getActionCommand().equals("Create")){
			Component[] components = jptext.getComponents();
			boolean allfilled = true;

			//判断是否输入为空
			for (Component component : components) {
				if(component instanceof JPasswordField) {
					if(String.valueOf(((JPasswordField) component).getPassword()).equals("")) {
						
						JOptionPane.showMessageDialog(null, component.getName()+" is empty!");
						component.requestFocus();
						allfilled = false;
						break;
					}
				}
				else if(component instanceof JTextField)
				{
					if(((JTextField) component).getText().trim().equals("")) {
						
						JOptionPane.showMessageDialog(null, component.getName()+" is empty!");
						component.requestFocus();
						allfilled = false;
						break;
					}

				}
			}
			
			if(allfilled) {
				//密码长度超过6
				if(String.valueOf(password.getPassword()).length() >= 6) {
					//2次密码必须相同
					if(String.valueOf(password.getPassword()).equals(String.valueOf(repwd.getPassword()))) {
						//电话号码长度在9-11位之间
						if (mobile.getText().trim().length()>8 && mobile.getText().trim().length()<12) {
							Account account = new Account();
	
							if(!account.isDuplicate(username.getText())){
							account.createAccount(username.getText(), String.valueOf(password.getPassword()),mobile.getText());
							JOptionPane.showMessageDialog(null, "注册成功!");
							//写入学生信息,如果有多个学生
							if(students !=null) {
								for(int i=0;i<students.length/3;i++) {
									new Student().addStudent(account.getNewUserID(),students[i+1],students[i],students[i+2]);
								}
							}
							
							new Student().addStudent(account.getNewUserID(),lname.getText(),fname.getText(),cbclass.getSelectedItem().toString());
							
							
							String user = username.getText();
							
							this.dispose();								
							new CreateLoginPanel(user);	
							}
							else {
								JOptionPane.showMessageDialog(null, "用户名已存在!");
								username.selectAll();
								username.requestFocus();
							}
						} 
						else {
						//用户名是否重复
						JOptionPane.showMessageDialog(null, "电话号码必须在9-11位之间");
						mobile.selectAll();
						mobile.requestFocus();
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "密码不匹配！");
						repwd.selectAll();
						repwd.requestFocus();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "密码必须超过6位！");
					password.selectAll();
					password.requestFocus();
				}
			}
			
		} 
		else if(e.getActionCommand().equals("Back")){
			this.dispose();
			new CreateLoginPanel();
			
			
		}
		else if(e.getActionCommand().equals("New")) {
			if(!fname.getText().equals("")) {
				if(!lname.getText().equals("")) {
					
					list.add(fname.getText());
					list.add(lname.getText());
					list.add(cbclass.getSelectedItem().toString());
					students =new CommonUse().getList(list);
					
					fname.setText("");
					lname.setText("");
					cbclass.setSelectedIndex(0);
				}
				else {
					JOptionPane.showMessageDialog(null, "学生姓为空！");
					lname.requestFocus();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "学生名为空！");
				fname.requestFocus();
				
			}
		}
	}
	
}
