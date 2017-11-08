package com.artopia;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JCheckBox;
import java.awt.Toolkit;

import javax.swing.JPasswordField;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangePassword extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	private JButton okButton;
	private JPasswordField oldPwd;
	private JPasswordField newPwd;
	private JPasswordField rePwd;
	private JCheckBox cbShowPwd;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			ChangePassword dialog = new ChangePassword();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public ChangePassword(int uid) {
		
		new Functions().setOptionPaneFont();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ChangePassword.class.getResource("/com/artopia/logo.png")));
		setTitle("\u4FEE\u6539\u5BC6\u7801");
		setBounds(100, 100, 300, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow][grow]", "[][][][]"));
		{
			JLabel lbloldPwd = new JLabel("\u65E7\u5BC6\u7801\uFF1A");
			lbloldPwd.setFont(new Font("微软雅黑", Font.PLAIN, 13));
			contentPanel.add(lbloldPwd, "cell 0 0,alignx trailing");
		}
		{
			oldPwd = new JPasswordField();
			oldPwd.setColumns(20);
			oldPwd.setFont(new Font("微软雅黑", Font.PLAIN, 13));
			contentPanel.add(oldPwd, "cell 1 0,growx");
		}
		{
			JLabel lblnewPwd = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
			lblnewPwd.setFont(new Font("微软雅黑", Font.PLAIN, 13));
			contentPanel.add(lblnewPwd, "cell 0 1,alignx trailing");
		}
		{
			newPwd = new JPasswordField();
			newPwd.setColumns(20);
			newPwd.setFont(new Font("微软雅黑", Font.PLAIN, 13));
			contentPanel.add(newPwd, "cell 1 1,growx");
		}
		{
			JLabel lblrePwd = new JLabel("\u518D\u6B21\u8F93\u5165\u65B0\u5BC6\u7801\uFF1A");
			lblrePwd.setFont(new Font("微软雅黑", Font.PLAIN, 13));
			contentPanel.add(lblrePwd, "cell 0 2,alignx trailing");
		}
		{
			rePwd = new JPasswordField();
			rePwd.setFont(new Font("微软雅黑", Font.PLAIN, 13));
			rePwd.setColumns(20);
			contentPanel.add(rePwd, "cell 1 2,growx");
		}
		{
			cbShowPwd = new JCheckBox("\u663E\u793A\u5BC6\u7801");
			cbShowPwd.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					
					if(cbShowPwd.isSelected()) {
						oldPwd.setEchoChar((char) 0);
						newPwd.setEchoChar((char) 0);
						rePwd.setEchoChar((char) 0);
					}
					else {
						oldPwd.setEchoChar('●');
						newPwd.setEchoChar('●');
						rePwd.setEchoChar('●');
					}
					
				}
			});
			cbShowPwd.setFont(new Font("微软雅黑", Font.PLAIN, 13));
			contentPanel.add(cbShowPwd, "cell 0 3 2 1,alignx center");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("\u786E\u5B9A");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Component[] components = contentPanel.getComponents();
						boolean allfilled = true;

						//判断是否输入为空
						for (Component component : components) {
							if(component instanceof JPasswordField) {
								if(String.valueOf(((JPasswordField) component).getPassword()).equals("")) {
									
									JOptionPane.showMessageDialog(contentPanel, component.getName()+" 为空!");
									component.requestFocus();
									allfilled = false;
									break;
								}
								else {
									
								}
							}
						}
						//如果所有项都不为空
						if(allfilled) {
							Account account = new Account();
							//如果旧密码正确
							if(String.valueOf(oldPwd.getPassword()).equals(account.getPassword(uid))) {
								if(newPwd.getPassword().length>=6) {
									if(!String.valueOf(newPwd.getPassword()).equals(String.valueOf(oldPwd.getPassword()))) {
										if(String.valueOf(newPwd.getPassword()).equals(String.valueOf(rePwd.getPassword()))) {
											account.modifyPasswod(uid,String.valueOf(newPwd.getPassword()));
											JOptionPane.showMessageDialog(contentPanel, "密码修改成功!");
										}
										else {
											JOptionPane.showMessageDialog(contentPanel, "两次输入的密码不一致!");
											newPwd.requestFocus();
										}
									}
									else {
										JOptionPane.showMessageDialog(contentPanel, "旧密码和新密码不能一样!");
										newPwd.requestFocus();
									}
								}
								else {
									JOptionPane.showMessageDialog(contentPanel, "新密码长度少于6位!");
									newPwd.requestFocus();
								}
								
							}
							else{
								JOptionPane.showMessageDialog(contentPanel, "旧密码不正确!");
								oldPwd.requestFocus();
							}
						}
					}
					
				});
				okButton.setFont(new Font("微软雅黑", Font.PLAIN, 13));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u53D6\u6D88");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 13));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
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
			this.dispose();
		}
	}
}
