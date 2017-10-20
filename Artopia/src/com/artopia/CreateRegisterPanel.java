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
		
		create = new JButton("����");
		back = new JButton("����");
		newstudent= new JButton("��ѧ��");
		
		create.addActionListener(this);
		back.addActionListener(this);
		newstudent.addActionListener(this);
		
		create.setActionCommand("Create");
		back.setActionCommand("Back");
		newstudent.setActionCommand("New");
		
		
		jlusername = new JLabel("�û���:");
		jlpassword = new JLabel("����:");
		jlrepwd = new JLabel("�ٴ�ȷ������:");
		jlfname = new JLabel("ѧ������:");
		jllname = new JLabel("ѧ������:");
		jlmobile = new JLabel("�ֻ�����:");
		jlclass = new JLabel("�������ڵİ༶:");
		
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
		cbclass.addItem("δ����༶");
		artClass = new ArtClass();
		String[] classnames = artClass.getAllClasses();
		new CommonUse().addItems(cbclass, classnames);
		
		username.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				//ֻ���������֣���ĸ���»���
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
				//ֻ��������ĸ
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
				//ֻ��������ĸ
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
				//ֻ����������
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
		
		// ���ô�С�������ؼ���
		setSize(300, 300);
		// ���ó�ʼλ��
		setLocationRelativeTo(null);
		// ���õ����ڹر�ʱ����֤JVMҲ�˳�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ��ʾ
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		//�ж����ĸ���ť�����
		if(e.getActionCommand().equals("Create")){
			Component[] components = jptext.getComponents();
			boolean allfilled = true;

			//�ж��Ƿ�����Ϊ��
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
				//���볤�ȳ���6
				if(String.valueOf(password.getPassword()).length() >= 6) {
					//2�����������ͬ
					if(String.valueOf(password.getPassword()).equals(String.valueOf(repwd.getPassword()))) {
						//�绰���볤����9-11λ֮��
						if (mobile.getText().trim().length()>8 && mobile.getText().trim().length()<12) {
							Account account = new Account();
	
							if(!account.isDuplicate(username.getText())){
							account.createAccount(username.getText(), String.valueOf(password.getPassword()),mobile.getText());
							JOptionPane.showMessageDialog(null, "ע��ɹ�!");
							//д��ѧ����Ϣ,����ж��ѧ��
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
								JOptionPane.showMessageDialog(null, "�û����Ѵ���!");
								username.selectAll();
								username.requestFocus();
							}
						} 
						else {
						//�û����Ƿ��ظ�
						JOptionPane.showMessageDialog(null, "�绰���������9-11λ֮��");
						mobile.selectAll();
						mobile.requestFocus();
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "���벻ƥ�䣡");
						repwd.selectAll();
						repwd.requestFocus();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "������볬��6λ��");
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
					JOptionPane.showMessageDialog(null, "ѧ����Ϊ�գ�");
					lname.requestFocus();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "ѧ����Ϊ�գ�");
				fname.requestFocus();
				
			}
		}
	}
	
}
