package com.semi.lecture.model.dao;

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

import com.semi.lecture.model.vo.Lecture;
import com.semi.lecture.model.vo.LectureUpload;
import com.semi.mento.model.dao.MentoDao;
import com.semi.mento.model.vo.Mento;

public class LectureDao {
	private Properties prop = new Properties();
	public LectureDao() {
		String path = MentoDao.class.getResource("/sql/semi/lecture-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}


	public int countLectureApproval(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("countLectureApproval");
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

	public List<Lecture> lectureApproList(Connection conn, int cPage, int numPerPage) {

		PreparedStatement pstmt = null;
		String sql = prop.getProperty("lectureApproList");
		ResultSet rs = null;
		List<Lecture> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Lecture lec = new Lecture();
				lec.setLecNum(rs.getInt("lecnum"));
				lec.setMtNum(rs.getInt("mtnum"));
				lec.setSubNum(rs.getInt("subnum"));
				lec.setLocalSubNum(rs.getInt("sublocalnum"));
				lec.setLecName(rs.getString("lecname"));
				lec.setLecType(rs.getString("lectype"));
				lec.setLecMaxCount(rs.getInt("lecmaxcount"));
				lec.setLecPrice(rs.getInt("lecprice"));
				lec.setLecTime(rs.getDouble("lectime"));
				lec.setLecCount(rs.getInt("leccount"));
				lec.setLecWeek(rs.getString("lecweek"));
				lec.setLecMeet(rs.getString("lecmeet"));
				lec.setLecTot(rs.getString("lecTot"));
				lec.setLecTot2(rs.getString("lecTot2"));
				lec.setLecOpenDate(rs.getDate("lecOpenDate"));
				lec.setLecOpenDate2(rs.getDate("lecOpenDate2"));
				lec.setLecLocalContent(rs.getString("lecLocalContent"));
				lec.setLecMentoContent(rs.getString("lecMentoContent"));
				lec.setLecLectureContent(rs.getString("lecLectureContent"));
				lec.setLecStudentCount(rs.getInt("lecStudentCount"));
				lec.setLecADate(rs.getDate("lecaDate"));
				lec.setLecCheck(rs.getString("lecCheck").charAt(0));
				lec.setLecReason(rs.getString("lecReason"));
				lec.setLecStatus(rs.getString("lecstatus").charAt(0));

				List<LectureUpload> lecUp = new LectureUploadDao().lectureUpCover(conn,rs.getInt("lecnum"));
				Mento m = new MentoDao().mentoView(conn, rs.getInt("mtnum"));
				lec.setLectureUpList(lecUp);
				lec.setLecMento(m);
				list.add(lec);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	//관리자 강의신청목록 검색
	public List<Lecture> lectureApproList(Connection conn, String type, String data, int cPage, int numPerPage) {

		List<LectureUpload> setUpList = new ArrayList();

		Statement stmt = null;
		ResultSet rs = null;
		List<Lecture> list = new ArrayList();
		int start = (cPage-1)*numPerPage+1;
		int end = cPage*numPerPage;
		String sql = "select * from ("
				+ "select rownum as rnum, a.* from("
				+ "select lec.* "
				+ "from tb_lecture lec join tb_mento mt on (lec.mtnum=mt.mtnum) "
				+ "join tb_member m on (mt.mnum=m.mnum) where "
				+ "lec.leccheck='N' and m." + type+ " like '%" + data + "%' "
				+ "order by lec.lecadate)a) where rnum between "+ start + " and " + end;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Lecture lec = new Lecture();
				lec.setLecNum(rs.getInt("lecnum"));
				lec.setMtNum(rs.getInt("mtnum"));
				lec.setSubNum(rs.getInt("subnum"));
				lec.setLocalSubNum(rs.getInt("sublocalnum"));
				lec.setLecName(rs.getString("lecname"));
				lec.setLecType(rs.getString("lectype"));
				lec.setLecMaxCount(rs.getInt("lecmaxcount"));
				lec.setLecPrice(rs.getInt("lecprice"));
				lec.setLecTime(rs.getDouble("lectime"));
				lec.setLecCount(rs.getInt("leccount"));
				lec.setLecWeek(rs.getString("lecweek"));
				lec.setLecMeet(rs.getString("lecmeet"));
				lec.setLecTot(rs.getString("lecTot"));
				lec.setLecTot2(rs.getString("lecTot2"));
				lec.setLecOpenDate(rs.getDate("lecOpenDate"));
				lec.setLecOpenDate2(rs.getDate("lecOpenDate2"));
				lec.setLecLocalContent(rs.getString("lecLocalContent"));
				lec.setLecMentoContent(rs.getString("lecMentoContent"));
				lec.setLecLectureContent(rs.getString("lecLectureContent"));
				lec.setLecStudentCount(rs.getInt("lecStudentCount"));
				lec.setLecADate(rs.getDate("lecaDate"));
				lec.setLecCheck(rs.getString("lecCheck").charAt(0));
				lec.setLecReason(rs.getString("lecReason"));
				lec.setLecStatus(rs.getString("lecstatus").charAt(0));
				List<LectureUpload> lecUp = new LectureUploadDao().lectureUpCover(conn,rs.getInt("lecnum"));
				Mento m = new MentoDao().mentoView(conn, rs.getInt("mtnum"));
				lec.setLectureUpList(lecUp);
				lec.setLecMento(m);
				list.add(lec);
			}

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return list;
	}

	//강의 상세보기
	public Lecture lectureView(Connection conn, int lecNum) {
		
		List<LectureUpload> setUpList = new ArrayList();
		List<Mento> setmUpList = new ArrayList();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("lectureView");
		Lecture lec = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lecNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				lec = new Lecture();
				lec.setLecNum(rs.getInt("lecnum"));
				lec.setMtNum(rs.getInt("mtnum"));
				lec.setSubNum(rs.getInt("subnum"));
				lec.setLocalSubNum(rs.getInt("sublocalnum"));
				lec.setLecName(rs.getString("lecname"));
				lec.setLecType(rs.getString("lectype"));
				lec.setLecMaxCount(rs.getInt("lecmaxcount"));
				lec.setLecPrice(rs.getInt("lecprice"));
				lec.setLecTime(rs.getDouble("lectime"));
				lec.setLecCount(rs.getInt("leccount"));
				lec.setLecWeek(rs.getString("lecweek"));
				lec.setLecMeet(rs.getString("lecmeet"));
				lec.setLecTot(rs.getString("lecTot"));
				lec.setLecTot2(rs.getString("lecTot2"));
				lec.setLecOpenDate(rs.getDate("lecOpenDate"));
				lec.setLecOpenDate2(rs.getDate("lecOpenDate2"));
				lec.setLecLocalContent(rs.getString("lecLocalContent"));
				lec.setLecMentoContent(rs.getString("lecMentoContent"));
				lec.setLecLectureContent(rs.getString("lecLectureContent"));
				lec.setLecStudentCount(rs.getInt("lecStudentCount"));
				lec.setLecADate(rs.getDate("lecaDate"));
				lec.setLecCheck(rs.getString("lecCheck").charAt(0));
				lec.setLecReason(rs.getString("lecReason"));
				lec.setLecStatus(rs.getString("lecstatus").charAt(0));

				List<LectureUpload> upList = new LectureUploadDao().lectureUpList(conn, rs.getInt("lecnum"));
				Mento m = new MentoDao().mentoView(conn, rs.getInt("mtnum"));
				
				for(int i = 0; i < upList.size(); i++) {
					setUpList.add(upList.get(i));
				}
				lec.setLectureUpList(setUpList);
				lec.setLecMento(m);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return lec;
		
	}

	//강의 거절사유 등록
	public int updateRefusalLecture(Connection conn, int lecNum, String reason) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateRefusalLecture");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reason);
			pstmt.setInt(2, lecNum);
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//강의 승인
	public int updateCheckLecture(Connection conn, int lecNum) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateCheckLecture");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lecNum);
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}

