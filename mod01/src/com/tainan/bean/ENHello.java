package com.tainan.bean;

public class ENHello implements IHello{

	@Override
	public String helloWorld(String who) {
		// TODO Auto-generated method stub
		return String.format("%s Hello World!!!",who);
	}

}
