package com.semi.subcategory.model.service;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.semi.category.model.vo.Category;
import com.semi.subcategory.model.dao.SubCategoryDao;
import com.semi.subcategory.model.vo.SubCategory;

public class SubCategoryService {
	
	private SubCategoryDao dao=new SubCategoryDao();
	
	public int enrollSubCategory(SubCategory sc, Category c) {
		
		Connection conn=getConnection();
		int result=dao.enrollSubCategory(conn,sc,c);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
		
	}
	
	public List<SubCategory> selectSubCategory(){
		Connection conn = getConnection();
		List<SubCategory> list=dao.selectSubCategory(conn);
		close(conn);
		return list;
	}
	
	public int updateSubCategory(String subName, int subNum) {
		Connection conn=getConnection();
		int result=dao.updateSubCategory(conn,subName,subNum);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteSubCategory(String subName) {
		Connection conn=getConnection();
		int result=dao.deleteSubCategory(conn,subName);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}



	public List selectSub(int scNum) {
		Connection conn = getConnection();
		List<SubCategory> list=dao.selectSubCategory(conn,scNum);
		close(conn);
		return list;
	}

}
