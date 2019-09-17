package com.semi.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.member.model.service.MemberService;
import com.semi.member.model.vo.Member;

/**
 * Servlet implementation class FindPasswordServlet
 */
@WebServlet("/member/findPwd")
public class FindPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mId = request.getParameter("mId");
		String email = request.getParameter("email");
		
		System.out.println(mId);
		System.out.println(email);
		
		MemberService service = new MemberService();
		Member m = service.findMemberPwd(mId, email);
		System.out.println(m);
		
		String msg = "";
		String loc = "";
		
		if(m != null)
		{
			request.setAttribute("member", m);
			request.getRequestDispatcher("/views/member/updatePwd.jsp").forward(request, response);
		}
		else
		{
			msg="입력하신 정보가 일치하지 않습니다.";
			loc="/views/member/findPwd.jsp";
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
