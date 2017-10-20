package com.artopia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Student {

	Dbcon db = new Dbcon();
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	public void addStudent(int userid,String lname,String fname,String classname) {
		
		
		Connection conn =db.connectDB();
		
		try {
			Statement statement= conn.createStatement();
			//stmt.executeUpdate(atp_student.sql);
			statement.executeUpdate("INSERT INTO atp_student(user_id,stu_lname,stu_fname,class_name)"
			+"VALUES('"+userid+"','"+lname+"','"+fname+"','"+classname+"')");
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getStudentName(int uid) {
		
		Connection conn =db.connectDB();
		
		try {
			Statement statement= conn.createStatement();
			ResultSet rSet = statement.executeQuery("SELECT `stu_fname`,`stu_lname` FROM `atp_student` WHERE `user_id` = '"
			+uid+"'");
			if(rSet.next()) {
				return (rSet.getString(1)+rSet.getString(2));
			}
			rSet.close();
			statement.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public int getBalance(int uid) {
		
		Connection conn =db.connectDB();
		
		try {
			Statement statement= conn.createStatement();
			ResultSet rSet = statement.executeQuery("SELECT `stu_balance` FROM `atp_student` WHERE `user_id` = '"
			+uid+"'");
			if(rSet.next()) {
				return rSet.getInt(1);
			}
			rSet.close();
			statement.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}
	
	public int getSid(int uid) {
		Connection conn =db.connectDB();
		
		try {
			Statement statement= conn.createStatement();
			ResultSet rSet = statement.executeQuery("SELECT `stu_id` FROM `atp_student` WHERE `user_id` = '"
			+uid+"'");
			if(rSet.next()) {
				return rSet.getInt(1);
			}
			rSet.close();
			statement.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}
}
