package com.semi.lecture.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.lecture.model.service.LectureService;

/**
 * Servlet implementation class UpdateCheckLectureServlet
 */
@WebServlet("/lecture/updateCheckLecture.do")
public class UpdateCheckLectureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateCheckLectureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int lecNum = Integer.parseInt(request.getParameter("lecNum"));
		int result = new LectureService().updateCheckLecture(lecNum);
		String msg = "";
		String loc = "/admin/AdminLectureApproval.do";
		msg = result>0?"강의 승인하였습니다.":"강의승인이 실패하였습니다.";
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/common/msgPop.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
