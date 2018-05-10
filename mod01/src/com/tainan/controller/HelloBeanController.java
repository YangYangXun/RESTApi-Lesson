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
	//建構Bean工廠物件clasapath /WEB-INF/classes/目錄下
	private ApplicationContext factory=
			new ClassPathXmlApplicationContext("applicationContext.xml");

	/**
	 * @return
	 */
	public String execute() {
		//傳統寫法 採用介面多型化 
		//IHello hello=new TWHello();
		IHello hello=new ENHello();
		message=hello.helloWorld("張三豐");
		return SUCCESS;
	}
	
	public String myHelloBean() {
		//透過工廠來 取一個元件(Bean) Bean ID-Config配置
		//直接取出一個配置的物件(正轉)-一律稱呼為Object
		IHello hello=factory.getBean("helloBean",IHello.class);
		message=hello.helloWorld("張三豐");
		return SUCCESS;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}