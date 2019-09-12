package com.semi.local.model.vo;

public class Local {
	
	private int localNum; //지역번호
	private String localCity; //지역이름
	
	public Local() {
		// TODO Auto-generated constructor stub
	}

	public Local(int localNum) {
		this.localNum = localNum;
	}
	
	public Local(String localCity) {
		this.localCity = localCity;
	}
	
	public Local(int localNum, String localCity) {
		super();
		this.localNum = localNum;
		this.localCity = localCity;
	}

	public int getLocalNum() {
		return localNum;
	}

	public void setLocalNum(int localNum) {
		this.localNum = localNum;
	}

	public String getLocalCity() {
		return localCity;
	}

	public void setLocalCity(String localCity) {
		this.localCity = localCity;
	}

	@Override
	public String toString() {
		return "Local [localNum=" + localNum + ", localCity=" + localCity + "]";
	}
	
	
	

}
