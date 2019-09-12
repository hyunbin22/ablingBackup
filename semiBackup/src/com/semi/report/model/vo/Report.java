package com.semi.report.model.vo;

import java.sql.Date;
import java.util.List;

public class Report {
	
	private int reportNum; // 신고번호
	private int mReporterNum; // 신고하는 사람 번호
	private int mAttackerNum; // 신고당하는사람 번호
	private String reportId; // 신고 아이디
	private String reportTitle; // 신고 제목
	private String reportContent; //신고 내용
	private char reportCheck; // 신고 결정 여부
	private Date reportDate; // 신고 날짜
	
	private List<ReportUpload> reportUpList;
	
	public Report(int reportNum, int mReporterNum, int mAttackerNum, String reportId, String reportTitle,
			String reportContent, char reportCheck, Date reportDate, List<ReportUpload> reportUpList) {
		super();
		this.reportNum = reportNum;
		this.mReporterNum = mReporterNum;
		this.mAttackerNum = mAttackerNum;
		this.reportId = reportId;
		this.reportTitle = reportTitle;
		this.reportContent = reportContent;
		this.reportCheck = reportCheck;
		this.reportDate = reportDate;
		this.reportUpList = reportUpList;
	}


	public Report(String reportId ,String reportTitle, String reportContent) {
		this.reportId = reportId;
		this.reportTitle = reportTitle;
		this.reportContent = reportContent;
	}
	
	
	public Report(int reportNum) {
		super();
		this.reportNum = reportNum;
	}



	public String getReportId() {
		return reportId;
	}



	public void setReportId(String reportId) {
		this.reportId = reportId;
	}



	public int getReportNum() {
		return reportNum;
	}

	public void setReportNum(int reportNum) {
		this.reportNum = reportNum;
	}

	public int getmReporterNum() {
		return mReporterNum;
	}

	public void setmReporterNum(int mReporterNum) {
		this.mReporterNum = mReporterNum;
	}

	public int getmAttackerNum() {
		return mAttackerNum;
	}

	public void setmAttackerNum(int mAttackerNum) {
		this.mAttackerNum = mAttackerNum;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public char getReportCheck() {
		return reportCheck;
	}

	public void setReportCheck(char reportCheck) {
		this.reportCheck = reportCheck;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	@Override
	public String toString() {
		return "Report [reportNum=" + reportNum + ", mReporterNum=" + mReporterNum + ", mAttackerNum=" + mAttackerNum
				+ ", reportTitle=" + reportTitle + ", reportContent=" + reportContent + ", reportCheck=" + reportCheck
				+ ", reportDate=" + reportDate + "]";
	}
	
	
	




	
	
	
	

}
