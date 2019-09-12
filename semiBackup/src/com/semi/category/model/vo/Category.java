package com.semi.category.model.vo;

public class Category {
	
	private int scNum; //카테고리 번호
	private String scName; //카테고리 이름
	
	public Category() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Category(int scNum) {
		this.scNum = scNum;
	}
	
	public Category(String scName) {
		this.scName = scName;
	}

	public Category(int scNum, String scName) {
		this.scNum = scNum;
		this.scName = scName;
	}

	public int getScNum() {
		return scNum;
	}

	public void setScNum(int scNum) {
		this.scNum = scNum;
	}

	public String getScName() {
		return scName;
	}

	public void setScName(String scName) {
		this.scName = scName;
	}

	@Override
	public String toString() {
		return "Category [scNum=" + scNum + ", scName=" + scName + "]";
	}

}
