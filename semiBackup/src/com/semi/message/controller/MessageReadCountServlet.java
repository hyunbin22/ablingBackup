package com.semi.message.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.member.model.service.MemberService;
import com.semi.member.model.vo.Member;
import com.semi.message.model.service.MessageService;

/**
 * Servlet implementation class MessageReadCountServlet
 */
@WebServlet("/message/readCount.do")
public class MessageReadCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MessageReadCountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		Member m = (Member) session.getAttribute("loginMember");
		int readCount = (int) session.getAttribute("readCount");
		int newReadCount = new MessageService().noReadCount(m.getmNum());
		if(readCount != newReadCount) {
			session.setAttribute("readCount", newReadCount);
		}
		response.getWriter().write(newReadCount);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
