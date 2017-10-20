package com.artopia;

import java.sql.*;  
public class Dbcon {

	  
	  public Connection connectDB() {    
	      String url= "jdbc:mysql://localhost:3306/db_artopia?useSSL=false&useUnicode=true&characterEncoding=utf-8";
	      String user="root";
	      String password="lebin1986";
	      
	        try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}     //加载MYSQL JDBC驱动程序       

	   
	        try {    
	          Connection connect = DriverManager.getConnection(url,user,password);    
	               //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码    
	        
	         return connect;
	            
	        }    
	        catch (Exception e) {    
	          System.out.print("get data error!");    
	          e.printStackTrace();    
	        }
			return null;    
	      }   
	  
}  
