package com.tainan.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.opensymphony.xwork2.ActionSupport;
import com.tainan.dmain.IDao;
import com.tainan.dmain.MyUsers;
import com.tainan.dmain.Users;
import com.tainan.dmain.UsersDao;

public class RegisterController extends ActionSupport 
implements ServletResponseAware {
	//attribute javabean
	private Users user;
	private HttpServletResponse response;
	//會員註冊javabean
	private MyUsers myusers=new MyUsers();
	//性別項目集合(Javaean)
	private List<String> sexs=new ArrayList<String>();
	//自訂建構子
	public RegisterController()
	{
		sexs.add("女性");
		sexs.add("男性");
	}
	//應用系統
	private ServletContext application=
			ServletActionContext.getServletContext();
	//準備Spring BeanFactory物件
	private ApplicationContext factory=new 
			ClassPathXmlApplicationContext("applicationContext.xml");
	//訊息
	private String message;
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getSexs() {
		return sexs;
	}

	public void setSexs(List<String> sexs) {
		this.sexs = sexs;
	}

	public MyUsers getMyusers() {
		return myusers;
	}

	public void setMyusers(MyUsers myusers) {
		this.myusers = myusers;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String execute() {
		myusers.setUserName("ericchen");
		//派送登入畫面mylogin.html
		return SUCCESS;
	}
	
	//使用者登入驗證method(action)
	public String valid()
	{
		//建構一個DataSource物件
		BasicDataSource dataSource=new BasicDataSource();
		//1.設定url(連接字串)
		dataSource.setUrl(application.getInitParameter("url"));
		//2.載入Driver
		dataSource.setDriverClassName(application.getInitParameter("driverClassName"));
		//3設定登入帳號與密碼
		dataSource.setUsername(application.getInitParameter("userName"));
		dataSource.setPassword(application.getInitParameter("password"));
		//建構DAO物件(兩個物件形成互動)
		IDao<Users> dao=new UsersDao();
		//注入互動物件 DataSource
		dao.setDataSource(dataSource);
		
		boolean r=dao.selectForObject(this.user);
		if(r){
			//驗證通過
			//發出憑證(前端狀態Cookie)
			Cookie cookie=new Cookie("cred",user.getUserName());
			//Cookie必須經由Response(互動???)
			response.addCookie(cookie);
			return "ok";
		}else
		{
			return "failure";
		}
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		
	}
	
	//會員註冊資料新增
	public String usersAdd()
	{
		//0 資料來源? Bean物件IoC 注入控制反轉
		//跟工廠要一個dao物件 (正轉)...依存物件DataSource 進行反轉同時注入
		IDao<MyUsers> dao=factory.getBean("dao",IDao.class);
		try {
			dao.insert(this.myusers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//1.配合SpringFramework 進行正轉與反轉資料維護(元件)架構
		return "success";
	}
	
	//會員資料新增 使用手動的JdbcTemplate(Spring dao)
	public String jdbcTemplateUsersAdd()
	{
		//1.跟工廠直接要DataSource
		DataSource dataSource=factory.getBean("dataSource",DataSource.class);
		//2.建構JdbcTemplate
		JdbcTemplate dao=new JdbcTemplate();
		//3.注入與DataSource依存關係
		dao.setDataSource(dataSource);
		//4.完成新增記錄
		String sql="insert into users(username,password,address,phone,sex,email) values(?,?,?,?,?,?)";
		try{
		int r=dao.update(sql,
				myusers.getUserName(),
				myusers.getPassword(),
				myusers.getAddress(),
				myusers.getPhone(),
				myusers.getSex(),
				myusers.getEmail()
				); //內部走的PreparedStatement 
		return "success";
		}catch(Exception ex)
		{
			message=ex.getMessage();
			return "failure";
		}
	}
	
	//會員資料新增 使用Bean的JdbcTemplate(Spring dao)
		public String jdbcTemplateBeanUsersAdd()
		{
			//1.跟工廠直接要JdbcTemplate
			JdbcTemplate dao=factory.getBean("jdbcTemplate",JdbcTemplate.class);
			
			//4.完成新增記錄
			String sql="insert into users(username,password,address,phone,sex,email) values(?,?,?,?,?,?)";
			try{
			int r=dao.update(sql,
					myusers.getUserName(),
					myusers.getPassword(),
					myusers.getAddress(),
					myusers.getPhone(),
					myusers.getSex(),
					myusers.getEmail()
					); //內部走的PreparedStatement 
			return "success";
			}catch(Exception ex)
			{
				message=ex.getMessage();
				return "failure";
			}
		}
	
	
	
}