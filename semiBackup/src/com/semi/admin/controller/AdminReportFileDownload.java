package com.semi.admin.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminReportFileDownload
 */
@WebServlet("/admin/reportFileDown")
public class AdminReportFileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminReportFileDownload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ori=request.getParameter("orifileName");
		//사용자가 업로드한 파일명
		String re=request.getParameter("refileName");
		//실제로 보낼 파일명
		
		//1.경로
		String saveDir=getServletContext().getRealPath("/upload/report");
		
		//파일 오픈!
		File f=new File(saveDir+"/"+re);
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(f));
		//보낼스트림 생성
		BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
		
//		브라우저에 따른 인코딩처리
		String resFileName="";
		boolean isMSIE=request.getHeader("user-agent").indexOf("MSIE")!=-1
				||request.getHeader("user-agent").indexOf("Trident")!=-1;
		if(isMSIE) {
			resFileName=URLEncoder.encode(ori,"UTF-8");
			resFileName=resFileName.replaceAll("\\+", "%20");
		}
		else {
			resFileName=new String(ori.getBytes("UTF-8"),"ISO-8859-1");
		}
		
		//response해더설정
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", 
						"attachment;filename="+resFileName);
		
		//파일 쓰기
		int read=-1;
		while((read=bis.read())!=-1) {
			bos.write(read);
		}
		
		bos.close();
		bis.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
