package com.semi.message.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.member.model.vo.Member;
import com.semi.message.model.service.MessageService;
import com.semi.message.model.vo.Message;

/**
 * Servlet implementation class OpenMessageServlet
 */
@WebServlet("/message/openMessage.do")
public class OpenMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OpenMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Member m = (Member) session.getAttribute("loginMember");
//		Mento mt = new MentoService().mentoView(m.getmNum());
//		if(mt!=null) {
//			mt = (Mento) session.getAttribute("loginMento");
//		}
//		
//		int readCount = new MessageService().noReadCount(m.getmNum());
//		
//		request.setAttribute("member", m);
//		request.setAttribute("mento", mt);
//		request.setAttribute("readCount", readCount);
		
//		List<Message> messageList = new MessageService().messageList(m.getmNum(),m.getmNum());
//		request.setAttribute("messageList", messageList);
//		request.getRequestDispatcher("/views/common/webMessage.jsp").forward(request, response);
		
//		response.sendRedirect(request.getContextPath()+"/views/common/webMessage.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
