package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	
	//���ӱ������ݿ⣬�ϴ�ʱ���������������getConnection2()����������
	public  static  Connection getConnection() {
		try {
			//1.��������
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String user = "root";
		String password = "root";
		String url = "jdbc:mysql://localhost:3306/dazuoye";
		Connection connection = null;
		try {
			//2.�������Ӷ���
			 connection = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}
	
	//3.�ر�����
	public static void close(Connection connection ) {
		try {
			if (connection != null) {
				connection.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void close(PreparedStatement preparedStatement ) {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void close(ResultSet resultSet ) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// �������������ݿ⣬�ϴ�ʱ��������������ĺ���������
	public static Connection getConnection2(){
		String driver = "com.mysql.jdbc.Driver";
		String username = "root";
		String password = "qunying";
		//System.getenv("MYSQL_HOST_S"); Ϊ�ӿ⣬ֻ��
		Connection con = null;
		
		String dbUrl = String.format("jdbc:mysql://%s:%s/%s", "szfusrvxydcc.mysql.sae.sina.com.cn", "10063", "zz");
		    try {
				Class.forName(driver).newInstance();
				con = DriverManager.getConnection(dbUrl, username, password);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    // ...
		return con;
	}
}
