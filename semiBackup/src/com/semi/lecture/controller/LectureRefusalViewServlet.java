package com.semi.lecture.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.lecture.model.service.LectureService;

/**
 * Servlet implementation class LectureRefusalViewServlet
 */
@WebServlet("/lecture/lectureRefusalView.do")
public class LectureRefusalViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LectureRefusalViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int lecNum = Integer.parseInt(request.getParameter("lecNum"));
		String lecReason = new LectureService().lectureView(lecNum).getLecReason();
		
		request.setAttribute("lecNum", lecNum);
		request.setAttribute("lecReason", lecReason);
		request.setAttribute("mtLec", "lec");
		request.getRequestDispatcher("/views/admin/popRefusal.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
