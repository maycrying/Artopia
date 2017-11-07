package com.artopia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SignupAndBooking {

	Dbcon db = new Dbcon();
	boolean updated = false;
	//24小时
	final static int ALLOWED_RESIGNUP_TIMEGAP = 24*60;
	//7天
	final static int ALLOWED_REBOOK_TIMEGAP = 7*24*60;
	
	int sid = 0;
	String classname =null;
	String duration =null;
	String day = null;
	String op =null;
	
	public SignupAndBooking(int stuid, String cname, String newday,String dur, String operation) {		
		sid = stuid;
		classname=cname;
		day = newday;
		duration =dur;
		op = operation;
	}
	

	public SignupAndBooking() {
		// TODO Auto-generated constructor stub
	}


	public void newSB() {
		
		Connection conn =db.connectDB();
		
		if(!updated) {
			try {
				Statement statement = conn.createStatement();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String classDate = null;
				String classTime = null;
				
				if(op == "SignIn") {
					//获得今天的日期
					classDate = dateFormat.format(new Date());
					classTime = duration.substring(0,duration.indexOf("-"));
				}
				else if(op == "Booking") {
					//获取订课完整日期的前面部分
					classDate =duration .substring(0, duration.indexOf(" "));
					//获取订课完整日期的后半部分
					classTime = duration.substring(duration.indexOf(" ")+1,duration.length());
				}
				statement.executeUpdate("INSERT INTO atp_sign_book(stu_id,sb_time,sb_classname,sb_classday,sb_stime,sb_operation,sb_classdate)"
						+"VALUES('"+sid+"',CURRENT_TIMESTAMP,'"+classname+"','"+day+"','"+classTime+"','"+op+"','"+classDate+"')");
			
				statement.close();
				conn.close();
		
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean isExist() {
		Connection conn =db.connectDB();
		
		try {
			Statement statement = conn.createStatement();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String classDate = null;
			String classTime = null;
			if(op == "SignIn") {
				//获得今天的日期
				classDate = dateFormat.format(new Date());
				classTime = duration.substring(0,duration.indexOf("-"));
			}
			else if(op == "Booking") {
				//获取订课完整日期的前面部分
				classDate =duration .substring(0, duration.indexOf(" "));
				//获取订课完整日期的后半部分
				classTime = duration.substring(duration.indexOf(" ")+1,duration.length());
			}
			
			//是否存在相同的订课信息
			ResultSet rSet = statement.executeQuery("SELECT sb_time FROM atp_sign_book WHERE stu_id = '"+sid
					+"' AND sb_classname = '"+classname+"' AND sb_classday = '"+day+"' AND sb_stime = '"+classTime
					+"' AND sb_operation = '"+op+"' AND sb_classdate = '"+classDate+"'");
			
			if(rSet.next()) {
				
				return true;
			}
			
			rSet.close();
			statement.close();
			conn.close();
		
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}


	public void updateBalance(int uid, int fee) {
		// TODO Auto-generated method stub
		Connection conn =db.connectDB();
		
		try {
			Statement statement = conn.createStatement();

			statement.executeUpdate("UPDATE atp_user SET user_balance = user_balance-"+fee+" WHERE user_id = '"+uid+"'");

			
			statement.close();
			conn.close();
		
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}


	public boolean isBooked() {
		// TODO Auto-generated method stub
		Connection conn =db.connectDB();
		
		try {
			Statement statement = conn.createStatement();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String classDate = null;
			String classTime = null;
			
			//获得今天的日期
			classDate = dateFormat.format(new Date());
			classTime = duration.substring(0,duration.indexOf("-"));
			
			
			//是否存在订课信息
			ResultSet rSet = statement.executeQuery("SELECT sb_time FROM atp_sign_book WHERE stu_id = '"+sid
					+"' AND sb_classname = '"+classname+"' AND sb_classday = '"+day+"' AND sb_stime = '"+classTime
					+"' AND sb_operation = 'Booking' AND sb_classdate = '"+classDate+"'");
			
			if(rSet.next()) {
				
				//去除存在的订课信息
				
				statement.executeUpdate("DELETE FROM atp_sign_book WHERE stu_id = '"+sid
						+"' AND sb_classname = '"+classname+"' AND sb_classday = '"+day+"' AND sb_stime = '"+classTime
						+"' AND sb_operation = 'Booking' AND sb_classdate = '"+classDate+"'");
				
				return true;
			}
			
			rSet.close();
			statement.close();
			conn.close();
		
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	//获取signin时需要缴纳的剩余费用
	public int getRestFee(String classname,int postFee) {
		// TODO Auto-generated method stub
		int fee = 0;
		Connection conn =db.connectDB();
		
		
		try {
			Statement statement = conn.createStatement();

			ResultSet rSet = statement.executeQuery("SELECT class_fee FROM atp_class WHERE class_name = '"+classname+"'");
			
			if(rSet.next()) {
				fee = rSet.getInt(1);
			}
			
			statement.close();
			conn.close();
		
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return fee-postFee;
	}
	
	public List<String> getBookedList() {
		// TODO Auto-generated method stub
		Connection conn =db.connectDB();
		
		List<String> userlist = new ArrayList<String>();
		try {
			Statement statement = conn.createStatement();
//			ResultSet rSet = statement.executeQuery("SELECT stu_fname,stu_lname,sb_classname,sb_classdate,sb_stime"+
//					"FROM atp_sign_book, atp_student" + 
//					"WHERE sb_classdate >= CURRENT_TIMESTAMP AND sb_operation = 'Booking' AND atp_sign_book.stu_id = atp_student.stu_id");
			
			ResultSet rSet = statement.executeQuery("SELECT stu_fname,stu_lname,sb_classname, sb_classdate, sb_stime "
					+ "FROM atp_sign_book, atp_student "
					+ "WHERE "
					+ "sb_classdate >= CURRENT_TIMESTAMP AND sb_operation = 'Booking'"
					+ "ORDER BY sb_classdate, sb_stime");
			
			while(rSet.next()) {
				userlist.add(rSet.getString(1)+" "+rSet.getString(2));
				userlist.add(rSet.getString(3));
				userlist.add(rSet.getString(4)+" "+rSet.getString(5));
			}
			rSet.close();
			statement.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return userlist;
	}
}
