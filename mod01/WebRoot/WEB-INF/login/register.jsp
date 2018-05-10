<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>會員註冊</title>
    
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
   	<legend>會員註冊</legend>
   	<s:form title="會員資料新增" method="post" action="registersave.action">
   		<s:textfield label="識別碼名稱 " maxLength="20" style="width:200px" name="myusers.userName"></s:textfield>
   		<s:password label="密碼" maxLength="20" required="required" style="width:200px" name="myusers.password"></s:password>
   		<s:textfield label="聯絡地址" maxLength="50" style="width:200px" name="myusers.address"></s:textfield>
   		<s:textfield label="連絡電話 " maxLength="45" style="width:200px" name="myusers.phone"></s:textfield>
   		<s:textfield label="EMAIL " maxLength="45" style="width:200px" name="myusers.email"></s:textfield>
   		<s:combobox label="性別 " list="sexs" headerKey="-1" headerValue="--請選擇下列項目--" name="myusers.sex"></s:combobox>
   		<s:submit value="註冊"></s:submit>
   	</s:form>
   </fieldset>
  </body>
</html>
