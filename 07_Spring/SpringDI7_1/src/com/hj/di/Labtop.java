package com.hj.di;

import org.springframework.stereotype.Component;

@Component
public class Labtop implements Computer{
	public String getInfo() {
		return "Labtap";
	}
}
