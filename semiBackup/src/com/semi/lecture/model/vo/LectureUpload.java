package com.semi.lecture.model.vo;

public class LectureUpload {

	private int upLectureNum;
	private int lecNum;
	private String upLectureCategory;
	private String upLectureOrgName;
	private String upLectureReName;
	
	public LectureUpload() {
		// TODO Auto-generated constructor stub
	}

	public LectureUpload(int upLectureNum, int lecNum, String upLectureCategory, String upLectureOrgName,
			String upLectureReName) {
		super();
		this.upLectureNum = upLectureNum;
		this.lecNum = lecNum;
		this.upLectureCategory = upLectureCategory;
		this.upLectureOrgName = upLectureOrgName;
		this.upLectureReName = upLectureReName;
	}

	public LectureUpload(int upLectureNum, String upLectureCategory, String upLectureOrgName, String upLectureReName) {
		super();
		this.upLectureNum = upLectureNum;
		this.upLectureCategory = upLectureCategory;
		this.upLectureOrgName = upLectureOrgName;
		this.upLectureReName = upLectureReName;
	}

	public int getUpLectureNum() {
		return upLectureNum;
	}

	public void setUpLectureNum(int upLectureNum) {
		this.upLectureNum = upLectureNum;
	}

	public int getLecNum() {
		return lecNum;
	}

	public void setLecNum(int lecNum) {
		this.lecNum = lecNum;
	}

	public String getUpLectureCategory() {
		return upLectureCategory;
	}

	public void setUpLectureCategory(String upLectureCategory) {
		this.upLectureCategory = upLectureCategory;
	}

	public String getUpLectureOrgName() {
		return upLectureOrgName;
	}

	public void setUpLectureOrgName(String upLectureOrgName) {
		this.upLectureOrgName = upLectureOrgName;
	}

	public String getUpLectureReName() {
		return upLectureReName;
	}

	public void setUpLectureReName(String upLectureReName) {
		this.upLectureReName = upLectureReName;
	}

	@Override
	public String toString() {
		return "LectureUpload [upLectureNum=" + upLectureNum + ", lecNum=" + lecNum + ", upLectureCategory="
				+ upLectureCategory + ", upLectureOrgName=" + upLectureOrgName + ", upLectureReName=" + upLectureReName
				+ "]";
	}
	
	

}
