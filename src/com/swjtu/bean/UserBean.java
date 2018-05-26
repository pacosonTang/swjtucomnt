package com.swjtu.bean;

import java.io.Serializable;

/**
 * UserBean中仅仅是定义了三个属性：name、password和email。
 * 注意：要存储在Session域中或跨JVM传输的JavaBean应实现Serializable接口
 */
public class UserBean implements Serializable {
	private String name = "";
	private String password = "";
	private String email = "";

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

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public boolean validatePassword(String password) {
		if (this.password.equals(password)) {
			return true;
		} else {
			return false;
		}
	}
}