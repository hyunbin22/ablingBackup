package com.semi.sublocal.model.service;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.semi.local.model.vo.Local;
import com.semi.subcategory.model.vo.SubCategory;
import com.semi.sublocal.model.dao.SubLocalDao;
import com.semi.sublocal.model.vo.SubLocal;

public class SubLocalService {
	
	private SubLocalDao dao=new SubLocalDao();
	
	public int enrollSubLocal(SubLocal sl, Local l) {
		
		Connection conn=getConnection();
		int result=dao.enrollSubLocal(conn,sl,l);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public List<SubLocal> selectSubLocal(){
		Connection conn = getConnection();
		List<SubLocal> list=dao.selectSubLocal(conn);
		close(conn);
		return list;
	}
	
	public int updateSubLocal(String localCountry, int localsubNum) {
		Connection conn=getConnection();
		int result=dao.updateSubLocal(conn,localCountry,localsubNum);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteSubLocal(String localCountry) {
		Connection conn=getConnection();
		int result=dao.deleteSubLocal(conn,localCountry);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public List<SubLocal> selectSublocal(int localNum) {
		Connection conn = getConnection();
		List<SubLocal> list=dao.selectSubLocal(conn,localNum);
		close(conn);
		return list;
	}

}
