package com.artopia;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeInfo extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField mobile;
	private JTextField wechat;

	private JButton okButton;
	
	int myuid;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			ChangeInfo dialog = new ChangeInfo();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public ChangeInfo(int uid) {
		setResizable(false);
		
		new Functions().setOptionPaneFont();
		myuid = uid;
		setTitle("\u4FEE\u6539\u4E2A\u4EBA\u4FE1\u606F");
		setBounds(100, 100, 249, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[100,trailing][grow]", "[][]"));
		{
			JLabel lblNewLabel = new JLabel("\u7535\u8BDD\u53F7\u7801\uFF1A");
			lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 13));
			contentPanel.add(lblNewLabel, "cell 0 0,alignx trailing");
		}
		{
			mobile = new JTextField();
			mobile.setFont(new Font("微软雅黑", Font.PLAIN, 13));
			contentPanel.add(mobile, "cell 1 0,alignx leading");
			mobile.setColumns(20);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("\u5FAE\u4FE1\u6635\u79F0\uFF1A");
			lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 13));
			contentPanel.add(lblNewLabel_1, "cell 0 1,alignx trailing");
		}
		{
			wechat = new JTextField();
			wechat.setFont(new Font("微软雅黑", Font.PLAIN, 13));
			contentPanel.add(wechat, "cell 1 1,alignx leading");
			wechat.setColumns(20);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("\u786E\u5B9A");
				okButton.addActionListener(this);
				okButton.setFont(new Font("微软雅黑", Font.PLAIN, 13));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u53D6\u6D88");
				cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 13));
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
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
					okButton.doClick();
				}
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
			this.dispose();
		}
		else if(e.getActionCommand().equals("OK")) {
			
				Account account = new Account();
//				String strMobile = mobile.getText().trim();
//				String strWechat = wechat.getText().trim();
				if(!mobile.getText().trim().equals("")) {
					if (mobile.getText().trim().length()>8 && mobile.getText().trim().length()<12) {
						if(!wechat.getText().trim().equals("")) {
							account.updateInfo(myuid, mobile.getText().trim(), wechat.getText().trim());
							JOptionPane.showMessageDialog(contentPanel, "信息修改成功!");
							this.dispose();
						}
						else {
							account.updateInfo(myuid, mobile.getText().trim(), null);
							JOptionPane.showMessageDialog(contentPanel, "电话号码修改成功!");
							this.dispose();
						}
					}
					else {
						JOptionPane.showMessageDialog(contentPanel, "电话号码必须在9-11位之间!");
						mobile.selectAll();
						mobile.requestFocus();
					}
				}
				else {
					if(!wechat.getText().trim().equals("")) {
						account.updateInfo(myuid, null, wechat.getText().trim());
						JOptionPane.showMessageDialog(contentPanel, "微信昵称修改成功!");
						this.dispose();
					}
					else {
						JOptionPane.showMessageDialog(contentPanel, "至少填入一项!");
						mobile.selectAll();
						mobile.requestFocus();
					}
				}
			}
		
	}
}
