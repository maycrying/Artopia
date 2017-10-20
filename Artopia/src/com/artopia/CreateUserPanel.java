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

	//余额显示
	private JLabel jlbalance,jlnowclass,jllateclass,jlname,jlwarning;
	//签到，订课
	private JButton signup,booking;
	//当天课程显示,未来课程显示
	private JComboBox<Object> cbnowclass,cbnowtime,cblateclass,cbday,cbtime;
	
	private JPanel jpselect,jpinfo;
	ArtClass artClass;
	Student student;
	String[] classes,nowtimes,classnames,days,times;
	int sid=0;
	public CreateUserPanel(int uid) {
		// TODO Auto-generated constructor stub
		
		jlbalance = new JLabel();
		jllateclass = new JLabel("未来一个月的课程：");
		jlnowclass = new JLabel("今天提供的课程：");
		jlname = new JLabel();
		jlwarning= new JLabel();
		
		signup = new JButton("签到");
		booking = new JButton("订课");
		
		
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
			cbnowclass.addItem("今天的课都结束了");
			signup.setEnabled(false);
		}
	
		nowtimes = artClass.findAvaiableTimes(cbnowclass.getSelectedItem().toString(),
				weekday);
		if(nowtimes != null) {
			new CommonUse().addItems(cbnowtime, nowtimes);
		}
		else {
			cbnowtime.addItem("无时间段");
		}
		
		classnames = artClass.getAllClasses();
		if(classnames != null) {
			new CommonUse().addItems(cblateclass, classnames);
		}
		else {
			cblateclass.addItem("没有课程存在");
		}
		
		
		days = artClass.findAvaiableDays(cblateclass.getSelectedItem().toString());
		if(days != null) {
			new CommonUse().addItems(cbday, days);
		}
		else {
			cbday.addItem("该课程没有安排时间");
		}
		
		
		times = artClass.findAvaiableTimes(cblateclass.getSelectedItem().toString(),
				cbday.getSelectedItem().toString());
		if(times != null) {
			//显示未来一个月可订的课
			new CommonUse().addItems(cbtime, new CommonUse().getNewTimes(cbday.getSelectedItem().toString(), times));
			
		}
		else {
			cbtime.addItem("无时间段");
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
		jlname.setText("您的孩子："+student.getStudentName(uid));
		jlbalance.setText("所剩的学费为："+student.getBalance(uid));
		
		if(student.getBalance(uid)<=50) {
			jlwarning.setText("当前余额过低，请尽快充值！");
		}
		jpinfo.add(jlname);
		jpinfo.add(jlbalance);
		jpinfo.add(jlwarning);
		
		// 设置大小，按像素计算
		setSize(400, 400);
		// 设置初始位置
		setLocationRelativeTo(null);
		// 设置当窗口关闭时，保证JVM也退出
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 显示
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e){
		//判断是哪个按钮被点击
		if(e.getActionCommand().equals("Signup")){
			SignupAndBooking newsb = new SignupAndBooking(sid, cbnowclass.getSelectedItem().toString(), new CommonUse().getWeekOfDate(new Date()), cbnowtime.getSelectedItem().toString(), "Signup");
			if(!newsb.isDuplicate()){
				newsb.newSB();
				JOptionPane.showMessageDialog(null, "签到"+cbnowclass.getSelectedItem().toString()+"成功!");
			}
			else {
				JOptionPane.showMessageDialog(null, "你已经签到了"+cbnowclass.getSelectedItem().toString()+"!");
			}
		} 
		else if(e.getActionCommand().equals("Booking")){
			SignupAndBooking newsb = new SignupAndBooking(sid, cblateclass.getSelectedItem().toString(), cbday.getSelectedItem().toString(), cbtime.getSelectedItem().toString(), "Booking");
			if(!newsb.isDuplicate()){
				newsb.newSB();
				JOptionPane.showMessageDialog(null, "订课"+cblateclass.getSelectedItem().toString()+"成功!");
			}
			else {
				JOptionPane.showMessageDialog(null, "你已经预定了"+cblateclass.getSelectedItem().toString()+"!");
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
					cbnowtime.addItem("无时间段");
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
					cbday.addItem("该课程没有安排时间");
				}
				times = artClass.findAvaiableTimes(cblateclass.getSelectedItem().toString(),
						cbday.getSelectedItem().toString());
				//
				if(times != null) {
					cbtime.removeAllItems();
					new CommonUse().addItems(cbtime, new CommonUse().getNewTimes(cbday.getSelectedItem().toString(), times));
				}
				else {
					cbtime.addItem("无时间段");
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
					cbtime.addItem("无时间段");
				}
			}
		}
	}

	
}
