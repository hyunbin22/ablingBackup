package com.semi.lecture.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.semi.lecture.model.service.LectureService;
import com.semi.lecture.model.service.LectureUploadService;
//import com.semi.lecture.model.service.LectureService;
import com.semi.lecture.model.vo.Lecture;
import com.semi.lecture.model.vo.LectureUpload;
import com.semi.member.model.vo.Member;
import com.semi.mento.model.vo.Mento;

import common.oreilly.servlet.multipart.AblingFileRenamePolicy;

/**
 * Servlet implementation class ClassEnroll
 */
@WebServlet("/lectureEnrollEnd")
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

       
      if(!ServletFileUpload.isMultipartContent(request)) {
            request.setAttribute("msg", "신고 실패 ![form:ectype] 관리자에게 문의하세요!");
            request.setAttribute("loc", "/");
            request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
            
            return;
         }
         
         String root=getServletContext().getRealPath("/");
         
         String saveDir=root+"/upload/report";
         
         int maxSize=1024*1024*1024;
         
         HttpSession session = request.getSession();
         Member memberLogin = (Member) session.getAttribute("loginMember");
         MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, "UTF-8", new AblingFileRenamePolicy(memberLogin.getmId()));
         
         String coverOriImage = mr.getOriginalFileName("file1");
         String coverReImage = mr.getFilesystemName("file1");
         String lecOriImage = mr.getOriginalFileName("addImg");
         String lecReImage = mr.getFilesystemName("addImg");
         
         int mtNum = Integer.parseInt(mr.getParameter("mtNum")); //멘토 참조번호
         String lecName = mr.getParameter("className"); //수업제목
         System.out.println(lecName);
         int subCategory = Integer.parseInt(mr.getParameter("subcategory")); //부카테고리
         System.out.println(subCategory);
         String lecType = mr.getParameter("classType"); //수업형태 (1:1수업, 그룹수업)
         System.out.println(lecType);
         int lecmaxcount = Integer.parseInt(mr.getParameter("studentCount")); //수업정원
         System.out.println(lecmaxcount);
         String address= mr.getParameter("subcategory");
         address+=mr.getParameter("local2");
         System.out.println(address);
         String lecMentoContent =mr.getParameter("mentoIntroduce"); //멘토소개
         System.out.println(lecMentoContent);
         String lecLectureContent = mr.getParameter("classIntroduce"); //수업소개
         System.out.println(lecLectureContent);
         int lecPrice = Integer.parseInt(mr.getParameter("price")); //시간당 가격
         System.out.println(lecPrice);
         int lecTime = Integer.parseInt(mr.getParameter("time")); //1회 수업 시간
         System.out.println(lecTime);
         int lecCount = Integer.parseInt(mr.getParameter("totaltime")); //한달 기준 수업 횟수
         System.out.println(lecCount);
         int totalprice = lecPrice*lecTime*lecCount ; //총가격
         System.out.println(totalprice);
         int subLocal = Integer.parseInt(mr.getParameter("local"));//서브지역카테고리
         System.out.println(subLocal);
         String lecLocalContent = mr.getParameter("local2"); //세부위치작성
         System.out.println(lecLocalContent);
         String lecWeek = mr.getParameter("yo"); //요일
         System.out.println(lecWeek);
         String lecTot = mr.getParameter("day1"); // 수업시간
         System.out.println(lecTot);
         String lecMeet = mr.getParameter("week1"); // 날짜선택 버튼,날짜협의 버튼
         System.out.println(lecMeet);
         Date lecOpenDate = Date.valueOf(mr.getParameter("month1"));//개설날짜
         System.out.println(lecOpenDate);
         String lecTot2 = mr.getParameter("day2"); // 수업시간
         System.out.println(lecTot2);
         String week2 = mr.getParameter("week2"); //날짜선택 버튼,날짜협S의 버튼
         System.out.println(week2);
         Date lecOpenDate2 = Date.valueOf(mr.getParameter("month2")); //개설날짜
         System.out.println(lecOpenDate2);
         
  
         Lecture l = new Lecture(mtNum, subCategory, subLocal, lecName, lecType,
               lecmaxcount, lecPrice, lecTime, lecCount, lecWeek, lecMeet, lecTot,
               lecTot2, lecOpenDate, lecOpenDate2, lecLocalContent, lecMentoContent,
               lecLectureContent);
         
         System.out.println(l);
         int result = new LectureService().insertLecture(l, mtNum); // mtnum
         System.out.println("result : "+result);
         
         String category="";
         
         Lecture lec = new Lecture(lecName, lecType, lecmaxcount, lecPrice, lecTime, lecCount, lecWeek, lecMeet, lecTot, lecTot2, lecOpenDate, lecOpenDate2, lecLocalContent, lecMentoContent, lecLectureContent);
         Mento mt = new Mento(mtNum);
                  
         int result2=0;
         
         if(mr.getOriginalFileName("file1")!=null) { // lecture테이블 cover
            category="cover";
            String upLectureOriCover = mr.getOriginalFileName("file1");
            String upLectureReCover = mr.getFilesystemName("file1");
            LectureUpload lecup1 = new LectureUpload(result, category, upLectureOriCover, upLectureReCover);
            result2=new LectureUploadService().insertLectureImage(lecup1, result, category);
            System.out.println("카테고리 : "+category+", 파일이름"+upLectureReCover);
         }
         if(mr.getOriginalFileName("addImg")!=null) {
            category="lecimage";
            String upLectureOrilecimage = mr.getOriginalFileName("addImg");
            String upLectureRelecimage = mr.getFilesystemName("addImg");
            LectureUpload lecup2 = new LectureUpload(result, category, upLectureOrilecimage, upLectureRelecimage);
            result2=new LectureUploadService().insertLectureImage(lecup2, result, category);
            System.out.println("카테고리 : "+category+", 파일이름"+upLectureRelecimage);
         }
         

         String msg="";
         String loc="";
         
         if(result>0 && result2>0) {
            msg="강의등록 완료";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("views/mento/mentoPageView.jsp").forward(request, response);
         }
         else{
            msg="강의동록 실패!!";
            loc="/";
            request.setAttribute("msg", msg);
           request.setAttribute("loc", loc);
           request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
         }

   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}