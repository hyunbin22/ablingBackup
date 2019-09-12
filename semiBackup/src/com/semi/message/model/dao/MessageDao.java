package com.semi.message.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import static common.template.JDBCTemplate.close;
import com.semi.category.model.dao.CategoryDao;

public class MessageDao {
	
	private Properties prop=new Properties();
	
	public MessageDao() {
		String path=CategoryDao.class.getResource("/sql/semi/message-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//헤더에서 안읽은 메세지수 보여주기
	public int allReadCount(Connection conn, int mNum) {
		Statement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "select count(*) from tb_message where to_mnum="+mNum;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		
		return result;
	}

}
