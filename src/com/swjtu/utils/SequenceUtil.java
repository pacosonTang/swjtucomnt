package com.swjtu.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.istack.internal.logging.Logger;
import com.swjtu.error.UtilsException;

public class SequenceUtil {
	private Connection conn = DBUtil.getConnSingleton();
	private Logger logger = Logger.getLogger(SequenceUtil.class);
	
	public synchronized String getNextKey(String tblName) throws UtilsException {
		StringBuilder key = new StringBuilder();
		
		String nickName = this.getTblNickName(tblName);
		if (null == nickName) {
			throw new UtilsException("获取数据库表别名失败！！");
		}
		key.append(nickName).append("_").append(this.getCurDate());
		String curSequenceValue = ""; 
		key.append(curSequenceValue);
		
		return key.toString() ;
	}
	/**
	 * 获取当前日期 yyyyMMdd
	 * @return
	 */
	private String getCurDate() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}
	/**
	 * 获取 数据库表 tblName 的 别名
	 * @param tblName
	 * @return
	 */
	private String getTblNickName(String tblName) {
		try {
			String sql = "select tbl_nickname from alias_tbl where tbl_fullname = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, tblName);
			ps.addBatch();
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.severe("关闭数据库连接失败！！");
				e.printStackTrace();
			}
		}
		return null;
	}
	private String getCurValue(String nickName) {
		try {
			String sql = "select cur_value from sequence_tbl where tbl_name = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nickName);
			ps.addBatch();
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.severe("关闭数据库连接失败！！");
				e.printStackTrace();
			}
		}
		return null;
	}
	private int getAndUpdCurValue(String nickName) {
		try {
			String sql = "select cur_value from sequence_tbl where tbl_name = ? for update";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nickName);
			ps.addBatch();
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
			} else {
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.severe("关闭数据库连接失败！！");
				e.printStackTrace();
			}
		}
		return -1;
	}
}
