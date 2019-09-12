package com.semi.lecture.model.vo;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import com.semi.mento.model.vo.Mento;

public class Lecture {
	
	private int lecNum;
	private int mtNum;
	private int subNum;
	private int localSubNum;
	private String lecName;
	private String lecType;
	private int lecMaxCount;
	private int lecPrice;
	private double lecTime;
	private int lecCount;
	private String lecWeek;
	private String lecMeet;
	private String lecTot;
	private String lecTot2;
	private Date lecOpenDate;
	private Date lecOpenDate2;
	private String lecLocalContent;
	private String lecMentoContent;
	private String lecLectureContent;
	private int LecStudentCount;
	private Date lecADate;
	private char lecCheck;
	private String lecReason;
	private char lecStatus;
	private List<LectureUpload> lectureUpList;
	private Mento lecMento;
	public Lecture() {
		// TODO Auto-generated constructor stub
	}
	public Lecture(int lecNum, int mtNum, int subNum, int localSubNum, String lecName, String lecType, int lecMaxCount,
			int lecPrice, double lecTime, int lecCount, String lecWeek, String lecMeet, String lecTot, String lecTot2,
			Date lecOpenDate, Date lecOpenDate2, String lecLocalContent, String lecMentoContent,
			String lecLectureContent, int lecStudentCount, Date lecADate, char lecCheck, String lecReason,
			char lecStatus, List<LectureUpload> lectureUpList, Mento lecMento) {
		super();
		this.lecNum = lecNum;
		this.mtNum = mtNum;
		this.subNum = subNum;
		this.localSubNum = localSubNum;
		this.lecName = lecName;
		this.lecType = lecType;
		this.lecMaxCount = lecMaxCount;
		this.lecPrice = lecPrice;
		this.lecTime = lecTime;
		this.lecCount = lecCount;
		this.lecWeek = lecWeek;
		this.lecMeet = lecMeet;
		this.lecTot = lecTot;
		this.lecTot2 = lecTot2;
		this.lecOpenDate = lecOpenDate;
		this.lecOpenDate2 = lecOpenDate2;
		this.lecLocalContent = lecLocalContent;
		this.lecMentoContent = lecMentoContent;
		this.lecLectureContent = lecLectureContent;
		LecStudentCount = lecStudentCount;
		this.lecADate = lecADate;
		this.lecCheck = lecCheck;
		this.lecReason = lecReason;
		this.lecStatus = lecStatus;
		this.lectureUpList = lectureUpList;
		this.lecMento = lecMento;
	}
	public int getLecNum() {
		return lecNum;
	}
	public void setLecNum(int lecNum) {
		this.lecNum = lecNum;
	}
	public int getMtNum() {
		return mtNum;
	}
	public void setMtNum(int mtNum) {
		this.mtNum = mtNum;
	}
	public int getSubNum() {
		return subNum;
	}
	public void setSubNum(int subNum) {
		this.subNum = subNum;
	}
	public int getLocalSubNum() {
		return localSubNum;
	}
	public void setLocalSubNum(int localSubNum) {
		this.localSubNum = localSubNum;
	}
	public String getLecName() {
		return lecName;
	}
	public void setLecName(String lecName) {
		this.lecName = lecName;
	}
	public String getLecType() {
		return lecType;
	}
	public void setLecType(String lecType) {
		this.lecType = lecType;
	}
	public int getLecMaxCount() {
		return lecMaxCount;
	}
	public void setLecMaxCount(int lecMaxCount) {
		this.lecMaxCount = lecMaxCount;
	}
	public int getLecPrice() {
		return lecPrice;
	}
	public void setLecPrice(int lecPrice) {
		this.lecPrice = lecPrice;
	}
	public double getLecTime() {
		return lecTime;
	}
	public void setLecTime(double lecTime) {
		this.lecTime = lecTime;
	}
	public int getLecCount() {
		return lecCount;
	}
	public void setLecCount(int lecCount) {
		this.lecCount = lecCount;
	}
	public String getLecWeek() {
		return lecWeek;
	}
	public void setLecWeek(String lecWeek) {
		this.lecWeek = lecWeek;
	}
	public String getLecMeet() {
		return lecMeet;
	}
	public void setLecMeet(String lecMeet) {
		this.lecMeet = lecMeet;
	}
	public String getLecTot() {
		return lecTot;
	}
	public void setLecTot(String lecTot) {
		this.lecTot = lecTot;
	}
	public String getLecTot2() {
		return lecTot2;
	}
	public void setLecTot2(String lecTot2) {
		this.lecTot2 = lecTot2;
	}
	public Date getLecOpenDate() {
		return lecOpenDate;
	}
	public void setLecOpenDate(Date lecOpenDate) {
		this.lecOpenDate = lecOpenDate;
	}
	public Date getLecOpenDate2() {
		return lecOpenDate2;
	}
	public void setLecOpenDate2(Date lecOpenDate2) {
		this.lecOpenDate2 = lecOpenDate2;
	}
	public String getLecLocalContent() {
		return lecLocalContent;
	}
	public void setLecLocalContent(String lecLocalContent) {
		this.lecLocalContent = lecLocalContent;
	}
	public String getLecMentoContent() {
		return lecMentoContent;
	}
	public void setLecMentoContent(String lecMentoContent) {
		this.lecMentoContent = lecMentoContent;
	}
	public String getLecLectureContent() {
		return lecLectureContent;
	}
	public void setLecLectureContent(String lecLectureContent) {
		this.lecLectureContent = lecLectureContent;
	}
	public int getLecStudentCount() {
		return LecStudentCount;
	}
	public void setLecStudentCount(int lecStudentCount) {
		LecStudentCount = lecStudentCount;
	}
	public Date getLecADate() {
		return lecADate;
	}
	public void setLecADate(Date lecADate) {
		this.lecADate = lecADate;
	}
	public char getLecCheck() {
		return lecCheck;
	}
	public void setLecCheck(char lecCheck) {
		this.lecCheck = lecCheck;
	}
	public String getLecReason() {
		return lecReason;
	}
	public void setLecReason(String lecReason) {
		this.lecReason = lecReason;
	}
	public char getLecStatus() {
		return lecStatus;
	}
	public void setLecStatus(char lecStatus) {
		this.lecStatus = lecStatus;
	}
	public List<LectureUpload> getLectureUpList() {
		return lectureUpList;
	}
	public void setLectureUpList(List<LectureUpload> lectureUpList) {
		this.lectureUpList = lectureUpList;
	}
	public Mento getLecMento() {
		return lecMento;
	}
	public void setLecMento(Mento lecMento) {
		this.lecMento = lecMento;
	}
	@Override
	public String toString() {
		return "Lecture [lecNum=" + lecNum + ", mtNum=" + mtNum + ", subNum=" + subNum + ", localSubNum=" + localSubNum
				+ ", lecName=" + lecName + ", lecType=" + lecType + ", lecMaxCount=" + lecMaxCount + ", lecPrice="
				+ lecPrice + ", lecTime=" + lecTime + ", lecCount=" + lecCount + ", lecWeek=" + lecWeek
				+ ", lecMeet=" + lecMeet + ", lecTot=" + lecTot + ", lecTot2=" + lecTot2 + ", lecOpenDate="
				+ lecOpenDate + ", lecOpenDate2=" + lecOpenDate2 + ", lecLocalContent=" + lecLocalContent
				+ ", lecMentoContent=" + lecMentoContent + ", lecLectureContent=" + lecLectureContent
				+ ", LecStudentCount=" + LecStudentCount + ", lecADate=" + lecADate + ", lecCheck=" + lecCheck
				+ ", lecReason=" + lecReason + ", lecStatus=" + lecStatus + ", lectureUpList=" + lectureUpList
				+ ", lecMento=" + lecMento + "]";
	}
	
	
	
}
