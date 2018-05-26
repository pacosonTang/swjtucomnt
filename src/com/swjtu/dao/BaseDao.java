package com.swjtu.dao;

import java.sql.Connection;

import com.swjtu.utils.DBUtil;

public class BaseDao {
	protected Connection conn;
	
	public BaseDao() {
		conn = DBUtil.getConnSingleton();
	}
	
}
