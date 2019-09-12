package com.semi.mento.model.vo;

public class MentoUpload {
	private int upMentoNum; // 멘토첨부파일 번호
	private int mtNum; // 멘토테이블 참조 멘토번호
	private String upMentoCategory; 
	private String upMentoNameLicense;
	private String upMentoOrgName;
	private String upMentoReName;
	
	public MentoUpload() {
		// TODO Auto-generated constructor stub
	}

	public MentoUpload(int upMentoNum, int mtNum, String upMentoCategory, String upMentoNameLicense,
			String upMentoOrgName, String upMentoReName) {
		super();
		this.upMentoNum = upMentoNum;
		this.mtNum = mtNum;
		this.upMentoCategory = upMentoCategory;
		this.upMentoNameLicense = upMentoNameLicense;
		this.upMentoOrgName = upMentoOrgName;
		this.upMentoReName = upMentoReName;
	}

	public int getUpMentoNum() {
		return upMentoNum;
	}

	public void setUpMentoNum(int upMentoNum) {
		this.upMentoNum = upMentoNum;
	}

	public int getMtNum() {
		return mtNum;
	}

	public void setMtNum(int mtNum) {
		this.mtNum = mtNum;
	}

	public String getUpMentoCategory() {
		return upMentoCategory;
	}

	public void setUpMentoCategory(String upMentoCategory) {
		this.upMentoCategory = upMentoCategory;
	}

	public String getUpMentoNameLicense() {
		return upMentoNameLicense;
	}

	public void setUpMentoNameLicense(String upMentoNameLicense) {
		this.upMentoNameLicense = upMentoNameLicense;
	}

	public String getUpMentoOrgName() {
		return upMentoOrgName;
	}

	public void setUpMentoOrgName(String upMentoOrgName) {
		this.upMentoOrgName = upMentoOrgName;
	}

	public String getUpMentoReName() {
		return upMentoReName;
	}

	public void setUpMentoReName(String upMentoReName) {
		this.upMentoReName = upMentoReName;
	}

	@Override
	public String toString() {
		return "MentoUpload [upMentoNum=" + upMentoNum + ", mtNum=" + mtNum + ", upMentoCategory=" + upMentoCategory
				+ ", upMentoNameLicense=" + upMentoNameLicense + ", upMentoOrgName=" + upMentoOrgName
				+ ", upMentoReName=" + upMentoReName + "]";
	}
	
	


}