package com.semi.sublocal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.subcategory.model.vo.SubCategory;
import com.semi.sublocal.model.service.SubLocalService;
import com.semi.sublocal.model.vo.SubLocal;

/**
 * Servlet implementation class SelectSublocalServlet
 */
@WebServlet("/selectSubLocal")
public class SelectSublocalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectSublocalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int localNum = Integer.parseInt(request.getParameter("localNum"));
		
		SubLocalService service = new SubLocalService();
		List<SubLocal> list = service.selectSublocal(localNum);
		
		
		System.out.println(localNum);
		System.out.println(list);
		
		String csv = "";
		if(!list.isEmpty() ) {
			for(int i = 0; i < list.size(); i++)
			{
				if(i != 0)
				{
					csv += ",";
				}
				csv += list.get(i);
			}
		}
		
		response.setContentType("text/csv;charset=UTF-8");
		response.getWriter().write(csv);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
