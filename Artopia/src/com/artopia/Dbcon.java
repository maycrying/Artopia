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
			}     //����MYSQL JDBC��������       

	   
	        try {    
	          Connection connect = DriverManager.getConnection(url,user,password);    
	               //����URLΪ   jdbc:mysql//��������ַ/���ݿ���  �������2�������ֱ��ǵ�½�û���������    
	        
	         return connect;
	            
	        }    
	        catch (Exception e) {    
	          System.out.print("get data error!");    
	          e.printStackTrace();    
	        }
			return null;    
	      }   
	  
}  
