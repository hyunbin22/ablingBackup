package com.semi.lecture.model.vo;

public class LectureUpload {

	private int upLectureNum;
	private int lecNum;
	private String upLectureOrgCover;
	private String upLectureReCover;
	private String upLectureOrgLecImg;
	private String upLectureReLecImg;
	
	public LectureUpload() {
		// TODO Auto-generated constructor stub
	}

	public LectureUpload(int upLectureNum, int lecNum, String upLectureOrgCover, String upLectureReCover,
			String upLectureOrgLecImg, String upLectureReLecImg) {
		super();
		this.upLectureNum = upLectureNum;
		this.lecNum = lecNum;
		this.upLectureOrgCover = upLectureOrgCover;
		this.upLectureReCover = upLectureReCover;
		this.upLectureOrgLecImg = upLectureOrgLecImg;
		this.upLectureReLecImg = upLectureReLecImg;
	}
	
	

	public LectureUpload(int upLectureNum, int lecNum, String upLectureOrgCover, String upLectureReCover) {
		super();
		this.upLectureNum = upLectureNum;
		this.lecNum = lecNum;
		this.upLectureOrgCover = upLectureOrgCover;
		this.upLectureReCover = upLectureReCover;
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

	public String getUpLectureOrgCover() {
		return upLectureOrgCover;
	}

	public void setUpLectureOrgCover(String upLectureOrgCover) {
		this.upLectureOrgCover = upLectureOrgCover;
	}

	public String getUpLectureReCover() {
		return upLectureReCover;
	}

	public void setUpLectureReCover(String upLectureReCover) {
		this.upLectureReCover = upLectureReCover;
	}

	public String getUpLectureOrgLecImg() {
		return upLectureOrgLecImg;
	}

	public void setUpLectureOrgLecImg(String upLectureOrgLecImg) {
		this.upLectureOrgLecImg = upLectureOrgLecImg;
	}

	public String getUpLectureReLecImg() {
		return upLectureReLecImg;
	}

	public void setUpLectureReLecImg(String upLectureReLecImg) {
		this.upLectureReLecImg = upLectureReLecImg;
	}

	@Override
	public String toString() {
		return "LectureUpload [upLectureNum=" + upLectureNum + ", lecNum=" + lecNum + ", upLectureOrgCover="
				+ upLectureOrgCover + ", upLectureReCover=" + upLectureReCover + ", upLectureOrgLecImg="
				+ upLectureOrgLecImg + ", upLectureReLecImg=" + upLectureReLecImg + "]";
	}
	
	
	
}
