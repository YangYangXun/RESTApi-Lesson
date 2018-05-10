package com.tainan.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

public class LoadController extends ActionSupport {
	//結合到action result參數<param name="inputName">
	private InputStream fileInputStream;
	
	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public String execute() {
		try {
			//建構一個input讀取串流物件
			fileInputStream=new FileInputStream("c:\\docs\\Struts2.pdf");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
}