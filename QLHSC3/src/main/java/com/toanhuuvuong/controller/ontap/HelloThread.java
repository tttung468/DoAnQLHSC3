package com.toanhuuvuong.controller.ontap;

import java.util.List;

import com.toanhuuvuong.model.Student;
import com.toanhuuvuong.service.impl.StudentService;

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
		StudentService roleService = new StudentService();
		
		List<Student> roles = roleService.findAll();
		String format = "%-15s";
		
		for(Student role : roles)
			System.out.println(String.format(format, role.getCode()));
	}
}
