package com.semi.lecture.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.semi.lecture.model.vo.LectureUpload;
import com.semi.mento.model.dao.MentoDao;

public class LectureUploadDao {
	
	private Properties prop = new Properties();
	public LectureUploadDao() {
		String path = MentoDao.class.getResource("/sql/semi/lecture-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}


	//강의번호에 맞는 이미지 넣기(단일)
	public LectureUpload lectureUpCover(Connection conn, int lecNum) {
		Statement stmt = null;
		LectureUpload lecUp = null;
		ResultSet rs = null;
		String sql = "select up_lectureNum, lecnum, up_lecture_org_cover, up_lecture_re_cover from tb_upload_lecture where lecNum="+lecNum;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				lecUp = new LectureUpload();
				lecUp.setUpLectureNum(rs.getInt("up_lecturenum"));
				lecUp.setLecNum(rs.getInt("lecnum"));
				lecUp.setUpLectureOrgCover(rs.getString("up_lecture_org_cover"));
				lecUp.setUpLectureReCover(rs.getString("up_lecture_re_cover"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		} return lecUp;
		
	}
	
	// 강의번호에 맞는 이미지 넣기(다중)
	public List<LectureUpload> lectureUpImgList(Connection conn, int lecNum) {
		Statement stmt = null;
		List<LectureUpload> list = new ArrayList();
		ResultSet rs = null;
		String sql = "select up_lectureNum, lecnum, up_lecture_org_lecimg, up_lecture_re_lecimg from tb_upload_lecture where lecNum="+lecNum;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				LectureUpload lul = new LectureUpload();
				lul.setUpLectureNum(rs.getInt("up_lecturenum"));
				lul.setLecNum(rs.getInt("lecnum"));
				lul.setUpLectureOrgLecImg(rs.getString("up_lecture_org_lecimg"));
				lul.setUpLectureReLecImg(rs.getString("up_lecture_re_lecimg"));
				list.add(lul);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		} return list;
	}
	
	// 다받아오기
	public List<LectureUpload> lectureUpList(Connection conn, int lecNum) {
		Statement stmt = null;
		List<LectureUpload> list = new ArrayList();
		ResultSet rs = null;
		String sql = "select * from tb_upload_lecture where lecNum="+lecNum;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				LectureUpload lul = new LectureUpload();
				lul.setUpLectureNum(rs.getInt("up_lecturenum"));
				lul.setLecNum(rs.getInt("lecnum"));
				lul.setUpLectureOrgCover(rs.getString("up_lecture_org_cover"));
				lul.setUpLectureReCover(rs.getString("up_lecture_re_cover"));
				lul.setUpLectureOrgLecImg(rs.getString("up_lecture_org_lecimg"));
				lul.setUpLectureReLecImg(rs.getString("up_lecture_re_lecimg"));
				list.add(lul);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		} return list;
	}
}
