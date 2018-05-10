package com.tainan.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/valid.do")
public class LoginController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//不提共最後的畫面　只做控制流程
		//決定擷取編碼
		request.setCharacterEncoding("big5");
		//擷取表單欄位
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		//進資料驗證...呼叫Model
		//結果
		if(userName.equals("eric"))
		{
			//拒絕了
			RequestDispatcher disp=request.getRequestDispatcher("failure.jsp");
			disp.forward(request, response);
		}else
		{
			RequestDispatcher disp=request.getRequestDispatcher("ok.jsp");
			disp.forward(request, response);
		}
		
		
	}
	
}
