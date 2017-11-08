package com.artopia;


import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserFrame implements ActionListener{

	protected static final int BOOKINGFEE = 10;
	protected static final int LOW_BALANCE_NOTICE = 30;
	private JFrame frmUser;
	private JComboBox<String> cbStudent;
	private JComboBox<Object> cblateclass;
	private JComboBox<Object> cbnowclass;
	private JComboBox<Object> cbnowtime;
	private JComboBox<Object> cbday;
	private JComboBox<Object> cbtime;
	private JButton signin,booking;
	private JMenuItem miAddDel,miBook, miRecord;
	
	private ArtClass artClass;
	private Student student;
	private String[] classes,nowtimes,classnames,days,times;
	
	int sid=0;
	static int myuid;
	Object[][] mydata;
	
	private JTable tableInfo;
	JScrollPane tablePane; 
	private JMenu menu_Admin,menu_User,menu_Student;
	private JMenuItem miLowBalance,miPassword,miPersonal;
	private JMenu menu_Sys;
	private JMenuItem miQuit;
	private JLabel lblStudent;
	private JMenuItem miAllBooked;
	private JLabel lblNowClass;
	private JComboBox<Object> cboldtime;
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
		frmUser.setBounds(100, 100, 550, 303);
		frmUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUser.getContentPane().setLayout(new MigLayout("", "[150][150][150,grow]", "[][grow][grow][grow][grow]"));
		
		lblNowClass = new JLabel("\u4ECA\u65E5\u8BFE\u7A0B\uFF1A");
		lblNowClass.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.getContentPane().add(lblNowClass, "cell 0 0,alignx trailing");
		
		JLabel lblLateClass = new JLabel("\u672A\u6765\u4E00\u4E2A\u6708\u7684\u8BFE\u7A0B\uFF1A");
		lblLateClass.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.getContentPane().add(lblLateClass, "cell 1 0,alignx trailing");
		
		lblStudent = new JLabel("\u5B66\u751F\u59D3\u540D\uFF1A");
		lblStudent.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.getContentPane().add(lblStudent, "flowx,cell 2 0,alignx leading");
		
		cbStudent = new JComboBox<>();
		
		cbStudent.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.getContentPane().add(cbStudent, "cell 2 0,alignx trailing");
		
		
		cbnowclass = new JComboBox<>();
		
		cbnowclass.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.getContentPane().add(cbnowclass, "cell 0 1,growx,aligny bottom");
		
		cblateclass = new JComboBox<>();
		
		cblateclass.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.getContentPane().add(cblateclass, "cell 1 1,growx,aligny bottom");
		
		
		
		cbnowtime = new JComboBox<>();
		cbnowtime.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.getContentPane().add(cbnowtime, "cell 0 2,growx,aligny bottom");
		
		cbday = new JComboBox<>();
		
		cbday.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.getContentPane().add(cbday, "cell 1 2,growx,aligny bottom");
		
		cboldtime = new JComboBox<>();
		cboldtime.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.getContentPane().add(cboldtime, "cell 0 3,growx,aligny bottom");
		
		cbtime = new JComboBox<>();
		cbtime.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.getContentPane().add(cbtime, "cell 1 3,growx,aligny bottom");
		
		signin = new JButton("\u7B7E\u5230");
		
		signin.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.getContentPane().add(signin, "cell 0 4,alignx right,aligny bottom");
		
		booking = new JButton("\u8BA2\u8BFE");
		
		booking.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmUser.getContentPane().add(booking, "cell 1 4,alignx right,aligny bottom");
		
		JMenuBar menuBar = new JMenuBar();
		frmUser.setJMenuBar(menuBar);
		
		menu_Sys = new JMenu("\u7CFB\u7EDF");
		menuBar.add(menu_Sys);
		
		miQuit = new JMenuItem("\u9000\u51FA\u767B\u5F55");
		menu_Sys.add(miQuit);
		
		menu_Student = new JMenu("\u5B66\u751F");
		menuBar.add(menu_Student);
		
		miAddDel = new JMenuItem("\u6DFB\u52A0/\u5220\u9664\u5B66\u751F");
		menu_Student.add(miAddDel);
		
		miAddDel.addActionListener(this);
		miAddDel.setActionCommand("Update");
		
		miBook = new JMenuItem("\u8BA2\u8BFE\u4FE1\u606F");
		
		menu_Student.add(miBook);
		
		miRecord = new JMenuItem("\u4E0A\u8BFE\u660E\u7EC6");
		menu_Student.add(miRecord);
		
		menu_User = new JMenu("\u4E2A\u4EBA\u8BBE\u7F6E");
		menuBar.add(menu_User);
		
		miPassword = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		menu_User.add(miPassword);
		
		
		miPersonal = new JMenuItem("\u4FEE\u6539\u4E2A\u4EBA\u4FE1\u606F");
		menu_User.add(miPersonal);
		
		menu_Admin = new JMenu("\u7BA1\u7406\u5458");
		menuBar.add(menu_Admin);
		
		miLowBalance = new JMenuItem("\u4F4E\u4F59\u989D\u7528\u6237\u67E5\u8BE2");
		
		menu_Admin.add(miLowBalance);
		
		miAllBooked = new JMenuItem("\u6240\u6709\u8BA2\u8BFE\u4FE1\u606F");
		menu_Admin.add(miAllBooked);
		
		
		
		
		
	}
	

	public  UserFrame(int uid) {
		initialize();
		artClass = new ArtClass();
		
		classnames = artClass.getAllClasses();
		if(classnames != null) {
			new Functions().addItems(cblateclass, classnames);
		}
		else {
			cblateclass.addItem("没有课程存在");
		}
		
		
		days = artClass.findAvaiableDays(cblateclass.getSelectedItem().toString());
		if(days != null) {
			new Functions().addItems(cbday, days);
		}
		else {
			cbday.addItem("该课程没有安排时间");
		}
		
		
		times = artClass.findAvaiableTimes(cblateclass.getSelectedItem().toString(),
				cbday.getSelectedItem().toString());
		if(times != null) {
			//显示未来一个月可订的课
			new Functions().addItems(cbtime, new Functions().getNewTimes(cbday.getSelectedItem().toString(), times));
			
		}
		else {
			cbtime.addItem("无时间段");
		}
		if(uid == 1) {
			//预载管理员初始界面
			signin.setText("签到该课人员名单");
			booking.setText("预定该课人员名单");
			lblNowClass.setText("过去一周课程：");
			
			
			
			//所有课程列表
//			classes = artClass.getAllClasses();
//			new Functions().addItems(cbnowtime, classes);
		
			//过去一周的时间列表
			String[] weeklist = new Functions().getWeekList(new Date());
			if(weeklist != null) {
				new Functions().addItems(cbnowclass, weeklist);
				cbnowclass.setSelectedIndex(cbnowclass.getItemCount()-1);;
			}
			
			String weekday = new Functions().getWeekOfDate(new Date());
			classes = artClass.findTodayClass(weekday);
			if(classes != null) {
				new Functions().addItems(cbnowtime, classes);
			}
			else {
				cbnowtime.addItem("今日无课程");
			}
			
			times = artClass.findAvaiableTimes(cbnowtime.getSelectedItem().toString(),
					weekday);
			if(times != null) {
				new Functions().addItems(cboldtime, times);
			}
			else {
				cboldtime.addItem("无时间段");
			}
			
			adminOnly(uid);
		}
		else {
			//预载普通用户初始界面
			

			String weekday = new Functions().getWeekOfDate(new Date());
			
			classes = artClass.findCurrentClass(weekday);
			if(classes != null) {
				new Functions().addItems(cbnowclass, classes);
			}
			else {
				cbnowclass.addItem("今天的课都结束了");
				signin.setEnabled(false);
			}
		
			nowtimes = artClass.findAvaiableTimes(cbnowclass.getSelectedItem().toString(),
					weekday);
			if(nowtimes != null) {
				new Functions().addItems(cbnowtime, nowtimes);
			}
			else {
				cbnowtime.addItem("无时间段");
			}
			
			
			userOnly(uid);
		}
		
		cblateclass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				days = artClass.findAvaiableDays(cblateclass.getSelectedItem().toString());
				//
				if(days != null) {
					int size = cbday.getItemCount();
					new Functions().addItems(cbday, days);
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
					new Functions().addItems(cbtime, new Functions().getNewTimes(cbday.getSelectedItem().toString(), times));
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
					new Functions().addItems(cbtime, new Functions().getNewTimes(cbday.getSelectedItem().toString(), times));
				}
				else {
					cbtime.addItem("无时间段");
				}
			}
		});
		
		miQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmUser.dispose();
				new LoginApp();
			}
		});
		
		miPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangePassword(uid);
			}
		});
		
		
		// 设置初始位置
		frmUser.setLocationRelativeTo(null);
		frmUser.setVisible(true);
	}

	//管理员界面设计
	public void adminOnly(int uid) {
		
		//隐藏不需要的界面元素
		miPersonal.setVisible(false);		
		menu_Student.setVisible(false);
		lblStudent.setVisible(false);
		cbStudent.setVisible(false);
		
		Vector<Object> colNames = new Vector<Object>();
//		Vector<Vector<Object>> content = new Vector<Vector<Object>>();
		
		//设置管理员查询的表头和内容
		colNames.add("查询");
		colNames.add("信息");
				
//		Vector<Object> dataVector = new Vector<Object>();
//		dataVector.add("一个世界");
//		dataVector.addElement("在等待...");
//				
//		content.add(dataVector);

				
		//初始内容
		String[][] tmpdata = {
				{"一个世界","在等待..."}
				};
		
		mydata = tmpdata;		
				
				
		//获取当前学生的余额，显示在表中
			
		class MyTableModel extends AbstractTableModel {
			      
			private static final long serialVersionUID = 1L;
					
			private String[] columnNames;
					
					
					
			private Object[][] data;
			        
			public void updateTable() {
			   	this.columnNames = colNames.toArray(new String[0]);
			   	this.data = mydata;
			   	fireTableStructureChanged();
			}
			       
			 
			public int getColumnCount() {
				return columnNames.length;
			}
			 
			public int getRowCount() {
			    return data.length;
			}
			        
			 
			public String getColumnName(int col) {
				return columnNames[col];
			}
			 
			public Object getValueAt(int row, int col) {
			    return data[row][col];
			}
			 
			public boolean isCellEditable(int row, int col) {
			            
				return false;
			          
			}
			
			public void setValueAt(Object value, int row, int col) {
			          
			 
			   data[row][col] = value;
			   fireTableCellUpdated(row, col);
			 
			   
			}
			
		}
				
			
		tableInfo = new JTable();		
		MyTableModel myTableModel = new MyTableModel();	
		myTableModel.updateTable();
		tableInfo.setModel(myTableModel);
				
				
		tablePane = new JScrollPane(tableInfo);
		frmUser.getContentPane().add(tablePane, "cell 2 1 1 4,grow");
		Account account = new Account();
			
		miLowBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String max = (String)JOptionPane.showInputDialog(
						frmUser,
						"低于该数值的用户将会被显示",
		                "请输入数值", 
		                JOptionPane.PLAIN_MESSAGE,
		                null,
		                null, "25");

				int num = Integer.parseInt(max);
				
				//制作显示信息表
				colNames.clear();
				colNames.add("微信昵称");
				colNames.add("余额");
				colNames.add("联系电话");
				java.util.List<String> userlist = account.getLowBalanceList(num);
				String[][] tmpdata= new String[userlist.size()/3][3];
				for(int i=0; i<userlist.size()/3;i++) {
					for(int j =0;j<3;j++) {
						if(j==1) {
							tmpdata[i][1] ="$"+userlist.get(i*3+j);
						}
						else {
							tmpdata[i][j] = userlist.get(i*3+j);
						}
							
					}	
				}	
				mydata = tmpdata;
				myTableModel.updateTable();
			}
		});
		
		miAllBooked.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignupAndBooking newsb = new SignupAndBooking();
				java.util.List<String> userlist = newsb.getAllBookedList();
			
				//制作显示信息表
				colNames.clear();
				colNames.add("学生姓名");
				colNames.add("课程名");
				colNames.add("时间");
				
				String[][] tmpdata= new String[userlist.size()/3][3];
				for(int i=0; i<userlist.size()/3;i++) {
					for(int j=0;j<3;j++) {
						
							tmpdata[i][j] = userlist.get(i*3+j);
						
					}	
						
				}	
				mydata = tmpdata;
				myTableModel.updateTable();
			}
		});
		
		booking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignupAndBooking newsb = new SignupAndBooking();
				java.util.List<String> userlist = newsb.getBookedList(cblateclass.getSelectedItem().toString(),cbtime.getSelectedItem().toString());
			
				//制作显示信息表
				colNames.clear();
				colNames.add("预定该课的学生名单");
				
				String[][] tmpdata= new String[userlist.size()][1];
				for(int i=0; i<userlist.size();i++) {
					
						
							tmpdata[i][0] = userlist.get(i);
						
							
						
				}	
				mydata = tmpdata;
				myTableModel.updateTable();
			}
		});
		
		signin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignupAndBooking newsb = new SignupAndBooking();
				int daynum = new Functions().getWeekdayNum(new Date());
				java.util.List<String> userlist = new ArrayList<>();
				if(cbnowclass.getSelectedIndex()< 7-daynum) {
					userlist = newsb.getSignedList(cbnowclass.getSelectedItem().toString().substring(1)
							,cbnowtime.getSelectedItem().toString(),cboldtime.getSelectedItem().toString().substring(0, cboldtime.getSelectedItem().toString().indexOf('-')));
				}
				else {
					userlist = newsb.getSignedList(cbnowclass.getSelectedItem().toString()
							,cbnowtime.getSelectedItem().toString(),cboldtime.getSelectedItem().toString().substring(0, cboldtime.getSelectedItem().toString().indexOf('-')));
				}
				//制作显示信息表
				colNames.clear();
				colNames.add("签到该课的学生名单");
				
				String[][] tmpdata= new String[userlist.size()][1];
				for(int i=0; i<userlist.size();i++) {
					
						
							tmpdata[i][0] = userlist.get(i);
						
							
						
				}	
				mydata = tmpdata;
				myTableModel.updateTable();
			}
		});
		
		cbnowclass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int size = cbnowtime.getItemCount();
				//获取当前选择的星期
				int daynum = new Functions().getWeekdayNum(new Date());
				if(cbnowclass.getSelectedIndex()< 7-daynum) {
					//去掉选择条目上的“上”字
					classes = artClass.findTodayClass(cbnowclass.getSelectedItem().toString().substring(1));
				}
				else {
					classes = artClass.findTodayClass(cbnowclass.getSelectedItem().toString());
				}
				
				//重新加载cbnowtime,即当日存在的课程名	
				if(classes != null) {
					
					new Functions().addItems(cbnowtime, classes);
					
				}
				else {
					cbnowtime.addItem("该课程没有安排时间");
				}
				
				//移除旧的条目
				for(int i = 0;i<size; i++) {
					cbnowtime.removeItemAt(0);
				}
//				times = artClass.findAvaiableTimes(cbnowtime.getSelectedItem().toString(),
//						cbnowclass.getSelectedItem().toString().substring(2));
//				//
//				if(times != null) {
//					cboldtime.removeAllItems();
//					new Functions().addItems(cboldtime, new Functions().getNewTimes(cbday.getSelectedItem().toString(), times));
//				}
//				else {
//					cbtime.addItem("无时间段");
//				}
			}
		});
		
		cbnowtime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int daynum = new Functions().getWeekdayNum(new Date());
				if(cbnowclass.getSelectedIndex()< 7-daynum) {
					times = artClass.findAvaiableTimes(cbnowtime.getSelectedItem().toString(),
						cbnowclass.getSelectedItem().toString().substring(1));
				}
				else {
					times = artClass.findAvaiableTimes(cbnowtime.getSelectedItem().toString(),
							cbnowclass.getSelectedItem().toString());
				}
				//
				if(times != null) {
					cboldtime.removeAllItems();
					new Functions().addItems(cboldtime,times);
				}
				else {
					cbtime.addItem("无时间段");
				}
			}
		});
		
	}
	
	//用户界面设计
	public void userOnly(int uid) {
		
		menu_Admin.setVisible(false);
		cboldtime.setVisible(false);
		myuid =uid;
		
		Vector<Object> colNames = new Vector<Object>();
		Vector<Vector<Object>> content = new Vector<Vector<Object>>();
		
		miPersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangeInfo(uid);
			}
		});
		
		cbnowclass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				times = artClass.findAvaiableTimes(cbnowclass.getSelectedItem().toString(),
						new Functions().getWeekOfDate(new Date()));
				//
				if(times != null) {
					cbnowtime.removeAllItems();
					new Functions().addItems(cbnowtime, times);
				}
				else {
					cbnowtime.addItem("无时间段");
				}
			}
		});
		

		
		student = new Student();
		
		String[] students = student.getStudentName(uid);
		for(int i=0;i<students.length;i++) {
			cbStudent.addItem(students[i]);
		}
		
		//带UID查询防止不同用户的重名情况
		sid = student.getSid(uid,cbStudent.getSelectedItem().toString());
		
		//设置学生余额表查询的表头和内容
		colNames.add("");
		colNames.add("金额");
		
		Vector<Object> dataVector = new Vector<Object>();
		dataVector.add("现有余额：");
		dataVector.addElement("$"+new Double(student.getBalance(uid)));
		
		content.add(dataVector);

		//获取第一个vector的长度，作为二维数组的y,包含vetor的数目为x
		
		
		mydata =new Functions().getArrays(content);
		
		
		
		//获取当前学生的余额，显示在表中
	
		class MyTableModel extends AbstractTableModel {
	      
			private static final long serialVersionUID = 1L;
			
			private String[] columnNames;
			
			
			
			private Object[][] data;
	        
	        public void updateTable() {
	        	this.columnNames = colNames.toArray(new String[0]);
	        	this.data = mydata;
	        	fireTableStructureChanged();
	        }
	       
	 
	        public int getColumnCount() {
	            return columnNames.length;
	        }
	 
	        public int getRowCount() {
	            return data.length;
	        }
	        
	 
	        public String getColumnName(int col) {
	            return columnNames[col];
	        }
	 
	        public Object getValueAt(int row, int col) {
	            return data[row][col];
	        }
	 
	        public boolean isCellEditable(int row, int col) {
	            
	             return false;
	          
	        }
	        public void setValueAt(Object value, int row, int col) {
	          
	 
	            data[row][col] = value;
	            fireTableCellUpdated(row, col);
	 
	        }
		}
		
		tableInfo = new JTable();
		MyTableModel myTableModel = new MyTableModel();
		myTableModel.updateTable();
		tableInfo.setModel(myTableModel);
		
		//Create the scroll pane and add the table to it.
		tablePane = new JScrollPane(tableInfo);
		frmUser.getContentPane().add(tablePane, "cell 2 1 1 4,grow");
        
		//订课按钮响应事件
		booking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignupAndBooking newsb = new SignupAndBooking(sid, cblateclass.getSelectedItem().toString(), cbday.getSelectedItem().toString(), cbtime.getSelectedItem().toString(), "Booking");
				if(!newsb.isExist()){
					newsb.newSB();
					newsb.updateBalance(uid,BOOKINGFEE);
					//JOptionPane.showMessageDialog(null, "订课"+cblateclass.getSelectedItem().toString()+"成功!");
					
					//设置订课完成后的表单.3行,表头不变
					colNames.clear();
					colNames.add("");
					colNames.add("金额");
					if(student.getBalance(uid) <= LOW_BALANCE_NOTICE) {
						JOptionPane.showMessageDialog(null, "余额过低，请尽快充值!");
					}
					String[][] tmpdata = {
							{"该学生原有余额：","$"+new Double(student.getBalance(uid)+BOOKINGFEE)},
							{"订课"+cblateclass.getSelectedItem().toString(),"-$"+BOOKINGFEE},
							{"该学生现有余额：","$"+new Double(student.getBalance(uid))}
					};
					mydata = tmpdata;
					myTableModel.updateTable();
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "你已经预定了"+cblateclass.getSelectedItem().toString()+"!");
				}
			}
		});
		
		signin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignupAndBooking newsb = new SignupAndBooking(sid, cbnowclass.getSelectedItem().toString(), new Functions().getWeekOfDate(new Date()), cbnowtime.getSelectedItem().toString(), "SignIn");
				//判断是否签到，是否有订课
				if(!newsb.isExist()){
					if(newsb.isBooked()) {
						newsb.newSB();
						newsb.updateBalance(uid,newsb.getRestFee(cbnowclass.getSelectedItem().toString(),BOOKINGFEE));
						//JOptionPane.showMessageDialog(null, "签到"+cblateclass.getSelectedItem().toString()+"成功!");
						
						//设置订课完成后的表单.3行,表头不变
						colNames.clear();
						colNames.add("");
						colNames.add("金额");
						
						if(student.getBalance(uid) <= LOW_BALANCE_NOTICE) {
							JOptionPane.showMessageDialog(null, "该学生余额过低，请尽快充值!");
						}
						
						String[][] tmpdata = {
								{"该学生原有余额：","$"+new Double(student.getBalance(uid)+newsb.getRestFee(cbnowclass.getSelectedItem().toString(),BOOKINGFEE))},
								{"签到"+cbnowclass.getSelectedItem().toString(),"-$"+newsb.getRestFee(cbnowclass.getSelectedItem().toString(),BOOKINGFEE)},
								{"该学生现有余额：","$"+new Double(student.getBalance(uid))}
						};
						mydata = tmpdata;
						myTableModel.updateTable();
					}
					else {
						JOptionPane.showMessageDialog(null, "你并没有预定"+cbnowclass.getSelectedItem().toString()+"!");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "你已经签到了"+cbnowclass.getSelectedItem().toString()+"!");
				}
			}
		});
		
		cbStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sid = student.getSid(uid,cbStudent.getSelectedItem().toString());
				
				colNames.clear();
				colNames.add("");
				colNames.add("金额");
				
				String[][] tmpdata = {
						{"现有余额：","$"+new Double(student.getBalance(uid))}
				};
				mydata = tmpdata;
				myTableModel.updateTable();
			}
			
		});
		
		miBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sid = student.getSid(uid,cbStudent.getSelectedItem().toString());
				
				colNames.clear();
				colNames.add("课程名");
				colNames.add("日期");
				colNames.add("开始时间");
				java.util.List<String> classlist = student.getBookedClasses(sid);
				String[][] tmpdata= new String[classlist.size()/3][3];
				for(int i=0; i<classlist.size()/3;i++) {
					for(int j =0;j<3;j++) {
						tmpdata[i][j]= classlist.get(i*3+j);
					}	
				}	
				mydata = tmpdata;
				myTableModel.updateTable();
			}
		});
		
		miRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sid = student.getSid(uid,cbStudent.getSelectedItem().toString());
				
				colNames.clear();
				colNames.add("课程名");
				colNames.add("日期");
				java.util.List<String> classlist = student.getSignedClasses(sid);
				String[][] tmpdata= new String[classlist.size()/2][2];
				for(int i=0; i<classlist.size()/2;i++) {
					for(int j =0;j<2;j++) {
						tmpdata[i][j]= classlist.get(i*2+j);
					}	
				}	
				mydata = tmpdata;
				myTableModel.updateTable();
			}
		});
	}
	public void updateCB() {
		// TODO Auto-generated method stub
		int size = cbStudent.getItemCount();
		student = new Student();
		
		String[] students = student.getStudentName(myuid);
		for(int i=0;i<students.length;i++) {
			cbStudent.addItem(students[i]);
		}
		for(int i=0;i<size;i++) {
			cbStudent.removeItemAt(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Update")) {
			new UpdateStu(myuid, this);
		}
	}

}
