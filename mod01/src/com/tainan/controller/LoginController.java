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
		//�����@�̫᪺�e���@�u������y�{
		//�M�w�^���s�X
		request.setCharacterEncoding("big5");
		//�^��������
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		//�i�������...�I�sModel
		//���G
		if(userName.equals("eric"))
		{
			//�ڵ��F
			RequestDispatcher disp=request.getRequestDispatcher("failure.jsp");
			disp.forward(request, response);
		}else
		{
			RequestDispatcher disp=request.getRequestDispatcher("ok.jsp");
			disp.forward(request, response);
		}
		
		
	}
	
}
