package com.artopia;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JPasswordField;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;

public class RegisteFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel loginPane;
	private JTextField username;
	private JTextField mobile;
	private JSeparator sepStudent;
	private JLabel lblfname;
	private JTextField fname;
	private JLabel label;
	private JTextField lname;
	private JLabel lblClass;
	private JComboBox<Object> cbclass;
	private JButton registe;
	private JButton back;
	private JComboBox<String> cbStudents;
	private JCheckBox chbDisplayPwd;
	private JPasswordField password;
	private JPasswordField repwd;
	private JLabel lblUserInfo;
	private JSeparator sepUser;
	private JLabel label_1;

	List<String> list = new ArrayList<String>();
	private String[] students = null;
	
	
	boolean isNewStudentItemExist = true;
	private JLabel lblWeChat;
	private JTextField wechat;
	
	/**
	 * Create the frame.
	 */
	public RegisteFrame() {
		setResizable(false);
		
		new Functions().setOptionPaneFont();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisteFrame.class.getResource("/com/artopia/logo.png")));
		setTitle("\u521B\u5EFA\u65B0\u7528\u6237");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 435);
		loginPane = new JPanel();
		loginPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		setContentPane(loginPane);
		loginPane.setLayout(new MigLayout("", "[100][120px,grow]", "[][][grow][grow][grow][][grow][][][][][grow][grow][grow][][grow]"));
		
		lblUserInfo = new JLabel("\u4E2A\u4EBA\u4FE1\u606F");
		lblUserInfo.setFont(new Font("微软雅黑", Font.BOLD, 12));
		loginPane.add(lblUserInfo, "cell 0 0");
		
		sepUser = new JSeparator();
		loginPane.add(sepUser, "cell 0 1 2 1,growx");
		
		JLabel lblUserName = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblUserName.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		loginPane.add(lblUserName, "cell 0 2,alignx trailing");
		
		username = new JTextField();
		username.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		username.setToolTipText("\u8BF7\u8F93\u5165\u7528\u6237\u540D\uFF0C\u4EC5\u6570\u5B57\uFF0C\u5B57\u6BCD\u548C\u4E0B\u5212\u7EBF");
		loginPane.add(username, "cell 1 2,alignx leading");
		username.setColumns(20);
		
		JLabel lblPwd = new JLabel("\u5BC6\u7801\uFF1A");
		lblPwd.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		loginPane.add(lblPwd, "cell 0 3,alignx trailing");
		
		password = new JPasswordField();
		password.setToolTipText("\u8BF7\u8F93\u51656\u4F4D\u4EE5\u4E0A\u7684\u5BC6\u7801");
		password.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		password.setColumns(20);
		loginPane.add(password, "cell 1 3,alignx leading");
		
		JLabel lblRepwd = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		lblRepwd.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		loginPane.add(lblRepwd, "cell 0 4,alignx trailing");
		
		chbDisplayPwd = new JCheckBox("\u663E\u793A\u5BC6\u7801");
		chbDisplayPwd.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(chbDisplayPwd.isSelected()) {
					password.setEchoChar((char) 0);
					repwd.setEchoChar((char) 0);
				}
				else {
					password.setEchoChar('●');
					repwd.setEchoChar('●');
				}
			}
		});
		
		repwd = new JPasswordField();
		repwd.setToolTipText("\u8BF7\u8F93\u5165\u4E00\u6837\u7684\u5BC6\u7801");
		repwd.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		repwd.setColumns(20);
		loginPane.add(repwd, "cell 1 4,alignx leading");
		chbDisplayPwd.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		loginPane.add(chbDisplayPwd, "cell 0 5 2 1,alignx center");
		
		JLabel lblMobile = new JLabel("\u624B\u673A\u53F7\u7801\uFF1A");
		lblMobile.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		loginPane.add(lblMobile, "cell 0 6,alignx trailing");
		
		mobile = new JTextField();
		mobile.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		mobile.setToolTipText("\u8BF7\u8F93\u51659-11\u4F4D\u624B\u673A\u53F7\uFF0C\u4EC5\u6570\u5B57");
		loginPane.add(mobile, "cell 1 6,alignx leading");
		mobile.setColumns(20);
		
		lblWeChat = new JLabel("\u5FAE\u4FE1\u6635\u79F0\uFF1A");
		lblWeChat.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		loginPane.add(lblWeChat, "cell 0 7,alignx trailing");
		
		wechat = new JTextField();
		wechat.setToolTipText("\u4F60\u7684\u5FAE\u4FE1\u6635\u79F0\uFF0C\u6216\u8005\u4F60\u7528\u6765\u8054\u7CFB\u7684\u6635\u79F0");
		wechat.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		loginPane.add(wechat, "cell 1 7,alignx leading");
		wechat.setColumns(20);
		
		label_1 = new JLabel("\u5B66\u751F\u4FE1\u606F");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 12));
		loginPane.add(label_1, "cell 0 8");
		
		sepStudent = new JSeparator();
		loginPane.add(sepStudent, "cell 0 9 2 1,growx");
		
		cbStudents = new JComboBox<String>();
		

		
		cbStudents.setPreferredSize(new Dimension(14, 20));
		cbStudents.setMinimumSize(new Dimension(100, 20));
		cbStudents.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		cbStudents.setModel(new DefaultComboBoxModel<>(new String[] {"\u5B66\u751F1", "\u65B0\u5B66\u751F"}));
		cbStudents.setSelectedIndex(0);
		cbStudents.setEditable(true);
		cbStudents.setBounds(0, 0, 80, 20);
		loginPane.add(cbStudents, "cell 0 10,growx");
		
		
		
		lblfname = new JLabel("\u5B66\u751F\u7684\u540D\uFF1A");
		lblfname.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		loginPane.add(lblfname, "cell 0 11,alignx trailing");
		
		fname = new JTextField();
		fname.setToolTipText("\u5B66\u751F\u540D\uFF0C\u4EC5\u5B57\u6BCD");
		fname.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		loginPane.add(fname, "cell 1 11,alignx leading");
		fname.setColumns(20);
		
		label = new JLabel("\u5B66\u751F\u7684\u59D3\uFF1A");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		loginPane.add(label, "cell 0 12,alignx trailing");
		
		lname = new JTextField();
		lname.setToolTipText("\u5B66\u751F\u59D3\uFF0C\u4EC5\u5B57\u6BCD");
		lname.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		loginPane.add(lname, "cell 1 12,alignx leading");
		lname.setColumns(20);
		
		lblClass = new JLabel("\u5B66\u751F\u6240\u5B66\u7684\u8BFE\u7A0B\uFF1A");
		lblClass.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		loginPane.add(lblClass, "cell 0 13,alignx trailing");
		
		cbclass = new JComboBox<>();
		cbclass.setMaximumRowCount(20);
		cbclass.setModel(new DefaultComboBoxModel<>(new String[] {"\u6CA1\u6709\u53C2\u52A0\u7684\u8BFE\u7A0B"}));
		cbclass.setSelectedIndex(0);
		cbclass.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		
		new Functions().addItems(cbclass, new ArtClass().getAllClasses());
		
		loginPane.add(cbclass, "cell 1 13,growx");
		
		username.setName("用户名");
		password.setName("密码");
		repwd.setName("确认密码");
		fname.setName("学生名");
		lname.setName("学生姓");
		mobile.setName("手机号码");
		wechat.setName("微信昵称");
		
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
		fname.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				//只能输入字母
				Matcher matcher = Pattern.compile("^[A-Za-z]+$").matcher(String.valueOf(e.getKeyChar()));
				if(matcher.matches()) {
					
					//首字母大写
					if(fname.getText().toString().equals("")) {
						e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
					}
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
					//首字母大写
					if(lname.getText().toString().equals("")) {
						e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
					}
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
					wechat.requestFocus();
				}
			}
		});
		
		wechat.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					fname.requestFocus();
				}
			}
		});
		
		cbclass.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					registe.doClick();;
				}
			}
		});
		
		
		
		cbStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//目前有多少项
				int count= cbStudents.getItemCount();
				//目前选的第几行
				int index= cbStudents.getSelectedIndex();
				//判断上一次选择的是不是新学生
				
				//如果选的是最后一项，即“新学生”
				if(index == count -1) {
					if(isNewStudentItemExist) {
						if(!fname.getText().equals("")) {
							if(!lname.getText().equals("")) {
								
									list.add(fname.getText());
									list.add(lname.getText());
									list.add(cbclass.getSelectedItem().toString());
								
									//将现有学生存入student数组
									students =new Functions().getList(list);	
									//清除所有项目并添加存储的student信息	
									cbStudents.removeAllItems();
									for(int i=0;i<students.length/3;i++) {
									cbStudents.addItem(students[i*3]+" "+students[i*3+1].substring(0, 1));
									}
									//cbStudents.insertItemAt("学生"+count,index);
									cbStudents.addItem("学生"+count);
									cbStudents.addItem("新学生");
									cbStudents.setSelectedIndex(index);
							}
							else {
							
							JOptionPane.showMessageDialog(null, "学生姓为空！");
							cbStudents.setSelectedIndex(count-2);
							lname.requestFocus();
							return;
							}	
						}
						else {
								
							JOptionPane.showMessageDialog(null, "学生名为空！");
							cbStudents.setSelectedIndex(count-2);
							fname.requestFocus();
							return;		
						}	
					}
					else {
						if(!fname.getText().equals("")) {
							if(!lname.getText().equals("")) {
								cbStudents.insertItemAt("学生"+count,index);
								cbStudents.setSelectedIndex(cbStudents.getItemCount()-2);
								isNewStudentItemExist =true;
							}
						}
						fname.setText("");
						lname.setText("");
						return;
					}
					
					cbStudents.setSelectedIndex(cbStudents.getItemCount()-2);
					//充值学生信息框
					fname.setText("");
					lname.setText("");
					
					cbclass.setSelectedIndex(0);
					isNewStudentItemExist =true;
				}
				else if(index == count-2) {
					//存在"学生x"
					if(isNewStudentItemExist) {
						//学生x不为空
						if(!fname.getText().equals("")&&!lname.getText().equals("")) {
								list.add(fname.getText());
								list.add(lname.getText());
								list.add(cbclass.getSelectedItem().toString());
				
								//将现有学生存入student数组
								students =new Functions().getList(list);	
								//清除所有项目并添加存储的student信息	
								cbStudents.removeAllItems();
					
								for(int i=0;i<students.length/3;i++) {
									cbStudents.addItem(students[i*3]+" "+students[i*3+1].substring(0, 1));
								}
					
								cbStudents.addItem("新学生");
								cbStudents.setSelectedIndex(index);
								isNewStudentItemExist = false;
							
						}
						//学生x信息不完整，退出事件
						return;
					}
					
					//不存在学生x
					else {
						fname.setText(students[index*3]);
						lname.setText(students[index*3+1]);
					}
					//isLastItemisNew = false;
				}
				else {
					
						if(isNewStudentItemExist) {
							//学生x不为空
							if(!fname.getText().equals("")&&!lname.getText().equals("")) {
								
									list.add(fname.getText());
									list.add(lname.getText());
									list.add(cbclass.getSelectedItem().toString());
			
									//将现有学生存入student数组
									students =new Functions().getList(list);
							
									cbStudents.removeAllItems();
									for(int i=0;i<students.length/3;i++) {
										cbStudents.addItem(students[i*3]+" "+students[i*3+1].substring(0, 1));
									}
									cbStudents.addItem("新学生");	
							}
							//学生x信息不完整，删除学生x
							fname.setText(students[index*3]);
							lname.setText(students[index*3+1]);
							cbStudents.removeItemAt(count-2);
						
						}
						else {
							//存在项目之间切换
							fname.setText(students[index*3]);
							lname.setText(students[index*3+1]);
							return;
						}
					isNewStudentItemExist = false;
				}
			}
			
		});
		registe = new JButton("\u6CE8\u518C");
		registe.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		loginPane.add(registe, "cell 0 15,alignx trailing");
		
		back = new JButton("\u8FD4\u56DE");
		back.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		loginPane.add(back, "cell 1 15,alignx center");
		
		registe.addActionListener(this);
		back.addActionListener(this);
		
		registe.setActionCommand("Registe");
		back.setActionCommand("Back");
		
		//窗口位置在屏幕中央
		this.setLocationRelativeTo(null);
		// 设置当窗口关闭时，保证JVM也退出
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{username, password, repwd, mobile, wechat, fname, lname}));
		//显示窗体
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		//判断是哪个按钮被点击
		if(e.getActionCommand().equals("Registe")){
			Component[] components = loginPane.getComponents();
			boolean allfilled = true;

			//判断是否输入为空
			for (Component component : components) {
				if(component instanceof JPasswordField) {
					if(String.valueOf(((JPasswordField) component).getPassword()).equals("")) {
						
						JOptionPane.showMessageDialog(null, component.getName()+" 为空!");
						component.requestFocus();
						allfilled = false;
						break;
					}
				}
				else if(component instanceof JTextField)
				{
					if(((JTextField) component).getText().trim().equals("")) {
						
						//如果已经students已经存在学生姓名
						if(component.getName().equals("学生名")||component.getName().equals("学生姓")) {
							if(students == null) {
								JOptionPane.showMessageDialog(null, component.getName()+" 为空!");
								component.requestFocus();
								allfilled = false;
								break;
							}
						}
						else {
							JOptionPane.showMessageDialog(null, component.getName()+" 为空!");
							component.requestFocus();
							allfilled = false;
							break;
						}
						
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
								account.createAccount(username.getText(), String.valueOf(password.getPassword()),mobile.getText(),wechat.getText());
								
								//写入学生信息,如果有多个学生
								if(students !=null) {
									for(int i=0;i<students.length/3;i++) {
										new Student().addStudent(account.getNewUserID(),students[i*3+1],students[i*3],students[i*3+2]);
									}
									if(!lname.getText().equals("")&&!fname.getText().equals("")) {
										new Student().addStudent(account.getNewUserID(),lname.getText(),fname.getText(),cbclass.getSelectedItem().toString());
									}
									else {
										JOptionPane.showMessageDialog(null, cbStudents.getSelectedItem().toString()+"的信息不完整，将不存入系统!");
									}
								}
								else {
									new Student().addStudent(account.getNewUserID(),lname.getText(),fname.getText(),cbclass.getSelectedItem().toString());
								}
								JOptionPane.showMessageDialog(null, "注册成功!");
								String user = username.getText();
							
								this.dispose();								
								new LoginApp(user);	
							}
							else {
								JOptionPane.showMessageDialog(null, "用户名已存在!");
								username.selectAll();
								username.requestFocus();
							}
						} 
						else {
						
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
			new LoginApp();
			
			
		}
	}
}
