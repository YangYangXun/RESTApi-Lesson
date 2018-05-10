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

//��Ȥ��Ʀ������A��
@Path("/customers")
public class CustomersService {
	//Spring Bean Factory
	private ApplicationContext factory=new ClassPathXmlApplicationContext("applicationContext.xml");
	//����@�Ȥ���(JSON���榡)
	@Path("/singlecustomers")
	@POST
	@Produces("application/json")
	public String getCustomers()
	{
		//�غc�Ȥ᪫��
		Customers customers=new Customers();
		//�`�J���e
		customers.setCustomerID("001");
		customers.setCompanyName("���K�x�n");
		customers.setAddress("�x�n�������");
		customers.setEmail("gjun@pcschool.com.tw");
		customers.setPhone("06-1234567");
		customers.setCountryId("01");
		customers.setCreateDate(new Date("2001/1/1"));
		Gson gson=new Gson();
		return gson.toJson(customers); //�p��N����ǦC�Ʀ�JSON??
	}
	//�����Ʈw�i�� �Ȥ�s���d��
	@Path("/customersqrybyid")
	@GET
	@Produces("application/json")
	public String customerQueryById(@QueryParam("cid")String customerid)
	{
		//�z�L�u�t�n�@��Dao����
		JdbcTemplate dao=factory.getBean("dao",JdbcTemplate.class);
		//�d��
		List<Customers> customers=(List<Customers>)dao.query("select * from customers where customerid=?",
				new Object[]{customerid},
				new  BeanPropertyRowMapper(Customers.class)
				);
		Gson gson=new Gson();
		return gson.toJson(customers);
	}

}
