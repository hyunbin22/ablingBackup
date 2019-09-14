package com.semi.message.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.member.model.service.MemberService;
import com.semi.message.model.service.MessageService;

/**
 * Servlet implementation class MessageSubmitServlet
 */
@WebServlet("/message/messageSubmit.do")
public class MessageSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MessageSubmitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int fromMNum = Integer.parseInt(request.getParameter("fromMNum"));
		int toMNum = Integer.parseInt(request.getParameter("toMNum"));
		
		String fromId = new MemberService().selectMember(fromMNum).getmId();
		String toId = new MemberService().selectMember(toMNum).getmId();
		
		String text = request.getParameter("text");
		if(fromId == null || fromId.equals("") || toId == null || toId.equals("") || text == null || text.equals("")) {
			response.getWriter().write("0");
		} else {
			fromId = URLDecoder.decode(fromId, "UTF-8");
			toId = URLDecoder.decode(toId, "UTF-8");
			text = URLDecoder.decode(text, "UTF-8");
			response.getWriter().write(new MessageService().insertMessage(toId,fromId,text)+"");
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
