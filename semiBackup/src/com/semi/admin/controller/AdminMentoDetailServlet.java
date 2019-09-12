package com.semi.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.mento.model.service.MentoService;
import com.semi.mento.model.vo.Mento;

/**
 * Servlet implementation class AdminMentoDetailServlet
 */
@WebServlet("/admin/AdminMentoDetailServlet.do")
public class AdminMentoDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminMentoDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int mtNum = Integer.parseInt(request.getParameter("mtNum"));
		int temp = 0;
		Mento mt = new MentoService().mentoView(mtNum);
		if(mt.getMtCheck()=='N') {
			temp = 1;	//승인안된거에서 불러오는것
		} else {
			temp = 0;	//승인된거에서 불러옴
		}
		request.setAttribute("temp", temp);
		request.setAttribute("mento", mt);
		request.getRequestDispatcher("/views/admin/adminMentoDetail.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
