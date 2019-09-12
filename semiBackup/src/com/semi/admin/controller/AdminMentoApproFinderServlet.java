package com.semi.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.mento.model.service.MentoService;
import com.semi.mento.model.vo.Mento;

/**
 * Servlet implementation class AdminMentoApproFinderServlet
 */
@WebServlet("/admin/mentoApproFinder.do")
public class AdminMentoApproFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminMentoApproFinderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("searchType");
		String data = request.getParameter("searchKeyword");
		int temp = Integer.parseInt(request.getParameter("temp"));
		
		int cPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
			cPage = 1;
		}
		int numPerPage=10;
		int countMentoApproval = new MentoService().countMentoApproval(type, data);
		
		List<Mento> mentoList = new MentoService().mentoFindList(type, data, cPage, numPerPage, temp);
		
		int mentoTotalPage=(int)Math.ceil((double)countMentoApproval/numPerPage);
		String pageBar="";
		int pageSizeBar=5;
		int pageNo=((cPage-1)/pageSizeBar)*pageSizeBar+1;
		int pageEnd=pageNo+pageSizeBar-1;
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>&nbsp;";
		}
		else {
			pageBar+="<a href="+request.getContextPath()+"/admin/AdminApprovalServlet.do?cPage="+(pageNo-1)+">[이전]</a>&nbsp;";
		}
		while(!(pageNo>pageEnd||pageNo>mentoTotalPage)) {
			if(pageNo==cPage) {
				pageBar+="<span class='admin-appro-cPage'>"+pageNo+"</span>&nbsp;";
			}
			else {
				pageBar+="<a href="+request.getContextPath()+"/admin/AdminApprovalServlet.do?cPage="+pageNo+">"+pageNo+"</a>&nbsp;";
			}
			pageNo++;
		}
		if(pageNo>mentoTotalPage) {
			pageBar+="<span>[다음]</span>";
		}
		else {
			pageBar+="<a href="+request.getContextPath()+
			"/admin/AdminMentoApproval.do?cPage="+(pageNo)+">[다음]</a>";
		}
		
		//view페이지에 데이터 전송
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.setAttribute("mentoList",mentoList);
		if(temp==0) {
			request.getRequestDispatcher("/views/admin/adminMentoList.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/views/admin/adminMentoApproval.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
