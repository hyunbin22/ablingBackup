package com.semi.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.member.model.service.MemberService;
import com.semi.member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberListServlet
 */
@WebServlet("/admin/memberList.do")
public class AdminMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	   /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      //페이징처리 추가하기
      int cPage;
      try {
         cPage=Integer.parseInt(request.getParameter("cPage"));
      }catch(NumberFormatException e) {
         cPage=1;
      }
      
      int numPerPage=5;//페이지당 출력할 데이터
      int totalMember=new MemberService().selectCountMember();
            
      List<Member> list=new MemberService().selectListPage(cPage,numPerPage);
      
      int totalPage=(int)Math.ceil((double)totalMember/numPerPage);
      String pageBar="";
      int pageSizeBar=5;
      System.out.println("cpage : "+cPage);
      int pageNo=((cPage-1)/pageSizeBar)*pageSizeBar+1;
      System.out.println("pageNo : "+(cPage-1)/pageSizeBar*pageSizeBar);
      int pageEnd=pageNo+pageSizeBar-1;
      if(pageNo==1) {
         pageBar+="<span>[이전]</span>";
      }
      else {
         pageBar+="<a href="+request.getContextPath()+"/admin/memberList.do?cPage="+(pageNo-1)+">[이전]</a>";
      }
      while(!(pageNo>pageEnd||pageNo>totalPage)) {
         if(pageNo==cPage) {
            pageBar+="<span class='cPage'>"+pageNo+"</span>";
         }
         else {
            pageBar+="<a href="+request.getContextPath()+"/admin/memberList.do?cPage="+pageNo+">"+pageNo+"</a>";
         }
         pageNo++;
      }
      if(pageNo>totalPage) {
         pageBar+="<span>[다음]</span>";
      }
      else {
         pageBar+="<a href="+request.getContextPath()+
         "/admin/memberList.do?cPage="+(pageNo)+">[다음]</a>";
      }
      
            
      //view페이지에 데이터 전송
      request.setAttribute("pageBar", pageBar);
      request.setAttribute("cPage", cPage);
      request.setAttribute("members",list);
      request.getRequestDispatcher("/views/admin/adminMemberList.jsp").forward(request, response);
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}
