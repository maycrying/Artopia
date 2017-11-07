package com.artopia;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
	
//	private static final int ADMIN_TYPE = 1;
	Dbcon db = new Dbcon();
	
	public Account() {
		
	}

	public void createAccount(String username,String password,String mobile,String wechat) {
			
		Connection conn =db.connectDB();
		
		try {
			Statement statement = conn.createStatement();
		
			statement.executeUpdate("INSERT INTO atp_user(user_name,user_password,user_mobile,user_wechat)"
						+"VALUES('"+username+"','"+password+"','"+mobile+"','"+wechat+"')");
			
			statement.close();
			conn.close();
		
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public boolean isDuplicate(String username) {
		
		Connection conn =db.connectDB();
		try {
			Statement statement = conn.createStatement();
			ResultSet rSet = statement.executeQuery("SELECT * FROM atp_user WHERE user_name='"
			+username+"'");
			
			if(!rSet.next()) {
				return false;
			}
			rSet.close();
			statement.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return true;
		
	}

	public int getNewUserID() {
		
		Connection conn =db.connectDB();
		int useid = 0;
		try {
			Statement statement = conn.createStatement();
			ResultSet rSet = statement.executeQuery("SELECT MAX(user_id) FROM `atp_user`");
			if(rSet.next()) {
			useid = rSet.getInt(1);
			rSet.close();
			statement.close();
			try {
				conn.close();
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return useid;
	}
	public int getUserID(String username,String password) {
		
		Connection conn =db.connectDB();
		
		int uid=0;
		try {
			Statement statement = conn.createStatement();
			ResultSet rSet = statement.executeQuery("SELECT `user_id` FROM `atp_user` WHERE `user_name` = '"
			+username+"' AND `user_password` ='"+password+"'");
			if(rSet.next()) {
//				if(isAdmin(username, password)) {
//					//管理员uid为1
//					uid = 1;
//				}
//				else {
					uid = rSet.getInt(1);					
//				}
				
			}
			rSet.close();	
			statement.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return uid;
	}
	
//	private boolean isAdmin(String username,String password) {
//		
//		Connection conn =db.connectDB();
//		
//		try {
//			Statement statement = conn.createStatement();
//			ResultSet rSet = statement.executeQuery("SELECT `user_type` FROM `atp_user` WHERE `user_name` = '"
//			+username+"' AND `user_password` ='"+password+"'");
//			if(rSet.getInt(1) == ADMIN_TYPE) {
//				return true;			
//			}
//			rSet.close();
//			statement.close();
//			conn.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return false;
//	}

	public List<String> getLowBalanceList(int balance) {
		// TODO Auto-generated method stub
		Connection conn =db.connectDB();
		
		List<String> userlist = new ArrayList<String>();
		try {
			Statement statement = conn.createStatement();
			ResultSet rSet = statement.executeQuery("SELECT user_wechat, user_balance, user_mobile  FROM `atp_user` WHERE `user_balance` <= '"
			+balance+"' AND `user_id` >1");
			while(rSet.next()) {
				userlist.add(rSet.getString(1));
				userlist.add(rSet.getString(2));
				userlist.add(rSet.getString(3));
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
