package com.tainan.service;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.google.gson.Gson;
import com.tainan.dmain.Customers;
@WebServlet("/customers/custqryid.do")
public class CustomersQryIdServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//設定回應編碼
		response.setCharacterEncoding("UTF-8");
		//設定回應的Content Type 設定Json
		response.setContentType("application/json");
		//使用Spring 工廠
		ApplicationContext factory=new ClassPathXmlApplicationContext("applicationContext.xml");
		//去要Dao
		JdbcTemplate dao=factory.getBean("jdbcTemplate",JdbcTemplate.class);
		String customerid=request.getParameter("cid");
		//進行單筆查詢
		Customers customers=null;
		try{
		customers=dao.queryForObject("select * from customers where customerid=?",
				new Object[]{customerid},
				//巢狀匿名類別 建構一個沒有名稱的類別的物件 類別實作RowMapper介面
		new RowMapper<Customers>()
		{

			@Override
			public Customers mapRow(ResultSet result, int arg1)
					throws SQLException {
				//逐筆進來處理
				Customers customers=new Customers();
				//封裝相對紀錄欄位注入到物件屬性去
				customers.setCustomerID(result.getString("customerid"));
				customers.setCompanyName(result.getString("companyname"));
				customers.setAddress(result.getString("address"));
				customers.setPhone(result.getString("phone"));
				customers.setEmail(result.getString("email"));
				customers.setCountryId(result.getString("countryid"));
				System.out.println(customers.getCompanyName());
				return customers;
			}
			
		}
		
				);
		}catch(Exception ex)
		{
			
		}
		//序列化Customers變成Json字串
		Gson gson=new Gson();
		String jsonString=gson.toJson(customers);
		response.getWriter().print(jsonString);
		
		
		
	}

}
