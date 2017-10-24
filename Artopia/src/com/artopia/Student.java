package com.artopia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

	public String[] getStudentName(int uid) {
		
		Connection conn =db.connectDB();
		
		try {
			Statement statement= conn.createStatement();
			ResultSet rSet = statement.executeQuery("SELECT `stu_fname`,`stu_lname` FROM `atp_student` WHERE `user_id` = '"
			+uid+"'");
			
			List<String> list=new ArrayList<String>();
			
			while(rSet.next()) {
				list.add(rSet.getString(1)+" "+rSet.getString(2));
			}
			
			rSet.close();
			statement.close();
			conn.close();
			
			return new Functions().getList(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public int getBalance(int sid) {
		
		Connection conn =db.connectDB();
		
		try {
			Statement statement= conn.createStatement();
			ResultSet rSet = statement.executeQuery("SELECT `stu_balance` FROM `atp_student` WHERE `stu_id` = '"
			+sid+"'");
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
	
	public int getSid(int uid,String studentName) {
		Connection conn =db.connectDB();
		//将学生姓名拆分成，名和姓
		String fname = studentName.substring(0,studentName.indexOf(" "));
		String lname = studentName.substring(studentName.indexOf(" ")+1,studentName.length());
		try {
			Statement statement= conn.createStatement();
			ResultSet rSet = statement.executeQuery("SELECT `stu_id` FROM `atp_student` WHERE `user_id` = '"
			+uid+"' AND `stu_fname` = '"+fname+"' AND `stu_lname` = '"+lname+"'");
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
