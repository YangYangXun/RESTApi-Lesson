package com.tainan.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

//POJO(Plian Old Java Object)
//哪來的端點/xxx/xxx 
//傳送方式HTTP GET/POST/PUT/DELETE 
//回應內容是哪一種Content Type(MIME)
@Path("/hello")
public class HelloService {
	
	//方法就是服務
	@Path("/helloworld")
	@GET //設定傳送方式
	@Produces("text/html")
	public String helloWorld()
	{
		return "<font size='7' color='red'>Hello world!!!</font>"; //工地要開張了
	}

}
