package com.semi.message.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OpenLecMessageServlet
 */
@WebServlet("/message/openLecMessage.do")
public class OpenLecMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OpenLecMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String toId = request.getParameter("toId");
		request.setAttribute("toId", toId);
		System.out.println("openLecMessageServlet : "+toId);
		request.getRequestDispatcher("/views/common/webMessageView.jsp?toId="+toId).forward(request, response);;
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
