package com.tainan.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tainan.dmain.Customers;
//Controller��@���REST Service���g �^��Json ���
public class CustomersJsonController {
	//�^���Ҧ��Ȥ��ƪ�JSON Array String
	private ApplicationContext factory=new ClassPathXmlApplicationContext("applicationContext.xml");
	private List<Customers> customers;
	public String getAllCustomersJson()
	{
		//���XDao����
		JdbcTemplate dao=factory.getBean("jdbcTemplate",JdbcTemplate.class);
		//1.��X�Ҧ��Ȥ���
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
