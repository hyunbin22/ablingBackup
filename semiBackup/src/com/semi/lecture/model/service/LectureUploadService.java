package com.semi.lecture.model.service;

import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.rollback;
import java.sql.Connection;
import java.util.List;

import com.semi.lecture.model.dao.LectureUploadDao;
import com.semi.lecture.model.vo.LectureUpload;

public class LectureUploadService {
	
	private LectureUploadDao dao = new LectureUploadDao();

	public int insertLectureImage(LectureUpload lecup, int lecNum, String category) {
	      Connection conn = getConnection();
	      int result=dao.insertLectureImage(conn,lecup,lecNum,category);
	      if(result>0) {
	         commit(conn);
	      }else {
	         rollback(conn);
	      }
	      close(conn);
	      return result;
	 }
}
