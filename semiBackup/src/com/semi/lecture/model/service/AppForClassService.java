package com.semi.lecture.model.service;


import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.rollback;

import java.sql.Connection;

import com.semi.lecture.model.dao.AppForClassDao;
import com.semi.lecture.model.vo.AppForClass;

public class AppForClassService {

	private AppForClassDao dao = new AppForClassDao();
	
	public int insertAppForClass(int lecNum, String mId, String text, int price) {
		Connection conn = getConnection();

		int result = dao.insertAppForClass(conn, lecNum, mId, text, price);
		if(result>0) {
			commit(conn);
			result = dao.selectAppForClass(conn, lecNum, mId, price).getsNum();
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public AppForClass selectAppForClass(int sNum, String mId, int lecNum) {
		Connection conn = getConnection();
		AppForClass afc = null;
		afc = dao.selectAppForClass(conn, sNum, mId, lecNum);
		close(conn);
		return afc;
		
	}

}
