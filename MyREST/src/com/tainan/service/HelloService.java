package com.tainan.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

//POJO(Plian Old Java Object)
//���Ӫ����I/xxx/xxx 
//�ǰe�覡HTTP GET/POST/PUT/DELETE 
//�^�����e�O���@��Content Type(MIME)
@Path("/hello")
public class HelloService {
	
	//��k�N�O�A��
	@Path("/helloworld")
	@GET //�]�w�ǰe�覡
	@Produces("text/html")
	public String helloWorld()
	{
		return "<font size='7' color='red'>Hello world!!!</font>"; //�u�a�n�}�i�F
	}

}
