package com.semi.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.report.model.service.ReportService;
import com.semi.report.model.vo.Report;
import com.semi.report.model.vo.ReportUpload;

/**
 * Servlet implementation class AdminReportViewServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin/reportView" })
public class AdminReportViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminReportViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int reportNo = Integer.parseInt(request.getParameter("reportNo"));
		
		System.out.println("신고글 번호 : " + reportNo);
			
		Report rp = new ReportService().selectReportContent(reportNo);
		Report rp2 = new ReportService().selectReportContent2(reportNo);
		ReportUpload rpu = new ReportService().selectReportUpload(reportNo);
		
		
		request.setAttribute("reportUp", rpu);
		request.setAttribute("report", rp);
		request.setAttribute("report2", rp2);
		
		System.out.println(rp);
		System.out.println(rpu);
		request.getRequestDispatcher("/views/admin/reportView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
