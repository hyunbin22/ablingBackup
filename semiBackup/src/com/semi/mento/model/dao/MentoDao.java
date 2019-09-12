package com.semi.mento.model.dao;

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

import com.semi.member.model.dao.MemberDao;
import com.semi.member.model.vo.Member;
import com.semi.mento.model.vo.Mento;
import com.semi.mento.model.vo.MentoUpload;


public class MentoDao {

	private Properties prop = new Properties();

	public MentoDao() {
		String path = MentoDao.class.getResource("/sql/semi/mento-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	//멘토신청
	public int registerMento(Connection conn, Mento mt, Member m) {
		PreparedStatement pstmt = null;
		int result=0;
		String sql=prop.getProperty("registerMento");
		try {
			pstmt=conn.prepareStatement(sql);
			System.out.println("m.getmNum() : "+m.getmNum());
			pstmt.setInt(1, m.getmNum());
			pstmt.setString(2, mt.getMtNickName());
			pstmt.setString(3, mt.getMtHowConfirm());
			pstmt.setString(4, mt.getMtAcademic());
			pstmt.setString(5, mt.getMtAcademicDept());
			pstmt.setString(6, mt.getMtGraduation());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	//멘토번호 가져오기
	public int selectSeqMento(Connection conn, int mNum) {
		Statement stmt = null;
		ResultSet rs = null;
		int result=0;
		String sql="select mtnum from tb_mento join tb_member using(mNum) where mnum="+mNum; // id를 받아서 멘토테이블 조회한 다음에 번호 조회? select mtnum from tb_mento where mid=?
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				result=rs.getInt(1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return result;
	}
	
	//멘토 신청 이미지 등록
	public int registerMentoImage(Connection conn, MentoUpload mtu, int mtnum) {
		PreparedStatement pstmt=null;
		System.out.println("mtnum : "+mtnum);
		int result=0;
		String sql=prop.getProperty("registerMentoImage");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, mtnum);
			pstmt.setString(2, mtu.getUpMentoOrgProfile());
			pstmt.setString(3, mtu.getUpMentoReProfile());
			pstmt.setString(4, mtu.getUpMentoOrgConfirm());
			pstmt.setString(5, mtu.getUpMentoReConfirm());
			pstmt.setString(6, mtu.getUpMentoNameLicense());
			pstmt.setString(7, mtu.getUpMentoOrgLicense());
			pstmt.setString(8, mtu.getUpMentoReLicense());
			result=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	//멘토 신청 목록
	public int countMentoApproval(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("countMentoApproval");
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("cnt");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	//멘토 승인요청
	public List<Mento> mentoApproList(Connection conn, int cPage, int numPerPage) {
		Member m = null;
		List<MentoUpload> setUpList = new ArrayList();

		PreparedStatement pstmt = null;
		String sql = prop.getProperty("mentoApproList");
		ResultSet rs = null;
		List<Mento> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Mento mt = new Mento();
				mt.setMtNum(rs.getInt("mtNum"));
				mt.setmNum(rs.getInt("mNum"));
				mt.setMtHireDate(rs.getDate("mtHireDate"));
				mt.setMtNickName(rs.getString("mtNickName"));
				mt.setMtHowConfirm(rs.getString("mtHowConfirm"));
				mt.setMtAcademic(rs.getString("mtAcademic"));
				mt.setMtAcademicDept(rs.getString("mtAcademicDept"));
				mt.setMtGraduation(rs.getString("mtGraduation"));
				mt.setMtaDate(rs.getDate("mtaDate"));
				mt.setMtCheck(rs.getString("mtcheck").charAt(0));
				mt.setMtReason(rs.getString("mtReason"));
				m = new MemberDao().selectMember(conn, rs.getString("mId"));
				
				List<MentoUpload> upList = new MentoUploadDao().mentoUpProList(conn,rs.getInt("mtNum"));
				for(int i = 0; i < upList.size(); i++) {
					setUpList.add(upList.get(i));
				}
				
				mt.setList(setUpList);
				list.add(mt);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;

	}

	//멘토 상세보기
	public Mento mentoView(Connection conn, int mtNum) {
		Member m = null;
		List<MentoUpload> setUpList = new ArrayList();
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql = prop.getProperty("mentoView");
		Mento mt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mtNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mt = new Mento();
				mt.setMtNum(rs.getInt("mtNum"));
				mt.setmNum(rs.getInt("mNum"));
				mt.setMtHireDate(rs.getDate("mtHireDate"));
				mt.setMtNickName(rs.getString("mtNickName"));
				mt.setMtHowConfirm(rs.getString("mtHowConfirm"));
				mt.setMtAcademic(rs.getString("mtAcademic"));
				mt.setMtAcademicDept(rs.getString("mtAcademicDept"));
				mt.setMtGraduation(rs.getString("mtGraduation"));
				mt.setMtaDate(rs.getDate("mtaDate"));
				mt.setMtCheck(rs.getString("mtcheck").charAt(0));
				mt.setMtReason(rs.getString("mtReason"));
				m = new MemberDao().selectMember(conn, rs.getString("mId"));
				List<MentoUpload> upList = new MentoUploadDao().mentoUpList(conn,rs.getInt("mtNum"));
				for(int i = 0; i < upList.size(); i++) {
					setUpList.add(upList.get(i));

				}
				mt.setList(setUpList);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return mt;

	}

	//승인 업데이트
	public int updateCheckMento(Connection conn, int mtNum) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateCheckMento");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mtNum);
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	//멘토 거절사유 등록
	public int updateRefusalMento(Connection conn, int mtNum, String mtReason) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateRefusalMento");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mtReason);
			pstmt.setInt(2, mtNum);
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//멘토신청 검색
	public int countMentoApproval(Connection conn, String type, String data) {
		Statement stmt = null;
		String sql = "select count(*) from tb_mento join tb_member using(mNum) "
				+ "where " + type + " like '%" + data + "%'";
		ResultSet rs = null;
		int result = 0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return result;
	}

	public List<Mento> mentoFindList(Connection conn, String type, String data, int cPage, int numPerPage, int temp) {
		Member m = null;
		List<MentoUpload> setUpList = new ArrayList();

		Statement stmt = null;
		ResultSet rs = null;
		List<Mento> list = new ArrayList();
		int start = (cPage-1)*numPerPage+1;
		int end = cPage*numPerPage;
		char check = temp==0?'Y':'N';	//멘토관리인지 승인관리인지, 0번은 멘토관리
		String sql = "select * from ("
				+ "select rownum as rnum, a.* from("
				+ "select mt.*, m.mid, m.mname, m.mphone from tb_mento mt join "
				+ "tb_member m on mt.mnum = m.mnum where "
				+ "mtcheck='" + check + "' and "+type+" like '%"+data+"%' "
				+ "order by mtaDate)a) where rnum between "+ start + " and " + end;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Mento mt = new Mento();
				mt.setMtNum(rs.getInt("mtNum"));
				mt.setmNum(rs.getInt("mNum"));
				mt.setMtHireDate(rs.getDate("mtHireDate"));
				mt.setMtNickName(rs.getString("mtNickName"));
				mt.setMtHowConfirm(rs.getString("mtHowConfirm"));
				mt.setMtAcademic(rs.getString("mtAcademic"));
				mt.setMtAcademicDept(rs.getString("mtAcademicDept"));
				mt.setMtGraduation(rs.getString("mtGraduation"));
				mt.setMtaDate(rs.getDate("mtaDate"));
				mt.setMtCheck(rs.getString("mtcheck").charAt(0));
				mt.setMtReason(rs.getString("mtReason"));
				m = new MemberDao().selectMember(conn, rs.getString("mId"));
				List<MentoUpload> upList = new MentoUploadDao().mentoUpProList(conn,rs.getInt("mtNum"));
				for(int i = 0; i < upList.size(); i++) {
					setUpList.add(upList.get(i));

				}
				mt.setList(setUpList);
				list.add(mt);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return list;
	}

	public List<Mento> mentoFindList(Connection conn, String type, String data) {
		Member m = null;
		List<MentoUpload> setUpList = new ArrayList();


		Statement stmt = null;
		ResultSet rs = null;
		List<Mento> list = new ArrayList();
		String sql = "select mt.*, m.mid, m.mname, m.mphone from tb_mento mt join "
				+ "tb_member m on mt.mnum = m.mnum where "
				+ type+" like '%"+data+"%'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Mento mt = new Mento();
				mt.setMtNum(rs.getInt("mtNum"));
				mt.setmNum(rs.getInt("mNum"));
				mt.setMtHireDate(rs.getDate("mtHireDate"));
				mt.setMtNickName(rs.getString("mtNickName"));
				mt.setMtHowConfirm(rs.getString("mtHowConfirm"));
				mt.setMtAcademic(rs.getString("mtAcademic"));
				mt.setMtAcademicDept(rs.getString("mtAcademicDept"));
				mt.setMtGraduation(rs.getString("mtGraduation"));
				mt.setMtaDate(rs.getDate("mtaDate"));
				mt.setMtCheck(rs.getString("mtcheck").charAt(0));
				mt.setMtReason(rs.getString("mtReason"));
				m = new MemberDao().selectMember(conn, rs.getString("mId"));
				List<MentoUpload> upList = new MentoUploadDao().mentoUpProList(conn,rs.getInt("mtNum"));
				for(int i = 0; i < upList.size(); i++) {
					setUpList.add(upList.get(i));

				}
				mt.setList(setUpList);
				list.add(mt);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return list;
	}



	//승인완료된 멘토 리스트(mid, mname, mphone 포함)
	public List<Mento> mentoList(Connection conn){
		Member m = null;
		List<MentoUpload> setUpList = new ArrayList();

		Statement stmt = null;
		ResultSet rs = null;
		List<Mento> list = new ArrayList();
		String sql = "select mt.*, m.mid, m.mname, m.mphone from tb_mento mt join tb_member m on (mt.mnum=m.mnum) where mt.mtcheck='Y'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Mento mt = new Mento();
				mt.setMtNum(rs.getInt("mtNum"));
				mt.setmNum(rs.getInt("mNum"));
				mt.setMtHireDate(rs.getDate("mtHireDate"));
				mt.setMtNickName(rs.getString("mtNickName"));
				mt.setMtHowConfirm(rs.getString("mtHowConfirm"));
				mt.setMtAcademic(rs.getString("mtAcademic"));
				mt.setMtAcademicDept(rs.getString("mtAcademicDept"));
				mt.setMtGraduation(rs.getString("mtGraduation"));
				mt.setMtaDate(rs.getDate("mtaDate"));
				mt.setMtCheck(rs.getString("mtcheck").charAt(0));
				mt.setMtReason(rs.getString("mtReason"));
				m = new MemberDao().selectMember(conn, rs.getString("mId"));
				List<MentoUpload> upList = new MentoUploadDao().mentoUpProList(conn, rs.getInt("mtNum"));

				for(int i = 0; i < upList.size(); i++) {

					setUpList.add(upList.get(i));

				}
				mt.setList(setUpList);

				list.add(mt);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return list;
	}
	
	//lecture에 넣을 mento
	

	//승인완료된 멘토 리스트 보기(페이징)
	public int countMento(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("countMento");
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public List<Mento> mentoList(Connection conn, int cPage, int numPerPage) {
		Member m = null;
		List<MentoUpload> setUpList = new ArrayList();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Mento> list = new ArrayList();
		String sql = prop.getProperty("mentoList");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cPage);
			pstmt.setInt(2, numPerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Mento mt = new Mento();
				mt.setMtNum(rs.getInt("mtNum"));
				mt.setmNum(rs.getInt("mNum"));
				mt.setMtHireDate(rs.getDate("mtHireDate"));
				mt.setMtNickName(rs.getString("mtNickName"));
				mt.setMtHowConfirm(rs.getString("mtHowConfirm"));
				mt.setMtAcademic(rs.getString("mtAcademic"));
				mt.setMtAcademicDept(rs.getString("mtAcademicDept"));
				mt.setMtGraduation(rs.getString("mtGraduation"));
				mt.setMtaDate(rs.getDate("mtaDate"));
				mt.setMtCheck(rs.getString("mtcheck").charAt(0));
				mt.setMtReason(rs.getString("mtReason"));
				m = new MemberDao().selectMember(conn, rs.getString("mId"));
				List<MentoUpload> upList = new MentoUploadDao().mentoUpProList(conn, rs.getInt("mtNum"));
				setUpList.add(upList.get(0));

				mt.setList(setUpList);

				list.add(mt);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}


}
