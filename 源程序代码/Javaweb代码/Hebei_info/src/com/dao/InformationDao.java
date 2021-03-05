package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Information;
import com.util.DBUtil;

public class InformationDao {
	
	//插入抄袭字段
	public void add(String content,int index) {
		// TODO Auto-generated method stub
		Connection connection = DBUtil.getConnection();
		String sql = "INSERT INTO copy (copy,info_index) VALUES " + "(?,?)";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, content);
			ps.setInt(2, index);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
	}
	public List<Information> loadAll() {
		System.out.println("姚雅丽");
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT * FROM info_tech";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Information> info = new ArrayList<Information>();
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Information c = new Information();
				c.setIndex(rs.getInt("index"));
				c.setContent(rs.getString("content"));
				c.setType1(rs.getString("title1"));
				c.setType2(rs.getString("title2"));
				c.setTitle(rs.getString("title3"));
				c.setKeyword(rs.getString("keyword"));
				c.setType(rs.getString("type"));
				c.setAbstracted(rs.getString("abstract"));
				info.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
		System.out.println(info);
		return info;
	}
	
	
	public List<Information> loadMohu(String name) {
		System.out.println("姚雅丽");
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT * FROM info_tech where title3 like '%"+name+"%'";
		System.out.println(sql);
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Information> info = new ArrayList<Information>();
		try {
			ps = connection.prepareStatement(sql);
			//ps.setString(1, '%'+name+'%');
			rs = ps.executeQuery();
			while(rs.next()) {
				Information c = new Information();
				c.setIndex(rs.getInt("index"));
				c.setContent(rs.getString("content"));
				c.setType1(rs.getString("title1"));
				c.setType2(rs.getString("title2"));
				c.setTitle(rs.getString("title3"));
				c.setKeyword(rs.getString("keyword"));
				c.setType(rs.getString("type"));
				c.setAbstracted(rs.getString("abstract"));
				System.out.println(c.getContent());
				info.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
		System.out.println(info);
		return info;
	}
	
	public List<Information> loadJing(String name) {
		System.out.println("姚雅丽");
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT * FROM info_tech where keyword like ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Information> info = new ArrayList<Information>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, '%'+name+'%');
			rs = ps.executeQuery();
			while(rs.next()) {
				Information c = new Information();
				c.setIndex(rs.getInt("index"));
				c.setContent(rs.getString("content"));
				c.setType1(rs.getString("title1"));
				c.setType2(rs.getString("title2"));
				c.setTitle(rs.getString("title3"));
				c.setKeyword(rs.getString("keyword"));
				c.setType(rs.getString("type"));
				c.setAbstracted(rs.getString("abstract"));
				info.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
		System.out.println(info);
		return info;
	}
	
	public List<Information> Auto_load(String name) {
		System.out.println("姚雅丽");
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT * FROM info_tech where type= ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Information> info = new ArrayList<Information>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()) {
				Information c = new Information();
				c.setIndex(rs.getInt("index"));
				c.setContent(rs.getString("content"));
				c.setType1(rs.getString("title1"));
				c.setType2(rs.getString("title2"));
				c.setTitle(rs.getString("title3"));
				c.setKeyword(rs.getString("keyword"));
				c.setType(rs.getString("type"));
				c.setAbstracted(rs.getString("abstract"));
				info.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
		System.out.println(info);
		return info;
	}
	
	
	public List<Information> Select_type(String name) {
		System.out.println("姚雅丽");
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT * FROM info_tech where title2= ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Information> info = new ArrayList<Information>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()) {
				Information c = new Information();
				c.setIndex(rs.getInt("index"));
				c.setContent(rs.getString("content"));
				c.setType1(rs.getString("title1"));
				c.setType2(rs.getString("title2"));
				c.setTitle(rs.getString("title3"));
				c.setKeyword(rs.getString("keyword"));
				c.setType(rs.getString("type"));
				c.setAbstracted(rs.getString("abstract"));
				info.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
		System.out.println(info);
		return info;
	}
	public Information load(int index) {
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT * FROM info_tech WHERE `index` = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Information c = new Information();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, index);
			rs = ps.executeQuery();
			while(rs.next()) {
				c.setIndex(rs.getInt("index"));
				c.setContent(rs.getString("content"));
				c.setType1(rs.getString("title1"));
				c.setType2(rs.getString("title2"));
				c.setTitle(rs.getString("title3"));
				c.setKeyword(rs.getString("keyword"));
				c.setType(rs.getString("type"));
				c.setAbstracted(rs.getString("abstract"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
		return c;
	}
	
	public String loadcpoy(int index) {
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT * FROM copy WHERE info_index = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		//Information c = new Information();
		String copy="";
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, index);
			rs = ps.executeQuery();
			while(rs.next()) {
				copy=rs.getString("copy");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
		return copy;
	}
	public List<String> loadCopy(int index) {
		System.out.println("姚雅丽");
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT * FROM copy WHERE info_index = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> copy = new ArrayList<String>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, index);
			rs = ps.executeQuery();
			while(rs.next()) {
				
				copy.add(rs.getString("copy"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
		System.out.println(copy);
		return copy;
	}
	
	public List<String> loadCopyAll() {
		System.out.println("姚雅丽");
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT * FROM copy";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> copy = new ArrayList<String>();
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				
				copy.add(rs.getString("copy"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
		System.out.println(copy);
		return copy;
	}
} 
