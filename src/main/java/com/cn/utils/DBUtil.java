package com.cn.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBUtil {
	
	//连接数据库
	public Connection getCon() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shiro?useSSL=true","root","1234567890");
		return con;
	}
	
	public void closeCon(Connection con) {
		if(con!=null)
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) {
		DBUtil db=new DBUtil();
		Connection con=null;
		try {
			con=db.getCon();
			System.out.println("连接成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("连接失败");
		}finally {
			db.closeCon(con);
		}
	}

}
