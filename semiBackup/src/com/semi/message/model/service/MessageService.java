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
	
	//안읽은 메세지수 전체 불러오기
	public int noReadCount(String id) {
		Connection conn = getConnection();
		int result = dao.noReadCount(conn, id);
		close(conn);
		return result;
	}
	
	//대화상대별 안읽은 메세지 수
	public int noReadCountById(String fromId, String toId) {
		Connection conn = getConnection();
		int result = dao.noReadCountById(conn, fromId, toId);
		close(conn);
		return result;
	}
	
	//한 사용자 메세지 읽음 처리
	public int readChat(int fromMNum, int toMNum) {
		Connection conn = getConnection();
		int result = dao.readChat(conn, fromMNum, toMNum);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	//메시지 불러오기
	public List<Message> messageListById(int fromMNum,int toMNum,String chatId) {
		Connection conn = getConnection();
		List<Message> list = dao.messageListById(conn, fromMNum, toMNum, chatId);
		close(conn);
		return list;
	}
	
	public List<Message> messageListByRecent(int fromMNum, int toMNum, int number) {
		Connection conn = getConnection();
		List<Message> list = dao.messageListByRecent(conn, fromMNum, toMNum, number);
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
		close(conn);
		return result;
	}
	
	//각 대화상대별 대화내역 가져오기
	public List<Message> getMessageBox(String userId){
		Connection conn = getConnection();
		List<Message> list = dao.getMessageBox(conn, userId);
		close(conn);
		return list;
	}

	
}
