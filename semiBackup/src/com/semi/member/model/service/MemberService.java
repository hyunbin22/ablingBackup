package com.semi.member.model.service;

import java.sql.Connection;

import com.semi.member.model.dao.MemberDao;
import com.semi.member.model.vo.Member;

import common.template.JDBCTemplate;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.rollback;
import static common.template.JDBCTemplate.close;

public class MemberService {

	MemberDao dao = new MemberDao();

	//회원가입
	public int insertMember(Member m) {
		Connection conn = getConnection();
		int result = dao.register(conn, m);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);

		return result;
	}

	//아이디 중복체크
	public int idDupliCheck(String userId) {
		Connection conn = getConnection();
		int check = dao.idDupliCheck(conn, userId);

		return check;
	}

	//이메일 중복체크
	public int emailDupliCheck(String email) {
		Connection conn = getConnection();
		int check = dao.emailDupliCheck(conn, email);

		return check;
	}
	
	//회원번호로 조회
	public Member selectMember(int mNum) {
		Connection conn= JDBCTemplate.getConnection();
		Member m=dao.selectMemberMnum(conn,mNum);
		close(conn);
		return m;      
	}

	//아이디로 조회 , 마이페이지
	public Member selectMember(String mId) {
		Connection conn= JDBCTemplate.getConnection();
		Member m=dao.selectMember(conn,mId);
		close(conn);
		return m;      
	}

	//로그인
	public Member selectId(String mId, String mPw) {
		Connection conn= JDBCTemplate.getConnection();
		Member m=dao.selectId(conn,mId,mPw);
		close(conn);
		return m;
	}

	//회원탈퇴
	public int deleteMember(String mId) {
		Connection conn= JDBCTemplate.getConnection();
		int result=dao.deleteMember(conn,mId);
		if(result>0) 
		{
			commit(conn);
		}
		else 
		{
			rollback(conn);
			System.out.println("서비스");
		}
		close(conn);
		return result;

	}

	public int updateMember(String mPw , String mEmail, String mPhone, String mId) {
		Connection conn= JDBCTemplate.getConnection();
		int result=dao.updateMember(conn,mPw,mEmail,mPhone, mId);
		if(result>0) 
		{
			commit(conn);
		}
		else 
		{
			rollback(conn);
		}
		close(conn);
		return result;		
	}
}
