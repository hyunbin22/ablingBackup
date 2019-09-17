package com.semi.report.model.service;

import java.sql.Connection;
import java.util.List;

import com.semi.report.model.dao.ReportDao;
import com.semi.member.model.vo.Member;
import com.semi.report.model.vo.Report;
import com.semi.report.model.vo.ReportUpload;

import common.template.JDBCTemplate;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.rollback;

public class ReportService {

	private static ReportDao dao = new ReportDao();
	
	public int registerReport(Report rp, Member m) {
		
		Connection conn = getConnection();
		int result = dao.registerReport(conn, rp, m);
		if(result > 0)
		{
			result = dao.selectSeqReport(conn);
			commit(conn); // 이거 꼬이면 순서 바꿔요
		}
		else
		{
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int regsterReportImage(ReportUpload ru, int seqNo) {
		Connection conn = getConnection();
		int result = dao.registerReportImage(conn, ru , seqNo);
		if(result > 0)
		{
			commit(conn);
		}
		else
		{
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public List<Report> selectAllReport(int cPage,int numPerPage) {
		Connection conn=getConnection();
		List<Report> list=dao.selectReportList(conn,cPage,numPerPage);
		close(conn);
		return list;
	}

	public int selectReportCount() {
		Connection conn=getConnection();
		int result=dao.selectReportCount(conn);
		close(conn);
		return result;
	}
	
	public int selectReportCount2() {
		Connection conn=getConnection();
		int result=dao.selectReportCount2(conn);
		close(conn);
		return result;
	}
	
	public int selectReportCount3(int mNum) {
		Connection conn=getConnection();
		int result=dao.selectReportCount3(conn,mNum);
		close(conn);
		return result;
	}

	public List<Report> selectAllReportComplete(int cPage, int numPerPage) {
		Connection conn=getConnection();
		List<Report> list=dao.selectReportCompleteList(conn,cPage,numPerPage);
		close(conn);
		return list;
	}

	public Report selectReportContent(int reportNo) {
		Connection conn=getConnection();
		Report rp = dao.selectReportContent(conn,reportNo);
		close(conn);
		return rp;
		
	}

	public ReportUpload selectReportUpload(int reportNo) {
		Connection conn=getConnection();
		ReportUpload rpu = dao.selectReportUpload(conn,reportNo);
		close(conn);
		return rpu;
	}

	public Report selectReportContent2(int reportNo) {
		Connection conn=getConnection();
		Report rp = dao.selectReportContent2(conn,reportNo);
		close(conn);
		return rp;
	}

	public int memberBlack(int mAttackerNum) {
		Connection conn=getConnection();
		int result = dao.memberBlack(conn,mAttackerNum);
		close(conn);
		return result;
	}

	public int memberUse(int mAttackerNum) {
		Connection conn=getConnection();
		int result = dao.memberUse(conn,mAttackerNum);
		close(conn);
		return result;
	}

	public List<Report> reportHistoyry(int cPage, int numPerPage, int mNum) {
		Connection conn=getConnection();
		List<Report> list=dao.reportHistory(conn, cPage, numPerPage, mNum);
		close(conn);
		return list;
	}

	public int regsterReport2(int attackNum,String rReply) {
		Connection conn = getConnection();
		int result = dao.registerReport2(conn, attackNum,rReply);
		close(conn);
		return result;
	}

	public Report seeReport(String title) {
		Connection conn = getConnection();
		Report rp = dao.seeReport(conn, title);
		close(conn);
		return rp;
	}




}
