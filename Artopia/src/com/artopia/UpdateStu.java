package com.artopia;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class UpdateStu extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField fname;
	private JTextField lname;
	private Student student;
	private UserFrame userFrame;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			UpdateStu dialog = new UpdateStu();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 * @param uid 
	 */
	public UpdateStu(int uid, UserFrame uFrame) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		new Functions().setOptionPaneFont();
		userFrame = uFrame;
		
		setResizable(false);
		setTitle("\u6DFB\u52A0/\u5220\u9664/\u66F4\u65B0 \u5B66\u751F\u4FE1\u606F");
		setBounds(100, 100, 290, 240);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPanel);
		contentPanel.setLayout(new MigLayout("", "[grow][grow][grow]", "[][][][][][]"));
	

		JRadioButton rbAdd = new JRadioButton("\u6DFB\u52A0");
		
		rbAdd.setSelected(true);
		rbAdd.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		contentPanel.add(rbAdd, "cell 0 0,alignx trailing");
		
		JRadioButton rbDel = new JRadioButton("\u5220\u9664");
		
		rbDel.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		contentPanel.add(rbDel, "cell 1 0,alignx center");
		
		JRadioButton rbUpdate = new JRadioButton("\u66F4\u65B0");
		rbUpdate.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		contentPanel.add(rbUpdate, "cell 2 0");
		
		ButtonGroup opGroup = new ButtonGroup();
		opGroup.add(rbAdd);
		opGroup.add(rbDel);
		opGroup.add(rbUpdate);
		
		
		JComboBox<String> cbStudents = new JComboBox<String>();
		
		cbStudents.setEnabled(false);
		cbStudents.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		contentPanel.add(cbStudents, "cell 0 1,growx");
		
		student = new Student();
		
		String[] students = student.getStudentName(uid);
		for(int i=0;i<students.length;i++) {
			cbStudents.addItem(students[i]);
		}
		
		JLabel lblFname = new JLabel("\u5B66\u751F\u540D\uFF1A");
		lblFname.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		contentPanel.add(lblFname, "cell 0 2,alignx trailing");
		
		fname = new JTextField();
		fname.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		contentPanel.add(fname, "cell 1 2 2 1,growx");
		fname.setColumns(10);
		
		JLabel lblLname = new JLabel("\u5B66\u751F\u59D3\uFF1A");
		lblLname.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		contentPanel.add(lblLname, "cell 0 3,alignx trailing");
		
		lname = new JTextField();
		lname.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		contentPanel.add(lname, "cell 1 3 2 1,growx");
		lname.setColumns(10);
		
		
		
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
		});
		
		JLabel label = new JLabel("\u5B66\u751F\u6240\u5728\u8BFE\u7A0B\uFF1A");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		contentPanel.add(label, "cell 0 4,alignx trailing");
		
		JComboBox<Object> cbclass = new JComboBox<Object>();
		cbclass.setModel(new DefaultComboBoxModel<>(new String[] {"\u6CA1\u6709\u53C2\u52A0\u8BFE\u7A0B"}));
		cbclass.setSelectedIndex(0);
		cbclass.setMaximumRowCount(20);
		cbclass.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		contentPanel.add(cbclass, "cell 1 4 2 1,growx");
		
		new Functions().addItems(cbclass, new ArtClass().getAllClasses());
		
		
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确认");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(rbAdd.isSelected()) {
							if(!fname.getText().equals("")) {
								if(!lname.getText().equals("")) {
									student.addStudent(uid, lname.getText(), fname.getText(), cbclass.getSelectedItem().toString());
									cbStudents.addItem(fname.getText()+" "+lname.getText());
									//重置所有
									fname.setText("");
									lname.setText("");
									cbclass.setSelectedIndex(0);
									
									JOptionPane.showMessageDialog(contentPanel, "添加成功！");
								}
								else {
									JOptionPane.showMessageDialog(contentPanel,"学生姓为空！");
								}
							}
							else {
								JOptionPane.showMessageDialog(contentPanel,"学生名为空！");
							}
						}
						else if(rbDel.isSelected()){
							int sid = student.getSid(uid, cbStudents.getSelectedItem().toString());
							student.removeStudent(sid);
							int index = cbStudents.getSelectedIndex();
							cbStudents.removeItemAt(index);
							JOptionPane.showMessageDialog(contentPanel, "删除成功！");
						}
						else if(rbUpdate.isSelected()) {
							int sid = student.getSid(uid, cbStudents.getSelectedItem().toString());
							
							student.updateStudent(sid,fname.getText(),lname.getText(),cbclass.getSelectedItem().toString());
							
							int index = cbStudents.getSelectedIndex();
							cbStudents.insertItemAt(fname.getText()+" "+lname.getText(), index);
							cbStudents.removeItemAt(index+1);
							JOptionPane.showMessageDialog(contentPanel, "更新成功！");
							
						}
					}
				});
				okButton.setFont(new Font("微软雅黑", Font.PLAIN, 13));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				//getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("取消");
				
				cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 13));
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				
			}
		}
		
	
		
		rbAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//姓名选框不可用，输入框清空
				cbStudents.setEnabled(false);
				fname.setEditable(true);
				lname.setEditable(true);
				cbclass.setEnabled(true);
				
				
				fname.setText("");
				lname.setText("");
				
			}
		});
		
		rbDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbStudents.setEnabled(true);
				//文本框不可编辑
				fname.setEditable(false);
				lname.setEditable(false);
				cbclass.setEnabled(false);
				
				String name = cbStudents.getSelectedItem().toString();
				fname.setText(name.substring(0, name.indexOf(" ")));
				lname.setText(name.substring(name.indexOf(" ")+1,name.length()));
				
				//查询学生所在班级
				int sid = student.getSid(uid, cbStudents.getSelectedItem().toString());
				String classname = student.getClassname(sid);
				cbclass.setSelectedItem(classname);
				
			}
		});
		
		rbUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbStudents.setEnabled(true);
				cbclass.setEnabled(true);
				
				//文本框可编辑
				fname.setEditable(true);
				lname.setEditable(true);
				
				String name = cbStudents.getSelectedItem().toString();
				fname.setText(name.substring(0, name.indexOf(" ")));
				lname.setText(name.substring(name.indexOf(" ")+1,name.length()));

				//查询学生所在班级
				int sid = student.getSid(uid, cbStudents.getSelectedItem().toString());
				String classname = student.getClassname(sid);
				cbclass.setSelectedItem(classname);
			}
		});
		
		cbStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = cbStudents.getSelectedItem().toString();
				fname.setText(name.substring(0, name.indexOf(" ")));
				lname.setText(name.substring(name.indexOf(" ")+1,name.length()));
				
				//查询学生所在班级
				int sid = student.getSid(uid, cbStudents.getSelectedItem().toString());
				String classname = student.getClassname(sid);
				cbclass.setSelectedItem(classname);
			}
		});
		
		//窗口置顶
		this.setModal(true);
		this.setAlwaysOnTop(true);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		
		if(e.getActionCommand().equals("Cancel")) {
			userFrame.updateCB();
			this.dispose();
		}
	}
}
