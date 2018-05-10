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
		//�]�w�^���s�X
		response.setCharacterEncoding("UTF-8");
		//�]�w�^����Content Type �]�wJson
		response.setContentType("application/json");
		//�ϥ�Spring �u�t
		ApplicationContext factory=new ClassPathXmlApplicationContext("applicationContext.xml");
		//�h�nDao
		JdbcTemplate dao=factory.getBean("jdbcTemplate",JdbcTemplate.class);
		String customerid=request.getParameter("cid");
		//�i��浧�d��
		Customers customers=null;
		try{
		customers=dao.queryForObject("select * from customers where customerid=?",
				new Object[]{customerid},
				//�_���ΦW���O �غc�@�ӨS���W�٪����O������ ���O��@RowMapper����
		new RowMapper<Customers>()
		{

			@Override
			public Customers mapRow(ResultSet result, int arg1)
					throws SQLException {
				//�v���i�ӳB�z
				Customers customers=new Customers();
				//�ʸˬ۹�������`�J�쪫���ݩʥh
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
		//�ǦC��Customers�ܦ�Json�r��
		Gson gson=new Gson();
		String jsonString=gson.toJson(customers);
		response.getWriter().print(jsonString);
		
		
		
	}

}
