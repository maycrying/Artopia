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
	
	
	boolean isLastItemisNew = true;
	
	/**
	 * Create the frame.
	 */
	public RegisteFrame() {
		
		new CommonUse().setOptionPaneFont();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisteFrame.class.getResource("/com/artopia/logo.png")));
		setTitle("\u521B\u5EFA\u65B0\u7528\u6237");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 415);
		loginPane = new JPanel();
		loginPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		setContentPane(loginPane);
		loginPane.setLayout(new MigLayout("", "[100][120px,grow]", "[][][grow][grow][grow][][grow][][][][grow][grow][grow][][grow]"));
		
		lblUserInfo = new JLabel("\u4E2A\u4EBA\u4FE1\u606F");
		lblUserInfo.setFont(new Font("΢���ź�", Font.BOLD, 12));
		loginPane.add(lblUserInfo, "cell 0 0");
		
		sepUser = new JSeparator();
		loginPane.add(sepUser, "cell 0 1 2 1,growx");
		
		JLabel lblUserName = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblUserName.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		loginPane.add(lblUserName, "cell 0 2,alignx trailing");
		
		username = new JTextField();
		username.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		username.setToolTipText("\u8BF7\u8F93\u5165\u7528\u6237\u540D\uFF0C\u4EC5\u6570\u5B57\uFF0C\u5B57\u6BCD\u548C\u4E0B\u5212\u7EBF");
		loginPane.add(username, "cell 1 2,alignx leading");
		username.setColumns(20);
		
		JLabel lblPwd = new JLabel("\u5BC6\u7801\uFF1A");
		lblPwd.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		loginPane.add(lblPwd, "cell 0 3,alignx trailing");
		
		password = new JPasswordField();
		password.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		password.setColumns(20);
		loginPane.add(password, "cell 1 3,alignx leading");
		
		JLabel lblRepwd = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		lblRepwd.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		loginPane.add(lblRepwd, "cell 0 4,alignx trailing");
		
		chbDisplayPwd = new JCheckBox("\u663E\u793A\u5BC6\u7801");
		chbDisplayPwd.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(chbDisplayPwd.isSelected()) {
					password.setEchoChar((char) 0);
					repwd.setEchoChar((char) 0);
				}
				else {
					password.setEchoChar('��');
					repwd.setEchoChar('��');
				}
			}
		});
		
		repwd = new JPasswordField();
		repwd.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		repwd.setColumns(20);
		loginPane.add(repwd, "cell 1 4,alignx leading");
		chbDisplayPwd.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		loginPane.add(chbDisplayPwd, "cell 0 5 2 1,alignx center");
		
		JLabel lblMobile = new JLabel("\u624B\u673A\u53F7\u7801\uFF1A");
		lblMobile.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		loginPane.add(lblMobile, "cell 0 6,alignx trailing");
		
		mobile = new JTextField();
		mobile.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		mobile.setToolTipText("\u8BF7\u8F93\u51659-11\u4F4D\u624B\u673A\u53F7\uFF0C\u4EC5\u6570\u5B57");
		loginPane.add(mobile, "cell 1 6,alignx leading");
		mobile.setColumns(20);
		
		label_1 = new JLabel("\u5B66\u751F\u4FE1\u606F");
		label_1.setFont(new Font("΢���ź�", Font.BOLD, 12));
		loginPane.add(label_1, "cell 0 7");
		
		sepStudent = new JSeparator();
		loginPane.add(sepStudent, "cell 0 8 2 1,growx");
		
		cbStudents = new JComboBox<String>();
		

		
		cbStudents.setPreferredSize(new Dimension(14, 20));
		cbStudents.setMinimumSize(new Dimension(70, 20));
		cbStudents.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		cbStudents.setModel(new DefaultComboBoxModel<>(new String[] {"\u5B66\u751F1", "\u65B0\u5B66\u751F"}));
		cbStudents.setSelectedIndex(0);
		cbStudents.setEditable(true);
		cbStudents.setBounds(0, 0, 80, 20);
		loginPane.add(cbStudents, "cell 0 9,alignx left");
		
		
		
		lblfname = new JLabel("\u5B66\u751F\u7684\u540D\uFF1A");
		lblfname.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		loginPane.add(lblfname, "cell 0 10,alignx trailing");
		
		fname = new JTextField();
		fname.setToolTipText("\u5B66\u751F\u540D\uFF0C\u4EC5\u5B57\u6BCD");
		fname.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		loginPane.add(fname, "cell 1 10,alignx leading");
		fname.setColumns(20);
		
		label = new JLabel("\u5B66\u751F\u7684\u59D3\uFF1A");
		label.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		loginPane.add(label, "cell 0 11,alignx trailing");
		
		lname = new JTextField();
		lname.setToolTipText("\u5B66\u751F\u59D3\uFF0C\u4EC5\u5B57\u6BCD");
		lname.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		loginPane.add(lname, "cell 1 11,alignx leading");
		lname.setColumns(20);
		
		lblClass = new JLabel("\u5B66\u751F\u6240\u5B66\u7684\u8BFE\u7A0B\uFF1A");
		lblClass.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		loginPane.add(lblClass, "cell 0 12,alignx trailing");
		
		cbclass = new JComboBox<>();
		cbclass.setMaximumRowCount(20);
		cbclass.setModel(new DefaultComboBoxModel<>(new String[] {"\u6CA1\u6709\u53C2\u52A0\u7684\u8BFE\u7A0B"}));
		cbclass.setSelectedIndex(0);
		cbclass.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		
		new CommonUse().addItems(cbclass, new ArtClass().getAllClasses());
		
		loginPane.add(cbclass, "cell 1 12,growx");
		
		username.setName("�û���");
		password.setName("����");
		repwd.setName("ȷ������");
		fname.setName("ѧ����");
		lname.setName("ѧ����");
		mobile.setName("�ֻ�����");
		
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
		fname.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				//ֻ��������ĸ
				Matcher matcher = Pattern.compile("^[A-Za-z]+$").matcher(String.valueOf(e.getKeyChar()));
				if(matcher.matches()) {
					
					//����ĸ��д
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
				//ֻ��������ĸ
				Matcher matcher = Pattern.compile("^[A-Za-z]+$").matcher(String.valueOf(e.getKeyChar()));
				if(matcher.matches()) {
					//����ĸ��д
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
				//ֻ����������
				Matcher matcher = Pattern.compile("^[0-9]$").matcher(String.valueOf(e.getKeyChar()));
				
				if(matcher.matches()) {
					return;				
				}
				e.consume();
			}
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
				
				//Ŀǰ�ж�����
				int count= cbStudents.getItemCount();
				//Ŀǰѡ�ĵڼ���
				int index= cbStudents.getSelectedIndex();
				//�ж���һ��ѡ����ǲ�����ѧ��
				
				//���ѡ�������һ�������ѧ����
				if(index == count -1) {
					if(isLastItemisNew) {
						if(!fname.getText().equals("")) {
							if(!lname.getText().equals("")) {
								
									list.add(fname.getText());
									list.add(lname.getText());
									list.add(cbclass.getSelectedItem().toString());
								
									//������ѧ������student����
									students =new CommonUse().getList(list);	
									//���������Ŀ�����Ӵ洢��student��Ϣ	
									cbStudents.removeAllItems();
									for(int i=0;i<students.length/3;i++) {
									cbStudents.addItem(students[i*3]+" "+students[i*3+1]);
									}
									//cbStudents.insertItemAt("ѧ��"+count,index);
									cbStudents.addItem("ѧ��"+count);
									cbStudents.addItem("��ѧ��");
									cbStudents.setSelectedIndex(index);
							}
							else {
							
							JOptionPane.showMessageDialog(null, "ѧ����Ϊ�գ�");
							cbStudents.setSelectedIndex(count-2);
							lname.requestFocus();
							}	
						}
						else {
								
							JOptionPane.showMessageDialog(null, "ѧ����Ϊ�գ�");
							cbStudents.setSelectedIndex(count-2);
							fname.requestFocus();
									
						}	
					}
					else {
						cbStudents.insertItemAt("ѧ��"+count,index);
						cbStudents.setSelectedIndex(index);
					}
					
					//��ֵѧ����Ϣ��
					fname.setText("");
					lname.setText("");
					cbclass.setSelectedIndex(0);
					isLastItemisNew =true;
				}
				else {
					if(isLastItemisNew) {
						cbStudents.removeAllItems();
						for(int i=0;i<students.length/3;i++) {
							cbStudents.addItem(students[i*3]+" "+students[i*3+1]);
						}
						cbStudents.addItem("��ѧ��");
					}
					fname.setText(students[index*3]);
					lname.setText(students[index*3+1]);
					isLastItemisNew = false;
				}
			}
			
		});
		registe = new JButton("\u6CE8\u518C");
		registe.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		loginPane.add(registe, "cell 0 14,alignx trailing");
		
		back = new JButton("\u8FD4\u56DE");
		back.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		loginPane.add(back, "cell 1 14,alignx center");
		
		registe.addActionListener(this);
		back.addActionListener(this);
		
		registe.setActionCommand("Registe");
		back.setActionCommand("Back");
		
		//����λ������Ļ����
		this.setLocationRelativeTo(null);
		// ���õ����ڹر�ʱ����֤JVMҲ�˳�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{username, password, repwd, mobile, fname, lname}));
		//��ʾ����
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		//�ж����ĸ���ť�����
		if(e.getActionCommand().equals("Registe")){
			Component[] components = loginPane.getComponents();
			boolean allfilled = true;

			//�ж��Ƿ�����Ϊ��
			for (Component component : components) {
				if(component instanceof JPasswordField) {
					if(String.valueOf(((JPasswordField) component).getPassword()).equals("")) {
						
						JOptionPane.showMessageDialog(null, component.getName()+" Ϊ��!");
						component.requestFocus();
						allfilled = false;
						break;
					}
				}
				else if(component instanceof JTextField)
				{
					if(((JTextField) component).getText().trim().equals("")) {
						
						//����Ѿ�students�Ѿ�����ѧ������
						if(component.getName().equals("ѧ����")||component.getName().equals("ѧ����")) {
							if(students == null) {
								JOptionPane.showMessageDialog(null, component.getName()+" Ϊ��!");
								component.requestFocus();
								allfilled = false;
								break;
							}
						}
						else {
							JOptionPane.showMessageDialog(null, component.getName()+" Ϊ��!");
							component.requestFocus();
							allfilled = false;
							break;
						}
						
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
								
								//д��ѧ����Ϣ,����ж��ѧ��
								if(students !=null) {
									for(int i=0;i<students.length/3;i++) {
										new Student().addStudent(account.getNewUserID(),students[i*3+1],students[i*3],students[i*3+2]);
									}
									if(!lname.getText().equals("")&&!fname.getText().equals("")) {
										new Student().addStudent(account.getNewUserID(),lname.getText(),fname.getText(),cbclass.getSelectedItem().toString());
									}
									else {
										JOptionPane.showMessageDialog(null, cbStudents.getSelectedItem().toString()+"����Ϣ����������������ϵͳ!");
									}
								}
								else {
									new Student().addStudent(account.getNewUserID(),lname.getText(),fname.getText(),cbclass.getSelectedItem().toString());
								}
								JOptionPane.showMessageDialog(null, "ע��ɹ�!");
								String user = username.getText();
							
								this.dispose();								
								new LoginApp(user);	
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
			new LoginApp();
			
			
		}
	}
}