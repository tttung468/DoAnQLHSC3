package com.toanhuuvuong.dao;

import com.toanhuuvuong.model.Role;

public interface IRoleDAO
{
	public Role findByCode(String code);
	public Role findByName(String name);
}