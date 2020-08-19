package com.toanhuuvuong.service;

import com.toanhuuvuong.model.ConductType;

public interface IConductTypeService 
{
	public ConductType findByCode(String code);
	public ConductType findByName(String name);
}
