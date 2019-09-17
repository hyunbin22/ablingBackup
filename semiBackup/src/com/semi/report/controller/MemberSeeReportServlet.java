package com.semi.report.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.report.model.service.ReportService;
import com.semi.report.model.vo.Report;

/**
 * Servlet implementation class MemberSeeReportServlet
 */
@WebServlet("/member/seeReport")
public class MemberSeeReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSeeReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("reportTitle");
		
		ReportService service = new ReportService();
		Report rp = service.seeReport(title);
		
		System.out.println(title);
		System.out.println(rp);
		
		request.setAttribute("seeReport", rp);
		request.getRequestDispatcher("/views/report/seeReport.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
