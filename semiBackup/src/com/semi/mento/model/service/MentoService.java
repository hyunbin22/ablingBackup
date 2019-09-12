package com.semi.mento.model.service;

import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.semi.member.model.vo.Member;
import com.semi.mento.model.dao.MentoDao;
import com.semi.mento.model.vo.Mento;
import com.semi.mento.model.vo.MentoUpload;


public class MentoService {
	
	private MentoDao dao = new MentoDao();
	
	//멘토 신청
	public int registerMento(Mento mt, Member m) {
		Connection conn = getConnection();
		int result=dao.registerMento(conn, mt, m);
		if(result>0) {
			commit(conn);
			result=dao.selectSeqMento(conn, m.getmNum()); // m.getId

		}else {
			rollback(conn);
		}
		close(conn);
		return result;
		
	}
	
	//멘토 신청 이미지 등록
//	public int registerMentoImage(MentoUpload mtu, int mtnum) {
//		Connection conn=getConnection();
//		int result=dao.registerMentoImage(conn, mtu, mtnum);
//		if(result>0) {
//			commit(conn);
//		}else {
//			rollback(conn);
//		}
//		close(conn);
//		return result;
//	}
	
	//멘토신청목록
	public int countMentoApproval() {
		Connection conn = getConnection();
		int result = dao.countMentoApproval(conn);
		close(conn);
		return result;
	}

	
	public List<Mento> mentoApproList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Mento> list = dao.mentoApproList(conn, cPage, numPerPage);
		close(conn);
		return list;
		
	}
	//멘토신청목록 끝

	//멘토 상세보기
	public Mento mentoView(int mtNum) {
		Connection conn = getConnection();
		Mento mt = dao.mentoView(conn, mtNum);
		close(conn);
		return mt;
	}

	//멘토승인
	public int updateCheckMento(int mtNum) {
		Connection conn = getConnection();
		int result = dao.updateCheckMento(conn, mtNum);
		if(result > 0) {
			commit(conn);
		} else rollback(conn);
		close(conn);
		return result;
	}

	//멘토거절
	public int updateRefusalMento(int mtNum, String mtReason) {
		Connection conn = getConnection();
		int result = dao.updateRefusalMento(conn, mtNum, mtReason);
		if(result > 0) {
			commit(conn);
		} else rollback(conn);
		close(conn);
		
		return result;
	}
	
	//멘토 검색
	public int countMentoApproval(String type, String data) {
		Connection conn = getConnection();
		int result = dao.countMentoApproval(conn, type, data);
		close(conn);
		return result;
	}


	public List<Mento> mentoFindList(String type, String data, int cPage, int numPerPage, int temp) {
		Connection conn = getConnection();
		List<Mento> list = dao.mentoFindList(conn, type, data, cPage, numPerPage, temp);
		close(conn);
		return list;
	}

	//멘토검색 끝


	//승인완료된 멘토 리스트
	public List<Mento> mentoList(int mtNum) {
		Connection conn = getConnection();
		List<Mento> list = dao.mentoList(conn);
		close(conn);
		return list;
	}
	
	//승인완료된 멘토 보기
	public int countMento() {
		Connection conn = getConnection();
		int result = dao.countMento(conn);
		close(conn);
		return result;
	}


	public List<Mento> mentoList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Mento> list = dao.mentoList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}




}
