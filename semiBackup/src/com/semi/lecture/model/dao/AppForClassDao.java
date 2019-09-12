package com.semi.lecture.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.semi.lecture.model.vo.AppForClass;
import com.semi.lecture.model.vo.Lecture;
import com.semi.member.model.dao.MemberDao;
import com.semi.member.model.vo.Member;

public class AppForClassDao {

	private Properties prop = new Properties();
	public AppForClassDao() {
		String path = AppForClassDao.class.getResource("/sql/semi/appforclass-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public int insertAppForClass(Connection conn, int lecNum, String mId, String text, int price) {
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAppForClass");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, lecNum);
			pstmt.setString(3, text);
			pstmt.setInt(4, price);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;

		
	}

	//신청 등록 후 신청내용 검색(return sNum)
	
	public AppForClass selectAppForClass(Connection conn, int sNum, String mId, int lecNum) {
		Member m = new MemberDao().selectMember(conn, mId);
		Lecture lec = new LectureDao().lectureView(conn, lecNum);
		
		Statement stmt = null;
		String sql = "select * from tb_app_for_class where snum="+sNum;
		AppForClass afc = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				afc.setsNum(rs.getInt("sNum"));
				afc.setmNum(rs.getInt("mnum"));
				afc.setLecNum(rs.getInt("lecnum"));
				afc.setsText(rs.getString("sText"));
				afc.setsPrice(rs.getInt("sprice"));
				afc.setsPayment(rs.getString("spayment").charAt(0));
				afc.setAfcCheck(rs.getString("afcCheck").charAt(0));
				afc.setMember(m);
				afc.setLecture(lec);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return afc;
	}

}
