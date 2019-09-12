package com.semi.report.model.service;

import java.sql.Connection;

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

	private ReportDao dao = new ReportDao();
	
	public int regsterReport(Report rp, Member m) {
		
		Connection conn = getConnection();
		int result = dao.registerReport(conn, rp, m);
		if(result > 0)
		{
			commit(conn);
			result = dao.selectSeqReport(conn, m.getmNum());
		}
		else
		{
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int regsterReportImage(ReportUpload ru, int reportNum) {
		Connection conn = getConnection();
		int result = dao.registerReportImage(conn, ru , reportNum);
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



}
