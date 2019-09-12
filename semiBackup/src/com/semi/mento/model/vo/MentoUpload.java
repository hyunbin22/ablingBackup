package com.semi.mento.model.vo;

public class MentoUpload {
	private int upMentoNum; // 멘토첨부파일 번호
	private int mtNum; // 멘토테이블 참조 멘토번호
	private String upMentoOrgProfile; //멘토 프로필 첨부파일 원래이름
	private String upMentoReProfile; // 멘토 프로필 첨부파일 변경이름
	private String upMentoOrgConfirm; // 멘토 신분인증 첨부파일 원래이름
	private String upMentoReConfirm; // 멘토 신분인증 첨부파일 변경이름
	private String upMentoNameLicense; // 멘토 자격증 이름
	private String upMentoOrgLicense; // 멘토 자격증 첨부파일 원래이름
	private String upMentoReLicense; // 멘토 자격증 첨부파일 변경이름



	public MentoUpload() {
		// TODO Auto-generated constructor stub
	}


	public MentoUpload(int mtNum, String upMentoOrgProfile, String upMentoReProfile, String upMentoOrgConfirm,
			String upMentoReConfirm, String upMentoNameLicense, String upMentoOrgLicense, String upMentoReLicense) {
		super();
		this.mtNum = mtNum;
		this.upMentoOrgProfile = upMentoOrgProfile;
		this.upMentoReProfile = upMentoReProfile;
		this.upMentoOrgConfirm = upMentoOrgConfirm;
		this.upMentoReConfirm = upMentoReConfirm;
		this.upMentoNameLicense = upMentoNameLicense;
		this.upMentoOrgLicense = upMentoOrgLicense;
		this.upMentoReLicense = upMentoReLicense;
	}


	public MentoUpload(String upMentoOrgProfile, String upMentoReProfile, String upMentoOrgConfirm,
			String upMentoReConfirm, String upMentoNameLicense, String upMentoOrgLicense, String upMentoReLicense) {
		super();
		this.upMentoOrgProfile = upMentoOrgProfile;
		this.upMentoReProfile = upMentoReProfile;
		this.upMentoOrgConfirm = upMentoOrgConfirm;
		this.upMentoReConfirm = upMentoReConfirm;
		this.upMentoNameLicense = upMentoNameLicense;
		this.upMentoOrgLicense = upMentoOrgLicense;
		this.upMentoReLicense = upMentoReLicense;
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



	public String getUpMentoOrgProfile() {
		return upMentoOrgProfile;
	}



	public void setUpMentoOrgProfile(String upMentoOrgProfile) {
		this.upMentoOrgProfile = upMentoOrgProfile;
	}



	public String getUpMentoReProfile() {
		return upMentoReProfile;
	}



	public void setUpMentoReProfile(String upMentoReProfile) {
		this.upMentoReProfile = upMentoReProfile;
	}



	public String getUpMentoOrgConfirm() {
		return upMentoOrgConfirm;
	}



	public void setUpMentoOrgConfirm(String upMentoOrgConfirm) {
		this.upMentoOrgConfirm = upMentoOrgConfirm;
	}



	public String getUpMentoReConfirm() {
		return upMentoReConfirm;
	}



	public void setUpMentoReConfirm(String upMentoReConfirm) {
		this.upMentoReConfirm = upMentoReConfirm;
	}



	public String getUpMentoNameLicense() {
		return upMentoNameLicense;
	}



	public void setUpMentoNameLicense(String upMentoNameLicense) {
		this.upMentoNameLicense = upMentoNameLicense;
	}



	public String getUpMentoOrgLicense() {
		return upMentoOrgLicense;
	}



	public void setUpMentoOrgLicense(String upMentoOrgLicense) {
		this.upMentoOrgLicense = upMentoOrgLicense;
	}



	public String getUpMentoReLicense() {
		return upMentoReLicense;
	}



	public void setUpMentoReLicense(String upMentoReLicense) {
		this.upMentoReLicense = upMentoReLicense;
	}


	@Override
	public String toString() {
		return "MentoUpload [upMentoNum=" + upMentoNum + ", mtNum=" + mtNum + ", upMentoOrgProfile=" + upMentoOrgProfile
				+ ", upMentoReProfile=" + upMentoReProfile + ", upMentoOrgConfirm=" + upMentoOrgConfirm
				+ ", upMentoReConfirm=" + upMentoReConfirm + ", upMentoNameLicense=" + upMentoNameLicense
				+ ", upMentoOrgLicense=" + upMentoOrgLicense + ", upMentoReLicense=" + upMentoReLicense + "]";
	}

	



}