package com.semi.message.model.service;

import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.close;

import java.sql.Connection;

import com.semi.message.model.dao.MessageDao;

public class MessageService {
	
	private MessageDao dao = new MessageDao();
	
	public int allReadCount(int mNum) {
		Connection conn = getConnection();
		int result = dao.allReadCount(conn, mNum);
		close(conn);
		return result;
		
	}
	
}
