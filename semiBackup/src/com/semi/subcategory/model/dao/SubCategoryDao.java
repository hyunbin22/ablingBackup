package com.semi.subcategory.model.dao;

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
import com.semi.subcategory.model.vo.SubCategory;

public class SubCategoryDao {
	
	private Properties prop=new Properties();
	
	public SubCategoryDao() {
		String path=SubCategoryDao.class.getResource("/sql/semi/subcategory-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int enrollSubCategory(Connection conn, SubCategory sc, Category c) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("enrollSubCategory");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, sc.getSubName());
			pstmt.setInt(2, c.getScNum());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<SubCategory> selectSubCategory(Connection conn){
		Statement stmt=null;
		ResultSet rs=null;
		List<SubCategory> list=new ArrayList();
		String sql="select * from tb_subcategory";
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				SubCategory sc=new SubCategory();
				sc.setSubNum(rs.getInt("subNum"));
				sc.setSubName(rs.getString("SubName"));
				list.add(sc);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return list;
	}
	
	public int updateSubCategory(Connection conn, String subName, int subNum) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateSubCategory");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, subName);
			pstmt.setInt(2, subNum);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteSubCategory(Connection conn, String subName) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteSubCategory");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, subName);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public List<SubCategory> selectSubCategory(Connection conn,int scNum){
		Statement stmt=null;
		ResultSet rs=null;
		List<SubCategory> list=new ArrayList();
		String sql="select subname from tb_subcategory where scnum = " + scNum;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				SubCategory sc=new SubCategory();
				sc.setSubName(rs.getString("subName"));
				list.add(sc);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return list;
	}
}
