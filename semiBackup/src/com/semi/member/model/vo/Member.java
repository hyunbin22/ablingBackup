package com.semi.member.model.vo;

import java.sql.Date;

public class Member {
	
	private int mNum;
	private String mId;
	private String mPassword;
	private String mName;
	private char mGender;
	private Date mBirth;
	private String mEmail;
	private String mPhone;
	private char mUse;
	private Date mHireDate;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(int mNum, String mId, String mPassword, String mName, char mGender, Date mBirth, String mEmail,
			String mPhone, char mUse, Date mHireDate) {
		super();
		this.mNum = mNum;
		this.mId = mId;
		this.mPassword = mPassword;
		this.mName = mName;
		this.mGender = mGender;
		this.mBirth = mBirth;
		this.mEmail = mEmail;
		this.mPhone = mPhone;
		this.mUse = mUse;
		this.mHireDate = mHireDate;
	}

	
	public Member(String mId, String mPassword, String mName, char mGender, Date mBirth, String mEmail,
			String mPhone) {
		super();
		this.mId = mId;
		this.mPassword = mPassword;
		this.mName = mName;
		this.mGender = mGender;
		this.mBirth = mBirth;
		this.mEmail = mEmail;
		this.mPhone = mPhone;
	}
	
	public Member(int mNum) {
		super();
		this.mNum = mNum;
	}
	
	public Member(String mName) {
		this.mName = mName;
	}
	
	
	
	public int getmNum() {
		return mNum;
	}

	public void setmNum(int mNum) {
		this.mNum = mNum;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmPassword() {
		return mPassword;
	}

	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public char getmGender() {
		return mGender;
	}

	public void setmGender(char mGender) {
		this.mGender = mGender;
	}

	public Date getmBirth() {
		return mBirth;
	}

	public void setmBirth(Date mBirth) {
		this.mBirth = mBirth;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public char getmUse() {
		return mUse;
	}

	public void setmUse(char mUse) {
		this.mUse = mUse;
	}

	public Date getmHireDate() {
		return mHireDate;
	}

	public void setmHireDate(Date mHireDate) {
		this.mHireDate = mHireDate;
	}

	@Override
	public String toString() {
		return "Member [mNum=" + mNum + ", mId=" + mId + ", mPassword=" + mPassword + ", mName=" + mName + ", mGender="
				+ mGender + ", mBirth=" + mBirth + ", mEmail=" + mEmail + ", mPhone=" + mPhone + ", mUse=" + mUse
				+ ", mHireDate=" + mHireDate + "]";
	}
	
}