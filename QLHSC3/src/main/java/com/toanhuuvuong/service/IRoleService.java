package com.toanhuuvuong.service;

import com.toanhuuvuong.model.Role;

public interface IRoleService 
{
	public Role findByCode(String code);
	public Role findByName(String name);
}
