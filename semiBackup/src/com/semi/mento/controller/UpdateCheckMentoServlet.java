package com.semi.mento.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.mento.model.service.MentoService;
import com.semi.mento.model.vo.Mento;
import com.semi.message.model.service.MessageService;

/**
 * Servlet implementation class AdminUpdateMentoApproServlet
 */
@WebServlet("/mento/updateCheckMento.do")
public class UpdateCheckMentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateCheckMentoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mtNum = Integer.parseInt(request.getParameter("mtNum"));
		int result = new MentoService().updateCheckMento(mtNum);
		String msg = "";
		String loc = "/admin/AdminMentoApproval.do";
		msg = result>0?"멘토 승인하였습니다.":"멘토승인이 실패하였습니다.";
		
		//멘토 승인되었다는 메세지 보내기
		Mento mt = new MentoService().mentoView(mtNum);
		if(result>0) {
			new MessageService().insertMessage("msgAdmin", mt.getMember().getmId(), "멘토신청이 승인되었습니다.");
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
