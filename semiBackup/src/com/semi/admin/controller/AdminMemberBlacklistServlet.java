package com.semi.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.message.model.service.MessageService;
import com.semi.report.model.service.ReportService;

/**
 * Servlet implementation class AdminMemberBlacklistServlet
 */
@WebServlet("/admin/memberBlacklist")
public class AdminMemberBlacklistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberBlacklistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int mAttackerNum = Integer.parseInt(request.getParameter("attNo"));
		String mId = request.getParameter("reportId");
		String check = request.getParameter("check");
		String rReply = request.getParameter("rReply");
		
		ReportService service = new ReportService();
		int result3 = service.regsterReport2(mAttackerNum, rReply);

		
		if(check.equals("Y"))
		{
			int result2 = service.memberUse(mAttackerNum);
		}
		//메세지 보내기 처리
		if(result3>0) {
			new MessageService().insertMessage("msgAdmin", mId, "신고내역이 처리되었습니다.");
		}
		
		String msg=result3>0?"처리완료":"처리실패";
		String loc="/admin/AdminReportApproval.do";
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp")
		.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
