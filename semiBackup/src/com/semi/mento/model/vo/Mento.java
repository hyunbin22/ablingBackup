package com.semi.mento.model.vo;

import java.sql.Date;
import java.util.List;

import com.semi.member.model.vo.Member;

public class Mento{

	private int mtNum;
	private int mNum;
	private Date mtHireDate;
	private String mtNickName;
	private String mtHowConfirm;
	private String mtAcademic;
	private String mtAcademicDept;
	private String mtGraduation;
	private String mtBank;
	private String mtAccountNumber;
	private Date mtaDate;	//멘토신청날짜
	private char mtCheck;
	private String mtReason;
	private Member member;
	private List<MentoUpload> list;


	public Mento() {

	}


	public Mento(int mtNum, int mNum, Date mtHireDate, String mtNickName, String mtHowConfirm, String mtAcademic,
			String mtAcademicDept, String mtGraduation, String mtBank, String mtAccountNumber, Date mtaDate,
			char mtCheck, String mtReason, Member member, List<MentoUpload> list) {
		super();
		this.mtNum = mtNum;
		this.mNum = mNum;
		this.mtHireDate = mtHireDate;
		this.mtNickName = mtNickName;
		this.mtHowConfirm = mtHowConfirm;
		this.mtAcademic = mtAcademic;
		this.mtAcademicDept = mtAcademicDept;
		this.mtGraduation = mtGraduation;
		this.mtBank = mtBank;
		this.mtAccountNumber = mtAccountNumber;
		this.mtaDate = mtaDate;
		this.mtCheck = mtCheck;
		this.mtReason = mtReason;
		this.member = member;
		this.list = list;
	}
	
	


	public Mento(int mtNum) {
		super();
		this.mtNum = mtNum;
	}


	public Mento(String mtNickName, String mtHowConfirm, String mtAcademic, String mtAcademicDept, String mtGraduation,
			String mtBank, String mtAccountNumber) {
		super();
		this.mtNickName = mtNickName;
		this.mtHowConfirm = mtHowConfirm;
		this.mtAcademic = mtAcademic;
		this.mtAcademicDept = mtAcademicDept;
		this.mtGraduation = mtGraduation;
		this.mtBank = mtBank;
		this.mtAccountNumber = mtAccountNumber;
	}


	public int getMtNum() {
		return mtNum;
	}


	public void setMtNum(int mtNum) {
		this.mtNum = mtNum;
	}


	public int getmNum() {
		return mNum;
	}


	public void setmNum(int mNum) {
		this.mNum = mNum;
	}


	public Date getMtHireDate() {
		return mtHireDate;
	}


	public void setMtHireDate(Date mtHireDate) {
		this.mtHireDate = mtHireDate;
	}


	public String getMtNickName() {
		return mtNickName;
	}


	public void setMtNickName(String mtNickName) {
		this.mtNickName = mtNickName;
	}


	public String getMtHowConfirm() {
		return mtHowConfirm;
	}


	public void setMtHowConfirm(String mtHowConfirm) {
		this.mtHowConfirm = mtHowConfirm;
	}


	public String getMtAcademic() {
		return mtAcademic;
	}


	public void setMtAcademic(String mtAcademic) {
		this.mtAcademic = mtAcademic;
	}


	public String getMtAcademicDept() {
		return mtAcademicDept;
	}


	public void setMtAcademicDept(String mtAcademicDept) {
		this.mtAcademicDept = mtAcademicDept;
	}


	public String getMtGraduation() {
		return mtGraduation;
	}


	public void setMtGraduation(String mtGraduation) {
		this.mtGraduation = mtGraduation;
	}


	public String getMtBank() {
		return mtBank;
	}


	public void setMtBank(String mtBank) {
		this.mtBank = mtBank;
	}


	public String getMtAccountNumber() {
		return mtAccountNumber;
	}


	public void setMtAccountNumber(String mtAccountNumber) {
		this.mtAccountNumber = mtAccountNumber;
	}


	public Date getMtaDate() {
		return mtaDate;
	}


	public void setMtaDate(Date mtaDate) {
		this.mtaDate = mtaDate;
	}


	public char getMtCheck() {
		return mtCheck;
	}


	public void setMtCheck(char mtCheck) {
		this.mtCheck = mtCheck;
	}


	public String getMtReason() {
		return mtReason;
	}


	public void setMtReason(String mtReason) {
		this.mtReason = mtReason;
	}


	public Member getMember() {
		return member;
	}


	public void setMember(Member member) {
		this.member = member;
	}


	public List<MentoUpload> getList() {
		return list;
	}


	public void setList(List<MentoUpload> list) {
		this.list = list;
	}


	@Override
	public String toString() {
		return "Mento [mtNum=" + mtNum + ", mNum=" + mNum + ", mtHireDate=" + mtHireDate + ", mtNickName=" + mtNickName
				+ ", mtHowConfirm=" + mtHowConfirm + ", mtAcademic=" + mtAcademic + ", mtAcademicDept=" + mtAcademicDept
				+ ", mtGraduation=" + mtGraduation + ", mtBank=" + mtBank + ", mtAccountNumber=" + mtAccountNumber
				+ ", mtaDate=" + mtaDate + ", mtCheck=" + mtCheck + ", mtReason=" + mtReason + ", member=" + member
				+ ", list=" + list + "]";
	}
	
	
}
