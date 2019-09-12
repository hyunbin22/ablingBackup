package com.semi.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.lecture.model.service.LectureService;
import com.semi.lecture.model.vo.Lecture;

/**
 * Servlet implementation class AdminClassDetailServlet
 */
@WebServlet("/admin/AdminLectureDetailServlet.do")
public class AdminLectureDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLectureDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int lecNum = Integer.parseInt(request.getParameter("lecNum"));
		int temp = 0;
		Lecture lec = new LectureService().lectureView(lecNum);
		if(lec.getLecCheck()=='N') {
			temp = 1;
			if(lec.getLecReason()!=null) {
				temp = 2;
			}
		} else {
			temp = 0;
		}
		request.setAttribute("temp", temp);
		request.setAttribute("lecture", lec);
		request.getRequestDispatcher("/views/admin/adminLectureDetail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
