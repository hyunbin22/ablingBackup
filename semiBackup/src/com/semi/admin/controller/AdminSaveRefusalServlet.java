package com.semi.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.lecture.model.service.LectureService;
import com.semi.lecture.model.vo.Lecture;
import com.semi.mento.model.service.MentoService;
import com.semi.mento.model.vo.Mento;
import com.semi.message.model.service.MessageService;

/**
 * Servlet implementation class MentoSaveRefusalServlet
 */
@WebServlet("/admin/saveRefusal.do")
public class AdminSaveRefusalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminSaveRefusalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mtLec=request.getParameter("mtLec");
		
		String msg = "";
		String loc = "";
		
		if(mtLec.equals("mt")) {
			int mtNum = Integer.parseInt(request.getParameter("mtNum"));
			String reason = request.getParameter("reason");
			int result = new MentoService().updateRefusalMento(mtNum, reason);
			msg = result>0?"거절완료":"거절실패";
			loc = "/admin/AdminMentoApproval.do";
			//거절되었다는 메세지 보내기
			Mento mt = new MentoService().mentoView(mtNum);
			if(result>0) {
				new MessageService().insertMessage("msgAdmin", mt.getMember().getmId(), "멘토신청이 거절되었습니다.");
			}
			
		} else if(mtLec.equals("lec")) {
			int lecNum = Integer.parseInt(request.getParameter("lecNum"));
			String reason = request.getParameter("reason");
			int result = new LectureService().updateRefusalLecture(lecNum, reason);
			msg = result>0?"거절완료":"거절실패";
			loc = "/admin/AdminLectureApproval.do";
			//거절되었다는 메세지 보내기
			Lecture lec = new LectureService().lectureView(lecNum);
			if(result>0) {
				new MessageService().insertMessage("msgAdmin", lec.getLecMento().getMember().getmId(), "["+lec.getLecName()+"] 강의가 거절되었습니다.");
			}
			
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
