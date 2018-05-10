package com.tainan.dmain;
//使用者Javabean
public class Users implements java.io.Serializable {
	//設定Javabean(字串物件)
	private String userName;
	private String password;
	//setXxx 去掉set Xxx回歸匈牙利命名 xxx 稱為property
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
