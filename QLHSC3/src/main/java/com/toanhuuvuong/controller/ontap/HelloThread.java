package com.toanhuuvuong.controller.ontap;

import java.util.List;

import com.toanhuuvuong.model.Role;
import com.toanhuuvuong.service.impl.RoleService;

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
		RoleService roleService = new RoleService();
		
		List<Role> roles = roleService.findAll();
		
		for(Role role : roles)
			System.out.println(role.getName());
	}
}
