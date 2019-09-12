package com.semi.mento.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.semi.mento.model.vo.MentoUpload;

public class MentoUploadDao {

	private Properties prop = new Properties();

	public MentoUploadDao() {
		String path = MentoDao.class.getResource("/sql/semi/mento-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	//멘토 이미지 등록
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

	public List<MentoUpload> mentoUpList(Connection conn, int mtNum) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("mentoUpList");
		List<MentoUpload> list = new ArrayList();
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mtNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MentoUpload mtu = new MentoUpload();
				mtu.setUpMentoNum(rs.getInt("up_mentonum"));
				mtu.setMtNum(rs.getInt("mtnum"));
				mtu.setUpMentoOrgProfile(rs.getString("up_mento_org_profile"));
				mtu.setUpMentoReProfile(rs.getString("up_mento_re_profile"));
				mtu.setUpMentoOrgConfirm(rs.getString("up_mento_org_confirm"));
				mtu.setUpMentoReConfirm(rs.getString("up_mento_re_confirm"));
				mtu.setUpMentoNameLicense(rs.getString("up_mento_name_license"));
				mtu.setUpMentoOrgLicense(rs.getString("up_mento_org_license"));
				mtu.setUpMentoReLicense(rs.getString("up_mento_re_license"));
				list.add(mtu);
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		} return list;
	}

	//한개만 들어오는 데이터 
	public List<MentoUpload> mentoUpProList(Connection conn, int mtNum) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("mentoUpProList");
		List<MentoUpload> list = new ArrayList();
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mtNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getString("up_mento_re_profile")!=null) {
					MentoUpload mtu = new MentoUpload();
					mtu.setUpMentoNum(rs.getInt("up_mentonum"));
					mtu.setMtNum(rs.getInt("mtnum"));
					mtu.setUpMentoOrgProfile(rs.getString("up_mento_org_profile"));
					mtu.setUpMentoReProfile(rs.getString("up_mento_re_profile"));
					mtu.setUpMentoOrgConfirm(rs.getString("up_mento_org_confirm"));
					mtu.setUpMentoReConfirm(rs.getString("up_mento_re_confirm"));
					list.add(mtu);
				}
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		} return list;
	}

	//여러개 들어오는 자격증
	public List<MentoUpload> mentoUpLicenList(Connection conn, int mtNum) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("mentoUpLicenList");
		List<MentoUpload> list = new ArrayList();
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mtNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MentoUpload mtu = new MentoUpload();
				mtu.setUpMentoNum(rs.getInt("up_mentonum"));
				mtu.setMtNum(rs.getInt("mtnum"));
				mtu.setUpMentoNameLicense(rs.getString("up_mento_name_license"));
				mtu.setUpMentoOrgLicense(rs.getString("up_mento_org_license"));
				mtu.setUpMentoReLicense(rs.getString("up_mento_re_license"));
				list.add(mtu);

			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		} return list;
	}

}
