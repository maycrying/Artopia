package com.artopia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class ArtClass {
	
	Dbcon db = new Dbcon();
	
	public ArtClass() {
		// TODO Auto-generated constructor stub
	}

	public String[] findCurrentClass(String day) {
		

		Connection conn =db.connectDB();
		
		try {
			SimpleDateFormat sFormat = new SimpleDateFormat("HH:mm:ss");
			String time = sFormat.format(new Date());
			Statement statement = conn.createStatement();
			ResultSet rSet = statement.executeQuery("SELECT `class_name` FROM `atp_class` WHERE `class_day` = '"
					+day+"' AND `class_etime` > '"+ time+"'");

			List<String> list=new ArrayList<String>();
			while (rSet.next()) {
				list.add(rSet.getString(1));
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

	public String[] getAllClasses() {
		
		Connection conn =db.connectDB();
		
		try {
			
			Statement statement = conn.createStatement();
			ResultSet rSet = statement.executeQuery("SELECT `class_name` FROM `atp_class`");

			List<String> list=new ArrayList<String>();
			while (rSet.next()) {
				list.add(rSet.getString(1));
			}
			
			rSet.close();
			statement.close();
			conn.close();
			
			return new Functions().noDuplicate(new Functions().getList(list));  
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public String[] findAvaiableDays(String classname) {
		
		Connection conn =db.connectDB();
		
		try {
			
			Statement statement = conn.createStatement();
			ResultSet rSet = statement.executeQuery("SELECT `class_day` FROM `atp_class` WHERE `class_name` = '"+
			classname+"'");

			
			List<String> list=new ArrayList<String>();
			while (rSet.next()) {
				list.add(rSet.getString(1));
			}
			
			rSet.close();
			statement.close();
			conn.close();
			
			return new Functions().noDuplicate(new Functions().getList(list)); 
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public String[] findAvaiableTimes(String classname, String day) {
		// TODO Auto-generated method stub
		Connection conn =db.connectDB();
		
		try {
			
			Statement statement = conn.createStatement();
			ResultSet rSet = statement.executeQuery("SELECT `class_stime`,`class_etime` FROM `atp_class` WHERE "
					+ "`class_name` = '"+classname+"' AND `class_day` = '"+day+"'");

			
			List<String> list=new ArrayList<String>();
			while (rSet.next()) {
				list.add(rSet.getString(1)+"-"+rSet.getString(2));
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
}
