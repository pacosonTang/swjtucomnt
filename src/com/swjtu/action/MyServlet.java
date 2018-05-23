package com.swjtu.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swjtu.utils.DBUtil;
import com.swjtu.utils.RandomGenerator;

public class MyServlet extends HttpServlet{
	private static final long serialVersionUID = 2723773556574678122L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Connection conn = DBUtil.getConnInstance();
		try {
			String sql = "INSERT INTO article_tbl (rcrd_id, title, content) VALUES (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, new RandomGenerator.String(47).next());
			ps.setString(2, title);
			ps.setString(3, content);
			ps.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
