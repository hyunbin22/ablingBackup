package com.semi.local.model.dao;

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
import com.semi.local.model.vo.Local;

public class LocalDao {

	private Properties prop=new Properties();
	
	public LocalDao() {
		String path=LocalDao.class.getResource("/sql/semi/local-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int enrollLocal(Connection conn, Local c) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("enrollLocal");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, c.getLocalCity());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<Local> selectLocal(Connection conn){
		Statement stmt=null;
		ResultSet rs=null;
		List<Local> list=new ArrayList();
		String sql="select * from tb_location";
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				Local l=new Local();
				l.setLocalNum(rs.getInt("localNum"));
				l.setLocalCity(rs.getString("local_city"));
				list.add(l);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return list;
	}
	
	public int updateLocal(Connection conn, String localCity, int localNum) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateLocal");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, localCity);
			pstmt.setInt(2, localNum);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteLocal(Connection conn, String localCity) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteLocal");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, localCity);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
}
