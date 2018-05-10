package com.tainan.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tainan.bean.ENHello;
import com.tainan.bean.IHello;
import com.tainan.bean.TWHello;

public class HelloBeanController extends ActionSupport {
	//attribute
	private String message;
	//�غcBean�u�t����clasapath /WEB-INF/classes/�ؿ��U
	private ApplicationContext factory=
			new ClassPathXmlApplicationContext("applicationContext.xml");

	/**
	 * @return
	 */
	public String execute() {
		//�ǲμg�k �ĥΤ����h���� 
		//IHello hello=new TWHello();
		IHello hello=new ENHello();
		message=hello.helloWorld("�i�T��");
		return SUCCESS;
	}
	
	public String myHelloBean() {
		//�z�L�u�t�� ���@�Ӥ���(Bean) Bean ID-Config�t�m
		//�������X�@�Ӱt�m������(����)-�@�ߺ٩I��Object
		IHello hello=factory.getBean("helloBean",IHello.class);
		message=hello.helloWorld("�i�T��");
		return SUCCESS;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}