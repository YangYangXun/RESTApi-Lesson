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
	//�|�����Ujavabean
	private MyUsers myusers=new MyUsers();
	//�ʧO���ض��X(Javaean)
	private List<String> sexs=new ArrayList<String>();
	//�ۭq�غc�l
	public RegisterController()
	{
		sexs.add("�k��");
		sexs.add("�k��");
	}
	//���Ψt��
	private ServletContext application=
			ServletActionContext.getServletContext();
	//�ǳ�Spring BeanFactory����
	private ApplicationContext factory=new 
			ClassPathXmlApplicationContext("applicationContext.xml");
	//�T��
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
		//���e�n�J�e��mylogin.html
		return SUCCESS;
	}
	
	//�ϥΪ̵n�J����method(action)
	public String valid()
	{
		//�غc�@��DataSource����
		BasicDataSource dataSource=new BasicDataSource();
		//1.�]�wurl(�s���r��)
		dataSource.setUrl(application.getInitParameter("url"));
		//2.���JDriver
		dataSource.setDriverClassName(application.getInitParameter("driverClassName"));
		//3�]�w�n�J�b���P�K�X
		dataSource.setUsername(application.getInitParameter("userName"));
		dataSource.setPassword(application.getInitParameter("password"));
		//�غcDAO����(��Ӫ���Φ�����)
		IDao<Users> dao=new UsersDao();
		//�`�J���ʪ��� DataSource
		dao.setDataSource(dataSource);
		
		boolean r=dao.selectForObject(this.user);
		if(r){
			//���ҳq�L
			//�o�X����(�e�ݪ��ACookie)
			Cookie cookie=new Cookie("cred",user.getUserName());
			//Cookie�����g��Response(����???)
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
	
	//�|�����U��Ʒs�W
	public String usersAdd()
	{
		//0 ��ƨӷ�? Bean����IoC �`�J�������
		//��u�t�n�@��dao���� (����)...�̦s����DataSource �i�����P�ɪ`�J
		IDao<MyUsers> dao=factory.getBean("dao",IDao.class);
		try {
			dao.insert(this.myusers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//1.�t�XSpringFramework �i�楿��P�����ƺ��@(����)�[�c
		return "success";
	}
	
	//�|����Ʒs�W �ϥΤ�ʪ�JdbcTemplate(Spring dao)
	public String jdbcTemplateUsersAdd()
	{
		//1.��u�t�����nDataSource
		DataSource dataSource=factory.getBean("dataSource",DataSource.class);
		//2.�غcJdbcTemplate
		JdbcTemplate dao=new JdbcTemplate();
		//3.�`�J�PDataSource�̦s���Y
		dao.setDataSource(dataSource);
		//4.�����s�W�O��
		String sql="insert into users(username,password,address,phone,sex,email) values(?,?,?,?,?,?)";
		try{
		int r=dao.update(sql,
				myusers.getUserName(),
				myusers.getPassword(),
				myusers.getAddress(),
				myusers.getPhone(),
				myusers.getSex(),
				myusers.getEmail()
				); //��������PreparedStatement 
		return "success";
		}catch(Exception ex)
		{
			message=ex.getMessage();
			return "failure";
		}
	}
	
	//�|����Ʒs�W �ϥ�Bean��JdbcTemplate(Spring dao)
		public String jdbcTemplateBeanUsersAdd()
		{
			//1.��u�t�����nJdbcTemplate
			JdbcTemplate dao=factory.getBean("jdbcTemplate",JdbcTemplate.class);
			
			//4.�����s�W�O��
			String sql="insert into users(username,password,address,phone,sex,email) values(?,?,?,?,?,?)";
			try{
			int r=dao.update(sql,
					myusers.getUserName(),
					myusers.getPassword(),
					myusers.getAddress(),
					myusers.getPhone(),
					myusers.getSex(),
					myusers.getEmail()
					); //��������PreparedStatement 
			return "success";
			}catch(Exception ex)
			{
				message=ex.getMessage();
				return "failure";
			}
		}
	
	
	
}