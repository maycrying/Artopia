package com.artopia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	

	public void newSB() {
		
		Connection conn =db.connectDB();
		
		if(!updated) {
			try {
				Statement statement = conn.createStatement();
				String nextclass = new CommonUse().getNextDate(day,0);
				statement.executeUpdate("INSERT INTO atp_sign_book(stu_id,sb_time,sb_classname,sb_classday,sb_duration,sb_operation,sb_classdate)"
						+"VALUES('"+sid+"',CURRENT_TIMESTAMP,'"+classname+"','"+day+"','"+duration+"','"+op+"','"+nextclass+"')");
			
				statement.close();
				conn.close();
		
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean isDuplicate() {
		Connection conn =db.connectDB();
		
		try {
			Statement statement = conn.createStatement();
			
			//是否存在相同的订课信息
			ResultSet rSet = statement.executeQuery("SELECT sb_time FROM atp_sign_book WHERE stu_id = '"+sid
					+"' AND sb_classname = '"+classname+"' AND sb_classday = '"+day+"' AND sb_duration = '"+duration
					+"' AND sb_operation = '"+op+"'");
			
			if(!rSet.next()) {
				
				return false;
			}
			else {
				String time =rSet.getString(1);
				rSet = statement.executeQuery("SELECT TIMESTAMPDIFF(MINUTE,'"+time+"',CURRENT_TIMESTAMP)");
				
				
				int ALLOWED_TIMEGAP;
				if(op == "Signup") {
					//离上次signup时间超过24小时，则认为是新的signup
					ALLOWED_TIMEGAP = ALLOWED_RESIGNUP_TIMEGAP;
				}
				else if(op == "Booking"){
					//离上次Book时间超过7天，则认为是新的Booking
					ALLOWED_TIMEGAP = ALLOWED_REBOOK_TIMEGAP;
				}
				else {
					ALLOWED_TIMEGAP = 0;
				}
				if(rSet.next() && rSet.getInt(1)>ALLOWED_TIMEGAP) {
					statement.executeUpdate("UPDATE atp_sign_book SET sb_time = CURRENT_TIMESTAMP WHERE stu_id = '"
							+sid+"' AND sb_classname = '"+classname+"' AND sb_classday = '"+day+"' AND sb_duration = '"
							+duration+"' AND sb_operation = '"+op+"'");
					updated =true;
					return false;
				}
			}
			
			rSet.close();
			statement.close();
			conn.close();
		
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
}
