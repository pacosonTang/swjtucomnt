package com.swjtu.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * DbUtil类是一个单件类，在整个Web应用程序中只能创建DbUtil类的一个实例对象，
 * 在DbUtil类内部预存储了两个用户信息来模拟数据库中的用户记录。
 */
public class DBUtil {
	private static Connection conn;
	
	public static Connection getConnSingleton() {
		if (null == conn) {
			synchronized (DBUtil.class) {
				if (null == conn) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/swjtu?serverTimezone=UTC&" +
                                "user=root&password=root");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return conn;
	}
}