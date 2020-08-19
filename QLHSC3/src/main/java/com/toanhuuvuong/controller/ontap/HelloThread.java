package com.toanhuuvuong.controller.ontap;

import java.util.List;

import com.toanhuuvuong.model.Conduct;
import com.toanhuuvuong.service.impl.ConductService;

public class HelloThread extends Thread
{
	@Override
	public void run() 
	{
		super.run();
		
		System.out.println("Hello Thread!");
	}
	public static void main(String[] agrs)
	{
		ConductService roleService = new ConductService();
		
		List<Conduct> roles = roleService.findAll();
		String format = "%-15s";
		
		for(Conduct role : roles)
			System.out.println(String.format(format, role.getId()));
	}
}
