package com.semi.category.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.semi.category.model.vo.Category;

public class CategoryDao {
	
	private Properties prop=new Properties();
	
	public CategoryDao() {
		String path=CategoryDao.class.getResource("/sql/semi/category-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	
	public int enrollCategory(Connection conn, Category c) {
		PreparedStatement pstmt = null;
		int result=0;
		String sql=prop.getProperty("enrollCategory");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, c.getScName());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
	public List<Category> selectCategory(Connection conn){
		Statement stmt=null;
		ResultSet rs=null;
		List<Category> list=new ArrayList();
		String sql="select * from tb_category";
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				Category c=new Category();
				c.setScNum(rs.getInt("scNum"));
				c.setScName(rs.getString("ScName"));
				list.add(c);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return list;
	}
	
	public int updateCategory(Connection conn, String scName, int scNum) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateCategory");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, scName);
			pstmt.setInt(2, scNum);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteCategory(Connection conn, String scName) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteCategory");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, scName);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
}
