package com.semi.lecture.model.service;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.semi.lecture.model.dao.LectureDao;
import com.semi.lecture.model.vo.Lecture;
import com.semi.mento.model.vo.Mento;

public class LectureService {
	
	private LectureDao dao = new LectureDao();
	
	//승인카운트
	public int countLectureApproval() {
		Connection conn = getConnection();
		int result = dao.countLectureApproval(conn);
		close(conn);
		return result;
	}
	
	//강의승인리스트
	public List<Lecture> lectureApproList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Lecture> list = dao.lectureApproList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	//강의승인목록 검색
	public List<Lecture> lectureApproFindList(String type, String data, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Lecture> list = dao.lectureApproList(conn, type, data, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	//강의 한개 보기
	public Lecture lectureView(int lecNum) {
		Connection conn = getConnection();
		Lecture lec = dao.lectureView(conn, lecNum);
		close(conn);
		return lec;
	}

	//강의 신청 거절사유 등록
	public int updateRefusalLecture(int lecNum, String reason) {
		Connection conn = getConnection();
		int result = dao.updateRefusalLecture(conn, lecNum, reason);
		if(result > 0) {
			commit(conn);
		} else rollback(conn);
		close(conn);
		
		return result;
	}
	
	//강의 승인
	public int updateCheckLecture(int lecNum) {
		Connection conn = getConnection();
		int result = dao.updateCheckLecture(conn, lecNum);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	//멘토가 강의 등록신청
	public int insertLecture(Lecture l , int mtNum) {
	      
      Connection conn= getConnection();
      int result=dao.insertLecture(conn, l , mtNum);
      
      if(result>0) {
         commit(conn);
         result = dao.selectSeqLecNum(conn, l.getMtNum());
      }else {
         rollback(conn);
      }
      return result;
   }
	
	// 강의 전체 갯수!(승인된것)
   public int selectLectureCount() {
      Connection conn=getConnection();
      int result=dao.selectLectureCount(conn);
      close(conn);
      return result;
   }
   
   // lectureList 페이징
   public List<Lecture> selectLectureList(int cPage, int numPerPage) {
      Connection conn=getConnection();
      List<Lecture> list=dao.selectLectureList(conn, cPage, numPerPage);
      close(conn);
      
      return list;
   }

   //강의 선택
   public Lecture selectLecture(String lectureNo) {
      Connection conn = getConnection();
      Lecture lec = dao.lectureView(conn, Integer.parseInt(lectureNo));

      close(conn);
      return lec;
   }

}
