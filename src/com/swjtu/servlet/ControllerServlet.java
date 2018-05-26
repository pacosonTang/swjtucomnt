package com.swjtu.servlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.swjtu.bean.*;
import com.swjtu.utils.DbUtil2;
import com.swjtu.utils.DbUtilException;

public class ControllerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4090098405519833193L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;

		String name = new String(request.getParameter("name").getBytes(
				"ISO8859-1"), "UTF-8");
		String password1 = new String(request.getParameter("password1")
				.getBytes("ISO8859-1"), "UTF-8");
		String password2 = new String(request.getParameter("password2")
				.getBytes("ISO8859-1"), "UTF-8");
		String email = new String(request.getParameter("email").getBytes(
				"ISO8859-1"), "UTF-8");

		RegisterFormBean registerForm = new RegisterFormBean();
		registerForm.setName(name);
		registerForm.setPassword1(password1);
		registerForm.setPassword2(password2);
		registerForm.setEmail(email);
		request.setAttribute("registerForm", registerForm);

		// 如果表单字段中填写的内容格式有问题，则跳转到注册页面
		if (!registerForm.validate()) {
			rd = request.getRequestDispatcher("/chapter9/ex9_19/register.jsp");
			rd.forward(request, response);
			return;
		}

		UserBean user = new UserBean();
		user.setName(name);
		user.setPassword(password1);
		user.setEmail(email);
		DbUtil2 db = DbUtil2.getInstance();
		try {
			db.insertUser(user);
		} catch (DbUtilException e) {
			e.printStackTrace();
		}
		/*
		 * 用户成功注册的同时也自动完成登录，直接进入成功登录后的页面， 所以，应该将当前注册的用户信息要保存到Session域中
		 */
		HttpSession session = request.getSession();
		session.setAttribute("logonUser", user);
		rd = request.getRequestDispatcher("/chapter9/ex9_19/logonSuccess.jsp");
		rd.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}