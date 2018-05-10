package com.tainan.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.gson.Gson;
import com.tainan.dmain.Customers;

//跟客戶資料有關的服務
@Path("/customers")
public class CustomersService {
	//Spring Bean Factory
	private ApplicationContext factory=new ClassPathXmlApplicationContext("applicationContext.xml");
	//取單一客戶資料(JSON文件格式)
	@Path("/singlecustomers")
	@POST
	@Produces("application/json")
	public String getCustomers()
	{
		//建構客戶物件
		Customers customers=new Customers();
		//注入內容
		customers.setCustomerID("001");
		customers.setCompanyName("巨匠台南");
		customers.setAddress("台南市公園路");
		customers.setEmail("gjun@pcschool.com.tw");
		customers.setPhone("06-1234567");
		customers.setCountryId("01");
		customers.setCreateDate(new Date("2001/1/1"));
		Gson gson=new Gson();
		return gson.toJson(customers); //如何將物件序列化成JSON??
	}
	//面對資料庫進行 客戶編號查詢
	@Path("/customersqrybyid")
	@GET
	@Produces("application/json")
	public String customerQueryById(@QueryParam("cid")String customerid)
	{
		//透過工廠要一個Dao物件
		JdbcTemplate dao=factory.getBean("dao",JdbcTemplate.class);
		//查詢
		List<Customers> customers=(List<Customers>)dao.query("select * from customers where customerid=?",
				new Object[]{customerid},
				new  BeanPropertyRowMapper(Customers.class)
				);
		Gson gson=new Gson();
		return gson.toJson(customers);
	}

}
