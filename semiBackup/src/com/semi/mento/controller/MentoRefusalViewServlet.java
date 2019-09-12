package com.semi.mento.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.mento.model.service.MentoService;

/**
 * Servlet implementation class MentoRefusalViewServlet
 */
@WebServlet("/mento/mentoRefusalView.do")
public class MentoRefusalViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MentoRefusalViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int mtNum = Integer.parseInt(request.getParameter("mtNum"));
		String mtReason = new MentoService().mentoView(mtNum).getMtReason();
		
		request.setAttribute("mtNum", mtNum);
		request.setAttribute("mtReason", mtReason);
		request.setAttribute("mtLec", "mt");
		request.getRequestDispatcher("/views/admin/popRefusal.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
