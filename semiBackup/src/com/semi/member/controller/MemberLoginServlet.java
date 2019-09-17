package com.semi.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.member.model.service.MemberService;
import com.semi.member.model.vo.Member;
import com.semi.mento.model.service.MentoService;
import com.semi.mento.model.vo.Mento;

/**
 * Servlet implementation class LoginMemberServlet
 */
@WebServlet(name = "MemberLogin", urlPatterns = "/member/memberLoginServlet.do")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		String mId = request.getParameter("mId");
		String mPw = request.getParameter("mPw");
		
		MemberService service = new MemberService();
		Member m = service.selectId(mId,mPw);
		
		String view = "";

		if(m != null)
		{
			System.out.println(m.getmUse()+"");
			if(m.getmUse() != 'N') {
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", m);
			
			MentoService service2 = new MentoService();
			Mento mt = service2.mentoView(m.getmNum());

			if(mt != null)
			{
				session.setAttribute("loginMento", mt);
			}
			
			view = "/";
			response.sendRedirect(request.getContextPath()+view);
			} else {
				String msg = "로그인 할 수 없습니다. 관리자에게 문의하세요!";
				request.setAttribute("msg", msg);
				view = "/views/common/msg.jsp";
				String loc = "/";
				request.setAttribute("loc", loc);
				RequestDispatcher rd = request.getRequestDispatcher(view);
				rd.forward(request, response);
			}
			
			
		}
		else
		{
			
			String msg = "아이디나 비밀번호가 일치하지 않습니다.";
			request.setAttribute("msg", msg);
			view = "/views/common/msg.jsp";
			String loc = "/";
			request.setAttribute("loc", loc);
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
			
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
