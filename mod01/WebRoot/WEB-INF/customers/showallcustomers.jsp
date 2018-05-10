<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showallcustomers.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style>
	.headerStyle:{background-color:yellow;}
	</style>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(
		function()
		{
			//挑表格物件
			var table=$('#mytable');
			var tbody=$('#tb');
			$('#header').addClass('headerStyle');
			
			//採用ajax直接呼喚後端的Action(as Service)回應是一個Json String
			$.ajax(
			//JavaScript Object
			{
				url:"customers/allcustomersservice.action",
				type:'GET',
				success:function(result,status,xhr)
				{
					var data=result.customers; //取出JsonArrayObject
					//alert(data);
					//動態add資料
					for(var row in data)
					{
						var cid=data[row].customerID;
						var company=data[row].companyName;
						var address=data[row].address;
						var phone=data[row].phone;
						var countryId=data[row].countryId;
						var email=data[row].email;
						var date=data[row].createDate;
						//動態加一個列與欄
						tbody.last().append('<tr><td>'+cid+'</td><td>'+company+'</td><td>'
						+address+'</td><td>'+phone+'</td><td>'+countryId+'</td><td>'+email+'</td><td>'+date+'</td></tr>');
						
					}
				},
				error:function(xhr,status,error)
				{
					alert(error);
				}
			}
			);
		}
	);
</script>
  </head>
   <body>
    <fieldset>
    	<legend>客戶資料清單</legend>
    	<table id="mytable" style="width:100%;border:"1">
    		<tbody id="tb">
    			<tr id="header">
    				<td>客戶編號</td>
    				<td>公司行號</td>
    				<td>聯絡地址</td>
    				<td>連絡電話</td>
    				<td>國家別</td>
    				<td>Email</td>
    				<td>建立日期</td>
    			</tr>
    		</tbody>
    	</table>
    </fieldset>
  </body>
</html>
