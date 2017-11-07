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
	private JMenuItem miLowBalance;
	private JMenu menu_Sys;
	private JMenuItem miQuit;
	private JLabel lblStudent;
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
		frmUser.getContentPane().setFont(new Font("΢���ź�", Font.PLAIN, 13));
		frmUser.setIconImage(Toolkit.getDefaultToolkit().getImage(UserFrame.class.getResource("/com/artopia/logo.png")));
		frmUser.setTitle("\u7528\u6237\u4E2D\u5FC3");
		frmUser.setBounds(100, 100, 550, 303);
		frmUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUser.getContentPane().setLayout(new MigLayout("", "[150][150][150,grow]", "[][grow][grow][grow][grow]"));
		
		JLabel lblNowClass = new JLabel("\u4ECA\u65E5\u8BFE\u7A0B\uFF1A");
		lblNowClass.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		frmUser.getContentPane().add(lblNowClass, "cell 0 0,alignx trailing");
		
		JLabel lblLateClass = new JLabel("\u672A\u6765\u4E00\u4E2A\u6708\u7684\u8BFE\u7A0B\uFF1A");
		lblLateClass.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		frmUser.getContentPane().add(lblLateClass, "cell 1 0,alignx trailing");
		
		lblStudent = new JLabel("\u5B66\u751F\u59D3\u540D\uFF1A");
		lblStudent.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		frmUser.getContentPane().add(lblStudent, "flowx,cell 2 0,alignx leading");
		
		cbStudent = new JComboBox<>();
		
		cbStudent.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		frmUser.getContentPane().add(cbStudent, "cell 2 0,alignx trailing");
		
		
		cbnowclass = new JComboBox<>();
		
		cbnowclass.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		frmUser.getContentPane().add(cbnowclass, "cell 0 1,growx,aligny bottom");
		
		cblateclass = new JComboBox<>();
		
		cblateclass.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		frmUser.getContentPane().add(cblateclass, "cell 1 1,growx,aligny bottom");
		
		
		
		cbnowtime = new JComboBox<>();
		cbnowtime.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		frmUser.getContentPane().add(cbnowtime, "cell 0 2,growx,aligny bottom");
		
		cbday = new JComboBox<>();
		
		cbday.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		frmUser.getContentPane().add(cbday, "cell 1 2,growx,aligny bottom");
		
		cbtime = new JComboBox<>();
		cbtime.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		frmUser.getContentPane().add(cbtime, "cell 1 3,growx,aligny bottom");
		
		signin = new JButton("\u7B7E\u5230");
		
		signin.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		frmUser.getContentPane().add(signin, "cell 0 4,alignx right,aligny bottom");
		
		booking = new JButton("\u8BA2\u8BFE");
		
		booking.setFont(new Font("΢���ź�", Font.PLAIN, 13));
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
		
		JMenuItem miPassword = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		menu_User.add(miPassword);
		
		JMenuItem miPersonal = new JMenuItem("\u4FEE\u6539\u4E2A\u4EBA\u4FE1\u606F");
		menu_User.add(miPersonal);
		
		menu_Admin = new JMenu("\u7BA1\u7406\u5458");
		menuBar.add(menu_Admin);
		
		miLowBalance = new JMenuItem("\u4F4E\u4F59\u989D\u7528\u6237\u67E5\u8BE2");
		
		menu_Admin.add(miLowBalance);
		
		
		
		
		
	}
	

	public  UserFrame(int uid) {
		initialize();
		artClass = new ArtClass();
		
		classnames = artClass.getAllClasses();
		if(classnames != null) {
			new Functions().addItems(cblateclass, classnames);
		}
		else {
			cblateclass.addItem("û�пγ̴���");
		}
		
		
		days = artClass.findAvaiableDays(cblateclass.getSelectedItem().toString());
		if(days != null) {
			new Functions().addItems(cbday, days);
		}
		else {
			cbday.addItem("�ÿγ�û�а���ʱ��");
		}
		
		
		times = artClass.findAvaiableTimes(cblateclass.getSelectedItem().toString(),
				cbday.getSelectedItem().toString());
		if(times != null) {
			//��ʾδ��һ���¿ɶ��Ŀ�
			new Functions().addItems(cbtime, new Functions().getNewTimes(cbday.getSelectedItem().toString(), times));
			
		}
		else {
			cbtime.addItem("��ʱ���");
		}
		if(uid == 1) {
			//Ԥ�ع���Ա��ʼ����
			signin.setText("ǩ����Ա����");
			booking.setText("������Ա����");
			
			adminOnly(uid);
		}
		else {
			//Ԥ����ͨ�û���ʼ����
			

			String weekday = new Functions().getWeekOfDate(new Date());
			
			classes = artClass.findCurrentClass(weekday);
			if(classes != null) {
				new Functions().addItems(cbnowclass, classes);
			}
			else {
				cbnowclass.addItem("����Ŀζ�������");
				signin.setEnabled(false);
			}
		
			nowtimes = artClass.findAvaiableTimes(cbnowclass.getSelectedItem().toString(),
					weekday);
			if(nowtimes != null) {
				new Functions().addItems(cbnowtime, nowtimes);
			}
			else {
				cbnowtime.addItem("��ʱ���");
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
					cbday.addItem("�ÿγ�û�а���ʱ��");
				}
				times = artClass.findAvaiableTimes(cblateclass.getSelectedItem().toString(),
						cbday.getSelectedItem().toString());
				//
				if(times != null) {
					cbtime.removeAllItems();
					new Functions().addItems(cbtime, new Functions().getNewTimes(cbday.getSelectedItem().toString(), times));
				}
				else {
					cbtime.addItem("��ʱ���");
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
					cbtime.addItem("��ʱ���");
				}
			}
		});
		
		miQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmUser.dispose();
				new LoginApp();
			}
		});
		
		// ���ó�ʼλ��
		frmUser.setLocationRelativeTo(null);
		frmUser.setVisible(true);
	}

	//����Ա�������
	public void adminOnly(int uid) {
		
		//���ز���Ҫ�Ľ���Ԫ��
		menu_User.setVisible(false);		
		menu_Student.setVisible(false);
		lblStudent.setVisible(false);
		cbStudent.setVisible(false);
		
		Vector<Object> colNames = new Vector<Object>();
//		Vector<Vector<Object>> content = new Vector<Vector<Object>>();
		
		//���ù���Ա��ѯ�ı�ͷ������
		colNames.add("��ѯ");
		colNames.add("��Ϣ");
				
//		Vector<Object> dataVector = new Vector<Object>();
//		dataVector.add("һ������");
//		dataVector.addElement("�ڵȴ�...");
//				
//		content.add(dataVector);

				
		//��ʼ����
		String[][] tmpdata = {
				{"һ������","�ڵȴ�..."}
				};
		
		mydata = tmpdata;		
				
				
		//��ȡ��ǰѧ��������ʾ�ڱ���
			
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
						"���ڸ���ֵ���û����ᱻ��ʾ",
		                "��������ֵ", 
		                JOptionPane.PLAIN_MESSAGE,
		                null,
		                null, "25");

				int num = Integer.parseInt(max);
				
				//������ʾ��Ϣ��
				colNames.clear();
				colNames.add("΢���ǳ�");
				colNames.add("���");
				colNames.add("��ϵ�绰");
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
			
		booking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignupAndBooking newsb = new SignupAndBooking();
				java.util.List<String> userlist = newsb.getBookedList();
			
				//������ʾ��Ϣ��
				colNames.clear();
				colNames.add("ѧ������");
				colNames.add("�γ���");
				colNames.add("ʱ��");
				
				String[][] tmpdata= new String[userlist.size()/3][3];
				for(int i=0; i<userlist.size()/3;i++) {
					for(int j =0;j<3;j++) {
						
							tmpdata[i][j] = userlist.get(i*3+j);
						
							
					}	
				}	
				mydata = tmpdata;
				myTableModel.updateTable();
			}
		});
		
	}
	
	//�û��������
	public void userOnly(int uid) {
		
		menu_Admin.setVisible(false);
		myuid =uid;
		
		Vector<Object> colNames = new Vector<Object>();
		Vector<Vector<Object>> content = new Vector<Vector<Object>>();
		
		
		
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
					cbnowtime.addItem("��ʱ���");
				}
			}
		});
		

		
		student = new Student();
		
		String[] students = student.getStudentName(uid);
		for(int i=0;i<students.length;i++) {
			cbStudent.addItem(students[i]);
		}
		
		//��UID��ѯ��ֹ��ͬ�û����������
		sid = student.getSid(uid,cbStudent.getSelectedItem().toString());
		
		//����ѧ�������ѯ�ı�ͷ������
		colNames.add("");
		colNames.add("���");
		
		Vector<Object> dataVector = new Vector<Object>();
		dataVector.add("��ѧ������");
		dataVector.addElement("$"+new Double(student.getBalance(uid)));
		
		content.add(dataVector);

		//��ȡ��һ��vector�ĳ��ȣ���Ϊ��ά�����y,����vetor����ĿΪx
		
		
		mydata =new Functions().getArrays(content);
		
		
		
		//��ȡ��ǰѧ��������ʾ�ڱ���
	
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
        
		//���ΰ�ť��Ӧ�¼�
		booking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignupAndBooking newsb = new SignupAndBooking(sid, cblateclass.getSelectedItem().toString(), cbday.getSelectedItem().toString(), cbtime.getSelectedItem().toString(), "Booking");
				if(!newsb.isExist()){
					newsb.newSB();
					newsb.updateBalance(uid,BOOKINGFEE);
					//JOptionPane.showMessageDialog(null, "����"+cblateclass.getSelectedItem().toString()+"�ɹ�!");
					
					//���ö�����ɺ�ı�.3��,��ͷ����
					colNames.clear();
					colNames.add("");
					colNames.add("���");
					if(student.getBalance(uid) <= LOW_BALANCE_NOTICE) {
						JOptionPane.showMessageDialog(null, "�����ͣ��뾡���ֵ!");
					}
					String[][] tmpdata = {
							{"��ѧ��ԭ����","$"+new Double(student.getBalance(uid)+BOOKINGFEE)},
							{"����"+cblateclass.getSelectedItem().toString(),"-$"+BOOKINGFEE},
							{"��ѧ��������","$"+new Double(student.getBalance(uid))}
					};
					mydata = tmpdata;
					myTableModel.updateTable();
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "���Ѿ�Ԥ����"+cblateclass.getSelectedItem().toString()+"!");
				}
			}
		});
		
		signin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignupAndBooking newsb = new SignupAndBooking(sid, cbnowclass.getSelectedItem().toString(), new Functions().getWeekOfDate(new Date()), cbnowtime.getSelectedItem().toString(), "SignIn");
				//�ж��Ƿ�ǩ�����Ƿ��ж���
				if(!newsb.isExist()){
					if(newsb.isBooked()) {
						newsb.newSB();
						newsb.updateBalance(uid,newsb.getRestFee(cbnowclass.getSelectedItem().toString(),BOOKINGFEE));
						//JOptionPane.showMessageDialog(null, "ǩ��"+cblateclass.getSelectedItem().toString()+"�ɹ�!");
						
						//���ö�����ɺ�ı�.3��,��ͷ����
						colNames.clear();
						colNames.add("");
						colNames.add("���");
						
						if(student.getBalance(uid) <= LOW_BALANCE_NOTICE) {
							JOptionPane.showMessageDialog(null, "��ѧ�������ͣ��뾡���ֵ!");
						}
						
						String[][] tmpdata = {
								{"��ѧ��ԭ����","$"+new Double(student.getBalance(uid)+newsb.getRestFee(cbnowclass.getSelectedItem().toString(),BOOKINGFEE))},
								{"ǩ��"+cbnowclass.getSelectedItem().toString(),"-$"+newsb.getRestFee(cbnowclass.getSelectedItem().toString(),BOOKINGFEE)},
								{"��ѧ��������","$"+new Double(student.getBalance(uid))}
						};
						mydata = tmpdata;
						myTableModel.updateTable();
					}
					else {
						JOptionPane.showMessageDialog(null, "�㲢û��Ԥ��"+cbnowclass.getSelectedItem().toString()+"!");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "���Ѿ�ǩ����"+cbnowclass.getSelectedItem().toString()+"!");
				}
			}
		});
		
		cbStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sid = student.getSid(uid,cbStudent.getSelectedItem().toString());
				
				colNames.clear();
				colNames.add("");
				colNames.add("���");
				
				String[][] tmpdata = {
						{"������","$"+new Double(student.getBalance(uid))}
				};
				mydata = tmpdata;
				myTableModel.updateTable();
			}
			
		});
		
		miBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sid = student.getSid(uid,cbStudent.getSelectedItem().toString());
				
				colNames.clear();
				colNames.add("�γ���");
				colNames.add("����");
				colNames.add("��ʼʱ��");
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
				colNames.add("�γ���");
				colNames.add("����");
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
