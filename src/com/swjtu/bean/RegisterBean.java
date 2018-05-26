package com.swjtu.bean;

public class RegisterBean {
	private String nickName = null;
	private boolean male = false;
	private String[] specialities = new String[] { "none", "none", "none",
			"none", "none" };

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	public boolean isMale() {
		return male;
	}

	public void setSpecialities(String[] sp) {
		this.specialities = sp;
	}

	public void setSpecialities(int index, String sp) {
		specialities[index] = sp;
	}

	public String[] getSpecialities() {
		return specialities;
	}

	public String getSpecialities(int index) {
		return specialities[index];
	}
}