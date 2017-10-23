package com.artopia;


import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.util.Date;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserFrame {

	private JFrame frmUser;
	private JTable tableInfo;

	
	private ArtClass artClass;
	private Student student;
	private String[] classes,nowtimes,classnames,days,times;
	
	int sid=0;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserFrame window = new UserFrame();
//					window.frmUser.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	/**
	 * @wbp.parser.constructor
	 */
	public UserFrame() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUser = new JFrame();
		frmUser.getContentPane().setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.setIconImage(Toolkit.getDefaultToolkit().getImage(UserFrame.class.getResource("/com/artopia/logo.png")));
		frmUser.setTitle("\u7528\u6237\u4E2D\u5FC3");
		frmUser.setBounds(100, 100, 450, 303);
		frmUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUser.getContentPane().setLayout(new MigLayout("", "[150][][150][150,grow]", "[][grow][grow][grow][grow]"));
		
		JLabel lblNowClass = new JLabel("\u4ECA\u65E5\u8BFE\u7A0B\uFF1A");
		lblNowClass.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.getContentPane().add(lblNowClass, "cell 0 0,alignx trailing");
		
		JLabel lblLateClass = new JLabel("\u672A\u6765\u4E00\u4E2A\u6708\u7684\u8BFE\u7A0B\uFF1A");
		lblLateClass.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.getContentPane().add(lblLateClass, "cell 2 0,alignx trailing");
		
//		JComboBox<String> cbStudent = new JComboBox<>();
//		cbStudent.setFont(new Font("微软雅黑", Font.PLAIN, 13));
//		frmUser.getContentPane().add(cbStudent, "cell 3 0,alignx left");
		
		
		JComboBox<Object> cbnowclass = new JComboBox<>();
		
		cbnowclass.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.getContentPane().add(cbnowclass, "cell 0 1,growx,aligny bottom");
		
		JComboBox<Object> cblateclass = new JComboBox<>();
		
		cblateclass.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.getContentPane().add(cblateclass, "cell 2 1,growx,aligny bottom");
		
		tableInfo = new JTable();
		frmUser.getContentPane().add(tableInfo, "cell 3 1 1 4,grow");
		
		JComboBox<Object> cbnowtime = new JComboBox<>();
		cbnowtime.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.getContentPane().add(cbnowtime, "cell 0 2,growx,aligny bottom");
		
		JComboBox<Object> cbday = new JComboBox<>();
		
		cbday.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.getContentPane().add(cbday, "cell 2 2,growx,aligny bottom");
		
		JComboBox<Object> cbtime = new JComboBox<>();
		cbtime.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.getContentPane().add(cbtime, "cell 2 3,growx,aligny bottom");
		
		JButton signin = new JButton("\u7B7E\u5230");
		
		signin.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.getContentPane().add(signin, "cell 0 4,alignx right,aligny bottom");
		
		JButton booking = new JButton("\u8BA2\u8BFE");
		
		booking.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.getContentPane().add(booking, "cell 2 4,alignx right,aligny bottom");
		
		JMenuBar menuBar = new JMenuBar();
		frmUser.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u5B66\u751F");
		menuBar.add(menu);
		
		JMenuItem miAddDel = new JMenuItem("\u6DFB\u52A0/\u5220\u9664\u5B66\u751F");
		menu.add(miAddDel);
		
		JMenuItem miBook = new JMenuItem("\u8BA2\u8BFE\u4FE1\u606F");
		menu.add(miBook);
		
		JMenuItem miRecord = new JMenuItem("\u4E0A\u8BFE\u660E\u7EC6");
		menu.add(miRecord);
		
		JMenu menu_1 = new JMenu("\u4E2A\u4EBA\u8BBE\u7F6E");
		menuBar.add(menu_1);
		
		JMenuItem miPassword = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		menu_1.add(miPassword);
		
		JMenuItem miPersonal = new JMenuItem("\u4FEE\u6539\u4E2A\u4EBA\u4FE1\u606F");
		menu_1.add(miPersonal);
		
		artClass = new ArtClass();

		String weekday = new CommonUse().getWeekOfDate(new Date());
		
		classes = artClass.findCurrentClass(weekday);
		if(classes != null) {
			new CommonUse().addItems(cbnowclass, classes);
		}
		else {
			cbnowclass.addItem("今天的课都结束了");
			signin.setEnabled(false);
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
		
		cbnowclass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
		
		cblateclass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
		
		cbday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
		
		signin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignupAndBooking newsb = new SignupAndBooking(sid, cbnowclass.getSelectedItem().toString(), new CommonUse().getWeekOfDate(new Date()), cbnowtime.getSelectedItem().toString(), "SignIn");
				if(!newsb.isExist()){
					newsb.newSB();
					JOptionPane.showMessageDialog(null, "签到"+cbnowclass.getSelectedItem().toString()+"成功!");
				}
				else {
					JOptionPane.showMessageDialog(null, "你已经签到了"+cbnowclass.getSelectedItem().toString()+"!");
				}
			}
		});
		
		booking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignupAndBooking newsb = new SignupAndBooking(sid, cblateclass.getSelectedItem().toString(), cbday.getSelectedItem().toString(), cbtime.getSelectedItem().toString(), "Booking");
				if(!newsb.isExist()){
					newsb.newSB();
					JOptionPane.showMessageDialog(null, "订课"+cblateclass.getSelectedItem().toString()+"成功!");
				}
				else {
					JOptionPane.showMessageDialog(null, "你已经预定了"+cblateclass.getSelectedItem().toString()+"!");
				}
			}
		});
	}
	
	public  UserFrame(int uid) {
		initialize();
		student = new Student();
		
		
		JComboBox<String> cbStudent = new JComboBox<>();
		cbStudent.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.getContentPane().add(cbStudent, "cell 3 0,alignx left");
		
		String[] students = student.getStudentName(uid);
		for(int i=0;i<students.length;i++) {
			cbStudent.addItem(students[i]);
		}
		
		//带UID查询防止不同用户的重名情况
		sid = student.getSid(uid,cbStudent.getSelectedItem().toString());
		
		cbStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sid = student.getSid(uid,cbStudent.getSelectedItem().toString());
			}
			
		});
		// 设置初始位置
		frmUser.setLocationRelativeTo(null);
		frmUser.setVisible(true);
	}

}
