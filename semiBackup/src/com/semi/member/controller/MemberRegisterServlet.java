package com.semi.member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.member.model.service.MemberService;
import com.semi.member.model.vo.Member;

/**
 * Servlet implementation class MemberRegisterServlet
 */
@WebServlet(name="MemberRegister",urlPatterns = "/member/MemberRegisterServlet.do")
public class MemberRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		char gender = request.getParameter("gender").charAt(0);
		Date birth = Date.valueOf(request.getParameter("birth"));
		String email = request.getParameter("email");
		String phone = request.getParameter("tel1");
		phone+=request.getParameter("tel2");
		phone+=request.getParameter("tel3");
		MemberService service = new MemberService();
		Member m = new Member(id,pw,name,gender,birth,email,phone);
		int result = service.insertMember(m);
		String view = "";
		String msg = "";
		String loc = "/";
		msg = result>0 ? name+"님 회원가입을 축하합니다!^^!" : "회원가입이 실패하였습니다.";
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.setAttribute("title", "회원가입이 완료되었습니다");
		request.setAttribute("memberinfo", m);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);	
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
