package com.swjtu.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sun.istack.internal.logging.Logger;
import com.swjtu.error.UtilsException;
import com.swjtu.utils.SWJTU;

public class PostDao extends BaseDao {
	private Logger logger = Logger.getLogger(PostDao.class);
	
	public int insertPost(String title, String content) {
		String sql = "INSERT INTO post_tbl (rcrd_id, title, content) VALUES (?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, SWJTU.Utils.SequenceUtil.getNextKey("post_tbl"));
			ps.setString(2, title);
			ps.setString(3, content);
			ps.addBatch();
			int[] bats = ps.executeBatch();
			ps.close();
			return bats[0];
		} catch (UtilsException e) {
			logger.severe("使用sequence生成主键异常！！");
			e.printStackTrace();
		} catch (SQLException e) {
			logger.severe("关闭数据库连接失败！！");
			e.printStackTrace();
		} 
		return 0;
	}
}