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
	private static Connection conn = DBUtil.getConnSingleton();
	private static Logger logger = Logger.getLogger(SequenceUtil.class);
	
	public static String getNextKey(String tblName) throws UtilsException {
		StringBuilder key = new StringBuilder();
		
		String nickName = getTblNickName(tblName);
		if (null == nickName) {
			throw new UtilsException("获取数据库表别名失败！！");
		}
		key.append(nickName).append("_").append(getCurDate());
		int curV = getCurValue(nickName);
		if (0 == curV) {
			throw new UtilsException("获取sequence表的当前值失败！！");
		}
		key.append(curV);
		
		return key.toString() ;
	}
	/**
	 * 获取当前日期 yyyyMMdd
	 * @return
	 */
	private static String getCurDate() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}
	/**
	 * 获取 数据库表 tblName 的 别名
	 * @param tblName
	 * @return
	 */
	private static String getTblNickName(String tblName) {
		try {
			String sql = "select tbl_nickname from alias_tbl where tbl_fullname = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, tblName);
			ps.addBatch();
			ResultSet rs = ps.executeQuery();
			if (false == rs.next()) {
				return null;
			}
			String nickName = rs.getString(1);
			rs.close();
			ps.close();
			return nickName;
		} catch (SQLException e) {
			logger.severe("关闭数据库连接失败！！");
			e.printStackTrace();
		} 
		return null;
	}
	private static int getCurValue(String nickName) throws UtilsException{
		int result = 0;
		try {
			String selSql = "select min_value, max_value, cur_value from sequence_tbl where tbl_name = ? for update"; // 添加行级锁
			String updSql = "update sequence_tbl set min_value = ?"
					+ ", max_value = ?"
					+ ", cur_value = ? where tbl_name = ? and cur_value = ?"; 
			PreparedStatement ps = conn.prepareStatement(selSql);
			ps.setString(1, nickName);
			ps.addBatch(); 
			ResultSet rs = ps.executeQuery();
			if (false == rs.next()) {
				return 0;
			}
			int minV = rs.getInt(1);
			int maxV = rs.getInt(2);
			int curV = rs.getInt(3);
			result = curV;
			
			curV += 1;
			if (curV > maxV) {
				curV = minV;
			}
			PreparedStatement ps2 = conn.prepareStatement(updSql);
			ps2.setInt(1, minV);
			ps2.setInt(2, maxV);
			ps2.setInt(3, curV);
			ps2.setString(4, nickName);
			ps2.setInt(5, result);
			int upds = ps2.executeUpdate();
			if (upds == 0) {
				throw new UtilsException("更新sequence表记录失败！！");
			}
			rs.close();
			ps2.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return 0;
	}
}
