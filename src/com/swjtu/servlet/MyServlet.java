package com.swjtu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swjtu.dao.PostDao;

public class MyServlet extends HttpServlet{
	private static final long serialVersionUID = 2723773556574678122L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String title = new String(request.getParameter("title").getBytes("iso8859-1"), "UTF-8");
		String content = new String(request.getParameter("content").getBytes("iso8859-1"), "UTF-8");
		
		PostDao dao = new PostDao();
		int ints = dao.insertPost(title, content);
		response.setContentType("text/html;charset=UTF-8"); 
		if (ints == 0) {
			response.getWriter().write("插入失败，请稍后重试！！");
		} else {
			response.getWriter().write("title: " + title);
			response.getWriter().write("<br/>content: " + content);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	} 
}
