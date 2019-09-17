package com.semi.report.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.report.model.service.ReportService;
import com.semi.report.model.vo.Report;

/**
 * Servlet implementation class MemberReportHistoryServlet
 */
@WebServlet("/member/reportHistroy")
public class MemberReportHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberReportHistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mNum = Integer.parseInt(request.getParameter("mNum"));
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		int numPerPage=10;
		ReportService service=new ReportService();
		int totalReport = service.selectReportCount3(mNum);

		
		List<Report> list = new ReportService().reportHistoyry(cPage,numPerPage,mNum);
		System.out.println("신고내역 조회 객체들 : " + list);
		System.out.println("total : " + totalReport);
		System.out.println("mNum : " + mNum);
		
		//페이징처리 구현
				int totalPage=(int)Math.ceil((double)totalReport/numPerPage);
				
				String pageBar="";
				int pageSizeBar=5;
				int pageNo=((cPage-1)/pageSizeBar)*pageSizeBar+1;
				int pageEnd=pageNo+pageSizeBar-1;
				
				if(pageNo==1) {
					pageBar+="<span>[이전]</span>";
				}else {
					pageBar+="<a href='"+request.getContextPath()
					+"/member/reportHistroy?cPage="+(pageNo-1)+"'>[이전]</a>";
				}
				
				while(!(pageNo>pageEnd||pageNo>totalPage)) {
					if(pageNo==cPage) {
						pageBar+="<span>"+pageNo+"</span>";
					}
					else {
						pageBar+="<a href='"+request.getContextPath()
						+"/member/reportHistroy?cPage="+pageNo+"'>"+pageNo+"</a>";
					}
					pageNo++;
				}
				
				if(pageNo>totalPage) {
					pageBar+="<span>[다음]</span>";
				}
				else {
					pageBar+="<a href='"+request.getContextPath()
					+"/member/reportHistroy?cPage="+(pageNo)+"'>[다음]</a>";
				}
				
				request.setAttribute("pageBar", pageBar);
				request.setAttribute("cPage", cPage);
				request.setAttribute("reportHistory", list);
				request.getRequestDispatcher("/views/report/reportHistory.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
