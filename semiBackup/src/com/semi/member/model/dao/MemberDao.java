package com.semi.member.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.semi.member.model.vo.Member;

public class MemberDao {

	private Properties prop = new Properties();

	public MemberDao() {
		String path = MemberDao.class.getResource("/sql/semi/member-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	//회원가입
	public int register(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertMember");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getmId());
			pstmt.setString(2, m.getmPassword());
			pstmt.setString(3, m.getmName());
			pstmt.setString(4, ""+m.getmGender());
			pstmt.setDate(5, m.getmBirth());
			pstmt.setString(6, m.getmEmail());
			pstmt.setString(7, m.getmPhone());

			result = pstmt.executeUpdate();


		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//로그인
	public Member selectId(Connection conn, String mId, String mPw) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectId");
		Member m = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, mPw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m = new Member();
				m.setmNum(rs.getInt("mNum"));
				m.setmId(rs.getString("mId"));
				m.setmPassword(rs.getString("mPassword"));
				m.setmName(rs.getString("mName"));
				m.setmGender(rs.getString("mGender").charAt(0));
				m.setmBirth(rs.getDate("mBirth"));
				m.setmEmail(rs.getString("mEmail"));
				m.setmPhone(rs.getString("mPhone"));
				m.setmUse(rs.getString("mUse").charAt(0));
				m.setmHireDate(rs.getDate("mHire_Date"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}

	//아이디 중복체크
	public int idDupliCheck(Connection conn, String userId) {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from tb_member where mid='"+userId+"'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				return 0;	//중복된 아이디
			} else {
				return 1;	//가입가능한 아이디
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return -1;	//db오류

	}

	//이메일 중복체크
	public int emailDupliCheck(Connection conn, String email) {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from tb_member where memail='"+email+"'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				return 0;	//중복된 이메일
			} else {
				return 1;	//가입가능한 이메일 
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return -1;	//db오류
	}

	//아이디로 조회
	public Member selectMember(Connection conn, String mId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		String sql=prop.getProperty("selectMember");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m = new Member();
				m.setmNum(rs.getInt("mNum"));
				m.setmId(rs.getString("mId"));
				m.setmPassword(rs.getString("mPassword"));
				m.setmName(rs.getString("mName"));
				m.setmGender(rs.getString("mGender").charAt(0));
				m.setmBirth(rs.getDate("mBirth"));
				m.setmEmail(rs.getString("mEmail"));
				m.setmPhone(rs.getString("mPhone"));
				m.setmUse(rs.getString("mUse").charAt(0));
				m.setmHireDate(rs.getDate("mHire_Date"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}
	
	public Member selectMemberMnum(Connection conn, int mNum) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		String sql=prop.getProperty("selectMemberMnum");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, mNum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m = new Member();
				m.setmNum(rs.getInt("mNum"));
				m.setmId(rs.getString("mId"));
				m.setmPassword(rs.getString("mPassword"));
				m.setmName(rs.getString("mName"));
				m.setmGender(rs.getString("mGender").charAt(0));
				m.setmBirth(rs.getDate("mBirth"));
				m.setmEmail(rs.getString("mEmail"));
				m.setmPhone(rs.getString("mPhone"));
				m.setmUse(rs.getString("mUse").charAt(0));
				m.setmHireDate(rs.getDate("mHire_Date"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}

	//마이페이지
	/*
	 * public Member selectMember(Connection conn, String mId) { PreparedStatement
	 * pstmt=null; ResultSet rs=null; Member m=null; String
	 * sql=prop.getProperty("selectMember"); try { pstmt=conn.prepareStatement(sql);
	 * pstmt.setString(1, mId); rs=pstmt.executeQuery(); if(rs.next()) { m = new
	 * Member(); m.setmNum(rs.getInt("mNum")); m.setmId(rs.getString("mId"));
	 * m.setmPassword(rs.getString("mPassword")); m.setmName(rs.getString("mName"));
	 * m.setmGender(rs.getString("mGender").charAt(0));
	 * m.setmBirth(rs.getDate("mBirth")); m.setmEmail(rs.getString("mEmail"));
	 * m.setmPhone(rs.getString("mPhone"));
	 * m.setmUse(rs.getString("mUse").charAt(0));
	 * m.setmHireDate(rs.getDate("mHire_Date")); } }catch(SQLException e) {
	 * e.printStackTrace(); } finally { close(rs); close(pstmt); } return m; }
	 */

	//회원탈퇴
	public int deleteMember(Connection conn, String mId) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteMember");
		System.out.println(sql);
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}

	//회원정보수정
	public int updateMember(Connection conn, String mPw, String mEmail, String mPhone, String mId) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateMember");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, mPw);
			pstmt.setString(2, mEmail);
			pstmt.setString(3, mPhone);
			pstmt.setString(4, mId);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}

   //admin 페이징
   public int selectCountMember(Connection conn) {
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      int result=0;
      String sql=prop.getProperty("selectCountMember");
      try {
         pstmt=conn.prepareStatement(sql);
         rs=pstmt.executeQuery();
         if(rs.next()) {
            result=rs.getInt("cnt");
         }
      }catch(SQLException e) {
         e.printStackTrace();
      }
      finally {
         close(rs);
         close(pstmt);
      }
      return result;
   }
   public List<Member> selectListPage(Connection conn, int cPage, int numPerPage){
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      String sql=prop.getProperty("selectListPage");
      List<Member> list=new ArrayList();
      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1,(cPage-1)*numPerPage+1);
         pstmt.setInt(2,cPage*numPerPage);
         rs=pstmt.executeQuery();
         while(rs.next()) {
            Member m=new Member();
            m.setmNum(rs.getInt("mNum"));
            m.setmId(rs.getString("mId"));
            m.setmPassword(rs.getString("mPassword"));
            m.setmName(rs.getString("mName"));
            m.setmGender(rs.getString("mGender").charAt(0));
            m.setmBirth(rs.getDate("mBirth"));
            m.setmEmail(rs.getString("mEmail"));
         m.setmPhone(rs.getString("mPhone"));
            m.setmUse(rs.getString("mUse").charAt(0));
            m.setmHireDate(rs.getDate("mHire_Date"));
            list.add(m);
         }
      }catch(SQLException e) {
         e.printStackTrace();
      }finally {
         close(rs);
         close(pstmt);
      }return list;
      
   }
   

	//비밀번호 찾기
	public Member findMemberPwd(Connection conn, String mId, String email) {
		PreparedStatement pstmt=null;
	    ResultSet rs=null;
	    Member m = null;
	    String sql = prop.getProperty("findPwd");
	    try 
	    {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, email);
			rs=pstmt.executeQuery();
			while(rs.next()) {
	            m = new Member();
	            m.setmNum(rs.getInt("mNum"));
	            m.setmId(rs.getString("mId"));
	            m.setmPassword(rs.getString("mPassword"));
	            m.setmName(rs.getString("mName"));
	            m.setmGender(rs.getString("mGender").charAt(0));
	            m.setmBirth(rs.getDate("mBirth"));
	            m.setmEmail(rs.getString("mEmail"));
	            m.setmPhone(rs.getString("mPhone"));
	            m.setmUse(rs.getString("mUse").charAt(0));
	            m.setmHireDate(rs.getDate("mHire_Date"));
	         }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return m;

	}

	//비밀번호 변경
	public int updatePwd(Connection conn, String mId, String pwd) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updatePassword");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, pwd);
			pstmt.setString(2, mId);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}

	//아이디 찾기
	public Member findMemberId(Connection conn, String name, String date, String phone) {
		PreparedStatement pstmt=null;
	    ResultSet rs=null;
	    Member m = null;
	    String sql = prop.getProperty("findId");
	    try 
	    {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, date);
			pstmt.setString(3, phone);
			rs=pstmt.executeQuery();
			while(rs.next()) {
	            m = new Member();
	            m.setmNum(rs.getInt("mNum"));
	            m.setmId(rs.getString("mId"));
	            m.setmPassword(rs.getString("mPassword"));
	            m.setmName(rs.getString("mName"));
	            m.setmGender(rs.getString("mGender").charAt(0));
	            m.setmBirth(rs.getDate("mBirth"));
	            m.setmEmail(rs.getString("mEmail"));
	            m.setmPhone(rs.getString("mPhone"));
	            m.setmUse(rs.getString("mUse").charAt(0));
	            m.setmHireDate(rs.getDate("mHire_Date"));
	         }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return m;
	}
}


















