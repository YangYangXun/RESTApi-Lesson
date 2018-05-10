package com.tainan.bean;

public class FRHello implements IHello{

	@Override
	public String helloWorld(String who) {
		// TODO Auto-generated method stub
		return String.format("Bonjour  %s",who);
	}

}
