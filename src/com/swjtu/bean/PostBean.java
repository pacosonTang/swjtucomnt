package com.swjtu.bean;

import java.io.Serializable;

public class PostBean implements Serializable {
	private String rcrd_id;
	private String title;
	private String content;
	
	public String getRcrd_id() {
		return rcrd_id;
	}
	public void setRcrd_id(String rcrd_id) {
		this.rcrd_id = rcrd_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}