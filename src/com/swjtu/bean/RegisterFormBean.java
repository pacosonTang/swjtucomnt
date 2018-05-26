package com.swjtu.bean;

import java.util.*;

/*RegisterFormBean用于封装注册表单的信息，其中定义了四个属性：name、password1、
 password2和email。RegisterFormBean还对这四个属性进行基本的格式验证，如果
 有误则将相应的错误信息保存到一个HashTable对象中，同时也定义了供JSP页面
 检索错误信息的方法。*/
public class RegisterFormBean {
	private String name = "";
	private String password1 = "";
	private String password2 = "";
	private String email = "";
	private Hashtable errors = new Hashtable();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword1() {
		return this.password1;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getPassword2() {
		return this.password2;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public boolean validate() {
		boolean allOk = true;
		if (name.trim().equals("")) {
			errors.put("name", "Please input your name.");
			allOk = false;
		}
		if (password1.length() > 10 || password1.length() < 6) {
			errors.put("password1", "password must have 6-10 characters.");
			allOk = false;
		}
		if (!password2.equals(password1)) {
			errors.put("password2", "passwords do not match.");
			allOk = false;
		}
		/*
		 * 对email格式的校验采用了正则表达式，关于正则表达式的技术细节， 请参看笔者编写的《JavaScript网页开发》一书
		 */
		if (!email.matches("[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+")) {
			errors.put("email", "illegal email.");
			allOk = false;
		}
		return allOk;
	}

	public void setErrorMsg(String err, String errMsg) {
		if ((err != null) && (errMsg != null)) {
			errors.put(err, errMsg);
		}
	}

	public String getErrorMsg(String err) {
		String err_msg = (String) errors.get(err);
		return (err_msg == null) ? "" : err_msg;
	}
}