package com.semi.lecture.model.service;

import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.close;
import java.sql.Connection;
import java.util.List;

import com.semi.lecture.model.dao.LectureUploadDao;
import com.semi.lecture.model.vo.LectureUpload;

public class LectureUploadService {
	
	private LectureUploadDao dao = new LectureUploadDao();

	/*
	 * public List<LectureUpload> lectureUpList() {
	 * 
	 * Connection conn = getConnection(); List<LectureUpload> list =
	 * dao.lectureUpList(conn); close(conn); return list; }
	 */

}
