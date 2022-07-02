package com.hj.di;

public class ComputerFactory {
	public static Computer getComputer(String type) {
		if(type.contentEquals("D"))
			return new Desktop();
		else if(type.contentEquals("L"))
			return new Labtop();
		
		return null;
	}
}
