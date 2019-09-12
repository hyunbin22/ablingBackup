package com.semi.lecture.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.lecture.model.service.LectureService;
//import com.semi.lecture.model.service.LectureService;
import com.semi.lecture.model.vo.Lecture;
import com.semi.lecture.model.vo.LectureUpload;
import com.semi.member.model.vo.Member;
import com.semi.mento.model.vo.Mento;

/**
 * Servlet implementation class ClassEnroll
 */
@WebServlet("/lecture/lectureEnrollEnd.do")
public class LectureEnrollEndServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LectureEnrollEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      System.out.println("이거야 -->>>>>>>>>>>>>"+request.getParameter("mtNum"));
      System.out.println("null이니"+request.getParameter("studentCount"));
      int mtNum = Integer.parseInt(request.getParameter("mtNum")); //멘토 참조번호
      String lecName = request.getParameter("className"); //수업제목
      int subCategory = Integer.parseInt(request.getParameter("subcategory")); //부카테고리
      //int subCategory = 1;
      String lecType = request.getParameter("classType"); //수업형태 (1:1수업, 그룹수업)
      int lecmaxcount = Integer.parseInt(request.getParameter("studentCount")); //수업정원
      //int lecmaxcount = 1;
      String address= request.getParameter("subcategory");
      address+=request.getParameter("local2");
      String lecMentoContent =request.getParameter("mentoIntroduce"); //멘토소개
      String lecLectureContent = request.getParameter("classIntroduce"); //수업소개
      int lecPrice = Integer.parseInt(request.getParameter("price")); //시간당 가격
      int lecTime = Integer.parseInt(request.getParameter("time")); //1회 수업 시간
      int lecCount = Integer.parseInt(request.getParameter("totaltime")); //한달 기준 수업 횟수
      int totalprice = lecPrice*lecTime*lecCount ; //총가격
      int subLocal = Integer.parseInt(request.getParameter("local"));//서브지역카테고리
      String lecLocalContent = request.getParameter("local2"); //세부위치작성
      String lecWeek = request.getParameter("yo"); //요일
      String lecTot = request.getParameter("day1"); // 수업시간
      String lecMeet = request.getParameter("week1"); // 날짜선택 버튼,날짜협의 버튼
      Date lecOpenDate = Date.valueOf(request.getParameter("month1"));//개설날짜
      String lecTot2 = request.getParameter("day2"); // 수업시간
      String week2 = request.getParameter("week2"); //날짜선택 버튼,날짜협S의 버튼
      Date lecOpenDate2 = Date.valueOf(request.getParameter("month2")); //개설날짜
      
   }
   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}