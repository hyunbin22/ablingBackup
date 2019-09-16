package com.semi.order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.order.model.service.OrderService;
import com.semi.order.model.vo.Order;

/**
 * Servlet implementation class LecturePaymentViewServlet
 */
@WebServlet("/order/orderPaymentView.do")
public class OrderPaymentViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OrderPaymentViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		int mNum = Integer.parseInt(request.getParameter("mNum"));
		int oNum = Integer.parseInt(request.getParameter("oNum"));
		Order order = new OrderService().noPayOrder(oNum);
		request.setAttribute("order", order);
		request.getRequestDispatcher("/views/lecture/orderPayment.jsp").forward(request, response);

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
