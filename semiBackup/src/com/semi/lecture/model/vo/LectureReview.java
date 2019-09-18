package com.semi.lecture.model.vo;

import java.sql.Date;

public class LectureReview {

   
   
   private int rNum; // 리뷰번호
   private int lecnum; // 수업 참조번호
   private int mNum; //리뷰작성자 회원 참조번호
   private String rTitle; // 리뷰제목
   private String rText; // 리뷰내용
   private Date rDate; // 리뷰작성날자
   
   
   public LectureReview() {
      // TODO Auto-generated constructor stub
   }
   
   
   


   public LectureReview(int rNum, int lecnum, int mNum, String rTitle, String rText) {
      super();
      this.rNum = rNum;
      this.lecnum = lecnum;
      this.mNum = mNum;
      this.rTitle = rTitle;
      this.rText = rText;
   }





   public LectureReview(int rNum, int lecnum, int mNum, String rTitle, String rText, Date rDate) {
      super();
      this.rNum = rNum;
      this.lecnum = lecnum;
      this.mNum = mNum;
      this.rTitle = rTitle;
      this.rText = rText;
      this.rDate = rDate;
   }
   
   
   


   public int getrNum() {
      return rNum;
   }


   public void setrNum(int rNum) {
      this.rNum = rNum;
   }


   public int getLecnum() {
      return lecnum;
   }


   public void setLecnum(int lecnum) {
      this.lecnum = lecnum;
   }


   public int getmNum() {
      return mNum;
   }


   public void setmNum(int mNum) {
      this.mNum = mNum;
   }


   public String getrTitle() {
      return rTitle;
   }


   public void setrTitle(String rTitle) {
      this.rTitle = rTitle;
   }


   public String getrText() {
      return rText;
   }


   public void setrText(String rText) {
      this.rText = rText;
   }


   public Date getrDate() {
      return rDate;
   }


   public void setrDate(Date rDate) {
      this.rDate = rDate;
   }


   @Override
   public String toString() {
      return "LectureReview [rNum=" + rNum + ", lecnum=" + lecnum + ", mNum=" + mNum + ", rTitle=" + rTitle
            + ", rText=" + rText + ", rDate=" + rDate + "]";
   }

   
}