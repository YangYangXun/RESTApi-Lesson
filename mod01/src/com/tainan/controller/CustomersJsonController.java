package com.tainan.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tainan.dmain.Customers;
//Controller當作整個REST Service撰寫 回應Json 資料
public class CustomersJsonController {
	//回應所有客戶資料的JSON Array String
	private ApplicationContext factory=new ClassPathXmlApplicationContext("applicationContext.xml");
	private List<Customers> customers;
	public String getAllCustomersJson()
	{
		//取出Dao物件
		JdbcTemplate dao=factory.getBean("jdbcTemplate",JdbcTemplate.class);
		//1.找出所有客戶資料
		customers=dao.query("select * from customers", 
				new BeanPropertyRowMapper(Customers.class));
		
		return "success";
	}
	public List<Customers> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customers> customers) {
		this.customers = customers;
	}
	
		

}
