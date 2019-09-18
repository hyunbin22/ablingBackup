package com.semi.order.model.service;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.rollback;

import java.sql.Connection;

import com.semi.order.model.dao.OrderDao;
import com.semi.order.model.vo.Order;

public class OrderService {
	
private OrderDao dao = new OrderDao();
	
//	수업신청
//	public int insertAppForClass(Order order) {
//		Connection conn = getConnection();
//
//		int result = dao.insertOrder(conn, order);
//		if(result>0) {
//			commit(conn);
//			result = dao.selectOrder(conn, lecNum, mId, price).getsNum();
//		} else {
//			rollback(conn);
//		}
//		close(conn);
//		return result;
//	}
	
	//결제 전 신청내역 가져오기
	public Order noPayOrder(int oNum) {
		Connection conn = getConnection();
		Order order = null;
		order = dao.selectOrder(conn, oNum);
		close(conn);
		return order;
		
	}

	//결제 Y로 바꾸기
	public int updatePayment(int oNum) {
		Connection conn = getConnection();
		int result = dao.updatePayment(conn, oNum);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

}
