package com.semi.subcategory.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.subcategory.model.service.SubCategoryService;
import com.semi.subcategory.model.vo.SubCategory;

/**
 * Servlet implementation class SelectSubCategoryServlet
 */
@WebServlet("/selectSubCategory")
public class SelectSubCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectSubCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int scNum = Integer.parseInt(request.getParameter("scNum"));

		SubCategoryService service = new SubCategoryService();
		List<SubCategory> list = service.selectSub(scNum);

		
		
		
		System.out.println(scNum);
		System.out.println(list);
		
//		request.setAttribute("scList", list);
//		request.getRequestDispatcher("/views/lecture/lectureEnrollEnd.jsp").forward(request, response);
		
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
