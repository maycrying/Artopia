package com.artopia;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.*;

public class CreateUserPanel extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4615022061562750286L;

	//�����ʾ
	private JLabel jlbalance,jlnowclass,jllateclass,jlname,jlwarning;
	//ǩ��������
	private JButton signup,booking;
	//����γ���ʾ,δ���γ���ʾ
	private JComboBox<Object> cbnowclass,cbnowtime,cblateclass,cbday,cbtime;
	
	private JPanel jpselect,jpinfo;
	ArtClass artClass;
	Student student;
	String[] classes,nowtimes,classnames,days,times;
	int sid=0;
	public CreateUserPanel(int uid) {
		// TODO Auto-generated constructor stub
		
		jlbalance = new JLabel();
		jllateclass = new JLabel("δ��һ���µĿγ̣�");
		jlnowclass = new JLabel("�����ṩ�Ŀγ̣�");
		jlname = new JLabel();
		jlwarning= new JLabel();
		
		signup = new JButton("ǩ��");
		booking = new JButton("����");
		
		
		signup.addActionListener(this);
		booking.addActionListener(this);
		
		signup.setActionCommand("Signup");
		booking.setActionCommand("Booking");
		
		cbnowclass = new JComboBox<>();		
		cbnowtime = new JComboBox<>();
		cblateclass = new JComboBox<>();
		cbday = new JComboBox<>();
		cbtime = new JComboBox<>();
		
		artClass = new ArtClass();

		String weekday = new CommonUse().getWeekOfDate(new Date());
		
		classes = artClass.findCurrentClass(weekday);
		if(classes != null) {
			new CommonUse().addItems(cbnowclass, classes);
		}
		else {
			cbnowclass.addItem("����Ŀζ�������");
			signup.setEnabled(false);
		}
	
		nowtimes = artClass.findAvaiableTimes(cbnowclass.getSelectedItem().toString(),
				weekday);
		if(nowtimes != null) {
			new CommonUse().addItems(cbnowtime, nowtimes);
		}
		else {
			cbnowtime.addItem("��ʱ���");
		}
		
		classnames = artClass.getAllClasses();
		if(classnames != null) {
			new CommonUse().addItems(cblateclass, classnames);
		}
		else {
			cblateclass.addItem("û�пγ̴���");
		}
		
		
		days = artClass.findAvaiableDays(cblateclass.getSelectedItem().toString());
		if(days != null) {
			new CommonUse().addItems(cbday, days);
		}
		else {
			cbday.addItem("�ÿγ�û�а���ʱ��");
		}
		
		
		times = artClass.findAvaiableTimes(cblateclass.getSelectedItem().toString(),
				cbday.getSelectedItem().toString());
		if(times != null) {
			//��ʾδ��һ���¿ɶ��Ŀ�
			new CommonUse().addItems(cbtime, new CommonUse().getNewTimes(cbday.getSelectedItem().toString(), times));
			
		}
		else {
			cbtime.addItem("��ʱ���");
		}
		
		cbnowclass.addActionListener(this);
		cblateclass.addActionListener(this);
		cbday.addActionListener(this);
		
		jpselect = new JPanel();
		jpinfo = new JPanel();
		
		setLayout(new GridLayout(1,0));
		add(jpselect);
		jpselect.setLayout(new GridLayout(0, 1,0,10));
		add(jpinfo);
		jpinfo.setLayout(new GridLayout(0,1));		
		
		jpselect.add(jlnowclass);
		jpselect.add(cbnowclass);
		jpselect.add(cbnowtime);
		jpselect.add(signup);
		jpselect.add(jllateclass);
		jpselect.add(cblateclass);
		jpselect.add(cbday);
		jpselect.add(cbtime);
		jpselect.add(booking);
		
		student = new Student();
		sid = student.getSid(uid);
		jlname.setText("���ĺ��ӣ�"+student.getStudentName(uid));
		jlbalance.setText("��ʣ��ѧ��Ϊ��"+student.getBalance(uid));
		
		if(student.getBalance(uid)<=50) {
			jlwarning.setText("��ǰ�����ͣ��뾡���ֵ��");
		}
		jpinfo.add(jlname);
		jpinfo.add(jlbalance);
		jpinfo.add(jlwarning);
		
		// ���ô�С�������ؼ���
		setSize(400, 400);
		// ���ó�ʼλ��
		setLocationRelativeTo(null);
		// ���õ����ڹر�ʱ����֤JVMҲ�˳�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ��ʾ
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e){
		//�ж����ĸ���ť�����
		if(e.getActionCommand().equals("Signup")){
			SignupAndBooking newsb = new SignupAndBooking(sid, cbnowclass.getSelectedItem().toString(), new CommonUse().getWeekOfDate(new Date()), cbnowtime.getSelectedItem().toString(), "Signup");
			if(!newsb.isDuplicate()){
				newsb.newSB();
				JOptionPane.showMessageDialog(null, "ǩ��"+cbnowclass.getSelectedItem().toString()+"�ɹ�!");
			}
			else {
				JOptionPane.showMessageDialog(null, "���Ѿ�ǩ����"+cbnowclass.getSelectedItem().toString()+"!");
			}
		} 
		else if(e.getActionCommand().equals("Booking")){
			SignupAndBooking newsb = new SignupAndBooking(sid, cblateclass.getSelectedItem().toString(), cbday.getSelectedItem().toString(), cbtime.getSelectedItem().toString(), "Booking");
			if(!newsb.isDuplicate()){
				newsb.newSB();
				JOptionPane.showMessageDialog(null, "����"+cblateclass.getSelectedItem().toString()+"�ɹ�!");
			}
			else {
				JOptionPane.showMessageDialog(null, "���Ѿ�Ԥ����"+cblateclass.getSelectedItem().toString()+"!");
			}
		}
		else if(e.getSource().equals(KeyEvent.VK_ENTER)) {
			
		}
		else if (e.getActionCommand().equals("comboBoxChanged")) {
			if(e.getSource().equals(cbnowclass)) {
				
				times = artClass.findAvaiableTimes(cbnowclass.getSelectedItem().toString(),
						new CommonUse().getWeekOfDate(new Date()));
				//
				if(times != null) {
					cbnowtime.removeAllItems();
					new CommonUse().addItems(cbnowtime, times);
				}
				else {
					cbnowtime.addItem("��ʱ���");
				}
			}
			else if(e.getSource().equals(cblateclass)) {
				days = artClass.findAvaiableDays(cblateclass.getSelectedItem().toString());
				//
				if(days != null) {
					int size = cbday.getItemCount();
					new CommonUse().addItems(cbday, days);
					for(int i = 0;i<size; i++) {
						cbday.removeItemAt(0);
					}
				}
				else {
					cbday.addItem("�ÿγ�û�а���ʱ��");
				}
				times = artClass.findAvaiableTimes(cblateclass.getSelectedItem().toString(),
						cbday.getSelectedItem().toString());
				//
				if(times != null) {
					cbtime.removeAllItems();
					new CommonUse().addItems(cbtime, new CommonUse().getNewTimes(cbday.getSelectedItem().toString(), times));
				}
				else {
					cbtime.addItem("��ʱ���");
				}
			}
			else if (e.getSource().equals(cbday)) {
				times = artClass.findAvaiableTimes(cblateclass.getSelectedItem().toString(),
						cbday.getSelectedItem().toString());

				//
				if(times != null) {
					cbtime.removeAllItems();
					new CommonUse().addItems(cbtime, new CommonUse().getNewTimes(cbday.getSelectedItem().toString(), times));
				}
				else {
					cbtime.addItem("��ʱ���");
				}
			}
		}
	}

	
}
