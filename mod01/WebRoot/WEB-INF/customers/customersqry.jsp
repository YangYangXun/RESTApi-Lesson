<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>客戶資料查詢</title>
	
    <meta name="keywords" content="keyword1,keyword2,keyword3">
    <meta name="description" content="this is my page">
    <meta name="content-type" content="text/html;charset=UTF-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
	<!-- å¼ç¨jquery framework -->
	<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
	<script>
		var panel;
		$(document).ready(
			function(){
				//alert('hi jquery');
				//隱藏結果面板
				panel=$('#result');
				panel.hide();	
				$('#btnSend').click(
					function(e) 
					{
						panel.hide();
						var cid=$('#customerID').val();
						var urlString='custqryid.do?cid='+cid;
						//alert('hi jquery');
						
						$.ajax(
							//JavaScript
							{
								url:urlString,
								type:'GET',
								//callback
								success:function(result,status,xhr)
								{
									if(result==null)
									{
										alert('查無客戶記錄');
									}else
									{
										//鋪資料
										$('#custid').text(result.customerID);
										$('#companyname').text(result.companyName);
										$('#address').text(result.address);
										$('#phone').text(result.phone);
										$('#email').text(result.email);
										$('#country').text(result.country);
										panel.show();
									}
								},
								error:function(xhr,status,error)
								{
									alert(error)
								}
								
							}
						);
						e.preventDefault(); 
					}
				);
			}
		
		);
	</script>
  </head>
  
  <body>
    <fieldset>
    	<legend>客戶資料查詢</legend>
    	<form>
<label>客戶編號</label><input type="text" name="customerID" id="customerID">
<input type="submit" value="查詢" id="btnSend"/>
    	</form>
    	<br/>
    	<div id="result">
    		<table width="100%" border="1">
    			<tr>
    				<td>客戶編號</td>
    				<td><label id="custid"></label></td>
    			</tr>
    			<tr>
    				<td>公司行號</td>
    				<td><label id="companyname"></td>
    			</tr>
    			<tr>
    				<td>聯絡地址</td>
    				<td><label id="address"></td>
    			</tr>
    			<tr>
    				<td>連絡電話</td>
    				<td><label id="phone"></td>
    			</tr>
    			<tr>
    				<td>EMAIL</td>
    				<td><label id="email"></td>
    			</tr>
    			<tr>
    				<td>國家別</td>
    				<td><label id="country"></td>
    			</tr>
    			
    		</table>
    	</div>
    </fieldset>
  </body>
</html>
