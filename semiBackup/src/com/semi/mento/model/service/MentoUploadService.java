package com.semi.mento.model.service;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.semi.mento.model.dao.MentoUploadDao;
import com.semi.mento.model.vo.MentoUpload;

public class MentoUploadService {
	
	private MentoUploadDao dao = new MentoUploadDao();

}
