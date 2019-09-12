package com.semi.local.model.service;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.semi.category.model.vo.Category;
import com.semi.local.model.dao.LocalDao;
import com.semi.local.model.vo.Local;

public class LocalService {
	
	private LocalDao dao=new LocalDao();
	
	public int enrollLocal(Local c) {
		Connection conn=getConnection();
		int result=dao.enrollLocal(conn,c);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public List<Local> selectLocal(){
		Connection conn = getConnection();
		List<Local> list=dao.selectLocal(conn);
		close(conn);
		return list;
	}
	
	public int updateLocal(String localCity, int localNum) {
		Connection conn=getConnection();
		int result=dao.updateLocal(conn,localCity,localNum);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteLocal(String localCity) {
		Connection conn=getConnection();
		int result=dao.deleteLocal(conn,localCity);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

}
