package com.semi.lecture.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.category.model.service.CategoryService;
import com.semi.category.model.vo.Category;
import com.semi.local.model.service.LocalService;
import com.semi.local.model.vo.Local;
import com.semi.subcategory.model.service.SubCategoryService;
import com.semi.subcategory.model.vo.SubCategory;
import com.semi.sublocal.model.service.SubLocalService;
import com.semi.sublocal.model.vo.SubLocal;

/**
 * Servlet implementation class LectureEnrollServlet
 */
@WebServlet("/mento/enrollLecture.do")
public class LectureEnrollServlet extends HttpServlet {
	   private static final long serialVersionUID = 1L;
       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public LectureEnrollServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

	   /**
	    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	    */
	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      
	      String mtNum = request.getParameter("mtNum");
	      
	        List<Category> c= (List)new CategoryService().selectCategory();
	        List<SubCategory> sc= (List)new SubCategoryService().selectSubCategory();
	        List<Local> l= (List)new LocalService().selectLocal();
	        List<SubLocal> sl= (List)new SubLocalService().selectSubLocal();
	        
	        
	        request.setAttribute("category", c);
	        request.setAttribute("subcategory", sc);
	        request.setAttribute("local", l);
	        request.setAttribute("subLocal", sl);
	        
	      request.setAttribute("mtNum", mtNum);
	      request.getRequestDispatcher("/views/lecture/enrollLecture.jsp").forward(request, response);
	      
	      
	   }

	   /**
	    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	    */
	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      // TODO Auto-generated method stub
	      doGet(request, response);
	   }


}
