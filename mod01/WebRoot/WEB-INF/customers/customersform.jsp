<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    
    <title>客戶資料維護</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <fieldset>
    	<legend>客戶資料維護</legend>
    	<s:form action="customerssave.action" method="post">
    		<s:textfield name="customers.customerID" label="客戶編號"/>
    		<s:textfield name="customers.companyName" label="公司行號"/>
    		<s:textfield name="customers.address" label="聯絡地址"/>
    		<s:textfield name="customers.phone" label="連絡電話"/>
    		<s:textfield name="customers.email" label="EMAIL"/>
    		<s:combobox list="countries" name="customers.country" listValue="countryName" listKey="countryID" headerKey="-1" headerValue="--請選擇--" ></s:combobox>
    		<s:submit value="新增"></s:submit>
    	</s:form>
    </fieldset>
  </body>
</html>
