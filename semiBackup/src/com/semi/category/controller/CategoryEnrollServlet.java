package com.semi.category.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.category.model.service.CategoryService;
import com.semi.category.model.vo.Category;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/admin/categoryEnroll")
public class CategoryEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryEnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String scName = request.getParameter("scName");
		
		Category c = new Category(scName);
		System.out.println(c);
		
		CategoryService service=new CategoryService();
		int result=service.enrollCategory(c);
		

		//List<Category> list = service.selectCategory(scName);
		
	
		System.out.println(result);

		
		
		String msg="";
		String loc="/";
		msg=result>0?"카테고리 등록 성공":"카테고리 등록 실패";
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
//		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
