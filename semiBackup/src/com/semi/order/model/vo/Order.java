package com.semi.order.model.vo;

import java.sql.Date;

import com.semi.lecture.model.vo.Lecture;
import com.semi.member.model.vo.Member;

public class Order {
	
	   
   private int oNum;
   private int mNum;
   private int lecNum;
   private String oTot;
   private String oText;
   private int oPrice;
   private char oPayment;
   private char oCheck;
   private Lecture lecture;
   private Member member;
   private Date oDate;
   private Date payDate;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(int oNum, int mNum, int lecNum, String oTot, String oText, int oPrice, char oPayment, char oCheck,
			Lecture lecture, Member member, Date oDate, Date payDate) {
		super();
		this.oNum = oNum;
		this.mNum = mNum;
		this.lecNum = lecNum;
		this.oTot = oTot;
		this.oText = oText;
		this.oPrice = oPrice;
		this.oPayment = oPayment;
		this.oCheck = oCheck;
		this.lecture = lecture;
		this.member = member;
		this.oDate = oDate;
		this.payDate = payDate;
	}




	public Order(int mNum, int lecNum, String oTot, String oText, int oPrice) {
		super();
		this.mNum = mNum;
		this.lecNum = lecNum;
		this.oTot = oTot;
		this.oText = oText;
		this.oPrice = oPrice;
	}
	

	public Date getoDate() {
		return oDate;
	}

	public void setoDate(Date oDate) {
		this.oDate = oDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public int getoNum() {
		return oNum;
	}

	public void setoNum(int oNum) {
		this.oNum = oNum;
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

	public String getoTot() {
		return oTot;
	}

	public void setoTot(String oTot) {
		this.oTot = oTot;
	}

	public String getoText() {
		return oText;
	}

	public void setoText(String oText) {
		this.oText = oText;
	}

	public int getoPrice() {
		return oPrice;
	}

	public void setoPrice(int oPrice) {
		this.oPrice = oPrice;
	}

	public char getoPayment() {
		return oPayment;
	}

	public void setoPayment(char oPayment) {
		this.oPayment = oPayment;
	}

	public char getoCheck() {
		return oCheck;
	}

	public void setoCheck(char oCheck) {
		this.oCheck = oCheck;
	}

	public Lecture getLecture() {
		return lecture;
	}

	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "Order [oNum=" + oNum + ", mNum=" + mNum + ", lecNum=" + lecNum + ", oTot=" + oTot + ", oText=" + oText
				+ ", oPrice=" + oPrice + ", oPayment=" + oPayment + ", oCheck=" + oCheck + ", lecture=" + lecture
				+ ", member=" + member + "]";
	}
	
	
	

}
