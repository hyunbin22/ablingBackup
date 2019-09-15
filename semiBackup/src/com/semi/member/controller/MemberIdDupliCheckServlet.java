package com.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.member.model.service.MemberService;

/**
 * Servlet implementation class MemberIdDupliCheckServlet
 */
@WebServlet("/member/MemberIdDupliCheckServlet.do")
public class MemberIdDupliCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberIdDupliCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String userId = request.getParameter("userId");
		if(userId== null || userId.equals("")) response.getWriter().write(-1);
		response.getWriter().write(new MemberService().idDupliCheck(userId)+"");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
