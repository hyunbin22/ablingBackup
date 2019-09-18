package com.semi.order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.order.model.service.OrderService;

/**
 * Servlet implementation class OrderUpdatePaymentServlet
 */
@WebServlet("/order/orderUpdatePayment.do")
public class OrderUpdatePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OrderUpdatePaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int oNum = Integer.parseInt(request.getParameter("oNum"));
		int result = new OrderService().updatePayment(oNum);
		System.out.println(result);
		response.getWriter().write(result+"");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
