//package com.semi.mento.controller;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
//
//import com.oreilly.servlet.MultipartRequest;
//import common.oreilly.servlet.multipart.AblingFileRenamePolicy;
//import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
//import com.semi.member.model.vo.Member;
//import com.semi.mento.model.service.MentoService;
//import com.semi.mento.model.vo.Mento;
//import com.semi.mento.model.vo.MentoUpload;
//
///**
// * Servlet implementation class MentoRegisterServlet
// */
//@WebServlet("/mento/mentoRegisterEnd.do")
//public class MentoRegisterEndServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public MentoRegisterEndServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		if(!ServletFileUpload.isMultipartContent(request)) {
//			request.setAttribute("msg", "멘토신청 실패 [form:ectype] 관리자에게 문의하세요!");
//			request.setAttribute("loc", "/");
//			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
//			
//			return;
//		}
//		
//		String root=getServletContext().getRealPath("/");
//		System.out.println(root);
//		
//		String saveDir=root+"/upload/mentoUpload";
//		
//		int maxSize=1024*1024*1024;
//		
//		HttpSession session = request.getSession();
//		Member memberLogin = (Member) session.getAttribute("loginMember");
//		MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, "UTF-8", new AblingFileRenamePolicy(memberLogin.getmId()));
//		
//		String mtnickname = mr.getParameter("mtnickname");
//		String mthowconfirm = mr.getParameter("mthowconfirm");
//		String mtacademic = mr.getParameter("mtacademic");
//		String mtacademicdept = mr.getParameter("mtacademicdept");
//		String mtgraduation = mr.getParameter("mtgraduation");
//		String mtBank = mr.getParameter("mtBack");
//		String mtAccountNumber = mr.getParameter("mtAccountNumber");
//		
//		String upMentoOrgProfile=mr.getOriginalFileName("mtprofileimg");
//		String upMentoReProfile = mr.getFilesystemName("mtprofileimg");
//		String upMentoOrgConfirm =mr.getOriginalFileName("mtconfirming");
//		String upMentoReConfirm= mr.getFilesystemName("mtconfirming");
//		String upMentoNameLicense = mr.getParameter("mtlicense");
//		String upMentoOrgLicense =mr.getOriginalFileName("mtlicenseimg");
//		String upMentoReLicense= mr.getFilesystemName("mtlicenseimg");
//		
//		int mNum = Integer.parseInt(mr.getParameter("mNum"));
//		System.out.println(mNum);
//		
//		Mento mt = new Mento(mtnickname, mthowconfirm, mtacademic, mtacademicdept, mtgraduation, mtBank, mtAccountNumber);
//		Member m = new Member(mNum);
//		
//		System.out.println(upMentoNameLicense);
//		System.out.println(upMentoOrgLicense);
//		System.out.println(upMentoReLicense);
//		
//		
//		
//
//		
//		
//		int result=new MentoService().registerMento(mt, m);
//		MentoUpload mtu = new MentoUpload(result, upMentoOrgProfile, upMentoReProfile, upMentoOrgConfirm, upMentoReConfirm, upMentoNameLicense, upMentoOrgLicense, upMentoReLicense);
//		int result2=new MentoService().registerMentoImage(mtu, result);
//		
//		String msg="";
//		String loc="";
//		
//		if(result>0) {
//			msg="멘토신청 완료";
//			loc="/";
//		}
//		else{
//			msg="멘토신청 실패";
//			loc="/";
//		}
//		
//		request.setAttribute("msg", msg);
//		request.setAttribute("loc", loc);
//		request.setAttribute("mentoInfo", mt);
//		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
//		
//		
//		
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}
