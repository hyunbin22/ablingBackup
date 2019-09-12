package com.semi.report.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.semi.member.model.vo.Member;
import com.semi.mento.model.dao.MentoDao;
import com.semi.report.model.vo.Report;
import com.semi.report.model.vo.ReportUpload;

public class ReportDao {
	
	private Properties prop = new Properties();
	
	public ReportDao() {
		String path=MentoDao.class.getResource("/sql/semi/report-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public int registerReport(Connection conn, Report rp, Member m) {
		
		PreparedStatement pstmt = null;
		int result=0;
		String sql=prop.getProperty("registerReport");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m.getmNum());
			pstmt.setString(2, rp.getReportId());
			pstmt.setString(3, rp.getReportTitle());
			pstmt.setString(4, rp.getReportContent());
			result = pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectSeqReport(Connection conn, int mNum) {
		
		Statement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "select report_num from tb_report where MREPORTER_NUM = " + mNum;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				result = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return result;
	}

	public int registerReportImage(Connection conn, ReportUpload ru, int reportNum) {
		PreparedStatement pstmt = null;
		System.out.println("reportNum : " + reportNum);
		int result = 0;
		String sql = prop.getProperty("registerReportImage");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reportNum);
			pstmt.setString(2, ru.getFileOriName());
			pstmt.setString(3, ru.getFileReNmae());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}

}
