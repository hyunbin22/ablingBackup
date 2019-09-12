package com.semi.report.model.vo;

public class ReportUpload {
	
	
	private int reportRefNum; // 신고게시글 참조 번호
	private String fileOriName; // 첨부파일 원래 이름
	private String fileReNmae; // 첨부파일 바뀐 이름
	
	
	
	public ReportUpload() {
		
	}


	public ReportUpload(String fileOriName, String fileReNmae) {

		
		this.fileOriName = fileOriName;
		this.fileReNmae = fileReNmae;
		
	}



	public int getReportRefNum() {
		return reportRefNum;
	}



	public void setReportRefNum(int reportRefNum) {
		this.reportRefNum = reportRefNum;
	}



	public String getFileOriName() {
		return fileOriName;
	}



	public void setFileOriName(String fileOriName) {
		this.fileOriName = fileOriName;
	}



	public String getFileReNmae() {
		return fileReNmae;
	}



	public void setFileReNmae(String fileReNmae) {
		this.fileReNmae = fileReNmae;
	}



	@Override
	public String toString() {
		return "ReportUpload [reportRefNum=" + reportRefNum + ", fileOriName=" + fileOriName + ", fileReNmae="
				+ fileReNmae + "]";
	}
	
	
	
	
	
	
	
	
	
	
	

}
