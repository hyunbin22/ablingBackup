package com.semi.message.model.service;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.semi.message.model.dao.MessageDao;
import com.semi.message.model.vo.Message;

public class MessageService {
	
	private MessageDao dao = new MessageDao();
	
	public int noReadCount(int mNum) {
		Connection conn = getConnection();
		int result = dao.noReadCount(conn, mNum);
		close(conn);
		return result;
		
	}
	
	//메시지 불러오기
	public List<Message> messageList(int messageNum, int toMNum, int fromMNum) {
		Connection conn = getConnection();
		List<Message> list = dao.messageList(conn, messageNum, toMNum, fromMNum);
		close(conn);
		return list;
	}
	
	//메세지 저장
	public int insertMessage(String fromId, String toId, String text) {
		Connection conn = getConnection();
		int result = dao.insertMessage(conn, fromId, toId, text);
		if(result >  0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
}
