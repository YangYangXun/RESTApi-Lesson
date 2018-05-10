package com.tainan.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.tainan.dmain.Country;
import com.tainan.dmain.Customers;
//�i��Controller ��l�Ƶ{��
public class CustomersController extends ActionSupport implements  Preparable  {

	private String message;
	//Attribute
	private Customers customers=new Customers();
	//Attribute Country Collection
	private List<Country> countries=new ArrayList<>();
	//Bean Factory
	private ApplicationContext factory=new ClassPathXmlApplicationContext("applicationContext.xml");
	//��l�� �ŰѼƬ[�c�l
	public CustomersController()
	{
		
		
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String execute() {
		
		return SUCCESS;
	}
	public Customers getCustomers() {
		return customers;
	}
	public void setCustomers(Customers customers) {
		this.customers = customers;
	}
	public List<Country> getCountries() {
		return countries;
	}
	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
	
	
	@Override
	public void prepare() throws Exception {
		//��a�O��ƪ�l��
		//1.��X��a�O���(Spring Ioc�`�J������� )
		JdbcTemplate dao=factory.getBean("jdbcTemplate",JdbcTemplate.class);
		//2.�N�d�ߵ��G�ഫ�� List<Country>
		List<Map<String,Object>> result=dao.queryForList("select * from country");
		//���X
		for(Map<String,Object> record :result)
		{
			//�غc��a�O����(Javabean)
			Country country=new Country();
			country.setCountryID(record.get("countryid").toString());
			country.setCountryName(record.get("countryname").toString());
			countries.add(country);
		}
		
	}
	//�g�J�Ȥ���Action
	public String customersSave()
	{
		//�z�LBean Factory���XDAO(JdbcTemplate)
		JdbcTemplate dao=factory.getBean("jdbcTemplate",JdbcTemplate.class);
		//2.�i��O���x�s
		try{
		int result=dao.update(
				//��@����PreparedStatementCreator �P�ɹ�@��k �غc������ �ǻ��i�h
				//�ΦW���O
				new PreparedStatementCreator(){

					@Override
					public PreparedStatement createPreparedStatement(
							Connection connection) throws SQLException {
						//1.�z�LConnection���PreparedStatement����
						String sql="insert into customers(customerid,companyname,address,phone,countryid,email) values(?,?,?,?,?,?)";
						PreparedStatement st=connection.prepareStatement(sql);
						//�]�w�Ѽ�
						st.setString(1,customers.getCustomerID());
						st.setString(2,customers.getCompanyName());
						st.setString(3,customers.getAddress());
						st.setString(4, customers.getPhone());
						st.setString(5,customers.getCountryId());
						st.setString(6, customers.getEmail());
						return st;
					}
					
				}
				);
		if(result>0){
			message="�O���s�W���\";
			return "success";
		}else
		{
			return "failure";
		}
		}catch(Exception ex)
		{
			message="��Ʒs�W����:"+ex.getMessage();
			return "failure";
		}
			
	}
	
	//��X�Ȥ��� ���ͬO�@�Ӥ�r��json 
	public String customersQryByCustomerID()
	{
		return "success";
	}
	
	//�˵��Ҧ��Ȥ���Ation �o�O�@��View
	public String showCustomers()
	{
		return "success";
	}
	
}