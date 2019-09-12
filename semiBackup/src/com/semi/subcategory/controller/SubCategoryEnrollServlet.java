package com.semi.subcategory.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.category.model.vo.Category;
import com.semi.subcategory.model.service.SubCategoryService;
import com.semi.subcategory.model.vo.SubCategory;

/**
 * Servlet implementation class SubCategoryEnrollServlet
 */
@WebServlet("/admin/subcategoryEnroll")
public class SubCategoryEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubCategoryEnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String subName = request.getParameter("subName");
		int scNum = Integer.parseInt(request.getParameter("scNum"));
		
		System.out.println(subName);
		System.out.println(scNum);
		
		SubCategory sc = new SubCategory(subName);
		Category c = new Category(scNum);
		
		SubCategoryService service=new SubCategoryService();
		int result=service.enrollSubCategory(sc,c);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
