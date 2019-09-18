package com.semi.report.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import common.oreilly.servlet.multipart.AblingFileRenamePolicy;

import com.oreilly.servlet.MultipartRequest;
import com.semi.member.model.vo.Member;
import com.semi.message.model.service.MessageService;
import com.semi.report.model.service.ReportService;
import com.semi.report.model.vo.Report;
import com.semi.report.model.vo.ReportUpload;

/**
 * Servlet implementation class MemberReportServlet
 */
@WebServlet("/Report.do")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "신고 실패 ![form:ectype] 관리자에게 문의하세요!");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			
			return;
		}
		
		String root=getServletContext().getRealPath("/");
		
		String saveDir=root+"/upload/report";
		
		int maxSize=1024*1024*1024;
		
		HttpSession session = request.getSession();
		Member memberLogin = (Member) session.getAttribute("loginMember");
		MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, "UTF-8", new AblingFileRenamePolicy(memberLogin.getmId()));
		
		int mNum = Integer.parseInt(mr.getParameter("mNum"));	
		String reportId = mr.getParameter("rId");
		String reportTitle = mr.getParameter("rTitle");
		String reportContent = mr.getParameter("rContent");
		
		
		String reReportOriFile = mr.getOriginalFileName("reportPhoto");
		String reReportReFile = mr.getFilesystemName("reportPhoto");

		
		Report rp = new Report(reportId, reportTitle, reportContent);
		Member m = new Member(mNum);
		
		//reportDB에 저장
		int result = new ReportService().registerReport(rp, m);
		
		
		ReportUpload ru = new ReportUpload(reReportOriFile, reReportReFile);
		int result2 = new ReportService().regsterReportImage(ru, result);
		
		
		String msg="";
		String loc="";
		
		if(result>0 && result2>0) {
			msg="신고 완료";
			loc="/";
		}
		else{
			msg="신고 실패";
			loc="/";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.setAttribute("reportInfo", rp);
		request.getRequestDispatcher("views/common/msg.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
