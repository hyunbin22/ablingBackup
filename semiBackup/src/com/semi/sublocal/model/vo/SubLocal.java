package com.semi.sublocal.model.vo;

public class SubLocal {

	private int localsubNum;
	private String localCountry;
	
	public SubLocal() {
		// TODO Auto-generated constructor stub
	}

	public SubLocal(int localsubNum, String localCountry) {
		super();
		this.localsubNum = localsubNum;
		this.localCountry = localCountry;
	}
	
	public SubLocal(String localCountry) {
		this.localCountry = localCountry;
	}
	
	public SubLocal(int localsubNum) {
		this.localsubNum = localsubNum;
	}

	public int getLocalsubNum() {
		return localsubNum;
	}

	public void setLocalsubNum(int localsubNum) {
		this.localsubNum = localsubNum;
	}

	public String getLocalCountry() {
		return localCountry;
	}

	public void setLocalCountry(String localCountry) {
		this.localCountry = localCountry;
	}

	@Override
	public String toString() {
		return localCountry;
	}

	
	 
}

