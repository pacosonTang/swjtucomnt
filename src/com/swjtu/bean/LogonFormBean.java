package com.swjtu.bean;

import java.util.*;

/*LogonFormBean用于封装登录表单的信息，其中定义了两个属性：name和password。
 LogonFormBean对这两个属性进行基本的格式验证，如果有误则将相应的错误信息
 保存到一个HashTable对象中，同时也定义了供JSP页面检索错误信息的方法。*/
public class LogonFormBean {
	private String name = "";
	private String password = "";

	private Hashtable errors = new Hashtable();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public boolean validate() {
		boolean allOk = true;
		if (name.trim().equals("")) {
			errors.put("name", "Please input your name.");
			allOk = false;
		}
		if (password.length() > 10 || password.length() < 6) {
			errors.put("password", "password must have 6-10 characters.");
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