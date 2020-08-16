package com.toanhuuvuong.controller.ontap;

import java.util.List;

import com.toanhuuvuong.model.StudentOfClass;
import com.toanhuuvuong.service.impl.StudentOfClassService;

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
		StudentOfClassService roleService = new StudentOfClassService();
		
		List<StudentOfClass> roles = roleService.findAll();
		String format = "%-15s";
		
		for(StudentOfClass role : roles)
			System.out.println(String.format(format, role.getId()));
	}
}
