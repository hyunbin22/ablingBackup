package com.semi.message.controller;

import java.io.IOException;
import java.net.URLDecoder;

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
		
		String userId = request.getParameter("userId");
		if(userId==null || userId.equals("")) {
			response.getWriter().write("0");
		} else {
			userId = URLDecoder.decode(userId,"UTF-8");
			response.getWriter().write(new MessageService().noReadCount(userId)+"");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
