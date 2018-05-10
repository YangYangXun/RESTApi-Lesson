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
//進行Controller 初始化程序
public class CustomersController extends ActionSupport implements  Preparable  {

	private String message;
	//Attribute
	private Customers customers=new Customers();
	//Attribute Country Collection
	private List<Country> countries=new ArrayList<>();
	//Bean Factory
	private ApplicationContext factory=new ClassPathXmlApplicationContext("applicationContext.xml");
	//初始化 空參數架構子
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
		//國家別資料初始化
		//1.找出國家別資料(Spring Ioc注入控制反轉 )
		JdbcTemplate dao=factory.getBean("jdbcTemplate",JdbcTemplate.class);
		//2.將查詢結果轉換成 List<Country>
		List<Map<String,Object>> result=dao.queryForList("select * from country");
		//走訪
		for(Map<String,Object> record :result)
		{
			//建構國家別物件(Javabean)
			Country country=new Country();
			country.setCountryID(record.get("countryid").toString());
			country.setCountryName(record.get("countryname").toString());
			countries.add(country);
		}
		
	}
	//寫入客戶資料Action
	public String customersSave()
	{
		//透過Bean Factory取出DAO(JdbcTemplate)
		JdbcTemplate dao=factory.getBean("jdbcTemplate",JdbcTemplate.class);
		//2.進行記錄儲存
		try{
		int result=dao.update(
				//實作介面PreparedStatementCreator 同時實作方法 建構成物件 傳遞進去
				//匿名類別
				new PreparedStatementCreator(){

					@Override
					public PreparedStatement createPreparedStatement(
							Connection connection) throws SQLException {
						//1.透過Connection獲取PreparedStatement物件
						String sql="insert into customers(customerid,companyname,address,phone,countryid,email) values(?,?,?,?,?,?)";
						PreparedStatement st=connection.prepareStatement(sql);
						//設定參數
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
			message="記錄新增成功";
			return "success";
		}else
		{
			return "failure";
		}
		}catch(Exception ex)
		{
			message="資料新增失敗:"+ex.getMessage();
			return "failure";
		}
			
	}
	
	//找出客戶資料 產生是一個文字檔json 
	public String customersQryByCustomerID()
	{
		return "success";
	}
	
	//檢視所有客戶資料Ation 這是一個View
	public String showCustomers()
	{
		return "success";
	}
	
}