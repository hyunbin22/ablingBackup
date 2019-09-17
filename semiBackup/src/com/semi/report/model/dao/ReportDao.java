package com.semi.report.model.dao;

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

import com.semi.member.model.dao.MemberDao;
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

	public int registerReportImage(Connection conn, ReportUpload ru, int mNum) {
		PreparedStatement pstmt = null;
		System.out.println("reportNum : " + mNum);
		int result = 0;
		String sql = prop.getProperty("registerReportImage");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mNum);
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

	public int selectReportCount(Connection conn) {
		Statement stmt=null;
		ResultSet rs=null;
		int result=0;
		String sql="select count(*) from tb_report where report_check = 'N'";
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next())
			{
				result=rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}return result;
	}
	
	public int selectReportCount2(Connection conn) {
		Statement stmt=null;
		ResultSet rs=null;
		int result=0;
		String sql="select count(*) from tb_report where report_check = 'Y'";
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next())
			{
				result=rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}return result;
	}
	
	public int selectReportCount3(Connection conn, int mNum) {
		Statement stmt=null;
		ResultSet rs=null;
		int result=0;
		String sql="select count(*) from tb_report where mreporter_num = '" + mNum + "'";
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next())
			{
				result=rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}return result;
	}

	public List<Report> selectReportList(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Report> list=new ArrayList();
		Member m = new Member();
		MemberDao dao = new MemberDao();
		
		String sql=prop.getProperty("selectReportList");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Report rp = new Report();
				rp.setReportNum(rs.getInt("report_num"));
				rp.setmReporterNum(rs.getInt("mreporter_num"));
				rp.setmAttackerNum(rs.getInt("mattacker_num"));
				rp.setReportTitle(rs.getString("report_title"));
				rp.setReportContent(rs.getString("report_content"));
				rp.setReportCheck(rs.getString("report_check").charAt(0));
				rp.setReportDate(rs.getDate("report_date"));
				rp.setMember(dao.selectMemberMnum(conn, rs.getInt("mreporter_num")));
				list.add(rp);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}

	public List<Report> selectReportCompleteList(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Report> list=new ArrayList();
		Member m = new Member();
		MemberDao dao = new MemberDao();
		
		String sql=prop.getProperty("selectReportList2");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Report rp = new Report();
				rp.setReportNum(rs.getInt("report_num"));
				rp.setmReporterNum(rs.getInt("mreporter_num"));
				rp.setmAttackerNum(rs.getInt("mattacker_num"));
				rp.setReportTitle(rs.getString("report_title"));
				rp.setReportContent(rs.getString("report_content"));
				rp.setReportCheck(rs.getString("report_check").charAt(0));
				rp.setReportDate(rs.getDate("report_date"));
				rp.setMember(dao.selectMemberMnum(conn, rs.getInt("mreporter_num")));
				list.add(rp);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}

	public Report selectReportContent(Connection conn, int reportNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Report rp=null;
		Member m = new Member();
		MemberDao dao = new MemberDao();
		String sql=prop.getProperty("selectReport");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, reportNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				rp=new Report();
				rp.setReportNum(rs.getInt("report_num"));
				rp.setmReporterNum(rs.getInt("mreporter_num"));
				rp.setmAttackerNum(rs.getInt("mattacker_num"));
				rp.setReportTitle(rs.getString("report_title"));
				rp.setReportContent(rs.getString("report_content"));
				rp.setReportCheck(rs.getString("report_check").charAt(0));
				rp.setReportDate(rs.getDate("report_date"));
				rp.setReportReason(rs.getString("report_reason"));
				rp.setMember(dao.selectMemberMnum(conn, rs.getInt("mreporter_num")));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return rp;
	}

	public ReportUpload selectReportUpload(Connection conn, int reportNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ReportUpload rpu=null;
		String sql = prop.getProperty("selectReportUpload");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reportNo);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				rpu = new ReportUpload();
				rpu.setFileOriName(rs.getString("up_report_org_filename"));
				rpu.setFileReNmae(rs.getString("up_report_re_filename"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return rpu;
	}

	public Report selectReportContent2(Connection conn, int reportNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Report rp=null;
		Member m = new Member();
		MemberDao dao = new MemberDao();
		String sql=prop.getProperty("selectReport");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, reportNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				rp=new Report();
				rp.setReportNum(rs.getInt("report_num"));
				rp.setmReporterNum(rs.getInt("mreporter_num"));
				rp.setmAttackerNum(rs.getInt("mattacker_num"));
				rp.setReportTitle(rs.getString("report_title"));
				rp.setReportContent(rs.getString("report_content"));
				rp.setReportCheck(rs.getString("report_check").charAt(0));
				rp.setReportDate(rs.getDate("report_date"));
				rp.setReportReason(rs.getString("report_reason"));
				rp.setMember(dao.selectMemberMnum(conn, rs.getInt("mattacker_num")));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return rp;
	}

	public int memberBlack(Connection conn, int mAttackerNum) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result = 0;
		String sql=prop.getProperty("memberBlack");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, mAttackerNum);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}

	public int memberUse(Connection conn, int mAttackerNum) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result = 0;
		String sql=prop.getProperty("memberUse");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, mAttackerNum);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}

	public List<Report> reportHistory(Connection conn, int cPage, int numPerPage, int mNum) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Report rp = null;
		List<Report> list=new ArrayList();
		String sql=prop.getProperty("reportHistory");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, mNum);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				rp=new Report();
				rp.setReportNum(rs.getInt("report_num"));
				rp.setmReporterNum(rs.getInt("mreporter_num"));
				rp.setmAttackerNum(rs.getInt("mattacker_num"));
				rp.setReportTitle(rs.getString("report_title"));
				rp.setReportContent(rs.getString("report_content"));
				rp.setReportCheck(rs.getString("report_check").charAt(0));
				rp.setReportDate(rs.getDate("report_date"));
				rp.setReportReason(rs.getString("report_reason"));
				list.add(rp);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	public int selectSeqReport(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "select seq_report.currval from dual";
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

	public int registerReport2(Connection conn, int attkNum,String rReply) {
		
		PreparedStatement pstmt = null;
		int result=0;
		String sql=prop.getProperty("registerReport2");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rReply);
			pstmt.setInt(2, attkNum);
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

	public Report seeReport(Connection conn, String title) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Report rp = null;
		Member m = new Member();
		MemberDao dao = new MemberDao();
		String sql=prop.getProperty("seeReport");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, title);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				rp=new Report();
				rp.setReportNum(rs.getInt("report_num"));
				rp.setmReporterNum(rs.getInt("mreporter_num"));
				rp.setmAttackerNum(rs.getInt("mattacker_num"));
				rp.setReportTitle(rs.getString("report_title"));
				rp.setReportContent(rs.getString("report_content"));
				rp.setReportCheck(rs.getString("report_check").charAt(0));
				rp.setReportDate(rs.getDate("report_date"));
				rp.setReportReason(rs.getString("report_reason"));
				rp.setMember(dao.selectMemberMnum(conn, rs.getInt("mattacker_num")));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return rp;
	}
	

}
