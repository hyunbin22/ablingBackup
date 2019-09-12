package com.semi.lecture.model.vo;

import com.semi.member.model.vo.Member;

public class AppForClass {
	
	private int sNum;
	private int mNum;
	private int lecNum;
	private String sText;
	private int sPrice;
	private char sPayment;
	private char afcCheck;
	private Member member;
	private Lecture lecture;
	
	public AppForClass() {
		// TODO Auto-generated constructor stub
	}

	public AppForClass(int sNum, int mNum, int lecNum, String sText, int sPrice, char sPayment, char afcCheck,
			Member member, Lecture lecture) {
		super();
		this.sNum = sNum;
		this.mNum = mNum;
		this.lecNum = lecNum;
		this.sText = sText;
		this.sPrice = sPrice;
		this.sPayment = sPayment;
		this.afcCheck = afcCheck;
		this.member = member;
		this.lecture = lecture;
	}

	public int getsNum() {
		return sNum;
	}

	public void setsNum(int sNum) {
		this.sNum = sNum;
	}

	public int getmNum() {
		return mNum;
	}

	public void setmNum(int mNum) {
		this.mNum = mNum;
	}

	public int getLecNum() {
		return lecNum;
	}

	public void setLecNum(int lecNum) {
		this.lecNum = lecNum;
	}

	public String getsText() {
		return sText;
	}

	public void setsText(String sText) {
		this.sText = sText;
	}

	public int getsPrice() {
		return sPrice;
	}

	public void setsPrice(int sPrice) {
		this.sPrice = sPrice;
	}

	public char getsPayment() {
		return sPayment;
	}

	public void setsPayment(char sPayment) {
		this.sPayment = sPayment;
	}

	public char getAfcCheck() {
		return afcCheck;
	}

	public void setAfcCheck(char afcCheck) {
		this.afcCheck = afcCheck;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Lecture getLecture() {
		return lecture;
	}

	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
	}

	@Override
	public String toString() {
		return "AppForClass [sNum=" + sNum + ", mNum=" + mNum + ", lecNum=" + lecNum + ", sText=" + sText + ", sPrice="
				+ sPrice + ", sPayment=" + sPayment + ", afcCheck=" + afcCheck + ", member=" + member + ", lecture="
				+ lecture + "]";
	}
	
	

}
