package com.tainan.dmain;
//�ϥΪ�Javabean
public class Users implements java.io.Serializable {
	//�]�wJavabean(�r�ꪫ��)
	private String userName;
	private String password;
	//setXxx �h��set Xxx�^�k�I���Q�R�W xxx �٬�property
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
